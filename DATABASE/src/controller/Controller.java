package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.User;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// context.xml의 <Resource>와 연결해줌
	@Resource(name="jdbc/webshop")
	private DataSource ds; // 데이터소스 ds로 DB연결
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); // 한글설정(서블릿으로 한글출력하려면 필요)
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
		if(action == null) {
			// 받아온 action값이 null이면 다시 index.jsp로 
			// 질문. action이 null인데 왜 포워드를 해줘야하지?? => 리퀘스트에 이동할 주소를 입력해준것이므로 포워드를 안해주면 리퀘스트에 주소값이 저장만되고 이동을 못함
			// => forward는 특정주소로 이동시켜주는 역할을 함
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}else if (action.equals("login")){
			// action값이 login일 경우 아래의 속성들(속성이름, 값)이 함께 login.jsp로 전달(forward)됨
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("message", "");
			// 여기서 각 속성값을 공백으로 초기화해주는건 최초요청이 아닐 경우 이미 세 속성에 값이 들어있는데 여기서는 보여줄필요가 없으므로 초기화해주는것임
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}else if (action.equals("createaccount")){
			// action값이 createaccount일 경우 message 속성을 createaccount.jsp로 forward함
			request.setAttribute("message", "");
			request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			
		}else {
			out.println("없는 액션입니다.");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); // 한글설정(서블릿으로 한글출력하려면 필요)
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		
		if(action == null) {
			out.println("알 수 없는 요청입니다.");
			return;
		}
		
		Connection conn = null;	// 톰캣에 커넥터를 추가해줬으므로 Connection을 사용할수있음
		
		try {
			conn = ds.getConnection(); // DB연결
			
		} catch (SQLException e) {
			out.println("DB에 연결 실패...");
			e.printStackTrace();
			return;
		} 
//		out.println("DB연결 테스트 완료!"); // 테스트가 완료되었으므로 주석처리
		Account account = new Account(conn);	// Account클래스 생성 => DB연결을 Account클래스에 줌
		
		if(action.equals("dologin")) {
			// 파라메타로 받아온 email, password값을 문자열로 저장
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			// 문자열로 저장한 이메일과 비밀번호를 리퀘스트에 setAttribute로 저장
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			
			// DB가 들어가는 부분은 try-catch문이 없으면 오류남
			try {
				if(account.login(email, password)) {
					request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);	
				}else {
					request.setAttribute("message", "ID 또는 PASSWORD가 틀립니다.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace(); // 실행시 발생하는 에러를 콘솔에 메시지로 띄워주는 코드. 에러메시지를 보면 어떤에러인지 추측이 가능함.
				request.setAttribute("message", "DB 에러 발생!!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}else if(action.equals("createaccount")) {	// 가입하기 페이지에서 작성 후 가입하기 버튼 클릭 시(이 버튼의 액션이 createaccount임)
			// 각 parameter로 넘어온 값들을 객체에 저장
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatpassword");
			request.setAttribute("email",email);	// 이메일 주소를 request에 저장(나중에 새 계정을 만든 후 만든 이메일주소를 createsuccess.jsp에서 띄워주기 위해 저장)
			
			if(!password.equals(repeatPassword)) {	// 두개의 패스워드가 같지 않을 경우
				request.setAttribute("message", "패스워드가 틀립니다."); // message속성으로 왜 가입하기가 안됐는지를 저장해 
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response); // createaccount.jsp페이지로 포워드함
			}else {
				User user = new User(email, password);	// 새 user객체를 만드는데 email과 password는 위의 변수값를 사용
				
				if(!user.validate()) { // 유효성검사를 불합격 했을때
					request.setAttribute("message", user.getMessage());	// 유저객체 안에 틀렸을때 저장된 메시지를 불러옴 
					request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
				}else {	// 합격했을 땐 email 중복확인 후 새 계정 생성
					try { // 위에서 에러를 throws로 처리했으므로 실제로 에러가 나는 여기서 trycatch문으로 처리해줌
						// account클래스객체에 가서 email을 비교
						if(account.exists(email)) {	// 이메일이 중복되었을 경우
							request.setAttribute("message", "이미 가입된 계정이 있습니다.");
							request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
								// 다시 /createaccount.jsp페이지로 돌려보냄
						}else {
							// 새 계정을 만들기
							account.create(email, password);
							request.getRequestDispatcher("/createsuccess.jsp").forward(request, response);
							
						}
					} catch (SQLException e) {	// sql에러 발생시
						request.setAttribute("message", "SQL에러발생");
						request.getRequestDispatcher("/error.jsp").forward(request, response);
					}
				}
			}
			
		}
		
		try {
			conn.close(); // 실제로는 conn을 닫는것 대신에 connection을 connection pool로 보냄(요청이 올때까지 대기중인 커넥션풀로 보낸다는뜻) 
		} catch (SQLException e) {
			out.println("DB연결 종료과정에서 에러발생...");
		}
	}

}

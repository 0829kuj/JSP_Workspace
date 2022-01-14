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
//		out.println("DB연결 테스트 완료!");
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
		}
		
		try {
			conn.close(); // 실제로는 conn을 닫는것 대신에 connection을 connection pool로 보냄(요청이 올때까지 대기중인 커넥션풀로 보낸다는뜻) 
		} catch (SQLException e) {
			out.println("DB연결 종료과정에서 에러발생...");
		}
	}

}

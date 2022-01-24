package todoApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todoApp.dao.LoginDao;
import todoApp.model.LoginBean;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDao loginDao;	// 로그인 체크 Dao객체
	
	@Override
	public void init() {
		// 서블릿이 생성될 때 실행되는 init()메서드를 오버라이드
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// 로그인 페이지로 이동. send는 페이지 새로열기이므로 모든 입력데이터가 사라짐
		response.sendRedirect("login/login.jsp"); 	// login폴더 안의 login.jsp로 이동. forward가 아니라 단순히 페이지만 이동함 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 입력받을 때 한글설정
		response.setContentType("text/html;charset=UTF-8");	// 출력할 때 한글설정
		// id, password를 parameter로 입력받기
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		// 로그인이 성공했는지 체크
		if (loginDao.validate(loginBean)) {	// true면 계정있음. 로그인됨 => 할일 페이지로 forward
			System.out.println("로그인 성공~!");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);	// 로그인 한 유저네임을 세션에 저장
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
			dispatcher.forward(request, response);
		}else { // false면 계정없음. 로그인 실패
			System.out.println("로그인 실패...");
			request.setAttribute("user", username);		// username은 session에 저장해 다시 보내줌 (비밀번호만 틀렸을때 재입력이 편하게)
			request.setAttribute("message", "Login Fail 로그인에 실패했습니다...");
			// 로그인 실패 내용을 forward로 다시 로그인 페이지에 보여주기(forward를 안해주면 리퀘스트는 새로고침할때 사라짐)
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}

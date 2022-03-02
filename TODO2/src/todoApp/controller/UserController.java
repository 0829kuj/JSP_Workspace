package todoApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoApp.dao.UserDao;
import todoApp.model.User;

/**
 * @author admin
 *
 */
@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao; // 아래서 계속 사용되므로 전역객체를 선언해줌
	
	@Override
	public void init() throws ServletException {
		// 이 메서드는 서블릿이 만들어질 때 한 번만 실행됨
		userDao = new UserDao();	// UserDao클래스를 메서드로 실행해 userDao에 결과를 리턴받아옴
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유저 입력시 post로 입력 데이터를 전달함
		request.setCharacterEncoding("UTF-8"); // 한글설정
		// parameter로 성, 이름, 유저, 비밀번호를 입력받음 
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		User user = new User(firstName, lastName, userName, password);
		// DB에 위의 user를 입력한다.
		
		int result = userDao.registerUser(user);	// 1이 리턴되면 성공, 0이 리턴되면 에러
		if(result == 1) {
			System.out.println("회원등록 완료!");		// 콘솔에 출력
			request.setAttribute("MESSAGE", "회원등록 완료!"); // 웹페이지에서도 볼 수 있게 리퀘스트에 Attribure로 저장
		} else {
			System.out.println("회원등록 실패...");
			request.setAttribute("MESSAGE", "회원등록 실패...");
		}
		
		// 화면을 보여주기 (register.html 페이지를 보여주기)
//		request.getRequestDispatcher("register.jsp").forward(request, response);  한 줄로 작성했을때
		RequestDispatcher dispatcher = request.getRequestDispatcher("register/Register.jsp");
		dispatcher.forward(request, response); 	// 위의 한 줄코드를 두줄로 작성했을 때
		
	}

}

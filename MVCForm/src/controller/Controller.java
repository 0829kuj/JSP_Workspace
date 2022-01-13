package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// 저장된 email, password, valmessage를 초기화 => 로그인 정보는 post로 전송되므로 get에서는 초기화필요 
		request.setAttribute("email", "");
		request.setAttribute("password", "");
		request.setAttribute("valmessage", "");
		
		if(action == null) {
			// action의 값이 없을 경우 잘못된 parameter이므로 다시 index.jsp페이지로 돌아감
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if(action.equals("login")) {
			// action의 값이 login인 경우 login.jsp페이지로 넘어감
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if(action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			// 로그인을 실패했을 때 잘못입력했던 내용을 다시 보여주기 위함
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			
			// javabean클래스의 User임
			User user = new User(email, password);
			
			// 유효성 검사에 합격하면 로그인성공 페이지로
			if(user.validate()) {
				request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
			}else {
				// 유효성 검사에서 실패시 실패메세지를 리퀘스트 객체에 파라메타로 추가
				request.setAttribute("valmessage", user.getMessage()); // 리퀘스트에 추가
				// 유효성 검사에서 실패했으므로 다시 login.jsp페이지로(메세지가 추가된 채로 되돌아가는점이 최초접속과 다름)
				request.getRequestDispatcher("/login.jsp").forward(request, response);	
			}
		}
		
	}

}

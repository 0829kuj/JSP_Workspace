package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gallery")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> actionMap = new HashMap<>();
	public Controller() {
		// 컨트롤러 생성자 (처음 시작할 때 한번 실행). key-value로 한묶음
		actionMap.put("home", "/home.jsp");
		actionMap.put("image", "/image.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// action parameter 불러오기
		String action = request.getParameter("action");
		// 만약 action이 없거나 actionMap에 초기값이 없으면 action에 home을 넣음
		if(action == null || !actionMap.containsKey(action)) action = "home";
		
		// actionMap의 action값이 home이면 home.jsp페이지로, image면 image.jsp페이지로 이동
		request.getRequestDispatcher(actionMap.get(action)).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

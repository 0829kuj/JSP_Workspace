package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

@WebServlet("/pass")
public class PassObjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 각각 다른 user객체(이름, id)들을 다른 scope로 전달
		User user1 = new User("Bob", 1);
		User user2 = new User("Mike", 2);
		User user3 = new User("Sue", 3);
		
		
		// 리퀘스트 스코프 (리퀘스트로 저장)
		request.setAttribute("user1", user1);
		
		// 세션 스코프 (user2를 세션로 저장)
		HttpSession session = request.getSession();
		session.setAttribute("user2", user2);

		// 컨텍스트 스코프 (user3을 컨텍스트로 저장)
		ServletContext context = getServletContext();
		context.setAttribute("user3", user3);
		
		// map list 보내기
		Map<String, String> map = new HashMap<>();
		map.put("fruit1", "apple");
		map.put("fruit2", "orange");
		
		request.setAttribute("mapList", map);
		
		// array list
		List<User> list = new ArrayList<>();
		list.add(new User("dog", 1));
		list.add(new User("cat", 2));
		list.add(new User("cow", 3));
		
		session.setAttribute("list", list);
//		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/recieveObjects.jsp").forward(request, response);
	}

}

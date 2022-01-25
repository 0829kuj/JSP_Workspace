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

@WebServlet("/PassList")
public class Pass2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// array list
		List<User> list = new ArrayList<>();
		list.add(new User("dog", 1));
		list.add(new User("cat", 2));
		list.add(new User("cow", 3));
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/recieveObjects.jsp").forward(request, response);
	}
}

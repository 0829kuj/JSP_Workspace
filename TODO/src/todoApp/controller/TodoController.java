package todoApp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoApp.dao.TodoDao;
import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;

// 주소를 "/"로 해두면 다른 서블릿 "/register, /login"등을 제외한 다른 모든 요청들을 이 서블릿에서 처리한다
@WebServlet("/")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TodoDao todoDAO;
	
	public void init() {
		todoDAO = new TodoDaoImpl();	// 실제 객체는 todoDao를 구현한 TodoDaoImpl로 생성
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	// post로 요청하더라도 get으로 처리

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청주소가 localhost:8090/TODO/new 이면 => "/new"가 action의 값이 된다(ContextPath의 주소 이후만 action에 넣는다는뜻)
		String action = request.getServletPath();
		
		switch(action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertTodo(request, response);
			break;
		case "/delete":
			deleteTodo(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateTodo(request, response);
			break;
		case "/list":	// localhost:8090/TODO/list
			listTodo(request, response);
			break;
		default:	// 요청 주소가 기본(/)이거나 잘못되었을 경우 로그인 페이지로 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);
			break;
		}// switch문 끝
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Todo> listTodo = todoDAO.selectAllTodos();	// DB에서 할일들을 가져와 리스트에 저장 
		request.setAttribute("listTodo", listTodo);		// 리스트를 리퀘스트에 저장
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);	// 위의 listTodo를 request에 저장한 상태로 todo-list.jsp페이지로 forward
	}
}

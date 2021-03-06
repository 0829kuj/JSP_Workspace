package todoApp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todoApp.dao.TodoDao;
import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;

// 주소를 "/"로 해두면 다른 서블릿 "/register, /login"등을 제외한 다른 모든 요청들을 이 서블릿에서 처리한다
@WebServlet("/todos")
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
		String action = request.getParameter("action");	 // 주소가 TODO/todos?=text일때 text의 값을 action에 저장
		System.out.println(action);
		
		switch(action) {
		case "new":
			showNewForm(request, response);
			break;
		case "post":
			insertTodo(request, response);
			break;
		case "delete":
			deleteTodo(request, response);
			break;
		case "edit":	// 수정form을 보여줌
			showEditForm(request, response);
			break;
		case "update":
			updateTodo(request, response);
			break;
		case "list":	// localhost:8090/TODO/todos/list
			listTodo(request, response);
			break;
		default:	// 요청 주소가 기본(/)이거나 잘못되었을 경우, action이 없을때 로그인 페이지로 이동. 로그아웃시에도 여기로 오게된다.
			HttpSession session = request.getSession();
			session.invalidate();	// session에 저장된 로그인 정보를 전체삭제
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);
			break;
		}// switch문 끝
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// todo-list.jsp페이지에서 '할일추가' 버튼을 누르면 action에 new를 받아오며 request, response값을 가지고 showNewForm메서드로 넘어옴
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);	// forward로 request, response값을 가지고 todo/todo-form.jsp 페이지로 이동
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 할일을 추가하는 메서드. 입력한 값들을 받아 DB의 todos테이블에 저장
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String username = (String)session.getAttribute("username");	// session에 저장된 username속성을 불러와 변수username에 저장
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate")); // request parameter에 저장된 문자열인 targetDate속성의 값을 LocalDate로 변환하여 저장
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));	// request parameter에 저장된 문자열인 isDone속성의 값을 boolean으로 변환하여 저장
		
		Todo newTodo = new Todo(title, username, description, targetDate, isDone);	// 우변의 속성들은 바로위에서 저장해둔 변수들임
		
		todoDAO.insertTodo(newTodo); // DB에 입력하는 insertTodo 메서드를 통해 newTodo의 을 DB에 저장
		response.sendRedirect("todos?action=list");	// 위에서 DB에 할을을 저장하고나서 todos의 action값에 list를줘서 다시 DB의 리스트를 받아 화면에 띄우러 감
		
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제기능은 js를 통해 한번 확인작업을 거치는데 자바스크립트가 먼저 동작하기때문에 confirm창에서 확인을 눌러야 여기까지넘어오게 된다
		Long id = Long.parseLong(request.getParameter("id"));	// id를 받음
		todoDAO.deleteTodo(id);
		response.sendRedirect("todos?action=list");	// 삭제 후 다시 todo-list.jsp페이지로 돌아감
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Todo theTodo = todoDAO.selectTodo(id);
		// 수정할 todo객체를 같이 보냄
		request.setAttribute("todo", theTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		// 업데이트 시에는 새로입력과 달리 id가 입력됨
		Long id = Long.parseLong(request.getParameter("id"));
		String title = request.getParameter("title");
		String username = (String)session.getAttribute("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		Todo oldTodo = new Todo(id, title, username, description, targetDate, isDone);	// 우변의 속성들은 바로위에서 저장해둔 변수들임
		todoDAO.updateTodo(oldTodo);
		
		response.sendRedirect("todos?action=list"); // 할 일을 수정한 후 todo-list.jsp페이지로 이동
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Todo> listTodo = todoDAO.selectAllTodos();	// DB에서 할일들을 가져와 리스트에 저장 
		request.setAttribute("listTodo", listTodo);		// 리스트를 리퀘스트에 저장
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);	// 위의 listTodo를 request에 저장한 상태로 todo-list.jsp페이지로 forward
	}
}

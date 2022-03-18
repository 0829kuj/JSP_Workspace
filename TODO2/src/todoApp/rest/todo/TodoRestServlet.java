package todoApp.rest.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import todoApp.dao.TodoDao;
import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;
import todoApp.model.User;


//요청 공통 URL주소
//http://localhost:8090/TODO2/api/todo/

// * : 0개이상의 속성(0개도 포함됨)
@WebServlet(urlPatterns = "/api/todo/*", loadOnStartup = 1)
public class TodoRestServlet extends HttpServlet {

	// 인터페이스로 정의 후 구현을 하면 나중에 수정할 경우에도 객체만 수정하면됨. 단, 처리할사항이 있으면 부가코드가 필요할수있음
	private TodoDao todoDao = new TodoDaoImpl();
	private Gson gson = new Gson();

	@Override
	public void init() throws ServletException {
		System.out.println("TodoRestServlet init() 호출됨");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		gson = gsonBuilder.setPrettyPrinting().create();	// gson이 출력될때 결과를 줄바꿈하여 보기 편하게 해줌
	}


	private void sendResponse(String strJson, HttpServletResponse resp) throws IOException {
		// string으로 문자열을 받아 json으로 보냄
		resp.setContentType("application/json; charset=UTF-8"); // 가져올 정보를 미리 정해둔 후 아래 PrintWriter로 출력함. 순서어기면 안됨.
		PrintWriter out = resp.getWriter();
		out.print(strJson);
//		out.flush();
		out.close();
	} // sendResponse

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TodoRestServlet doGet() 호출됨");
		// 레코드 한 개 조회
		// http://localhost:8090/TODO2/api/todo?category=one&id=1

		// 레코드 특정 사용자 데이터만(여러개) 조회
		// http://localhost:8090/TODO2/api/todo?category=me&username=hong

		// 레코드 전체(여러개) 조회
		// http://localhost:8090/TODO2/api/todo?category=all

		String category = req.getParameter("category");
		String strJson = "";

		if (category.equals("one")) {
			strJson = processGetOne(req);
		} else if (category.equals("me")) {
			strJson = processGetMe(req);
		} else if (category.equals("all")) {
			strJson = processGetAll(req);
		}

		// if문을 거치며 리턴받아온 값(json형태)을 보내줌
		sendResponse(strJson, resp);

	} // doGet

	private String processGetOne(HttpServletRequest req) {
		// id에 해당하는 하나의 todo레코드만 조회

		// getParameter는 String으로 받아야함(변경이 필요한 경우 받은 후에 변경)
		String strId = req.getParameter("id");
		long id = Long.parseLong(strId); // "5" -> 5L

		Todo todo = todoDao.selectTodo(id);

		TodoOneResult todoOneResult = new TodoOneResult();
		if (todo != null) {
			todoOneResult.setHasResult(true);
			todoOneResult.setTodo(todo);
		} else { // todo == null
			todoOneResult.setHasResult(false);
		}

		// String을 json으로 변경하는 작업
		String strJson = gson.toJson(todoOneResult);
		System.out.println("strJson : " + strJson);

		return strJson;
	} // processGetOne

	private String processGetMe(HttpServletRequest req) {
		// username에 해당하는 레코드들만 조회
		String username = req.getParameter("username");

		User user = todoDao.getUserAndTodos(username);

		TodoListResult todoListResult = new TodoListResult();

		if (user != null) {
			todoListResult.setHasResult(true);
			todoListResult.setTotalCount(user.getTodoList().size());
			todoListResult.setUser(user);
		} else { // user == null	 // 리턴된 user객체가 null이면 결과값이 없는것
			todoListResult.setHasResult(false);
			todoListResult.setTotalCount(0);
		}
		
		String strJson = gson.toJson(todoListResult);
		System.out.println("strJson : " + strJson);
		return strJson;
	} // processGetMine
	

	private String processGetAll(HttpServletRequest request) {

		List<User> userList = todoDao.getAllUsersAndTodoCount();
		
		TodoListUsersResult result = new TodoListUsersResult();
		
		if (userList.size() > 0) {
			result.setHasResult(true);
			result.setTotalCount(userList.size());
			result.setUserList(userList);
		} else {
			result.setHasResult(false);
			result.setTotalCount(userList.size());
		}
		
		String strJson = gson.toJson(result);
		System.out.println("strJson : " + strJson);
		return strJson;
	} // processGetAll

	/*
	 * post json형식 { "title" : "오늘의 할일", "username" : "hong", "description" :
	 * "할일이있습니다...", "targetDate" : { "year": 2022, "month": 3, "day": 5 }, "status"
	 * : false }
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TodoRestServlet doPost() 호출됨");

		req.setCharacterEncoding("utf-8"); // 한글깨지지않도록 유니코드 변환 처리

		BufferedReader reader = req.getReader(); // 문자 입력스트림 가져오기
		
//		StringBuilder sb - new StringBuilder();
//		String line = 
		
		
		
		
		

		Todo todo = gson.fromJson(reader, Todo.class); // JSON문자열로부터 Todo 자바객체로 변환
		System.out.println(todo.toString());

		todoDao.insertTodo(todo); // 새로운 할일 하나 추가
		
		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);

		String strJson = gson.toJson(todoResult);

		sendResponse(strJson, resp);

	} // doPost

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TodoRestServlet doPut() 호출됨");

		req.setCharacterEncoding("utf-8"); // 한글깨지지않도록 유니코드 변환 처리

		BufferedReader reader = req.getReader(); // 문자 입력스트림 가져오기

		Todo todo = gson.fromJson(reader, Todo.class); // JSON문자열로부터 Todo 자바객체로 변환
		System.out.println(todo.toString());

		todoDao.updateTodo(todo); // 할일 하나 수정

		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		
		sendResponse(strJson, resp);
	} // doPut
	

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TodoRestServlet doDelete() 호출됨");
		// 레코드 한 개 삭제
		// http://localhost:8090/TODO2/api/todo?category=one&id=1

		// 레코드 특정 사용자 데이터만(여러개) 삭제
		// http://localhost:8090/TODO2/api/todo?category=mine&username=hong

		String category = req.getParameter("category");
		String strJson = "";
		
		if (category.equals("one")) {
			strJson = processDeleteOne(req);
		} else if (category.equals("mine")) {
			strJson = processDeleteMine(req);
		}
		
		sendResponse(strJson, resp);
	} // doDelete
	

	private String processDeleteOne(HttpServletRequest req) {
		// id에 해당하는 레코드 하나를 삭제
		String strId = req.getParameter("id"); // 삭제할 글번호(id) 를 가져옴
		long id = Long.parseLong(strId);

		todoDao.deleteTodo(id); // 가져온 id에 해당하는 글 하나 삭제
		
		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		return strJson;
	} // processDeleteOne
	
	
	private String processDeleteMine(HttpServletRequest req) {
		// username가 같은 레코드를 모두 삭제
		String username = req.getParameter("username"); // 삭제할 username

		todoDao.deleteTodo(username); // username에 해당하는 글 모두 삭제

		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		return strJson;
	} // processDeleteMine


	@Override
	public void destroy() {
		System.out.println("TodoRestServlet destroy() 호출됨");
	}
	
}

package todoApp.restservelet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import todoApp.dao.UserDao;
import todoApp.model.User;

// REST API 서버 요청방식 - 개별 CRUD 작업과 매칭해서 사용함
// - GET : 조회(Read. DB의 select문)
// - POST : 생성(Creat. DB의 insert문)
// - PUT : 수정(Update. DB의 update문)
// - DELETE : 삭제(Delete. DB의 delete문)

// 요청 공통 URL주소
// http://localhost:8090/TODO/api/user/

@WebServlet(urlPatterns = "/api/user/*", loadOnStartup = 1)
public class UserRestServlet extends HttpServlet {
	
	private ServletContext application;
	private UserDao userDao = new UserDao();
	private Gson gson = new Gson();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()호출됨");
		
		// application : 애플리케이션당 한개 유지되는 영역객체		
		application = config.getServletContext();

		// 요청횟수 totalCount
		application.setAttribute("totalCount", 0);
	}
	
	private void addCount() {
		int totalCount = (Integer) application.getAttribute("totalCount");
		totalCount++;
		application.setAttribute("totalCount", totalCount);
		
		System.out.println("\"/api/user/*\" 요청횟수: " + totalCount);
	}
	
	
	@Override
	public void destroy() {
		System.out.println("destroy()호출됨");
		// 정리 작업을 할때 필요
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet() 호출됨");
		addCount();
		
		// 레코드 한 개 조회
		// http://localhost:8090/TODO/api/user?category=one&userName=hong
		
		// 레코드 전체(여러개) 조회
		// http://localhost:8090/TODO/api/user?category=list
		
		String category = req.getParameter("category");
		
		String strJson = "";
		
		if (category.equals("one")) {
			strJson = processGetOne(req);
		} else if (category.equals("list")) {
			strJson = processGetList(req);
		}	
		sendResponse(strJson, resp);

	} // doGet


	private void sendResponse(String strJson, HttpServletResponse resp) throws IOException {
//		resp.setContentType("text/html");	// html로 응답을 줄 경우
		resp.setContentType("application/json; charset=UTF-8");	// 가져올 정보를 미리 정해둔 후 아래 PrintWriter로 출력함. 순서어기면 안됨. 
		PrintWriter out = resp.getWriter();
		out.print(strJson);
//		out.flush();
		out.close();
		
	} //sendResponse
	
	
	private String processGetOne(HttpServletRequest req) throws ServletException, IOException {
		// 하나의 레코드를 가져올때
		String userName = req.getParameter("userName");
		
		User user = userDao.getUserByUserName(userName);
		// XML 또는 JSON 문자열로 응답을 줌
		
		UserOneResult userOneResult = new UserOneResult();
		
		if (user != null) {	// 결과가 있으면 user에 user를 담고 없으면 null을 담는다
			userOneResult.setHasResult(true);
			userOneResult.setUser(user);
			
		}else {
			userOneResult.setHasResult(false);
		}
		
		// Gson을 이용해서 userOneResult 자바객체를 JSON 형식의 문자열로 변환하기
		String strJson = gson.toJson(userOneResult);	// 자바객체를 json형식의 문자열로 바꾸어 리턴
		System.out.println("strJson : " + strJson);
		
		return strJson;
	} // processGetOne

	
	private String processGetList(HttpServletRequest req) {
		// 복수의(혹은 전체) 레코드를 가져올때
		List<User> userList = userDao.getAllUsers();
		
		UserListResult userListResult = new UserListResult();
		if (userList.size() > 0) {	// 값이 하나라도 있을때
			userListResult.setHasResult(true);
			userListResult.setUserList(userList);
			userListResult.setTotalCount(userList.size());
		} else {	// userList.size() == 0 
			userListResult.setHasResult(false);
			userListResult.setTotalCount(userList.size());
		}
		
		String strJson = gson.toJson(userListResult);
		System.out.println("strJson" + strJson);
		
		
		return strJson;
	} // processGetList
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost() 호출됨");
		addCount();
		
		req.setCharacterEncoding("utf-8");	// 한글깨지지않도록 유니코드 변환 처리
		
		
		BufferedReader reader = req.getReader();	// 문자 입력스트림 가져오기
		User user = gson.fromJson(reader, User.class); // JSON문자열로부터 User 자바객체로 변환
		
		System.out.println(user.toString());
		
		userDao.registerUser(user); // 신규회원 추가
		// 신규회원을 추가할때 추가 후 제대로 추가가 되었는지 응답을 주어 확인하는 용도로 UserResult클래스를 만들어 true가 오면 제대로 작동한것으로 간주함
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);	// true값을 받으면 요청한 작업이 제대로 완료되었음을 확인할 수 있도록 해줌
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, resp);
		
	} // doPost

	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPut() 호출됨");
		addCount();
		
		
		req.setCharacterEncoding("utf-8");	// 한글처리
		
		BufferedReader reader = req.getReader();	// 문자 입력스트림 가져오기
		User user = gson.fromJson(reader, User.class); // JSON문자열로부터 User 자바객체로 변환
		// 파라미터 값 가져오기 -> User 객체로 준비
//		User user = new User();
//		user.setFirstName(req.getParameter("firstName"));
//		user.setLastName(req.getParameter("lastName"));
//		user.setUsertName(req.getParameter("userName"));
		System.out.println(reader.toString());
		
		userDao.update(reader); // 회원정보 수정
		
		// 신규회원을 추가할때 추가 후 제대로 추가가 되었는지 응답을 주어 확인하는 용도로 UserResult클래스를 만들어 true가 오면 제대로 작동한것으로 간주함
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);	// true값을 받으면 요청한 작업이 제대로 완료되었음을 확인할 수 있도록 해줌
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, resp);
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doDelete() 호출됨");
		addCount();
		
		
	}
	
} // class UserRestServlet

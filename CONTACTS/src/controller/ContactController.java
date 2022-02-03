package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.ContactDao;
import model.Contact;

@WebServlet("/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContactDao contactDao;

	@Resource(name = "jdbc/demo")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		contactDao = new ContactDao(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		DB연결 테스트용 출력
//		List<Contact> list = contactDao.findAll();
//		list.forEach(contact -> System.out.println(contact.toString()));
//		contactDao.delete(1);	// 삭제
//		System.out.println(contactDao.find(1));	// 검색
//		Contact contact = new Contact(1, "mark", "mark@naver.com", "010-1111-2222");	// 업데이트
//		contactDao.update(contact);
//		Contact contact = new Contact(51, "markK", "markk@gmail.com", "010-1234-5678");	// 저장
//		contactDao.save(contact);
		
		request.setCharacterEncoding("UTF-8");
		// 파라메터가 cmd값을 읽어서 액션으로 저장하는데 만약 값이 null이면 "list"로 대체함 (값이 있으면 그대로 저장)
		String action = request.getParameter("cmd") != null ? request.getParameter("cmd") : "list";
		
		try {
			
			switch (action) {
			case "post":		// 새로입력 저장
				save(request, response);
				break;
			case "edit":		// 수정하기 창을 표시
				edit(request, response);
				break;
			case "update":		// 실제 수정하는 작업
				update(request, response);
				break;
			case "del":			// 삭제
				delete(request, response);
				break;
			default:			// 전체 연락처를 화면에 테이블로 표시
				list(request, response);
				break;
			}
		}finally {
			
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contact> contacts = contactDao.findAll();	// DB의 모든 연락처 가져오기
		request.setAttribute("contacts", contacts); 	// "contacts"에는 key값, contacts에는 실제 값이 저장됨
		RequestDispatcher rd = request.getRequestDispatcher("contact/list.jsp");	// forward해주기 위해 RequestDispatcher로 리퀘스트를 유지해줌 
		rd.forward(request, response);	// request에 저장된 contacts를 유지하며 list.jsp페이지로 이동
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

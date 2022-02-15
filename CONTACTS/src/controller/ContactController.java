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
import utills.Json;

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
//		System.out.println(contactDao.find(11));	// 검색
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

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 모달창에서 새 연락처를 입력받아 저장하는 작업
		Contact contact = new Contact();
		
		contact.setName(request.getParameter("name"));
		contact.setEmail(request.getParameter("email"));
		contact.setPhone(request.getParameter("phone"));
		
		boolean isSaved = contactDao.save(contact);		// ture이면 저장성공, false이면 실패
		
		if(isSaved) {
			System.out.println("입력 완료");
			new Json(response).sendMessage(true, "연락처 입력됨");	// ajax로 결과를 받기 위해 추가함
		}
//		list(request, response);	// 다시 리스트화면 출력 (ajax를 사용하지 않을때 사용)
		

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));	// 문자열id를 int변환
		
		Contact contact = contactDao.find(id);	// id로 연락처 객체 찾기
		if(contact != null) {
			System.out.println("찾기 완료");
			new Json(response).sendContact(contact);	// 연락처와 상태를 json으로 변환해 출력
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		Contact contact = new Contact();
		contact.setId(Integer.parseInt(request.getParameter("id")));
		contact.setName(request.getParameter("name"));
		contact.setEmail(request.getParameter("email"));
		contact.setPhone(request.getParameter("phone"));
		
		boolean isUpdated = contactDao.update(contact);		// ture이면 저장성공, false이면 실패
		
		if(isUpdated) {
			System.out.println("수정 완료");
			new Json(response).sendMessage(true, "연락처 수정됨");	// ajax로 결과를 받기 위해 추가함
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));	// id를 가져옴
		boolean isDeleted = contactDao.delete(id);
		if(isDeleted) {
			System.out.println("삭제 완료");
			new Json(response).sendMessage(true, "연락처 삭제됨");
		}

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

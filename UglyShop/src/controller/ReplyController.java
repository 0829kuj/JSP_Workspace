package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.ReplyDao;
import model.Reply;


@WebServlet("/replyController")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReplyDao replyDao;
	
	@Resource(name = "jdbc/shop")
	private DataSource datasource;

    @Override
	public void init() throws ServletException {
		// 서블릿 컨테이터가 서블릿 객체를 생성한 후 맨 처음 딱 한번만 호출되는 메서드. ReplyDao의 datasource를 가져와 replyDao객체에 저장하여 사용
		replyDao = new ReplyDao(datasource);
	}


	public ReplyController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// CRUD테스트용
//		Reply reply1 = new Reply("farmer03","리뷰내용가나다라마바사아자차",789);
//		replyDao.save(reply1);		// 입력 테스트용
		
		System.out.println(replyDao.find(2));		// 찾기 테스트용

//		System.out.println(replyDao.delete(3));		// 삭제테스트용. true가 나와야하는데...
		
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("cmd") != null ? request.getParameter("cmd") : "list";
		
		try {
			switch (action) {
			case "find":
				find(request, response);
				break;
			case "edit":
				edit(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			default:
				find(request, response);
				break;
			}
		} catch (Exception e) {
			System.out.println("잘못된 접근입니다.");
		}
	}

	// review테이블의 farmID와 현재 로그인한 famrID와 비교하는 부분은 이 서블릿의 각 메서드에서 만들어줄것. 우선은 확인해야하니 빼고만듦.
	// 클릭한 reviewID와 같은 하나의 덧글만 출력되므로 일반적인 경우의 find메서드와 동일하다고 생각하면 됨 => 기본형을 find로 수정하자
	
	private void find(HttpServletRequest request, HttpServletResponse response) {
		// 리뷰ID가 같은 reply테이블의 행이 있을 경우 덧글을 보여준다
		int id = Integer.parseInt(request.getParameter("replyID"));
		
		Reply reply = replyDao.find(id);
		if(reply != null) {
			System.out.println("덧글 찾기 완료");
			System.out.println(reply);
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// 덧글작성버튼을 눌렀을때 덧글작성메서드가 실행되도록
		Reply reply = new Reply();

		reply.setReplyID(Integer.parseInt(request.getParameter("replyID")));
		reply.setFarmID(request.getParameter("farmID"));
		reply.setReplyContant(request.getParameter("replyContant"));
		reply.setReviewID(Integer.parseInt(request.getParameter("reviewID")));

	}


	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// 덧글삭제버튼을 눌렀을때 덧글삭제메서드가 실행되도록
		int id = Integer.parseInt(request.getParameter("replyID"));	// replyID를 가져옴
		boolean isDeleted = replyDao.delete(id);
		if(isDeleted) {
			System.out.println("삭제왼료@@");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

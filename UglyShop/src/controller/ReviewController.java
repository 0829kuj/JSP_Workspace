package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.ReplyDao;
import dao.ReviewDao;
import model.Reply;
import model.Review;


@WebServlet("/reviewController")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewDao reviewDao;
	private ReplyDao replyDao;
	
	@Resource(name = "jdbc/shop")
	private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		reviewDao = new ReviewDao(datasource);
		replyDao = new ReplyDao(datasource);
	}
       

    public ReviewController() {
        super();
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 전체출력 테스트용
//		List<Review> list = reviewDao.findAll();
//		list.forEach(review -> System.out.println(review.toString()));
//		System.out.println(reviewDao.find(1));
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("cmd") != null ? request.getParameter("cmd") : "list";
		
		try {
			
			switch (action) {
//			case "post":		// 새로입력 저장
//				save(request, response);
//				break;
//			case "edit":		// 수정하기 창을 표시
//				edit(request, response);
//				break;
//			case "update":		// 실제 수정하는 작업
//				update(request, response);
//				break;
//			case "delete":		// 삭제
//				delete(request, response);
			case "view":		// 리뷰상세페이지에 들어갔을때 
				view(request, response);
				break;
			
			default:
				list(request, response);
				break;
			}
		} finally {}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 모든 리뷰를 리스트형태로 보여줄 메서드
		List<Review> reviews = reviewDao.findAll();	// DB에서 모든 리뷰를 가져옴
		request.setAttribute("reviews", reviews); 	// "reviews"에는 key값, reviews에는 실제 값이 저장됨
		RequestDispatcher rd = request.getRequestDispatcher("reviewFar.jsp");	// forward해주기 위해 RequestDispatcher로 리퀘스트를 유지함
		rd.forward(request, response);
		
	}


	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리뷰상세페이지에 접속했을때 해당리뷰 하나의 모든 정보를 나열하도록
		int id = Integer.parseInt(request.getParameter("id"));
		
		Review review = reviewDao.find(id);
		Reply reply = replyDao.find(id);
		
		if(review != null) {
			request.setAttribute("review", review);
			
			if(reply != null) {
				request.setAttribute("reply", reply);
			}

			RequestDispatcher rd = request.getRequestDispatcher("reviewDetailFar.jsp");	// forward해주기 위해 RequestDispatcher로 리퀘스트를 유지함
			rd.forward(request, response);
			System.out.println("리뷰상세정보찾기 성공");
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

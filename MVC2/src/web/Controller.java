package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		// 서블릿이 생성될 때 한 번 실행
		super.init();
	}
	public void destroy() {
		// 서블릿 종료시 한 번 실행
		super.destroy();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String page = null;
		// index.jsp에서 받은 action값에 따라 처리		
		if(action == null) {
			page = "/error.jsp";
		}else if(action.equals("login")) {
			page = "/login.jsp";
		}else if(action.equals("about")) {
			page = "/about.jsp";
		}else {
			page = "/error.jsp";
		}
		// 서블릿컨텍스트페이지(MVC2) > 리퀘스트를 받을 페이지(login.jsp) > forward방식으로 page에 보냄		
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}

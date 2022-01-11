package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServlet을 상속한 클래스 = 서블릿
@WebServlet("/hi")
public class Hi_jsp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 주소url로 요청시
		// get 요청(request, req), 응답(response, res)
//		super.doGet(req, resp);

		resp.setContentType("text/html;charset=UTF-8");	// 한글설정
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<b>Hi<b><br>");
		out.println("<b>반갑습니다.<b>");
		out.println("<html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form요청시 예-로그인(주소창에 표시되지 않음)
		super.doPost(req, resp);
	}
	
}

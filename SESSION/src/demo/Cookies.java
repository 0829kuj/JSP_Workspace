package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cookies")
public class Cookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); // 프린터객체생성
		
		out.println("<html>");
		
		Cookie[] cookies = request.getCookies(); // 쿠기배열 생성
		
		if (cookies == null) {
			// 쿠키배열에 값이 null값일 경우
			out.println("No cookies found<br/>"); // 쿠키없음 출력
		} else {
			// 쿠키배열에 값이 있을 경우 => 반복문을 이용해 배열이 끝날때까지 각 이름과 값을 name과 value에 저장해 같이 출력
			for (Cookie retrievedCookie : cookies) {
				String name = retrievedCookie.getName();
				String value = retrievedCookie.getValue();
				
				out.println(name+" = "+value+"<br/>");
			}
		}
		// user, Son의 이름과 값을 가진 c1쿠키를 생성
		Cookie c1 = new Cookie("user", "Son");
		Cookie c2 = new Cookie("user2", "Kim");
		
		c1.setMaxAge(300);	// 쿠키의 유효시간: 300초
		c2.setMaxAge(300);
		response.addCookie(c1);	// c1을 쿠키에 저장
		response.addCookie(c2);
		out.println("Cookie save.<br/>");
		out.println("<html>");
		
		// 실행해보면 최초실행땐 Cookie배열에 아무것도 없으므로 쿠키없음이 출력되지만 새로고침을 해주면 생성한 c1이 나온다 
		
	}
}

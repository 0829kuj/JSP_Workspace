package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Connect")
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); // 한글설정(서블릿으로 한글출력하려면 필요)
		PrintWriter out = response.getWriter();
		
		Connection conn = null;	// 톰캣에 커넥터를 추가해줬으므로 Connection을 사용할수있음
		
		try {
			// 0. 드라이버 로딩 (생략가능. JDK6버전 이하는 반드시 필요)
			Class.forName("com.mysql.jdbc.Driver");
			// 1. DB연결
			conn = DriverManager.getConnection( 	// DB주소(SSL속성은 번거로우니 꺼줌), 유저id, 비밀번호
					"jdbc:mysql://localhost:3306/webshop?useSSL=false", "root", "1234");
			
		} catch (SQLException e) {
			// DB를 찾지 못했을때 발생하는 예외
			out.println("DB에 연결 실패...");
			return;
		} catch (ClassNotFoundException e) {
			// 드라이버를 못찾을때 발생하는 예외
			out.println("드라이버 클래스를 찾을 수 없습니다.");
			return;
		}
		// 실패없이 여기까지 내려오면 성공
		out.println("DB연결 테스트 완료!");
		
		try {
			conn.close(); // conn종료
		} catch (SQLException e) {
			out.println("DB연결 종료과정에서 에러발생...");
		}
	}
}

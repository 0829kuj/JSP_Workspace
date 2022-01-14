package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/DatasourceDemo")
public class DatasourceDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// context.xml의 <Resource>와 연결해줌
	@Resource(name="jdbc/webshop")
	private DataSource ds; // 데이터소스 ds로 DB연결
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); // 한글설정(서블릿으로 한글출력하려면 필요)
		PrintWriter out = response.getWriter();
		
		Connection conn = null;	// 톰캣에 커넥터를 추가해줬으므로 Connection을 사용할수있음
		
		try {
			conn = ds.getConnection(); // DB연결
			
		} catch (SQLException e) {
			out.println("DB에 연결 실패...");
			e.printStackTrace();
			return;
		} 
		out.println("DB연결 테스트 완료!");
		
		try {
			conn.close(); // 실제로는 conn을 닫는것 대신에 connection을 connection pool로 보냄(요청이 올때까지 대기중인 커넥션풀로 보낸다는뜻) 
		} catch (SQLException e) {
			out.println("DB연결 종료과정에서 에러발생...");
		}
	}
}

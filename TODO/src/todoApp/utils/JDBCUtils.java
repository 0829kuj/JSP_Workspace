package todoApp.utils;
// DB연결을 도와주는 클래스

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {
	// demo 데이터베이스 사용, useSSL=false : SSL인증을 사용하지 않음
	private static String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "1234";
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	// 0번
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);	// 1번
			// jdbcURL, jdbcUsername, jdbcPassword을 매개변수로 커넥션함
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾지못함...");
		} catch (SQLException e) {
			System.out.println("SQL문 에러발생!");
		}
		// DB연결 성공
		return conn;	// DB에 연결하여 커넥션을 받아옴
	}
	
	// 일반 날짜(java날짜)를 sql날짜로 변경함
	public static Date getSQLDate(LocalDate date) {	 
		return java.sql.Date.valueOf(date);
	}
	// sql날짜를 일반날짜(java날짜)로 변경
	public static LocalDate getUtilDate(Date sqlDate) {
		return sqlDate.toLocalDate();
	}
}
	
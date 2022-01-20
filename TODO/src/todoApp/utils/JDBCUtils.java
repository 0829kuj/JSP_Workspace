package todoApp.utils;
// DB연결을 도와주는 클래스

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {
	// 간단히 연결을 사용하기위해 모두 static으로 선언
	// demo 데이터베이스 사용, useSSL=false : SSL인증을 사용하지 않음
	private static String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "1234";
	
//	public static void main(String[] args) {	// DB연결 테스트용
//		Connection conn = getConnection();
//	}
	
	// DB연결을 위한 객체 선언
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	// 0번. 드라이버 로드
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);	// 1번. 
			// jdbcURL, jdbcUsername, jdbcPassword을 매개변수로 커넥션함 (이 세가지는 위에 private static으로 선언해둠)
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾지못함...");
		} catch (SQLException e) {
			System.out.println("SQL문 에러발생!");
		}
		// DB연결 성공
		System.out.println("DB연결 테스트 성공!");	// DB와 '연결'만 성공되었는지 확인. 작업은 안했음.
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
	
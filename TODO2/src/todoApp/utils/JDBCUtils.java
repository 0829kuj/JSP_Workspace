package todoApp.utils;
// DB연결을 도와주는 클래스

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;


public class JDBCUtils {
	// 간단히 연결을 사용하기위해 모두 static으로 선언
	// demo 데이터베이스 사용, useSSL=false : SSL인증을 사용하지 않음
	private static String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "1234";
	
		
    public static void main(String[] args) throws SQLException {
		Connection conn = getConnection();
	}
    
    public static Connection getConnection() throws SQLException {
    	Connection conn = null;
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");	 //0
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 못찾음"); // jar파일 찾지 못함
		}
//			}catch(SQLException e) {
//				System.out.println("SQL 에러");
//			}
    	// DB연결 성공
    	System.out.println("DB연결 성공");
    	return conn; // DB에 연결하여 커넥션을 받아옴
    }
	    
		
		
		
    public static void close(Connection conn, PreparedStatement pstmt) {
    	close(conn, pstmt, null); // 매개변수 3개있는 close로 토스
    }
    
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
    	if(rs != null) {
    		try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if(pstmt != null) {
    		try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if(conn != null) {
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
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
	
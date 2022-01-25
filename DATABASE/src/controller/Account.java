package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Account {
	private Connection conn;
	
	public Account(Connection conn) {
		this.conn = conn;
	}
	// login(이메일, 패스워드) DB에서 같은 이메일, 패스워드를 확인해 false true중 하나를 리턴
	public boolean login(String email, String password) throws SQLException {
						// 이메일과 패스워드가 DB(users테이블에서 찾음)와 같은 갯수를 count에 올림(둘 다 일치하는게 하나면 count=1이 됨)
		String sql = "SELECT COUNT(*) AS count FROM users WHERE email=? and password=?";
		// ?는 로그인시도를 할때마다 바뀌는 값이라 prepareStatement를 사용해 ?에 값을 넣어줌
		
		// preparedstatement는 ?에 값을 넣기위한
		PreparedStatement pstmt = conn.prepareStatement(sql); // prepareStatement sql문으로 준비
		
		// 자료형에 따라 지정 setInt, setString...
		pstmt.setString(1, email);	// 위의 sql문의 첫번째 ?에 email입력 
		pstmt.setString(2, password); // 두번째 ?에 password입력
		
		// 결과를 ResultSet객체 rs에 저장 
		ResultSet rs = pstmt.executeQuery(); // SQL문 실행
		
		int count = 0;
		
		if(rs.next()) {	// 결과가 같으면
			count = rs.getInt("count"); // count객체에 sql문의 count열의 값을 리턴(int형)
		}
		rs.close(); // ResultSet객체 종료
		
		if(count == 0) return false;	// 없으면 false 있으면 true 리턴
		else return true;
		
	}
	
	public boolean exists(String email) throws SQLException {
		// DB에 이미 등록된 email이 있을 경우 true 없으면 false
		String sql = "SELECT COUNT(*) AS count FROM users WHERE email=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);	// sql문의 ?에 값을 넣기 위해 필요한 객체
		pstmt.setString(1, email); // 첫번째 ?에 email값을 넣음
		ResultSet rs = pstmt.executeQuery();	// 준비된 sql을 실행해 return값을 Result객체의 rs변수에 받는다 
		
		int count = 0;
		
		if(rs.next()) {	// 결과가 있으면
			count = rs.getInt("count"); // DB에 같은 이메일이 있는 숫자만큼 count객체에 sql문의 count열의 값을 리턴(int형)
		}
		rs.close(); // ResultSet객체 종료
		
		if(count == 0) return false;	// 없으면 false 
		else return true;				// 하나라도 있으면 true 리턴
	}
	
	public void create(String email, String password) throws SQLException {
		// DB에 새 user email과 password를 insert하는 메서드
		String sql = "INSERT INTO users(email, password) VALUES(?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// insert문은 결과를 받을 필요가 없기때문에 ResultSet객체가 필요없음
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		pstmt.executeUpdate(); 	// 결과 rs가 없을 필요경우 (입력, 수정, 삭제) executeUpdate() 사용
		
		pstmt.close();
		
	}
}

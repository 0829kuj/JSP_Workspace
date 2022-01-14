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
}

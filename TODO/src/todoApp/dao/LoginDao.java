package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todoApp.model.LoginBean;
import todoApp.utils.JDBCUtils;

// DB에 연결하여 로그인 체크하는 메서드를 만들 클래스. 직접 작업을 수행하는 메서드를 만듦
public class LoginDao {
	// return을 boolean으로 받으며, DB에 계정이 있으면 ture 없으면 false
	public boolean validate(LoginBean loginBean) {
		boolean status = false; // 체크해서 없으면 false
		
		Connection conn = JDBCUtils.getConnection();	// DB연결함
		String sql = "SELECT * FROM users WHERE userName = ? AND password = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginBean.getUsername());	// 첫번째 ?에 loginBean클래스의 getUsername()메서드로 리턴받은 값을 넣음
			pstmt.setString(2, loginBean.getPassword());	// sql문 준비완료
			
			ResultSet rs = pstmt.executeQuery();	// 쿼리문 실행한 값을 ResultSet의 객체 rs에 저장 
			status = rs.next(); // 결과 행이 있으면 true, 없으면 false(next()가 rs의 값을보고 둘중하나리턴해줌)
			
		} catch (SQLException e) {
			System.out.println("SQL 로그인 에러");
		}
		
		return status;
	}
}

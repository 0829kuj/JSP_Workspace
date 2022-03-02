package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todoApp.model.User;
import todoApp.utils.JDBCUtils;

// DAO는 DB에 연결해 데이터를 조작하는 클래스이다
public class UserDao {
	// 유저 입력 => DB에 유저데이터를 입력
	// ↓여기서 User는 todoApp.model에 내가 만들어둔 User임
	public int registerUser(User user) {	// 쿼리문 insert의 결과는 1행이므로, 1이 리턴됨. 아니면 0 이하가 리턴되나, 0이하는 비정상값으로 에러발생.
		String INSERT_USER_SQL = "insert into users(firstName,lastName,userName,password) "
				+ "values (?, ?, ?, ?)";
		int result = 0;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection(); // 우변은 JDBCUtils클래스에 내가 만들어둔 getConnection()메서드를 말함
			pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, user.getFirstName());	// 첫번째 ?에 user에서 FirstName속성값을 가져와 넣음
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsertName());
			pstmt.setString(4, user.getPassword());		// pstmt 준비완료 (모든 ?를 채운 상태)
			
			result = pstmt.executeUpdate();	// 결과가 없는 업데이트, 삭제, 입력 등은 쿼리 업데이트를 해준 줄의 갯수가 리턴됨(결과가 있는 select문은 약간 형태가 다름)
			
		} catch (SQLException e) {
			System.out.println("SQL 입력 에러 발생...");
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		
		return result;
	} // registerUser
	
	public User getUserByUserName(String userName) {
		User user = null;
		
		String sql = "SELECT * FROM users WHERE userName = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUsertName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return user;
	} // getUserByUserName
	
	
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>(); 

		
		String sql = "SELECT * FROM users ORDER BY userName ASC";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUsertName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				
				userList.add(user);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		
		return userList;
	} // getAllUsers
	
	
	public void update(User user) {
		String sql = "";
		sql += " UPDATE users ";
		sql += " SET firstName = ?, lastName = ? ";
		sql += " WHERE userName = ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(1, user.getLastName());
			pstmt.setString(3, user.getUsertName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		
	} //update
	
}

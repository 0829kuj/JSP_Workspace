package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Contact;

public class ContactDao {
	// DB의 contacts 테이블에 CRUD작업을 하는 클래스 (각 메서드 작성)
	// DB연결 객체들
	private DataSource dataSource;	// jdbc/demo 커넥션 풀 연결객체. 생성자를 통해 객체생성 시 필요한 dataSource를 여기서 넣어줌
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;	// 결과를 반환받는 객체
	
	public ContactDao(DataSource dataSource) {
		this.dataSource = dataSource;	// 객체생성시 커넥션 풀 dataSource를 입력
	}

	// 모든 연락처를 리스트로 리턴
	public List<Contact> findAll(){
		List<Contact> list = new ArrayList<Contact>();	// 비어있는 리스트 생성
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from contacts");
			rs = pstmt.executeQuery();	// 반환받은 결과를 rs에 저장
			
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));
				
				list.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("SQL에러 - findAll");
		} finally {			// finally : 에러여부와 관계없이 무조건 실행됨
			// DB연결 객체들을 닫는 과정이 필요함. (다수의 클라이언트 사용시 필수.)
			closeAll();
		}
		return list;
	}
	
	// 모든 DB연결 객체들을 닫아주느 메서드. DB연결시마다 반복해서 닫아주는 작업이 필요하므로 함수화시킴. 
	public void closeAll() {
		try {
			// 생성한 순서의 역순으로 닫아줌. rs > pstmt > conn (pool로 되돌아감)
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();	// 실제로는 Connection Pool로 되돌아감
		} catch (Exception e2) {
			System.out.println("DB연결 닫는 작업에서 에러발생");
		}
	}

	// 특정 id에 해당하는 데이터 한 행 출력
	public Contact find(int id) {
		Contact contact = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from contacts where id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			// contact에 찾은 데이터를 저장해 리턴해줘야하므로 아래의 contact에 리턴받은 데이터를 저장하는 과정이 필요
			while(rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL에러 - find");
		} finally {
			closeAll();
		}
		return contact;
	}
	// 입력된 contact객체를 DB에 저장
	public boolean save(Contact contact) {
		boolean fowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("insert into contacts(name,email, phone) values (?,?,?)");
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			fowAffected = pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("SQL에러 - save");
		} finally {
			closeAll();
		}
		return fowAffected;
	}
	
	// 입력된 contact객체를 DB에 업데이트
	public boolean update(Contact contact) {
		boolean rowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("update contacts set name=?, email=?, phone=? where id=?");
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			pstmt.setInt(4, contact.getId());
			rowAffected = pstmt.executeUpdate() > 0;
			
			
		} catch (SQLException e) {
			System.out.println("SQL에러 - update");
		} finally {
			closeAll();
		}
		System.out.println("업데이트 성공");
		
		return rowAffected;
	}
	
	// 입력한 id에 해당하는 데이터를 테이블에서 삭제
	public boolean delete(int id) {
		Boolean deleteId = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("delete from contacts where id=?");
			pstmt.setInt(1, id);
			deleteId = pstmt.executeUpdate() >= 1;	// 반환값이 1이면 삭제, 0이면 에러
			
		} catch (SQLException e) {
			System.out.println("SQL에러 - delete");
		} finally {
			closeAll();
		}
		System.out.println("삭제 성공");
		return deleteId;
	}
	
}

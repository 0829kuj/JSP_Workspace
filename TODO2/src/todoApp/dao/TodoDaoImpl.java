package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import todoApp.model.Todo;
import todoApp.model.User;
import todoApp.utils.JDBCUtils;

// DAO 클래스에서 DB의 todos테이블에 CRUD(생성, 읽기, 업데이트, 삭제)작업
public class TodoDaoImpl implements TodoDao {
	// 1.DB연결 2.각 기능에 맞게 작업(리턴타입이 지정되어있음)

	
	// 외부조인해서 가져오기. 특정 사용자에 대해 등록된 할일을 조인으로 한번에 들고옴.
	public User getUserAndTodos(String userName) {
		User user = null;
		List<Todo> todoList = new ArrayList<Todo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.id AS user_id, u.userName, u.firstName, u.lastName, u.password, ";
		sql += "        t.id AS todo_id, t.title, t.description, t.is_done, t.target_date ";
		sql += " FROM users u LEFT OUTER JOIN todos t ";
		sql += " ON u.userName = t.username ";
		sql += " WHERE u.userName = ? ";
		sql += " ORDER BY target_date DESC ";
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, userName);
			
			rs = pstmt.executeQuery();
			
			// todos 테이블의 정보 가져오기
			while (rs.next()) {
				//if the value is SQL NULL, the value returned is 0
				if (rs.getLong("todo_id") == 0) {	// 조인해서 받아온 값(숫자형이라 값이 없으면 0이 리턴됨)이 0일 경우 아래의 todo객체를 만들어 값을 받아오는 작업을 생략한다.
					continue;	// 자신을 감싸는 가장 가까운 반복문 블럭을 뛰어넘는다. 즉, while문의 내용은 건너뛰고 다시 while문의 처음으로 돌아감
				}
				
				Todo todo = new Todo();
				todo.setId(rs.getLong("todo_id"));
				todo.setTitle(rs.getString("title"));
				todo.setDescription(rs.getString("description"));
				todo.setStatus(rs.getBoolean("is_done"));
				todo.setTargetDate((rs.getDate("target_date") != null) ? rs.getDate("target_date").toLocalDate() : null);// null이 아니면 좌항을, null이면 우항을  실행
				
				todoList.add(todo); // 리스트에 추가
			} // while
			
			// users 테이블의 정보 가져오기
			if (rs.last()) {	// 행이 있으면 마지막 데이터행으로 rs객체의 커서 옮기기 = 마지막 데이터 행으로 커서 위치를 이동시키기
				user = new User();
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setPassword(rs.getString("password"));
				
				user.setTodoList(todoList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return user;
	} // getUserAndTodos
	
	

	@Override
	public List<User> getAllUsersAndTodoCount() {
		List<User> userList = new ArrayList<User>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.id AS user_id, u.userName, u.firstName, u.lastName, ";
		sql += "        COUNT(t.username) AS todo_count ";
		sql += " FROM users u LEFT OUTER JOIN todos t ";
		sql += " ON u.userName = t.username ";
		sql += " GROUP BY u.username ";
		sql += " HAVING COUNT(t.username) > 0 ";
		sql += " ORDER BY userName ASC, target_date DESC ";
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setTodoCount(rs.getInt("todo_count"));
				
				userList.add(user); // 리스트에 추가
			} // while
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return userList;
	} // getAllUsersAndTodoCount



	@Override
	public void insertTodo(Todo todo) {
		String INSERT_USER_SQL = "INSERT INTO todos(title, username, description, target_date, is_done) "
				+ "VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection(); // 우변은 JDBCUtils클래스에 내가 만들어둔 getConnection()메서드를 말함
			pstmt = conn.prepareStatement(INSERT_USER_SQL);
			// 아래 속성들은 Todo클래스에서 가져와야함
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate())); // JDBCUtils에서 만들어둔 SQLDate메서드로 java날짜를 SQL날짜로
																			// 변경해 4번째 ?에 넣어줌
			pstmt.setBoolean(5, todo.isStatus()); // pstmt 준비완료 (모든 ?를 채움)

			pstmt.executeUpdate(); // 입력은 리턴값이 없음

		} catch (SQLException e) {
			System.out.println("SQL todo입력 에러..");
		} finally {
			JDBCUtils.close(conn, pstmt);
		} // insert 작업이므로 리턴값 없음
	} //insertTodo
	


	@Override
	public Todo selectTodo(long todoId) {
		// 할일을 하나 검색했을 때. id로 검색함. 리턴값이 있음.
		Todo todo = null;
		String SELECT_TODO_BY_ID = "SELECT id, title, username, description, target_date, is_done FROM todos WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection(); // DB연결함
			pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			pstmt.setLong(1, todoId);

			rs = pstmt.executeQuery(); // 쿼리문 실행한 값을 ResultSet의 객체 rs에 저장
			if (rs.next()) { // 결과가 있으면 값을 저장. 없을때 저장하면 에러발생.
				long id = rs.getLong("id"); // 리턴값을 long타입의 id에 저장
				// 우변은 DB의 속성이름, 좌변은 리턴받은 우변의 데이터를 저장하는 변수의 이름
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate(); // 그냥 rs.getDate("target_date")하면
																				// java날짜와 SQL날짜형이 달라 에러나므로 로컬데이트(자바날짜)로
																				// 변환
				Boolean status = rs.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate, status);
			}

		} catch (SQLException e) {
			System.out.println("SQL todo검색 에러..");
			return null;
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		return todo;
	} //selectTodo
	

	@Override
	public List<Todo> selectTodoByUsername(String username) {
		// username에 해당하는 레코드 모두 조회

		List<Todo> todos = new ArrayList<>(); // 빈 ArrayList를 생성

		String SELECT_ALL_TODOS = "SELECT * FROM todos WHERE username = ? "; // todos테이블 전체검색

		// try-catch문은 PreparedStatement객체를 만들때 생기는 예외를 처리하는 작업이므로 Connection의 위치는 상관없음.
		// 원래는 Connection객체를 선언할때도 예외처리가 필요하지만 JDBCUtils클래스에서 getConnection메서드의 예외처리를 이미
		// 해주었으므로 여기선 다시 안해줘도 됨
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection(); // DB연결함
			pstmt = conn.prepareStatement(SELECT_ALL_TODOS);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery(); // 쿼리실행 후 결과저장
			// 결과가 여러행일 경우 while()을 사용하여 처리함. 한 행일땐 if문 사용.
			while (rs.next()) { // 결과가 있을 경우 값을 저장. 없는데 저장하면 에러발생
				// setter메서드를 통해 바로 값을 넣어주는 코드
				Todo todo = new Todo();
				todo.setId(rs.getLong("id"));
				todo.setTitle(rs.getString("title"));
				todo.setUsername(rs.getString("username"));
				todo.setDescription(rs.getString("description"));
				todo.setTargetDate(rs.getDate("target_date").toLocalDate());
				todo.setStatus(rs.getBoolean("is_done"));

				// 리스트에 담기 (todo객체로 입력)
				todos.add(todo);
			} // while문은 rs.next(한 행이 끝나면 다음행으로 넘어감)에 값이 있을 경우 반복해서 실행됨
		} catch (SQLException e) {
			System.out.println("SQL todo ALL검색 에러..");
			return null;
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		System.out.println("todo 리스트 검색완료!");
		return todos;
	} // selectTodoByUsername
	

	@Override
	public List<Todo> selectAllTodos() {
		// 모든 할일을 검색했을 때
		List<Todo> todos = new ArrayList<>(); // 빈 ArrayList를 생성

		String SELECT_ALL_TODOS = "SELECT * FROM todos"; // todos테이블 전체검색

		// try-catch문은 PreparedStatement객체를 만들때 생기는 예외를 처리하는 작업이므로 Connection의 위치는 상관없음.
		// 원래는 Connection객체를 선언할때도 예외처리가 필요하지만 JDBCUtils클래스에서 getConnection메서드의 예외처리를 이미
		// 해주었으므로 여기선 다시 안해줘도 됨
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection(); // DB연결함

			pstmt = conn.prepareStatement(SELECT_ALL_TODOS); // 쿼리문에 ?가 없으므로 바로실행
			rs = pstmt.executeQuery(); // 쿼리실행 후 결과저장
			// 결과가 여러행일 경우 while()을 사용하여 처리함. 한 행일땐 if문 사용.
			while (rs.next()) { // 결과가 있을 경우 값을 저장. 없는데 저장하면 에러발생
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate(); // 그냥 rs.getDate("target_date")하면
																				// java날짜와 SQL날짜형이 달라 에러나므로 로컬데이트(자바날짜)로
																				// 변환
				Boolean status = rs.getBoolean("is_done");
				// 리스트에 담기 (todo객체로 입력)
				todos.add(new Todo(id, title, username, description, targetDate, status));
			} // while문은 rs.next(한 행이 끝나면 다음행으로 넘어감)에 값이 있을 경우 반복해서 실행됨
		} catch (SQLException e) {
			System.out.println("SQL todo ALL검색 에러..");
			return null;
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		System.out.println("todo 리스트 검색완료!");
		return todos;
	} //selectAllTodos
	

	@Override
	public boolean deleteTodo(long todoId) {
		// id로 검색해 삭제
		String DELETE_TODO = "DELETE FROM todos WHERE id = ? ";
		boolean rowdeleted = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection(); // 우변은 JDBCUtils클래스에 내가 만들어둔 getConnection()메서드를 말함
			pstmt = conn.prepareStatement(DELETE_TODO);
			pstmt.setLong(1, todoId);
			rowdeleted = pstmt.executeUpdate() > 0; // 한 행이상 삭제되면 true

		} catch (SQLException e) {
			System.out.println("SQL todo삭제 에러..");
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("삭제 완료");
		return rowdeleted;
	} //deleteTodo

	@Override
	public boolean deleteTodo(String username) {
		String DELETE_TODO = "DELETE FROM todos WHERE username = ? ";
		boolean rowdeleted = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection(); // 우변은 JDBCUtils클래스에 내가 만들어둔 getConnection()메서드를 말함
			pstmt = conn.prepareStatement(DELETE_TODO);
			pstmt.setString(1, username);
			rowdeleted = pstmt.executeUpdate() > 0; // 한 행이상 삭제되면 true

		} catch (SQLException e) {
			System.out.println("SQL todo삭제 에러..");
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("할일 삭제 완료");
		return rowdeleted;
	} // deleteTodo
	
	
	@Override
	public boolean updateTodo(Todo todo) {
		// 같은 id의 모든 데이터 업데이트
		String UPDATE_TODO = 
				"UPDATE todos set title=?, username=?, description=?, target_date=?, is_done=? WHERE id=?";
		Boolean rowupdate = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(UPDATE_TODO);
			// 아래 속성들은 Todo클래스에서 가져와야함
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate())); // JDBCUtils에서 만들어둔 SQLDate메서드로 java날짜를 SQL날짜로
																			// 변경해 4번째 ?에 넣어줌
			pstmt.setBoolean(5, todo.isStatus());
			pstmt.setLong(6, todo.getId());

			rowupdate = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("SQL todo업데이트 에러..");
			return false;
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("업데이트 완료");
		return rowupdate;
	} //updateTodo

	
//	public static void main(String[] args) {
//		// 테스트용 메인메서드
//		UserDao userDao = new UserDao();
//		User user = userDao.getUserAndTodos("son");
//		System.out.println(user);
//		
//		List<Todo> todoList = user.getTodoList();			
//	}

}

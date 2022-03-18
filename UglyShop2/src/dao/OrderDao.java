package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.Farmer;
import beans.Order;
import beans.TotalCart;
import beans.User;

public class OrderDao {
	private DataSource dataSource; // jdbc/shop 커넥션 풀 연결 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public OrderDao(DataSource dataSource) {
		this.dataSource = dataSource; // 객체 생성시 커넥션 풀 daraSource를 입력
	}

	public List<Order> findAll() {
		List<Order> ordersList = new ArrayList<Order>(); // 빈 리스트 생성

		try {
			conn = dataSource.getConnection(); // DB연결
			pstmt = conn.prepareStatement("SELECT * FROM `order`"); // sql문
			rs = pstmt.executeQuery(); // 쿼리문 실행

			while (rs.next()) { // 반복문으로 orders 리스트 저장
				Order order = new Order();

				order.setOrderID(rs.getInt("orderID"));
				order.setOrderNum(rs.getInt("orderNum"));
				order.setUserID(rs.getString("userID"));
				order.setUserAdd(rs.getString("userAdd"));
				order.setUserTel(rs.getString("userTel"));
				order.setProdID(rs.getInt("prodID"));
				order.setProdName(rs.getString("prodName"));
				order.setProdPrice(rs.getInt("prodPrice"));
				order.setOrderQuantity(rs.getInt("orderQuantity"));
				order.setFarmID(rs.getString("farmID"));
				order.setIs_status(rs.getString("is_status"));

				ordersList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("주문 내역 전체 출력 SQL에러");
		} finally { // 에러에 상관없이 무조건 실행
			closeAll(); // 여러 사람이 사용할 때를 대비하여 DB연결 객체들을 닫는 과정
		}

		System.out.println("전체 주문 내역 검색 완료");
		return ordersList;
	}

	// userID로 검색하기. 리퀘에 없는 나머지 정보들을.
	public Order findByUserId(String userID) {
		Order order = null;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select userName, userAdd, userTel from orders where userID=?");
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setUserName(rs.getString("userName"));
				order.setUserAdd(rs.getString("userAdd"));
				order.setUserTel(rs.getString("userTel"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("userId select SQL 에러");
		} finally { // 에러에 상관없이 무조건 실행
			// DB연결 객체들을 닫는 과정이 필요하다.
			closeAll();
		}

		return order;

	}

	// farmID로 검색하기. 리퀘에 없는 나머지 정보들을.
	public Farmer findByFarmId(String farmId) {
		Farmer farmer = null;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from farmer where farmId=?");
			pstmt.setString(1, farmId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				farmer = new Farmer();
				farmer.setFarmID(rs.getString("farmID"));
				farmer.setFarmPassword(rs.getString("farmPassword"));
				farmer.setFarmName(rs.getString("farmName"));
				farmer.setFarmAdd(rs.getString("farmAdd"));
				farmer.setFarmTel(rs.getString("farmTel"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("farmerId select SQL 에러");
		} finally { // 에러에 상관없이 무조건 실행
			// DB연결 객체들을 닫는 과정이 필요하다.
			closeAll();
		}

		return farmer;

	}

	public List<Order> findByMyOrders(String userID) {
		// userId를 이용해 해당 id가 주문한 모든 주문을 cartID별로 정렬해서 보여줌

		List<Order> ordersList = new ArrayList<Order>(); // 빈 리스트 생성

		try {
			conn = dataSource.getConnection(); // DB연결
			pstmt = conn.prepareStatement("select * from orders where userID = ? ORDER BY cartID ASC"); // sql문
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); // 쿼리문 실행

			while (rs.next()) { // 반복문으로 orders 리스트 저장
				Order order = new Order();

				order.setOrderID(rs.getInt("orderID"));
				order.setOrderNum(rs.getInt("orderNum"));
				order.setUserID(rs.getString("userID"));
				order.setUserAdd(rs.getString("userAdd"));
				order.setUserTel(rs.getString("userTel"));
				order.setProdID(rs.getInt("prodID"));
				order.setProdName(rs.getString("prodName"));
				order.setProdPrice(rs.getInt("prodPrice"));
				order.setOrderQuantity(rs.getInt("orderQuantity"));
				order.setFarmID(rs.getString("farmID"));
				order.setIs_status(rs.getString("is_status"));

				ordersList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("내 주문 내역 전체 출력 SQL에러");
		} finally { // 에러에 상관없이 무조건 실행
			closeAll(); // 여러 사람이 사용할 때를 대비하여 DB연결 객체들을 닫는 과정
		}

		System.out.println("내 주문 내역 전체 검색 완료");
		return ordersList;
	}

	public List<Order> orderDetail(int cart) {
		// 하나의 카드번호에 대한 주문내역
		List<Order> ordersList = new ArrayList<Order>(); // 빈 리스트 생성

		try {
			conn = dataSource.getConnection(); // DB연결
			pstmt = conn.prepareStatement("select * from orders where cartID = ? ORDER BY orderID ASC"); // sql문
			pstmt.setInt(1, cart);
			rs = pstmt.executeQuery(); // 쿼리문 실행

			while (rs.next()) { // 반복문으로 orders 리스트 저장
				Order order = new Order();

				order.setOrderID(rs.getInt("orderID"));
				order.setOrderNum(rs.getInt("orderNum"));
				order.setUserID(rs.getString("userID"));
				order.setUserAdd(rs.getString("userAdd"));
				order.setUserTel(rs.getString("userTel"));
				order.setProdID(rs.getInt("prodID"));
				order.setProdName(rs.getString("prodName"));
				order.setProdPrice(rs.getInt("prodPrice"));
				order.setOrderQuantity(rs.getInt("orderQuantity"));
				order.setFarmID(rs.getString("farmID"));
				order.setIs_status(rs.getString("is_status"));

				ordersList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("하나의 주문 출력 SQL에러");
		} finally { // 에러에 상관없이 무조건 실행
			closeAll(); // 여러 사람이 사용할 때를 대비하여 DB연결 객체들을 닫는 과정
		}

		System.out.println("하나의 주문 검색 완료");
		return ordersList;
		
	}
	
	public boolean save(Order order) {
		boolean rowAffected = false;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(
					"insert into orders (userID, prodID, prodName, prodPrice, orderQuantity, orderNum, farmID, is_status) values (?,?,?,?,?,?,?,?)");

			pstmt.setString(1, order.getUserID());
			pstmt.setInt(2, order.getProdID());
			pstmt.setString(3, order.getProdName());
			pstmt.setInt(4, order.getProdPrice());
			pstmt.setInt(5, order.getOrderQuantity());
			pstmt.setInt(6, order.getOrderNum());
			pstmt.setString(7, order.getFarmID());
			pstmt.setString(8, "배송요청");

			rowAffected = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("order sql 오류");
		} finally {
			closeAll();
		}

		System.out.println("order테이블 등록 완료");
		return rowAffected;
	}

	public boolean userSave(Order order) {
		boolean update = false;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("set sql_safe_updates=0");
			pstmt.executeQuery();
			pstmt.close();

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(
					"update orders o inner join user u on u.userID = o.userID set o.userName = u.userName, o.userAdd = u.userAdd, o.userTel = u.userTel where o.userID = ?");
			pstmt.setString(1, order.getUserID());

			update = pstmt.executeUpdate() > 0; // update가 0보다 크면 true

		} catch (SQLException e) {
			System.out.println("SQL 에러" + e.getMessage());
		} finally {
			closeAll();
		}

		return update;
	}

	
	public int findOrderNum() {
		int num = 1;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select max(orderNum) from orders");	// 최대값을 검색해옴
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("orderNum select SQL 에러");
		} finally {
			closeAll();
		}
		return num;
	} // findOrderNum
	
	
	
	
	public TotalCart totalCartCheck() {
		// 저장된 숫자를 체크. 없을경우 0이 나옴.
//		int totalCart = 0;
		TotalCart totalCart = null;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from totalCart");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCart.setTotalCart(rs.getInt("totalCart"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 에러");
		} finally { // 에러에 상관없이 무조건 실행
			// DB연결 객체들을 닫는 과정이 필요하다.
			closeAll();
		}
		return totalCart;
	}

	public Boolean totalCartInsert(TotalCart totalCart) {
		// 저장된 숫자가 없을경우 1을 저장해줌
		boolean rowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("insert into totalcart (cartNum) values (1)");
			rs = pstmt.executeQuery();
			rowAffected = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("totalCart insert sql 오류");
		} finally {
			closeAll();
		}

		System.out.println("totalCart 등록 완료");
		return rowAffected;
	}

	public boolean totalCartUpdate(TotalCart totalCart) {
		// 업데이트
		boolean update = false;
//		totalCart += 1;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("update totalCart set cartNum = ? where cartNum = ?");
			pstmt.setInt(1, totalCart.getTotalCart() + 1);
			pstmt.setInt(2, totalCart.getTotalCart());

			update = pstmt.executeUpdate() > 0; // update가 0보다 크면 true

		} catch (SQLException e) {
			System.out.println("SQL 에러" + e.getMessage());
		} finally {
			closeAll();
		}
		return update;
	}

	private void closeAll() {
		try {
			// 나중에 연 순서부터 닫음 rs => pstmt => conn(풀로 되돌아감)
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close(); // 실제로는 Connection Pool로 되돌아감
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 닫기 에러");
		}
	}
}

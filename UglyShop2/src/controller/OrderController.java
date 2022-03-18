package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Farmer;
import beans.Order;
import beans.User;
import dao.OrderDao;
import dao.ProductDao;
import beans.Cart;
import beans.Product;
import beans.TotalCart;
import utills.Json;

@WebServlet("/order")
public class OrderController extends HttpServlet {

	private TotalCart cartNum = null;

	private static final long serialVersionUID = 1L;

	private OrderDao orderDao;

	@Resource(name = "jdbc/shop")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		orderDao = new OrderDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println(orderDao.findByMyOrders("user01"));
//		System.out.println(orderDao.totalCartCheck());
//		List<Order> order = new ArrayList<>();
//		order = orderDao.orderDetail(3);
		List<Order> list = orderDao.orderDetail(3);
		list.forEach(order -> System.out.println(order.toString()));
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("cmd");

		switch (action) {
		case "save": // order테이블에 주문 저장하기
//			save(req, resp);
			break;
		case "list": // 주문 내역 전체 출력
			ordersList(req, resp);
			break;
		case "myorders":
			myorderList(req, resp);
		case "oneOrder":
			oneOrder(req, resp);
		default: // 요청 주소가 기본 또는 잘못 되었을 경우 ordersList로 이동
			ordersList(req, resp);
			break;
		}

	}

	private void oneOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 하나의 주문에 대한 정보만 가져오기. cartID로 검색
//		http://localhost:8090/UglyShop2/order?cmd=oneOrder&cartID=3
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		int id = Integer.parseInt(req.getParameter("cartID"));
		List<Order> order = orderDao.orderDetail(id);

		req.setAttribute("order", order);
		RequestDispatcher rd = req.getRequestDispatcher("order/OrderDetail.jsp");
		rd.include(req, resp);
		
	}

	private void myorderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한 명의 주문만 전체 조회하기
		List<Order> orders = orderDao.findByMyOrders("user01");

		req.setAttribute("orders", orders);
//		RequestDispatcher rd = req.getRequestDispatcher("orders/MyOrders.jsp");
//		rd.forward(req, resp);
	}

	private void ordersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Order> orders = orderDao.findAll(); // DB의 모든 주문내역 가져오기

		req.setAttribute("orders", orders);

		RequestDispatcher rd = req.getRequestDispatcher("orders/ordersList.jsp"); // !!!jsp이름 수정하기!!!
		rd.forward(req, resp); // 리퀘스트를 유지하면서 ordersList.jsp페이지로 이동

	}

//	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
//		doPost(req, resp);
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

//		cartNum += 1;
//		TotalCart callNum = null;
//		callNum = orderDao.totalCartCheck();	// DB의 totalCart테이블에서 받아온 값을 여기 넣어줌. 0이면 들어간 값이 없는상태.
//		
//		if (callNum == null) {
//			orderDao.totalCartInsert(callNum);
//		} else {
//			orderDao.totalCartUpdate(callNum);
//		}
//		callNum = orderDao.totalCartCheck();	// 다시한번 DB의 totalCart의 값을 가져옴(여기선 반드시 1이상이게됨)
//		cartNum = callNum;

		HashMap<Integer, Cart> cartList = null;
		cartList = new HashMap<>();

		HttpSession session = req.getSession();
		cartList = (HashMap<Integer, Cart>) session.getAttribute("cartList");

		Order order = null;
		
		int num = orderDao.findOrderNum();	// DB에서 orderNum을 찾아줌
		num += 1;

		for (Cart cart : cartList.values()) {
			order = new Order();
			order.setUserID(cart.getUserID());
			order.setProdID(cart.getProdID());
			order.setProdName(cart.getProdName());
			order.setProdPrice(cart.getProdPrice());
			order.setOrderQuantity(cart.getOrderQuantity());
			order.setFarmID(cart.getFarmID());
			order.setOrderNum(num);

			boolean isSaved = orderDao.save(order);
			boolean userSaved = orderDao.userSave(order);

			order = orderDao.findByUserId(cart.getUserID());

			session.removeAttribute("cartList");
		} // for

		order.setOrderNum(num);
		
		req.setAttribute("order", order);
		RequestDispatcher rd = req.getRequestDispatcher("order/orderCheck.jsp");
		rd.include(req, resp);
	}

}

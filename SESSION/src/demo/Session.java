package demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Session")
public class Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// cart클래스 객체를 그냥 생성하면 접속할때마다 계속 새로 cart객체를 만들어 덮어쓰게되므로 null인 경우에만 새로 생성하도록함
		Cart cart = (Cart)session.getAttribute("cart");
		
		if(cart == null) {
			// cart클래스 객체 생성
			cart = new Cart();	
		}
		
		cart.setTotalItems(7);
		
		// 세션(서버)에 cart를 저장
		session.setAttribute("cart", cart);
		
		// showCart.jsp에 리퀘스트, 리스폰스를 포워드로 저장된 세션과 함께 이동
		getServletContext().getRequestDispatcher("/showCart.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

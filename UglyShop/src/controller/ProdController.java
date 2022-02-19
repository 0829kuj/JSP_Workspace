package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.Product;
import dao.ProductDao;
import dao.ReplyDao;
import dao.ReviewDao;

@WebServlet("/ProdController")
public class ProdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ProductDao prodDao;
	
	@Resource(name = "jdbc/shop")
	private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		prodDao = new ProductDao(datasource);
	}
    public ProdController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Product> prod = prodDao.findAll();
		prod.forEach(product -> System.out.println(product.toString()));	// 전체출력 테스트용
		
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("cmd") != null ? request.getParameter("cmd") : "list";
		
		try {
			switch (action) {

			case "del":			// 삭제
				delete(request, response);
				break;
			case "list":		// 전체 상품을 화면에 테이블로 표시
				list(request, response);
				break;
			default:	
				list(request, response);
				break;
			}
		}finally {
		}
	}


	private void list(HttpServletRequest request, HttpServletResponse response) {
		// 상품 전체출력
		
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// 상품 삭제
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

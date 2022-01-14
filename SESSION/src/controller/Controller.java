package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
													// getContextPath: Context의 주소를 표시함
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletContext context = getServletContext(); // 현재 이 어플리케이션의 context객체 리턴
		
		// context에 저장한 hits를 가져옴 
		Integer hits = (Integer)context.getAttribute("hits");
		 			// (Integer): getAttribute는 object를 리턴하기때문에 int형으로 변환해줌
		
		// 가져온 hits에 값이 없을 경우(=최초호출시 값이 없음) 
		if(hits == null) hits = 0;	// hits를 0으로 초기화
		else hits++;				// hits에 저장된 값이 있을 경우 +1 해줌

		context.setAttribute("hits", hits); // hits라는 속성값을 context에 저장
		
		// context에 저장된 hits를 출력
		PrintWriter out = response.getWriter();
		out.println("Hits: "+ hits);
		
		// ServletContext: 톰캣 컨테이너 실행 시 각 웹 어플리케이션(컨텍스트)마다 하나의 객체를 생성함. 톰캣컨테이너가 종료되면 같이 소멸됨.
		// 세션은 브라우저별로 따로 관리되지만 컨텍스트는 브라우저가 달라도 공유됨. 리퀘스트는 그 요청에서만 사용됨
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

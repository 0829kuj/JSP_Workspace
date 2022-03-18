package todoApp.rest.todo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/displayImage", loadOnStartup = 1)
public class DisplayImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// http://localhost:8090/TODO2/testImage.html
    	// http://localhost:8090/TODO/displayImage?filename=mario.png
    	// http://localhost:8090/TODO2/displayImage?filename=파일이름.확장자
    	
    	// (경로가 포함된) 이미지 파일명을 파라미터로 가져오기
    	String filename = request.getParameter("filename"); // "mario.png"
    	System.out.println("filename : " + filename);
    	
    	File file = new File("C:/Users/KIMeongeong/Pictures", filename);	// "경로", 파일명	"C:/java502/IMGS/unkown.png"
    	System.out.println("file : " + file.getAbsolutePath());
		
		if (!file.exists()) { // file.exists() == false
			System.out.println("요청한 이미지 파일이 해당 경로에 존재하지 않습니다.");
			return;
		}
		
		// "image/jpeg"  "image/png"  "image/gif"
		String contentType = Files.probeContentType(file.toPath());
		System.out.println("contentType : " + contentType);
		
		// ContentType 설정 후에 출력스트림을 가져와야 함에 주의!
		response.setContentType(contentType);
		
		// 입력스트림 객체 준비
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		
		// 출력스트림 객체 준비
		BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
		
		int data;
		while ((data = is.read()) != -1) {
			os.write(data);
		} // while
		os.flush(); // 출력버퍼 비우기
		
		// 입출력 스트림 객체 닫기
		is.close();
		os.close();
	} // doGet
}

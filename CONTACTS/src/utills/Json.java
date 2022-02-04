package utills;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Contact;

public class Json {
	
	private ContactJson contactJson;	// 보낼 객체
	private Gson gson;					// Gson라이브러리 객체
	private PrintWriter out;			// 출력 객체
	private HttpServletResponse response;	// 응답 객체
	
	public Json(HttpServletResponse response) {	// 생성자(매개변수로 response객체필요) => 응답으로 제이슨 출력
		contactJson = new ContactJson();	// 객체 생성
		gson = new Gson();					// 객체 생성
		
		this.response = response;
		this.response.setContentType("application/json; charset=utf-8"); // 응답 타입을 제이슨으로 설정
		
		try {
			out = response.getWriter();	// 응답 out객체 생성
		}
		catch (IOException e) {	// 파일입출력 관련 예외처리
			e.printStackTrace();
		}
	}
	// 1. 연락처를 응답으로 보낼 때 (수정 요청 시 => 해당 수정연락처의 내용을 응답으로 보내줌)
	public void sendContact(Contact contact) {
		contactJson.setStatus(true);		// 상태 양호
		contactJson.setContact(contact);	// 연락처 객체를 입력
		
		sendResponse(gson.toJson(contactJson));	// 입력된 연락처와 상태를 모두 제이슨으로 변환하여 출력
	}
	// 2. 메세지만 응답으로 보낼 때 (입력, 업데이트, 삭제 시 메세지로 성공여부만 보냄)
	public void sendMessage(boolean status, String message) {
		contactJson.setStatus(status);		// 상태 입력
		contactJson.setMessage(message);	// 메세지 입력
		
		sendResponse(gson.toJson(contactJson));	// 입력된 상태와 메세지를 제이슨으로 변환하여 출력
	}
	// 공통으로 사용될 출력 메서드
	private void sendResponse(String jsonData) {	// 위의 1혹은 2 메서드의 결과로 받은 제이슨 데이터를 매개변수로 받아옴
		out.print(jsonData);
		out.flush();	// 만약 남아있는 데이터가 있으면 모두 출력 => 남아있는 내용이 없도록함
	}
}

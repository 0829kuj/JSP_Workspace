package beans;

public class User {
	// 초기값을 모두 공백으로	
	private String email = "";
	private String password = "";
	
	private String message ="";	// 유효성 검사에 불합격시 출력할 메세지
	
	public User() {
		// 기본생성자
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	
	public boolean validate() {
	// 입력된 필드변수의 값이 유효한지 유효성 검사
//		if(email == null) {
//			message = "Invalid email";
//			return false;
//		}
//		if(password == null) {
//			message = "Invalid password";
//			return false;
//		}
		if(!email.matches("\\w+@\\w+\\.\\w+")) {
			// 문자열@문자열.문자열 의 구조라는 의미.
			// 정규 표현식으로 java문자열은 \를 사용하려면 두번적어야 함. \w+: 숫자포함 모든문자 w: 1개이상의 문자
			message = "Invalid email address";
			return false;
		}
		if(password.length() < 8) {
			message = "패스워드는 8자 이상";
			return false;
		}
		else if (password.matches("\\w*\\s+\\w*")) {
			message = "패스워드에 스페이스가 포함되면 안됩니다.";
			return false;
		}
		
		// 위의 모든 if를 true로 통과했을 경우
		return true;
	}
	
	
}

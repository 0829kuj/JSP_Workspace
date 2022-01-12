package beans;
// 자바 bean으로 만들 클래스 (필드변수, 기본 생성자, get/set메서드 필요)
public class User {
	private String email;
	private String password;
	private String name;
	
	// 기본생성자는 생략, getset메서드는 자동생성
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

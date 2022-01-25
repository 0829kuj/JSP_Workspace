package todoApp.model;

public class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	public User() {	}	// 기본생성성자는 java bean용으로 필요
	
	// 이 User생성자는 UserController에서 User객체를 사용할 때 4개의 매개변수가 모두 필요하므로 미리 만들어둠
	public User(String firstName, String lastName, String usertName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = usertName;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsertName() {
		return userName;
	}
	public void setUsertName(String usertName) {
		this.userName = usertName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

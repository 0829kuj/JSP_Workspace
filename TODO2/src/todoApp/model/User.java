package todoApp.model;

import java.util.List;

public class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
//	private Todo todo;	// 테이블 조인에서 1:1 관계일 경우 (유저가 할일을 최대 하나만 등록할 수 있으므로 리스트를 쓸 필요가 없음)
	private List<Todo> todoList;	// 테이블 조인에서 1:N 관계
	
	private Integer todoCount;	// int로 지정하면 null값이 들어가지 않으므로 좀 더 에러율을 낮추기위해 기본자료형보다는 객체형을 선호하는편이다.(대신 기본자료형보다 메모리사용량이 많아 안드로이드에서는 선호되지 않음)
	
	public User() {	}	// 기본생성성자는 java bean용으로 필요
	
	// 이 User생성자는 UserController에서 User객체를 사용할 때 4개의 매개변수가 모두 필요하므로 미리 만들어둠
	public User(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}

	public Integer getTodoCount() {
		return todoCount;
	}
	public void setTodoCount(Integer todoCount) {
		this.todoCount = todoCount;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", todoList=" + todoList + ", todoCount=" + todoCount + "]";
	}
	
}

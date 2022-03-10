package com.example.domain;

import com.google.gson.annotations.SerializedName;

public class User {
	
	// 원래는 json의 속성이름과 똑같이 맞춰줘야하지만 부득이하게 다른이름을 써야 할 경우 어노테이션을 사용해 받은 값의 이름이 달라도 지정한 속성명의 값을 해당 변수에 저장할 수 있다.
	@SerializedName("firstName")
	private String firstName;
	
	private String lastName;
	private String userName;
	private String password;
	
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
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + "]";
	}
	
}

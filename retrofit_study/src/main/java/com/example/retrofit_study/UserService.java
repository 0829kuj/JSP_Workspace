package com.example.retrofit_study;
// Retrofit 객체의 create메서드에 의해 자동으로 구현객체를 만들 수 있음.
// 구현된 서비스 객체의 용도는 GET, POST, PUT, DELETE 요청을 담당하는 메서드를 가짐.

import com.example.domain.User;
import com.example.domain.UserListResult;
import com.example.domain.UserOneResult;
import com.example.domain.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {
	// 이 인터페이스에서는 나중에 요청을 보낼 객체를 뼈대만(주소 등은 여기서 미리 만들어둠) 만들어두고, 나중에 이 메서드가 호출되었을때 객체를 완성하는 역할을 한다.
	// 여기서 완성된 객체가 Exn 클래스에서 서버로 요청을 보내고, 응답을 받아와 각 응답에 따른 처리를 함.
	
	@GET("TODO2/api/user/")	// 여기서 요청주소의 뒷부분(uri주소)을 작성함. 그러면 url과 합쳐저 완성된 주소가 됨.
	Call<UserOneResult> getUser(
				@Query("category") String category, 	// user를 하나 가져옴. 인터페이스의 접근지정자는 무조건 public abstict이니 생략함.
				@Query("userName") String userName);	// @Query의 값이 속성이름, 그 다음에 받는 값이 해당 속성에 대한 값임.
	
//	@GET("TODO2/api/user/")
//	Call<UserOneResult> getUser(@QueryMap Map<String, String> options);		// Query String이 아닌 Map을 이용한 방법. 속성과 값이 여러개일때 유용.
	
	@GET("TODO2/api/user/")
	Call<UserListResult> getUsers(@Query("category") String category);
	
	@POST("TODO2/api/user/")
	Call<UserResult> createUser(@Body User user);	// json문자열을 url을 통해 바로 전송하는 방법. 쿼리스트링사용x
	
	@PUT("TODO2/api/user/")
	Call<UserResult> updateUser(@Body User user);
	
	@DELETE("TODO2/api/user/")
	Call<UserResult> deleteUser(@Query("userName") String userName);
	
} //interface UserService 

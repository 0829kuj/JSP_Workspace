package com.example.retrofit_study;

import com.example.domain.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Ex5 {

	public static void main(String[] args) {
		// delete user
		
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://localhost:8090/")
			.addConverterFactory(GsonConverterFactory.create())
			.build();
		
		UserService userService = retrofit.create(UserService.class);
		
		// 네트워크로 요청할 정보객체 call 을 준비
		Call<UserResult> call = userService.deleteUser("user5");
		
		// 네트워크 요청을 보냄. 서버로부터 응답이 오면 콜백객체의 onResponse 또는 onFailure 가 호출됨.
		call.enqueue(new Callback<UserResult>() {

			public void onResponse(Call<UserResult> call, Response<UserResult> response) {
				// isSuccessful 메소드로 200번대 정상 응답이 아닌 경우(응답코드가 300, 400, 500번대인 경우)
				if (response.isSuccessful() == false) {
					System.out.println("onResponse: 실패");
					return;
				}
				
				// isSuccessful 메소드로 200번대 정상 응답인 경우
				System.out.println("onResponse: 성공");
				
				UserResult userResult = response.body();
				System.out.println("응답결과: " + userResult.toString());
			}

			public void onFailure(Call<UserResult> call, Throwable t) {
				System.out.println("onFailure: " + t.getMessage());
			}
		});
		
		
		System.out.println("=== main 메소드 종료됨 ===");
	} // main
}






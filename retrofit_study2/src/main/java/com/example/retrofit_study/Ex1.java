package com.example.retrofit_study;

import com.example.domain.UserOneResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Ex1 {

	public static void main(String[] args) {
		// select one user
		
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://localhost:8090/")
			.addConverterFactory(GsonConverterFactory.create())
			.build();
		
		UserService userService = retrofit.create(UserService.class);
		
		// 네트워크로 요청할 정보객체 call 을 준비
		Call<UserOneResult> call = userService.getUser("one", "hong");
		
		// 네트워크 요청을 보냄. 서버로부터 응답이 오면 콜백객체의 onResponse 또는 onFailure 가 호출됨.
		call.enqueue(new Callback<UserOneResult>() {

			public void onResponse(Call<UserOneResult> call, Response<UserOneResult> response) {
				// isSuccessful 메소드로 200번대 정상 응답이 아닌 경우(응답코드가 300, 400, 500번대인 경우)
				if (response.isSuccessful() == false) {
					System.out.println("onResponse: 실패");
					return;
				}
				
				// isSuccessful 메소드로 200번대 정상 응답인 경우
				System.out.println("onResponse: 성공");
				
				UserOneResult userOneResult = response.body();
				System.out.println("응답결과: " + userOneResult.toString());
			}

			public void onFailure(Call<UserOneResult> call, Throwable t) {
				System.out.println("onFailure: " + t.getMessage());
			}
		});
		
	} // main
}






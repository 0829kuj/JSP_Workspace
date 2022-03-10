package com.example.retrofit_study;

import com.example.domain.User;
import com.example.domain.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ex4 {

	public static void main(String[] args) {
		// update user

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8090/") // 기준url(프로토콜이름부터 포트번호까지)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		UserService userService = retrofit.create(UserService.class);

		// update할 user 객체 준비
		User user = new User("공", "효진", "gong", null);
		
		
		// 네트워크로 요청할 정보객체 call을 준비
		Call<UserResult> call = userService.updateUser(user);

		// 네트워크 요청을 보냄. 응답이 오면 콜백객체의 onResponse 또는 onFailure가 호출됨.
		call.enqueue(new Callback<UserResult>() {
			// 응답이 왔을때 할 동작. 단, 응답이 왔다고 모두 정상응답은 아님.(에러코드역시 응답은 응답임에 유의)

			// isSuccessful 메서드로 200번대 응답(정상응답)이 아닌 경우 예- 응답코드 300, 400, 500번대
			public void onResponse(Call<UserResult> call, Response<UserResult> response) {
				// 응답이 왔을때 할 동작. 단, 응답이 왔다고 모두 정상응답은 아님.(에러코드역시 응답은 응답임에 유의)

				// isSuccessful 메서드로 200번대 응답(정상응답)이 아닌 경우 예- 응답코드 300, 400, 500번대
				if (!response.isSuccessful()) {
					System.out.println("onResponse: 실패");
					return;
				}

				// isSuccessful 메서드로 200번대 응답(정상응답)인 경우
				System.out.println("onResponse: 성공");

				UserResult userResult = response.body();
				System.out.println("응답결과: " + userResult.toString());

			} // onResponse

			public void onFailure(Call<UserResult> call, Throwable t) {
				// 요청실패 상황에서 할 동작
				System.out.println("onFailure: " + t.getMessage());
			} // onFailure
		});

	} // main

}

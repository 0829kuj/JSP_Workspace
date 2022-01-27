<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습4_Email중복체크</title>
  </head>
  <body>
    <h1>회원가입시 Email 중복체크</h1>
    <!-- ex1~3번 예제들과 달리 이번엔 form을 사용하지만 주소는 ajax에서 처리하므로 action속성으로 작성하지 않는다 -->
    <form name="myform">
    	<input type="email" name="email" placeholder="이메일 입력">
    	<input type="button" onclick="sendServer()" value="중복체크">
    	<div id="output"></div>	
    </form>
    <script>
      const input = document.querySelector('input[type="email"]'); // input태그 중 type이 text인 태그만 선택
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax request객체 생성

      function sendServer() {
        // 버튼을 누르면 실행되는 함수
        let email = input.value; // input창에 입력한 값을 저장
        let url = 'emailCheck.jsp?email=' + email; // 요청할 jsp페이지 주소
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = getInfo; 
        }
        // 함수를 따로 정의한 후 그 함수를 호출하는식으로 작성함
        function getInfo() {
            if (request.readyState == 4 && request.status == 200) {
              let info = request.responseText; // 요청한 결과 받기
              output.textContent = info;
            }
      };
    </script>
  </body>
</html>

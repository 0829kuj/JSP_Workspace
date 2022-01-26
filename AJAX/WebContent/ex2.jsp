<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습2_구구단</title>
  </head>
  <body>
    <h1>원하는 구구단의 숫자는?</h1>
    <input type="text" />
    <!-- 버튼을 클릭하면 sendServer함수가 실행됨 -->
    <button onclick="sendServer();">출력하기</button>
    <div id="output"></div>
    <script>
      const input = document.querySelector('input[type="text"]'); // input태그 중 type이 text인 태그만 선택
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax request객체 생성

      function sendServer() {
        // 버튼을 누르면 실행되는 함수
        let v = input.value; // input창에 입력한 값을 저장
        let url = 'gugu.jsp?val=' + v; // 요청할 jsp페이지 주소

        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = function () {
          if (request.readyState == 4 && request.status == 200) {
            let val = request.responseText; // 요청한 결과 받기
            console.log(val);
            output.innerHTML = val;
          }
        };
      }
    </script>
  </body>
</html>

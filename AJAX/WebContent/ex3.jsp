<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습3_ID검색</title>
  </head>
  <body>
    <h1>ID입력시 DB를 검색해 결과출력</h1>
    ID 입력: <input onkeyup="sendServer()" type="text" />
    <div id="output"></div>
    <script>
      const input = document.querySelector('input[type="text"]'); // input태그 중 type이 text인 태그만 선택
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax request객체 생성

      // input태그의 onkeyup속성을 안쓰고 이벤트리스너로 작성할 경우(다량작업시 이벤트리스너 권장)
      // input.addEventListener('keyup', sendServer());
      
      function sendServer() {
        // 버튼을 누르면 실행되는 함수
        let id = input.value; // input창에 입력한 값을 저장
        let url = 'getDB.jsp?id=' + id; // 요청할 jsp페이지 주소
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

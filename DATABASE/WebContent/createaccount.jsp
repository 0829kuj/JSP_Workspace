<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>가입하기 페이지</title>
    <style>
      * {
        margin: 0px;
        padding: 0px;
        text-decoration: none;
        font-family: sans-serif;
      }
      body {
        background-color: #34495e;
        width: 100%;
        height: 100%;
      }
      .loginForm {
        position: absolute;
        width: 300px;
        height: 470px;
        padding: 30px, 20px;
        background-color: #FFFFFF;
        text-align: center;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        border-radius: 15px;
      }
      .loginForm h2 {
        text-align: center;
        margin: 30px;
      }
      .emailForm {
        border-bottom: 2px solid #adadad;
        margin: 30px;
        padding: 10px 10px;
      }
      .passForm {
        border-bottom: 2px solid #adadad;
        margin: 30px;
        padding: 10px 10px;
      }
      .email {
        width: 100%;
        border: none;
        outline: none;
        color: #636e72;
        font-size: 16px;
        height: 25px;
        background: none;
      }
      .pw {
        width: 100%;
        border: none;
        outline: none;
        color: #636e72;
        font-size: 16px;
        height: 25px;
        background: none;
      }
      .btn {
        position: relative;
        left: 40%;
        transform: translateX(-50%);
        margin-bottom: 40px;
        width: 80%;
        height: 40px;
        background: linear-gradient(125deg, #81ecec, #6c5ce7, #81ecec);
        background-position: left;
        background-size: 200%;
        color: white;
        font-weight: bold;
        border: none;
        cursor: pointer;
        transition: 0.4s;
        display: inline;
      }
      .btn:hover {
        background-position: right;
      }
      .bottomText {
        text-align: center;
      }
    </style>
  </head>

  <body>
  <!-- 로그인을 할땐 보안상 보통 post방식으로 요청 -->
    <form action="<%= request.getContextPath() %>/Controller" method="post" class="loginForm">
    <!-- 아래에 hidden속성에 name과 value값이 있으므로 위에서 /Controller?action=createaccount해준것과 같음 -->
      <input type="hidden" name="action" value="createaccount" >
      <h2>가입하기</h2>
      <div class="emailForm">
      	<!-- 입력받은 값을 value의 request에 각 속성별로 담아 form태그의 action의 주소로 이동할 때 함께 가져감 -->
        <input type="text" class="email" name="email" placeholder="이메일" value="<%= request.getAttribute("email") %>">
        <!-- value=" request.getAttribute("email") ": 비밀번호를 틀렸어도 email을 다시 입력할 필요 없도록 작성했던 값을 불러옴 -->
      </div>
      <div class="passForm">
        <input type="password" class="pw" name="password" placeholder="비밀번호" value="">
      </div>
      <div class="passForm">
        <input type="password" class="pw" name="repeatpassword" placeholder="비밀번호 확인" value="">
      </div>
      <button type="submit" class="btn">가입하기</button>
      <div class="bottomText">
        <%= request.getAttribute("message") %>
      </div>
    </form>
  </body>

  </html>
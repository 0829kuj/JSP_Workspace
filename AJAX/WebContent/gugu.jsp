<%
// 입력받은 n의 구구단을 출력
  int n = Integer.parseInt(request.getParameter("val"));
	//int m = 0;
  for(int i = 1; i <= 9; i++){
    String s = String.format("%d X %d = %d <br>", n, i, n*i);
	out.print(s);
	
	//m = n * i;
	//out.print(n+" X "+i+" = "+m+"<br>");  // 주석친건 내가 풀어본것
  }
%>
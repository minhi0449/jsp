<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 요청하기 전에 쿠키확인을 먼저 한다 요청하는 서버에 쿠키가 있는지 확인 -->
	<!-- 쿠키값을 쓰든 안 쓰든 해당 서버에서 가져옴 add 쿠키 안함  request로 실려옴-->
	<meta charset="UTF-8">
	<title>cookieResult</title>
</head>
<body>
	<h3>쿠키확인</h3>
	<%
		// request는 기본적으로 쿠키를 가지고 요청
		Cookie[] cookies = request.getCookies();
	 	for(Cookie cookie : cookies){
	 %> 
	 	
	<p>
		쿠키명 : <%= cookie.getName() %><br>
		쿠키값 : <%= cookie.getValue() %><br>
	
	</p>
	<%
	 }
	%>
	
	<a href="../1.cookieTest.jsp">처음으로</a>
	<!-- 비밀번호 쿠키는 1분 지나면 없어짐 -->
</body>
</html>
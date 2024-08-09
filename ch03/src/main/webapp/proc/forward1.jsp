<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>forward1</title>
</head>
<body>
	<h3>포워드1 페이지</h3>
	
	<%
	
		// 리다이렉트와 큰 차이점 
		pageContext.forward("../1.request.jsp");
	%>
	
</body>
</html>
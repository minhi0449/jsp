<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>responseFile</title>
</head>
<body>
	<h3>파일 다운로드</h3>
	<%
		// 서버 -> 클라이언트 response
		// 클라이언트가 브라우저인데 기본적으로 html인데 파일이 html이 아닐경우
		// 파일을 다운로드 한다
		
		// 해당 페이지를 브라우저에 출력하지 않고 파일 다운로드
		response.setHeader("Content-Type", "application/octet-stream");
	%>
</body>
</html>
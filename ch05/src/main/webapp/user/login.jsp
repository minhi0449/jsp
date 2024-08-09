<%@page import="user1.UserVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 자동 로그인 검사
	Cookie[] cookies = request.getCookies();

	if(cookies != null){
		
		for(Cookie cookie : cookies){
			
			// auto라는 쿠키가 있는 것 만으로도 자동로그인이라는 뜻
			// auto 라는 값이 사용자 id 가 되는 거임
			// new Cookie("auto", uid);
			if(cookie.getName().equals("auto")){
				
				// 쿠키값으로 데이터베이스 조회
				// uid값이 사용자가 입력한 id
				String uid = cookie.getValue();
				
				// 로그인 사용자 객체 생성
				UserVO userVO = new UserVO();
				userVO.setUid("abc123");
				userVO.setPass("1234");
				userVO.setName("홍길동");
				userVO.setAge(23);
				
				
				// 세션 저장
				session.setAttribute("sessUser", userVO);
				
				// 로그인 성공 페이지로 이동
				response.sendRedirect("./loginSuccess.jsp");
				return; // 처리종료
				
			}
		}
	}
	
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>user::login</title>
</head>
<body>
	<h3>로그인</h3>
	
	<form action="./loginProc.jsp" method="post">
		<input type="text" name="uid" placeholder="아이디 입력" /><br>
		<input type="password" name="pass" placeholder="비밀번호 입력" /><br>
		<label><input type="checkbox" name="auto" value="1">자동로그인</label><br>
		<input type="submit" value="로그인" />
	</form>
</body>
</html>
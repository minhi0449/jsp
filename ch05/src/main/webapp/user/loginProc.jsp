<%@page import="user1.UserVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	String auto = request.getParameter("auto"); // 1 or null  / 체크 하면 1 안하면 null / 1 : 자동로그인
	
	// 데이터베이스 작업 생략 (1 ~ 6단계)
	// 사용자 과정에서 만들어짐
	
	// 회원 abc123/1234 라고 가정
	if(uid.equals("abc123") && pass.equals("1234")){
		// 자동로그인 처리
		if(auto != null){
			
			Cookie autoCookie = new Cookie("auto", uid); // 사용자 아이디로 해야함 uid
			autoCookie.setMaxAge(60 * 3); // 3분
			autoCookie.setPath("/ch05"); // 쿠키경로 : 해당 경로 하위에서 참조 가능 (ch05전체)
			response.addCookie(autoCookie); // response에  쿠키 실었음
		}
		
		
		// 회원이 맞을 경우 --> ★ 사용자 객체 생성 후 세션 저장 ★
		
		
		UserVO userVO = new UserVO();
		// 아래 정보는 DB 멤버 테이블에서 가져온 데이터들이다
		userVO.setUid("abc123");
		userVO.setPass("1234");
		userVO.setName("홍길동");
		userVO.setAge(23);
		
		session.setAttribute("sessUser", userVO); //로그인 처리의 핵심
		
		// login.jsp가 클라이언트  -> loginProc.jsp 
		// 성공 페이지 이동 (로그인 성공페이지 이동)
		response.sendRedirect("./loginSuccess.jsp");
		
	}else{
		// 회원이 아닐 경우
		// 파라미터를 들고 가는 게 좋음   // ? 파라미터 시작 그 뒤에는 쿼리 String
		response.sendRedirect("./login.jsp?success=100");
		// 그냥 코드 짓는 거 fail
		
		
		
	}

%>
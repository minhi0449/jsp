<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	// 세션 해제(현재 클라이언트 세션 정보 초기화)
	//session.removeAttribute("sessUser"); --> 아래 invalidate 한번에 처리
	session.invalidate();
	
	// 자동 로그인 쿠키삭제
	Cookie autoCookie = new Cookie("auto", null); // 사용자 아이디로 해야함 uid
	autoCookie.setMaxAge(0); // 3분
	autoCookie.setPath("/ch05"); // 쿠키경로 : 해당 경로 하위에서 참조 가능 (ch05전체)
	response.addCookie(autoCookie); // response에  쿠키 실었음
	
	// 로그인 페이지 이동
	response.sendRedirect("./login.jsp?logout=success");
	
	%>
<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 문자열 안에 문자열 처리 : 이스케이프 = 역슬래시
	//JSON 문자열 생성
	String jsonData = "{\"uid\": \"a101\", \"name\": \"홍길동\", \"age\": 23, \"addr\": \"부산\"}";
	
	// JSON 출력
	out.print(jsonData);
	
	
	
	
	

%>
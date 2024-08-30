<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 포맷 : 이쪽에 있는 시스템(디바이스) -> 저쪽에 있는 시스템(디바이스) : 순수하게 데이터만 보낼 때
	String xml = "<user>";
		   xml += "<uid>a101</uid>";
		   xml += "<name>홍길동</name>";
		   xml += "<age>23</age>";
		   xml += "<addr>부산</addr>";
		   xml += "</user>";
		   
	out.print(xml);

%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>1. ELTest</title>
	<!-- 
		날짜 : 2024.08.13 (화)
		이름 : 김민희
		내용 : 표현언어 실습하기
	 -->
</head>
<body>
	<h3>1. 표현언어</h3>
	
	<%
		int num1 = 1;
		int num2 = 2;
	
		String str1 = "hello";
		String str2 = "welcome";
		String str3 = "";
		String str4 = null;
		
		// 표현언어 출력을 위해 내장객체 스코프 설정
		pageContext.setAttribute("num1", num1);
		request.setAttribute("num2", num2);
		session.setAttribute("str1", str1);
		application.setAttribute("str2", str2);
		request.setAttribute("str3", str3);
		request.setAttribute("str4", str4);
		
		
	%>
	
	<h4>표현식</h4>
	<p>
		num1 : <%= num1 %><br>
		num2 : <%= num2 %><br>
		str1 : <%= str1 %><br>
		str2 : <%= str2 %><br>
		str3 : <%= str3 %><br>
		str4 : <%= str4 %><br>
	</p>
	
	<h4>표현언어</h4>
	<p>
		num1 : ${num1} <br>
		num2 : ${num2} <br>
		str1 : ${str1} <br>
		str2 : ${str2} <br>
		str3 : ${str3} <br>
		str4 : ${str4} <br>

	</p>
	
	<h4>표현언어 내장객체</h4>
	<p>
	<!-- 내장객체가 앞에 생략되어있음 page Scope 내장객체 -->
	<!-- 표현언어 내장객체 접미사는 Scope ! -->
	<!-- 보통은 생략을 합니다. -->
	
		num1 : ${pageScope.num1} <br>
		num2 : ${requestScope.num2} <br>
		str1 : ${sessionScope.str1} <br>
		str2 : ${applicationScope.str2} <br>
		str3 : ${str3} <br>
		str4 : ${str4} <br>

	</p>
	
	
	<h4>표현언어 연산자</h4>
	<p>
		num1 + num2 = ${num1 + num2}<br>
		num1 - num2 = ${num1 - num2}<br>
		num1 * num2 = ${num1 * num2}<br>
		num1 / num2 = ${num1 / num2}<br>
		num1 % num2 = ${num1 % num2}<br><br>
		
		num1 > num2 = ${num1 > num2}<br>
		num1 < num2 = ${num1 < num2}<br>
		num1 >= num2 = ${num1 >= num2}<br>
		num1 <= num2 = ${num1 <= num2}<br>
		num1 == num2 = ${num1 == num2}<br>
		num1 != num2 = ${num1 != num2}<br><br>
		
		num1 gt num2 = ${num1 gt num2}<br>  <!-- gt (greater than): num1 > num2 (num1이 num2보다 큰지 확인) -->
		num1 lt num2 = ${num1 lt num2}<br>  <!-- lt (less than): num1 < num2 (num1이 num2보다 작은지 확인) -->
		num1 ge num2 = ${num1 ge num2}<br>	<!-- ge (greater than or equal to): num1 >= num2 (num1이 num2보다 크거나 같은지 확인) -->
		num1 le num2 = ${num1 le num2}<br>	<!-- le (less than or equal to): num1 <= num2 (num1이 num2보다 작거나 같은지 확인) -->
		num1 eq num2 = ${num1 eq num2}<br>	<!-- eq (equal): num1 == num2 (num1과 num2가 같은지 확인) -->
		num1 ne num2 = ${num1 ne num2}<br><br>	<!-- ne (not equal): num1 != num2 (num1과 num2가 같지 않은지 확인) -->
		
		<!-- "" = 빈 문자열  -->
		<!-- empty = 빈 문자열  -->
		<!-- ${str1 eq "hello"}; = 문자열 비교  -->
		
		empty str1 = ${empty str1}<br>
		empty str2 = ${empty str2}<br>
		empty str3 = ${empty str3}<br>
		empty str4 = ${empty str4}<br>
		str1 eq "hello" = ${str1 eq "hello"};<br><br><br><br><br><br><br><br><br><br><br>
	</p>
	
	
	
	
	
</body>
</html>
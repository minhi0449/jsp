<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>3.Loop</title>
	<%--
		날짜 : 2024.08.05 (월)
		이름 : 김민희
		내용 : 반복문 실습하기
	 --%>
</head>
<body>
	<h3>3.반복문</h3>
	
	<h4>for</h4>
	<%
		for(int i=1 ; i<3 ; i++){
			out.println("<p>i : " + i + "</p>");
		}
	%>
	<%
		for(int j=1 ; j<=3 ; j++){
	%>
		<p>j : <%= j %></p>
	<%
		}
	%>
	
	
	<h4>while</h4>
	<%
		int k = 1;
		while(k <= 3){
	%>
		<p>k : <%= k %></p>
	<%
			k++;
		}
	%>
	
	<h4>구구단</h4>
	<% for(int x=2 ; x<=9 ; x++){ %>
	<% out.println (x+ "단"); %>
	<% for(int y=1 ; y<=9 ; y++){ %>
	<% int z = x * y;  %>
	<% out.println(x + " x " + y + " = " + z); %>
	<% } %>
	<% } %>
	
	<table>
		<tr>
			<th>1단</th>
			<th>2단</th>
			<th>3단</th>
			<th>4단</th>
			<th>5단</th>
			<th>6단</th>
			<th>7단</th>
			<th>8단</th>
			<th>9단</th>
		</tr>
		<tr>
			<td>2 x 1 = 2</td>
			<td>3 x 1 = 3</td>
			<td>4 x 1 = 4</td>
			<td>5 x 1 = 5</td>
			<td>6 x 1 = 6</td>
			<td>7 x 1 = 7</td>
			<td>8 x 1 = 8</td>
			<td>9 x 1 = 9</td>
		</tr>
	</table>
	
	<table border="1">
		<tr>
			<th>1단</th>
			<th>2단</th>
			<th>3단</th>
			<th>4단</th>
			<th>5단</th>
			<th>6단</th>
			<th>7단</th>
			<th>8단</th>
			<th>9단</th>
		</tr>
		<tr>
			<td>2 x 1 = 2</td>
			<td>3 x 1 = 3</td>
			<td>4 x 1 = 4</td>
			<td>5 x 1 = 5</td>
			<td>6 x 1 = 6</td>
			<td>7 x 1 = 7</td>
			<td>8 x 1 = 8</td>
			<td>9 x 1 = 9</td>
		</tr>
	</table>
	
	
	
</body>
</html>























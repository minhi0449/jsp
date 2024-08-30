<%@page import="com.google.gson.Gson"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="user1.User1VO"%>
<%@page import="javax.swing.text.AbstractDocument.Content"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	// 데이터 수신
	String uid = request.getParameter("uid");


	int rowCount = 0;

	try{
		 
		// 1단계 - JNDI 서비스 객체 생성
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		
		// 2단계 - 커넥션 가져오기
		DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
		Connection conn = ds.getConnection();
		 
		 // 2단계 - SQL실행 객체 실행
		 String sql = "delete from `user1` where `uid`=?";
		 PreparedStatement psmt = conn.prepareStatement(sql);
		 psmt.setString(1, uid);
		 //psmt.setString(1, uid);
		 //psmt.setString(2, name);
		 //psmt.setString(3, birth);
		 //psmt.setString(4, hp);
		 //psmt.setString(5, age); // 굳이 안해도 됨
		 
		 
		 // 3단계 - SQL실행 
		 // executeUpdate();하면 int 가 반환됨
	     rowCount = psmt.executeUpdate();  // executeInsert();  이런 쿼리문 없음 executeQuery(); 절대 아님
		 
		 
		 // 4단계 - 결과처리 (SELECT일 경우)
		 // 5단계 - 데이터베이스 종료
		 psmt.close();
		 conn.close();
	
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	
	// JSON 출력
	JsonObject json = new JsonObject();
	json.addProperty("result", rowCount);
	out.print(json.toString());

%>













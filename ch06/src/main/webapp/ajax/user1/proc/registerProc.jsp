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
	// JSON 문자열 스트림 처리
	BufferedReader reader = request.getReader();
	StringBuilder requestBody = new StringBuilder();
	
	String line;
	while((line = reader.readLine()) != null){
		requestBody.append(line);
	}
	
	reader.close();
	
	// JSON 파싱
	Gson gson = new Gson();
	User1VO user1 = gson.fromJson(requestBody.toString(), User1VO.class);
	System.out.println(user1);
	
     // 데이터 수신	 개별 파라미터가 아니야
	 //String uid = request.getParameter("uid");
	 //String name = request.getParameter("name");
	 //String birth = request.getParameter("birth");
	 //String hp = request.getParameter("hp");
	 //String age = request.getParameter("age");
	
	 //System.out.print("uid : " + uid);
	 //System.out.print("name : " + name);
	 //System.out.print("age : " + age);
	
	 int rowCount = 0;
	 
	 try{
		 
		// 1단계 - JNDI 서비스 객체 생성
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		
		// 2단계 - 커넥션 가져오기
		DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
		Connection conn = ds.getConnection();
		 
		 // 2단계 - SQL실행 객체 실행
		 String sql = "insert into `user1` values (?,?,?,?,?)";
		 PreparedStatement psmt = conn.prepareStatement(sql);
		 psmt.setString(1, user1.getUid());
		 psmt.setString(2, user1.getName());
		 psmt.setString(3, user1.getBirth());
		 psmt.setString(4, user1.getHp());
		 psmt.setInt(5, user1.getAge()); // 굳이 안해도 됨
		 
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
	 
	 // 목록 이동
	 //response.sendRedirect("/ch06/user1/list.jsp");
     //String jsonData = "{\"result\":"+rowCount+"}";
     
     JsonObject json = new JsonObject();
     json.addProperty("result", rowCount);
     
     out.print(json.toString());
     
%>






























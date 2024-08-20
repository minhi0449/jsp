<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.nio.Buffer"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sub1.FileVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	
	// 데이터베이스 파일 정보 삭제
	// sname 선언
	String sname = null;
	
	try{
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
		
		Connection conn = ds.getConnection();
		// delete 하기 전에 select 해줘야 함                       sname 선언
		PreparedStatement psmt = conn.prepareStatement("select sname from filetest where `no`=?");
		psmt.setString(1, no);
		Statement stmt = conn.createStatement(); 
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			// 저장된 이름만 조회
			sname = rs.getString(1);
		}
		
		stmt.executeUpdate("delete from filetest where `no`=" +no);
	
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
	// 디렉터리 파일 삭제
	String path = application.getRealPath("/uploads");
	File file = new File(path + File.separator + sname); // 파일명 : sname 가져와야 됨 
	
	file.delete();
	
	// 리다이렉트
	response.sendRedirect("/ch07/2.fileDownTest.jsp");
	
	
	
	
	

%>
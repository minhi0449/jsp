package com.ssamz.biz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertUserTest {
	public static void main(String[] args) {
		// JDBC 관련 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// JDBC 1단계 : 드라이버 객체 로딩
			DriverManager.registerDriver(new org.h2.Driver());
			
			// JDBC 2단계 : 커넥션 연결
			String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa", "");
			
			if(conn != null) {
				System.out.println("H2 연결 성공 : " + conn.toString());
			}
			
			// JDBC 3단계 : Statement 생성
			String sql = "insert into users values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			if(stmt != null) {
				System.out.println("Statement 객체 : " + stmt.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

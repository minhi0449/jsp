package com.jboard.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.CommentDTO;
import com.jboard.util.DBHelper;
import com.jboard.util.sql;

public class CommentDAO extends DBHelper {
	
	private static CommentDAO instance = new CommentDAO();
	public static CommentDAO getInstance() {
		return instance;
	}
	private CommentDAO() {}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// insert와 select 동시에 하기
	public int insertComment(CommentDTO dto) {
		
		int pk = 0 ;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.insert_comment, Statement.RETURN_GENERATED_KEYS); // insert 실행 후 자동생성되는 pk 값을 리턴하는 옵션 (모든 DB 표준) 
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate(); // result 값
			
			rs = psmt.getGeneratedKeys();
			if(rs.next()) {
				pk = rs.getInt(1);
				// 위의 int pk를 return
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return pk;
	}
	
	
	public CommentDTO selectComment(int no) {
		CommentDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.select_comment);
			psmt.setInt(1, no);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new CommentDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setRegip(rs.getString(5));
				dto.setRdateSubstring(rs.getString(6));
			}
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return dto;
	}
	
	public List<CommentDTO> selectComments(String parent) {
		
		List<CommentDTO> comments = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.select_comments);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setRegip(rs.getString(5));
				dto.setRdateSubstring(rs.getString(6));
				dto.setNick(rs.getString(7));
				comments.add(dto);
			}
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return comments;
	}
	
	public int updateComment(CommentDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.update_comment);
			psmt.setString(1, dto.getContent());
			psmt.setInt(2, dto.getNo());
			result = psmt.executeUpdate();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
	
	public int deleteComment(String no) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.delete_comment);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
}
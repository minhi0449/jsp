package com.jboard.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.UserDTO;
import com.jboard.util.DBHelper;
import com.jboard.util.sql;

public class UserDAO extends DBHelper {
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	private UserDAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int selectCountUser(String type, String value) {
		
		int result = 0;
		
		StringBuilder SQL = new StringBuilder(sql.select_count_user);
		
		if(type.equals("uid")) {
			SQL.append(sql.where_uid);
		}else if(type.equals("nick")) {
			SQL.append(sql.where_nick);
		}else if(type.equals("email")) {
			SQL.append(sql.where_email);
		}else if(type.equals("hp")) {
			SQL.append(sql.where_hp);
		}
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.toString());
			psmt.setString(1, value);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		
		return result;
	}
	
	
 	public void insertUser(UserDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.insert_user);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public UserDTO selectUser(String uid, String pass) {
		
		UserDTO user = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.select_user);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));				
			}
			closeAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return user;
	}
	public List<UserDTO> selectUsers() {
		return null;
	}
	public void updateUser(UserDTO dto) {}
	public void deleteUser(String uid) {}
}





















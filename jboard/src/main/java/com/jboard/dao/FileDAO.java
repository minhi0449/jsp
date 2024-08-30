package com.jboard.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.FileDTO;
import com.jboard.dto.UserDTO;
import com.jboard.util.DBHelper;
import com.jboard.util.sql;

public class FileDAO extends DBHelper {
 
	
	private static FileDAO instance = new FileDAO();
	public static FileDAO getInstance() {
		return instance;
	}
	private FileDAO() {}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertFile(FileDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.insert_file);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getoName());
			psmt.setString(3, dto.getsName());
			psmt.executeUpdate();
			closeAll();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		
	}
	
	public FileDTO selectFile(String fno) {
		
		FileDTO fileDto = null; // 선언
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.select_file);
			psmt.setString(1, fno);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				fileDto = new FileDTO(); // 생성
				fileDto.setFno(rs.getInt(1)); 
				fileDto.setAno(rs.getInt(2)); 
				fileDto.setoName(rs.getString(3)); 
				fileDto.setsName(rs.getString(4)); 
				fileDto.setDownload(rs.getInt(5)); 
				fileDto.setRdate(rs.getString(6)); 
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		
		return fileDto;
	}
	
	public List<FileDTO> selectFiles() {
		return null;
	}
	
	public void updateFileDownloadCount(String fno) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.update_file_download_count);
			psmt.setString(1, fno);
			psmt.executeUpdate();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	
	
	public void updateFile(FileDTO dto) {
		
	}
	
	public void deleteFile(int fno) {
		
	}
	
	
	
}

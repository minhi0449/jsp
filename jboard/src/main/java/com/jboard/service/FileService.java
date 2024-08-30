package com.jboard.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dao.FileDAO;
import com.jboard.dto.FileDTO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public enum FileService {
	
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	private FileDAO dao = FileDAO.getInstance();
	
	
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	
	public FileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	
	public List<FileDTO> selectFiles() {
		return selectFiles();
	}
	
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	
	public void updateFileDownloadCount(String fno) {
		dao.updateFileDownloadCount(fno);
	}

	public void deleteFile(int fno) {
		dao.deleteFile(fno);
	}
	
	public List<FileDTO> fileUpload(HttpServletRequest req) {
		
		// return 될 List를 여기에다가
		List<FileDTO> files = new ArrayList<>();
		
		ServletContext ctx = req.getServletContext();
		String uploadPath = ctx.getRealPath("/uploads");
		logger.debug("uploadPath : " + uploadPath);
		
		try {
			// Part list라고 생각하셈 첨부파일 2개라서 => parts
			Collection<Part> parts = req.getParts(); // 첨부 파일 정보객체 가져오기 (Part 첨부파일 객체다)
		
			for(Part part : parts) {
				// 파일명 추출 (다른 애들은 파일명이 없어)
				String ofileName = part.getSubmittedFileName();
				
				if(ofileName != null && !ofileName.isEmpty()) {
					logger.debug("ofileName : " + ofileName);
					
					// 고유 파일명 생성
					int idx = ofileName.lastIndexOf(".");
					String ext= ofileName.substring(idx);
					
					String sfileName = UUID.randomUUID().toString() + ext;
					// 파일을 첨부했으면 
					logger.debug("sfileName : " + sfileName);
					
					// 파일 저장
					part.write(uploadPath + File.separator + sfileName);
					
					// fildDTO 생성
					FileDTO fileDto = new FileDTO();
					fileDto.setoName(ofileName);
					fileDto.setsName(sfileName);
					files.add(fileDto);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return files;		
	}
	
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp) {
		
		// 공유 참조값 가져오기
		FileDTO fileDto = (FileDTO) req.getAttribute("fileDto");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			// response 헤더정보 수정
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDto.getoName(), "utf-8"));
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "private");
	
			// 파일 내용 스트림 처리
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/uploads");
			File file = new File(path + File.separator + fileDto.getsName());
			
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(resp.getOutputStream());
			
			bis.transferTo(bos);
			bos.flush();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			
			try {
				
				if(bos != null) {
					bos.close();
				}
				
				if(bis != null) {
					bis.close();
				}
			
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
	}
}





package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.ArticleDTO;
import com.jboard.dto.FileDTO;
import com.jboard.dto.UserDTO;
import com.jboard.service.ArticleService;
import com.jboard.service.FileService;
import com.jboard.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/write.do")
public class WriteController extends HttpServlet{


	private static final long serialVersionUID = 324614381015718983L;
	
	private ArticleService articleservice = ArticleService.INSTANCE;
	private FileService fileservice = FileService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();

		// 파일 업로드
		List<FileDTO> files = fileservice.fileUpload(req);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(files.size());
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 글 등록 (글 등록이 되어야 파일 업로드가 된다) (insert를 하고 글 번호를 반환 받자 !)(등록된 글의 글 번호를 반환을 받는 다면)
		int no = articleservice.insertArticle(dto);
		
		for(FileDTO fileDto : files) {
			fileDto.setAno(no);
			fileservice.insertFile(fileDto);
		}
		resp.sendRedirect("/jboard/article/list.do");
	}
}











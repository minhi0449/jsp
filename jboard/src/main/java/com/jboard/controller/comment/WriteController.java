package com.jboard.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jboard.dto.CommentDTO;
import com.jboard.service.CommentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comment/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = 5903808803237119629L;
	private CommentService service = CommentService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 폼 데이터 수신
		String parent = req.getParameter("parent");
		String comment = req.getParameter("comment");
		String writer = req.getParameter("writer"); // 댓글 작성자
		String regip = req.getRemoteAddr();
		
		// DTO 생성
		CommentDTO dto = new CommentDTO();
		dto.setParent(parent);
		dto.setContent(comment);
		dto.setWriter(writer);
		dto.setRegip(regip);
		logger.debug(dto.toString());
		
		// 댓글 등록 (단순히 result를 리턴하는 게 아니고)
		int pk = service.insertComment(dto);
		
		// 등록한 댓글 조회
		CommentDTO commentDto = service.selectComment(pk);
		//commentDto 자체가 이미 필드를 가지고 있음
		
		
		// 리다이렉트 안 함
		//resp.sendRedirect("/jboard/article/view.do?no="+parent);
		
		// JSON 생성 및 출력 (fetch 함수 사용했기 때문에 리다이렉트 아님)
		//JsonObject json = new JsonObject();
		//json.addProperty("result", result);
		//json.addProperty("nick", commentDto.getNick());
		//json.addProperty("Rdate", commentDto.getRdate());
		//json.addProperty("content", commentDto.getContent());
		
		// JSON 생성 및 출력
		Gson gson = new Gson();
		String json = gson.toJson(commentDto);
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.print(json);
		
		
	}
	
}
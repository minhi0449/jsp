package com.jboard.controller.user;

import java.io.IOException;

import com.jboard.dao.TermsDAO;
import com.jboard.dto.TermsDTO;
import com.jboard.service.TermsService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet {

	private static final long serialVersionUID = 324614381015718983L;

	private TermsService service = TermsService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		TermsDAO termsDTO = service.selectTerms();
		
		req.setAttribute("termsDTO", termsDTO);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/terms.jsp");
		dispatcher.forward(req, resp);
	}
	
}




















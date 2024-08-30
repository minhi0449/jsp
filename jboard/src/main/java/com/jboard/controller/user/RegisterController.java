package com.jboard.controller.user;

import java.io.IOException;

import com.jboard.dto.UserDTO;
import com.jboard.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet{


	private static final long serialVersionUID = 324614381015718983L;

	private UserService service = UserService.instance;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/register.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	 // 데이터 수신
	 String uid = req.getParameter("uid");
	 String pass = req.getParameter("pass1");
	 String name = req.getParameter("name");
	 String nick = req.getParameter("nick");
	 String email = req.getParameter("email");
	 String hp = req.getParameter("hp");
	 String zip = req.getParameter("zip");
	 String addr1 = req.getParameter("addr1");
	 String addr2 = req.getParameter("addr2");
	 String regip = req.getRemoteAddr();
			 
	 // DTO 생성 9개 데이터를 1개씩 넘기는 거 보다 9개를 한 번에 넘기는 게 더 효율적이라서 DTO 생성하는 것
	 //service.insertUser(uid, pass, name, nick, email, hp, zip, addr1, addr2);
	 // 이게 하나씩 넘 겨 주 는 거
	 
	 // DTO 생성
	 UserDTO userDTO = new UserDTO();
	 userDTO.setUid(uid);
	 userDTO.setPass(pass);
	 userDTO.setName(name);
	 userDTO.setNick(nick);
	 userDTO.setEmail(email);
	 userDTO.setHp(hp);
	 userDTO.setZip(zip);
	 userDTO.setAddr1(addr1);
	 userDTO.setAddr2(addr2);
	 userDTO.setRegip(regip);
	 
	 // 데이터 저장
	 service.insertUser(userDTO);
	 
	 // 리다이렉트
	 resp.sendRedirect("/jboard/user/login.do");
	 
	}
}









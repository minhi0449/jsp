package com.ssamz.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginProcess", urlPatterns = { "/LoginProcess" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
    	System.out.println("===> LoginServlet 생성");
    }
	
	public LoginServlet(String servletName) {
    	System.out.println("===> " + servletName + "생성");
    }

	public void init() throws ServletException {
		System.out.println("---> init 호출");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> service() 호출");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> GET 방식의 요청 처리");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> POST 방식의 요청 처리");
	}
	
	public void destroy() {
		System.out.println("---> destory() 호출");
	}
	
}

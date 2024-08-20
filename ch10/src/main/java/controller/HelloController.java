package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello.do")
public class HelloController extends HttpServlet{

	private static final long serialVersionUID = 5737892199811153918L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 출력 할 뷰 페이지 경로  (여기서 Run as 해봐바 -> index 클릭)
		//RequestDispatcher dispatcher = req.getRequestDispatcher("뷰 페이지 경로");
		// 포워드 경로 => WEB-INF (웹 인포에 넎었을 때)
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/hello.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	}
	
	
	
}

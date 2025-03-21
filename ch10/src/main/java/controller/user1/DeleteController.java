package controller.user1;

import java.io.IOException;
import java.util.List;

import dto.User1DTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.User1Service;


@WebServlet("/user1/delete.do")
public class DeleteController extends HttpServlet{


	private static final long serialVersionUID = 3989756952756485985L;

	private User1Service service = User1Service.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// a태그는 get 입니다.
		// 데이터 수신
		String uid = req.getParameter("uid");
		
		// 데이터 삭제
		service.deleteUser1(uid);
		
		// 리다이렉트
		resp.sendRedirect("/ch10/user1/list.do");
		
		
	}
	
	
}

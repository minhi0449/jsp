package com.jboard.controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jboard.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/user/checkUser.do")
public class CheckUserController extends HttpServlet{

	private static final long serialVersionUID = 5473767728246242808L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserService service = UserService.instance;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 수신 (uid, nick) 파라미터 하나로 만들기
		String type = req.getParameter("type");
		String value = req.getParameter("value");
		logger.debug("type : " + type);
		logger.debug("value : " + value);

		// 조회하기
		int result = service.selectCountUser(type, value);
		
		if(type.equals("email") && result == 0) {
			// 이메일 인증번호 발송하기 value 값이 사용자가 입력한 이메일 값
			String code = service.sendEmailCode(value);
			
			// 세션 저장
			HttpSession session = req.getSession();
			session.setAttribute("authCode", code);
			
		}
		
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 문자열 스트림 처리 해줘야함 fetch 함수
		// JSON 문자열 스트림 처리
		BufferedReader reader = req.getReader();
		StringBuilder requestBody = new StringBuilder();
		
		String line;
		while((line = reader.readLine()) != null){
			requestBody.append(line);
		}
		
		reader.close();
		
		// JSON 파싱
		Gson gson = new Gson();
		Properties prop = gson.fromJson(requestBody.toString(), Properties.class);
		String code = prop.getProperty("code");
		logger.debug("code : " + code);
		
		// 인증코드 일치 여부 확인 authCode = code 랑 비교하는 것
		HttpSession session = req.getSession();
		String authCode = (String) session.getAttribute("authCode");
		logger.debug("authCode : " + authCode);
		
		// JSON 생성 후 출력
		JsonObject json = new JsonObject();
		
		if(authCode.equals(code)) {
			// JSON 생성 & 출력
			json.addProperty("result", 1);
			
		}else {
			json.addProperty("result", 0);
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(json);
		
	}
}


















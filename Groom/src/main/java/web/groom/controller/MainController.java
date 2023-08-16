package web.groom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.gr") //.gr 어노테이션 매핑 선언
public class MainController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI(); // uri의 주소 추출 /Groom/main.gr
		String contextPath = request.getContextPath(); // 프로젝트 명 추출/Groom
		String page = uri.substring(contextPath.length()); //substring을 이용하여 contextPath(/Groom)뒤인 페이지명(main.gr)등을 추출

		 if (page.equals("/main.gr")) {
	            webForward(request, response, "mainpages", "main");
	     } 
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String page) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+page+".jsp").forward(request, response);
	}

}

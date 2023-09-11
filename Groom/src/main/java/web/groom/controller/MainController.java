package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.ReviewDTO;
import web.groom.service.MainService;


@WebServlet("*.gr") //.gr 메인 어노테이션 매핑 선언
public class MainController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 // 메인페이지 main/main.gr
		 if (sPath.equals("/main.gr")) {
			 
			 // 메인페이지에 베스트 리뷰(3개)를 보여주기 위한 메서드 호출
			 List<ReviewDTO> reviewList = new MainService().getReviewList();
			 
			 request.setAttribute("reviewList", reviewList);
			 
	         webForward(request, response, "main", "main");      
	     }
		 
		 // 매장정보 페이지 main/storeInfo.gr
		 if (sPath.equals("/storeInfo.gr")) {
	            webForward(request, response, "main", "storeInfo");

	     }
		 
		 // 회사정보 페이지 main/about.gr
		 if (sPath.equals("/about.gr")) {
	            webForward(request, response, "main", "about");

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
	
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+pageName+".jsp").forward(request, response);
	}

} //end_of_MainController

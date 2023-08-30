package web.groom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.ReviewDTO;
import web.groom.service.MainService;
import web.groom.service.ReviewService;


@WebServlet("*.gr") //.gr 메인 어노테이션 매핑 선언
public class MainController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 //메인화면페이지
		 if (sPath.equals("/main.gr")) {
			 
			 MainService mainService = new MainService();
			 List<ReviewDTO> reviewList = mainService.getReviewList();
			 
			 request.setAttribute("reviewList", reviewList);
			 
	         webForward(request, response, "main", "main");      
	     }
		 
		 if (sPath.equals("/storeInfo.gr")) {
	            webForward(request, response, "main", "storeInfo");

	     }
		 
		 if (sPath.equals("/blog.gr")) {
	            webForward(request, response, "main", "blog");

	     }
		 if (sPath.equals("/contact.gr")) {
	            webForward(request, response, "main", "contact");

	     }
		 
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

}

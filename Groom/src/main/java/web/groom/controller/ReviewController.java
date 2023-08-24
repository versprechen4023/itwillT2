package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.ReviewDTO;
import web.groom.service.ReviewService;

@WebServlet("*.re") //.re 리뷰페이지 어노테이션 매핑 선언
public class ReviewController extends HttpServlet {
	
	RequestDispatcher dispatcher = null; 
	ReviewService reviewService = null;
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		
		//페이지이동
		 if (sPath.equals("/review.re")) {
			 reviewService = new ReviewService();
			 List<ReviewDTO> reviewList = reviewService.getReviewList();
			 request.setAttribute("reviewList", reviewList);
			 System.out.println("review.re");
			 
			 webForward(request, response, "review", "review");
	     }// review.re [리뷰목록]
		 
		 if (sPath.equals("/reviewDetails.re")) {
			 System.out.println("reviewDetails.re");
			 reviewService = new ReviewService();
			 reviewService.updateReadcount(request); // 조회수
			 
			 ReviewDTO reviewDTO = reviewService.getReview(request);
			 request.setAttribute("reviewDTO", reviewDTO);
			 webForward(request, response, "review", "reviewDetails");
	     }// reviewDetails.re [리뷰상세]
		 
//========================================================================
		 if (sPath.equals("/reviewDetails_my.re")) {
			 System.out.println("reviewDetails_my.re");
			 webForward(request, response, "review", "reviewDetails_my");
	     }// reviewDetails_my.re [리뷰상세]
//========================================================================
		 if (sPath.equals("/writereview.re")) {
			 System.out.println("writereview.re");
			 webForward(request, response, "review", "writereview");
	     }// writereview.re
		 
		 if (sPath.equals("/writereviewPro.re")) {
			 System.out.println("writereviewPro.re");
			 request.setCharacterEncoding("utf-8");
			 reviewService = new ReviewService();
			 reviewService.insertReview(request);
			 response.sendRedirect("리뷰작성하면 어디로갈까");
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

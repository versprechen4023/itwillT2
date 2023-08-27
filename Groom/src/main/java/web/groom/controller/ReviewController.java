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
		 if (sPath.equals("/reviewList.re")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 System.out.println("reviewList.re");
			 webForward(request, response, "review", "reviewList");
	     }// reviewList.re [리뷰목록]
		 
		 
		 if (sPath.equals("/reviewList1.re")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 webForward(request, response, "review", "reviewList1");
	     }// reviewList1.re [리뷰목록1]
		 if (sPath.equals("/reviewList2.re")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 webForward(request, response, "review", "reviewList2");
	     }// reviewList2.re [리뷰목록2]
		 if (sPath.equals("/reviewList3.re")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 webForward(request, response, "review", "reviewList3");
	     }// reviewList3.re [리뷰목록3] //////////// 상단 메뉴 선택시 버튼색이 안변해서 페이지 여러개 만듦
		 
		 
		 if (sPath.equals("/reviewContent.re")) {
			 System.out.println("reviewDetails.re");
			 reviewService = new ReviewService();
			 reviewService.updateReadcount(request); // 조회수
			 
			 ReviewDTO reviewDTO = reviewService.getReview(request);
			 request.setAttribute("reviewDTO", reviewDTO);
			 webForward(request, response, "review", "reviewContent");
	     }// reviewContent.re [리뷰상세]
		 

		 if (sPath.equals("/reviewWrite.re")) {
			 System.out.println("writereview.re");
			 webForward(request, response, "review", "reviewWrite");
	     }// reviewWrite.re [리뷰작성]
		 		 
		 if (sPath.equals("/reviewWritePro.re")) {
			 System.out.println("reviewWritePro.re");
			 request.setCharacterEncoding("utf-8");
			 reviewService = new ReviewService();
			 reviewService.insertReview(request);
			 response.sendRedirect("reviewList.re?pro_name= "); //일단 목록페이지로
		 }// writereviewPro.re [리뷰작성 후 등록]
		 
	
		 if(sPath.equals("/reviewDelete.re")) {
			 reviewService = new ReviewService();
			 reviewService.deleteReview(request);
			 response.sendRedirect("reviewList.re?pro_name=");
		 }// reviewDelete.re [리뷰삭제]
		 
// 답글작성용 페이지 제작 필요, 리뷰수정용 페이지도 동시에 제작하는걸로 /////////////////////////
		 
		 if(sPath.equals("/reWrite.re")) {
			 System.out.println("reWrite.re");
			 reviewService = new ReviewService();
			 ReviewDTO reviewDTO = reviewService.getReview(request);
			 request.setAttribute("reviewDTO", reviewDTO);
		 }//reWrite.re [답글작성]
		 
		 if(sPath.equals("reWritePro.re")) {
			 System.out.println("reWritePro.re");
			 reviewService = new ReviewService();
			 reviewService.writeRe(request);
			 response.sendRedirect("main.gr"); // 일단메인으로
		 }//reWritePro.re [답글작성 후 등록]
		 
		 if(sPath.equals("/reDelete.re")) {
			 System.out.println("reDelete.re");
			 reviewService = new ReviewService();
			 reviewService.deleteRe(request);
			 response.sendRedirect("main.gr"); // 일단메인으로
		 }// reDelete.re [답글삭제]
		 
		 
		 
	}// doProcess

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

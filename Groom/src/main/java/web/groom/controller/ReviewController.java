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
import web.groom.javascript.JSForward;
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
//			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewListAll();
			 request.setAttribute("reviewList", reviewList);
			 System.out.println("reviewList.re");
			 webForward(request, response, "review", "reviewList");
	     }// reviewList.re [리뷰목록]
		
		 if (sPath.equals("/reviewList.re?")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 System.out.println("reviewList.re");
			 webForward(request, response, "review", "reviewList");
	     }// reviewList.re [리뷰목록 선택]
		 
		 if (sPath.equals("/myReviewList.re")) {
			 reviewService = new ReviewService();
			 int u_num = Integer.parseInt(request.getParameter("u_num"));
			 List<ReviewDTO> reviewList = reviewService.getMyReviewList(u_num);
			 request.setAttribute("reviewList", reviewList);
			 System.out.println("reviewList.re");
			 webForward(request, response, "review", "reviewList");
	     }// myReviewList.re [내 리뷰목록] // '내정보'에서 'u_num' 넘기는 버튼과 연결시키기 //
		 
		 
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
	     }// reviewList3.re [리뷰목록3]
		 if (sPath.equals("/reviewList4.re")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 webForward(request, response, "review", "reviewList4");
	     }// reviewList4.re [리뷰목록4]
		 if (sPath.equals("/reviewList5.re")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 webForward(request, response, "review", "reviewList5");
	     }// reviewList5.re [리뷰목록5]
		 if (sPath.equals("/reviewList6.re")) {
			 reviewService = new ReviewService();
			 String proName = request.getParameter("pro_name");
			 List<ReviewDTO> reviewList = reviewService.getReviewList(proName);
			 request.setAttribute("reviewList", reviewList);
			 webForward(request, response, "review", "reviewList6");
	     }// reviewList6.re [리뷰목록6]
		 //////////// 상단 메뉴 선택시 버튼색이 안변해서 페이지 여러개 만듦
		 
		 
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
			 JSForward.windowClose(response); // 창닫기 라인
		 }// writereviewPro.re [리뷰작성 후 등록]
		 
	
		 if(sPath.equals("/reviewDelete.re")) {
			 System.out.println("reviewDelete.re");
			 reviewService = new ReviewService();
			 reviewService.deleteReview(request);
			 response.sendRedirect("reviewList.re?pro_name=");
		 }// reviewDelete.re [리뷰삭제]
		 
		 if(sPath.equals("/reviewDeletePoint.re")) {
			 System.out.println("reviewDeletePoint.re");
			 reviewService = new ReviewService();
			 reviewService.deleteReviewPoint(request);
			 response.sendRedirect("reviewList.re?pro_name=");
		 }// reviewDelete.re [리뷰삭제 + 포인트회수]
		 
		 
		 if(sPath.equals("/reWrite.re")) {
			 System.out.println("reWrite.re");
			 reviewService = new ReviewService();
			 ReviewDTO reviewDTO = reviewService.getReview(request);
			 request.setAttribute("reviewDTO", reviewDTO);
			 webForward(request, response, "review", "reWrite");
		 }//reWrite.re [답글작성]
		 
		 if(sPath.equals("/reWritePro.re")) {
			 System.out.println("reWritePro.re");
			 reviewService = new ReviewService();
			 reviewService.writeRe(request);
			 response.sendRedirect("reviewList.re?pro_name="); // 목록으로
		 }//reWritePro.re [답글작성 후 등록 + 포인트추가]
		 
		 
		 if(sPath.equals("/reUpdate.re")) {
			 System.out.println("reUpdate.re");
			 reviewService = new ReviewService();
			 ReviewDTO reviewDTO = reviewService.getReview(request);
			 request.setAttribute("reviewDTO", reviewDTO);
			 webForward(request, response, "review", "reUpdate");
		 }//reUpdate.re [답글수정]
		 
		 if(sPath.equals("/reUpdatePro.re")) {
			 System.out.println("reUpdatePro.re");
			 reviewService = new ReviewService();
			 reviewService.updateRe(request);
			 response.sendRedirect("reviewList.re?pro_name="); // 목록으로
		 }//reUpdatePro.re [답글수정 후 등록]
		 
		 
		 if(sPath.equals("/reDelete.re")) {
			 System.out.println("reDelete.re");
			 reviewService = new ReviewService();
			 reviewService.deleteRe(request);
			 response.sendRedirect("reviewList.re?pro_name="); // 목록으로
		 }// reDelete.re [답글삭제]
		 
		 
		 if(sPath.equals("/reviewUpdate.re")) {
			 System.out.println("reviewUpdate.re");
			 reviewService = new ReviewService();
			 ReviewDTO reviewDTO = reviewService.getReview(request);
			 request.setAttribute("reviewDTO", reviewDTO);
			 webForward(request, response, "review", "reviewUpdate");
		 }//reUpdate.re [리뷰수정]
		 
		 if(sPath.equals("/reviewUpdatePro.re")) {
			 System.out.println("reviewUpdatePro.re");
			 reviewService = new ReviewService();
			 reviewService.updateReview(request);
			 response.sendRedirect("reviewList.re?pro_name="); // 목록으로
		 }//reviewUpdatePro.re [리뷰수정 후 등록]
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
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

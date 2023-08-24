package web.groom.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.ReviewDAO;
import web.groom.dto.ReviewDTO;

public class ReviewService {
ReviewDAO reviewDAO = null;
	
	public List<ReviewDTO> getReviewList() {
		System.out.println("리뷰서비스");
		List<ReviewDTO> reviewList = null;
		try {
			reviewDAO = new ReviewDAO();
			reviewList = reviewDAO.getReviewList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getReviewList() [리뷰목록]

	public ReviewDTO getReview(HttpServletRequest request) {
		System.out.println("ReviewService getReview");
		ReviewDTO reviewDTO = null;
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			reviewDAO = new ReviewDAO();
			reviewDTO = reviewDAO.getReview(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewDTO;
	}// getReview() [리뷰상세]
	
	public void updateReadcount(HttpServletRequest request) {
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			reviewDAO = new ReviewDAO();
			reviewDAO.updateReadcount(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// updateReadcount [리뷰조회수] 증가
	
// ========================================================================================================
	public void insertReview(HttpServletRequest request) {
		int rev_num = 1;
		int u_num;
		int res_num;
		int pro_id;
		
		String pro_name;
		String rev_img_url;
		String emp_grade;
		String emp_name;
		String s_location;
		String rev_rating;
		String u_name;
		String rev_date;
		String u_count;
		String rev_content;
		int rev_count = 0;
		
//		rev_num = reviewDAO.getMaxNum() + 1;
		
	}// insertReview() Details 먼저하고 마지막에 하자..
	
	
}

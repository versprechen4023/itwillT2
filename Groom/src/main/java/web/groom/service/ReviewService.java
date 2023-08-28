package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web.groom.dao.ReviewDAO;
import web.groom.dto.ReviewDTO;

public class ReviewService {
ReviewDAO reviewDAO = null;
	
public List<ReviewDTO> getReviewList(String proName) {
		System.out.println("ReviewService getReviewList()");
		List<ReviewDTO> reviewList = null;
		try {
			reviewDAO = new ReviewDAO();
			reviewList = reviewDAO.getReviewList(proName); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getReviewList() [리뷰목록]


	public ReviewDTO getReview(HttpServletRequest request) {
		System.out.println("ReviewService getReview()");
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
	
	
	public void insertReview(HttpServletRequest request) {
		System.out.println("ReviewService insertReview");
		try {
			String uploadPath=request.getRealPath("/upload");
			System.out.println(uploadPath);
			int maxSize=10*1024*1024;
			MultipartRequest multi 
			= new MultipartRequest(request, uploadPath, maxSize,"utf-8", new DefaultFileRenamePolicy()); 
			
//			int rev_num = 1; // (auto_increment)
			int u_num = 0; // 세션
			int res_num = 0; // from예약내역
			int pro_id = 0; // from예약내역
			int s_num = 0; // from예약내역
			String rev_content = multi.getParameter("rev_content");
			String rev_img_url = multi.getFilesystemName("rev_img_url");
			String rev_rating = multi.getParameter("rev_rating");
//			String rev_date = "now()"; // 일단 임의지정
			Timestamp rev_date = new Timestamp(System.currentTimeMillis());
			int rev_count = 0; // 조회수 초기값=0
			int re_ref = 0; // 답글번호 초기값=0
			int re_lev = 0; // 답글깊이 초기값=0
			int re_seq = 0; // 답글개수 초기값=0
			String re_content = null; //
			Timestamp re_date = null; //
			
			reviewDAO = new ReviewDAO();
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setU_num(u_num);
			reviewDTO.setRes_num(res_num);
			reviewDTO.setPro_id(pro_id);
			reviewDTO.setPro_id(s_num);
			reviewDTO.setRev_content(rev_content);
			reviewDTO.setRev_img_url(rev_img_url);
			reviewDTO.setRev_rating(rev_rating);
			reviewDTO.setRev_date(rev_date);
			reviewDTO.setRev_count(rev_count);
			reviewDTO.setRe_ref(re_ref);
			reviewDTO.setRe_lev(re_lev);
			reviewDTO.setRe_seq(re_seq);
			reviewDTO.setRe_content(re_content); //
			reviewDTO.setRe_date(re_date); //
			reviewDAO.insertReview(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// insertReview() [리뷰작성]
	
	
	public void deleteReview(HttpServletRequest request) {
		System.out.println("ReviewService deleteReview()");
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			reviewDAO = new ReviewDAO();
			reviewDAO.deleteReview(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// deleteReview() [리뷰삭제]


	public void writeRe(HttpServletRequest request) { // 여기도 multi로 해야하는지 확인할것
		System.out.println("ReviewService writeRe()");
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			String re_content = request.getParameter("re_content");
			Timestamp re_date = new Timestamp(System.currentTimeMillis());
			
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRe_content(re_content);
			reviewDTO.setRe_date(re_date);
			
			reviewDAO = new ReviewDAO();
			reviewDAO.writeRe(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// writeRe [답글작성]
	
	public void updateRe(HttpServletRequest request) { // 여기도 multi로 해야하는지 확인할것
		System.out.println("ReviewService updateRe()");
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			String re_content = request.getParameter("re_content");
//			Timestamp re_date = new Timestamp(System.currentTimeMillis());
			
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRe_content(re_content);
//			reviewDTO.setRe_date(re_date); // 수정해도 시간은 그대로
			
			reviewDAO = new ReviewDAO();
			reviewDAO.updateRe(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// updateRe [답글수정]


	public void deleteRe(HttpServletRequest request) { // 여기도 multi로 해야하는지 확인할것
		System.out.println("ReviewService deleteRe()");
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			String re_content = request.getParameter("re_content");
			
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRe_content(re_content);
			
			reviewDAO = new ReviewDAO();
			reviewDAO.deleteRe(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// deleteRe [답글삭제]


	public void updateReview(HttpServletRequest request) {
		System.out.println("ReviewService updateReview() fupdate 참고");
	}

	
	
	
	
	
	
	
}// class

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
	
    //모든 리뷰 리스트 가져오는 서비스
	public List<ReviewDTO> getReviewListAll() {
		System.out.println("ReviewService getReviewList()");
		List<ReviewDTO> reviewList = null;
		try {
			// getReviewListAll()메서드 호출
			reviewList = new ReviewDAO().getReviewListAll(); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getReviewList() [리뷰목록]
	
	// 특정 프로덕트에 대한 리뷰 리스트 가져오는 서비스
	public List<ReviewDTO> getReviewList(String proName) {
		System.out.println("ReviewService getReviewList()");
		List<ReviewDTO> reviewList = null;
		try {
			// getReviewList(proName)메서드 호출
			reviewList = new ReviewDAO().getReviewList(proName); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getReviewList() [리뷰목록 선택]
	
	// 내 리뷰 리스트 가져오는 서비스
	public List<ReviewDTO> getMyReviewList(int u_num) {
		System.out.println("ReviewService getReviewList()");
		List<ReviewDTO> reviewList = null;
		try {
			reviewList = new ReviewDAO().getMyReviewList(u_num); // 유저번호 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getMyReviewList() [내 리뷰목록]


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
	
	//
	public void insertReview(HttpServletRequest request) {
		System.out.println("ReviewService insertReview");
		try {
			String uploadPath=request.getRealPath("/upload");
			System.out.println(uploadPath);
			int maxSize=10*1024*1024;
			MultipartRequest multi 
			= new MultipartRequest(request, uploadPath, maxSize,"utf-8", new DefaultFileRenamePolicy()); 
			
//			int rev_num = 1; // (auto_increment)
			int u_num = Integer.parseInt(multi.getParameter("u_num"));
			int res_num = Integer.parseInt(multi.getParameter("res_num"));
			String rev_content = multi.getParameter("rev_content");
			String rev_img_url = multi.getFilesystemName("rev_img_url");
			String rev_rating = multi.getParameter("rev_rating");
			Timestamp rev_date = new Timestamp(System.currentTimeMillis());
			int rev_count = 0; // 조회수 초기값=0
			int re_ref = 0; // 답글번호 초기값=0
			int re_lev = 0; // 답글깊이 초기값=0
			int re_seq = 0; // 답글개수 초기값=0
			String re_content = null;
			Timestamp re_date = null;
			
			reviewDAO = new ReviewDAO();
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setU_num(u_num);
			reviewDTO.setRes_num(res_num);
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
	
	
	public boolean updateReview(HttpServletRequest request) {
		System.out.println("ReviewService updateReview()");
		
		boolean result = false;
		
		try {
			String uploadPath=request.getRealPath("/upload");
			int maxSize=10*1024*1024;
			MultipartRequest multi 
			= new MultipartRequest(request, uploadPath, maxSize,"utf-8", new DefaultFileRenamePolicy()); 
			
			int rev_num = Integer.parseInt(multi.getParameter("rev_num"));
			String rev_content = multi.getParameter("rev_content");
			String rev_img_url = multi.getFilesystemName("rev_img_url");
			String rev_rating = multi.getParameter("rev_rating"); // 잠시
			
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRev_content(rev_content);
			reviewDTO.setRev_img_url(rev_img_url);
			reviewDTO.setRev_rating(rev_rating); // 잠시
			result = new ReviewDAO().updateReview(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// insertReview() [리뷰수정]
	
	
	public boolean deleteReview(HttpServletRequest request) {
		System.out.println("ReviewService deleteReview()");
		boolean result = false;
		
		try {
			// 리퀘스트로 부터 리뷰번호 가져오기
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			reviewDAO = new ReviewDAO();
			result = reviewDAO.deleteReview(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// deleteReview() [리뷰삭제]
	
	public void deleteReviewPoint(HttpServletRequest request) {
		System.out.println("ReviewService deleteReviewPoint()");
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			reviewDAO = new ReviewDAO();
			reviewDAO.deleteReviewPoint(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// deleteReviewPoint() [리뷰삭제 + 포인트회수]


	public boolean writeRe(HttpServletRequest request) { // 여기도 multi로 해야하는지 확인할것
		System.out.println("ReviewService writeRe()");
		
		boolean result = false;
		try {
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			String re_content = request.getParameter("re_content");
			Timestamp re_date = new Timestamp(System.currentTimeMillis());
			
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRe_content(re_content);
			reviewDTO.setRe_date(re_date);
			
			reviewDAO = new ReviewDAO();
			result = new ReviewDAO().writeRe(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// writeRe [답글작성 + 포인트추가]
	

	public boolean updateRe(HttpServletRequest request) { // 여기도 multi로 해야하는지 확인할것
		System.out.println("ReviewService updateRe()");
		
		boolean result = false;
		try {
			// 변수에 리퀘스트 파라미터값 저장(리뷰번호, 리뷰내용)
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			String re_content = request.getParameter("re_content");
//			Timestamp re_date = new Timestamp(System.currentTimeMillis());
			
			// DTO에 변경할 리뷰의 내용과 번호를 저장
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRe_content(re_content);
//			reviewDTO.setRe_date(re_date); // 수정해도 시간은 그대로
			
			// 리뷰 내용을 변경하기위해 DAO 호출
			result = new ReviewDAO().updateRe(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// updateRe [답글수정]

	// 답글 삭제를 위한 서비스
	public boolean deleteRe(HttpServletRequest request) {
		System.out.println("ReviewService deleteRe()");
		
		boolean result = false;
		
		try {
			// 변수에 리퀘스트 파라미터값 저장(리뷰번호)
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			
			// 답글 삭제후 초기화를 위한 DAO 호출
			result = new ReviewDAO().deleteRe(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// deleteRe [답글삭제]


	



	
		
	
	
	
	
}// class

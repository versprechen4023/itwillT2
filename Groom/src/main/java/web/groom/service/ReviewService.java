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

	// 모든 리뷰 리스트 가져오는 서비스
	public List<ReviewDTO> getReviewListAll() {
		System.out.println("ReviewService getReviewList()");
		List<ReviewDTO> reviewList = null;
		try {
			// 모든 리뷰 리스트를 얻기위한 getReviewListAll()메서드 호출
			reviewList = new ReviewDAO().getReviewListAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getReviewList() [리뷰목록]

	// 특정 프로덕트에 대한 리뷰 리스트 가져오는 서비스
	public List<ReviewDTO> getReviewList(HttpServletRequest request) {
		System.out.println("ReviewService getReviewList()");
		List<ReviewDTO> reviewList = null;
		try {
			// 변수에 리퀘스트 파라미터값 저장(프로덕트명(상품명))
			String proName = request.getParameter("pro_name");
			// 특정 프로덕트에 대한 리뷰 리스트를 얻기위한 getReviewList(proName)메서드 호출
			reviewList = new ReviewDAO().getReviewList(proName); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getReviewList() [리뷰목록 선택]

	// 내 리뷰 리스트 가져오는 서비스
	public List<ReviewDTO> getMyReviewList(HttpServletRequest request) {
		System.out.println("ReviewService getReviewList()");
		List<ReviewDTO> reviewList = null;
		try {
			// 세션에서 유저 번호 가져오기
			int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));
			// 유저의 리뷰 리스트를 얻기 위한 getMyReviewList(u_num)메서드 호출
			reviewList = new ReviewDAO().getMyReviewList(u_num); // 유저번호 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}// getMyReviewList() [내 리뷰목록]

	// 리뷰 내용 가져오는 서비스
	public ReviewDTO getReview(HttpServletRequest request) {
		System.out.println("ReviewService getReview()");
		ReviewDTO reviewDTO = null;
		try {
			// 변수에 리퀘스트 파라미터값 저장(리뷰 글번호)
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			// 리뷰 내용을 얻기 위한 getReview(rev_num)메서드 호출
			reviewDTO = new ReviewDAO().getReview(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewDTO;
	}// getReview() [리뷰상세]

	// 리뷰 조회수 증가 서비스
	public void updateReadcount(HttpServletRequest request) {
		try {
			// 변수에 리퀘스트 파라미터값 저장(리뷰 글번호)
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			// 조회수 증가를 위한 updateReadcount(rev_num)메서드 호출
			new ReviewDAO().updateReadcount(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// updateReadcount [리뷰조회수] 증가

	// 리뷰 작성 관련 서비스
	public boolean insertReview(HttpServletRequest request) {
		System.out.println("ReviewService insertReview");

		boolean result = false;

		try {
			// 서블릿의 업로드 패스 지정
			String uploadPath = request.getRealPath("/upload");
			System.out.println(uploadPath);
			// 업로드 10 메가로 제한
			int maxSize = 10 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
					new DefaultFileRenamePolicy());

			// 세션에서 유저 번호 가져오기
			int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

//			int rev_num = 1; // (auto_increment)
//			int u_num = Integer.parseInt(multi.getParameter("u_num")); 유저 번호는 가급적이면 세션에서 가져 올 수 있게끔

			// 변수에 리퀘스트 파라미터값 저장(예약주문번호, 리뷰내용, 이미지경로(파일명), 별점)
			int res_num = Integer.parseInt(multi.getParameter("res_num"));
			String rev_content = multi.getParameter("rev_content");
			String rev_img_url = multi.getFilesystemName("rev_img_url");
			String rev_rating = multi.getParameter("rev_rating");

			// 리뷰 작성일은 현재 일자로 세팅
			Timestamp rev_date = new Timestamp(System.currentTimeMillis());

			// 리뷰 조회수 및 답글 관련 초기화 세팅
			int rev_count = 0; // 조회수 초기값=0
			int re_ref = 0; // 답글번호 초기값=0
			int re_lev = 0; // 답글깊이 초기값=0
			int re_seq = 0; // 답글개수 초기값=0

			// 답변 초기화 설정
			String re_content = null;
			Timestamp re_date = null;

			// 리뷰 DTO에 값들을 저장
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

			// 리뷰 작성을 위한 insertReview(reviewDTO)메서드 호출
			result = new ReviewDAO().insertReview(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}// insertReview() [리뷰작성]

	// 리뷰 수정 관련 서비스
	public boolean updateReview(HttpServletRequest request) {
		System.out.println("ReviewService updateReview()");

		boolean result = false;

		try {
			// 서블릿의 업로드 패스 지정
			String uploadPath = request.getRealPath("/upload");
			// 업로드 10 메가로 제한
			int maxSize = 10 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
					new DefaultFileRenamePolicy());

			// 변수에 리퀘스트 파라미터값 저장(리뷰 글번호, 리뷰내용, 이미지경로(파일명), 별점)
			int rev_num = Integer.parseInt(multi.getParameter("rev_num"));
			String rev_content = multi.getParameter("rev_content");
			String rev_img_url = multi.getFilesystemName("rev_img_url");
			String rev_rating = multi.getParameter("rev_rating"); // 잠시

			// 리뷰 DTO에 변경할 값들을 저장
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRev_content(rev_content);
			reviewDTO.setRev_img_url(rev_img_url);
			reviewDTO.setRev_rating(rev_rating); // 잠시

			// 리뷰 수정을 위한 updateReview(reviewDTO)메서드 호출
			result = new ReviewDAO().updateReview(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}// insertReview() [리뷰수정]

	// 리뷰 삭제 관련 서비스
	public boolean deleteReview(HttpServletRequest request) {
		System.out.println("ReviewService deleteReview()");

		boolean result = false;

		try {
			// 변수에 리퀘스트 파라미터값 저장(리뷰 글번호)
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			// 리뷰 삭제를 위한 deleteReview(rev_num)메서드 호출
			result = new ReviewDAO().deleteReview(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}// deleteReview() [리뷰삭제]

//  포인트 관리는 현재 관리자 페이지에서 되고 있으므로 사용되지 않는 것으로 보임
//	public void deleteReviewPoint(HttpServletRequest request) {
//		System.out.println("ReviewService deleteReviewPoint()");
//		try {
//			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
//			reviewDAO = new ReviewDAO();
//			reviewDAO.deleteReviewPoint(rev_num);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}// deleteReviewPoint() [리뷰삭제 + 포인트회수]

	// 관리자 답글 작성 관련 서비스
	public boolean writeRe(HttpServletRequest request) { // 여기도 multi로 해야하는지 확인할것
		System.out.println("ReviewService writeRe()");

		boolean result = false;
		try {
			// 변수에 리퀘스트 파라미터값 저장(리뷰 글번호, 답변내용)
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			String re_content = request.getParameter("re_content");
			// 답글 작성일은 현재 일자로 세팅
			Timestamp re_date = new Timestamp(System.currentTimeMillis());

			// 리뷰 DTO에 관련 값들을 저장
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRe_content(re_content);
			reviewDTO.setRe_date(re_date);
			// 답글 작성을 위한 writeRe(reviewDTO)메서드 호출
			result = new ReviewDAO().writeRe(reviewDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}// writeRe [답글작성 + 포인트추가]

	// 관리자 답글 수정 관련 서비스
	public boolean updateRe(HttpServletRequest request) { // 여기도 multi로 해야하는지 확인할것
		System.out.println("ReviewService updateRe()");

		boolean result = false;
		try {
			// 변수에 리퀘스트 파라미터값 저장(리뷰 글번호, 답글내용)
			int rev_num = Integer.parseInt(request.getParameter("rev_num"));
			String re_content = request.getParameter("re_content");
//			Timestamp re_date = new Timestamp(System.currentTimeMillis());

			// DTO에 변경할 리뷰의 내용과 번호를 저장
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setRev_num(rev_num);
			reviewDTO.setRe_content(re_content);
//			reviewDTO.setRe_date(re_date); // 수정해도 시간은 그대로

			// 답글 업데이트를 위한 updateRe(reviewDTO)메서드 호출
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

			// 답글 삭제후 초기화를 위한 deleteRe(reviewDTO) 호출
			result = new ReviewDAO().deleteRe(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}// deleteRe [답글삭제]

}// class

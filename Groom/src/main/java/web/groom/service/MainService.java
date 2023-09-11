package web.groom.service;

import java.util.List;

import web.groom.dao.MainDAO;
import web.groom.dto.ReviewDTO;

public class MainService {
	
	// 메인페이지에 표시할 리뷰리스트(3개)를 가져오는 메서드
	public List<ReviewDTO> getReviewList() {
		
		List<ReviewDTO> reviewList = null;
		
		try {
			// 리뷰리스트를 얻기위한 getReviewList() 메서드 호출
			reviewList = new MainDAO().getReviewList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}

} // end_of_MainService

package web.groom.service;

import java.util.List;

import web.groom.dao.MainDAO;
import web.groom.dto.ReviewDTO;

public class MainService {

	public List<ReviewDTO> getReviewList() {
		
		List<ReviewDTO> reviewList = null;
		try {
			MainDAO mainDAO = new MainDAO();
			reviewList = mainDAO.getReviewList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}

}

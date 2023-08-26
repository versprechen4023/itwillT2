package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.ReviewDTO;

public class ReviewDAO {

	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs =null;
	
	public void dbClose() {
		if(rs != null) {try {rs.close();} catch (SQLException e) {	}}			
		if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {	}}
		if(con != null) {try {con.close();} catch (SQLException e) {	}}
	}
	
	public List<ReviewDTO> getReviewList(String proName) {
		System.out.println("ReviewDAO getReviewList");
		List<ReviewDTO> reviewList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "select * from test01 where pro_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + proName + "%"); // 프로덕트 이름 설정
			rs = pstmt.executeQuery();
			reviewList = new ArrayList<>();
			while(rs.next()) {
				ReviewDTO reviewDTO = new ReviewDTO();
				reviewDTO.setRev_num(rs.getInt("rev_num"));
				reviewDTO.setPro_name(rs.getString("pro_name"));
				reviewDTO.setRev_img_url(rs.getString("rev_img_url"));
				reviewDTO.setEmp_grade(rs.getString("emp_grade"));
				reviewDTO.setEmp_name(rs.getString("emp_name"));
				reviewDTO.setS_location(rs.getString("s_location"));
				reviewDTO.setRev_rating(rs.getString("rev_rating"));
				reviewDTO.setU_name(rs.getString("u_name"));
				reviewDTO.setRev_date(rs.getString("rev_date"));
				reviewDTO.setU_count(rs.getString("u_count"));
				reviewDTO.setRev_content(rs.getString("rev_content"));
				reviewList.add(reviewDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return reviewList;
	}// getReviewList() [리뷰목록]

	public ReviewDTO getReview(int rev_num) {
		System.out.println("ReviewDAO getReview");
		ReviewDTO reviewDTO = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "select * from test01 where rev_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reviewDTO = new ReviewDTO();
				reviewDTO.setRev_num(rs.getInt("rev_num"));
				reviewDTO.setPro_name(rs.getString("pro_name"));
				reviewDTO.setEmp_grade(rs.getString("emp_grade"));
				reviewDTO.setEmp_name(rs.getString("emp_name"));
				reviewDTO.setS_location(rs.getString("s_location"));
				reviewDTO.setRev_rating(rs.getString("rev_rating"));
				reviewDTO.setU_name(rs.getString("u_name"));
				reviewDTO.setRev_date(rs.getString("rev_date"));
				reviewDTO.setRev_count(rs.getInt("rev_count"));
				reviewDTO.setU_count(rs.getString("u_count"));
				reviewDTO.setRev_content(rs.getString("rev_content"));
				reviewDTO.setRev_img_url(rs.getString("rev_img_url"));//
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return reviewDTO;
	}// getReview() [리뷰상세]

	public void updateReadcount(int rev_num) {
		System.out.println("ReviewDAO updateReadcount");
		try {
			con = new SQLConnection().getConnection();
			String sql = "update test01 set rev_count=rev_count+1 where rev_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}// updateReadcount() [리뷰조회수] 증가
	
	public void insertReview(ReviewDTO reviewDTO) {
		System.out.println("ReviewDAO insertReview()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "insert into testwrite(u_num, res_num, pro_id, s_num, rev_content,"
					+ "rev_img_url, rev_rating, rev_date, rev_count, re_ref, re_lev, re_seq)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reviewDTO.getU_num());
			pstmt.setInt(2, reviewDTO.getRes_num());
			pstmt.setInt(3, reviewDTO.getPro_id());
			pstmt.setInt(4, reviewDTO.getS_num());
			pstmt.setString(5, reviewDTO.getRev_content()); 
			pstmt.setString(6, reviewDTO.getRev_img_url()); //
			pstmt.setString(7, reviewDTO.getRev_rating()); 
			pstmt.setString(8, reviewDTO.getRev_date());
			pstmt.setInt(9, reviewDTO.getRev_count());
			pstmt.setInt(10, reviewDTO.getRe_ref());
			pstmt.setInt(11, reviewDTO.getRe_lev());
			pstmt.setInt(12, reviewDTO.getRe_seq());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// insertReview() [리뷰작성]
	
	
	
	
	
}// class

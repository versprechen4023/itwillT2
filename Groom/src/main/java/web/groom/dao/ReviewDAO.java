package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	public List<ReviewDTO> getReviewList() {
		System.out.println("ReviewDAO getReviewList");
		List<ReviewDTO> reviewList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "select * from test01";
			pstmt = con.prepareStatement(sql);
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
	
}

package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.OrderDTO;
import web.groom.dto.ReviewDTO;

public class MainDAO {
	Connection con = null;
	PreparedStatement pstmt = null;;
	ResultSet rs = null;
	ReviewDTO reviewDTO = null;
	
	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}

		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}

		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	
//	public List<ReviewDTO> getReviewList() {
//		
//		List<ReviewDTO> reviewList = null;
//		try {
//			con = new SQLConnection().getConnection();
//			String sql = "SELECT rev_num, pro_name, rev_img_url, emp_grade, emp_name, s_location, rev_rating, u_name, u_count, rev_content, rev_date\r\n"
//					+ "FROM test01\r\n"
//					+ "ORDER BY rev_rating desc\r\n"
//					+ "LIMIT 3";
//			
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			reviewList = new ArrayList<>();
//			
//			while(rs.next()) {
//				
//				reviewDTO = new ReviewDTO();
//				reviewDTO.setRev_num(rs.getInt("rev_num"));
//				reviewDTO.setPro_name(rs.getString("pro_name"));
//				reviewDTO.setRev_img_url(rs.getString("rev_img_url"));
//				reviewDTO.setEmp_grade(rs.getString("emp_grade"));
//				reviewDTO.setEmp_name(rs.getString("emp_name"));
//				reviewDTO.setS_location(rs.getString("s_location"));
//				reviewDTO.setRev_rating(rs.getString("rev_rating"));
//				reviewDTO.setU_name(rs.getString("u_name"));
//				reviewDTO.setU_count(rs.getString("u_count"));
//				reviewDTO.setRev_content(rs.getString("rev_content"));
//				reviewDTO.setRev_date(rs.getTimestamp("rev_date"));
//				
//				reviewList.add(reviewDTO);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			dbClose();
//		}
//		return reviewList;
//	}
	
public List<ReviewDTO> getReviewList() {
		
		List<ReviewDTO> reviewList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT a.rev_num, b.res_num, c.u_num, c.u_name, c.u_count, d.pro_name, e.emp_grade, e.emp_name,"
					+ "          f.s_location, a.rev_rating, a.rev_content, a.rev_img_url, a.rev_count, a.rev_date,"
					+ "          a.re_ref, a.re_lev, a.re_seq, a.re_content, a.re_date, c.u_point"
					+ "   FROM test_review a JOIN test_reservation b on a.res_num = b.res_num"
					+ "                      JOIN user2 c ON b.u_num = c.u_num"
					+ "                      JOIN product2 d ON b.pro_id2 = d.pro_id2"
					+ "                      JOIN employees e ON b.emp_num = e.emp_num"
					+ "                      JOIN store f ON b.s_num = f.s_num"
					+ "   ORDER BY a.rev_count desc"
					+ "   LIMIT 3";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			reviewList = new ArrayList<>();
			
			while(rs.next()) {
				ReviewDTO reviewDTO = new ReviewDTO();
				
				reviewDTO.setRev_num(rs.getInt("rev_num"));
				reviewDTO.setRes_num(rs.getInt("res_num"));
				reviewDTO.setU_num(rs.getInt("u_num"));
				reviewDTO.setU_name(rs.getString("u_name"));
				reviewDTO.setU_count(rs.getString("u_count"));
				reviewDTO.setPro_name(rs.getString("pro_name"));
				reviewDTO.setEmp_grade(rs.getString("emp_grade"));
				reviewDTO.setEmp_name(rs.getString("emp_name"));
				reviewDTO.setS_location(rs.getString("s_location"));
				reviewDTO.setRev_rating(rs.getString("rev_rating"));
				reviewDTO.setRev_content(rs.getString("rev_content"));
				reviewDTO.setRev_img_url(rs.getString("rev_img_url"));
				reviewDTO.setRev_count(rs.getInt("rev_count"));
				reviewDTO.setRev_date(rs.getTimestamp("rev_date"));
				reviewDTO.setRe_ref(rs.getInt("re_ref"));
				reviewDTO.setRe_lev(rs.getInt("re_lev"));
				reviewDTO.setRe_seq(rs.getInt("re_seq"));
				reviewDTO.setRe_content(rs.getString("re_content"));
				reviewDTO.setRe_date(rs.getTimestamp("re_date"));
				reviewDTO.setU_point(rs.getInt("u_point"));
				
				reviewList.add(reviewDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return reviewList;
	}

}

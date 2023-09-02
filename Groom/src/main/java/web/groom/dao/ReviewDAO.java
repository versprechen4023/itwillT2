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
	public List<ReviewDTO> getReviewListAll() {
		System.out.println("ReviewDAO getReviewList");
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
					+ "                      JOIN store f ON b.s_num = f.s_num;";
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
	}// getReviewList() [리뷰목록 전체]
	
	public List<ReviewDTO> getReviewList(String proName) {
		System.out.println("ReviewDAO getReviewList");
		List<ReviewDTO> reviewList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT a.rev_num, b.res_num, c.u_num, c.u_name, c.u_count, d.pro_name, e.emp_grade, e.emp_name,"
					+ "          f.s_location, a.rev_rating, a.rev_content, a.rev_img_url, a.rev_count, a.rev_date,"
					+ "          a.re_ref, a.re_lev, a.re_seq, a.re_content, a.re_date, c.u_point"
					+ "   FROM test_review a JOIN test_reservation b ON a.res_num = b.res_num"
					+ "                      JOIN user2 c ON b.u_num = c.u_num"
					+ "                      JOIN product2 d ON b.pro_id2 = d.pro_id2"
					+ "                      JOIN employees e ON b.emp_num = e.emp_num"
					+ "                      JOIN store f ON b.s_num = f.s_num"
					+ "   WHERE d.pro_name = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, proName); // 프로덕트 이름 설정
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
	}// getReviewList() [리뷰목록 선택]
	
	public List<ReviewDTO> getMyReviewList(int u_num) {
		System.out.println("ReviewDAO getReviewList");
		List<ReviewDTO> reviewList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT a.rev_num, b.res_num, c.u_num, c.u_name, c.u_count, d.pro_name, e.emp_grade, e.emp_name,"
					+ "          f.s_location, a.rev_rating, a.rev_content, a.rev_img_url, a.rev_count, a.rev_date,"
					+ "          a.re_ref, a.re_lev, a.re_seq, a.re_content, a.re_date, c.u_point"
					+ "   FROM test_review a JOIN test_reservation b ON a.res_num = b.res_num"
					+ "                      JOIN user2 c ON b.u_num = c.u_num"
					+ "                      JOIN product2 d ON b.pro_id2 = d.pro_id2"
					+ "                      JOIN employees e ON b.emp_num = e.emp_num"
					+ "                      JOIN store f ON b.s_num = f.s_num"
					+ "   WHERE b.u_num = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num); // 프로덕트 이름 설정
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
	}// getMyReviewList() [내 리뷰목록]
	

	public ReviewDTO getReview(int rev_num) {
		System.out.println("ReviewDAO getReview");
		ReviewDTO reviewDTO = null;
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
					+ "   WHERE a.rev_num = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reviewDTO = new ReviewDTO();
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
			String sql = "UPDATE test_review"
					+ "   SET rev_count = rev_count+1 WHERE rev_num = ?";
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
			String sql = "INSERT INTO test_review"
					+ "   (u_num, res_num, pro_id2, s_num, rev_content,"
					+ "    rev_img_url, rev_rating, rev_date, rev_count,"
					+ "    re_ref, re_lev, re_seq, re_content, re_date);"
					+ "   VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reviewDTO.getU_num());
			pstmt.setInt(2, reviewDTO.getRes_num());
			pstmt.setInt(3, reviewDTO.getPro_id2());
			pstmt.setInt(4, reviewDTO.getS_num());
			pstmt.setString(5, reviewDTO.getRev_content()); 
			pstmt.setString(6, reviewDTO.getRev_img_url());
			pstmt.setString(7, reviewDTO.getRev_rating()); 
			pstmt.setTimestamp(8, reviewDTO.getRev_date());
			pstmt.setInt(9, reviewDTO.getRev_count());
			pstmt.setInt(10, reviewDTO.getRe_ref());
			pstmt.setInt(11, reviewDTO.getRe_lev());
			pstmt.setInt(12, reviewDTO.getRe_seq());
			pstmt.setString(13, reviewDTO.getRe_content());
			pstmt.setTimestamp(14, reviewDTO.getRe_date());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// insertReview() [리뷰작성]
	
	
	public void updateReview(ReviewDTO reviewDTO) {
		System.out.println("ReviewDAO updateReview()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "UPDATE test_review"
					+ "   SET rev_content=?,rev_img_url=?"
					+ "   WHERE rev_num=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviewDTO.getRev_content()); 
			pstmt.setString(2, reviewDTO.getRev_img_url());
//			pstmt.setString(3, reviewDTO.getRev_rating()); // 잠시
			pstmt.setInt(3, reviewDTO.getRev_num());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// updateReview() [리뷰수정]
	
	
	public void deleteReview(int rev_num) {
		System.out.println("ReviewDAO deleteReview()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "delete from test_review where rev_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}// deleteReview() [리뷰삭제]
	
	
	public void deleteReviewPoint(int rev_num) {
		System.out.println("ReviewDAO deleteReviewPoint()");
		try {
			con = new SQLConnection().getConnection();
			String sql1 = "update user2"
					+ "   set u_point = u_point - 1000"
					+ "   where u_num = (select u_num from test_review"
					+ "                  where rev_num = ?);";
			
			String sql2 = "delete from test_review where rev_num=?";
			
			PreparedStatement pstmt1 = null;
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, rev_num);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2 = null;
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, rev_num);
			pstmt2.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}// deleteReviewPoint() [리뷰삭제 + 포인트회수]

	
	public void writeRe(ReviewDTO reviewDTO) {
		System.out.println("ReviewDAO writeRe()");
		try {
			con = new SQLConnection().getConnection();
			String sql1 = "update user2"
					+ "   set u_point = u_point + 1000"
					+ "   where u_num = (select u_num from test_review"
					+ "                  where rev_num = ?);";
			
			String sql2 = "update test_review"
					+ "   set re_content=?,re_date=?,re_ref=re_ref+1,re_lev=re_lev+1,re_seq=re_seq+1"
					+ "   where rev_num=?";
			PreparedStatement pstmt1 = null;
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, reviewDTO.getRev_num());
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2 = null;
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setString(1, reviewDTO.getRe_content());
			pstmt2.setTimestamp(2, reviewDTO.getRe_date());
			pstmt2.setInt(3, reviewDTO.getRev_num());
			pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}// writeRe() [답글작성 + 포인트추가]
	
	
	public void updateRe(ReviewDTO reviewDTO) {
		System.out.println("ReviewDAO updateRe()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "update test_review"
					+ "   set re_content=?"
					+ "   where rev_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, reviewDTO.getRe_content());
			pstmt.setInt(2, reviewDTO.getRev_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}// updateRe() [답글수정]

	
	public void deleteRe(ReviewDTO reviewDTO) {
		System.out.println("ReviewDAO writeRe()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "update test_review"
					+ "   set re_content=null,re_date=null,re_ref=re_ref-1,re_lev=re_lev-1,re_seq=re_seq-1"
					+ "   where rev_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reviewDTO.getRev_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}// deleteRe() [답글삭제]

	


	
	
	
	
	
}// class

package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.QnaDTO;

public class QnaDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	
	public void dbClose() {
		if(rs != null) {try {rs.close();} catch (SQLException e) {	}}		
		if(rs2 != null) {try {rs2.close();} catch (SQLException e) {	}}	
		if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {	}}
		if(pstmt2 != null) {try {pstmt2.close();} catch (SQLException e) {	}}
		if(con != null) {try {con.close();} catch (SQLException e) {	}}
	} // dbClose()
	
	
	
	public List<QnaDTO> getQnaList() {
		System.out.println("QnaDAO getQnaList()");
		List<QnaDTO> qnaList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql="select * from qna order by num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			qnaList = new ArrayList<>();
			while(rs.next()) {
				QnaDTO qnaDTO =new QnaDTO();
				qnaDTO.setQnanum(rs.getInt("qna_num"));
				qnaDTO.setTitle(rs.getString("qna_title"));
				qnaDTO.setId(rs.getString("qna_date"));
				qnaDTO.setDate(rs.getTimestamp("qna_date"));
				qnaDTO.setQreans(rs.getInt("qna_isanswered"));
				// 배열 한칸에 저장
				qnaList.add(qnaDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return qnaList;
	}//getQnaList(qna목록)
	
	

	public QnaDTO getQna(int qnanum) {
		System.out.println("QnaDAO getQna");
		QnaDTO qnaDTO = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "select * from qna where qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qnanum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qnaDTO = new QnaDTO();
				qnaDTO.setId(rs.getString("u_id"));
				qnaDTO.setTitle(rs.getString("qna_title"));
				qnaDTO.setContent(rs.getString("qna_content"));
				qnaDTO.setQnaimgurl(rs.getString("qna_img_url"));
				qnaDTO.setRecontent(rs.getString("re_content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return qnaDTO;
	}//getQna(qna상세)
	
	
	
	public void insertqnaBoard (QnaDTO qnaDTO) { // DB에서 처리하는 값들		
		System.out.println("QNA 인서트 요청");	
		try {			
			con = new SQLConnection().getConnection();
			
			// QNA 테이블에 있는 값들 
			String sql = "INSERT INTO qna(u_id , qna_title , qna_content, qna_date, qna_category, qna_img_url,"
					+ "re_ref, re_lev, re_seq, qna_isanswered, re_content, re_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);  
			pstmt.setString(1, qnaDTO.getId()); 
			pstmt.setString(2, qnaDTO.getTitle());
			pstmt.setString(3, qnaDTO.getContent());
			pstmt.setTimestamp(4, qnaDTO.getDate());
			pstmt.setString(5, qnaDTO.getCategory());
			pstmt.setString(6, qnaDTO.getQnaimgurl());
			pstmt.setInt(7, qnaDTO.getQreref());
			pstmt.setInt(8, qnaDTO.getQrelev());
			pstmt.setInt(9, qnaDTO.getQreseq());
			pstmt.setInt(10, qnaDTO.getQreans());
			pstmt.setString(11, qnaDTO.getRecontent());
			pstmt.setTimestamp(12, qnaDTO.getRedate());
			int result = pstmt.executeUpdate();
			if (result != 0 ) {
				System.out.println("저장 완료");
			} else {	
				System.out.println("저장 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}	
 } //insertqnaBoard(qna작성)
}

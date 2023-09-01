package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.PageDTO;
import web.groom.dto.QnaDTO;
import web.groom.dto.ReviewDTO;

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
	
	
	
	public List<QnaDTO> getQnaSearch(PageDTO pageDTO) {
		System.out.println("QnaDAO getQnaSearch()");
		List<QnaDTO> qna = null;
		try {
			con = new SQLConnection().getConnection();
			String sql="select * from qna where qna_title like ? order by qna_num desc limit ?, ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+pageDTO.getSearch()+"%");
			pstmt.setInt(2, pageDTO.getStartRow()-1);//시작행-1
			pstmt.setInt(3, pageDTO.getPageSize());//몇개
			rs = pstmt.executeQuery();
			qna = new ArrayList<>();
			while(rs.next()) {
				QnaDTO qnaDTO =new QnaDTO();
				qnaDTO.setQnanum(rs.getInt("qna_num"));
				qnaDTO.setTitle(rs.getString("qna_title"));
				qnaDTO.setId(rs.getString("u_id"));
				qnaDTO.setDate(rs.getTimestamp("qna_date"));
				qnaDTO.setQreans(rs.getInt("qna_isanswered"));
				// 배열 한칸에 저장
				qna.add(qnaDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return qna;
	}//getQnaList(qna목록)
	
	
	//검색
	public int getQnaCountSearch(PageDTO pageDTO) {
		int count = 0;
		try {
			//1,2 디비연결
			con = new SQLConnection().getConnection();
			//3 sql select count(*) from board
			String sql = "select count(*) from qna where qna_title like ?;";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+pageDTO.getSearch()+"%");
			//4 실행 => 결과저장
			rs = pstmt.executeQuery();
			//5 결과 행접근 => 열접근 => count변수 저장
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return count;
	}//getBoardCountSearch
	
	
	
	
	public List<QnaDTO> getQnaList(PageDTO pageDTO) {
		System.out.println("QnaDAO getQnaList()");
		List<QnaDTO> qnaList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql="select * from qna order by qna_num desc limit ?, ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageDTO.getStartRow()-1);//시작행-1
			pstmt.setInt(2, pageDTO.getPageSize());//몇개
			rs = pstmt.executeQuery();
			qnaList = new ArrayList<>();
			while(rs.next()) {
				QnaDTO qnaDTO =new QnaDTO();
				qnaDTO.setQnanum(rs.getInt("qna_num"));
				qnaDTO.setTitle(rs.getString("qna_title"));
				qnaDTO.setId(rs.getString("u_id"));
				qnaDTO.setDate(rs.getTimestamp("qna_date"));
				qnaDTO.setQreans(rs.getInt("qna_isanswered"));
				// 배열 한칸에 저장
				qnaList.add(qnaDTO);
			}
			System.out.println("qnaList"+qnaList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return qnaList;
	}//getQnaList(qna목록)
	
	
	
	public List<QnaDTO> getNoanswer(PageDTO pageDTO) {
		System.out.println("QnaDAO getNoanswer()");
		List<QnaDTO> qnaList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql="select * from qna where qna_isanswered=0 order by qna_num desc limit ?, ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageDTO.getStartRow()-1);//시작행-1
			pstmt.setInt(2, pageDTO.getPageSize());//몇개
			rs = pstmt.executeQuery();
			qnaList = new ArrayList<>();
			while(rs.next()) {
				QnaDTO qnaDTO =new QnaDTO();
				qnaDTO.setQnanum(rs.getInt("qna_num"));
				qnaDTO.setTitle(rs.getString("qna_title"));
				qnaDTO.setId(rs.getString("u_id"));
				qnaDTO.setDate(rs.getTimestamp("qna_date"));
				qnaDTO.setQreans(rs.getInt("qna_isanswered"));
				// 배열 한칸에 저장
				qnaList.add(qnaDTO);
			}
			System.out.println("qnaList"+qnaList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return qnaList;
	}//getNoanswer(답변X)
	
	
	public int getQnaCount() {
		int count = 0;
		try {
			//1,2 디비연결
			con = new SQLConnection().getConnection(); //
			//3 sql select count(*) from qna
			String sql = "select count(*) from qna;";
			pstmt=con.prepareStatement(sql);
			//4 실행 => 결과저장
			rs = pstmt.executeQuery();
			//5 결과 행접근 => 열접근 => count변수 저장
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return count;
	}//getQnaCount()
	
	

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

				qnaDTO.setQnanum(qnanum);
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
//			String sql = "INSERT INTO qna(u_id , qna_title , qna_content, qna_date, qna_category, qna_img_url,"
//					+ "re_ref, re_lev, re_seq, qna_isanswered, re_content, re_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "INSERT INTO qna(u_id , qna_title , qna_content, qna_date, qna_category, qna_img_url) VALUES(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);  
			pstmt.setString(1, qnaDTO.getId()); 
			pstmt.setString(2, qnaDTO.getTitle());
			pstmt.setString(3, qnaDTO.getContent());
			pstmt.setTimestamp(4, qnaDTO.getDate());
			pstmt.setString(5, qnaDTO.getCategory());
			pstmt.setString(6, qnaDTO.getQnaimgurl());
//			pstmt.setInt(7, qnaDTO.getQreref());
//			pstmt.setInt(8, qnaDTO.getQrelev());
//			pstmt.setInt(9, qnaDTO.getQreseq());
//			pstmt.setInt(10, qnaDTO.getQreans());
//			pstmt.setString(11, qnaDTO.getRecontent());
//			pstmt.setTimestamp(12, qnaDTO.getRedate());
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
	
	
	
	public void deleteQna(int qnanum) {
		System.out.println("QnaDAO deleteQna()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "delete from qna where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnanum);	
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}//deleteQna(qna삭제)
	
	
	
	public void updateQna(QnaDTO qnaDTO) {
		System.out.println("QnaDAO updateQna()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "update qna"
					+ "   set qna_title=?, qna_content=? "
					+ "   where qna_num=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qnaDTO.getId()); 
			pstmt.setString(2, qnaDTO.getTitle());
			pstmt.setString(3, qnaDTO.getCategory());
			pstmt.setString(4, qnaDTO.getContent());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//updateQna(qna수정)
	
	
	
	
	
	
	public void writeRe(QnaDTO qnaDTO) {
		System.out.println("QnaDAO writeRe()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "update qna"
					+ "   set re_content=?,re_date=? re_ref=?+1, re_lev=?+1 re_seq=?+1"
					+ "   where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, qnaDTO.getRecontent());
			pstmt.setTimestamp(2, qnaDTO.getRedate());
			pstmt.setInt(3, qnaDTO.getQreref());
			pstmt.setInt(4, qnaDTO.getQrelev());
			pstmt.setInt(5, qnaDTO.getQreseq());
			pstmt.setInt(6, qnaDTO.getQnanum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}//writeRe(답글작성)
	
	
	
	public void updateRe(QnaDTO qnaDTO) {
		System.out.println("QnaDAO updateRe()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "update qna"
					+ "   set re_content=?"
					+ "   where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, qnaDTO.getRecontent());
			pstmt.setInt(2, qnaDTO.getQnanum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}//updateRe(답글수정)
	
	
	
	public void deleteRe(QnaDTO qnaDTO) {
		System.out.println("QnaDAO writeRe()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "update qna"
					+ "   set re_content=null,re_date=null re_ref=0, re_lev=0 re_seq=0"
					+ "   where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnaDTO.getQnanum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}//deleteRe(답글삭제)
	
}//

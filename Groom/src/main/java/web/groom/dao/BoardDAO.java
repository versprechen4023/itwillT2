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

import web.groom.dto.BoardDTO;
import web.groom.dto.PageDTO;


	public class BoardDAO {
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		
		// 1,2단계 디비연결
		public Connection getConnection() throws Exception {
			Context init = new InitialContext();
			DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/c1d2304t2");
			con=ds.getConnection();
			return con;
		}
		
		
		// 기억장소 해제
		public void dbClose() {

			if(rs != null) {try {rs.close();} catch (SQLException e) {	}}			
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {	}}
			if(con != null) {try {con.close();} catch (SQLException e) {	}}
		}
		
		
		public List<BoardDTO> getBoardList(PageDTO pageDTO) {
			System.out.println("BoardDAO getBoardList()");
			List<BoardDTO> boardList = null;
			try {
				
				//1,2 디비연결
				con = getConnection();
				
				// sql 
				String sql="select * from notice order by n_num desc limit ?, ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pageDTO.getStartRow()-1);//시작행-1
				pstmt.setInt(2, pageDTO.getPageSize());//몇개
				rs = pstmt.executeQuery();
				
				// boardList 객체생성
				boardList = new ArrayList<>();
				
				// BoardDTO객체생성 => set호출
				while(rs.next()) {
					BoardDTO boardDTO =new BoardDTO();
					boardDTO.setNum(rs.getInt("n_num"));
					boardDTO.setSubject(rs.getString("n_title"));
					boardDTO.setContent(rs.getString("n_content"));
					boardDTO.setDate(rs.getTimestamp("n_date"));
//					boardDTO.setNoticeNum(rs.getInt("noticeNum"));
//					boardDTO.setNoticeSubject(rs.getString("noticeSubject"));
//					boardDTO.setNoticeContent(rs.getString("noticeContent"));
//					boardDTO.setNoticeReadcount(rs.getInt("noticeReadcount"));
//					boardDTO.setNoticeIssuedate(rs.getTimestamp("noticeIssuedate"));
					// 배열 한칸에 저장
					boardList.add(boardDTO);
				}
				System.out.println(boardList);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return boardList;
		}//getBoardList()
		
		
		// 게시물 번호
		public int getMaxNum() {
			System.out.println("BoardDAO getMaxNum()");
			int num = 0;
			try {
				// db연결
				con=getConnection();

				// sql 실행
				String sql = "select max(num) from board;";
				pstmt=con.prepareStatement(sql);
				rs =pstmt.executeQuery();
				
				// if 다음행 열데이터 num 저장
				if(rs.next()) {
					num = rs.getInt("max(num)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return num;
		}//getMaxNum()
		
		
		
		public void insertBoard(BoardDTO boardDTO) {
			System.out.println("BoardDAO insertBoard()");
			try {
				// db연결
				con=getConnection();
				
				// sql 실행
				String sql = "insert into notice(NoticeNum,NoticeSubject,NoticeContent,NoticeReadcount,NoticeIssuedate) values(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
//				pstmt.setInt(1, boardDTO.getNoticeNum());      
//				pstmt.setString(2, boardDTO.getNoticeSubject());
//				pstmt.setString(3, boardDTO.getNoticeContent());
//				pstmt.setInt(4, boardDTO.getNoticeReadcount());
//				pstmt.setTimestamp(5, boardDTO.getNoticeIssuedate());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
		}//insertBoard()
		
		
		
		// 게시물 총개수
		public int getBoardCount() {
			int count = 0;
			try {
				// 
				con=getConnection();
				
				// sql 실행
				String sql = "select count(*) from board;";
				pstmt=con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				// 행 => 열 => count변수 저장
				if(rs.next()) {
					count = rs.getInt("count(*)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return count;
		}//getBoardCount()
		
		
		
		// 해당 번호의 게시물 열람
		public BoardDTO getBoard(int num) {
			BoardDTO boardDTO = null;
			try {
				// db연결
				con = getConnection();
				
				// sql 실행
				String sql="select * from board where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					// boardDTO 객체생성 
					boardDTO = new BoardDTO();
					
					// set메서드 호출 데이터 저장
//					boardDTO.setNoticeNum(rs.getInt("noticeNum"));
//					boardDTO.setNoticeSubject(rs.getString("noticeSubject"));
//					boardDTO.setNoticeContent(rs.getString("noticeContent"));
//					boardDTO.setNoticeReadcount(rs.getInt("noticeReadcount"));
//					boardDTO.setNoticeIssuedate(rs.getTimestamp("noticeIssuedate"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return boardDTO;
		}//getBoard
		
		
		
		
		
		
		

}//boardDAO

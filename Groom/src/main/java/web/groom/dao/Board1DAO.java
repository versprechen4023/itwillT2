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


import web.groom.dto.Board1DTO;
import web.groom.dto.PageDTO;


	public class Board1DAO {
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		//기억장소 해제 메서드()
		public void dbClose() {
			//  => con, pstmt, rs 기억장소 해제
			if(rs != null) {try {rs.close();} catch (SQLException e) {	}}			
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {	}}
			if(con != null) {try {con.close();} catch (SQLException e) {	}}
		}
		
		//========================================================================
		
		public List<Board1DTO> getNotice(PageDTO pageDTO) {//
			System.out.println("BoardDAO getNotice()");
			List<Board1DTO> notice = null;
			
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				String sql="select * from notice order by n_num desc limit ?, ?";
//				String sql = "select * from notice";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pageDTO.getStartRow()-1);//시작행-1
				pstmt.setInt(2, pageDTO.getPageSize());//몇개
				//4 실행 => 결과 저장
				rs = pstmt.executeQuery();
				// boardList 객체생성
				notice = new ArrayList<>();
				//5 결과 행접근 => BoardDTO객체생성 => set호출(열접근저장)
				// => 배열 한칸에 저장
				while(rs.next()) {
					Board1DTO boardDTO =new Board1DTO();
					boardDTO.setN_num(rs.getInt("n_num"));
					boardDTO.setN_title(rs.getString("n_title"));
					boardDTO.setN_content(rs.getString("n_content"));
					boardDTO.setN_date(rs.getTimestamp("n_date"));
					// => 배열 한칸에 저장
					notice.add(boardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return notice;			
		}//getNotice()//
		
		//검색
		public List<Board1DTO> getNoticeSearch(PageDTO pageDTO) {//
			System.out.println("BoardDAO getNotice()");
			List<Board1DTO> notice = null;
			
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
//				String sql="select * from notice order by n_num desc limit ?, ?";
//				String sql = "select * from notice";
				String sql="select * from notice where n_title like ? order by n_num desc limit ?, ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+pageDTO.getSearch()+"%");
				pstmt.setInt(2, pageDTO.getStartRow()-1);//시작행-1
				pstmt.setInt(3, pageDTO.getPageSize());//몇개
				//4 실행 => 결과 저장
				rs = pstmt.executeQuery();
				// boardList 객체생성
				notice = new ArrayList<>();
				//5 결과 행접근 => BoardDTO객체생성 => set호출(열접근저장)
				// => 배열 한칸에 저장
				while(rs.next()) {
					Board1DTO boardDTO =new Board1DTO();
					boardDTO.setN_num(rs.getInt("n_num"));
					boardDTO.setN_title(rs.getString("n_title"));
					boardDTO.setN_content(rs.getString("n_content"));
					boardDTO.setN_date(rs.getTimestamp("n_date"));
					// => 배열 한칸에 저장
					notice.add(boardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return notice;			
		}//getNoticeSearch()
		
		public int getMaxNum() {
			System.out.println("Board1DAO getMaxNum()");
				int n_num = 0;
			try {
				//1단계 JDBC 프로그램 가져오기
				//2단계 DB연결
				con = new SQLConnection().getConnection();
				
				//3단계 문자열을 -> sql구문으로 변경
				String sql = "select max(n_num) from notice ";
				pstmt = con.prepareStatement(sql);
				
				//4단계 sql구문 실행 => 실행결과 ResultSet 내장객체에 저장
				rs = pstmt.executeQuery();
				
				//5단계 : if 다음행 => 열데이터 가져와서 num 저장
				if(rs.next()) {
					n_num = rs.getInt("max(n_num)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return n_num;
		}
		
		public void insertNotice(Board1DTO boardDTO) {
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql insert
				String sql = "insert into notice(n_num,n_title,n_content,n_date,n_img_url) values(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, boardDTO.getN_num());     
				pstmt.setString(2, boardDTO.getN_title()); 
				pstmt.setString(3, boardDTO.getN_content());
				pstmt.setTimestamp(4, boardDTO.getN_date());			
				//파일추가
				pstmt.setString(5, boardDTO.getN_img_url());
								//4 실행 
				pstmt.executeUpdate();			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}	
		}//insertNotice//
		
		public Board1DTO getBoard(int n_num) {
			Board1DTO boardDTO = null;
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql select * from board where num = ?
				String sql="select * from notice where n_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, n_num);
				//4 실행 => 결과 저장
				rs = pstmt.executeQuery();
				//5 결과 행접근 => boardDTO 객체생성 
				//        => set메서드 호출 => 열접근 데이터 저장
				if(rs.next()) {
					boardDTO = new Board1DTO();
					boardDTO.setN_num(rs.getInt("n_num"));
					boardDTO.setN_title(rs.getString("n_title"));
					boardDTO.setN_content(rs.getString("n_content"));
					//첨부파일
					boardDTO.setN_img_url(rs.getString("n_img_url"));
				}
				System.out.println("BoardDTO"+boardDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return boardDTO;
		}//getBoard//
		
		public int getBoardCount() {
			int count = 0;
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql select count(*) from board
				String sql = "select count(*) from notice;"; //faq-> notice로 수정
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
		}//getBoardCount
		
		//검색
		public int getBoardCountSearch(PageDTO pageDTO) {
			int count = 0;
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql select count(*) from board
				String sql = "select count(*) from notice where n_title like ?;";
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
		
		
		
		public void updateNotice(Board1DTO boardDTO) {
			System.out.println("Board1DTO boardDTO");
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql
				String sql = "update notice set n_title=?, n_content=?, n_img_url=? where n_num=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, boardDTO.getN_title());
				pstmt.setString(2, boardDTO.getN_content());
				pstmt.setString(3, boardDTO.getN_img_url());
				pstmt.setInt(4, boardDTO.getN_num());
				
				//4실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
		}
		public void deleteNotice(int n_num) {
			try {
				System.out.println("Board1DAO deleteNotice");
				con = new SQLConnection().getConnection();
				String sql = "delete from notice where n_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, n_num);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			
		}
		
		
		
//FAQ
//===================================================================
		
		public List<Board1DTO> getFaq(PageDTO pageDTO) {//
			System.out.println("BoardDAO getFaq()");
			List<Board1DTO> faq = null;
			
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				String sql="select * from faq order by faq_num desc limit ?, ?";
//				String sql = "select * from faq";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pageDTO.getStartRow()-1);//시작행-1
				pstmt.setInt(2, pageDTO.getPageSize());//몇개
				//4 실행 => 결과 저장
				rs = pstmt.executeQuery();
				// boardList 객체생성
				faq = new ArrayList<>();
				//5 결과 행접근 => BoardDTO객체생성 => set호출(열접근저장)
				// => 배열 한칸에 저장
				while(rs.next()) {
					Board1DTO boardDTO =new Board1DTO();
					boardDTO.setFaq_num(rs.getInt("faq_num"));
					boardDTO.setFaq_title(rs.getString("faq_title"));
					boardDTO.setFaq_content(rs.getString("faq_content"));
					boardDTO.setFaq_date(rs.getTimestamp("faq_date"));
					// => 배열 한칸에 저장
					faq.add(boardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return faq;			
		}//getFaq()//
		
		//검색
		public List<Board1DTO> getFaqSearch(PageDTO pageDTO) {//
			System.out.println("BoardDAO getFaq()");
			List<Board1DTO> faq = null;
			
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
//				String sql="select * from faq order by faq_num desc limit ?, ?";
//				String sql = "select * from faq";
				String sql="select * from faq where faq_title like ? order by faq_num desc limit ?, ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+pageDTO.getSearch()+"%");
				pstmt.setInt(2, pageDTO.getStartRow()-1);//시작행-1
				pstmt.setInt(3, pageDTO.getPageSize());//몇개
				//4 실행 => 결과 저장
				rs = pstmt.executeQuery();
				// boardList 객체생성
				faq = new ArrayList<>();
				//5 결과 행접근 => BoardDTO객체생성 => set호출(열접근저장)
				// => 배열 한칸에 저장
				while(rs.next()) {
					Board1DTO boardDTO =new Board1DTO();
					boardDTO.setFaq_num(rs.getInt("faq_num"));
					boardDTO.setFaq_title(rs.getString("faq_title"));
					boardDTO.setFaq_content(rs.getString("faq_content"));
					boardDTO.setFaq_date(rs.getTimestamp("faq_date"));
					// => 배열 한칸에 저장
					faq.add(boardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return faq;			
		}//getFaqSearch()
		
		public int getMaxNum2() {
			System.out.println("Board1DAO getMaxNum()");
				int faq_num = 0;
			try {
				//1단계 JDBC 프로그램 가져오기
				//2단계 DB연결
				con = new SQLConnection().getConnection();
				
				//3단계 문자열을 -> sql구문으로 변경
				String sql = "select max(faq_num) from faq ";
				pstmt = con.prepareStatement(sql);
				
				//4단계 sql구문 실행 => 실행결과 ResultSet 내장객체에 저장
				rs = pstmt.executeQuery();
				
				//5단계 : if 다음행 => 열데이터 가져와서 num 저장
				if(rs.next()) {
					faq_num = rs.getInt("max(faq_num)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return faq_num;
		}
		
		public void insertFaq(Board1DTO boardDTO) {
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql insert
				String sql = "insert into faq(faq_num,faq_title,faq_content,faq_date,faq_img_url) values(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, boardDTO.getFaq_num());     
				pstmt.setString(2, boardDTO.getFaq_title()); 
				pstmt.setString(3, boardDTO.getFaq_content());
				pstmt.setTimestamp(4, boardDTO.getFaq_date());			
				//파일추가
				pstmt.setString(5, boardDTO.getFaq_img_url());
								//4 실행 
				pstmt.executeUpdate();			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}	
		}//insertFaq//
		
		public Board1DTO getBoard2(int faq_num) {
			Board1DTO boardDTO = null;
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql select * from board where num = ?
				String sql="select * from faq where faq_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, faq_num);
				//4 실행 => 결과 저장
				rs = pstmt.executeQuery();
				//5 결과 행접근 => boardDTO 객체생성 
				//        => set메서드 호출 => 열접근 데이터 저장
				if(rs.next()) {
					boardDTO = new Board1DTO();
					boardDTO.setFaq_num(rs.getInt("faq_num"));
					boardDTO.setFaq_title(rs.getString("faq_title"));
					boardDTO.setFaq_content(rs.getString("faq_content"));
					//첨부파일
					boardDTO.setFaq_img_url(rs.getString("faq_img_url"));
				}
				System.out.println("BoardDTO"+boardDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return boardDTO;
		}//getBoard//
		
		public int getBoardCount2() {
			int count = 0;
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql select count(*) from board
				String sql = "select count(*) from faq;";
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
		}//getBoardCount
		
		//검색
		public int getBoardCountSearch2(PageDTO pageDTO) {
			int count = 0;
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql select count(*) from board
				String sql = "select count(*) from faq where faq_title like ?;";
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
		
		
		
		public void updateFaq(Board1DTO boardDTO) {
			System.out.println("Board1DTO boardDTO");
			try {
				//1,2 디비연결
				con = new SQLConnection().getConnection();
				//3 sql
				String sql = "update faq set faq_title=?, faq_content=?, faq_img_url=? where faq_num=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, boardDTO.getFaq_title());
				pstmt.setString(2, boardDTO.getFaq_content());
				pstmt.setString(3, boardDTO.getFaq_img_url());
				pstmt.setInt(4, boardDTO.getFaq_num());
				
				//4실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
		}
		public void deleteFaq(int faq_num) {
			try {
				System.out.println("Board1DAO deleteFaq");
				con = new SQLConnection().getConnection();
				String sql = "delete from faq where faq_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, faq_num);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			
		}
		
		
}//boardDAO

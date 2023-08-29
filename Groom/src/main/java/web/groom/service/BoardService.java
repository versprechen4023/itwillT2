package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.BoardDAO;
import web.groom.dao.QnaDAO;
import web.groom.dao.ReviewDAO;
import web.groom.dto.BoardDTO;
import web.groom.dto.PageDTO;
import web.groom.dto.QnaDTO;
import web.groom.dto.ReviewDTO;
import web.groom.dto.NoticeDTO;

public class BoardService {
	
	BoardDAO boardDAO = null; 
	QnaDAO qnaDAO = null;
	QnaDTO qnaDTO = null;
	
	
	// [QnA] ==========================================================
	
	
	public List<QnaDTO> getQnaList() {
		System.out.println("BoardService getQnaList()");
		List<QnaDTO> qnaList = null;
		try {
			qnaDAO = new QnaDAO();
			qnaList = qnaDAO.getQnaList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaList; 
	}// getQnaList(qna목록)
	
	
	
	public QnaDTO getQna(HttpServletRequest request) {
		System.out.println("BoardService getQna()");
		QnaDTO qnaDTO = null;
		try {
			int qnanum = Integer.parseInt(request.getParameter("qna_num"));
			qnaDAO = new QnaDAO();
			qnaDTO = qnaDAO.getQna(qnanum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaDTO;
	}//getQna(qna상세)
	
	
	
	public QnaDTO insertqnaBoard(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
			
			// qnawrite에서 받는 값
			String u_id = request.getParameter("u_id");
			String qna_title = request.getParameter("qna_title");
			String qna_content = request.getParameter("qna_content");
			String qna_img_url = request.getParameter("qna_img_url");	
			Timestamp qna_date = new Timestamp(System.currentTimeMillis());
			 
			// QnaDTO 객체생성
			QnaDTO qnaDTO = new QnaDTO();
			// DB에 넘겨줄값들을 set메서드 호출해서 파라미터값 저장
			qnaDTO.setId(u_id);
			qnaDTO.setTitle(qna_title);
			qnaDTO.setContent(qna_content);
			qnaDTO.setDate(qna_date);
			qnaDTO.setQnaimgurl(qna_img_url);
			
			// QnaDAO 객체생성
			qnaDAO = new QnaDAO();
			
			// DAO생성해서 얘로 DTO값을 넘겨줌 
			QnaDAO qnaDAO = new QnaDAO();
			qnaDAO.insertqnaBoard(qnaDTO);
			
			// 값 받아오는지 확인하기 위함 지워도 상관없음 
			System.out.println("boardService");
			System.out.println("u_id=" + u_id);
			System.out.println("u_title=" + qna_title);
			System.out.println("u_content=" + qna_content);
			System.out.println("u_date=" + qna_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaDTO;
		
	}//insertqnaBoard(qna작성)
	
	
	
	
	
	
	// [Notice & FAQ] ==========================================================
	
	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardService getBoardList()");
		List<BoardDTO> boardList = null;
		try {
			
			// 시작하는 행
			int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;  	
			
			// 끝나는 행
			int endRow = startRow+pageDTO.getPageSize()-1;
			
			// pageDTO 저장
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			// BoardDAO 객체생성
			boardDAO = new BoardDAO();
			
			// boardList = getBoardList() 메서드 호출
			boardList = boardDAO.getBoardList(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}//getBoardList()

	
	public void insertBoard(HttpServletRequest request) {
		try {
            // 한글처리
			request.setCharacterEncoding("utf-8");

			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");

			int readcount = 0; //조회수
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			// BoardDAO 객체생성
			boardDAO = new BoardDAO();
			int num = boardDAO.getMaxNum() + 1;
			
			// BoardDTO 객체생성
			BoardDTO boardDTO = new BoardDTO();
			
			// 파라미터값 저장
			boardDTO.setNum(num);
			boardDTO.setName(name);
			boardDTO.setSubject(subject);
			boardDTO.setContent(content);
			boardDTO.setReadcount(readcount);
			boardDTO.setDate(date);
			
			// insertBoard(boardDTO) 호출
			boardDAO.insertBoard(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//insertBoard()

	
	
	public int getBoardCount() {

		int count=0;
		try {
			// BoardDAO 객체생성
			boardDAO = new BoardDAO();
			
			// count = getBoardCount() 호출
			count = boardDAO.getBoardCount();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCount

	
	
	public BoardDTO getBoard(HttpServletRequest request) {
		
		BoardDTO boardDTO = null;
		try {
			// 한글처리
			request.setCharacterEncoding("utf-8");
			
			// 파라미터값 int num 저장
			int num = Integer.parseInt(request.getParameter("num"));
			
			// BoardDAO 객체생성
			boardDAO = new BoardDAO();
			
			// boardDTO = getBoard(num) 메서드 호출
			boardDTO = boardDAO.getBoard(num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDTO;
	}//getBoard
	
	public void insertNotice(HttpServletRequest request) {
		
		 try {
	            request.setCharacterEncoding("utf-8");

	            // qnawrite에서 받는 값
	            String n_title = request.getParameter("n_title");
	            String n_content = request.getParameter("n_content");
	            String n_img_url = request.getParameter("n_img_url");
	            Timestamp n_date = new Timestamp(System.currentTimeMillis());

	            // QnaDTO 객체생성
	            NoticeDTO noticeDTO = new NoticeDTO();
	            // DB에 넘겨줄값들을 set메서드 호출해서 파라미터값 저장
	            noticeDTO.setN_title(n_title);
	            noticeDTO.setN_content(n_content);
	            noticeDTO.setN_date(n_date);
	            noticeDTO.setN_img_url(n_img_url);

	            // QnaDAO 객체생성
	            qnaDAO = new QnaDAO();

	            // DAO생성해서 얘로 DTO값을 넘겨줌 
	            QnaDAO qnaDAO = new QnaDAO();
	            qnaDAO.insertqnaBoard(qnaDTO);

	            // 값 받아오는지 확인하기 위함 지워도 상관없음 
	            System.out.println("boardService 값 넘어오는지 체크 ");
	            System.out.println("n_title=" + n_title);
	            System.out.println("n_content=" + n_content);
	            System.out.println("n_date=" + n_date);
	            System.out.println("n_date=" + n_img_url);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    

	    }//insertnotice

}//클래스

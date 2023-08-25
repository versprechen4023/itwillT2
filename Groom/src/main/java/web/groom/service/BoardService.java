package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.BoardDAO;
import web.groom.dto.BoardDTO;
import web.groom.dto.PageDTO;

public class BoardService {
	
	
	BoardDAO boardDAO = null; 
	
	
	// 페이징 처리
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

}//클래스

package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.BoardDTO;
import web.groom.dto.PageDTO;
import web.groom.service.BoardService;

@WebServlet("*.bo") //.bo 게시판관련페이지 어노테이션 매핑 선언
public class BoardController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 //페이지이동
		 if (sPath.equals("/something.bo")) {
	            
	     }
		 
		 if (sPath.equals("/notice.bo")) {
			 	// 한페이지에서 보여지는 글개수 설정
				int pageSize=10;
				// 페이지번호 
				String pageNum=request.getParameter("pageNum");
				// 페이지번호가 없으면 1페이지 설정
				if(pageNum == null) {
					pageNum = "1";
				}
				// 페이지 번호를 => 정수형 변경
				int currentPage = Integer.parseInt(pageNum);
				
				PageDTO pageDTO = new PageDTO();
				pageDTO.setPageSize(pageSize);
				pageDTO.setPageNum(pageNum);
				pageDTO.setCurrentPage(currentPage);
				
				// BoardService 객체생성
				BoardService boardService = new BoardService();
	// List<BoardDTO> boardList = getBoardList(); 메서드 호출
				List<BoardDTO> boardList=boardService.getBoardList(pageDTO);
			 request.setAttribute("boardList", boardList);
				
	            webForward(request, response, "board", "notice");

	     }
		 
		 if (sPath.equals("/qna.bo")) {
	            webForward(request, response, "board", "qna");

	     }
		 
		 if (sPath.equals("/faq.bo")) {
	            webForward(request, response, "board", "faq");

	     }
		 
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+pageName+".jsp").forward(request, response);
	}

}

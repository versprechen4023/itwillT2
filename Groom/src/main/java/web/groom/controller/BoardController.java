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
	
	BoardService boardService = new BoardService();
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 //페이지이동
		 if (sPath.equals("/something.bo")) {
	            
	     }
		 
		 if (sPath.equals("/noticeWrite.bo")) {
	            webForward(request, response, "board", "noticeWrite");

	     }
		 
		 if (sPath.equals("/notice.bo")) {
				PageDTO pageDTO = new PageDTO();	
	 			BoardService boardService = new BoardService();
				List<BoardDTO> boardList=boardService.getBoardList(pageDTO);
			    request.setAttribute("boardList", boardList);
	            webForward(request, response, "board", "notice");
	     }
		 
		 if (sPath.equals("/qna.bo")) {
	            webForward(request, response, "board", "qna");

	     } // qna리스트로 이동 
		 
		 if (sPath.equals("/qnacontent.bo")) {
	            webForward(request, response, "board", "qnacontent");

	     } // qna리스트로 이동 
		 
		 if (sPath.equals("/qnawrite.bo")) {
	            webForward(request, response, "board", "qnawrite");

	     } // qna 쓰는 페이지로 이동 
		 
		 if (sPath.equals("/qnawritePro.bo")) {
//	            boardService = new BoardService();
//	            boardService.insertqnaBoard(request); // qnainsertboard() 요청
//				// list.bo 주소 변경 되면서 이동
//				response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
	     }
		 
		 if (sPath.equals("/qnaupdate.bo")) {
				boardService = new BoardService();
				// BoardDTO boardDTO = getBoard(request) 메서드 호출
				// BoardDTO boardDTO = boardService.getBoard(request);
				// request에 "boardDTO",boardDTO 담아서
				// request.setAttribute("boardDTO", boardDTO);
				// webForward(request, response, "board", "qnaupdate"); 오류날까봐 일단 주석 처리 해놓음
	     }
		 
		 if (sPath.equals("/faq.bo")) {
	            webForward(request, response, "board", "faq");

	     }
		 
		 if (sPath.equals("/faqcontent.bo")) {
	            webForward(request, response, "board", "faqcontent");

	     }
		 
		 if (sPath.equals("/faqWrite.bo")) {
	            webForward(request, response, "board", "faqWrite");

	     }
		 
		 if (sPath.equals("/faqupdate.bo")) {
	            webForward(request, response, "board", "faqmodify");

	     }
		 
		 if (sPath.equals("/faqupdatePro.bo")) {
			 boardService = new BoardService();
	         // boardService.updatefaq(request);  //faqupdate() 요청
			 response.sendRedirect("notice.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	

	     }
		 
		 if (sPath.equals("/noticecontent.bo")) {
	            webForward(request, response, "board", "noticecontent");

	     } // 공지사항 내용 보기 
		 
		 if (sPath.equals("/noticeWrite.bo")) {
			 webForward(request, response, "board", "noticeWrite");
			 
		
	     } // 공지사항 글쓰기 
		 
		 if (sPath.equals("/noticeWritePro.bo")) {
			 boardService = new BoardService();
	         boardService.insertNotice(request); // qnainsertboard() 요청
				// list.bo 주소 변경 되면서 이동
				response.sendRedirect("notice.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
			 
		
	     } // 글 쓰기 처리 후 리스트로 이동 
		 
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

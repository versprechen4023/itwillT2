package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import web.groom.dto.Board1DTO;
import web.groom.dto.PageDTO;
import web.groom.dto.QnaDTO;
import web.groom.service.Board1Service;
import web.groom.service.QnaService;

@WebServlet("*.bo") //.bo 게시판관련페이지 어노테이션 매핑 선언
public class Board1Controller extends HttpServlet {
	
	QnaService qnaService = null;
	Board1Service boardService = null;
	RequestDispatcher dispatcher =null;

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("BoardController doProcess()");
		//가상주소 뽑아오기
		String sPath=request.getServletPath();
		System.out.println("뽑아온 가상주소:"+sPath);
		
		
		//=======================================================================
		if(sPath.equals("/notice.bo")) {
			System.out.println("뽑은 가상주소 비교 : /notice.bo");
			
			
			//한페이지에서 보여지는 글개수
			int pageSize=10;
			//페이지번호
			String pageNum=request.getParameter("pageNum");
			//페이지번호가 없으면 1페이지 실행
			if(pageNum == null) {
				pageNum = "1";
			}
			//페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);
			
			//pageDTO에 담음			
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);
			
			// BoardService 객체생성
			boardService = new Board1Service();
			
			// List<BoardDTO> notice = getNotice(); 메서드 호출
			List<Board1DTO> notice = boardService.getNotice(pageDTO);
			
			// 게시판 전체 글 개수 구하기 
			int count = boardService.getBoardCount();
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 10;
			// 시작하는 페이지 번호
			int startPage=(currentPage-1)/pageBlock*pageBlock+1;
			// 끝나는 페이지 번호
			int endPage=startPage+pageBlock-1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize==0?0:1);
			if(endPage > pageCount) {
				endPage = pageCount;
			}
						
			//pageDTO 저장
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);
			
			// request에 "board",board 저장
			request.setAttribute("notice", notice);
			request.setAttribute("pageDTO", pageDTO);
			
			// 주소변경없이 이동
			webForward(request, response, "board", "notice");
		}//notice.bo//
		
		if(sPath.equals("/noticeWrite.bo")) {
			// 주소변경없이 이동 center/write.jsp
//			dispatcher 
//		    = request.getRequestDispatcher("board/noticeWrite.jsp");
//		dispatcher.forward(request, response);
			webForward(request, response, "board", "noticeWrite");
		}//noticeWrite.bo//
		
		if(sPath.equals("/noticeWritePro.bo")) {
			System.out.println("뽑은 가상주소 비교 : /writePro.bo");
			// BoardService 객체생성
			boardService = new Board1Service();
			// 리턴할형없음 insertNotice(request) 메서드 호출
			boardService.insertNotice(request);
			// write.bo 주소 변경 되면서 이동
			response.sendRedirect("notice.bo");
		}//noticeWritePro//
		
		if(sPath.equals("/noticeContent.bo")){
			System.out.println("뽑은 가상주소 비교 : /noticeContent.bo");
			// BoardService 객체생성
			boardService = new Board1Service();
			// BoardDTO boardDTO = getBoard(request) 메서드 호출
			Board1DTO boardDTO = boardService.getNotice(request);
			System.out.println("잘넘어옴");
			
			//엔터키(\r\n)를 => <br>택로 변경하는 작업
			String n_content = boardDTO.getN_content();
			n_content = n_content.replace("\r\n", "<br>");
			boardDTO.setN_content(n_content);
			
			// request에 "boardDTO",boardDTO 담아서
			request.setAttribute("boardDTO", boardDTO);
			//  주소변경없이 이동
			webForward(request, response, "board", "noticeContent");
		}//noticeContent.bo
		
		if(sPath.equals("/noticeUpdate.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeUpdate.bo");
			// BoardService 객체생성
			boardService = new Board1Service();
			// BoardDTO boardDTO = getBoard(request) 메서드 호출
			Board1DTO boardDTO = boardService.getNotice(request);
			// request에 "boardDTO",boardDTO 담아서
			request.setAttribute("boardDTO", boardDTO);
			//  주소변경없이 이동
			webForward(request, response, "board", "noticeUpdate");
		}
		if(sPath.equals("/noticeUpdatePro.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeUpdatePro.bo");
			//BoardService객체생성
			boardService = new Board1Service();
			//updateBoard(request) 메서드 호출
			boardService.updateNotice(request);
			//글목록 list.bo 주소변경되면서 이동
			response.sendRedirect("notice.bo");
		}
		
		if(sPath.equals("/noticeDelete.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeDelete.bo");
			//BoardService객체생성
			boardService = new Board1Service();
			//deleteBoard(request) 메서드 호출
			boardService.deleteNotice(request);
			//글목록 list.bo 주소 변경되면서 이동
			response.sendRedirect("notice.bo");
		}
		
		//검색
		if(sPath.equals("/noticeSearch.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeSearch.bo");
			
			//request 한글처리
			request.setCharacterEncoding("UTF-8");
			//request 검색어 뽑아오기//
			String search = request.getParameter("search"); // notice.jsp의 검색창부분 name="search"
			System.out.println("search"+search);
			
			//한페이지에서 보여지는 글개수
			int pageSize=10;
			//페이지번호
			String pageNum=request.getParameter("pageNum");
			//페이지번호가 없으면 1페이지 실행
			if(pageNum == null) {
				pageNum = "1";
			}
			//페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);
			
			//pageDTO에 담음			
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);
			pageDTO.setSearch(search);
			
			// BoardService 객체생성
			boardService = new Board1Service();
			
			// List<BoardDTO> notice = getNotice(); 메서드 호출
			List<Board1DTO> notice = boardService.getNoticeSearch(pageDTO);
			
			// 게시판 전체 글 개수 구하기 
			int count = boardService.getBoardCountSearch(pageDTO);
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 10;
			// 시작하는 페이지 번호
			int startPage=(currentPage-1)/pageBlock*pageBlock+1;
			// 끝나는 페이지 번호
			int endPage=startPage+pageBlock-1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize==0?0:1);
			if(endPage > pageCount) {
				endPage = pageCount;
			}
						
			//pageDTO 저장
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);
			
			// request에 "board",board 저장
			request.setAttribute("notice", notice);
			request.setAttribute("pageDTO", pageDTO);
			
			// 주소변경없이 이동
			webForward(request, response, "board", "noticeSearch");
		}//notice.bo
		
		
		
		
		//FAQ
		//========================================================================= 
		if(sPath.equals("/faq.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faq.bo");
			
			
			//한페이지에서 보여지는 글개수
			int pageSize=10;
			//페이지번호
			String pageNum=request.getParameter("pageNum");
			//페이지번호가 없으면 1페이지 실행
			if(pageNum == null) {
				pageNum = "1";
			}
			//페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);
			
			//pageDTO에 담음			
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);
			
			// BoardService 객체생성
			boardService = new Board1Service();
			
			// List<BoardDTO> faq = getFaq(); 메서드 호출
			List<Board1DTO> faq = boardService.getFaq(pageDTO);
			
			// 게시판 전체 글 개수 구하기 
			int count = boardService.getBoardCount2();
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 10;
			// 시작하는 페이지 번호
			int startPage=(currentPage-1)/pageBlock*pageBlock+1;
			// 끝나는 페이지 번호
			int endPage=startPage+pageBlock-1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize==0?0:1);
			if(endPage > pageCount) {
				endPage = pageCount;
			}
						
			//pageDTO 저장
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);
			
			// request에 "board",board 저장
			request.setAttribute("faq", faq);
			request.setAttribute("pageDTO", pageDTO);
			
			// 주소변경없이 이동
			webForward(request, response, "board", "faq");
		}//faq.bo//
		
		if(sPath.equals("/faqWrite.bo")) {
			// 주소변경없이 이동 center/write.jsp
//			dispatcher 
//		    = request.getRequestDispatcher("board/faqWrite.jsp");
//		dispatcher.forward(request, response);
			webForward(request, response, "board", "faqWrite");
		}//faqWrite.bo//
		
		if(sPath.equals("/faqWritePro.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqwritePro.bo");
			// BoardService 객체생성
			boardService = new Board1Service();
			// 리턴할형없음 insertFaq(request) 메서드 호출
			boardService.insertFaq(request);
			// write.bo 주소 변경 되면서 이동
			response.sendRedirect("faq.bo");
		}//faqWritePro//
		
		if(sPath.equals("/faqContent.bo")){
			System.out.println("뽑은 가상주소 비교 : /faqContent.bo");
			// BoardService 객체생성
			boardService = new Board1Service();
			// BoardDTO boardDTO = getBoard(request) 메서드 호출
			Board1DTO boardDTO = boardService.getFaq(request);
			System.out.println("잘넘어옴");
			
			//엔터키(\r\n)를 => <br>택로 변경하는 작업
			String faq_content = boardDTO.getFaq_content();
			faq_content = faq_content.replace("\r\n", "<br>");
			boardDTO.setFaq_content(faq_content);
			
			// request에 "boardDTO",boardDTO 담아서
			request.setAttribute("boardDTO", boardDTO);
			//  주소변경없이 이동
			webForward(request, response, "board", "faqContent");
		}//faqContent.bo
		
		if(sPath.equals("/faqUpdate.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqUpdate.bo");
			// BoardService 객체생성
			boardService = new Board1Service();
			// BoardDTO boardDTO = getBoard(request) 메서드 호출
			Board1DTO boardDTO = boardService.getFaq(request);
			// request에 "boardDTO",boardDTO 담아서
			request.setAttribute("boardDTO", boardDTO);
			//  주소변경없이 이동
			webForward(request, response, "board", "faqUpdate");
		}
		if(sPath.equals("/faqUpdatePro.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqUpdatePro.bo");
			//BoardService객체생성
			boardService = new Board1Service();
			//updateBoard(request) 메서드 호출
			boardService.updateFaq(request);
			//글목록 list.bo 주소변경되면서 이동
			response.sendRedirect("faq.bo");
		}
		
		if(sPath.equals("/faqDelete.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqDelete.bo");
			//BoardService객체생성
			boardService = new Board1Service();
			//deleteBoard(request) 메서드 호출
			boardService.deleteFaq(request);
			//글목록 list.bo 주소 변경되면서 이동
			response.sendRedirect("faq.bo");
		}
		
		//검색
		if(sPath.equals("/faqSearch.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqSearch.bo");
			
			//request 한글처리
			request.setCharacterEncoding("UTF-8");
			//request 검색어 뽑아오기//
			String search = request.getParameter("search"); // faq.jsp의 검색창부분 name="search"
			System.out.println("search"+search);
			
			//한페이지에서 보여지는 글개수
			int pageSize=10;
			//페이지번호
			String pageNum=request.getParameter("pageNum");
			//페이지번호가 없으면 1페이지 실행
			if(pageNum == null) {
				pageNum = "1";
			}
			//페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);
			
			//pageDTO에 담음			
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);
			pageDTO.setSearch(search);
			
			// BoardService 객체생성
			boardService = new Board1Service();
			
			// List<BoardDTO> faq = getFaq(); 메서드 호출
			List<Board1DTO> faq = boardService.getFaqSearch(pageDTO);
			
			// 게시판 전체 글 개수 구하기 
			int count = boardService.getBoardCountSearch2(pageDTO);
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 10;
			// 시작하는 페이지 번호
			int startPage=(currentPage-1)/pageBlock*pageBlock+1;
			// 끝나는 페이지 번호
			int endPage=startPage+pageBlock-1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize==0?0:1);
			if(endPage > pageCount) {
				endPage = pageCount;
			}
						
			//pageDTO 저장
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);
			
			// request에 "board",board 저장
			request.setAttribute("faq", faq);
			request.setAttribute("pageDTO", pageDTO);
			
			// 주소변경없이 이동
			webForward(request, response, "board", "faqSearch");
		}//faq.bo
		
		
		
		
		
		//Board 2 라인
		//====================================================================

		 if (sPath.equals("/qna.bo")) {
			 
			 System.out.println("뽑은 가상주소 비교 : /qna.bo");
				
				//한페이지에서 보여지는 글개수
				int pageSize=10;
				//페이지번호
				String pageNum=request.getParameter("pageNum");
				//페이지번호가 없으면 1페이지 실행
				if(pageNum == null) {
					pageNum = "1";
				}
				//페이지 번호를 => 정수형으로 변경
				int currentPage = Integer.parseInt(pageNum);
							
				PageDTO pageDTO = new PageDTO();
				pageDTO.setPageSize(pageSize);
				pageDTO.setPageNum(pageNum);
				pageDTO.setCurrentPage(currentPage);
				
				qnaService = new QnaService();
				List<QnaDTO> qna = qnaService.getQnaList(pageDTO);
				
				
				// 게시판 전체 글 개수 구하기 
				
							int count = qnaService.getQnaCount();
							// 한화면에 보여줄 페이지개수 설정
							int pageBlock = 10;
							int startPage=(currentPage-1)/pageBlock*pageBlock+1;
							int endPage=startPage+pageBlock-1;
							int pageCount = count / pageSize + (count % pageSize==0?0:1);
							if(endPage > pageCount) {
								endPage = pageCount;
							}
							
							//pageDTO 저장
							pageDTO.setCount(count);
							pageDTO.setPageBlock(pageBlock);
							pageDTO.setStartPage(startPage);
							pageDTO.setEndPage(endPage);
							pageDTO.setPageCount(pageCount);
							request.setAttribute("pageDTO", pageDTO);
				            request.setAttribute("qna", qna);
	                        webForward(request, response, "board", "qna");
	     }//qna목록

		 
		 if (sPath.equals("/qnacontent.bo")) { 
			 System.out.println("qnacontent.bo");
			 qnaService = new QnaService();
			 QnaDTO qnaDTO = qnaService.getQna(request);
			 request.setAttribute("qnaDTO", qnaDTO);
	         webForward(request, response, "board", "qnacontent");
	     }//qna상세
		 
		 
		 if (sPath.equals("/qnaNoanswer.bo")) { 
			 System.out.println("qnaNoanswer.bo");
			 qnaService = new QnaService();
			 QnaDTO qnaDTO = qnaService.getQna(request);
			 request.setAttribute("qnaDTO", qnaDTO);
	         webForward(request, response, "board", "qnaNoanswer");
	     }//qna 답글X
		 
		 
		 if (sPath.equals("/qnaWrite.bo")) {
			 System.out.println("qnaWrite.bo");
	         webForward(request, response, "board", "qnaWrite");
	     }//qna작성
		 
		 
		 if (sPath.equals("/qnaWritePro.bo")) {
			 System.out.println("qnaWritePro.bo");
			 request.setCharacterEncoding("utf-8");
			 qnaService = new QnaService();
			 qnaService.insertQna(request);
		     response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
	     }//qna작성 후 등록
		 
		 
		 if(sPath.equals("/qnaDelete.bo")) {
			 qnaService = new QnaService();
			 qnaService.deleteQna(request);
		     response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
		 }//qna삭제
		 
		 
		 if(sPath.equals("/qnaUpdate.bo")) {
			 System.out.println("qnaUpdate.bo");
			 qnaService = new QnaService();
			 QnaDTO qnaDTO = qnaService.getQna(request);
			 request.setAttribute("qnaDTO", qnaDTO);
			 webForward(request, response, "board", "qnaUpdate");
		 }//qna수정
		 
		 
		 if(sPath.equals("/qnaUpdatePro.bo")) {
			 System.out.println("qnaUpdatePro.bo");
			 qnaService = new QnaService();
			 qnaService.updateQna(request);
			 response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
		 }//qna수정 후 등록
		 
		 
		 
		 
		 // 답글 ============================================================
		 
		 if(sPath.equals("/reWrite.bo")) {
			 System.out.println("reWrite.bo");
			 qnaService = new QnaService();
			 QnaDTO qnaDTO = qnaService.getQna(request);
			 request.setAttribute("qnaDTO", qnaDTO);
		     webForward(request, response, "board", "reWrite");
		 }//qna답글작성
		 
		 
		 if(sPath.equals("/reWritePro.bo")) {
			 System.out.println("reWritePro.bo");
			 qnaService = new QnaService();
			 qnaService.writeRe(request);
			 response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
		 }//qna답글작성 후 등록
		 
		 
		 if(sPath.equals("/reUpdate.bo")) {
			 System.out.println("reUpdate.re");
			 qnaService = new QnaService();
			 QnaDTO qnaDTO = qnaService.getQna(request);
			 request.setAttribute("qnaDTO", qnaDTO);
			 webForward(request, response, "board", "reUpdate");
		 }//qna답글수정
		 
		 
		 if(sPath.equals("/reUpdatePro.bo")) {
			 System.out.println("reUpdatePro.bo");
			 qnaService = new QnaService();
			 qnaService.updateRe(request);
			 response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
		 }//qna답글수정 후 등록
		 
		 
		 if(sPath.equals("/reDelete.bo")) {
			 System.out.println("reDelete.re");
			 qnaService = new QnaService();
			 qnaService.deleteRe(request);
			 response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동 	
		 }//qna답글삭제
		
		
		
		
		
		
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Board1Controller doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Board1Controller doPost()");
		doProcess(request, response);
	}
	
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+pageName+".jsp").forward(request, response);
	}

}


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
import web.groom.javascript.JSForward;
import web.groom.service.Board1Service;
import web.groom.service.QnaService;

@WebServlet("*.bo") // .bo 게시판관련페이지 어노테이션 매핑 선언
public class Board1Controller extends HttpServlet {

	QnaService qnaService = null;
	Board1Service boardService = null;
	RequestDispatcher dispatcher = null;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("BoardController doProcess()");
		// 가상주소 뽑아오기
		String sPath = request.getServletPath();
		System.out.println("뽑아온 가상주소:" + sPath);

		// =======================================================================

		// 공지사항 리스트 페이지 이동 board/notice.jsp
		if (sPath.equals("/notice.bo")) {
			System.out.println("뽑은 가상주소 비교 : /notice.bo");

			// 한페이지에서 보여지는 글개수
			int pageSize = 10;
			// 페이지번호
			String pageNum = request.getParameter("pageNum");
			// 페이지번호가 없으면 1페이지 실행
			if (pageNum == null) {
				pageNum = "1";
			}
			// 페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);

			// pageDTO에 담음
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
			int pageBlock = 3;
			// 시작하는 페이지 번호
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			// 끝나는 페이지 번호
			int endPage = startPage + pageBlock - 1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			// pageDTO 저장
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
		} // notice.bo//

		// 공지사항 작성 페이지 이동 board/noticeWrite.jsp
		if (sPath.equals("/noticeWrite.bo")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				webForward(request, response, "board", "noticeWrite");
			}
		} // noticeWrite.bo//

		// 공지사항 작성 관련 매핑
		if (sPath.equals("/noticeWritePro.bo")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 공지사항 작성을 위한 서비스에 리퀘스트 전달후 결과값 반환
				boolean result = new Board1Service().insertNotice(request);
				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "공지사항 작성이 완료되었습니다", "notice.bo");
				} else {
					JSForward.locationHref(response, "공지사항 작성에 문제가 발생했습니다", "notice.bo");
				}
			}
		} // noticeWritePro//

		// 공지사항 내용 페이지 이동 board/noticeContent.jsp
		if (sPath.equals("/noticeContent.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeContent.bo");

			// BoardDTO boardDTO = getBoard(request) 메서드 호출
			Board1DTO boardDTO = new Board1Service().getNotice(request);

			// 엔터키(\r\n)를 => <br>택로 변경하는 작업
			String n_content = boardDTO.getN_content();
			n_content = n_content.replace("\r\n", "<br>");
			boardDTO.setN_content(n_content);

			// request에 "boardDTO",boardDTO 담아서
			request.setAttribute("boardDTO", boardDTO);
			// 주소변경없이 이동
			webForward(request, response, "board", "noticeContent");
		} // noticeContent.bo

		// 공지사항 수정 페이지 이동 board/noticeUpdate.jsp
		if (sPath.equals("/noticeUpdate.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeUpdate.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// BoardDTO boardDTO = getBoard(request) 메서드 호출
				Board1DTO boardDTO = new Board1Service().getNotice(request);
				// request에 "boardDTO",boardDTO 담아서
				request.setAttribute("boardDTO", boardDTO);
				// 주소변경없이 이동
				webForward(request, response, "board", "noticeUpdate");
			}
		} // noticeUpdate.bo

		// 공지사항 수정 관련 매핑
		if (sPath.equals("/noticeUpdatePro.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeUpdatePro.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 공지사항 수정을 위한 서비스에 리퀘스트 전달후 결과값 반환
				boolean result = new Board1Service().updateNotice(request);

				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "공지사항 수정이 완료되었습니다", "notice.bo");
				} else {
					JSForward.locationHref(response, "공지사항 수정에 문제가 발생했습니다", "notice.bo");
				}
			}
		} // noticeUpdatePro.bo

		// 공지사항 삭제 관련 매핑
		if (sPath.equals("/noticeDelete.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeDelete.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 공지사항 삭제를 위한 서비스에 리퀘스트 전달후 결과값 반환
				boolean result = new Board1Service().deleteNotice(request);

				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "공지사항 삭제가 완료되었습니다", "notice.bo");
				} else {
					JSForward.locationHref(response, "공지사항 삭제에 문제가 발생했습니다", "notice.bo");
				}
			}
		} // noticeDelete.bo

		// 공지사항 검색 관련 매핑
		if (sPath.equals("/noticeSearch.bo")) {
			System.out.println("뽑은 가상주소 비교 : /noticeSearch.bo");

			// request 한글처리
			request.setCharacterEncoding("UTF-8");
			// request 검색어 뽑아오기//
			String search = request.getParameter("search"); // notice.jsp의 검색창부분 name="search"
			System.out.println("search" + search);

			// 한페이지에서 보여지는 글개수
			int pageSize = 10;
			// 페이지번호
			String pageNum = request.getParameter("pageNum");
			// 페이지번호가 없으면 1페이지 실행
			if (pageNum == null) {
				pageNum = "1";
			}
			// 페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);

			// pageDTO에 담음
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
			int pageBlock = 3;
			// 시작하는 페이지 번호
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			// 끝나는 페이지 번호
			int endPage = startPage + pageBlock - 1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			// pageDTO 저장
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
		} // notice.bo

		// FAQ
		// =========================================================================

		// 공지사항 리스트 페이지 이동 board/faq.jsp
		if (sPath.equals("/faq.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faq.bo");

			// 한페이지에서 보여지는 글개수
			int pageSize = 10;
			// 페이지번호
			String pageNum = request.getParameter("pageNum");
			// 페이지번호가 없으면 1페이지 실행
			if (pageNum == null) {
				pageNum = "1";
			}
			// 페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);

			// pageDTO에 담음
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
			int pageBlock = 3;
			// 시작하는 페이지 번호
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			// 끝나는 페이지 번호
			int endPage = startPage + pageBlock - 1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			// pageDTO 저장
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
		} // faq.bo//

		// 공지사항 리스트 페이지 이동 board/faqWrite.jsp
		if (sPath.equals("/faqWrite.bo")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				webForward(request, response, "board", "faqWrite");
			}
		} // faqWrite.bo//

		// FAQ 작성 관련 매핑
		if (sPath.equals("/faqWritePro.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqwritePro.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// FAQ 수정을 위한 서비스에 리퀘스트 전달후 결과값 반환
				boolean result = new Board1Service().insertFaq(request);

				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "FAQ 작성이 완료되었습니다", "faq.bo");
				} else {
					JSForward.locationHref(response, "FAQ 작성에 문제가 발생했습니다", "faq.bo");
				}

			}
		} // faqWritePro//

		// FAQ 내용 페이지 이동 board/faqContent.jsp
		if (sPath.equals("/faqContent.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqContent.bo");

			// BoardDTO boardDTO = getBoard(request) 메서드 호출
			Board1DTO boardDTO = new Board1Service().getFaq(request);

			// 엔터키(\r\n)를 => <br>택로 변경하는 작업
			String faq_content = boardDTO.getFaq_content();
			faq_content = faq_content.replace("\r\n", "<br>");
			boardDTO.setFaq_content(faq_content);

			// request에 "boardDTO",boardDTO 담아서
			request.setAttribute("boardDTO", boardDTO);
			// 주소변경없이 이동
			webForward(request, response, "board", "faqContent");
		} // faqContent.bo

		// FAQ 수정 페이지 이동 board/faqUpdate.jsp
		if (sPath.equals("/faqUpdate.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqUpdate.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// BoardDTO boardDTO = getBoard(request) 메서드 호출
				Board1DTO boardDTO = new Board1Service().getFaq(request);
				// request에 "boardDTO",boardDTO 담아서
				request.setAttribute("boardDTO", boardDTO);
				// 주소변경없이 이동
				webForward(request, response, "board", "faqUpdate");
			}
		} // faqUpdate.bo

		// FAQ 수정 관련 매핑
		if (sPath.equals("/faqUpdatePro.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqUpdatePro.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// FAQ 수정을 위한 서비스에 리퀘스트 전달후 결과값 반환
				boolean result = new Board1Service().updateFaq(request);

				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "FAQ 수정이 완료되었습니다", "faq.bo");
				} else {
					JSForward.locationHref(response, "FAQ 수정에 문제가 발생했습니다", "faq.bo");
				}
			}

		} // faqUpdatePro.bo

		// FAQ 삭제 관련 매핑
		if (sPath.equals("/faqDelete.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqDelete.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// FAQ 삭제를 위한 서비스에 리퀘스트 전달후 결과값 반환
				boolean result = new Board1Service().deleteFaq(request);
				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "FAQ 삭제가 완료되었습니다", "faq.bo");
				} else {
					JSForward.locationHref(response, "FAQ 삭제에 문제가 발생했습니다", "faq.bo");
				}
			}
		} // faqDelete.bo

		// FAQ 검색 관련 매핑
		if (sPath.equals("/faqSearch.bo")) {
			System.out.println("뽑은 가상주소 비교 : /faqSearch.bo");

			// request 한글처리
			request.setCharacterEncoding("UTF-8");
			// request 검색어 뽑아오기//
			String search = request.getParameter("search"); // faq.jsp의 검색창부분 name="search"
			System.out.println("search" + search);

			// 한페이지에서 보여지는 글개수
			int pageSize = 10;
			// 페이지번호
			String pageNum = request.getParameter("pageNum");
			// 페이지번호가 없으면 1페이지 실행
			if (pageNum == null) {
				pageNum = "1";
			}
			// 페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);

			// pageDTO에 담음
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);
			pageDTO.setSearch(search);

			// BoardService 객체생성
			boardService = new Board1Service();

			// List<Board1DTO> faq = getFaqSearch(); 메서드 호출
			List<Board1DTO> faq = boardService.getFaqSearch(pageDTO);

			// 게시판 전체 글 개수 구하기
			int count = boardService.getBoardCountSearch2(pageDTO);
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 3;
			// 시작하는 페이지 번호
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			// 끝나는 페이지 번호
			int endPage = startPage + pageBlock - 1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			// pageDTO 저장
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
		} // faqSearch.bo

		// Board 2 라인
		// ====================================================================

		// QNA 검색 관련 매핑
		if (sPath.equals("/qnaSearch.bo")) {
			System.out.println("뽑은 가상주소 비교 : /qnaSearch.bo");

			// request 한글처리
			request.setCharacterEncoding("UTF-8");
			// request 검색어 뽑아오기//
			String search = request.getParameter("search"); // notice.jsp의 검색창부분 name="search"
			System.out.println("search" + search);

			// 한페이지에서 보여지는 글개수
			int pageSize = 10;
			// 페이지번호
			String pageNum = request.getParameter("pageNum");
			// 페이지번호가 없으면 1페이지 실행
			if (pageNum == null) {
				pageNum = "1";
			}
			// 페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);

			// pageDTO에 담음
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);
			pageDTO.setSearch(search);

			// QnaService 객체생성
			qnaService = new QnaService();

			// List<QnaDTO> qna = getQnaSearch(); 메서드 호출
			List<QnaDTO> qna = qnaService.getQnaSearch(pageDTO);

			// 게시판 전체 글 개수 구하기
			int count = qnaService.getQnaCountSearch(pageDTO);
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 3;
			// 시작하는 페이지 번호
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			// 끝나는 페이지 번호
			int endPage = startPage + pageBlock - 1;
			// 전체페이지 구하기
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			// pageDTO 저장
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);

			// request에 "board",board 저장
			request.setAttribute("qna", qna);
			request.setAttribute("pageDTO", pageDTO);

			// 주소변경없이 이동
			webForward(request, response, "board", "qnaSearch");
		} // qnaSearch.bo

		// QNA 리스트 페이지 이동 board/qna.jsp
		if (sPath.equals("/qna.bo")) {

			System.out.println("뽑은 가상주소 비교 : /qna.bo");

			// 한페이지에서 보여지는 글개수
			int pageSize = 10;
			// 페이지번호
			String pageNum = request.getParameter("pageNum");
			// 페이지번호가 없으면 1페이지 실행
			if (pageNum == null) {
				pageNum = "1";
			}
			// 페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);

			// pageDTO에 담음
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);

			// QNA서비스 객체생성
			qnaService = new QnaService();
			// List<QnaDTO> qna = getQnaList(); 메서드 호출
			List<QnaDTO> qna = qnaService.getQnaList(pageDTO);

			// 게시판 전체 글 개수 구하기
			int count = qnaService.getQnaCount();
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 3;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			// pageDTO 저장
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);
			request.setAttribute("pageDTO", pageDTO);
			request.setAttribute("qna", qna);
			webForward(request, response, "board", "qna");
		} // qna목록

		// QNA 내용 페이지 이동 board/noticeContent.jsp
		if (sPath.equals("/qnaContent.bo")) {
			System.out.println("qnaContent.bo");

			// QnaDTO qnaDTO = getQna(request) 메서드 호출
			QnaDTO qnaDTO = new QnaService().getQna(request);

			// 엔터키(\r\n)를 => <br>택로 변경하는 작업
			String content = qnaDTO.getContent();
			content = content.replace("\r\n", "<br>");
			qnaDTO.setContent(content);

			// request에 "qnaDTO",qnaDTO 담아서
			request.setAttribute("qnaDTO", qnaDTO);
			// 주소변경없이 이동
			webForward(request, response, "board", "qnaContent");
		} // qna상세

		// QNA 내용 페이지 이동(답변안된거) board/noticeNoanswer.jsp
		if (sPath.equals("/qnaNoanswer.bo")) {
			System.out.println("qnaNoanswer.bo");

			// 한페이지에서 보여지는 글개수
			int pageSize = 10;
			// 페이지번호
			String pageNum = request.getParameter("pageNum");
			// 페이지번호가 없으면 1페이지 실행
			if (pageNum == null) {
				pageNum = "1";
			}
			// 페이지 번호를 => 정수형으로 변경
			int currentPage = Integer.parseInt(pageNum);

			// pageDTO에 담음
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);

			// QnaService 객체생성
			qnaService = new QnaService();
			// List<QnaDTO> qna = getNoanswer(); 메서드 호출
			List<QnaDTO> qna = qnaService.getNoanswer(pageDTO);

			// 게시판 전체 글 개수 구하기

			int count = qnaService.getCountNoanswer(pageDTO);
			// 한화면에 보여줄 페이지개수 설정
			int pageBlock = 3;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			// pageDTO 저장
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);
			request.setAttribute("pageDTO", pageDTO);

			request.setAttribute("qna", qna);
			webForward(request, response, "board", "qnaNoanswer");
		} // qna 답글X

		// QNA 작성 페이지 이동 board/qnaWrite.jsp
		if (sPath.equals("/qnaWrite.bo")) {
			System.out.println("qnaWrite.bo");
			webForward(request, response, "board", "qnaWrite");
		} // qna작성

		// QNA 작성 관련 매핑
		if (sPath.equals("/qnaWritePro.bo")) {
			System.out.println("qnaWritePro.bo");
			// QNA 작성을 위한 서비스에 리퀘스트 전달후 결과값 반환
			boolean result = new QnaService().insertQna(request);
			// 정상처리 되었을 경우 출력
			if (result) {
				JSForward.locationHref(response, "QNA 작성이 완료되었습니다", "qna.bo");
			} else {
				JSForward.locationHref(response, "QNA 작성에 문제가 발생했습니다", "qna.bo");
			}
		} // qna작성 후 등록

		// QNA 삭제 관련 매핑
		if (sPath.equals("/qnaDelete.bo")) {
			System.out.println("qnaDelete.bo");
			// QNA 삭제를 위한 서비스에 리퀘스트 전달후 결과값 반환
			boolean result = new QnaService().deleteQna(request);
			// 정상처리 되었을 경우 출력
			if (result) {
				JSForward.locationHref(response, "QNA 삭제가 완료되었습니다", "qna.bo");
			} else {
				JSForward.locationHref(response, "QNA 삭제에 문제가 발생했습니다", "qna.bo");
			}

		} // qna삭제

		// QNA 수정 페이지 이동 board/qnaUpdate.jsp
		if (sPath.equals("/qnaUpdate.bo")) {
			System.out.println("qnaUpdate.bo");
			// QnaDTO qnaDTO = getQna(request) 메서드 호출
			QnaDTO qnaDTO = new QnaService().getQna(request);
			// request에 "qnaDTO", qnaDTO 담아서
			request.setAttribute("qnaDTO", qnaDTO);
			// 주소변경없이 이동
			webForward(request, response, "board", "qnaUpdate");
		} // qna수정

		// QNA 수정 관련 매핑
		if (sPath.equals("/qnaUpdatePro.bo")) {
			System.out.println("qnaUpdatePro.bo");
			qnaService = new QnaService();
			// QNA 수정을 위한 서비스에 리퀘스트 전달후 결과값 반환
			boolean result = qnaService.updateQna(request);
			// 정상처리 되었을 경우 출력
			if (result) {
				JSForward.locationHref(response, "QNA 수정이 완료되었습니다", "qna.bo");
			} else {
				JSForward.locationHref(response, "QNA 수정에 문제가 발생했습니다", "qna.bo");
			}
		} // qna수정 후 등록

		// 답글 ============================================================

		// QNA 답변 수정(작성)관련 페이지 이동 board/qnaRE.jsp
		if (sPath.equals("/qnaRe.bo")) {
			System.out.println("qnaRe.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// QnaDTO qnaDTO = getQna(request) 메서드 호출
				QnaDTO qnaDTO = new QnaService().getQna(request);
				// request에 "boardDTO",boardDTO 담아서
				request.setAttribute("qnaDTO", qnaDTO);
				// 주소변경없이 이동
				webForward(request, response, "board", "qnaRe");
			}

		} // qna답글 수정(작성)하는 페이지

		// QNA 작성 관련 매핑
		if (sPath.equals("/qnaReWritePro.bo")) {
			System.out.println("qnaReWritePro.bo");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// QNA 답변 작성(수정)을 위한 서비스에 리퀘스트 전달후 결과값 반환
				boolean result = new QnaService().writeRe(request);
				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "QNA 답변 작성이 완료되었습니다", "qna.bo");
				} else {
					JSForward.locationHref(response, "QNA 답변 작성에 문제가 발생했습니다", "qna.bo");
				}
			}
		} // qna답변 작성 후 등록

		// 사용되지 않는 것으로 보임

//		if (sPath.equals("/reWrite.bo")) {
//			System.out.println("reWrite.bo");
//			qnaService = new QnaService();
//			QnaDTO qnaDTO = qnaService.getQna(request);
//			request.setAttribute("qnaDTO", qnaDTO);
//			webForward(request, response, "board", "reWrite");
//		} // qna답글작성
//
//		if (sPath.equals("/reWritePro.bo")) {
//			System.out.println("reWritePro.bo");
//			qnaService = new QnaService();
//			qnaService.writeRe(request);
//			response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동
//		} // qna답글작성 후 등록

//		if (sPath.equals("/reUpdate.bo")) {
//			System.out.println("reUpdate.re");
//			qnaService = new QnaService();
//			QnaDTO qnaDTO = qnaService.getQna(request);
//			request.setAttribute("qnaDTO", qnaDTO);
//			webForward(request, response, "board", "reUpdate");
//		} // qna답글수정
//
//		if (sPath.equals("/reUpdatePro.bo")) {
//			System.out.println("reUpdatePro.bo");
//			qnaService = new QnaService();
//			qnaService.updateRe(request);
//			response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동
//		} // qna답글수정 후 등록
//
//		if (sPath.equals("/reDelete.bo")) {
//			System.out.println("reDelete.re");
//			qnaService = new QnaService();
//			qnaService.deleteRe(request);
//			response.sendRedirect("qna.bo"); // 글을 다 쓰고 나면 다시 리스트로 이동
//		} // qna답글삭제

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Board1Controller doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Board1Controller doPost()");
		doProcess(request, response);
	}

	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName)
			throws ServletException, IOException {
		request.getRequestDispatcher("/" + folder + "/" + pageName + ".jsp").forward(request, response);
	}

} //end_of_Board1Controller

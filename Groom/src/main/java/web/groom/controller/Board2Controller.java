package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.PageDTO;
import web.groom.dto.QnaDTO;
import web.groom.service.QnaService;

@WebServlet("*.bo") //.bo 게시판관련페이지 어노테이션 매핑 선언
public class Board2Controller extends HttpServlet {
	
	QnaService qnaService = null;
	RequestDispatcher dispatcher =null;

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Board2Controller doProcess()");
		//가상주소 뽑아오기
		String sPath=request.getServletPath();
		System.out.println("뽑아온 가상주소:"+sPath);
		
		
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
		 
		 
	}//

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

package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web.groom.dao.Board1DAO;
import web.groom.dto.Board1DTO;
import web.groom.dto.PageDTO;

public class Board1Service {
	
	
	Board1DAO boardDAO = null;

	public List<Board1DTO> getNotice(PageDTO pageDTO) {
		System.out.println("List BoardService Notice()");
		List<Board1DTO> notice = null;
		try {
			// 시작하는 행부터 10개 뽑아오기
			int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;
			// 시작하는 행부터 끝나는 행까지 뽑아오기
			int endRow = startRow+pageDTO.getPageSize()-1;
			
			//pageDTO 저장 <= startRow, endRow
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// notice = getNotice() 메서드 호출
			notice = boardDAO.getNotice(pageDTO);//
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}//getNotice//
	
	//검색
	public List<Board1DTO> getNoticeSearch(PageDTO pageDTO) {
		System.out.println("BoardService getNoticeSearch()");
		List<Board1DTO> notice = null;
		try {
			// 시작하는 행부터 10개 뽑아오기
			int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;
			// 시작하는 행부터 끝나는 행까지 뽑아오기
			int endRow = startRow+pageDTO.getPageSize()-1;
			
			//pageDTO 저장 <= startRow, endRow
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// notice = getNotice() 메서드 호출
			notice = boardDAO.getNoticeSearch(pageDTO);//
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}
	

	public void insertNotice(HttpServletRequest request) {
		try {
			System.out.println("BoardService insertNotice()");
			// request 한글처리
			request.setCharacterEncoding("utf-8");
			
			//업로드 폴더 경로 => 물리적경로
			String uploadPath = request.getRealPath("/upload");
			//이클립스에 실행하면 이클립스 가상경로
			System.out.println(uploadPath);
			//파일 최대 크기 지정 10M
			int maxSize = 10*1024*1024;
//			import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
			// new DefaultFileRenamePolicy()
			MultipartRequest multi 
				= new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			
			// request 파라미터 값 가져오기
			String n_title = multi.getParameter("n_title");
			String n_content = multi.getParameter("n_content");
			//첨부파일이름 가져오기
			String n_img_url = multi.getFilesystemName("n_img_url");
			Timestamp n_date = new Timestamp(System.currentTimeMillis());
			boardDAO = new Board1DAO();
			// BoardDTO 객체생성
			Board1DTO boardDTO = new Board1DTO();
			// set메서드 호출 파라미터값 저장
			boardDTO.setN_title(n_title);
			boardDTO.setN_content(n_content);
			boardDTO.setN_date(n_date);
			//첨부파일
			boardDTO.setN_img_url(n_img_url);
			//확인
			System.out.println("n_title"+n_title);
			System.out.println("n_content"+n_content);
			System.out.println("n_date"+n_date);
			// 리턴할형없음 insertNotice(boardDTO) 호출
			boardDAO.insertNotice(boardDTO);
			
			// 값 받아오는지 확인하기 위함 지워도 상관없음 
            System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//insertNotice//

	public Board1DTO getNotice(HttpServletRequest request) {
		System.out.println("BoardService getNotice()");
		Board1DTO boardDTO = null;
		try {
			// request 한글처리
			request.setCharacterEncoding("utf-8");
			// request 파라미터 가져오기 => int num 저장
			int n_num = Integer.parseInt(request.getParameter("n_num"));
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// boardDTO = getBoard(num) 메서드 호출
			boardDTO = boardDAO.getBoard(n_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDTO;
	}//getNotice//

	public int getBoardCount() {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// count = getBoardCount() 호출
			count = boardDAO.getBoardCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCount

	
	//검색
	public int getBoardCountSearch(PageDTO pageDTO) {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// count = getBoardCount() 호출
			count = boardDAO.getBoardCountSearch(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCountSearch
	public void updateNotice(HttpServletRequest request) {
		System.out.println("BoardService updateNotice()");
		try {
			// request 한글처리
//			request.setCharacterEncoding("utf-8");
			
			//파일업로드
			// 업로드할 파일경로(upload폴더 있어야함)
			String uploadPath = request.getRealPath("/upload");
			int maxSize = 10*1024*1024;
			MultipartRequest multi 
				= new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			//  request 파라미터 값 가져오기
			int n_num = Integer.parseInt(multi.getParameter("n_num"));
			String n_title = multi.getParameter("n_title");
			String n_content = multi.getParameter("n_content");
			//file oldfile
			String n_img_url =multi.getFilesystemName("n_img_url");
			//첨부파일 없는 경우
			if(n_img_url == null) {
				//기존파일 이름 가져오기
				n_img_url = multi.getParameter("oldfile");
			}
			
			// BoardDTO 객체생성
			Board1DTO boardDTO = new Board1DTO();
			// set메서드 호출 파라미터값 저장
			boardDTO.setN_num(n_num);
			boardDTO.setN_title(n_title);
			boardDTO.setN_content(n_content);
			//파일
			boardDTO.setN_img_url(n_img_url);
			//BoardDAO 객체생성
			Board1DAO boardDAO = new Board1DAO();
			//updateBoard(boardDTO) 메서드 호출
			boardDAO.updateNotice(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteNotice(HttpServletRequest request) {
		System.out.println("Board1Service  deleteNotice");
		try {
			request.setCharacterEncoding("utf-8");
			int n_num = Integer.parseInt(request.getParameter("n_num"));
			Board1DTO boardDTO = new Board1DTO();
			boardDTO.setN_num(n_num);
			
			boardDAO = new Board1DAO();
			boardDAO.deleteNotice(n_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//FAQ
	//====================================================================
	
	public List<Board1DTO> getFaq(PageDTO pageDTO) {
		System.out.println("List BoardService Faq()");
		List<Board1DTO> faq = null;
		try {
			// 시작하는 행부터 10개 뽑아오기
			int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;
			// 시작하는 행부터 끝나는 행까지 뽑아오기
			int endRow = startRow+pageDTO.getPageSize()-1;
			
			//pageDTO 저장 <= startRow, endRow
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// faq = getFaq() 메서드 호출
			faq = boardDAO.getFaq(pageDTO);//
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faq;
	}//getFaq//
	
	//검색
	public List<Board1DTO> getFaqSearch(PageDTO pageDTO) {
		System.out.println("BoardService getFaqSearch()");
		List<Board1DTO> faq = null;
		try {
			// 시작하는 행부터 10개 뽑아오기
			int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;
			// 시작하는 행부터 끝나는 행까지 뽑아오기
			int endRow = startRow+pageDTO.getPageSize()-1;
			
			//pageDTO 저장 <= startRow, endRow
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// faq = getFaq() 메서드 호출
			faq = boardDAO.getFaqSearch(pageDTO);//
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faq;
	}
	

	public void insertFaq(HttpServletRequest request) {
		try {
			System.out.println("BoardService insertFaq()");
			// request 한글처리
			request.setCharacterEncoding("utf-8");
			
			//업로드 폴더 경로 => 물리적경로
			String uploadPath = request.getRealPath("/upload");
			//이클립스에 실행하면 이클립스 가상경로
			System.out.println(uploadPath);
			//파일 최대 크기 지정 10M
			int maxSize = 10*1024*1024;
//			import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
			// new DefaultFileRenamePolicy()
			MultipartRequest multi 
				= new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			
			// request 파라미터 값 가져오기
			String faq_title = multi.getParameter("faq_title");
			String faq_content = multi.getParameter("faq_content");
			//첨부파일이름 가져오기
			String faq_img_url = multi.getFilesystemName("faq_img_url");
			Timestamp faq_date = new Timestamp(System.currentTimeMillis());
			boardDAO = new Board1DAO();
			// BoardDTO 객체생성
			Board1DTO boardDTO = new Board1DTO();
			// set메서드 호출 파라미터값 저장
			boardDTO.setFaq_title(faq_title);
			boardDTO.setFaq_content(faq_content);
			boardDTO.setFaq_date(faq_date);
			//첨부파일
			boardDTO.setFaq_img_url(faq_img_url);
			//확인
			System.out.println("faq_title"+faq_title);
			System.out.println("faq_content"+faq_content);
			System.out.println("faq_date"+faq_date);
			// 리턴할형없음 insertFaq(boardDTO) 호출
			boardDAO.insertFaq(boardDTO);
			
			// 값 받아오는지 확인하기 위함 지워도 상관없음 
            System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//insertFaq//

	public Board1DTO getFaq(HttpServletRequest request) {
		System.out.println("BoardService getFaq()");
		Board1DTO boardDTO = null;
		try {
			// request 한글처리
			request.setCharacterEncoding("utf-8");
			// request 파라미터 가져오기 => int num 저장
			int faq_num = Integer.parseInt(request.getParameter("faq_num"));
			System.out.println(faq_num);
			
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// boardDTO = getBoard(num) 메서드 호출
			boardDTO = boardDAO.getBoard2(faq_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDTO;
	}//getFaq//

	public int getBoardCount2() {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// count = getBoardCount() 호출
			count = boardDAO.getBoardCount2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCount

	
	//검색
	public int getBoardCountSearch2(PageDTO pageDTO) {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// BoardDAO 객체생성
			boardDAO = new Board1DAO();
			// count = getBoardCount() 호출
			count = boardDAO.getBoardCountSearch(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCountSearch

	public void updateFaq(HttpServletRequest request) {
		System.out.println("BoardService updateFaq()");
		try {
			// request 한글처리
//			request.setCharacterEncoding("utf-8");
			
			//파일업로드
			// 업로드할 파일경로(upload폴더 있어야함)
			String uploadPath = request.getRealPath("/upload");
			int maxSize = 10*1024*1024;
			MultipartRequest multi 
				= new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			//  request 파라미터 값 가져오기
			int faq_num = Integer.parseInt(multi.getParameter("faq_num"));
			String faq_title = multi.getParameter("faq_title");
			String faq_content = multi.getParameter("faq_content");
			//file oldfile
			String faq_img_url =multi.getFilesystemName("faq_img_url");
			//첨부파일 없는 경우
			if(faq_img_url == null) {
				//기존파일 이름 가져오기
				faq_img_url = multi.getParameter("oldfile");
			}
			System.out.println("updatefaq");
			// BoardDTO 객체생성
			Board1DTO boardDTO = new Board1DTO();
			// set메서드 호출 파라미터값 저장
			boardDTO.setFaq_num(faq_num);
			boardDTO.setFaq_title(faq_title);
			boardDTO.setFaq_content(faq_content);
			//파일
			boardDTO.setFaq_img_url(faq_img_url);
			//BoardDAO 객체생성
			Board1DAO boardDAO = new Board1DAO();
			//updateBoard(boardDTO) 메서드 호출
			boardDAO.updateFaq(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteFaq(HttpServletRequest request) {
		System.out.println("Board1Service  deleteFaq");
		try {
			request.setCharacterEncoding("utf-8");
			int faq_num = Integer.parseInt(request.getParameter("faq_num"));
			Board1DTO boardDTO = new Board1DTO();
			boardDTO.setFaq_num(faq_num);
			
			boardDAO = new Board1DAO();
			boardDAO.deleteFaq(faq_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}//클래스

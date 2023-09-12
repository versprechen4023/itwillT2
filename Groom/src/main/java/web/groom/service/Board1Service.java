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
	
	// 공지사항 리스트 가져오는 서비스
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
			
			// notice = getNotice() 메서드 호출
			notice = new Board1DAO().getNotice(pageDTO);//
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}//getNotice//
	
	// 공지사항 검색하는 서비스
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
			
			// notice = getNotice() 메서드 호출
			notice = new Board1DAO().getNoticeSearch(pageDTO);//
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}//getNoticeSearch
	
	// 공지사항 작성하는 서비스
	public boolean insertNotice(HttpServletRequest request) {
		
		boolean result = false;
		
		try {
			System.out.println("BoardService insertNotice()");
			
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
			// insertNotice(boardDTO) 호출
			result = new Board1DAO().insertNotice(boardDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}//insertNotice//
	
	// 공지사항 내용 가져오는 서비스
	public Board1DTO getNotice(HttpServletRequest request) {
		System.out.println("BoardService getNotice()");
		Board1DTO boardDTO = null;
		try {
			// request 파라미터 가져오기 => int n_num 저장
			int n_num = Integer.parseInt(request.getParameter("n_num"));
			// boardDTO = getBoard(num) 메서드 호출
			boardDTO = new Board1DAO().getBoard(n_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDTO;
	}//getNotice//
	
	// 공지사항 전체 글 개수 구하는 서비스
	public int getBoardCount() {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// count = getBoardCount() 호출
			count = new Board1DAO().getBoardCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCount

	
	// 공지사항 검색할때 전체 글 개수 구하는 서비스
	public int getBoardCountSearch(PageDTO pageDTO) {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// count = getBoardCount() 호출
			count = new Board1DAO().getBoardCountSearch(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCountSearch
	
	// 공지사항 수정하는 서비스
	public boolean updateNotice(HttpServletRequest request) {
		System.out.println("BoardService updateNotice()");
		
		boolean result = false;
		
		try {
			
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
	
			//updateBoard(boardDTO) 메서드 호출
			result = new Board1DAO().updateNotice(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}//updateNotice
	
	// 공지사항 삭제하는 서비스
	public boolean deleteNotice(HttpServletRequest request) {
		System.out.println("Board1Service  deleteNotice");
		
		boolean result = false;
		
		try {
			// 변수에 리퀘스트 파라미터값 저장(공지사항 글번호)
			int n_num = Integer.parseInt(request.getParameter("n_num"));

			// deleteNotice(u_num) 메서드 호출
			result = new Board1DAO().deleteNotice(n_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}//deleteNotice
	
	//FAQ
	//====================================================================
	
	// FAQ 리스트 가져오는 서비스
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
			
			// faq = getFaq() 메서드 호출
			faq = new Board1DAO().getFaq(pageDTO);//
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faq;
	}//getFaq//
	
	// FAQ 검색해서 리스트 가져오는 서비스
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
			
			// faq = getFaq() 메서드 호출
			faq = new Board1DAO().getFaqSearch(pageDTO);//
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faq;
	}//getFaqSearch//
	
	// FAQ 작성하는 서비스
	public boolean insertFaq(HttpServletRequest request) {
		
		boolean result = false;
		
		try {
			System.out.println("BoardService insertFaq()");
			
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
			// insertFaq(boardDTO) 호출
			result = new Board1DAO().insertFaq(boardDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}//insertFaq//
	
	// FAQ 내용 가져오는 서비스
	public Board1DTO getFaq(HttpServletRequest request) {
		System.out.println("BoardService getFaq()");
		Board1DTO boardDTO = null;
		try {
			// request 파라미터 가져오기 => int faq_num 저장
			int faq_num = Integer.parseInt(request.getParameter("faq_num"));
			System.out.println(faq_num);
			
			// boardDTO = getBoard(num) 메서드 호출
			boardDTO = new Board1DAO().getBoard2(faq_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDTO;
	}//getFaq//
	
	// FAQ 전체 글 개수 구하는 서비스
	public int getBoardCount2() {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// count = getBoardCount() 호출
			count = new Board1DAO().getBoardCount2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCount

	
	// FAQ 검색할때 전체 글 개수 구하는 서비스
	public int getBoardCountSearch2(PageDTO pageDTO) {
		System.out.println("BoardService getBoardCount()");
		int count=0;
		try {
			// count = getBoardCount() 호출
			count = new Board1DAO().getBoardCountSearch2(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getBoardCountSearch
	
	// FAQ 글 수정하는 서비스
	public boolean updateFaq(HttpServletRequest request) {
		System.out.println("BoardService updateFaq()");
		
		boolean result = false;
		
		try {
			
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
			result = new Board1DAO().updateFaq(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}//updateFaq
	
	// FAQ 글 삭제하는 서비스
	public boolean deleteFaq(HttpServletRequest request) {
		System.out.println("Board1Service  deleteFaq");
		
		boolean result = false;
		try {
			// 변수에 리퀘스트 파라미터값 저장(FAQ 글번호)
			int faq_num = Integer.parseInt(request.getParameter("faq_num"));
			
			// deleteFAQ(faq_num) 메서드 호출
			result = new Board1DAO().deleteFaq(faq_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}//deleteFaq
	
	
}//클래스

package web.groom.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale.Category;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web.groom.dao.QnaDAO;
import web.groom.dto.PageDTO;
import web.groom.dto.QnaDTO;



public class QnaService {
	
	QnaDAO qnaDAO = null;
	QnaDTO qnaDTO = null;
	
	
	//검색
		public List<QnaDTO> getQnaSearch(PageDTO pageDTO) {
			System.out.println("BoardService getQnaSearch()");
			List<QnaDTO> qna = null;
			try {
				// 시작하는 행부터 10개 뽑아오기
				int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;
				// 시작하는 행부터 끝나는 행까지 뽑아오기
				int endRow = startRow+pageDTO.getPageSize()-1;
				
				//pageDTO 저장 <= startRow, endRow
				pageDTO.setStartRow(startRow);
				pageDTO.setEndRow(endRow);
				
				// BoardDAO 객체생성
				qnaDAO = new QnaDAO();
				// notice = getNotice() 메서드 호출
				qna = qnaDAO.getQnaSearch(pageDTO);//
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return qna;
		}
		
		
		
		
		
		//검색
		public int getQnaCountSearch(PageDTO pageDTO) {
			System.out.println("BoardService getQnaCountSearch()");
			int count=0;
			try {
				// BoardDAO 객체생성
				qnaDAO = new QnaDAO();
				// count = getBoardCount() 호출
				count = qnaDAO.getQnaCountSearch(pageDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}//getBoardCountSearch
	
	
	
	public List<QnaDTO> getQnaList(PageDTO pageDTO) {
		System.out.println("QnaService getQnaList()");
		List<QnaDTO> qna = null;
		try {
			
			int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;	
			int endRow = startRow+pageDTO.getPageSize()-1;
			
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			// BoardDAO 객체생성
			qnaDAO = new QnaDAO();
			// notice = getNotice() 메서드 호출
			qna = qnaDAO.getQnaList(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qna;
	}//getQnaList(qna목록)
	
	
	public List<QnaDTO> getNoanswer(PageDTO pageDTO) {
		System.out.println("QnaService getNoanswer()");
		List<QnaDTO> qna = null;
		try {
			
			int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;	
			int endRow = startRow+pageDTO.getPageSize()-1;
			
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			// BoardDAO 객체생성
			qnaDAO = new QnaDAO();
			// notice = getNotice() 메서드 호출
			qna = qnaDAO.getQnaList(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qna;
	}//getNoanswer(답변X)
	
	
	
	//검색
	public int getCountNoanswer(PageDTO pageDTO) {
		System.out.println("QnaService getCountNoanswer()");
		int count=0;
		try {
			// BoardDAO 객체생성
			qnaDAO = new QnaDAO();
			// count = getBoardCount() 호출
			count = qnaDAO.getCountNoanswer(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getCountNoanswer
	
	
	
	public int getQnaCount() {
		System.out.println("QnaService getBoardCount()");
		int count=0;
		try {
			// BoardDAO 객체생성
			qnaDAO = new QnaDAO();
			// count = getBoardCount() 호출
			count = qnaDAO.getQnaCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}//getQnaCount
	
	
	
	public QnaDTO getQna(HttpServletRequest request) {
		System.out.println("QnaService getQna()");
		QnaDTO qnaDTO = null;
		try {
			int qnanum = Integer.parseInt(request.getParameter("qna_num"));
			System.out.println(qnanum);
			qnaDAO = new QnaDAO();
			qnaDTO = qnaDAO.getQna(qnanum);
			System.out.println("DTO주소값"+qnaDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaDTO;
	}//getQna(qna상세)
	
	
	
	
	public QnaDTO insertQna(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");

            // qnawrite에서 받는 값
            String uploadPath = request.getRealPath("/upload");
            int maxSize = 1010241024;
            MultipartRequest multi 
            = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());

            String u_id = multi.getParameter("u_id");
            String qna_title = multi.getParameter("qna_title");
            String qna_content = multi.getParameter("qna_content");
            String qna_img_url = multi.getFilesystemName("qna_img_url");
            Timestamp qna_date = new Timestamp(System.currentTimeMillis());

            // QnaDTO 객체생성
            QnaDTO qnaDTO = new QnaDTO();
            // DB에 넘겨줄값들을 set메서드 호출해서 파라미터값 저장
            qnaDTO.setId(u_id);
            qnaDTO.setTitle(qna_title);
            qnaDTO.setContent(qna_content);
            qnaDTO.setDate(qna_date);
            qnaDTO.setQnaimgurl(qna_img_url);

            System.out.println("boardService");
            System.out.println("u_id==========" + u_id);
            System.out.println("u_title============" + qna_title);
            System.out.println("u_content===========" + qna_content);
            System.out.println("u_date===============" + qna_date);
            System.out.println("QNAIMGURL===========" + qna_img_url);

            // QnaDAO 객체생성
            qnaDAO = new QnaDAO();

            // DAO생성해서 얘로 DTO값을 넘겨줌 
            QnaDAO qnaDAO = new QnaDAO();
            qnaDAO.insertqnaBoard(qnaDTO);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return qnaDTO;

    }//insertQna(qna작성)
	
	
	
	
	
//	public QnaDTO insertQna(HttpServletRequest request) {
//		try {
//			request.setCharacterEncoding("utf-8");
//			
//			// qnawrite에서 받는 값
//			String id = request.getParameter("u_id");
//			System.out.println("id"+id);
//			String title = request.getParameter("qna_title");
//			String content = request.getParameter("qna_content");
//			String imgurl = request.getParameter("qna_img_url");	
//			String category = request.getParameter("qna_category");	
//			Timestamp qnadate = new Timestamp(System.currentTimeMillis());
//			 
//			// QnaDTO 객체생성
//			QnaDTO qnaDTO = new QnaDTO();
//			// DB에 넘겨줄값들을 set메서드 호출해서 파라미터값 저장
//			qnaDTO.setId(id);
//			qnaDTO.setTitle(title);
//			qnaDTO.setContent(content);
//			qnaDTO.setDate(qnadate);
//			qnaDTO.setQnaimgurl(imgurl);
//			qnaDTO.setCategory(category);
//
//			
//			// QnaDAO 객체생성
//			qnaDAO = new QnaDAO();
//			
//			// DAO생성해서 얘로 DTO값을 넘겨줌 
//			QnaDAO qnaDAO = new QnaDAO();
//			qnaDAO.insertqnaBoard(qnaDTO);
//			
////			// 값 받아오는지 확인하기 위함 지워도 상관없음 
////			System.out.println("boardService");
////			System.out.println("u_id=" + u_id);
////			System.out.println("qna_title=" + title);
////			System.out.println("u_content=" + qna_content);
////			System.out.println("u_date=" + qna_date);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return qnaDTO;
//		
//	}//insertQna(qna작성)
	
	
	
	public void deleteQna(HttpServletRequest request) {
		System.out.println("QnaService deleteQna()");
		try {
			int qna_num = Integer.parseInt(request.getParameter("qna_num"));
			qnaDAO = new QnaDAO();
			qnaDAO.deleteQna(qna_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//deleteQna(qna삭제)
	
	
	
	public void updateQna(HttpServletRequest request) {
		System.out.println("QnaService updateQna()");
	        try {
	            String uploadPath = request.getRealPath("/upload");
	            int maxSize = 10*1024*1024;
	            MultipartRequest multi 
	                = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());

	            int qnanum = Integer.parseInt(multi.getParameter("qna_num"));
	            String title = multi.getParameter("qna_title");
	            String content = multi.getParameter("qna_content");
	            //file oldfile
	            String qnaimgurl =multi.getFilesystemName("qna_img_url");
	            //첨부파일 없는 경우
	            if(qnaimgurl == null) {
	                //기존파일 이름 가져오기
	            	qnaimgurl = multi.getParameter("oldfile");
	            }
	            System.out.println("updateqna");
	            // BoardDTO 객체생성
	            QnaDTO qnaDTO = new QnaDTO();
	            // set메서드 호출 파라미터값 저장
	            qnaDTO.setQnanum(qnanum);
	            qnaDTO.setTitle(title);
	            qnaDTO.setContent(content);
	            //파일
	            qnaDTO.setQnaimgurl(qnaimgurl);
	            //BoardDAO 객체생성
	            QnaDAO qnaDAO = new QnaDAO();
	            //updateBoard(boardDTO) 메서드 호출
	            qnaDAO.updateQna(qnaDTO);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}//updateQna(qna수정)
	
	
	
	public void writeRe(HttpServletRequest request) { 
		System.out.println("QnaService writeRe()");
		try {
			int qna_num = Integer.parseInt(request.getParameter("qna_num"));
			String recontent = request.getParameter("re_content");
			Timestamp redate = new Timestamp(System.currentTimeMillis());
			
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setQnanum(qna_num);
			qnaDTO.setRecontent(recontent);
			qnaDTO.setRedate(redate);
			
			qnaDAO = new QnaDAO();
			qnaDAO.writeRe(qnaDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//writeRe(답글작성)
	
	
	
	public void updateRe(HttpServletRequest request) {
		System.out.println("QnaService updateRe()");
		try {
			int qnanum = Integer.parseInt(request.getParameter("qna_num"));
			String recontent = request.getParameter("re_content");

			QnaDTO reviewDTO = new QnaDTO();
			qnaDTO.setQnanum(qnanum);
			qnaDTO.setRecontent(recontent);

			qnaDAO = new QnaDAO();
			qnaDAO.updateRe(qnaDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//updateRe(답글수정)
	
	
	
	public void deleteRe(HttpServletRequest request) { 
		System.out.println("QnaService deleteRe()");
		try {
			int qnanum = Integer.parseInt(request.getParameter("qna_num"));
			String recontent = request.getParameter("re_content");
			
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setQnanum(qnanum);
			qnaDTO.setRecontent(recontent);
			
			qnaDAO = new QnaDAO();
			qnaDAO.deleteRe(qnaDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//deleteRe(답글삭제)


	
}//클래스

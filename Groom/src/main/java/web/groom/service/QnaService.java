package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web.groom.dao.QnaDAO;
import web.groom.dto.PageDTO;
import web.groom.dto.QnaDTO;



public class QnaService {
	
	QnaDAO qnaDAO = null;
	QnaDTO qnaDTO = null;
	
	
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
			qnaDAO = new QnaDAO();
			qnaDTO = qnaDAO.getQna(qnanum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaDTO;
	}//getQna(qna상세)
	
	
	
	public QnaDTO insertQna(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
			
			// qnawrite에서 받는 값
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
		
	}//insertQna(qna작성)
	
	
	
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

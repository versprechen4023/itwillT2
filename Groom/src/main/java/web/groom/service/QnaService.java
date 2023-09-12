package web.groom.service;

import java.io.File;
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

	// QNA 글 검색하는 서비스
	public List<QnaDTO> getQnaSearch(PageDTO pageDTO) {
		System.out.println("QnaService getQnaSearch()");
		List<QnaDTO> qna = null;
		try {
			// 시작하는 행부터 10개 뽑아오기
			int startRow = (pageDTO.getCurrentPage() - 1) * pageDTO.getPageSize() + 1;
			// 시작하는 행부터 끝나는 행까지 뽑아오기
			int endRow = startRow + pageDTO.getPageSize() - 1;

			// pageDTO 저장 <= startRow, endRow
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);

			// qna = getQnaSearch() 메서드 호출
			qna = new QnaDAO().getQnaSearch(pageDTO);//

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qna;
	}//getQnaSearch

	// QNA 검색할때 전체 글 개수 구하는 서비스
	public int getQnaCountSearch(PageDTO pageDTO) {
		System.out.println("QnaService getQnaCountSearch()");
		int count = 0;
		try {
			// count = getQnaCountSearch() 호출
			count = new QnaDAO().getQnaCountSearch(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}// getQnaCountSearch
	
	// QNA 리스트 가져오는 서비스
	public List<QnaDTO> getQnaList(PageDTO pageDTO) {
		System.out.println("QnaService getQnaList()");
		List<QnaDTO> qna = null;
		try {
			// 시작하는 행부터 10개 뽑아오기
			int startRow = (pageDTO.getCurrentPage() - 1) * pageDTO.getPageSize() + 1;
			// 시작하는 행부터 끝나는 행까지 뽑아오기
			int endRow = startRow + pageDTO.getPageSize() - 1;
			
			// pageDTO 저장 <= startRow, endRow
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);

			// qna = getQnaList() 메서드 호출
			qna = new QnaDAO().getQnaList(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qna;
	}// getQnaList(qna목록)
	
	// QNA 리스트 가져오는 서비스(미답변 된 것만)
	public List<QnaDTO> getNoanswer(PageDTO pageDTO) {
		System.out.println("QnaService getNoanswer()");
		List<QnaDTO> qna = null;
		try {
			// 시작하는 행부터 10개 뽑아오기
			int startRow = (pageDTO.getCurrentPage() - 1) * pageDTO.getPageSize() + 1;
			// 시작하는 행부터 끝나는 행까지 뽑아오기
			int endRow = startRow + pageDTO.getPageSize() - 1;
			
			// pageDTO 저장 <= startRow, endRow
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);

			// qna = getNoanswer() 메서드 호출
			qna = new QnaDAO().getNoanswer(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qna;
	}// getNoanswer(답변X)

	// QNA 전체 글 개수 구하는 서비스(미답변 된 것만)
	public int getCountNoanswer(PageDTO pageDTO) {
		System.out.println("QnaService getCountNoanswer()");
		int count = 0;
		try {
			// count = getCountNoanswer() 호출
			count = new QnaDAO().getCountNoanswer(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}// getCountNoanswer
	
	// QNA 전체 글 개수 구하는 서비스
	public int getQnaCount() {
		System.out.println("QnaService getQnaCount()");
		int count = 0;
		try {
			// count = getQnaCount() 호출
			count = new QnaDAO().getQnaCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}// getQnaCount
	
	// QNA 내용 가져오는 서비스
	public QnaDTO getQna(HttpServletRequest request) {
		System.out.println("QnaService getQna()");
		QnaDTO qnaDTO = null;
		try {
			// request 파라미터 가져오기 => int qnanum 저장
			int qnanum = Integer.parseInt(request.getParameter("qna_num"));
			// qnaDTO = getQna(qnanum) 메서드 호출
			qnaDTO = new QnaDAO().getQna(qnanum);
			System.out.println("DTO주소값" + qnaDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaDTO;
	}// getQna(qna상세)
	
	// QNA 작성하는 서비스
	public boolean insertQna(HttpServletRequest request) {
		
		boolean result = false;
		
		try {
			
			// qnawrite에서 받는 값
			String uploadPath = request.getRealPath("/upload");
			
			// 업로드 폴더 경로를 기반으로 File 객체 생성
			File uploadDir = new File(uploadPath);

			// 업로드 폴더가 존재하지 않으면 생성
			if (!uploadDir.exists()) {
			    if (uploadDir.mkdirs()) {
			        System.out.println("업로드 폴더 생성.");
			    } else {
			        System.out.println("업로드 폴더를 생성에 문제 발생.");
			    }
			}
			// 파일 최대 크기 지정 10M
			int maxSize = 10*1024*1024;
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			// request 파라미터 값 가져오기
			String u_id = multi.getParameter("u_id");
			String qna_title = multi.getParameter("qna_title");
			String qna_category = multi.getParameter("qna_category");
			String qna_content = multi.getParameter("qna_content");
			//첨부파일이름 가져오기
			String qna_img_url = multi.getFilesystemName("qna_img_url");
			Timestamp qna_date = new Timestamp(System.currentTimeMillis());

			// QnaDTO 객체생성
			QnaDTO qnaDTO = new QnaDTO();
			// DB에 넘겨줄값들을 set메서드 호출해서 파라미터값 저장
			qnaDTO.setId(u_id);
			qnaDTO.setTitle(qna_title);
			qnaDTO.setCategory(qna_category);
			qnaDTO.setContent(qna_content);
			qnaDTO.setQnaimgurl(qna_img_url);
			qnaDTO.setDate(qna_date);
			System.out.println("qnaService++++++++++++");
			System.out.println("u_id==========" + u_id);
			System.out.println("u_title============" + qna_title);
			System.out.println("u_content===========" + qna_content);
			System.out.println("u_date===============" + qna_date);
			System.out.println("QNAIMGURL===========" + qna_img_url);
			System.out.println("qna_category===================" + qna_category);


			// DAO생성해서 얘로 DTO값을 넘겨줌
			result = new QnaDAO().insertqnaBoard(qnaDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}// insertQna(qna작성)

	// QNA 삭제하는 서비스
	public boolean deleteQna(HttpServletRequest request) {
		System.out.println("QnaService deleteQna()");
		
		boolean result = false;
		
		try {
			// 변수에 리퀘스트 파라미터값 저장(QNA 글번호)
			int qna_num = Integer.parseInt(request.getParameter("qna_num"));
			// deleteQna(qna_num) 메서드 호출
			result = new QnaDAO().deleteQna(qna_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// deleteQna(qna삭제)
	
	// QNA 수정하는 서비스
	public boolean updateQna(HttpServletRequest request) {
		System.out.println("QnaService updateQna()");
		
		boolean result = false;
		
		try {
			String uploadPath = request.getRealPath("/upload");
			
			// 업로드 폴더 경로를 기반으로 File 객체 생성
			File uploadDir = new File(uploadPath);

			// 업로드 폴더가 존재하지 않으면 생성
			if (!uploadDir.exists()) {
			    if (uploadDir.mkdirs()) {
			        System.out.println("업로드 폴더 생성.");
			    } else {
			        System.out.println("업로드 폴더를 생성에 문제 발생.");
			    }
			}
			
			// 파일 최대 크기 지정 10M
			int maxSize = 10 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			// request 파라미터 값 가져오기
			int qnanum = Integer.parseInt(multi.getParameter("qna_num"));
			String title = multi.getParameter("qna_title");
			String content = multi.getParameter("qna_content");
			String category = multi.getParameter("qna_category");
			// file oldfile
			String qnaimgurl = multi.getFilesystemName("qna_img_url");
			// 첨부파일 없는 경우
			if (qnaimgurl == null) {
				// 기존파일 이름 가져오기
				qnaimgurl = multi.getParameter("oldfile");
			}
			System.out.println("updateqna");
			// qnaDTO 객체생성
			QnaDTO qnaDTO = new QnaDTO();
			// set메서드 호출 파라미터값 저장
			qnaDTO.setQnanum(qnanum);
			qnaDTO.setTitle(title);
			qnaDTO.setContent(content);
			qnaDTO.setCategory(category);
			// 파일
			qnaDTO.setQnaimgurl(qnaimgurl);
			
			System.out.println("큐엔에이서비스 qna_num" + qnanum);
			System.out.println("큐엔에이서비스 qna_num" + title);
			System.out.println("큐엔에이서비스 qna_num" + qnaimgurl);
			
			// updateQna(qnaDTO) 메서드 호출
			result = new QnaDAO().updateQna(qnaDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// updateQna(qna수정)
	
	// QNA 답글 작성하는 서비스
	public boolean writeRe(HttpServletRequest request) {
		System.out.println("QnaService writeRe()");
		
		boolean result = false;

		try {
			String uploadPath = request.getRealPath("/upload");
			
			// 업로드 폴더 경로를 기반으로 File 객체 생성
			File uploadDir = new File(uploadPath);

			// 업로드 폴더가 존재하지 않으면 생성
			if (!uploadDir.exists()) {
			    if (uploadDir.mkdirs()) {
			        System.out.println("업로드 폴더 생성.");
			    } else {
			        System.out.println("업로드 폴더를 생성에 문제 발생.");
			    }
			}
			
			// 파일 최대 크기 지정 10M
			int maxSize = 10 * 1024 * 1024;
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			// request 파라미터 값 가져오기
			int qna_num = Integer.parseInt(multi.getParameter("qna_num"));
			
			String re_content = multi.getParameter("re_content");
			Timestamp re_date = new Timestamp(System.currentTimeMillis());
			
			// qnaDTO 객체생성
			QnaDTO qnaDTO = new QnaDTO();
			// set메서드 호출 파라미터값 저장
			qnaDTO.setQnanum(qna_num);
			qnaDTO.setRecontent(re_content);
			qnaDTO.setRedate(re_date);

			System.out.println("qanservice writere ++++++++" + qna_num);
			System.out.println("qanservice writere ++++++++" + re_content);
			System.out.println("qanservice writere ++++++++" + re_date);
			
			// writeRe(qnaDTO) 메서드 호출
			result = new QnaDAO().writeRe(qnaDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// writeRe(답글작성)
	
	// 미사용 되는 것으로 보임
	
//	public void updateRe(HttpServletRequest request) {
//		System.out.println("QnaService updateRe()");
//		try {
//			int qnanum = Integer.parseInt(request.getParameter("qna_num"));
//			String recontent = request.getParameter("re_content");
//			String id = request.getParameter("u_id");
//	
//			QnaDTO qnaDTO = new QnaDTO();
//			qnaDTO.setQnanum(qnanum);
//			qnaDTO.setRecontent(recontent);
//
//			qnaDAO = new QnaDAO();
//			qnaDAO.updateRe(qnaDTO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}// updateRe(답글수정)
//
//	public void deleteRe(HttpServletRequest request) {
//		System.out.println("QnaService deleteRe()");
//		try {
//			int qnanum = Integer.parseInt(request.getParameter("qna_num"));
//			String recontent = request.getParameter("re_content");
//
//			QnaDTO qnaDTO = new QnaDTO();
//			qnaDTO.setQnanum(qnanum);
//			qnaDTO.setRecontent(recontent);
//
//			qnaDAO = new QnaDAO();
//			qnaDAO.deleteRe(qnaDTO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}// deleteRe(답글삭제)

}// 클래스
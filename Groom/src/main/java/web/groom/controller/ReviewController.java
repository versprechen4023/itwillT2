package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.ReviewDTO;
import web.groom.javascript.JSForward;
import web.groom.service.ReviewService;

@WebServlet("*.re") // .re 리뷰페이지 어노테이션 매핑 선언
public class ReviewController extends HttpServlet {

	RequestDispatcher dispatcher = null;
	ReviewService reviewService = null;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sPath = request.getServletPath();

		// 리뷰리스트 전체 받아오는 페이지 review/reviewList.jsp
		if (sPath.equals("/reviewList.re")) {
//			 String proName = request.getParameter("pro_name");

			// 리뷰 전체 리스트를 가져오기 위한 메서드 호출
			List<ReviewDTO> reviewList = new ReviewService().getReviewListAll();
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			request.setAttribute("reviewList", reviewList);
			System.out.println("reviewList.re");
			webForward(request, response, "review", "reviewList");
		} // reviewList.re [리뷰목록]

		// 사용되지 않는 것으로 보이는데 정확하지 않음 물어보고 주석처리
//		if (sPath.equals("/reviewList.re?")) {
//			String proName = request.getParameter("pro_name");
//			List<ReviewDTO> reviewList = new ReviewService().getReviewList(proName);
//			request.setAttribute("reviewList", reviewList);
//			System.out.println("reviewList.re");
//			webForward(request, response, "review", "reviewList");
//		} // reviewList.re [리뷰목록 선택]

		// 리뷰리스트 유저가적은리뷰 받아오는 페이지 review/reviewList.jsp
		if (sPath.equals("/myReviewList.re")) {
			List<ReviewDTO> reviewList = new ReviewService().getMyReviewList(request);
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			request.setAttribute("reviewList", reviewList);
			System.out.println("reviewList.re");
			webForward(request, response, "review", "reviewList");
		} // myReviewList.re [내 리뷰목록] // '내정보'에서 'u_num' 넘기는 버튼과 연결시키기 //

		// 리뷰리스트 목욕관련 받아오는 페이지 review/reviewList1.jsp
		if (sPath.equals("/reviewList1.re")) {
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			List<ReviewDTO> reviewList = new ReviewService().getReviewList(request);
			request.setAttribute("reviewList", reviewList);
			webForward(request, response, "review", "reviewList1");
		} // reviewList1.re [리뷰목록1]

		// 리뷰리스트 부분미용 관련 받아오는 페이지 review/reviewList2.jsp
		if (sPath.equals("/reviewList2.re")) {
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			List<ReviewDTO> reviewList = new ReviewService().getReviewList(request);
			request.setAttribute("reviewList", reviewList);
			webForward(request, response, "review", "reviewList2");
		} // reviewList2.re [리뷰목록2]

		// 리뷰리스트 부분+목욕 관련 받아오는 페이지 review/reviewList3.jsp
		if (sPath.equals("/reviewList3.re")) {
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			List<ReviewDTO> reviewList = new ReviewService().getReviewList(request);
			request.setAttribute("reviewList", reviewList);
			webForward(request, response, "review", "reviewList3");
		} // reviewList3.re [리뷰목록3]

		// 리뷰리스트 전체미용 관련 받아오는 페이지 review/reviewList4.jsp
		if (sPath.equals("/reviewList4.re")) {
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			List<ReviewDTO> reviewList = new ReviewService().getReviewList(request);
			request.setAttribute("reviewList", reviewList);
			webForward(request, response, "review", "reviewList4");
		} // reviewList4.re [리뷰목록4]

		// 리뷰리스트 스포팅 관련 받아오는 페이지 review/reviewList5.jsp
		if (sPath.equals("/reviewList5.re")) {
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			List<ReviewDTO> reviewList = new ReviewService().getReviewList(request);
			request.setAttribute("reviewList", reviewList);
			webForward(request, response, "review", "reviewList5");
		} // reviewList5.re [리뷰목록5]

		// 리뷰리스트 가위컷 관련 받아오는 페이지 review/reviewList6.jsp
		if (sPath.equals("/reviewList6.re")) {
			// 리퀘스트에 리뷰리스트를 저장후 페이지이동
			List<ReviewDTO> reviewList = new ReviewService().getReviewList(request);
			request.setAttribute("reviewList", reviewList);
			webForward(request, response, "review", "reviewList6");
		} // reviewList6.re [리뷰목록6]
			//////////// 상단 메뉴 선택시 버튼색이 안변해서 페이지 여러개 만듦

		// 리뷰내용 표시하는 페이지 review/reviewContent.jsp
		if (sPath.equals("/reviewContent.re")) {
			System.out.println("reviewDetails.re");
			// 리뷰 서비스 객체 생성
			reviewService = new ReviewService();
			// 조회수를 추가하기위한 메서드 호출
			reviewService.updateReadcount(request); // 조회수
			// 리뷰내용을 불러오기위한 메서드 호출
			ReviewDTO reviewDTO = reviewService.getReview(request);
			// 리퀘스트에 리뷰내용을 저장후 페이지이동
			request.setAttribute("reviewDTO", reviewDTO);
			webForward(request, response, "review", "reviewContent");
		} // reviewContent.re [리뷰상세]

		// 리뷰작성 관련 페이지 review/reviewWrite.jsp
		if (sPath.equals("/reviewWrite.re")) {
			System.out.println("writereview.re");
			webForward(request, response, "review", "reviewWrite");
		} // reviewWrite.re [리뷰작성]

		// 리뷰작성 관련 로직
		if (sPath.equals("/reviewWritePro.re")) {
			System.out.println("reviewWritePro.re");
			// 리뷰 작성을 위한 메서드 호출하고 결과값을 반환
			boolean result = new ReviewService().insertReview(request);
			if (result) {
				System.out.println("리뷰작성완료");
			} else {
				System.out.println("리뷰작성실패");
			}
			JSForward.windowClose(response); // 창닫기 라인
		} // writereviewPro.re [리뷰작성 후 등록]

		// 리뷰삭제를 위한 관련 로직
		if (sPath.equals("/reviewDelete.re")) {
			System.out.println("reviewDelete.re");
			// 답글 삭제를 위한 메서드를 호출하고 결과값을 반환
			boolean result = new ReviewService().deleteReview(request);
			// 정상처리 되었을 경우 출력
			if (result) {
				JSForward.locationHref(response, "리뷰 삭제 완료.", "reviewList.re");
			} else {
				JSForward.locationHref(response, "리뷰 삭제에 문제발생.", "reviewList.re");
			}
		} // reviewDelete.re [리뷰삭제]

		// 현재 포인트 회수는 관리자 페이지에서 이루어지고있으므로 사용안되고 있는 것으로 보임
//		// 리뷰삭제 및 포인트회수를 위한 관련 로직
//		if (sPath.equals("/reviewDeletePoint.re")) {
//			System.out.println("reviewDeletePoint.re");
//
//			// 리뷰 삭제를 위한 메서드 호출
//			new ReviewService().deleteReviewPoint(request);
//			JSForward.locationHref(response, "리뷰 삭제 완료.\n* 포인트가 회수됩니다.", "reviewList.re");
//		} // reviewDelete.re [리뷰삭제 + 포인트회수]

		// 관리자의 답글 작성 관련 페이지 review/reWrite.jsp
		if (sPath.equals("/reWrite.re")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				System.out.println("reWrite.re");
				// 초기 답글 호출 메서드
				ReviewDTO reviewDTO = new ReviewService().getReview(request);
				request.setAttribute("reviewDTO", reviewDTO);
				webForward(request, response, "review", "reWrite");
			}
		} // reWrite.re [답글작성]

		// 관리자 답글 작성 저장 관련 로직
		if (sPath.equals("/reWritePro.re")) {
			System.out.println("reWritePro.re");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 답글 작성을 위한 메서드를 호출하고 결과값을 반환
				boolean result = new ReviewService().writeRe(request);
				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "답글 작성 완료.\n* 포인트가 지급됩니다.", "reviewList.re");
				} else {
					JSForward.locationHref(response, "답글 작성에 문제발생", "reviewList.re");
				}
			}
		} // reWritePro.re [답글작성 후 등록 + 포인트추가]

		// 관리자 답글 수정 관련 페이지 review/reUpdate.jsp
		if (sPath.equals("/reUpdate.re")) {
			System.out.println("reUpdate.re");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 답글 내용 호출 메서드
				ReviewDTO reviewDTO = new ReviewService().getReview(request);
				request.setAttribute("reviewDTO", reviewDTO);
				webForward(request, response, "review", "reUpdate");
			}
		} // reUpdate.re [답글수정]

		// 관리자 답글 수정 관련 로직
		if (sPath.equals("/reUpdatePro.re")) {
			System.out.println("reUpdatePro.re");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 답글 수정을 위한 메서드를 호출하고 결과값을 반환
				boolean result = new ReviewService().updateRe(request);
//			 response.sendRedirect("reviewList.re"); // 목록으로
				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "답글 수정 완료.", "reviewList.re");
				} else {
					JSForward.locationHref(response, "답글 수정에 문제발생.", "reviewList.re");
				}
			}
		} // reUpdatePro.re [답글수정 후 등록]

		// 관리자 답글 삭제 관련 로직
		if (sPath.equals("/reDelete.re")) {
			System.out.println("reDelete.re");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 답글 삭제를 위한 메서드를 호출하고 결과값을 반환
				boolean result = new ReviewService().deleteRe(request);
				// 정상처리 되었을 경우 출력
				if (result) {
					JSForward.locationHref(response, "답글 삭제 완료.", "reviewList.re");
				} else {
					JSForward.locationHref(response, "답글 삭제 및 초기화에 문제 발생.", "reviewList.re");
				}
			}
		} // reDelete.re [답글삭제]

		// 리뷰 수정 관련 페이지 review/reviewUpdate.jsp
		if (sPath.equals("/reviewUpdate.re")) {
			System.out.println("reviewUpdate.re");

			// 기존의 리뷰내용을 불러오기위한 메서드 호출
			ReviewDTO reviewDTO = new ReviewService().getReview(request);
			// 리퀘스트에 리뷰내용을 저장후 페이지이동
			request.setAttribute("reviewDTO", reviewDTO);
			webForward(request, response, "review", "reviewUpdate");
		} // reUpdate.re [리뷰수정]

		// 리뷰 수정 관련 로직
		if (sPath.equals("/reviewUpdatePro.re")) {
			System.out.println("reviewUpdatePro.re");
			// 리뷰 수정을 위한 메서드를 호출하고 결과값을 반환
			boolean result = new ReviewService().updateReview(request);
			// 정상처리 되었을 경우 출력
			if (result) {
				JSForward.locationHref(response, "리뷰 수정 완료.", "reviewList.re");
			} else {
				JSForward.locationHref(response, "리뷰를 수정하는데 문제가 발생했습니다.", "reviewList.re");
			}
		} // reviewUpdatePro.re [리뷰수정 후 등록]

	}// doProcess

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName)
			throws ServletException, IOException {
		request.getRequestDispatcher("/" + folder + "/" + pageName + ".jsp").forward(request, response);
	}

} // end_of_review_controller

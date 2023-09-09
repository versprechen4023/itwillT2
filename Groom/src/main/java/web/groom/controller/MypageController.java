package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.groom.dto.MemberDTO;
import web.groom.dto.MypageDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.javascript.JSForward;
import web.groom.service.AdminService;
import web.groom.service.MemberService;
import web.groom.service.MypageService;

@WebServlet("*.my") // .my 마이페이지 어노테이션 매핑 선언
public class MypageController extends HttpServlet {

	MypageService ser = null;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sPath = request.getServletPath();

		// 마이페이지 이동 mypage/mypage.jsp
		if (sPath.equals("/mypage.my")) {

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");

			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {

				// 유저의 정보를 가져오기위한 멤버서비스 객체생성
				ser = new MypageService();

				// 유저 정보 가져오기
				MypageDTO mypageInfo = ser.MemberInfo(request);

				// request에 유저 정보 있는 memberInfo 저장
				request.setAttribute("mypageInfo", mypageInfo);

				// 펫 리스트는 mypetList에서 출력하므로 필요없음

//			// 유저 펫정보 가져오기
//			MypageDTO mypagepetinfo = ser.MypetInfo(request);
//
//			// request에 유저 정보 있는 mypagepetInfo 저장
//			request.setAttribute("mypagepetInfo", mypagepetinfo);

				// 내 펫 목록을 출력하기위한 메서드 호출
				List<MypageDTO> mypetList = ser.getMypetList(request);

				// request에 유저 정보 있는 memberInfo 저장
				request.setAttribute("mypetList", mypetList);

				// 내 예약 목록을 출력하기위한 메서드 호출
				List<OrderReservationDTO> reservationList = ser.getReservationList(request);
				// request에 예약 정보 있는 reservationList 저장
				request.setAttribute("reservationList", reservationList);

				// 모든 값 리퀘스트에 저장후 마이페이지로 이동
				webForward(request, response, "mypage", "mypage");

			}
		} // end_of_mypage.my

		// 펫정보 등록페이지 이동 mypage/insertmypet.jsp
		if (sPath.equals("/insertmypet.my")) {
			System.out.println("뽑은 가상주소 비교 : /insertmypet.my");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {
				webForward(request, response, "mypage", "insertmypet");
			}
		} // end_of_insertmypet.my

		// 펫정보 등록 매핑
		if (sPath.equals("/insertmypetPro.my")) {
			System.out.println("뽑은 가상주소 비교 : /insertmypetPro.my");

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {

				// 멤버서비스 객체생성
				ser = new MypageService();
				// 메서드호출(리퀘스트 값 넘겨주기)
				MypageDTO mpyagedto = ser.insertMypet(request);

				if (mpyagedto != null) {
					System.out.println("펫 등록 성공");
					// 마이페이지 화면이동
					JSForward.locationHref(response, "정상적으로 펫 등록이 되었습니다", "mypage.my");
				} else {
					System.out.println("펫 등록 실패");
					// 마이페이지 화면이동
					JSForward.locationHref(response, "정상적으로 펫 등록이 되지 않았습니다", "mypage.my");
				}
			}

		} // end_of_insertmypetPro.my

		// 내 펫정보 수정 mypage/updatemypet.jsp
		if (sPath.equals("/updatemypet.my")) {
			System.out.println("뽑은 가상주소 비교 : /updatemypet.my");
			// 수정하기 전에 디비 나의 정보 조회(세션값 id) => jsp 화면 출력
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {
				// MypageSerive 객체생성
				ser = new MypageService();

				// member/update.jsp 주소변경없이 이동

				// 수정한 부분입니다 ===================================================

				// 수정할 내 펫 정보를 가져오기위한 메서드 호출
				MypageDTO mypageDTOTest = ser.MypetTestInfo(request);

				// DTO를 리퀘스트에 저장
				request.setAttribute("mypageDTOTest", mypageDTOTest);

				// 값 리퀘스트에 저장후 페이지로 이동
				webForward(request, response, "mypage", "updatemypet");
			}
		} // end_of_updatemypet.my

		// 펫정보 수정 매핑
		if (sPath.equals("/updatemypetPro.my")) {
			System.out.println("뽑은 가상주소 비교 : /updatemypetPro.me");

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {
				// MypageService 객체생성
				ser = new MypageService();

				// 내 펫 정보를 수정하기위해 메서드호출 및 리퀘스트 전달
				boolean result = ser.updateMypet(request);

				if (result) {
					JSForward.locationHref(response, "펫 정보 수정이 완료되었습니다", "mypage.my");
				} else {
					JSForward.locationHref(response, "펫 정보 수정에 실패했습니다", "mypage.my");
				}
			}
		} // end_of_updatemypetPro.my

		// 펫 정보 삭제 매핑
		if (sPath.equals("/deletemypet.my")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {
				// 멤버서비스 객체생성
				ser = new MypageService();

				// 삭제 성공 유무 반환
				boolean result = ser.deleteMypet(request);

				if (result) {
					// 삭제 된 경우
					JSForward.locationHref(response, "정상적으로 삭제되었습니다", "mypage.my");
				} else {
					// 삭제 안된 경우
					JSForward.locationHref(response, "삭제 처리되지 않았습니다", "mypage.my");
				}
			}
		} // end_of_deletemypet.my

		// 내정보 수정페이지 mypage/modifyinfo.jsp
		if (sPath.equals("/modifyinfo.my")) {
			System.out.println("뽑은 가상주소 비교 : /modifyinfo.my");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {

				// MypageSerive 객체생성
				ser = new MypageService();

				// request에 유저 정보 있는 memberInfo 저장
				MypageDTO mypageInfo = ser.MemberInfo(request);
				request.setAttribute("mypageInfo", mypageInfo);

				webForward(request, response, "mypage", "modifyinfo");
			}
		} // end_of_modifyinfo.my

		// 내 정보 수정 매핑
		if (sPath.equals("/modifyinfopro.my")) {
			System.out.println("뽑은 가상주소 비교 : /modifyinfo.pro");
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {

				// MypageSerive 객체생성
				ser = new MypageService();
				// 내 정보 수정처리 성공 유무 반환
				boolean result = ser.modifyInfo(request);
				if (result) {
					JSForward.locationHref(response, "내정보 수정처리가 완료되었습니다", "mypage.my");
				} else {
					JSForward.locationHref(response, "내정보 수정처리가 완료되지 못했습니다", "mypage.my");
				}
			}
		} // end_of_modifyinfopro.my

		// 비밀번호 재설정 관련 페이지 mypage/readyresetpass.jsp
		if (sPath.equals("/readyresetpass.my")) {

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {
				webForward(request, response, "mypage", "readyresetpass");
			}

		} // end_of_readyresetpass.my

		// 비밀번호 재설정 매핑
		if (sPath.equals("/readyresetpassPro.my")) {

			// 멤버 서비스 객체생성
			MemberService memberService = new MemberService();

			// 멤버 서비스에서 비밀번호 검증
			MemberDTO memberDTO = memberService.userCheck(request);

			// 비밀번호가 일치한다면 true 아니라면 false
			boolean result = (memberDTO != null) ? true : false;

			// 비밀번호가 일치하는 경우 패스워드 재설정 창 이동
			if (result) {

				webForward(request, response, "member", "resetpassword");

			} else {
				// 비밀번호가 일치 하지 않는경우 내 정보 페이지로 이동
				JSForward.locationHref(response, "비밀번호가 일치하지 않습니다", "mypage.my");
			}

		} // end_of_readyresetpassPro.my

		// 회원 탈퇴 관련 페이지 mypage/withdraw.jsp
		if (sPath.equals("/withdraw.my")) {

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {

				webForward(request, response, "mypage", "withdraw");
			}

		} // end_of_withdraw.my

		// 회원 탈퇴 처리 매핑
		if (sPath.equals("/withdrawPro.my")) {

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {
				// 멤버서비스 객체생성
				MemberService memberService = new MemberService();

				// 멤버서비스에서 비밀번호 검증
				MemberDTO memberDTO = memberService.userCheck(request);

				// 비밀번호가 일치한다면 true 아니라면 false
				boolean result = (memberDTO != null) ? true : false;

				// 비밀번호가 일치하는 경우 회원 탈퇴(비활성화)처리
				if (result) {

					// 회원 탈퇴 비활성화 처리 유무 검증
					boolean isDisabled = memberService.userDisable(request);

					// 초기화 처리 완료
					if (isDisabled) {
						// 세션초기화
						request.getSession().invalidate();
						System.out.println("회원 탈퇴 처리 완료");
						JSForward.locationHref(response, "회원 탈퇴 처리 완료되었습니다", "main.gr");
					} else {
						// 세션초기화
						request.getSession().invalidate();
						System.out.println("회원 탈퇴 처리 실패");
						JSForward.locationHref(response, "회원 탈퇴 처리에 문제가 발생했습니다, 관리자에게 문의하십시오.", "main.gr");
					}
					// 비밀번호가 일치하지 않는경우 탈퇴 페이지 재이동
				} else {
					JSForward.locationHref(response, "비밀번호가 일치하지 않습니다.", "withdraw.my");
				}
			}

		} // end_of_withdrawPro.my

		// 에약 일정 변경 관련 페이지 mypage/changeRes.jsp
		if (sPath.equals("/changeRes.my")) {

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			if (id == null) {
				JSForward.locationHref(response, "로그인 후 이용해주십시오", "login.me");
			} else {
				webForward(request, response, "mypage", "changeRes");
			}
		} // end_of_changeRes.my

		// 예약 일정 변경 처리 매핑
		if (sPath.equals("/changeResPro.my")) {

			// 멤버 서비스 객체 생성
			ser = new MypageService();

			// 변경처리 성공 유무 반환
			boolean result = ser.changeRes(request);

			if (result) {
				JSForward.locationHref(response, "예약 일정 변경 처리가 완료되었습니다", "mypage.my");
			} else {
				JSForward.locationHref(response, "예약 일정 변경 처리에 실패했습니다", "mypage.my");
			}
		} // end_of_changeRes.my
	} // end_of_doProcess

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

} // end_of_mypage_controller

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

	RequestDispatcher dispatcher = null;
	MypageService ser = null;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sPath = request.getServletPath();

		// 페이지이동
		if (sPath.equals("/something.my")) {

		}

		// 마이페이지 이동
		if (sPath.equals("/mypage.my")) {
			
			 //유저 세션 검증
			 String id = (String)request.getSession().getAttribute("id");
			 
			 //세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			 if (id == null){
				 response.sendRedirect("login.me");
			 } 
			 
			// 유저의 정보를 가져오기위한 멤버서비스 객체생성
			ser = new MypageService();

			// 유저 정보 가져오기
			MypageDTO mypageInfo = ser.MemberInfo(request);

			// 유저 펫정보 가져오기
			MypageDTO mypagepetinfo = ser.MypetInfo(request);

			// request에 유저 정보 있는 memberInfo 저장
			request.setAttribute("mypageInfo", mypageInfo);

			// request에 유저 정보 있는 memberInfo 저장
			request.setAttribute("mypagepetInfo", mypagepetinfo);

			List<MypageDTO> mypetList = ser.getMypetList(request);
			request.setAttribute("mypetList", mypetList);
			
			List<OrderReservationDTO> reservationList = ser.getReservationList(request);
			request.setAttribute("reservationList", reservationList);
			
			if (mypageInfo != null) {
				webForward(request, response, "mypage", "mypage");
			} else {
				System.out.println("세션만료됨 에러발생");
			}
		}

		if (sPath.equals("/insertmypet.my")) {
			System.out.println("뽑은 가상주소 비교 : /insertmypet.my");
			webForward(request, response, "mypage", "insertmypet");
		}

		if (sPath.equals("/insertmypetPro.my")) {
			System.out.println("뽑은 가상주소 비교 : /insertmypetPro.my");
			// 멤버서비스 객체생성
			ser = new MypageService();
			// 메서드호출(리퀘스트 값 넘겨주기)
			MypageDTO mpyagedto = ser.insertMypet(request);

			if (mpyagedto != null) {
				System.out.println("펫 등록 성공");
				// 세션초기화
				// HttpSession session = request.getSession();
				// session.invalidate();
			} else {
				System.out.println("펫 등록 실패");
				// 세션초기화
				// HttpSession session = request.getSession();
				// session.invalidate();
			}
			// 마이페이지 화면이동
			response.sendRedirect("mypage.my");

		} //

		if (sPath.equals("/updatemypet.my")) {
			System.out.println("뽑은 가상주소 비교 : /updatemypet.my");
			// 수정하기 전에 디비 나의 정보 조회(세션값 id) => jsp 화면 출력
			// 세션 객체생성
			HttpSession session = request.getSession();

			int num = Integer.parseInt((String) request.getSession().getAttribute("num"));
			// MypageSerive 객체생성
			ser = new MypageService();
			// 유저 정보 가져오기
			MypageDTO mypageInfo = ser.MemberInfo(request);

			// 유저 펫정보 가져오기
			MypageDTO mypagepetinfo = ser.MypetInfo(request);

			// request에 유저 정보 있는 memberInfo 저장
			request.setAttribute("mypageInfo", mypageInfo);

			// request에 유저 정보 있는 memberInfo 저장
			request.setAttribute("mypagepetInfo", mypagepetinfo);

			// MypageDTO mypageDTO = getMypetinfo(id) 메서드 호출
			MypageDTO mypageDTO = ser.getMypetinfo(num);
			// request에 memberDTO 저장 ("이름",값)
			request.setAttribute("mypageDTO", mypageDTO);
			// member/update.jsp 주소변경없이 이동

			// 수정한 부분입니다 ===================================================
			MypageDTO mypageDTOTest = ser.MypetTestInfo(request);
			request.setAttribute("mypageDTOTest", mypageDTOTest);
			webForward(request, response, "mypage", "updatemypet");
		} //

		if (sPath.equals("/updatemypetPro.my")) {
			System.out.println("뽑은 가상주소 비교 : /updatemypetPro.me");
			request.setCharacterEncoding("utf-8");
			// MypageService 객체생성
			ser = new MypageService();

			ser.updateMypet(request);

			response.sendRedirect("mypage.my");
		}

//	// updatemypet 페이지에서 펫정보삭제
//	if (sPath.equals("/deletemypet.my")) {
//	    System.out.println("뽑은 가상주소 비교 : /deletemypet.my");
//	    // MypageService 객체생성
//	    ser = new MypageService();
//	    // deleteMypet(request) 메서드 호출
//	    ser.deleteMypet(request);
//	    // 글목록 list.bo 주소 변경 되면서 이동
//	    response.sendRedirect("mypage.my");
//	}

		if (sPath.equals("/deletemypet.my")) {
			int pet_num = Integer.parseInt(request.getParameter("pet_num"));
			MypageService ser = new MypageService();
			ser.deleteMypet(pet_num);
			response.sendRedirect("mypage.my"); // 삭제 후 마이페이지로 이동
		}

		if (sPath.equals("/modifyinfo.my")) {
			
			 //유저 세션 검증
			 String id = (String)request.getSession().getAttribute("id");
			 
			 //세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			 if (id == null){
				 response.sendRedirect("login.me");
			 } 
			 
			System.out.println("뽑은 가상주소 비교 : /modifyinfo.my");

			// MypageSerive 객체생성
			ser = new MypageService();

			// request에 유저 정보 있는 memberInfo 저장
			MypageDTO mypageInfo = ser.MemberInfo(request);
			request.setAttribute("mypageInfo", mypageInfo);

			webForward(request, response, "mypage", "modifyinfo");
		}

		if (sPath.equals("/modifyinfopro.my")) {
			System.out.println("뽑은 가상주소 비교 : /modifyinfo.pro");
			request.setCharacterEncoding("utf-8");
			ser = new MypageService();
			ser.modifyinfo(request);
			response.sendRedirect("mypage.my");
		}
	
		if (sPath.equals("/resetpassword.my")) {
			
			 //유저 세션 검증
			 String id = (String)request.getSession().getAttribute("id");
			 
			 //세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			 if (id == null){
				 response.sendRedirect("login.me");
			 } 
			 
			webForward(request, response, "mypage", "resetpassword");
			
		}
		
		if (sPath.equals("/resetpasswordPro.my")) {
			
			MemberService memberService = new MemberService();
			
            MemberDTO memberDTO = memberService.userCheck(request);
            
            boolean result = (memberDTO != null) ? true : false;
            
            if(result) {
            	
            	webForward(request, response, "member", "resetpassword");
            	
            } else {
            	
            	response.sendRedirect("mapageError.er");
            }
			
		}
		
		if (sPath.equals("/withdraw.my")) {
			
			//유저 세션 검증
			String id = (String)request.getSession().getAttribute("id");
			 
			//세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null){
				 response.sendRedirect("login.me");
			} 
			
			webForward(request, response, "mypage", "withdraw");
			
		}
		
		if (sPath.equals("/withdrawPro.my")) {
			
			MemberService memberService = new MemberService();
			
            MemberDTO memberDTO = memberService.userCheck(request);
            
            boolean result = (memberDTO != null) ? true : false;
            
            if(result) {
            	
            	boolean isDisabled = memberService.userDisable(request);
            	
            	if(isDisabled) {
            		//세션초기화
        			HttpSession session = request.getSession();
        			session.invalidate();
            		System.out.println("회원 탈퇴 처리 완료");
            		response.sendRedirect("main.gr");
            	} else {
            		//세션초기화
        			HttpSession session = request.getSession();
        			session.invalidate();
            		System.out.println("회원 탈퇴 처리 실패");
            		response.sendRedirect("main.gr");
            	}
            	
            } else {           	
            	response.sendRedirect("passwordError.er");
            }           
			
		}
		
		if (sPath.equals("/changeRes.my")) {
			webForward(request, response, "mypage", "changeRes");
		}
		
		if (sPath.equals("/changeResPro.my")) {
			
			ser = new MypageService();
			
			boolean result = ser.changeRes(request);
			
			if(result) {
				JSForward.locationHref(response, "예약 일정 변경 처리가 완료되었습니다", "mypage.my");
			} else {
				JSForward.locationHref(response, "예약 일정 변경 처리에 실패했습니다", "mypage.my");
			}
		}
	}

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

}

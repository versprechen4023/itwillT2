package web.groom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.groom.dto.MemberDTO;
import web.groom.javascript.JSForward;
import web.groom.service.MemberService;

@WebServlet("*.me") // .me 멤버 어노테이션 매핑 선언
public class MemberController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sPath = request.getServletPath();

		// 페이지이동

		// 로그인페이지 member/login.jsp
		if (sPath.equals("/login.me")) {

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");

			// 세션에 id값이 있을경우 메인페이지로 이동
			if (id != null) {
				JSForward.locationHref(response, "이미 로그인 되어 있습니다", "main.gr");
			}

			webForward(request, response, "member", "login");
		} // end_of_login.me

		// 로그인 로직 수행
		if (sPath.equals("/loginPro.me")) {

			// 메서드호출 및 값 넘겨주기
			MemberDTO memberdto = new MemberService().userCheck(request);

			// 로그인 검증
			if (memberdto != null && memberdto.getU_disabled() != 1) {

				System.out.println("로그인성공");

				// 값이 존재하면 세션에 id저장
				HttpSession session = request.getSession();

				// 세션에 id, salt, role, userNum 값 저장
				session.setAttribute("id", memberdto.getU_Id());
				session.setAttribute("salt", memberdto.getU_Salt());
				session.setAttribute("role", memberdto.getU_Role());
				session.setAttribute("num", String.valueOf(memberdto.getU_Num()));

				// 세션저장완료후 메인으로 이동
				response.sendRedirect("main.gr");
			} else {
				JSForward.locationHref(response, "아이디 혹은 비밀번호가 일치하지 않습니다", "login.me");
			}
		} // end_of_loginPro.me

		// 회원가입 페이지 member/singup.jsp
		if (sPath.equals("/signup.me")) {

			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");

			// 세션에 id값이 있을경우 메인페이지로 이동
			if (id != null) {
				JSForward.locationHref(response, "이미 로그인 되어 있습니다", "main.gr");
			}

			webForward(request, response, "member", "signup");
		} // end_of_singup.me

		// 회원가입 로직 수행
		if (sPath.equals("/signupPro.me")) {

			// 메서드호출(리퀘스트 값 넘겨주기)
			MemberDTO memberdto = new MemberService().insertMember(request);

			if (memberdto != null) {
				System.out.println("회원가입 성공");
				// 세션초기화
				request.getSession().invalidate();
				JSForward.locationHref(response, "회원 가입이 완료되었습니다", "login.me");
			} else {
				System.out.println("회원가입 실패");
				// 세션초기화
				request.getSession().invalidate();
				JSForward.locationHref(response, "회원 가입에 문제가 발생했습니다", "main.gr");
			}

		} // end_of_singupPro.me
		
		// 아이디 찾기 페이지 member/findid.jsp
		if (sPath.equals("/findid.me")) {

			webForward(request, response, "member", "findid");
		} // end_of_findid.me
		
		// 아이디 찾기 로직 수행 및 아이디 찾기 결과 반환 페이지 member/findidrsult.jsp
		if (sPath.equals("/findidresult.me")) {

			// 메서드호출(리퀘스트 값 넘겨주기)
			MemberDTO memberDTO = new MemberService().findId(request);

			// 처리결과 확인
			if (memberDTO != null) {

				// DTO에 찾은 아이디 값이 있다면 memberDTO 저장 후 result페이지로 이동
				request.setAttribute("memberDTO", memberDTO);
				webForward(request, response, "member", "findidresult");
				System.out.println("아이디 찾기 성공 ");

			} else {
				// 회원 정보 없는 경우 login.me로 이동
				JSForward.locationHref(response, "등록된 회원 정보가 없습니다", "login.me");
				System.out.println("아이디 찾기 실패 ");
			}

		} // end_of_findidresult.me
		
		// 비밀번호 찾기 페이지 member/findid.jsp
		if (sPath.equals("/findpass.me")) {
			webForward(request, response, "member", "findpass");
		} // end_of_findpass.me
		
		// 비밀번호 재설정전 리퀘스트 값(회원정보 존재유무) 검증 로직
		if (sPath.equals("/findpassresult.me")) {

			// 메서드호출(리퀘스트 값 넘겨주기)
			MemberDTO memberDTO = new MemberService().findPass(request);

			if (memberDTO != null) {

				// 값이 존재하면 세션에 유저 번호 저장후 비밀번호 변경 페이지 이동
				request.getSession().setAttribute("num", String.valueOf(memberDTO.getU_Num()));

				webForward(request, response, "member", "resetpassword");

				System.out.println("비밀번호 재설정가능");
				
			} else {
				// 회원정보 없는 경우 login.me로 이동
				JSForward.locationHref(response, "등록된 회원 정보가 없습니다", "login.me");
				System.out.println("비밀번호 재설정안됨");
			}

		} // end_of_findpassresult.me
		
		// 비밀번호 재설정 페이지 member/resetpassword.jsp
		if (sPath.equals("/resetpassword.me")) {

			// 메서드호출(리퀘스트 값 넘겨주기)
			MemberDTO memberDTO = new MemberService().resetPass(request);

			// 결과값 확인
			if (memberDTO != null) {

				// 세션 초기화
				request.getSession().invalidate();

				// 로그인창으로 보내기위한 메세지 출력
				JSForward.locationHref(response, "비밀번호가 재설정 되었습니다.", "login.me");

				System.out.println("비밀번호 재설정됨");
			} else {

				System.out.println("비밀번호 재설정안됨");
				// 에러발생시 메시지 출력
				JSForward.locationHref(response, "비밀번호가 재설정 되지 않았습니다", "login.me");
			}

		} // end_of_resetpassword.me

		// 로그아웃 로직 수행
		if (sPath.equals("/logout.me")) {
			// 로그아웃을 위한 세션 초기화
			request.getSession().invalidate();
			// 메인으로 이동
			JSForward.locationHref(response, "로그아웃 되었습니다", "main.gr");
		} // end_of_logout.me

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

} //end_of_MemberController

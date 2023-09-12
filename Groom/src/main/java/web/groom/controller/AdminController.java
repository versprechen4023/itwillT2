package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.javascript.JSForward;
import web.groom.service.AdminService;

@WebServlet("*.ad") // .ad 관리자페이지 어노테이션 매핑 선언
public class AdminController extends HttpServlet {

//	RequestDispatcher dispatcher = null; 
	AdminService adminService = null;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess");
		String sPath = request.getServletPath();

//		if (sPath.equals("/admin.ad")) {
//			 webForward(request, response, "admin", "admin_page");
//	     }// 삭제예정, /admin/admin_page.jsp도 삭제예정

		// 유저 정보 관리 관련 페이지 admin/admin_userCheck.jsp
		if (sPath.equals("/admin_userCheck.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				System.out.println("admin_userCheck.ad");

				// 멤버리스트를 가져오기위한 메서드 호출
				List<MemberDTO> memberList = new AdminService().getMemberList();
				// 리퀘스트에 멤버리스트를 저장후 페이지이동
				request.setAttribute("memberList", memberList);

				webForward(request, response, "admin", "admin_userCheck");
			}
		} // admin_userCheck.ad [회원목록]

		// 유저 예약 관리 관련 페이지 admin/admin_resCheck.jsp
		if (sPath.equals("/admin_resCheck.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				System.out.println("admin_resCheck.ad");
				// 예약 리스트를 가져오기위한 메서드 호출
				List<OrderReservationDTO> reservationList = new AdminService().getReservationList();
				// 리퀘스트에 예약리스트를 저장후 페이지이동
				request.setAttribute("reservationList", reservationList);

				webForward(request, response, "admin", "admin_resCheck");
			}
		} // admin_resCheck.ad [예약내역]

		// 유저 직원 관리 관련 페이지 admin/admin_storeCheck.jsp
		if (sPath.equals("/admin_storeCheck.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				System.out.println("admin_storeCheck.ad");
				// 직원 리스트를 가져오기위한 메서드 호출
				List<AdminDTO> empList = new AdminService().getEmpList();
				// 리퀘스트에 직원리스트를 저장후 페이지이동
				request.setAttribute("empList", empList);

				webForward(request, response, "admin", "admin_storeCheck");
			}
		} // admin_storeCheck.ad [직원목록]

		// 관리자 메인 관련 페이지 admin/admin_main.jsp
		if (sPath.equals("/admin_main.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				System.out.println("admin_main.ad");

				// 예약, 리뷰, 회원수등을 가져오기위한 메서드 호출
				AdminDTO adminDTO = new AdminService().getCount();
				// 리퀘스트에 카운트를 저장후 페이지이동
				request.setAttribute("adminDTO", adminDTO);

				// 토탈 출력용
//					adminDTO.getTotal_res();
//					System.out.println(adminDTO.getTotal_res());

				webForward(request, response, "admin", "admin_main");
			}
		} // admin_main.ad [관리자메인]

		// 여기서부터 추가했음
		// ===================================================================================

		// 관리자 가게 휴무일 설정 페이지 admin/admin_daydate.jsp
		if (sPath.equals("/storeDisDay.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				webForward(request, response, "admin", "admin_daydate");
			}
		} // storeDisDay.ad [가게 휴무일 설정]

		// 관리자 가게 휴무일 설정 매핑
		if (sPath.equals("/admin_disday.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 휴무일 업데이트를 위한 서비스에 리퀘스트 전달
				boolean result = new AdminService().insertDisDay(request);
				if (result) {
					System.out.println("휴무일 업데이트 성공");
					JSForward.windowClose(response, "휴무일 입력 완료."); // 창닫기 라인
//				response.getWriter().println("<script>window.close();</script>");
				} else {
					System.out.println("휴무일 업데이트 실패");
				}
			}
		} // admin_disday.ad [가게 휴무일 설정 로직]

		// 관리자 직원 휴무 시간 설정 페이지 admin/admin_daytime.jsp
		if (sPath.equals("/empDisTime.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 로그인 페이지로 이동
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				webForward(request, response, "admin", "admin_daytime");
			}
		} // empDisTime.ad [직원 휴무 시간]
		
		// 관리자 직원 휴무 시간 설정 매핑
		if (sPath.equals("/admin_distime.ad")) {
			
			// 휴무일 업데이트를 위한 서비스에 리퀘스트 전달
			boolean result = new AdminService().insertDisDayTime(request);
			if (result) {
				System.out.println("휴무시간 업데이트 성공");
				JSForward.windowClose(response, "휴무시간 입력 완료."); // 창닫기 라인
			} else {
				System.out.println("휴무시간 업데이트 실패");
			}
		} // admin_distime.ad [직원 휴무 시간 설정 로직]
		
		// 관리자 직원 휴무일 설정 페이지 admin/admin_disdayEmp.jsp
		if (sPath.equals("/empDisDay.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
			webForward(request, response, "admin", "admin_disdayEmp");
			}
		}// empDisDay.ad [직원 휴무일]
		
		// 관리자 직원 휴무일 설정 매핑
		if (sPath.equals("/admin_disdayEmp.ad")) {
		
			// 휴무일 업데이트를 위한 서비스에 리퀘스트 전달
			boolean result = new AdminService().insertDisDayEmp(request);
			if (result) {
				System.out.println("휴무시간 업데이트 성공");
				JSForward.windowClose(response, "휴무일 입력 완료."); // 창닫기 라인
			} else {
				System.out.println("휴무시간 업데이트 실패");
			}
		} // admin_disdayEmp.ad [직원 휴무일 설정 로직]
		
		// 관리자 가게 휴무일 확인 페이지 admin/check_StoreDisdays.jsp
		if (sPath.equals("/check_StoreDisdays.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 가게 휴무일 리스트를 가져오기 위한 메서드 호출
				List<AdminDTO> storeDisDaysList = new AdminService().getStoreDisDaysList();
				// 리퀘스트에 가게 휴무일 리스트를 저장후 페이지이동
				request.setAttribute("storeDisDaysList", storeDisDaysList);
				System.out.println("check_StoreDisdays.ad");

				webForward(request, response, "admin", "check_StoreDisdays");
			}
		} // check_StoreDisdays.ad [매장 휴무일 목록]
		
		// 관리자 직원 휴무일 확인 페이지 admin/check_EmpDisdays.jsp
		if (sPath.equals("/check_EmpDisdays.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 직원 휴무일 리스트를 가져오기 위한 메서드 호출
				List<AdminDTO> empDisDaysList = new AdminService().getEmpDisDaysList();
				// 리퀘스트에 직원 휴무일 리스트를 저장후 페이지이동
				request.setAttribute("empDisDaysList", empDisDaysList);
				System.out.println("check_EmpDisdays.ad");

				webForward(request, response, "admin", "check_EmpDisdays");
			}
		} // check_StoreDisdays.ad [직원 휴무일 목록]
		
		// 관리자 직원 휴무일 확인 페이지 admin/check_EmpDistime.jsp
		if (sPath.equals("/check_EmpDistime.ad")) {
			// 유저 세션 검증
			String id = (String) request.getSession().getAttribute("id");
			String role = (String) request.getSession().getAttribute("role");
			// 세션에 id값이 존재하지않거나 role이 admin이 아닐 경우 메인으로
			if (id == null || !role.equals("admin")) {
				JSForward.locationHref(response, "비정상적인 접근입니다.", "main.gr");
			} else {
				// 직원 휴무 시간 리스트를 가져오기 위한 메서드 호출
				List<AdminDTO> empDisTimeList = new AdminService().getEmpDisTimeList();
				// 리퀘스트에 직원 휴무 시간 리스트를 저장후 페이지이동
				request.setAttribute("empDisTimeList", empDisTimeList);
				System.out.println("check_EmpDistime.ad");

				webForward(request, response, "admin", "check_EmpDistime");
			}
		} // check_StoreDisdays.ad [직원 쉬는시간 목록]

	}// doProcess

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		doProcess(request, response);
	}

	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName)
			throws ServletException, IOException {
		request.getRequestDispatcher("/" + folder + "/" + pageName + ".jsp").forward(request, response);
	}
}// class

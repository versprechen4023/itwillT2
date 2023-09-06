package web.groom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.service.AdminService;

@WebServlet("*.ad") //.ad 관리자페이지 어노테이션 매핑 선언
public class AdminController extends HttpServlet {
	
//	RequestDispatcher dispatcher = null; 
	AdminService adminService = null;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doProcess");
		String sPath = request.getServletPath();
		
		
		if (sPath.equals("/admin.ad")) {
			 webForward(request, response, "admin", "admin_page");
	     }// 삭제예정, /admin/admin_page.jsp도 삭제예정
		
		if (sPath.equals("/admin_userCheck.ad")) {
			System.out.println("admin_userCheck.ad");
			adminService = new AdminService();
			List<MemberDTO> memberList = adminService.getMemberList();
			request.setAttribute("memberList", memberList);
			
			webForward(request, response, "admin", "admin_userCheck");
	     }// admin_userCheck.ad [회원목록]
		
		if (sPath.equals("/admin_resCheck.ad")) {
			System.out.println("admin_resCheck.ad");
			adminService = new AdminService();
			List<OrderReservationDTO> reservationList = adminService.getReservationList();
			request.setAttribute("reservationList", reservationList);
			webForward(request, response, "admin", "admin_resCheck");
	     }// admin_resCheck.ad [예약내역]
		
		if (sPath.equals("/admin_storeCheck.ad")) {
			System.out.println("admin_storeCheck.ad");
			adminService = new AdminService();
			List<AdminDTO> empList = adminService.getEmpList();
			request.setAttribute("empList", empList);
			
			webForward(request, response, "admin", "admin_storeCheck");
	     }// admin_storeCheck.ad [직원목록]
		
		if (sPath.equals("/admin_main.ad")) {
			System.out.println("admin_main.ad");
			AdminService adminService = new AdminService();
			AdminDTO adminDTO = adminService.getCount();
			request.setAttribute("adminDTO", adminDTO);
			adminDTO.getTotal_res();
			System.out.println(adminDTO.getTotal_res());
			
			 webForward(request, response, "admin", "admin_main");
	     }// admin_main.ad [관리자메인]
		
		// 여기서부터 추가했음 ===================================================================================
		
		if (sPath.equals("/storeDisDay.ad")) {
			 webForward(request, response, "admin", "admin_daydate");
	    }
		
		if (sPath.equals("/admin_disday.ad")) {
			// 어드민 서비스 객체생성
			AdminService adminService = new AdminService();
			// 휴무일 업데이트를 위한 서비스에 리퀘스트 전달
			boolean result = adminService.insertDisDay(request);
			if(result) {
				System.out.println("휴무일 업데이트 성공");
			} else {
				System.out.println("휴무일 업데이트 실패");
			}
	    }
		
		if (sPath.equals("/empDisTime.ad")) {
			 webForward(request, response, "admin", "admin_daytime");
	    }//
		
		if (sPath.equals("/admin_distime.ad")) {
			// 어드민 서비스 객체생성
			AdminService adminService = new AdminService();
			// 휴무일 업데이트를 위한 서비스에 리퀘스트 전달
			boolean result = adminService.insertDisDayTime(request);
			if(result) {
				System.out.println("휴무시간 업데이트 성공");
			} else {
				System.out.println("휴무시간 업데이트 실패");
			}
	    }//
		
		
		
		
		
		
		
		
		
		
		
		if (sPath.equals("/empDisDay.ad")) {
			 webForward(request, response, "admin", "admin_disdayEmp");
	    }//
		
		if (sPath.equals("/admin_disdayEmp.ad")) {
			// 어드민 서비스 객체생성
			AdminService adminService = new AdminService();
			// 휴무일 업데이트를 위한 서비스에 리퀘스트 전달
			boolean result = adminService.insertDisDayEmp(request);
			if(result) {
				System.out.println("휴무시간 업데이트 성공");
			} else {
				System.out.println("휴무시간 업데이트 실패");
			}
	    }//
		
	}// doProcess
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doProcess(request, response);
	}
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+pageName+".jsp").forward(request, response);
	}
}// class

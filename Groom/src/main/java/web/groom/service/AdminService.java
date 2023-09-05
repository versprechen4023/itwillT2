package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.AdminDAO;
import web.groom.dao.MemberDAO;
import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;
import web.groom.dto.OrderReservationDTO;

public class AdminService {
	AdminDAO adminDAO = null;

	public List<MemberDTO> getMemberList() {
		System.out.println("AdminService getMemberList()");
		List<MemberDTO> memberList = null;
		try {
			adminDAO = new AdminDAO();
			memberList = adminDAO.getMemberList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}//getMemberList() [회원정보 목록]
	
	public List<OrderReservationDTO> getReservationList() {
		System.out.println("AdminService getReservationList()");
		List<OrderReservationDTO> reservationList = null;
		try {
			adminDAO = new AdminDAO();
			reservationList = adminDAO.getReservationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservationList;
	}//getReservationList() [예약내역 목록]

	public List<AdminDTO> getEmpList() {
		System.out.println("AdminService getEmpList()");
		List<AdminDTO> empList = null;
		try {
			adminDAO = new AdminDAO();
			empList = adminDAO.getEmpList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}//getMemberList() [직원목록]
	
	public AdminDTO getCount() {
		System.out.println("AdminService getCount()");
		AdminDTO adminDTO = null;
		try {
			adminDAO = new AdminDAO();
			adminDTO = adminDAO.getCount();
			System.out.println("확인Ser"+adminDTO.getRes_a());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminDTO;
	}//getCount [관리자메인 count]
	
	// 여기서부터 추가했음 ===================================================================================
	public boolean insertDisDay(HttpServletRequest request) {
				
		boolean result = false;
		
		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			
			// 지점번호, 날짜 변수에 저장
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			String off_day = request.getParameter("datepicker");
			
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.insertDisDay(s_num, off_day);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}//
	
	public boolean statusComplete(HttpServletRequest request) {
		boolean result = false;
		try {
			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			// 지점번호, 날짜 변수에 저장
			int a = Integer.parseInt(request.getParameter("res_status")); 
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.statusComplete(a);
			if(result) {
				adminDAO.PointConfirm(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// statusComplete [예약상태 "완료"]

	public boolean statusCancel(HttpServletRequest request) {
		boolean result = false;
		try {
			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			// 지점번호, 날짜 변수에 저장
			int b = Integer.parseInt(request.getParameter("res_status")); 
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.statusCancle(b);
			if(result) {
				adminDAO.PointCancle(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// statusCancel [예약상태 "취소"]

	public boolean statusUnprocessed(HttpServletRequest request) {
		
		boolean result = false;
		try {
			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			// 지점번호, 날짜 변수에 저장
			int c = Integer.parseInt(request.getParameter("res_status")); 
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.statusUnprocessed(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// statusUnprocessed [예약상태 "진행중"]

	public boolean insertDisDayTime(HttpServletRequest request) {
		
		boolean result = false;
		
		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			
			// 지점번호, 직원번호, 시간, 날짜 변수에 저장
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			int emp_num = Integer.parseInt(request.getParameter("emp_num"));
			String dis_time = request.getParameter("timepicker");
			String dis_daydate = request.getParameter("datepicker");
			
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.insertDisDayTime(s_num, emp_num, dis_time, dis_daydate);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	
	

}// class

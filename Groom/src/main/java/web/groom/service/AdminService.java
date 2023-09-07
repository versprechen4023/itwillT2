package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.AdminDAO;
import web.groom.dao.MemberDAO;
import web.groom.dao.ReviewDAO;
import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.dto.ReviewDTO;

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
	
	public boolean pointStatusConfirm(HttpServletRequest request) {
		boolean result = false;
		try {
			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			// 지점번호, 날짜 변수에 저장
			int i = Integer.parseInt(request.getParameter("res_num_a")); 
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.pointStatus1(i);
			if(result) {
				adminDAO.PointConfirm(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// pointStatusConfirm [포인트지급상태 "지급완료"]

	public boolean pointStatusReturn(HttpServletRequest request) {
		boolean result = false;
		try {
			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			// 지점번호, 날짜 변수에 저장
			int i = Integer.parseInt(request.getParameter("res_num_b")); 
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.pointStatus2(i);
			if(result) {
				adminDAO.PointCancle(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// pointStatusConfirm [포인트지급상태 "미지급"]
	
	
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

	
	public boolean insertDisDayEmp(HttpServletRequest request) {
		boolean result = false;
		try {
			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			// 지점번호, 날짜 변수에 저장
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			int emp_num = Integer.parseInt(request.getParameter("emp_num"));
			String dis_day = request.getParameter("datepicker");
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = adminDAO.insertDisDayEmp(s_num, emp_num, dis_day);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//

	public List<AdminDTO> getEmpDisTimeList() {
		System.out.println("ReviewService getEmpDisTimeList()");
		List<AdminDTO> empDisTimeList = null;
		try {
			adminDAO = new AdminDAO();
			empDisTimeList = adminDAO.getEmpDisTimeList(); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDisTimeList;
	}// getEmpDisTimeList() [직원 쉬는시간 목록]

	public List<AdminDTO> getEmpDisDaysList() {
		System.out.println("ReviewService getEmpDisDaysList()");
		List<AdminDTO> empDisDaysList = null;
		try {
			adminDAO = new AdminDAO();
			empDisDaysList = adminDAO.getEmpDisDaysList(); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDisDaysList;
	}// getEmpDisDaysList() [직원 휴무일 목록]

	public List<AdminDTO> getStoreDisDaysList() {
		System.out.println("ReviewService getStoreDisDaysList()");
		List<AdminDTO> storeDisDaysList = null;
		try {
			adminDAO = new AdminDAO();
			storeDisDaysList = adminDAO.getStoreDisDaysList(); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storeDisDaysList;
	}// getStoreDisDaysList() [매장 휴무일 목록]

	public boolean del_EmpDisDays(HttpServletRequest request) {
		System.out.println("AdminService del_EmpDisDays()");
		boolean result = false;
		try {
			int dis_day_num = Integer.parseInt(request.getParameter("dis_day_num"));
			adminDAO = new AdminDAO();
			result = adminDAO.del_EmpDisDays(dis_day_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// del_EmpDisDays() [직원 휴무일 취소]

	public boolean del_EmpDisTime(HttpServletRequest request) {
		System.out.println("AdminService del_EmpDisTime()");
		boolean result = false;
		try {
			int dis_time_num = Integer.parseInt(request.getParameter("dis_time_num"));
			adminDAO = new AdminDAO();
			result = adminDAO.del_EmpDisTime(dis_time_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// del_EmpDisTime() [직원 쉬는시간 취소]

	public boolean del_StoreDisDays(HttpServletRequest request) {
		System.out.println("AdminService del_StoreDisDays()");
		boolean result = false;
		try {
			int off_num = Integer.parseInt(request.getParameter("off_num"));
			adminDAO = new AdminDAO();
			result = adminDAO.del_StoreDisDays(off_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// del_StoreDisDays() [매장 휴무일 취소]

	


}// class

package web.groom.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.AdminDAO;
import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;
import web.groom.dto.OrderReservationDTO;

public class AdminService {
	AdminDAO adminDAO = null;

	// 유저 리스트 정보 가져오는 서비스
	public List<MemberDTO> getMemberList() {
		System.out.println("AdminService getMemberList()");
		List<MemberDTO> memberList = null;
		try {
			// getMemberList() 메서드 호출
			memberList = new AdminDAO().getMemberList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}// getMemberList() [회원정보 목록]

	// 유저 에약 정보 가져오는 서비스
	public List<OrderReservationDTO> getReservationList() {
		System.out.println("AdminService getReservationList()");
		List<OrderReservationDTO> reservationList = null;
		try {
			// getReservationList() 메서드 호출
			reservationList = new AdminDAO().getReservationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservationList;
	}// getReservationList() [예약내역 목록]

	// 직원 정보 가져오는 서비스
	public List<AdminDTO> getEmpList() {
		System.out.println("AdminService getEmpList()");
		List<AdminDTO> empList = null;
		try {
			// getEmpList() 메서드 호출
			empList = new AdminDAO().getEmpList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}// getMemberList() [직원목록]

	// 예약, 리뷰, 회원수등을 가져오기위한 서비스
	public AdminDTO getCount() {
		System.out.println("AdminService getCount()");
		AdminDTO adminDTO = null;
		try {
			// getCount() 메서드 호출
			adminDTO = new AdminDAO().getCount();
			System.out.println("확인Ser" + adminDTO.getRes_a());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminDTO;
	}// getCount [관리자메인 count]

	// 예약 완료처리를 위한 서비스
	public boolean statusComplete(HttpServletRequest request) {
		boolean result = false;
		try {
			// 예약번호를 a 변수에 저장
			int a = Integer.parseInt(request.getParameter("res_status"));
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().statusComplete(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// statusComplete [예약상태 "완료"]

	// 예약 취소처리를 위한 서비스
	public boolean statusCancel(HttpServletRequest request) {
		boolean result = false;
		try {
			// 예약번호를 b 변수에 저장
			int b = Integer.parseInt(request.getParameter("res_status"));
			// adminDAO에 값을 전달하고 로직처리 수행
			adminDAO = new AdminDAO();
			result = new AdminDAO().statusCancle(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// statusCancel [예약상태 "취소"]

	// 예약중 처리를 위한 서비스
	public boolean statusUnprocessed(HttpServletRequest request) {

		boolean result = false;
		try {
			// 예약번호를 c 변수에 저장
			int c = Integer.parseInt(request.getParameter("res_status"));
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().statusUnprocessed(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// statusUnprocessed [예약상태 "진행중"]

	// 포인트 지급관련 지급 처리 서비스
	public boolean pointStatusConfirm(HttpServletRequest request) {
		boolean result = false;
		try {
			// 예약번호를 i 변수에 저장
			int i = Integer.parseInt(request.getParameter("res_num_a"));
			// AdminDAO 객체생성
			adminDAO = new AdminDAO();
			// adminDAO에 값을 전달하고 로직처리 수행
			result = adminDAO.pointStatus1(i);
			// 지급 처리 변경이 완료되었다면 포인트 지급 처리
			if (result) {
				adminDAO.PointConfirm(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// pointStatusConfirm [포인트지급상태 "지급완료"]

	// 포인트 지급관련 미지급 처리 서비스
	public boolean pointStatusReturn(HttpServletRequest request) {
		boolean result = false;
		try {
			// 예약번호를 i 변수에 저장
			int i = Integer.parseInt(request.getParameter("res_num_b"));
			// AdminDAO 객체생성
			adminDAO = new AdminDAO();
			// adminDAO에 값을 전달하고 로직처리 수행
			result = adminDAO.pointStatus2(i);
			// 미지급 처리 변경이 완료되었다면 포인트 회수 처리
			if (result) {
				adminDAO.PointCancle(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// pointStatusConfirm [포인트지급상태 "미지급"]

	// 여기서부터 추가했음
	// ===================================================================================
	// 매장 휴무일 추가 관련 서비스
	public boolean insertDisDay(HttpServletRequest request) {
		boolean result = false;
		try {
			// 매장번호, 날짜 변수에 저장
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			String off_day = request.getParameter("datepicker");
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().insertDisDay(s_num, off_day);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// insertDisDay
	
	// 직원 휴무시간 추가 관련 서비스
	public boolean insertDisDayTime(HttpServletRequest request) {
		boolean result = false;
		try {
			// 매장번호, 직원번호, 시간, 날짜 변수에 저장
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			int emp_num = Integer.parseInt(request.getParameter("emp_num"));
			String dis_time = request.getParameter("timepicker");
			String dis_daydate = request.getParameter("datepicker");
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().insertDisDayTime(s_num, emp_num, dis_time, dis_daydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // insertDisDayTime
	
	// 직원 휴무일 추가 관련 서비스
	public boolean insertDisDayEmp(HttpServletRequest request) {
		boolean result = false;
		try {
			// 매장번호, 날짜 변수에 저장
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			int emp_num = Integer.parseInt(request.getParameter("emp_num"));
			String dis_day = request.getParameter("datepicker");
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().insertDisDayEmp(s_num, emp_num, dis_day);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // insertDisDayEmp
	
	// 직원의 휴무시간 가져오는 서비스
	public List<AdminDTO> getEmpDisTimeList() {
		System.out.println("ReviewService getEmpDisTimeList()");
		List<AdminDTO> empDisTimeList = null;
		try {
			// adminDAO에 값을 전달하고 로직처리 수행
			empDisTimeList = new AdminDAO().getEmpDisTimeList(); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDisTimeList;
	}// getEmpDisTimeList() [직원 쉬는시간 목록]
	
	// 직원의 휴무일 가져오는 서비스
	public List<AdminDTO> getEmpDisDaysList() {
		System.out.println("ReviewService getEmpDisDaysList()");
		List<AdminDTO> empDisDaysList = null;
		try {
			// adminDAO에 값을 전달하고 로직처리 수행
			empDisDaysList = new AdminDAO().getEmpDisDaysList(); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDisDaysList;
	}// getEmpDisDaysList() [직원 휴무일 목록]
	
	// 매장의 휴무일 가져오는 서비스
	public List<AdminDTO> getStoreDisDaysList() {
		System.out.println("ReviewService getStoreDisDaysList()");
		List<AdminDTO> storeDisDaysList = null;
		try {
			// adminDAO에 값을 전달하고 로직처리 수행
			storeDisDaysList = new AdminDAO().getStoreDisDaysList(); // 프로덕트 이름 전달
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storeDisDaysList;
	}// getStoreDisDaysList() [매장 휴무일 목록]
	
	// 직원의 휴무일을 취소하는 서비스
	public boolean del_EmpDisDays(HttpServletRequest request) {
		System.out.println("AdminService del_EmpDisDays()");
		boolean result = false;
		try {
			// 리퀘스트로 부터 취소할 휴무일을 가져옴
			int dis_day_num = Integer.parseInt(request.getParameter("dis_day_num"));
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().del_EmpDisDays(dis_day_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// del_EmpDisDays() [직원 휴무일 취소]
	
	// 직원의 휴무시간을 취소하는 서비스
	public boolean del_EmpDisTime(HttpServletRequest request) {
		System.out.println("AdminService del_EmpDisTime()");
		boolean result = false;
		try {
			// 리퀘스트로 부터 취소할 휴무시간을 가져옴
			int dis_time_num = Integer.parseInt(request.getParameter("dis_time_num"));
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().del_EmpDisTime(dis_time_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// del_EmpDisTime() [직원 쉬는시간 취소]
	
	// 직원의 휴무일을 취소하는 서비스
	public boolean del_StoreDisDays(HttpServletRequest request) {
		System.out.println("AdminService del_StoreDisDays()");
		boolean result = false;
		try {
			// 리퀘스트로 부터 취소할 휴무일을 가져옴
			int off_num = Integer.parseInt(request.getParameter("off_num"));
			// adminDAO에 값을 전달하고 로직처리 수행
			result = new AdminDAO().del_StoreDisDays(off_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// del_StoreDisDays() [매장 휴무일 취소]

}// class

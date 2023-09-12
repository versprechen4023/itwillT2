package web.groom.service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.OrderDAO;
import web.groom.dto.OrderDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.dto.OrderServiceDTO;
import web.groom.dto.OrderinfoDTO;

public class OrderService {
	
	OrderDAO orderDAO = null;
	OrderReservationDTO orderReserv = null;
	OrderinfoDTO orderInfoDTO = null;
	
	// 예약가능일자 리스트 가져오는 서비스
	public List<OrderDTO> getServiceDate(HttpServletRequest request) {
		
		List<OrderDTO> serviceDate = null;
		
		// 변수에 리퀘스트 파라미터값 저장(매장번호,직원번호)
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));
		int emp_num = Integer.parseInt(request.getParameter("selectedManager"));
		
		try {
			// OrderDAO에 값을 전달하고 로직처리 수행
			serviceDate = new OrderDAO().getServiceDate(s_num, emp_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceDate;
	} // getServiceDate
	
	// 예약가능일자 리스트 가져오는 서비스(관리자 관련전용)
	public List<OrderDTO> getAdServiceDate(HttpServletRequest request) {
		
		List<OrderDTO> serviceDate = null;
		
		// 변수에 리퀘스트 파라미터값 저장(매장번호)
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));
		
		try {
			// OrderDAO에 값을 전달하고 로직처리 수행
			serviceDate = new OrderDAO().getServiceDate(s_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceDate;
	} // getAdServiceDate
	
	// 예약가능시간 리스트 가져오는 서비스
	public List<OrderDTO> getServiceTime(HttpServletRequest request) {

		List<OrderDTO> serviceTime = null;
		
		// 변수에 리퀘스트 파라미터값 저장(매장번호,직원번호,선택한날짜)
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));
		int emp_num = Integer.parseInt(request.getParameter("selectedManager"));
		String dis_daydate = (request.getParameter("selectedDate"));
		
		try {
			// OrderDAO에 값을 전달하고 로직처리 수행
			serviceTime = new OrderDAO().getServiceTime(s_num, emp_num, dis_daydate);
			
			//타임피커 비활성화를 위한 1분추가를 위한 시간계산
			for(OrderDTO orderDTO : serviceTime) {
				
				Time Time = orderDTO.getTime(); // 계산할 시간 얻기
			    long TimeInMillis = Time.getTime(); // 게산하기위해 Time을 밀리초로 변환
			    long addedTimeInMillis = TimeInMillis + (60 * 1000); // 1분(60초)를 밀리초로 변환하여 더해서 시간계산
			    Time addedTime = new Time(addedTimeInMillis); // 밀리초로 계산된 시간을 Time 객체로 변환

			    // addTime 업데이트
			    orderDTO.setAddTime(addedTime);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceTime;
	} // getServiceTime
	
	// 예약가능 서비스(상품) 리스트 가져오는 서비스
	public List<OrderServiceDTO> getServiceList(HttpServletRequest request) {
		
		List<OrderServiceDTO> serviceList = null;
		
		// 변수에 리퀘스트 파라미터값 저장(매장번호)
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));
		
		try {
			orderDAO = new OrderDAO();
			// 서비스의 시작 번호를 얻기위한 메서드 호출
			int startNum = orderDAO.getServiceStartNum(s_num);
			// OrderDAO에 값을 전달하고 로직처리 수행
			serviceList = orderDAO.getServiceList(s_num, startNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceList;
	} // getServiceList
	
	// 상품(무게) 관련 리스트 가져오는 서비스
	public List<OrderServiceDTO> getWeightList(HttpServletRequest request) {

		List<OrderServiceDTO> weightList = null;
		
		// 변수에 리퀘스트 파라미터값 저장(매장번호)
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));

		try {
			// OrderDAO에 값을 전달하고 로직처리 수행
			weightList = new OrderDAO().getWeightList(s_num);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return weightList;
	} // getWeightList
	
	// 직원 관련 리스트 가져오는 서비스
	public List<OrderServiceDTO> getManagerList(HttpServletRequest request) {

		List<OrderServiceDTO> managerList = null;
		
		// 변수에 리퀘스트 파라미터값 저장(매장번호)
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));

		try {
			// OrderDAO에 값을 전달하고 로직처리 수행
			managerList = new OrderDAO().getManagerList(s_num);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return managerList;
	} // getMangerList
	
	// 서비스(상품) 가격 관련 서비스
	public int getServicePrice(HttpServletRequest request) {
		
		// 가격계산을 위한 변수 선언 및 초기화
		int pro_price = 0; // 상품에 따른 요금
		int pet_extrafee = 0; // 펫 무게에 따른 추가요금
		int emp_extrafee = 0; // 직원에 따른 추가요금
		
		// 최종적으로 사용자가 선택한 서비스 번호(pro_id2의 목욕등)를 변수에 지정
		int pro_id2 = Integer.parseInt(request.getParameter("selectedPrice"));
		// 최종적으로 사용자가 선택한 서비스 번호(pro_id1의 견종)를 변수에 지정
		int pro_id1 = Integer.parseInt(request.getParameter("selectedPet"));
		// 최종적으로 사용자가 선택한 직원 번호(pro_id1의 견종)를 변수에 지정
		int emp_num = Integer.parseInt(request.getParameter("selectedManager"));
		// 최종적으로 사용자가 선택한 지점 번호(s_num)를 변수에 지정
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));
		
		try {
			
			orderDAO = new OrderDAO();
			// OrderDAO에 값을 전달하고 로직처리 수행
			pro_price = orderDAO.getServicePrice(pro_id2);
			pet_extrafee = orderDAO.getAddPrice(pro_id1, s_num);
			emp_extrafee = orderDAO.getAddFee(emp_num);
			
			//추가금액 포함 가격계산
			pro_price = pro_price+pet_extrafee+emp_extrafee;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pro_price;
	} // getServicePrice

	// myorderCheckout.jsp에서 예약 최종내역 표시 관련 서비스
	public OrderinfoDTO getOrderInfo(HttpServletRequest request) {
		
		try {
						
			// 변수에 리퀘스트 파라미터값 저장(매장번호,견종,서비스상품명1,2(상품명,무게),직원번호,최종금액,사용포인트)
			int s_num = Integer.parseInt(request.getParameter("storelist"));
			int pro_id1 = Integer.parseInt(request.getParameter("petlist"));
			int num1 = Integer.parseInt(request.getParameter("servicelist"));
			int num2 = Integer.parseInt(request.getParameter("weightlist"));
			int emp_num = Integer.parseInt(request.getParameter("managerlist"));
			int res_price = Integer.parseInt(request.getParameter("price"));
			int res_point = Integer.parseInt(request.getParameter("point"));
			
			// 변수에 리퀘스트 파라미터값 저장(이름, 전화번호, 예약시간, 예약일자, 회원 요구사항)
			String u_name = request.getParameter("name");
			String u_phone = request.getParameter("phone");
			String res_time = request.getParameter("timepicker");
			String res_day = request.getParameter("datepicker");
			String res_u_req = request.getParameter("res_u_req");
			
			// 상품번호 계산(상품종류값+무게값)-1
			int serviceNum = (num1+num2)-1;
			
			//오더 DTO에 값삽입
			orderInfoDTO = new OrderinfoDTO();
			orderInfoDTO.setS_num(s_num);
			orderInfoDTO.setPro_id1(pro_id1);
			orderInfoDTO.setServeiceNum(serviceNum);
			orderInfoDTO.setEmp_num(emp_num);
			
			orderInfoDTO.setU_name(u_name);
			orderInfoDTO.setU_phone(u_phone);
			orderInfoDTO.setRes_time(res_time);
			orderInfoDTO.setRes_day(res_day);
			orderInfoDTO.setRes_price(res_price);
			orderInfoDTO.setRes_u_req(res_u_req);
			orderInfoDTO.setRes_point(res_point);
			
			//DB에서 값 받아오기
			orderDAO = new OrderDAO();
			orderInfoDTO = orderDAO.getStoreName(orderInfoDTO);
			orderInfoDTO = orderDAO.getPetProName(orderInfoDTO);
			orderInfoDTO = orderDAO.getServiceName(orderInfoDTO);
			orderInfoDTO = orderDAO.getEmpName(orderInfoDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderInfoDTO;
	} // getOrderInfo
	
	// 예약 정보 저장 관련 서비스
	public OrderReservationDTO insertOrderReserv(HttpServletRequest request) {
		
		//유저 오더 정보 변수에 저장
		try {
			
			// 변수에 리퀘스트 파라미터값 저장(회원번호,서비스상품명1,2(견종,무게+상품번호),지점번호,직원번호,결제한가격,사용포인트)
			int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
			int pro_id1 = Integer.parseInt(request.getParameter("pro_id1"));
			int pro_id2 = Integer.parseInt(request.getParameter("pro_id2"));
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			int emp_num = Integer.parseInt(request.getParameter("emp_num"));
			int res_price = Integer.parseInt(request.getParameter("res_price"));
			int res_point = Integer.parseInt(request.getParameter("res_point"));
			int u_point = Integer.parseInt(request.getParameter("u_point"));//나중에 포인트 계산을 여기서 할려면 필요
			
			// 변수에 리퀘스트 파라미터값 저장(회원 요구사항, 결제수단, 예약시간, 예약일자)
			String res_u_req = request.getParameter("res_u_req");
			String res_method = request.getParameter("res_method");
			String res_time = request.getParameter("res_time");
			String res_day = request.getParameter("res_day");
			
			//오더 DTO에 값삽입
			orderReserv = new OrderReservationDTO();
			orderReserv.setU_num(u_num);
			orderReserv.setPro_id1(pro_id1);
			orderReserv.setPro_id2(pro_id2);
			orderReserv.setS_num(s_num);
			orderReserv.setEmp_num(emp_num);
			orderReserv.setRes_price(res_price);
			orderReserv.setRes_point(res_point);
			
			orderReserv.setRes_u_req(res_u_req);
			orderReserv.setRes_method(res_method);
			orderReserv.setRes_time(res_time);
			orderReserv.setRes_day(res_day);
			
			orderDAO = new OrderDAO();
			
			// 예약내역 값 삽입
			orderReserv = orderDAO.insertOrderReserv(orderReserv);
			// 포인트 처리
			if(orderReserv != null && res_point != 0) {
				orderReserv = orderDAO.useUserPoint(orderReserv);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderReserv;
	} // insertOrderReserv
	
	// 예약 정보 변경 가능 여부 반환 관련 서비스
	public boolean getCanChange(HttpServletRequest request) {
		
		boolean result = false;
		
		// 변수에 리퀘스트 파라미터값 저장(유저의 예약일자, 예약시간)
		String userDate = request.getParameter("userDate");
		String userTime = request.getParameter("userTime");
		
		// 현재 날짜 가져오는함수
        LocalDate currentDate = LocalDate.now();
        // 출력 형식 지정
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 현재 날짜를 지정된 형식으로 변수에 저장
        String nowDate = currentDate.format(dateFormat);
        
        // 날짜가 같다면 시간계산
        if(userDate.equals(nowDate)) {
        	
        	// 현재시간 가져오기
            LocalTime currentTime = LocalTime.now();
            // 출력 형식 지정
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            // 현재 시간을 지정된 형식으로 변수에 저장
            String nowTime = currentTime.format(timeFormat);
            
            // 시간 계산 수행을 위한 형변환
            LocalTime localUserTime = LocalTime.parse(userTime);
            
            LocalTime localNowTime = LocalTime.parse(nowTime);
            
            // 시간 차이 계산 수행
            Duration getTimeMath = Duration.between(localNowTime, localUserTime);
            
            // 시간 차이를 초로 변환
            long TimeResult = getTimeMath.getSeconds();

            if (TimeResult >= 2 * 60 * 60) {
            	// 2시간 이상의 여유가 있다면 예약 일정 변경 가능
                result = true;
            } else {
            	// 예약까지 2시간 미만이라면 예약 일정 변경 불가능
                result = false;
            }
        
        // 날짜가 같지 않다면 시간 계산 수행 필요없이 예약 변경 가능
        } else {
        	result = true;
        }
		
		return result;
	} // getCanChange

} // end_of_OrderService

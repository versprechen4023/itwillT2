package web.groom.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.OrderDAO;
import web.groom.dto.OrderDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.dto.OrderServiceDTO;
import web.groom.dto.OrderinfoDTO;

public class OrderService {
	
	OrderDAO orderDAO = null;
	
	public List<OrderDTO> getServiceDate(HttpServletRequest request) {
		
		List<OrderDTO> serviceDate = null;
		
		// 지점번호 받아오기
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));
		
		try {
			orderDAO = new OrderDAO();
			serviceDate = orderDAO.getServiceDate(s_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceDate;
	}

	public List<OrderDTO> getServiceTime(HttpServletRequest request) {

		List<OrderDTO> serviceTime = null;
		
		int s_num = Integer.parseInt(request.getParameter("selectedStore"));
		int emp_num = Integer.parseInt(request.getParameter("selectedManager"));
		String dis_date = (request.getParameter("selectedDate"));
		
		try {
			orderDAO = new OrderDAO();
			serviceTime = orderDAO.getServiceTime(s_num, emp_num, dis_date);
			
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
	}

	public List<OrderServiceDTO> getServiceList(HttpServletRequest request) {
		
		List<OrderServiceDTO> serviceList = null;
		
		int store_num = Integer.parseInt(request.getParameter("selectedStore"));
		
		try {
			orderDAO = new OrderDAO();
			int startNum = orderDAO.getServiceStartNum(store_num);
			serviceList = orderDAO.getServiceList(store_num, startNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceList;
	}
	
	public List<OrderServiceDTO> getWeightList(HttpServletRequest request) {

		List<OrderServiceDTO> weightList = null;

		int store = Integer.parseInt(request.getParameter("selectedStore"));

		try {
			orderDAO = new OrderDAO();
			weightList = orderDAO.getWeightList(store);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return weightList;
	}

	public List<OrderServiceDTO> getManagerList(HttpServletRequest request) {

		List<OrderServiceDTO> managerList = null;

		int store = Integer.parseInt(request.getParameter("selectedStore"));

		try {
			orderDAO = new OrderDAO();
			managerList = orderDAO.getManagerList(store);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return managerList;
	}

	public int getServicePrice(HttpServletRequest request) {
		
		int servicePrice = 0;
		int addPrice = 0;
		int addFee = 0;
		
		int servicenum = Integer.parseInt(request.getParameter("selectedPrice"));
		int servicepet = Integer.parseInt(request.getParameter("selectedPet"));
		int servicemanager = Integer.parseInt(request.getParameter("selectedManager"));
		
		try {
			orderDAO = new OrderDAO();
			
			servicePrice = orderDAO.getServicePrice(servicenum);
			addPrice = orderDAO.getAddPrice(servicepet);
			addFee = orderDAO.getAddFee(servicemanager);
			
			//추가금액 포함 가격계산
			servicePrice = servicePrice+addPrice+addFee;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return servicePrice;
	}

	public OrderinfoDTO getOrderInfo(HttpServletRequest request) {
		
		OrderinfoDTO orderInfoDTO = null;
		
		try {
			int s_num = Integer.parseInt(request.getParameter("storelist"));
			int p_num = Integer.parseInt(request.getParameter("petlist"));
			int num1 = Integer.parseInt(request.getParameter("servicelist"));
			int num2 = Integer.parseInt(request.getParameter("weightlist"));
			int emp_num = Integer.parseInt(request.getParameter("managerlist"));
			
			String u_name = request.getParameter("name");
			String u_phone = request.getParameter("phone");
			String res_day = request.getParameter("datepicker");
			String res_time = request.getParameter("timepicker");
			String res_price = request.getParameter("price");
			String res_u_req = request.getParameter("message");
			String res_point = request.getParameter("point");
			
			// 상품번호 계산(상품종류값+무게값)-1
			int serviceNum = (num1+num2)-1;
			
			//오더 DTO에 값삽입
			orderInfoDTO = new OrderinfoDTO();
			orderInfoDTO.setS_num(s_num);
			orderInfoDTO.setP_num(p_num);
			orderInfoDTO.setServeiceNum(serviceNum);
			orderInfoDTO.setEmp_num(emp_num);
			
			orderInfoDTO.setU_name(u_name);
			orderInfoDTO.setU_phone(u_phone);
			orderInfoDTO.setRes_day(res_day);
			orderInfoDTO.setRes_time(res_time);
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
	}

	public OrderReservationDTO insertOrderReserv(HttpServletRequest request) {
		
		OrderReservationDTO orderReserv = null;
		
		//유저 오더 정보 변수에 저장
		try {
			int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
			int pro_id1 = Integer.parseInt(request.getParameter("pro_id1"));
			int pro_id2 = Integer.parseInt(request.getParameter("pro_id2"));
			int s_num = Integer.parseInt(request.getParameter("s_num"));
			int emp_num = Integer.parseInt(request.getParameter("emp_num"));
			int res_price = Integer.parseInt(request.getParameter("res_price"));
			int res_point = Integer.parseInt(request.getParameter("res_point"));
			int u_point = Integer.parseInt(request.getParameter("u_point"));//나중에 포인트 계산을 여기서 할려면 필요
			
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
			
			System.out.println(orderReserv);
			
			orderDAO = new OrderDAO();
			
			// 포인트 사용이 있다면 포인트 계산 처리
			if(orderReserv.getRes_point() != 0) {
				orderReserv = orderDAO.updatePoint(orderReserv);
			}
			
			// 예약내역 값 삽입
			orderReserv = orderDAO.insertOrderReserv(orderReserv);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderReserv;
	}

}

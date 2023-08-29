package web.groom.service;

import java.sql.Time;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.OrderDAO;
import web.groom.dto.OrderDTO;
import web.groom.dto.OrderServiceDTO;

public class OrderService {
	
	OrderDAO orderDAO = null;
	
	public List<OrderDTO> getServiceDate(HttpServletRequest request) {
		
		List<OrderDTO> serviceDate = null;
		
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
		
		int store = Integer.parseInt(request.getParameter("selectedStore"));
		
		try {
			orderDAO = new OrderDAO();
			serviceList = orderDAO.getServiceList(store);
			
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
		
		System.out.println(servicenum);
		System.out.println(servicepet);
		System.out.println(servicemanager);
		
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

}

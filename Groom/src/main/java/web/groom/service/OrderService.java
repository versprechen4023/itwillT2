package web.groom.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.OrderDAO;
import web.groom.dto.OrderDTO;

public class OrderService {
	
	OrderDAO orderDAO = null;
	
	public List<OrderDTO> getServiceDate(HttpServletRequest request) {
		
		List<OrderDTO> serviceDate = null;
		
		String Value = request.getParameter("Value");
		
		try {
			orderDAO = new OrderDAO();
			serviceDate = orderDAO.getServiceDate(Value);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceDate;
	}

}

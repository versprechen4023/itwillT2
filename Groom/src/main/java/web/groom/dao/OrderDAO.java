package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.OrderDTO;
import web.groom.dto.OrderServiceDTO;
import web.groom.dto.OrderinfoDTO;

public class OrderDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;;
	ResultSet rs = null;
	OrderDTO orderDTO = null;
	
	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}

		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}

		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	
	public List<OrderDTO> getServiceDate(int s_num) {
		
		List<OrderDTO> serviceDate = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT dis_day FROM myDate where s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

			serviceDate = new ArrayList<>();

			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setDate(rs.getDate("dis_day"));

				serviceDate.add(orderDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return serviceDate;
	}

	public List<OrderDTO> getServiceTime(int s_num, int emp_num, String dis_date) {
		
		List<OrderDTO> serviceTime = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT dis_time FROM myTime WHERE s_num = ? and emp_num = ? and dis_date = ? ORDER BY dis_time";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			pstmt.setInt(2, emp_num);
			pstmt.setString(3, dis_date);
			rs = pstmt.executeQuery();

			serviceTime = new ArrayList<>();

			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setTime(rs.getTime("dis_time"));

				serviceTime.add(orderDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return serviceTime;
	}

	public List<OrderServiceDTO> getServiceList(int store) {
		
		List<OrderServiceDTO> serviceList = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT DISTINCT(s_name) FROM myService WHERE l_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store);
			rs = pstmt.executeQuery();

			serviceList = new ArrayList<>();
			
			int startingNumber = 1;
			int increment = 4;
			
			while(rs.next()) {
				OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
			    orderServiceDTO.setS_num(startingNumber);
				orderServiceDTO.setS_name(rs.getString("s_name"));
				
				serviceList.add(orderServiceDTO);
				
				startingNumber += increment;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return serviceList;
	}
	
	public List<OrderServiceDTO> getWeightList(int store) {

		List<OrderServiceDTO> weightList = null;

		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT DISTINCT(s_weight) FROM myService WHERE l_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store);
			rs = pstmt.executeQuery();

			weightList = new ArrayList<>();

			int startingNumber = 1;
			int increment = 1;

			while (rs.next()) {
				OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
				orderServiceDTO.setS_num(startingNumber);
				orderServiceDTO.setS_weight(rs.getString("s_weight"));

				weightList.add(orderServiceDTO);

				startingNumber += increment;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return weightList;
	}

	public List<OrderServiceDTO> getManagerList(int store) {

		List<OrderServiceDTO> serviceList = null;

		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT emp_num, emp_name, emp_grade FROM myEmployees WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store);
			rs = pstmt.executeQuery();

			serviceList = new ArrayList<>();

			while (rs.next()) {
				OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
				
				orderServiceDTO.setEmp_num(rs.getInt("emp_num"));
				orderServiceDTO.setEmp_name(rs.getString("emp_name"));
				orderServiceDTO.setEmp_grade(rs.getString("emp_grade"));
				
				serviceList.add(orderServiceDTO);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return serviceList;
	}

	public int getServicePrice(int p_num) {
		
		int ServicePrice = 0;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT s_price FROM myService WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				ServicePrice = rs.getInt("s_price");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return ServicePrice;
	}

	public int getAddPrice(int pet) {
		
		int addPrice = 0;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT p_addprice FROM myPrice WHERE p_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				addPrice = rs.getInt("p_addprice");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return addPrice;
	}
	
	public int getAddFee(int manager) {

		int addFee = 0;

		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT emp_extrafee FROM myEmployees WHERE emp_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, manager);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				addFee = rs.getInt("emp_extrafee");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return addFee;
	}

	public OrderinfoDTO getStoreName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT s_location FROM myStore WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderInfoDTO.getS_num());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderInfoDTO.setS_location(rs.getString("s_location"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return orderInfoDTO;
	}

	public OrderinfoDTO getPetProName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT p_dog FROM myPrice WHERE p_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderInfoDTO.getP_num());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderInfoDTO.setP_dog(rs.getString("p_dog"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return orderInfoDTO;
	}

	public OrderinfoDTO getServiceName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT s_name, s_weight FROM myService WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderInfoDTO.getServeiceNum());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderInfoDTO.setS_name(rs.getString("s_name"));
				orderInfoDTO.setS_weight(rs.getString("s_weight"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return orderInfoDTO;
	}

	public OrderinfoDTO getEmpName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT emp_grade, emp_name FROM myEmployees WHERE emp_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderInfoDTO.getEmp_num());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String grade = rs.getString("emp_grade");
				String name = rs.getString("emp_name");
				String fullName = grade + " " + name;
				orderInfoDTO.setEmp_name(fullName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return orderInfoDTO;
	}

}

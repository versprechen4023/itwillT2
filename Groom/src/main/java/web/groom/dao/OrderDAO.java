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
	
	public List<OrderDTO> getServiceDate(String value) {
		
		List<OrderDTO> serviceDate = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT date FROM myDate";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			serviceDate = new ArrayList<>();

			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setDate(rs.getDate("date"));

				serviceDate.add(orderDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return serviceDate;
	}

	public List<OrderDTO> getServiceTime(String date) {
		
		List<OrderDTO> serviceTime = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT time FROM myTime WHERE date = ? ORDER BY time";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();

			serviceTime = new ArrayList<>();

			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setTime(rs.getTime("time"));

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
			
			String sql = "SELECT * FROM myService WHERE l_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store);
			rs = pstmt.executeQuery();

			serviceList = new ArrayList<>();

			while(rs.next()) {
				OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
			    orderServiceDTO.setS_num(rs.getInt("s_num"));
				orderServiceDTO.setS_name(rs.getString("s_name"));

				serviceList.add(orderServiceDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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

}

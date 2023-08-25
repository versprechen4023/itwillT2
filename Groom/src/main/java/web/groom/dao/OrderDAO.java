package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.OrderDTO;

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
			
			String sql="SELECT * FROM myDate";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			serviceDate = new ArrayList<>();

			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setId(rs.getInt("id"));
				orderDTO.setDate(rs.getDate("date"));
				orderDTO.setTime(rs.getTime("time"));

				serviceDate.add(orderDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return serviceDate;
	}

}

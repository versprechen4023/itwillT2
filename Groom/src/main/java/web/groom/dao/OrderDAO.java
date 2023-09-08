package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.OrderDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.dto.OrderServiceDTO;
import web.groom.dto.OrderinfoDTO;

public class OrderDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;;
	ResultSet rs = null;
	OrderDTO orderDTO = null;
	OrderServiceDTO orderServiceDTO = null;
	
	// 데이트 피커 날짜 비활성화를 위한 메서드
	public List<OrderDTO> getServiceDate(int s_num, int emp_num) {
		
		List<OrderDTO> serviceDate = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT dis_day FROM disabled_days where s_num = ? and emp_num = ?\r\n"
					+ "UNION\r\n"
					+ "SELECT off_day FROM store_offdays WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			pstmt.setInt(2, emp_num);
			pstmt.setInt(3, s_num);
			rs = pstmt.executeQuery();

			serviceDate = new ArrayList<>();

			while(rs.next()) {
				orderDTO = new OrderDTO();
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
    
	// 데이트 피커 날짜 비활성화를 위한 메서드 [Admin 전용]
	public List<OrderDTO> getServiceDate(int s_num) {
		
		List<OrderDTO> serviceDate = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT off_day FROM store_offdays WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

			serviceDate = new ArrayList<>();

			while(rs.next()) {
				orderDTO = new OrderDTO();
				orderDTO.setDate(rs.getDate("off_day"));

				serviceDate.add(orderDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return serviceDate;
	}
	
	// 타임 피커 날짜 비활성화를 위한 메서드
	public List<OrderDTO> getServiceTime(int s_num, int emp_num, String dis_daydate) {
		
		List<OrderDTO> serviceTime = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT dis_time FROM disabled_time WHERE s_num = ? and emp_num = ? and dis_daydate = ?\r\n"
					+ "UNION\r\n"
					+ "SELECT res_time FROM reservation WHERE s_num = ? and emp_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			pstmt.setInt(2, emp_num);
			pstmt.setString(3, dis_daydate);
			pstmt.setInt(4, s_num);
			pstmt.setInt(5, emp_num);
			rs = pstmt.executeQuery();

			serviceTime = new ArrayList<>();

			while(rs.next()) {
				orderDTO = new OrderDTO();
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
	
	// 상품 번호를 얻기위한 상품 시작 번호를 얻기위한 메서드
	public int getServiceStartNum(int s_num) {
		
		int startNum = 0;
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT MIN(pro_id2) as pro_id2 FROM product2 WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				startNum = rs.getInt("pro_id2");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return startNum;
	}
	
	// 서비스 목록을 얻기 위한 메서드(목욕, 부분목욕등)
	public List<OrderServiceDTO> getServiceList(int s_num, int startNum) {
		
		List<OrderServiceDTO> serviceList = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT DISTINCT(pro_name) FROM product2 WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

			serviceList = new ArrayList<>();
			
			int startingNumber = startNum;
			int increment = 4;
			
			while(rs.next()) {
				orderServiceDTO = new OrderServiceDTO();
			    orderServiceDTO.setS_num(startingNumber);
				orderServiceDTO.setPro_name(rs.getString("pro_name"));
				
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
	
	// 서비스 목록을 얻기 위한 메서드(무게관련)
	public List<OrderServiceDTO> getWeightList(int s_num) {

		List<OrderServiceDTO> weightList = null;

		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT DISTINCT(pet_weight) FROM product2 WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

			weightList = new ArrayList<>();

			int startingNumber = 1;
			int increment = 1;

			while (rs.next()) {
				orderServiceDTO = new OrderServiceDTO();
				orderServiceDTO.setS_num(startingNumber);
				orderServiceDTO.setPet_weight(rs.getString("pet_weight"));

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
	
	// 직원 목록을 얻기 위한 메서드
	public List<OrderServiceDTO> getManagerList(int s_num) {

		List<OrderServiceDTO> serviceList = null;

		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT emp_num, emp_name, emp_grade, emp_extrafee FROM employees WHERE s_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

			serviceList = new ArrayList<>();

			while (rs.next()) {
				orderServiceDTO = new OrderServiceDTO();
				
				orderServiceDTO.setEmp_num(rs.getInt("emp_num"));
				orderServiceDTO.setEmp_name(rs.getString("emp_name"));
				orderServiceDTO.setEmp_grade(rs.getString("emp_grade"));
				orderServiceDTO.setEmp_extrafee(rs.getInt("emp_extrafee"));
				serviceList.add(orderServiceDTO);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return serviceList;
	}
	
	// 가격을 계산하기 위한 메서드
	public int getServicePrice(int pro_id2) {
		
		int pro_price = 0;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT pro_price FROM product2 WHERE pro_id2 = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_id2);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				pro_price = rs.getInt("pro_price");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return pro_price;
	}
	
	// 견종에 따른 추가 금액을 얻기 위한 메서드
	public int getAddPrice(int pro_id1, int s_num) {
		
		int pet_extrafee = 0;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT pet_extrafee FROM product1 WHERE pro_id1 = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_id1);
//			pstmt.setInt(2, s_num); 현재 지점 별로 견종이 다르지 않음
			rs = pstmt.executeQuery();

			if(rs.next()) {
				pet_extrafee = rs.getInt("pet_extrafee");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return pet_extrafee;
	}
	
	// 직원에 따른 추가 금액을 얻기 위한 메서드
	public int getAddFee(int emp_num) {

		int emp_extrafee = 0;

		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT emp_extrafee FROM employees WHERE emp_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				emp_extrafee = rs.getInt("emp_extrafee");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return emp_extrafee;
	}
	
	// 지점명을 얻기 위한 메서드
	public OrderinfoDTO getStoreName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT s_location FROM store WHERE s_num = ?";
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
	
	// 견종을 얻기 위한 메서드
	public OrderinfoDTO getPetProName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT pet_size FROM product1 WHERE pro_id1 = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderInfoDTO.getPro_id1());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderInfoDTO.setPet_size(rs.getString("pet_size"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return orderInfoDTO;
	}
	
	// 상품명, 무게 얻기 위한 메서드
	public OrderinfoDTO getServiceName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT pro_name, pet_weight FROM product2 WHERE pro_id2 = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderInfoDTO.getServeiceNum());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderInfoDTO.setPro_name(rs.getString("pro_name"));
				orderInfoDTO.setPet_weight(rs.getString("pet_weight"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return orderInfoDTO;
	}
	// 직원이름 얻기 위한 메서드
	public OrderinfoDTO getEmpName(OrderinfoDTO orderInfoDTO) {
		
		try {

			con = new SQLConnection().getConnection();

			String sql = "SELECT emp_grade, emp_name FROM employees WHERE emp_num = ?";
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

	public OrderReservationDTO insertOrderReserv(OrderReservationDTO orderReserv) {
		try {

			// db연결
			con = new SQLConnection().getConnection();

			// SQL 쿼리 실행(예약 내역 값 삽입)
			String SQL = "INSERT INTO reservation(u_num, pro_id1, pro_id2, s_num, emp_num, res_u_req, res_method, res_status, res_time, res_day, res_price, res_point, res_point_status) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, orderReserv.getU_num());
			pstmt.setInt(2, orderReserv.getPro_id1());
			pstmt.setInt(3, orderReserv.getPro_id2());
			pstmt.setInt(4, orderReserv.getS_num());
			pstmt.setInt(5, orderReserv.getEmp_num());
			pstmt.setString(6, orderReserv.getRes_u_req());
			pstmt.setString(7, orderReserv.getRes_method());
			pstmt.setInt(8, 0);
			pstmt.setString(9, orderReserv.getRes_time());
			pstmt.setString(10, orderReserv.getRes_day());
			pstmt.setInt(11, orderReserv.getRes_price());
			pstmt.setInt(12, orderReserv.getRes_point());
			pstmt.setInt(13, 0);
			int rs = pstmt.executeUpdate();

			if (rs != 0) {
				System.out.println("예약정보 저장완료");
			} else {
				System.out.println("예약정보 저장실패");
				orderReserv = null;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}

		return orderReserv;

	}
	
	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}

		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}

		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}

}

package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;
import web.groom.dto.OrderReservationDTO;

public class AdminDAO {
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs =null;
	
	public void dbClose() {
		if(rs != null) {try {rs.close();} catch (SQLException e) {	}}			
		if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {	}}
		if(con != null) {try {con.close();} catch (SQLException e) {	}}
	}

	public List<MemberDTO> getMemberList() {
		System.out.println("AdminDAO getMemberList()");
		String sql = "SELECT a.u_num, a.u_id, a.u_role, b.u_name, b.u_phone,"
				+ "          b.u_email, b.u_regdate, b.u_count, b.u_point"
				+ "   FROM user a JOIN user2 b"
				+ "   ON a.u_num = b.u_num"
				+ "   ORDER BY a.u_num desc";
		//일단 전체회원, 필요하면 where 넣기
		List<MemberDTO> memberList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<>();
			while(rs.next()){
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setU_Num(rs.getInt("u_num"));
				memberDTO.setU_Role(rs.getString("u_role"));
				memberDTO.setU_Id(rs.getString("u_id"));
				memberDTO.setU_Name(rs.getString("u_name"));
				memberDTO.setU_Phone(rs.getString("u_phone"));
				memberDTO.setU_Email(rs.getString("u_email"));
				memberDTO.setU_RegDate(rs.getTimestamp("u_regdate"));
				memberDTO.setU_Count(rs.getInt("u_count"));
				memberDTO.setU_Point(rs.getInt("u_point"));
				memberList.add(memberDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return memberList;
	}//getMemberList() [회원정보 목록]
	
	public List<OrderReservationDTO> getReservationList() {
		System.out.println("AdminDAO getReservationList()");
		String sql = "select a.res_num, a.res_day, a.res_time, b.pro_name, c.pet_size, b.pet_weight,"
				+ "          d.s_location, e.emp_grade, e.emp_name, f.u_name, f.u_phone, a.res_point,"
				+ "          a.res_price, a.res_status, a.res_method"
				+ "   from test_reservation a"
				+ "   join product2 b on a.pro_id2 = b.pro_id2"
				+ "   join product1 c on a.pro_id1 = c.pro_id1"
				+ "   join store d on a.s_num = d.s_num"
				+ "   join employees e on a.emp_num = e.emp_num"
				+ "   join user2 f on a.u_num = f.u_num"
				+ "   order by a.res_num desc;";
		List<OrderReservationDTO> reservationList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			reservationList = new ArrayList<>();
			while(rs.next()){
				OrderReservationDTO orderReservationDTO = new OrderReservationDTO();
				orderReservationDTO.setRes_num(rs.getInt("res_num"));
				orderReservationDTO.setRes_day(rs.getString("res_day"));
				orderReservationDTO.setRes_time(rs.getString("res_time"));
				orderReservationDTO.setPro_name(rs.getString("pro_name"));
				orderReservationDTO.setPet_size(rs.getString("pet_size"));
				orderReservationDTO.setPet_weight(rs.getString("pet_weight"));
				orderReservationDTO.setS_location(rs.getString("s_location"));
				orderReservationDTO.setEmp_grade(rs.getString("emp_grade"));
				orderReservationDTO.setEmp_name(rs.getString("emp_name"));
				orderReservationDTO.setU_name(rs.getString("u_name"));
				orderReservationDTO.setU_phone(rs.getString("u_phone"));
				orderReservationDTO.setRes_point(rs.getInt("res_point"));
				orderReservationDTO.setRes_price(rs.getInt("res_price"));
				orderReservationDTO.setRes_status(rs.getInt("res_status"));
				orderReservationDTO.setRes_method(rs.getString("res_method"));
				reservationList.add(orderReservationDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return reservationList;
	}//getReservationList() [예약내역 목록]

	public List<AdminDTO> getEmpList() {
		System.out.println("AdminDAO getEmpList()");
		String sql = "SELECT"
				+ "   ROW_NUMBER() OVER (PARTITION BY a.s_location ORDER BY b.s_num, b.emp_grade) AS number,"
				+ "   a.s_location, b.emp_grade, b.emp_name, b.emp_extrafee, b.emp_phone, b.emp_email, b.emp_date"
				+ "   FROM store a JOIN employees b ON a.s_num = b.s_num"
				+ "   ORDER BY a.s_location, b.s_num, b.emp_grade";
		List<AdminDTO> empList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			empList = new ArrayList<>();
			while(rs.next()){
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setNumber(rs.getInt("number"));
				adminDTO.setS_location(rs.getString("s_location"));
				adminDTO.setEmp_grade(rs.getString("emp_grade"));
				adminDTO.setEmp_name(rs.getString("emp_name"));
				adminDTO.setEmp_extrafee(rs.getInt("emp_extrafee"));
				adminDTO.setEmp_phone(rs.getString("emp_phone"));
				adminDTO.setEmp_email(rs.getString("emp_email"));
				adminDTO.setEmp_date(rs.getTimestamp("emp_date"));
				empList.add(adminDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return empList;
	}//getEmpList() [직원목록]
	
	public AdminDTO getCount() {
		AdminDTO adminDTO = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT"
					+ "    (SELECT COUNT(*) FROM user) AS total_user,"
					+ "    (SELECT COUNT(*) FROM review) AS total_rev,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE res_status = 0) AS total_res,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 1 AND res_status = 0) AS res_a,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 1 AND res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res_a,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 2 AND res_status = 0) AS res_b,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 2 AND res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res_b,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 3 AND res_status = 0) AS res_c,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 3 AND res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res_c;";			
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				adminDTO = new AdminDTO();
				
				adminDTO.setTotal_user(rs.getInt("total_user"));
				adminDTO.setTotal_rev(rs.getInt("total_rev"));
				adminDTO.setTotal_res(rs.getInt("total_res"));
				adminDTO.setToday_res(rs.getInt("today_res"));
				adminDTO.setRes_a(rs.getInt("res_a"));
				System.out.println("확인DAO"+adminDTO.getRes_a());
				adminDTO.setToday_res_a(rs.getInt("today_res_a"));
				adminDTO.setRes_b(rs.getInt("res_b"));
				adminDTO.setToday_res_b(rs.getInt("today_res_b"));
				adminDTO.setRes_c(rs.getInt("res_c"));
				adminDTO.setToday_res_c(rs.getInt("today_res_c"));
			} else {
				adminDTO = new AdminDTO();
				adminDTO.setTotal_user(0);
				adminDTO.setTotal_rev(0);
				adminDTO.setTotal_res(0);
				adminDTO.setToday_res(0);
				adminDTO.setRes_a(0);
				adminDTO.setToday_res_a(0);
				adminDTO.setRes_b(0);
				adminDTO.setToday_res_b(0);
				adminDTO.setRes_c(0);
				adminDTO.setToday_res_c(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return adminDTO;
	}//getCount() [관리자메인 count]
	
	
	
	
	
	// 여기서부터 추가했음 ===================================================================================
	public boolean insertDisDay(int s_num, String off_day) {
		
		boolean result = false;
		
		try {

			// db연결
			con = new SQLConnection().getConnection();

			// SQL 쿼리 실행(휴무날짜 내역 값 삽입)
			String SQL = "INSERT INTO store_offdays(s_num, off_day) VALUE(?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, s_num);
			pstmt.setString(2, off_day);
			
			int rs = pstmt.executeUpdate();
			
			// 성공시 true 실패시 false 반환
			result = (rs != 0) ? true : false;
			

		} catch (Exception e) {
			e.printStackTrace();
			result = false;

		} finally {
			dbClose();
		}

		return result;
	}//

	
	public boolean statusComplete(int a) {
		boolean result = false;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			// SQL 쿼리 실행(휴무날짜 내역 값 삽입)
			String SQL = "UPDATE test_reservation SET res_status = 1 where res_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, a);
			int rs = pstmt.executeUpdate();
			// 성공시 true 실패시 false 반환
			result = (rs != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			dbClose();
		}
		return result;
	}

	public boolean statusCancle(int b) {
		boolean result = false;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			// SQL 쿼리 실행(휴무날짜 내역 값 삽입)
			String SQL = "UPDATE test_reservation SET res_status = 2 where res_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, b);
			int rs = pstmt.executeUpdate();
			// 성공시 true 실패시 false 반환
			result = (rs != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			dbClose();
		}
		return result;
	}

	public boolean statusUnprocessed(int c) {
		boolean result = false;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			// SQL 쿼리 실행(휴무날짜 내역 값 삽입)
			String SQL = "UPDATE test_reservation SET res_status = 0 where res_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, c);
			int rs = pstmt.executeUpdate();
			// 성공시 true 실패시 false 반환
			result = (rs != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			dbClose();
		}
		return result;
	}

	public boolean insertDisDayTime(int s_num, int emp_num, String dis_time, String dis_daydate) {
		
		boolean result = false;
		
		try {

			// db연결
			con = new SQLConnection().getConnection();

			// SQL 쿼리 실행(휴무시간 내역 값 삽입)
			String SQL = "INSERT INTO disabled_time(s_num, emp_num, dis_time, dis_daydate) VALUE(?,?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, s_num);
			pstmt.setInt(2, emp_num);
			pstmt.setString(3, dis_time);
			pstmt.setString(4, dis_daydate);
			
			int rs = pstmt.executeUpdate();
			
			// 성공시 true 실패시 false 반환
			result = (rs != 0) ? true : false;
			

		} catch (Exception e) {
			e.printStackTrace();
			result = false;

		} finally {
			dbClose();
		}

		return result;
	}

	
	
	
	

}// class

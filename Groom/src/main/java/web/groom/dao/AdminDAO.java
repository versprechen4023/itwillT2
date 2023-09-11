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

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 유저 정보를 가져오기 위한 메서드
	public List<MemberDTO> getMemberList() {
		System.out.println("AdminDAO getMemberList()");
		String sql = "SELECT a.u_num, a.u_id, a.u_role, b.u_name, b.u_phone,"
				+ "          b.u_email, b.u_regdate, b.u_count, b.u_point" + "   FROM user a JOIN user2 b"
				+ "   ON a.u_num = b.u_num" + "   ORDER BY a.u_num desc";
		// 일단 전체회원, 필요하면 where 넣기
		List<MemberDTO> memberList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<>();
			while (rs.next()) {
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
		} finally {
			dbClose();
		}
		return memberList;
	}// getMemberList() [회원정보 목록]

	// 예약 정보를 가져오기 위한 메서드
	public List<OrderReservationDTO> getReservationList() {
		System.out.println("AdminDAO getReservationList()");
		String sql = "SELECT a.res_num, a.res_day, a.res_time, b.pro_name, c.pet_size, b.pet_weight,"
				+ "          d.s_location, e.emp_grade, e.emp_name, f.u_name, f.u_phone, a.res_point,"
				+ "          a.res_price, a.res_status, a.res_method, a.res_point_status" + "   FROM reservation a"
				+ "   JOIN product2 b on a.pro_id2 = b.pro_id2" + "   JOIN product1 c on a.pro_id1 = c.pro_id1"
				+ "   JOIN store d on a.s_num = d.s_num" + "   JOIN employees e on a.emp_num = e.emp_num"
				+ "   JOIN user2 f ON a.u_num = f.u_num" + "   ORDER BY a.res_num desc;";
		List<OrderReservationDTO> reservationList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			reservationList = new ArrayList<>();
			while (rs.next()) {
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
				orderReservationDTO.setRes_point_status(rs.getInt("res_point_status"));
				reservationList.add(orderReservationDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return reservationList;
	}// getReservationList() [예약내역 목록]

	// 직원 정보를 가져오기 위한 메서드
	public List<AdminDTO> getEmpList() {
		System.out.println("AdminDAO getEmpList()");
//		String sql = "SELECT"
//				+ "   ROW_NUMBER() OVER (PARTITION BY a.s_location ORDER BY b.s_num, b.emp_grade) AS number,"
//				+ "   a.s_num, a.s_location, b.emp_num, b.emp_grade, b.emp_name, b.emp_extrafee, b.emp_phone, b.emp_email, b.emp_date"
//				+ "   FROM store a JOIN employees b ON a.s_num = b.s_num"
//				+ "   ORDER BY a.s_location, b.s_num, b.emp_grade;";
		String sql = "SELECT"
				+ "   ROW_NUMBER() OVER (PARTITION BY a.s_location ORDER BY a.s_location_order, emp_order) AS number,"
				+ "   a.s_num, a.s_location, b.emp_num, b.emp_grade, b.emp_name, b.emp_extrafee, b.emp_phone, b.emp_email, b.emp_date"
				+ "   FROM (SELECT *, CASE s_location" + "				      WHEN '서면점' THEN 1"
				+ "				      WHEN '명지점' THEN 2" + "				      WHEN '율하점' THEN 3"
				+ "				      END AS s_location_order" + "				      FROM store) a"
				+ "   JOIN employees b ON a.s_num = b.s_num" + "   JOIN (SELECT '원장' AS emp_grade, 1 AS emp_order"
				+ "	        UNION ALL" + "	        SELECT '실장', 2" + "	        UNION ALL"
				+ "	        SELECT '수석', 3) AS grade_order ON b.emp_grade = grade_order.emp_grade"
				+ "   ORDER BY a.s_location_order, emp_order;";
		List<AdminDTO> empList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			empList = new ArrayList<>();
			while (rs.next()) {
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setNumber(rs.getInt("number"));
				adminDTO.setS_num(rs.getInt("s_num"));
				adminDTO.setS_location(rs.getString("s_location"));
				adminDTO.setEmp_num(rs.getInt("emp_num"));
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
		} finally {
			dbClose();
		}
		return empList;
	}// getEmpList() [직원목록]

	// 예약, 리뷰, 회원수등을 가져오기위한 메서드
	public AdminDTO getCount() {
		AdminDTO adminDTO = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT" + "    (SELECT COUNT(*) FROM user) AS total_user,"
					+ "    (SELECT COUNT(*) FROM review) AS total_rev,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE res_status = 0) AS total_res,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 1 AND res_status = 0) AS res_a,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 1 AND res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res_a,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 2 AND res_status = 0) AS res_b,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 2 AND res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res_b,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 3 AND res_status = 0) AS res_c,"
					+ "    (SELECT COUNT(*) FROM reservation WHERE s_num = 3 AND res_status = 0 AND DATE(res_day) = CURDATE()) AS today_res_c;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				adminDTO = new AdminDTO();

				adminDTO.setTotal_user(rs.getInt("total_user"));
				adminDTO.setTotal_rev(rs.getInt("total_rev"));
				adminDTO.setTotal_res(rs.getInt("total_res"));
				adminDTO.setToday_res(rs.getInt("today_res"));
				adminDTO.setRes_a(rs.getInt("res_a"));
				System.out.println("확인DAO" + adminDTO.getRes_a());
				adminDTO.setToday_res_a(rs.getInt("today_res_a"));
				adminDTO.setRes_b(rs.getInt("res_b"));
				adminDTO.setToday_res_b(rs.getInt("today_res_b"));
				adminDTO.setRes_c(rs.getInt("res_c"));
				adminDTO.setToday_res_c(rs.getInt("today_res_c"));

				// 예약, 리뷰, 회원수등이 0 일경우 '0' 값 입력
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
		} finally {
			dbClose();
		}
		return adminDTO;
	}// getCount() [관리자메인 count]

	// 예약완료 처리하는 메서드
	public boolean statusComplete(int a) {
		boolean result = false;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			// SQL 쿼리 실행(휴무날짜 내역 값 삽입)
			String SQL = "UPDATE reservation SET res_status = 1 WHERE res_num = ?";
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
	}// statusComplete() [예약상태 > 완료]

	// 예약취소 처리하는 메서드
	public boolean statusCancle(int b) {
		boolean result = false;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			// SQL 쿼리 실행(휴무날짜 내역 값 삽입)
			String SQL = "UPDATE reservation SET res_status = 2 WHERE res_num = ?";
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
	}// statusCancel() [예약상태 > 취소]

	// 예약진행 처리하는 메서드
	public boolean statusUnprocessed(int c) {
		boolean result = false;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			// SQL 쿼리 실행
			String SQL = "UPDATE reservation SET res_status = 0 WHERE res_num = ?";
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
	}// statusUnprocessed() [예약상태 > 진행중]

	// 포인트지급처리완료로 변경 처리하는 메서드
	public boolean pointStatus1(int i) {
		System.out.println("dao");
		boolean result = false;
		try {
			con = new SQLConnection().getConnection();
			String SQL = "UPDATE reservation SET res_point_status = 1" + "   WHERE res_num = ?;";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, i);
			int rs = pstmt.executeUpdate();
			result = (rs != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			dbClose();
		}
		return result;
	}// pointStatus() [포인트지급상태 변경 "완료"]

	// 포인트지급처리취소로 변경 처리하는 메서드
	public boolean pointStatus2(int i) {
		System.out.println("dao");
		boolean result = false;
		try {
			con = new SQLConnection().getConnection();
			String SQL = "UPDATE reservation SET res_point_status = 0" + "   WHERE res_num = ?;";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, i);
			int rs = pstmt.executeUpdate();
			result = (rs != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			dbClose();
		}
		return result;
	}// pointStatus() [포인트지급상태 변경 "취소"]

	// 포인트지급처리 하는메서드
	public void PointConfirm(int i) {
		System.out.println("AdminDAO PointConfirm()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "UPDATE user2"
					+ "   SET u_point = u_point + (SELECT res_price FROM reservation WHERE res_num = ?)*0.01"
					+ "   WHERE u_num = (SELECT u_num FROM reservation WHERE res_num = ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i);
			pstmt.setInt(2, i);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}// PointConfirm() [결제포인트 지급]

	// 포인트회수처리 하는메서드
	public void PointCancle(int i) {
		System.out.println("AdminDAO PointCancle()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "UPDATE user2"
					+ "   SET u_point = u_point - (SELECT res_price FROM reservation WHERE res_num = ?)*0.01"
					+ "   WHERE u_num = (SELECT u_num FROM reservation WHERE res_num = ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i);
			pstmt.setInt(2, i);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}// PointCancle() [결제포인트 회수]
	
	public boolean addUcount(int res_num) {
		boolean result = false;
		try {
			con = new SQLConnection().getConnection();
			String sql = "UPDATE user2"
					+ "   SET u_count = u_count + 1"
					+ "   WHERE u_num = (SELECT u_num"
					+ "                  FROM reservation"
					+ "                  WHERE res_num = ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, res_num);
			int rs = pstmt.executeUpdate();
			result = (rs!=0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}// addUcount [유저 방문(예약)횟수 증가]
	
	public boolean subUcount(int res_num) {
		boolean result = false;
		try {
			con = new SQLConnection().getConnection();
			String sql = "UPDATE user2"
					+ "   SET u_count = u_count - 1"
					+ "   WHERE u_num = (SELECT u_num"
					+ "                  FROM reservation"
					+ "                  WHERE res_num = ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, res_num);
			int rs = pstmt.executeUpdate();
			result = (rs!=0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}// subUcount [유저 방문(예약)횟수 감소]
	
	// 여기서부터 추가했음
	// ===================================================================================
	// 매장 휴무일 추가하는메서드
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
	}// insertDisDay() [매장 휴무일 추가]
	
	// 직원 휴무시간 추가하는메서드
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
	}// insertDisDayTime() [직원 휴무시간 추가]
	
	// 직원 휴무일 추가하는메서드
	public boolean insertDisDayEmp(int s_num, int emp_num, String dis_day) {
		boolean result = false;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			// SQL 쿼리 실행(휴무날짜 내역 값 삽입)
			String SQL = "INSERT INTO disabled_days(s_num, emp_num, dis_day) VALUE(?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, s_num);
			pstmt.setInt(2, emp_num);
			pstmt.setString(3, dis_day);
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
	}// insertDisDayEmp() [직원 휴무일 추가]
	
	// 직원 쉬는시간 가져오는 메서드
	public List<AdminDTO> getEmpDisTimeList() {
		System.out.println("AdminDAO getEmpDisTimeList");
		List<AdminDTO> empDisTimeList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT a.s_location, b.emp_name, c.dis_time, c.dis_daydate, c.dis_time_num"
					+ "   FROM disabled_time c" + "   JOIN store a ON c.s_num = a.s_num"
					+ "   JOIN employees b ON c.emp_num = b.emp_num"
					+ "   ORDER BY a.s_num ASC, c.dis_time DESC, c.dis_daydate DESC;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			empDisTimeList = new ArrayList<>();
			while (rs.next()) {
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setS_location(rs.getString("s_location"));
				adminDTO.setEmp_name(rs.getString("emp_name"));
				adminDTO.setDis_time(rs.getString("dis_time"));
				adminDTO.setDis_daydate(rs.getString("dis_daydate"));
				adminDTO.setDis_time_num(rs.getInt("dis_time_num"));

				empDisTimeList.add(adminDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return empDisTimeList;
	}// getEmpDisTimeList() [직원 쉬는시간 목록]
	
	// 직원 휴무일 가져오는 메서드
	public List<AdminDTO> getEmpDisDaysList() {
		System.out.println("AdminDAO getEmpDisDaysList");
		List<AdminDTO> empDisDaysList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT a.s_location, b.emp_name, c.dis_day, c.dis_day_num" + "   FROM disabled_days c"
					+ "   JOIN store a ON c.s_num = a.s_num" + "   JOIN employees b ON c.emp_num = b.emp_num"
					+ "   ORDER BY a.s_num ASC, c.dis_day DESC;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			empDisDaysList = new ArrayList<>();
			while (rs.next()) {
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setS_location(rs.getString("s_location"));
				adminDTO.setEmp_name(rs.getString("emp_name"));
				adminDTO.setDis_day(rs.getString("dis_day"));
				adminDTO.setDis_day_num(rs.getInt("dis_day_num"));

				empDisDaysList.add(adminDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return empDisDaysList;
	}// getEmpDisDaysList() [직원 휴무일 목록]
	
	// 매장 휴무일 가져오는 메서드
	public List<AdminDTO> getStoreDisDaysList() {
		System.out.println("AdminDAO getStoreDisDaysList");
		List<AdminDTO> storeDisDaysList = null;
		try {
			con = new SQLConnection().getConnection();
			String sql = "SELECT a.s_location, b.off_day, b.off_num" + "   FROM store_offdays b"
					+ "   JOIN store a ON a.s_num = b.s_num" + "   ORDER BY a.s_num ASC, b.off_day DESC;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			storeDisDaysList = new ArrayList<>();
			while (rs.next()) {
				AdminDTO adminDTO = new AdminDTO();
				adminDTO.setS_location(rs.getString("s_location"));
				adminDTO.setOff_day(rs.getString("off_day"));
				adminDTO.setOff_num(rs.getInt("off_num"));

				storeDisDaysList.add(adminDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return storeDisDaysList;
	}// getStoreDisDaysList() [매장 휴무일 목록]
	
	// 직원 휴무일 취소하는 메서드
	public boolean del_EmpDisDays(int dis_day_num) {
		System.out.println("AdminDAO del_EmpDisDays()");
		boolean result = false;
		try {
			con = new SQLConnection().getConnection();
			String sql = "DELETE FROM disabled_days" + "   WHERE dis_day_num = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dis_day_num);

			int i = pstmt.executeUpdate();
			result = (i != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}// del_EmpDisDays() [직원 휴무일 취소]
	
	// 직원 쉬는시간 취소하는 메서드
	public boolean del_EmpDisTime(int dis_time_num) {
		System.out.println("AdminDAO del_EmpDisDays()");
		boolean result = false;
		try {
			con = new SQLConnection().getConnection();
			String sql = "DELETE FROM disabled_time" + "   WHERE dis_time_num = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dis_time_num);

			int i = pstmt.executeUpdate();
			result = (i != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}// del_EmpDisTime() [직원 쉬는시간 취소]
	
	// 매장 휴무일 취소하는 메서드
	public boolean del_StoreDisDays(int off_num) {
		boolean result = false;
		System.out.println("AdminDAO del_StoreDisDays()");
		try {
			con = new SQLConnection().getConnection();
			String sql = "DELETE FROM store_offdays" + "   WHERE off_num = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, off_num);

			int i = pstmt.executeUpdate();
			result = (i != 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}// del_StoreDisDays() [매장 휴무일 취소]

	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}

		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}

		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}



}// class

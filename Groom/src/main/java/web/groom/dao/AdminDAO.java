package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;

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
				+ "   FROM user a JOIN user2 b\r\n"
				+ "   ON a.u_num = b.u_num\r\n"
				+ "   ORDER BY a.u_num desc";
		//일단 전체회원, 필요하면 where 넣기
//		String sql = "select * from userInfo";
//		String sql = "select * from userInfo where u_role='user'"; // 일반유저만 출력
		List<MemberDTO> memberList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<>();
			while(rs.next()){
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setNum(rs.getInt("u_num"));
				memberDTO.setRole(rs.getString("u_role"));
				memberDTO.setId(rs.getString("u_id"));
				memberDTO.setName(rs.getString("u_name"));
				memberDTO.setPhone(rs.getString("u_phone"));
				memberDTO.setEmail(rs.getString("u_email"));
				memberDTO.setRegDate(rs.getTimestamp("u_regdate"));
				memberDTO.setCount(rs.getInt("u_count"));
				memberDTO.setPoint(rs.getInt("u_point"));
				memberList.add(memberDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return memberList;
	}//getMemberList() [회원정보 목록]

	public List<AdminDTO> getEmpList() {
		System.out.println("AdminDAO getEmpList()");
		String sql = "SELECT"
				+ "   ROW_NUMBER() OVER (PARTITION BY a.s_location ORDER BY b.s_num, b.emp_grade) AS number,"
				+ "   a.s_location, b.emp_grade, b.emp_name"
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
				empList.add(adminDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return empList;
	}//getEmpList() [직원목록]
	
	
	
	

}// class
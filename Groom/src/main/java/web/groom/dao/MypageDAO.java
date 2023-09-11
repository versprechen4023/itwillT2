package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.MemberDTO;
import web.groom.dto.MypageDTO;
import web.groom.dto.OrderDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.security.MemberSecurity;

public class MypageDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MypageDTO mypagedto = null;
	
	// 유저의 정보를 얻기위한 메서드
	public MypageDTO getMemberInfo(int num) {

		try {

			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT a.u_num, a.u_id, b.u_name, b.u_phone, b.u_email, b.u_regdate , b.u_count, b.u_point FROM user a JOIN user2 b ON a.u_num  = b.u_num WHERE a.u_num = ?";

			// 얘가 원래 있던거
			// String SQL = "SELECT * FROM user2 WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			// 유저번호에 따른 개인정보 저장
			if (rs.next()) {
				mypagedto = new MypageDTO();

				mypagedto.setNum(rs.getInt("a.u_num"));
				mypagedto.setId(rs.getString("a.u_id"));
				mypagedto.setName(rs.getString("b.u_name"));
				mypagedto.setPhone(rs.getString("b.u_phone"));
				mypagedto.setEmail(rs.getString("b.u_email"));
				// mypagedto.setRegDate(rs.getTimestamp("b.u_regdate"));
				// mypagedto.setCount(rs.getInt("b.u_count"));
				mypagedto.setPoint(rs.getInt("b.u_point"));
			} else {
				mypagedto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			mypagedto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}

		return mypagedto;
	} // getMemberInfo
	
	// 펫 정보를 등록하기위한 메서드
	public MypageDTO insertMypet(MypageDTO mypagedto) {

		try {
			System.out.println("인설트마이펫" + mypagedto);
			// db연결
			con = new SQLConnection().getConnection();

			// SQL 쿼리 입력
			String SQL = "INSERT INTO pet(u_num, pet_breed, pet_name, pet_gender, pet_neuter, pet_comment) VALUE(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, mypagedto.getNum());
			pstmt.setString(2, mypagedto.getPetBreed());
			pstmt.setString(3, mypagedto.getPetName());
			pstmt.setString(4, mypagedto.getPetGender());
			pstmt.setString(5, mypagedto.getPetNeuter());
			pstmt.setString(6, mypagedto.getPetComment());

			// SQL실행
		    int rs = pstmt.executeUpdate();
			
		    // 업데이트 실패시 mypagedto 는 null
		    if(rs==0) {
		    	mypagedto = null;
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
			mypagedto = null; // 에러 발생시 멤버 DTO 저장소해제

		} finally {
			dbClose();
		}

		return mypagedto;
	} // insertMypet
	
	// 펫 정보를 얻기위한 메서드
	public MypageDTO getMypetinfo(int num) {

		try {

			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT * FROM pet WHERE u_num = ? ";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			// 유저번호에 따른 개인정보 저장

			if (rs.next()) {
				mypagedto = new MypageDTO();
				mypagedto.setPetName(rs.getString("pet_name"));
				mypagedto.setPetBreed(rs.getString("pet_breed"));
				mypagedto.setPetGender(rs.getString("pet_gender"));
				mypagedto.setPetNeuter(rs.getString("pet_neuter"));
				mypagedto.setPetComment(rs.getString("pet_comment"));
				mypagedto.setPetNum(rs.getInt("pet_num"));
			} else {
//				mypagedto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}

		return mypagedto;
	} // getMypetinfo
	
	// 유저 펫 정보 업데이트 메서드
	public boolean updateMypet(MypageDTO mypagedto) {
        
		boolean result = false;
		
		try {

			// db연결
			con = new SQLConnection().getConnection();

			// SQL 쿼리 입력
			String SQL = "UPDATE pet SET pet_breed = ?, pet_name = ?, pet_gender =? , pet_neuter =? , pet_comment =? WHERE pet_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mypagedto.getPetBreed());
			pstmt.setString(2, mypagedto.getPetName());
			pstmt.setString(3, mypagedto.getPetGender());
			pstmt.setString(4, mypagedto.getPetNeuter());
			pstmt.setString(5, mypagedto.getPetComment());
			pstmt.setInt(6, mypagedto.getPetNum());
			
			// SQL실행
			int rs = pstmt.executeUpdate();
			
			// 업데이트 성공유무 반환
			result = (rs != 0) ? true : false;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;

		} finally {
			dbClose();
		}
		
		return result;
		
	}// updateMypet
	
	// 유저 펫 정보 리스트 가져오는 메서드
	public List<MypageDTO> getMypetList(int u_num) {
		
		System.out.println("MypageDAO getMypetList()");
		
		List<MypageDTO> mypetList = null;
		
		try {
			
			// db연결
			con = new SQLConnection().getConnection();
			
			// SQL 쿼리 입력
			String sql = "SELECT * FROM pet WHERE u_num = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			
			// SQL실행
			rs = pstmt.executeQuery();
			
			mypetList = new ArrayList<>();
			
			while (rs.next()) {
				MypageDTO mypageDTO = new MypageDTO();
				mypageDTO.setPetNum(rs.getInt("pet_num"));
				mypageDTO.setPetName(rs.getString("pet_name"));
				mypageDTO.setPetBreed(rs.getString("pet_breed"));
				mypageDTO.setPetGender(rs.getString("pet_gender"));
				mypageDTO.setPetNeuter(rs.getString("pet_neuter"));
				mypageDTO.setPetComment(rs.getString("pet_comment"));
				mypetList.add(mypageDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return mypetList;
	}// getMypetList()
	
	// updatemypet에서 보여줄 펫 정보 가져오는 메서드
	public MypageDTO MypetTestInfo(int u_num, int pet_num) {

		try {

			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT * FROM pet WHERE u_num = ? AND pet_num = ? ";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, u_num);
			pstmt.setInt(2, pet_num);
			rs = pstmt.executeQuery();

			// 유저번호에 따른 개인정보 저장
			if (rs.next()) {
				mypagedto = new MypageDTO();
				mypagedto.setPetNum(rs.getInt("pet_num"));
				mypagedto.setPetName(rs.getString("pet_name"));
				mypagedto.setPetBreed(rs.getString("pet_breed"));
				mypagedto.setPetGender(rs.getString("pet_gender"));
				mypagedto.setPetNeuter(rs.getString("pet_neuter"));
				mypagedto.setPetComment(rs.getString("pet_comment"));

			} else {
				mypagedto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}

		return mypagedto;
	} // MypetTestInfo
	
	// 유저 펫 정보 삭제하는 메서드
	public boolean deleteMypet(int pet_num) {
		
		boolean result = false;
		
		try {
			// db 연결
			con = new SQLConnection().getConnection();
			String sql = "DELETE FROM pet WHERE pet_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_num);
			// 실행
			int rs = pstmt.executeUpdate();
			
			// 삭제 성공유무 반환
			result = (rs != 0) ? true : false;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	} // deleteMypet
	
	// 내 정보 수정하는 메서드
	public boolean modifyInfo(MypageDTO mypageDTO) {
		
		boolean result = false;
		
		try {
			// db 연결
			con = new SQLConnection().getConnection();
			String sql = "UPDATE user2 set u_phone = ?, u_email = ? WHERE u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mypageDTO.getPhone());
			pstmt.setString(2, mypageDTO.getEmail());
			pstmt.setInt(3, mypageDTO.getNum());

			// 실행
			int rs = pstmt.executeUpdate();
			
			// 업데이트 성공유무 반환
			result = (rs != 0) ? true : false;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	} // modifyinfo

// 미사용 메서드 비활성화 처리 유저체크는 멤버서비스에서 이루어지고있음
//	public MypageDTO userCheck(MypageDTO mypageDTO2) {
//		MypageDTO mypageDTO = null;
//
//		try {
//			// 디비연결
//			con = new SQLConnection().getConnection();
//			// 3 sql select * from members where id=? and pass=?
//			String sql = "select * from user where u_id=? and u_pass=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, mypageDTO2.getId());
//			pstmt.setString(2, mypageDTO2.getPass());
//			// 4 실행 결과 => rs 저장
//			rs = pstmt.executeQuery();
//			// 5 rs 첫번째 행으로 데이터 있으면
//			// mypageDTO 객체생성, set메서드 호출 rs열 데이터 저장
//			if (rs.next()) {
//				// 아이디 비밀번호 일치 => 객체생성 => 기억장소 주소 리턴
//				mypageDTO = new MypageDTO();
//				mypageDTO.setId(rs.getString("id"));
//				mypageDTO.setPass(rs.getString("pass"));
//				mypageDTO.setName(rs.getString("name"));
//
//			}
//			// 아이디 비밀번호 틀리면 => 초기값 null=> 리턴
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//		return mypageDTO;
//	}// userCheck()
	
	// 유저 예약 목록 가져오는 메서드
	public List<OrderReservationDTO> getReservationList(int u_num) {
		System.out.println("AdminDAO getReservationList()");
		List<OrderReservationDTO> reservationList = null;
		try {
			// db연결
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT a.res_num, a.res_day, a.res_time, a.s_num, a.emp_num, b.pro_name, c.pet_size, b.pet_weight,"
					+ "          d.s_location, e.emp_grade, e.emp_name, f.u_name, f.u_phone, a.res_point,"
					+ "          a.res_price, a.res_status, a.res_method, a.res_point_status" + "   FROM reservation a"
					+ "   JOIN product2 b ON a.pro_id2 = b.pro_id2" + "   JOIN product1 c ON a.pro_id1 = c.pro_id1"
					+ "   JOIN store d ON a.s_num = d.s_num" + "   JOIN employees e ON a.emp_num = e.emp_num"
					+ "   JOIN user2 f ON a.u_num = f.u_num" + "   WHERE f.u_num = ?" + "   ORDER BY a.res_num desc;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			rs = pstmt.executeQuery();
			reservationList = new ArrayList<>();
			
			while (rs.next()) {
				OrderReservationDTO orderReservationDTO = new OrderReservationDTO();
				orderReservationDTO.setRes_num(rs.getInt("res_num"));
				orderReservationDTO.setRes_day(rs.getString("res_day"));
				orderReservationDTO.setRes_time(rs.getString("res_time"));
				orderReservationDTO.setS_num(rs.getInt("s_num"));
				orderReservationDTO.setEmp_num(rs.getInt("emp_num"));
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
	}// 예약리스트
	
	// 유저 예약 정보 변경하는 메서드
	public boolean changeRes(String res_day, String res_time, int res_num, int u_num) {

		boolean result = false;

		try {

			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "UPDATE reservation SET res_day = ?, res_time = ? WHERE res_num = ? and u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, res_day);
			pstmt.setString(2, res_time);
			pstmt.setInt(3, res_num);
			pstmt.setInt(4, u_num);
			int rs = pstmt.executeUpdate();

			// 업데이트 성공유무 반환
			result = (rs != 0) ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return result;
	} // changeRes
	
    // 유저 예약 취소 하는 메서드
	public boolean cancelRes(int res_num, int u_num) {
		
		boolean result = false;

		try {

			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "UPDATE reservation SET res_status = 2 WHERE res_num = ? and u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, res_num);
			pstmt.setInt(2, u_num);
			int rs = pstmt.executeUpdate();

			// 업데이트 성공유무 결정
			result = (rs != 0) ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return result;
	} // cancelRes
	
	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	} // dbClose

} //end_of_MypageDAO
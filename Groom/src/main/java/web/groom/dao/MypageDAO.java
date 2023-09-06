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


	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		
		
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		
		
		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	
	public MypageDTO getMemberInfo(int num) {
		
		mypagedto = null;
		
		try {
			
			//db연결
			con = new SQLConnection().getConnection();
			
         	String SQL = "select a.u_num, a.u_id, b.u_name, b.u_phone, b.u_email, b.u_regdate , b.u_count, b.u_point from user a join user2 b on a.u_num  = b.u_num where a.u_num = ?";
			 

			// 얘가 원래 있던거 
		   // String SQL = "SELECT * FROM user2 WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			// 유저번호에 따른 개인정보 저장
			if(rs.next()) {
				mypagedto = new MypageDTO();
				
				mypagedto.setNum(rs.getInt("a.u_num"));
				mypagedto.setId(rs.getString("a.u_id"));
				mypagedto.setName(rs.getString("b.u_name"));
				mypagedto.setPhone(rs.getString("b.u_phone"));
				mypagedto.setEmail(rs.getString("b.u_email"));
			//	mypagedto.setRegDate(rs.getTimestamp("b.u_regdate"));
		//		mypagedto.setCount(rs.getInt("b.u_count"));
				mypagedto.setPoint(rs.getInt("b.u_point"));
			} else {
				mypagedto = null;
			}

		} catch(Exception e) {
			e.printStackTrace();
			mypagedto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}
		
		return mypagedto;
}
	public MypageDTO insertMypet(MypageDTO mypagedto) {
		
	try {
		System.out.println("인설트마이펫"+mypagedto);
		//db연결
		con = new SQLConnection().getConnection();

		// SQL 쿼리 입력
		String SQL = "INSERT INTO pet(u_num, pet_breed, pet_name, pet_gender, pet_neuter, pet_comment) VALUE(?, ?,?,?,?,?)";
		pstmt = con.prepareStatement(SQL);
		pstmt.setInt(1, mypagedto.getNum());
		pstmt.setString(2, mypagedto.getPetBreed());
		pstmt.setString(3, mypagedto.getPetName());
		pstmt.setString(4, mypagedto.getPetGender());
		pstmt.setString(5, mypagedto.getPetNeuter());
		pstmt.setString(6, mypagedto.getPetComment());
		
		// SQL실행
		pstmt.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		mypagedto = null; // 에러 발생시 멤버 DTO 저장소해제
	
		} finally {
		dbClose();
		//저장소 할당 해제
		}

		return mypagedto;
		}

	
	public MypageDTO getMypetinfo(int num) {
		
		mypagedto = new MypageDTO();
		
		try {
			
			//db연결
			con = new SQLConnection().getConnection();
			
         	String SQL = "select * from pet where u_num = ? ";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			// 유저번호에 따른 개인정보 저장
		
			if(rs.next()) {
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
			
		}catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			dbClose();
		}
		
		return mypagedto;
	}
	
	
	public void updateMypet(MypageDTO mypagedto) {
//		mypagedto = null;
		try {
			
			//db연결
			con = new SQLConnection().getConnection();
			

			// SQL 쿼리 입력
			String SQL = "update pet set  pet_breed = ?, pet_name = ?, pet_gender =? , pet_neuter =? , pet_comment =? where pet_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mypagedto.getPetBreed());
			pstmt.setString(2, mypagedto.getPetName());
			pstmt.setString(3, mypagedto.getPetGender());
			pstmt.setString(4, mypagedto.getPetNeuter());
			pstmt.setString(5, mypagedto.getPetComment());
			pstmt.setInt(6, mypagedto.getPetNum());
			// SQL실행
			pstmt.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
			mypagedto = null; // 에러 발생시 멤버 DTO 저장소해제
		
			} finally {
			dbClose();
			//저장소 할당 해제
			}
	}//updateMypet

	public List<MypageDTO> getMypetList(int u_num) {
		System.out.println("MypageDAO getMypetList()");
		String sql = "select * from pet where u_num = ? ";
		List<MypageDTO> mypetList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			rs = pstmt.executeQuery();
			mypetList = new ArrayList<>();
			while(rs.next()){
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
		}finally {
			dbClose();
		}
		return mypetList;
	}// getMypetList()

	public MypageDTO MypetTestInfo(int u_num, int pet_num) {
		
		mypagedto = new MypageDTO();
		
		try {
			
			//db연결
			con = new SQLConnection().getConnection();
			
         	String SQL = "select * from pet where u_num = ? and pet_num = ? ";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, u_num);
			pstmt.setInt(2, pet_num);
			rs = pstmt.executeQuery();
			
			// 유저번호에 따른 개인정보 저장
		
			if(rs.next()) {
				mypagedto = new MypageDTO();
				mypagedto.setPetNum(rs.getInt("pet_num"));
				mypagedto.setPetName(rs.getString("pet_name"));
				mypagedto.setPetBreed(rs.getString("pet_breed"));
				mypagedto.setPetGender(rs.getString("pet_gender"));
				mypagedto.setPetNeuter(rs.getString("pet_neuter"));
				mypagedto.setPetComment(rs.getString("pet_comment"));
	
			} else {
//				mypagedto = null;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			dbClose();
		}
		
		return mypagedto;
	}

	public void deleteMypet(int pet_num) {
	    try {
	        // db 연결
	        con = new SQLConnection().getConnection();
	        String sql = "DELETE FROM pet WHERE pet_num = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, pet_num);
	        // 실행
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        dbClose();
	    }
	}

	public void modifyinfo(MypageDTO mypageDTO) {
		  try {
		        // db 연결
		        con = new SQLConnection().getConnection();
		        String sql = "UPDATE user2 set u_phone = ?, u_email = ? WHERE u_num = ?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, mypageDTO.getPhone());
		        pstmt.setString(2, mypageDTO.getEmail());
		        pstmt.setInt(3, mypageDTO.getNum());
		        
		        // 실행
		        pstmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        dbClose();
		    }
		
	} //modifyinfo

	public MypageDTO userCheck(MypageDTO mypageDTO2) {
		MypageDTO mypageDTO = null;
		
		try {
			//디비연결
			con = new SQLConnection().getConnection();
			//3 sql select * from members where id=? and pass=?
			String sql="select * from user where u_id=? and u_pass=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mypageDTO2.getId());
			pstmt.setString(2, mypageDTO2.getPass());
			//4 실행 결과 => rs 저장
			rs = pstmt.executeQuery();
			//5 rs 첫번째 행으로 데이터 있으면 
			//  mypageDTO 객체생성, set메서드 호출 rs열 데이터 저장
			if(rs.next()) {
				// 아이디 비밀번호 일치 => 객체생성 => 기억장소 주소 리턴
				mypageDTO = new MypageDTO();
				mypageDTO.setId(rs.getString("id"));
				mypageDTO.setPass(rs.getString("pass"));
				mypageDTO.setName(rs.getString("name"));
	
			}
			//아이디 비밀번호 틀리면 => 초기값 null=> 리턴
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return mypageDTO;
	}// userCheck()

	public List<OrderReservationDTO> getReservationList(int u_num) {
		System.out.println("AdminDAO getReservationList()");
		String sql = "select a.res_num, a.res_day, a.res_time, b.pro_name, c.pet_size, b.pet_weight,"
				+ "          d.s_location, e.emp_grade, e.emp_name, f.u_name, f.u_phone, a.res_point,"
				+ "          a.res_price, a.res_status, a.res_method, a.res_point_status"
				+ "   from test_reservation a"
				+ "   join product2 b on a.pro_id2 = b.pro_id2"
				+ "   join product1 c on a.pro_id1 = c.pro_id1"
				+ "   join store d on a.s_num = d.s_num"
				+ "   join employees e on a.emp_num = e.emp_num"
				+ "   join user2 f on a.u_num = f.u_num"
				+ "   where f.u_num = ?"
				+ "   order by a.res_num desc;";
		List<OrderReservationDTO> reservationList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			rs = pstmt.executeQuery();
			reservationList = new ArrayList<>();
			while(rs.next()){
				OrderReservationDTO orderReservationDTO = new OrderReservationDTO();
//				orderReservationDTO.setU_num(rs.getInt("u_num"));
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
		}finally {
			dbClose();
		}
		return reservationList;
	}//예약리스트

	public List<OrderReservationDTO> getUserDate(int u_num) {
		
		List<OrderReservationDTO> userDateList = null;
		
		try {
			
			con = new SQLConnection().getConnection();
			
			String sql = "SELECT res_day FROM test_reservation WHERE u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			rs = pstmt.executeQuery();

			userDateList = new ArrayList<>();

			while(rs.next()) {
				OrderReservationDTO orderReserv = new OrderReservationDTO();
				orderReserv.setRes_day(rs.getString("res_day"));

				userDateList.add(orderReserv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return userDateList;
	}

}
	
	





	

	


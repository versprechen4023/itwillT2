package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.groom.dto.MemberDTO;
import web.groom.dto.MypageDTO;
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
			
         	String SQL = "select a.u_id, b.u_name, b.u_phone, b.u_email, b.u_regdate , b.u_count, b.u_point from user a join user2 b on a.u_num  = b.u_num where a.u_num = ?";
			 

			// 얘가 원래 있던거 
		   // String SQL = "SELECT * FROM user2 WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			// 유저번호에 따른 개인정보 저장
			if(rs.next()) {
				mypagedto = new MypageDTO();
				
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

	public List<MypageDTO> getMypetList() {
		System.out.println("MypageDAO getMypetList()");
		String sql = "select * from pet ";
		List<MypageDTO> mypetList = null;
		try {
			con = new SQLConnection().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			mypetList = new ArrayList<>();
			while(rs.next()){
				MypageDTO mypageDTO = new MypageDTO();
				mypageDTO.setPetName(rs.getString("pet_name"));
				mypageDTO.setPetBreed(rs.getString("pet_breed"));
				mypageDTO.setPetGender(rs.getString("pet_gender"));
				mypageDTO.setPetNeuter(rs.getString("pet_Neuter"));
				mypageDTO.setPetNeuter(rs.getString("pet_Neuter"));
				mypetList.add(mypageDTO);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return mypetList;
	}// getMypetList()
	
	





	

	
}

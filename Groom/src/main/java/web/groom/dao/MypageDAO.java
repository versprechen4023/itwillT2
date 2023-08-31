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
}
	
	





	

	


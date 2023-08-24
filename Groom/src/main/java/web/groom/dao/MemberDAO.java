package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.groom.dto.MemberDTO;
import web.groom.security.MemberSecurity;

public class MemberDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	MemberDTO memberdto = null;


	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		
		if (rs2 != null) {try {rs2.close();} catch (SQLException e) {e.printStackTrace();}}

		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		
		if (pstmt2 != null) {try {pstmt2.close();} catch (SQLException e) {e.printStackTrace();}}

		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	
	public MemberDTO insertMember(MemberDTO memberdto) {
		
		//암호화 메서드 실행을 위해 객체생성
		MemberSecurity security = new MemberSecurity();
		
		try {
				
				//db연결
				con = new SQLConnection().getConnection();
				
				// 솔트 생성 및 해시비밀번호 입력 준비
				String salt = security.generateSalt();
				String hashedPassword = security.hashPassword(memberdto.getPass(), salt);
				String role = "admin";
				
				// SQL 쿼리 실행(첫번째 유저테이블에 값삽입)
				String SQL = "INSERT INTO user(u_id, u_pass, u_salt, u_role) VALUE(?,?,?,?)";
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, memberdto.getId());
				pstmt.setString(2, hashedPassword);
				pstmt.setString(3, salt);
				pstmt.setString(4, role);
				int resultSet =  pstmt.executeUpdate();
				
				// SQL 쿼리 실행(두번째 유저테이블에 값삽입)
				String SQL2 = "INSERT INTO user2(u_name, u_phone, u_email, u_regdate) VALUE(?,?,?,?)";
				pstmt2 = con.prepareStatement(SQL2);
				pstmt2.setString(1, memberdto.getName());
				pstmt2.setString(2, memberdto.getPhone());
				pstmt2.setString(3, memberdto.getEmail());
				pstmt2.setTimestamp(4, memberdto.getRegDate());
				int resultSet2 =  pstmt2.executeUpdate();
				
				if (resultSet != 0 && resultSet2 != 0) {
					//성공적으로 값 입력시 나머지 부분 memberDTO에 입력
					memberdto.setPass(hashedPassword);
					memberdto.setSalt(salt);
					memberdto.setRole(role);
				}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
			security = null; //저장소 할당 해제
			
		} finally {
			dbClose();
			security = null; //저장소 할당 해제
		}
		
		return memberdto;
	}

	public MemberDTO userCheck(String id, String pass) {
		
		//memberdto를 넘겨주기위한 초기화
		memberdto = null;
		
		//암호화 메서드 실행을 위해 객체생성
		MemberSecurity security = new MemberSecurity();
		
		try {
			//db연결
			con = new SQLConnection().getConnection();
			
			// 로그인을위한 SQL 쿼리 실행(ID검색)
			String SQL = "SELECT * FROM user WHERE u_id = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			// 로그인 결과 처리
			if (rs.next()) {
				
				//DB로부터 해시 값과 솔트 값을 받아옴
				String hashedPassword = rs.getString("u_pass");
				String salt = rs.getString("u_salt");
				
				// 입력된 비밀번호와 솔트 값으로 해시화하여 비교
				String inputHashedPassword = security.hashPassword(pass, salt);
				
				//비밀번호 일치시 로그인 성공 로직
				if (hashedPassword.equals(inputHashedPassword)) {
					System.out.println("비밀번호 일치");
					
					//DB로부터 정보받아 memberdto에 값입력
					memberdto = new MemberDTO();
					
					//첫번째 테이블로부터 값받아 입력
					memberdto.setNum(rs.getInt("u_num"));
					memberdto.setId(rs.getString("u_id"));
					memberdto.setPass(hashedPassword);
					memberdto.setSalt(salt);
					memberdto.setRole(rs.getString("u_role"));
					
				}
				
				//비밀번호 불일치시 로그인 실패
				else {
					System.out.println("비밀번호 불일치");
				}
				
			//아이디가 없는경우 해당
			} else {
				System.out.println("아이디 없음");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
			security = null; //저장소 할당 해제
			
		} finally {
			dbClose();
			security = null; //저장소 할당 해제
		}
		return memberdto;
	}
	
	public MemberDTO searchId(String id) {
		
		memberdto = null;
		
		try {
			//db연결
			con = new SQLConnection().getConnection();
			
			String SQL = "SELECT * FROM user WHERE u_id = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			// id 있으면 memberdto 객체생성후 값저장후 넘김 Id없는 경우 memberdto는 null로 넘김
			if(rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setId(id);
			} else {
				memberdto = null;
			}

		} catch(Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}
		
		return memberdto;
	}
	
	public MemberDTO searchPhone(String phone) {
		
		memberdto = null;
		
		try {
			//db연결
			con = new SQLConnection().getConnection();
			
			String SQL = "SELECT * FROM user2 WHERE u_phone = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			
			// 전화번호 있으면 memberdto 객체생성후 값저장후 넘김 전화번호없는 경우 memberdto는 null로 넘김
			if(rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setPhone(phone);
			} else {
				memberdto = null;
			}

		} catch(Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}
		
		return memberdto;
	}

	public MemberDTO searchEmail(String email) {
		
		memberdto = null;
		
		try {
			//db연결
			con = new SQLConnection().getConnection();
			
			String SQL = "SELECT * FROM user2 WHERE u_email = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			// email 있으면 memberdto 객체생성후 값저장후 넘김 email없는 경우 memberdto는 null로 넘김
			if(rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setEmail(email);
			} else {
				memberdto = null;
			}

		} catch(Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}
		
		return memberdto;
	}
	
	public MemberDTO findid(String name, String email) {
		
		memberdto = null;

		try {

			con = new SQLConnection().getConnection();

			String SQL = "SELECT u.u_id FROM user u JOIN user2 u2 on u.u_num = u2.u_num  WHERE u2.u_name=? AND u2.u_email = ? ";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			System.out.println("memberdao " + name + email); // 임시로 데이터 값을 받는지 체크하기 위해 넣은 것.


			if (rs.next()) {
				memberdto = new MemberDTO();
				
				memberdto.setId(rs.getString("u_id"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return memberdto;
	} // findid 
	
	public MemberDTO findPass(String id, String email) {
		
		memberdto = null;

		try {
			
			con = new SQLConnection().getConnection();
			String SQL = "SELECT u.u_num FROM user u JOIN user2 u2 on u.u_num = u2.u_num WHERE u.u_id=? AND u2.u_email = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			System.out.println("memberdao " + id + email); // 임시로 데이터 값을 받는지 체크하기 위해 넣은 것.

			// 값 일치하면 memberdto 객체생성후 값저장후 넘김 값일치 없는 경우 memberdto는 null로 넘김
			if(rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setNum(rs.getInt("u_num"));
			} else {
				memberdto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			dbClose();
			
		}
		return memberdto;
	} // findpass
	
	public MemberDTO resetPass(String hashedPassword, String salt, int u_num) {
		
		memberdto = null;

		try {
			
			con = new SQLConnection().getConnection();
			String SQL = "UPDATE user SET u_pass = ?, u_salt = ? WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, hashedPassword);
			pstmt.setString(2, salt);
			pstmt.setInt(3, u_num);
			int result = pstmt.executeUpdate();
			

			// 값 일치하면 memberdto 객체생성후 값저장후 넘김 값일치 없는 경우 memberdto는 null로 넘김
			if(result != 0) {
				memberdto = new MemberDTO();
			} else {
				memberdto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null;
		} finally {
			dbClose();
		}
		return memberdto;
	} // resetPass


	public MemberDTO getMemberInfo(int num) {
		
		memberdto = null;
		
		try {
			
			//db연결
			con = new SQLConnection().getConnection();
			
			String SQL = "SELECT * FROM user2 WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			// 유저번호에 따른 개인정보 저장
			if(rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setName(rs.getString("u_name"));
				memberdto.setPhone(rs.getString("u_phone"));
				memberdto.setEmail(rs.getString("u_email"));
				memberdto.setRegDate(rs.getTimestamp("u_regdate"));
				memberdto.setCount(rs.getInt("u_count"));
				memberdto.setPoint(rs.getInt("u_point"));
			} else {
				memberdto = null;
			}

		} catch(Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}
		
		return memberdto;
	}// getMemberInfo
}

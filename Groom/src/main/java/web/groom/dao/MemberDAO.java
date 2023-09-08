package web.groom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import web.groom.dto.MemberDTO;
import web.groom.security.MemberSecurity;

public class MemberDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	MemberDTO memberdto = null;
	
	// 회원가입 메서드
	public MemberDTO insertMember(MemberDTO memberdto) {

		// 암호화 메서드 실행을 위해 객체생성
		MemberSecurity security = new MemberSecurity();

		try {

			// db연결
			con = new SQLConnection().getConnection();

			// 솔트 생성 및 해시비밀번호 입력 준비
			String salt = security.generateSalt();
			String hashedPassword = security.hashPassword(memberdto.getU_Pass(), salt);
			String role = "admin";

			// SQL 쿼리 실행(첫번째 유저테이블에 값삽입)
			String SQL = "INSERT INTO user(u_id, u_pass, u_salt, u_role) VALUE(?,?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, memberdto.getU_Id());
			pstmt.setString(2, hashedPassword);
			pstmt.setString(3, salt);
			pstmt.setString(4, role);
			int rs = pstmt.executeUpdate();

			// SQL 쿼리 실행(두번째 유저테이블에 값삽입)
			String SQL2 = "INSERT INTO user2(u_name, u_phone, u_email, u_regdate) VALUE(?,?,?,?)";
			pstmt2 = con.prepareStatement(SQL2);
			pstmt2.setString(1, memberdto.getU_Name());
			pstmt2.setString(2, memberdto.getU_Phone());
			pstmt2.setString(3, memberdto.getU_Email());
			pstmt2.setTimestamp(4, memberdto.getU_RegDate());
			int rs2 = pstmt2.executeUpdate();

			if (rs != 0 && rs2 != 0) {
				// 성공적으로 값 입력시 나머지 부분 memberDTO에 입력
				memberdto.setU_Pass(hashedPassword);
				memberdto.setU_Salt(salt);
				memberdto.setU_Role(role);
			}

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
			security = null; // 저장소 할당 해제

		} finally {
			dbClose();
			security = null; // 저장소 할당 해제
		}

		return memberdto;
	}
	
	// 로그인 메서드
	public MemberDTO userCheck(String u_id, String u_pass) {

		// memberdto를 넘겨주기위한 초기화
		memberdto = null;

		// 암호화 메서드 실행을 위해 객체생성
		MemberSecurity security = new MemberSecurity();

		try {
			// db연결
			con = new SQLConnection().getConnection();

			// 로그인을위한 SQL 쿼리 실행(ID검색)
			String SQL = "SELECT * FROM user WHERE u_id = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();

			// 로그인 결과 처리
			if (rs.next()) {

				// DB로부터 해시 값과 솔트 값을 받아옴
				String hashedPassword = rs.getString("u_pass");
				String u_salt = rs.getString("u_salt");

				// 입력된 비밀번호와 솔트 값으로 해시화하여 비교
				String inputHashedPassword = security.hashPassword(u_pass, u_salt);

				// 비밀번호 일치시 로그인 성공 로직
				if (hashedPassword.equals(inputHashedPassword)) {
					System.out.println("비밀번호 일치");

					// DB로부터 정보받아 memberdto에 값입력
					memberdto = new MemberDTO();

					// 첫번째 테이블로부터 값받아 입력
					memberdto.setU_Num(rs.getInt("u_num"));
					memberdto.setU_Id(rs.getString("u_id"));
					memberdto.setU_Pass(hashedPassword);
					memberdto.setU_Salt(u_salt);
					memberdto.setU_Role(rs.getString("u_role"));

				}

				// 비밀번호 불일치시 로그인 실패
				else {
					System.out.println("비밀번호 불일치");
				}

				// 아이디가 없는경우 해당
			} else {
				System.out.println("아이디 없음");
			}

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
			security = null; // 저장소 할당 해제

		} finally {
			dbClose();
			security = null; // 저장소 할당 해제
		}
		return memberdto;
	}
	
	// 아이디 중복검사 메서드
	public MemberDTO searchId(String u_id) {

		memberdto = null;

		try {
			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT * FROM user WHERE u_id = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();

			// id 있으면 memberdto 객체생성후 값저장후 넘김 Id없는 경우 memberdto는 null로 넘김
			if (rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setU_Id(u_id);
			} else {
				memberdto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}

		return memberdto;
	}
	
	// 핸드폰번호 중복검사 메서드
	public MemberDTO searchPhone(String u_phone) {

		memberdto = null;

		try {
			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT * FROM user2 WHERE u_phone = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, u_phone);
			rs = pstmt.executeQuery();

			// 전화번호 있으면 memberdto 객체생성후 값저장후 넘김 전화번호없는 경우 memberdto는 null로 넘김
			if (rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setU_Phone(u_phone);
			} else {
				memberdto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}

		return memberdto;
	}
    
	// 이메일 중복검사 메서드
	public MemberDTO searchEmail(String u_email) {

		memberdto = null;

		try {
			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT * FROM user2 WHERE u_email = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, u_email);
			rs = pstmt.executeQuery();

			// email 있으면 memberdto 객체생성후 값저장후 넘김 email없는 경우 memberdto는 null로 넘김
			if (rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setU_Email(u_email);
			} else {
				memberdto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}

		return memberdto;
	}
	
	// 아이디 찾기 메서드
	public MemberDTO findid(String u_name, String u_email) {

		memberdto = null;

		try {

			con = new SQLConnection().getConnection();

			String SQL = "SELECT u.u_id FROM user u JOIN user2 u2 on u.u_num = u2.u_num  WHERE u2.u_name=? AND u2.u_email = ? ";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, u_name);
			pstmt.setString(2, u_email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberdto = new MemberDTO();

				memberdto.setU_Id(rs.getString("u_id"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return memberdto;
	} // findid
	
	// 비밀번호 찾기 메서드
	public MemberDTO findPass(String u_id, String u_email) {

		memberdto = null;

		try {

			con = new SQLConnection().getConnection();
			String SQL = "SELECT u.u_num FROM user u JOIN user2 u2 on u.u_num = u2.u_num WHERE u.u_id=? AND u2.u_email = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, u_id);
			pstmt.setString(2, u_email);
			rs = pstmt.executeQuery();

			// 값 일치하면 memberdto 객체생성후 값저장후 넘김 값일치 없는 경우 memberdto는 null로 넘김
			if (rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setU_Num(rs.getInt("u_num"));
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
	
	// 비밀번호 재설정 메서드
	public MemberDTO resetPass(String hashedPassword, String u_salt, int u_num) {

		memberdto = null;

		try {

			con = new SQLConnection().getConnection();
			String SQL = "UPDATE user SET u_pass = ?, u_salt = ? WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, hashedPassword);
			pstmt.setString(2, u_salt);
			pstmt.setInt(3, u_num);
			int rs = pstmt.executeUpdate();

			// 값 일치하면 memberdto 객체생성후 값저장후 넘김 값일치 없는 경우 memberdto는 null로 넘김
			memberdto = (rs != 0) ? new MemberDTO() : null;

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null;
		} finally {
			dbClose();
		}
		return memberdto;
	} // resetPass
	
	// user2의 멤버 정보를 얻기 위한 메서드
	public MemberDTO getMemberInfo(int u_num) {

		memberdto = null;

		try {

			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT * FROM user2 WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, u_num);
			rs = pstmt.executeQuery();

			// 유저번호에 따른 개인정보 저장
			if (rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setU_Name(rs.getString("u_name"));
				memberdto.setU_Phone(rs.getString("u_phone"));
				memberdto.setU_Email(rs.getString("u_email"));
				memberdto.setU_RegDate(rs.getTimestamp("u_regdate"));
				memberdto.setU_Count(rs.getInt("u_count"));
				memberdto.setU_Point(rs.getInt("u_point"));
			} else {
				memberdto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}

		return memberdto;
	}// getMemberInfo
    
	// 유저 비활성화(탈퇴)를 위한 메서드
	public boolean userDisable(int u_num) {

		boolean isDisabled = false;

		try {

			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "UPDATE user2 SET u_disabled = 1 WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, u_num);
			int rs = pstmt.executeUpdate();

			// 업데이트 성공유무 결정
			isDisabled = (rs != 0) ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
			memberdto = null; // 에러 발생시 멤버 DTO 저장소해제
		} finally {
			dbClose();
		}

		return isDisabled;
	}
    
	// 회원탈퇴를 위한 유저번호와 날짜를 저장하기 위한 메서드
	public boolean insertCencel(int u_num, Timestamp del_date) {

		boolean result = false;

		try {

			// db연결
			con = new SQLConnection().getConnection();

			// SQL 쿼리 실행(유저탈퇴 테이블에 값 삽입)
			String SQL = "INSERT INTO cancel(s_num, del_date) VALUE(?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, u_num);
			pstmt.setTimestamp(2, del_date);
			int rs = pstmt.executeUpdate();

			result = (rs != 0) ? true : false;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}

		return result;

	}
    
	// 유저의 차단 or 탈퇴 검증 유무 메서드
	public MemberDTO isUserDisabled(MemberDTO memberdto) {

		try {
			// db연결
			con = new SQLConnection().getConnection();

			String SQL = "SELECT u_disabled FROM user2 WHERE u_num = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, memberdto.getU_Num());
			rs = pstmt.executeQuery();

			// member 비활성화 값 유무 저장
			if (rs.next()) {
				memberdto.setU_disabled(rs.getInt("u_disabled"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return memberdto;
	}
	
	public void dbClose() {

		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		
		if (rs2 != null) {try {rs2.close();} catch (SQLException e) {e.printStackTrace();}}

		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		
		if (pstmt2 != null) {try {pstmt2.close();} catch (SQLException e) {e.printStackTrace();}}

		if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
}

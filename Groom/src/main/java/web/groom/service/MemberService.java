package web.groom.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import web.groom.dto.MemberDTO;
import web.groom.security.MemberSecurity;
import web.groom.dao.MemberDAO;

public class MemberService {

	MemberDAO memberdao = null;
	MemberDTO memberdto = null;
	
	// 멤버 회원가입 처리 서비스
	public MemberDTO insertMember(HttpServletRequest request) {

		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 리퀘스트 파라미터값 변수에 저장
			String getId = request.getParameter("u_id");
			String getPass = request.getParameter("u_pass");
			String getName = request.getParameter("u_name");
			String getPhone = request.getParameter("u_phone");
			String getEmail = request.getParameter("u_email");
			Timestamp getDate = new Timestamp(System.currentTimeMillis());

			// MemberDTO에 객체생성후 파라미터값 저장
			memberdto = new MemberDTO();
			memberdto.setU_Id(getId);
			memberdto.setU_Pass(getPass);
			memberdto.setU_Name(getName);
			memberdto.setU_Phone(getPhone);
			memberdto.setU_Email(getEmail);
			memberdto.setU_RegDate(getDate);

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdao = new MemberDAO();

			// 중복여부 검사 id, phone, email
			MemberDTO IdCheck = memberdao.searchId(memberdto.getU_Id());
			MemberDTO emailCheck = memberdao.searchEmail(memberdto.getU_Email());
			MemberDTO phoneCheck = memberdao.searchPhone(memberdto.getU_Phone());
			if (IdCheck == null && emailCheck == null && phoneCheck == null) {
				// 중복검증 확인후 db에 데이터 삽입 준비
				memberdto = memberdao.insertMember(memberdto);
			} else {
				// 중복있을시 DB삽입 거부
				memberdto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;
	} // insertmember
	
	// 로그인 관련 처리 서비스
	public MemberDTO userCheck(HttpServletRequest request) {

		memberdto = null;

		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 리퀘스트 파라미터값 변수에 저장
			String getid = request.getParameter("u_id");
			String getpass = request.getParameter("u_pass");

			// MemberDAO에 값을 전달하고 로직처리 수행
			MemberDAO memberDAO = new MemberDAO();

			memberdto = memberDAO.userCheck(getid, getpass);
			// 유저 비활성화(탈퇴) 유무 검증
			if (memberdto != null) {
				memberdto = memberDAO.isUserDisabled(memberdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;
	} // usercheck
	
	// 아이디 찾기 및 검증 처리 서비스
	public MemberDTO searchId(HttpServletRequest request) {

		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 리퀘스트 파라미터값 변수에 저장
			String getId = request.getParameter("u_id");

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdto = new MemberDAO().searchId(getId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;
	} // searchid
	
	// 핸드폰 번호 중복 검증 처리 서비스
	public MemberDTO searchPhone(HttpServletRequest request) {

		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 리퀘스트 파라미터값 변수에 저장
			String getPhone = request.getParameter("u_phone");

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdto = new MemberDAO().searchPhone(getPhone);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;
	}// searchPhone
	
	// 이메일 중복 검증 처리 서비스
	public MemberDTO searchEmail(HttpServletRequest request) {

		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 리퀘스트 파라미터값 변수에 저장
			String getEmail = request.getParameter("u_email");

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdto = new MemberDAO().searchEmail(getEmail);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;
	}// searchEmail
	
	// 아이디 찾기 처리 서비스
	public MemberDTO findId(HttpServletRequest request) {

		try {
			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 리퀘스트 파라미터값 변수에 저장
			String name = request.getParameter("u_name");
			String email = request.getParameter("u_email");

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdto = new MemberDAO().findId(name, email);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;
	} // finid
	
	// 비밀번호 찾기 처리 서비스
	public MemberDTO findPass(HttpServletRequest request) {

		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 리퀘스트 파라미터값 변수에 저장
			String getId = request.getParameter("u_id");
			String getEmail = request.getParameter("u_email");

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdto = new MemberDAO().findPass(getId, getEmail);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;

	}// findpass
	
	// 비밀번호 재설정 관련 처리 서비스
	public MemberDTO resetPass(HttpServletRequest request) {

		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 입력받은 비밀번호, 유저번호 변수에저장
			String getPass = request.getParameter("u_pass");
			int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

			// 비밀번호 재설정을 위한 해시비밀번호 얻기
			// 시큐리티 클래스 객체생성
			MemberSecurity security = new MemberSecurity();

			// 솔트 재생성
			String salt = security.generateSalt();

			// 비밀번호 재설정
			String hashedPassword = security.hashPassword(getPass, salt);

			// MemberDAO에 재설정 값을 전달하고 로직처리 수행
			memberdto = new MemberDAO().resetPass(hashedPassword, salt, u_num);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberdto;

	}// findpass
	
	// 유저 정보 얻기 관련 처리 서비스
	public MemberDTO getMemberInfo(HttpServletRequest request) {

		// 유저 번호 가져오기
		int num = Integer.parseInt((String) request.getSession().getAttribute("num"));

		// MemberDAO에 값을 전달하고 로직처리 수행
		memberdto = new MemberDAO().getMemberInfo(num);

		return memberdto;
	}// getMemberInfo
	
	// 유저 비활성화(탈퇴) 관련 처리 서비스
	public boolean userDisable(HttpServletRequest request) {

		boolean isDisabled = false;

		boolean result = false;
		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");

			// 유저번호 변수에저장
			int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

			// MemberDAO에 값을 전달하고 로직처리 수행
			MemberDAO memberDAO = new MemberDAO();
			isDisabled = memberDAO.userDisable(u_num);

			if (isDisabled) {
				Timestamp del_date = new Timestamp(System.currentTimeMillis());
				result = memberDAO.insertCencel(u_num, del_date);
			} else {
				System.out.println("회원탈퇴정보 입력안됨");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}// userDisable
} // end_of_MemberService

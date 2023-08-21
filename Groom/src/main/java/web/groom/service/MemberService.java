package web.groom.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import web.groom.dto.MemberDTO;
import web.groom.dao.MemberDAO;

public class MemberService {
	
	MemberDAO memberdao = null;

	public void insertMember(HttpServletRequest request) {
		
		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			
			// 리퀘스트 파라미터값 변수에 저장
			String getId = request.getParameter("id");
			String getPass = request.getParameter("pass");
			String getName = request.getParameter("name");
			String getPhone = request.getParameter("Phone");
			String getEmail = request.getParameter("email");
			Timestamp getDate = new Timestamp(System.currentTimeMillis());

			// MemberDTO에 객체생성후 파라미터값 저장
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(getId);
			memberDTO.setPass(getPass);
			memberDTO.setName(getName);
			memberDTO.setPhone(getPhone);
			memberDTO.setEmail(getEmail);
			memberDTO.setRegDate(getDate);

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdao = new MemberDAO();

			memberdao.insertMember(memberDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public MemberDTO userCheck(HttpServletRequest request) {
		
		MemberDTO memberDTO = null;
		
		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			
			// 리퀘스트 파라미터값 변수에 저장
			String getid = request.getParameter("id");
			String getpass = request.getParameter("pass");

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberDTO = new MemberDAO().userCheck(getid, getpass);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberDTO;
	}
}

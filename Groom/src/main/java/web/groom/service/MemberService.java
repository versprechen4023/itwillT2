package web.groom.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import web.groom.dto.MemberDTO;
import web.groom.dao.MemberDAO;

public class MemberService {
	
	MemberDAO memberdao = null;
	MemberDTO memberdto = null;
	
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
			memberdto.setId(getId);
			memberdto.setPass(getPass);
			memberdto.setName(getName);
			memberdto.setPhone(getPhone);
			memberdto.setEmail(getEmail);
			memberdto.setRegDate(getDate);

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdao = new MemberDAO();

			memberdto = memberdao.insertMember(memberdto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberdto;
	}

	public MemberDTO userCheck(HttpServletRequest request) {
		
		memberdto = null;
		
		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			
			// 리퀘스트 파라미터값 변수에 저장
			String getid = request.getParameter("u_id");
			String getpass = request.getParameter("u_pass");

			// MemberDAO에 값을 전달하고 로직처리 수행
			memberdto = new MemberDAO().userCheck(getid, getpass);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberdto;
	}
	
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
	}
	
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
	}
	
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
	}
}

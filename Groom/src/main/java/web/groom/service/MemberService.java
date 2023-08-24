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
	} //insertmember

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
	} //usercheck 
	
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
	
	public MemberDTO findid (HttpServletRequest request) {
	
	MemberDTO memberDTO = new MemberDTO();
	
	 memberDTO = null;
	
	 try {
		 
		 String name = request.getParameter("u_name");
		 String email = request.getParameter("u_email");
		 MemberDAO memberDAO = new MemberDAO();
		 
		 memberDTO= memberDAO.findid(name, email);
		 
		 
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
	}
	
	return memberDTO;
	} // finid

	public MemberDTO findpass (HttpServletRequest request) {
	
	MemberDTO memberDTO = new MemberDTO();
	
	 memberDTO = null;
	
	 try {
		 
		 String id = request.getParameter("u_id");
		 String email = request.getParameter("u_email");
		 MemberDAO memberDAO = new MemberDAO();
		 
		 memberDTO= memberDAO.findid(id, email);
		 
		 
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
	}
	
	return memberDTO;
	} // findpass

	public MemberDTO getMemberInfo(HttpServletRequest request) {
		
		//유저 번호 가져오기
		int num = Integer.parseInt((String)request.getSession().getAttribute("num"));
		
		// MemberDAO에 값을 전달하고 로직처리 수행
		memberdto = new MemberDAO().getMemberInfo(num);
		
		return memberdto;
	}
}

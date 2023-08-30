package web.groom.service;

import java.util.List;


import web.groom.dao.AdminDAO;
import web.groom.dto.AdminDTO;
import web.groom.dto.MemberDTO;

public class AdminService {
	AdminDAO adminDAO = null;

	public List<MemberDTO> getMemberList() {
		System.out.println("AdminService getMemberList()");
		List<MemberDTO> memberList = null;
		try {
			adminDAO = new AdminDAO();
			memberList = adminDAO.getMemberList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}//getMemberList() [회원정보 목록]

	public List<AdminDTO> getEmpList() {
		System.out.println("AdminService getEmpList()");
		List<AdminDTO> empList = null;
		try {
			adminDAO = new AdminDAO();
			empList = adminDAO.getEmpList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}//getMemberList() [직원목록]
	
	public AdminDTO getCount() {
		System.out.println("AdminService getCount()");
		AdminDTO adminDTO = null;
		try {
			adminDAO = new AdminDAO();
			adminDTO = adminDAO.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminDTO;
	}//getCount [관리자메인 count]
	
	

}// class

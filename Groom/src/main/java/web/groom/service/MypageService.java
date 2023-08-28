package web.groom.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.MemberDAO;
import web.groom.dao.MypageDAO;
import web.groom.dto.MemberDTO;
import web.groom.dto.MypageDTO;

public class MypageService {
	MypageDAO mypagedao = null;
	MypageDTO mypagedto = null;
	
	public MypageDTO MemberInfo(HttpServletRequest request) {
		//유저 번호 가져오기
		int num = Integer.parseInt((String)request.getSession().getAttribute("num"));
		
		// MypageDAO에 값을 전달하고 로직처리 수행
		mypagedto = new MypageDAO().getMemberInfo(num);
		
		return mypagedto;
	}

public MypageDTO insertMypet(HttpServletRequest request) {
		
		try {

			// 한글 인코딩 처리
			request.setCharacterEncoding("UTF-8");
			
			// 리퀘스트 파라미터값 변수에 저장
			String getPetName = request.getParameter("petname");
			String getPetBreed = request.getParameter("petbreed");
			String getPetGender = request.getParameter("petgender");
			String getPetNeuter = request.getParameter("petneuter");
			String getPetComment = request.getParameter("petcomment");
			int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
			
			System.out.println(getPetName);
			System.out.println(getPetBreed);
			System.out.println(getPetGender);
			System.out.println(getPetNeuter);
			System.out.println(getPetComment);
			System.out.println(u_num);

			// 객체생성후 파라미터값 저장
			mypagedto = new MypageDTO();
			mypagedto.setNum(u_num);
			mypagedto.setPetName(getPetName);
			mypagedto.setPetBreed(getPetBreed);
			mypagedto.setPetGender(getPetGender);
			mypagedto.setPetNeuter(getPetNeuter);
			mypagedto.setPetComment(getPetComment);
	
			

			// MemberDAO에 값을 전달하고 로직처리 수행
			mypagedao = new MypageDAO();
			
			mypagedao.insertMypet(mypagedto);
			
	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mypagedto;
	}

public MypageDTO MypetInfo(HttpServletRequest request) {
	//유저 번호 가져오기
	int num = Integer.parseInt((String)request.getSession().getAttribute("num"));
	
	// MypageDAO에 값을 전달하고 로직처리 수행
	mypagedto = new MypageDAO().getMypetinfo(num);
	
	return mypagedto;
}


	public MypageDTO getMypetinfo(int num) {
		System.out.println("MypageService getMypetinfo()");		
		mypagedto = null;
		try {
			// MemberDAO 객체생성
			mypagedao = new MypageDAO();
			// memberDTO  = getMember(id) 메서드 호출
			mypagedto = mypagedao.getMypetinfo(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mypagedto;
	}
	
	public void updateMypetinfo(HttpServletRequest request) {
		System.out.println("MemberService updateMypetinfo()");
		try {
			// request 한글처리
			request.setCharacterEncoding("utf-8");
			// request 파라미터 가져오기(id,pass,name)
			String getPetName = request.getParameter("pet_name");
			String getPetBreed = request.getParameter("pet_breed");
			String getPetGender = request.getParameter("pet_gender");
			String getPetNeuter = request.getParameter("pet_neuter");
			String getPetComment = request.getParameter("pet_comment");
			// MemberDTO 객체생성 
			MypageDTO mypageDTO = new MypageDTO();
			// set메서드 호출 파라미터값 저장
			mypagedto = new MypageDTO();
			mypagedto.setPetName(getPetName);
			mypagedto.setPetBreed(getPetBreed);
			mypagedto.setPetGender(getPetGender);
			mypagedto.setPetNeuter(getPetNeuter);
			mypagedto.setPetComment(getPetComment);
			// MemberDAO 객체생성
			mypagedao = new MypageDAO();
			// updateMember(memberDTO) 메서드 호출
			mypagedao.updateMember(mypageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//updateMember()




}

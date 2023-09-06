package web.groom.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



import web.groom.dao.AdminDAO;
import web.groom.dao.MemberDAO;
import web.groom.dao.MypageDAO;
import web.groom.dto.MemberDTO;
import web.groom.dto.MypageDTO;
import web.groom.dto.OrderReservationDTO;

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
			// MypageDAO 객체생성
			mypagedao = new MypageDAO();
			// getMypetinfo(num) 메서드 호출
			mypagedto = mypagedao.getMypetinfo(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mypagedto;
	}
	
	public void updateMypet(HttpServletRequest request) {
		System.out.println("MypageService updateMypetinfo()");
		try {
			// request 한글처리
			request.setCharacterEncoding("utf-8");
			// 리퀘스트 파라미터값 변수에 저장
			String getPetName = request.getParameter("petname");
			String getPetBreed = request.getParameter("petbreed");
			String getPetGender = request.getParameter("petgender");
			String getPetNeuter = request.getParameter("petneuter");
			String getPetComment = request.getParameter("petcomment");
			int getPetNum = Integer.parseInt(request.getParameter("pet_num"));
			int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
			System.out.println(getPetNum);
				
			// 객체생성후 파라미터값 저장
			mypagedto = new MypageDTO();
			mypagedto.setNum(u_num);
			mypagedto.setPetNum(getPetNum);
			mypagedto.setPetName(getPetName);
			mypagedto.setPetBreed(getPetBreed);
			mypagedto.setPetGender(getPetGender);
			mypagedto.setPetNeuter(getPetNeuter);
			mypagedto.setPetComment(getPetComment);
			
			// MypageDAO 객체생성
			mypagedao = new MypageDAO();
			// updateMypet(mypagedto) 메서드 호출
			mypagedao.updateMypet(mypagedto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//updat()

	public List<MypageDTO> getMypetList(HttpServletRequest request) {
		System.out.println("MypageService getMypetList");
		List<MypageDTO> mypetList = null;
		int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
		
		try {
			mypagedao = new MypageDAO();
			mypetList = mypagedao.getMypetList(u_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mypetList;
	}//getMypetList
	
	public MypageDTO MypetTestInfo(HttpServletRequest request) {
		//유저 번호 가져오기
		int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
		
		// 펫 번호 가져오기
		int pet_num = Integer.parseInt(request.getParameter("petlist"));
		
		mypagedto = new MypageDAO().MypetTestInfo(u_num, pet_num);
		
		return mypagedto;
	}

//	public void deleteMypet(HttpServletRequest request) {
//	    System.out.println("MypageService deletemypet()");
//	    try {
//	        // 펫 번호 가져오기
//	        int pet_num = Integer.parseInt(request.getParameter("pet_num"));
//	        
//	        // MypageDAO 객체생성
//	        mypagedao = new MypageDAO();
//	        // deleteMypet(pet_num) 메서드 호출
//	        mypagedao.deleteMypet(pet_num);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
	
	public void deleteMypet(int pet_num) {
	    MypageDAO mypagedao = new MypageDAO();
	    mypagedao.deleteMypet(pet_num);
	}


	public MypageDTO modifyinfo(HttpServletRequest request) {
		
		try {
			// request 한글처리
			request.setCharacterEncoding("utf-8");
			// request 파라미터 가져오기(phone, email, u_num)
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
		
						// MypageDTO 객체생성 
			MypageDTO mypageDTO = new MypageDTO();
			// set메서드 호출 파라미터값 저장
			mypageDTO.setPhone(phone);
			mypageDTO.setEmail(email);
			mypageDTO.setNum(u_num);
			// MemberDAO 객체생성
			mypagedao = new MypageDAO();
		
			mypagedao.modifyinfo(mypageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		} return mypagedto;
	}

	public MypageDTO userCheck(HttpServletRequest request) {
		System.out.println("MypageService userCheck()");
		MypageDTO memberDTO = null;
		try {
			//한글처리
			request.setCharacterEncoding("utf-8");
			//id,pass 파라미터 값 가져오기
			String id=request.getParameter("id");
			String pass=request.getParameter("pass");
			// MemberDTO 저장
			MypageDTO mypageDTO2 = new MypageDTO();
			mypageDTO2.setId(id);
			mypageDTO2.setPass(pass);
			// MemberDAO 객체생성
			mypagedao = new MypageDAO();
			// memberDTO =  userCheck(memberDTO2) 메서드 호출
			mypagedto=mypagedao.userCheck(mypageDTO2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mypagedto;
	}

	public List<OrderReservationDTO> getReservationList(HttpServletRequest request) {
		System.out.println("AdminService getReservationList()");
		List<OrderReservationDTO> reservationList = null;
		int u_num = Integer.parseInt((String)request.getSession().getAttribute("num"));
	
		try {
			mypagedao = new MypageDAO();
			reservationList = mypagedao.getReservationList(u_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservationList;
	}









}

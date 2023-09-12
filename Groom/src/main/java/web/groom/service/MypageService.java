package web.groom.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.groom.dao.MypageDAO;
import web.groom.dto.MypageDTO;
import web.groom.dto.OrderReservationDTO;

public class MypageService {
	MypageDAO mypagedao = null;
	MypageDTO mypagedto = null;
	
	// 유저 정보 가져오는 서비스
	public MypageDTO MemberInfo(HttpServletRequest request) {
		
		// 유저 번호 가져오기
		int num = Integer.parseInt((String) request.getSession().getAttribute("num"));

		// MypageDAO에 값을 전달하고 로직처리 수행
		mypagedto = new MypageDAO().getMemberInfo(num);

		return mypagedto;
	} // MemberInfo
	
	// 펫 정보 입력 서비스
	public MypageDTO insertMypet(HttpServletRequest request) {

		try {

			// 리퀘스트 파라미터값 변수에 저장
			String getPetName = request.getParameter("petname");
			String getPetBreed = request.getParameter("petbreed");
			String getPetGender = request.getParameter("petgender");
			String getPetNeuter = request.getParameter("petneuter");
			String getPetComment = request.getParameter("petcomment");
			int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

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
			mypagedto = new MypageDAO().insertMypet(mypagedto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mypagedto;
	} // insertMypet
	
	// 유저 펫 정보 가져오는 서비스
	public MypageDTO MypetInfo(HttpServletRequest request) {
		// 유저 번호 가져오기
		int num = Integer.parseInt((String) request.getSession().getAttribute("num"));

		// MypageDAO에 값을 전달하고 로직처리 수행
		mypagedto = new MypageDAO().getMypetinfo(num);

		return mypagedto;
	} // MypetInfo

// 사용되지 않는 메서드 주석처리
//	public MypageDTO getMypetinfo(int num) {
//		System.out.println("MypageService getMypetinfo()");
//		mypagedto = null;
//		try {
//			// MypageDAO 객체생성
//			mypagedao = new MypageDAO();
//			// getMypetinfo(num) 메서드 호출
//			mypagedto = mypagedao.getMypetinfo(num);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mypagedto;
//	}
	
	// 유저 펫 정보 업데이트하는 서비스
	public boolean updateMypet(HttpServletRequest request) {
		
		boolean result = false;
		
		System.out.println("MypageService updateMypetinfo()");
		
		try {

			// 리퀘스트 파라미터값 변수에 저장
			String getPetName = request.getParameter("petname");
			String getPetBreed = request.getParameter("petbreed");
			String getPetGender = request.getParameter("petgender");
			String getPetNeuter = request.getParameter("petneuter");
			String getPetComment = request.getParameter("petcomment");
			int getPetNum = Integer.parseInt(request.getParameter("pet_num"));
			int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));
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

			// updateMypet(mypagedto) 메서드 호출
			result = new MypageDAO().updateMypet(mypagedto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}// updat()
	
	// 유저 펫 정보 가져오는 서비스(List)
	public List<MypageDTO> getMypetList(HttpServletRequest request) {
		System.out.println("MypageService getMypetList");
		
		List<MypageDTO> mypetList = null;
		
		// 유저 번호 세션값 변수에 저장
		int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

		try {
			// getMypetList(u_num) 메서드 호출
			mypetList = new MypageDAO().getMypetList(u_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mypetList;
	}// getMypetList
	
	// updatemypet.jsp 에서 사용되는 내 펫 정보 가져오기 서비스
	public MypageDTO MypetTestInfo(HttpServletRequest request) {
		// 유저 번호 가져오기
		int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

		// 펫 번호 가져오기
		int pet_num = Integer.parseInt(request.getParameter("petlist"));

		mypagedto = new MypageDAO().MypetTestInfo(u_num, pet_num);

		return mypagedto;
	} // MypetTestInfo

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
	
	// 유저 펫 정보 삭제하는 서비스
	public boolean deleteMypet(HttpServletRequest request) {
		
		boolean result = false;
		
		// 리퀘스트 파라미터값 변수에 저장
		int pet_num = Integer.parseInt(request.getParameter("pet_num"));
		
		// deleteMypet(pet_num) 메서드 호출
		result = new MypageDAO().deleteMypet(pet_num);
		
		return result;
	} // deleteMypet
	
	// 유저 정보 수정하는 서비스
	public boolean modifyInfo(HttpServletRequest request) {
		
		boolean result = false;
		
		try {

			// request 파라미터 가져오기(phone, email, u_num)
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

			// MypageDTO 객체생성
			MypageDTO mypageDTO = new MypageDTO();
			// set메서드 호출 파라미터값 저장
			mypageDTO.setPhone(phone);
			mypageDTO.setEmail(email);
			mypageDTO.setNum(u_num);
			
			// modifyInfo(mypageDTO) 메서드 호출
			result = new MypageDAO().modifyInfo(mypageDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // modifyInfo

// 미사용 메서드 비활성화 처리
//	public MypageDTO userCheck(HttpServletRequest request) {
//		System.out.println("MypageService userCheck()");
//		
//		try {
//			// 한글처리
//			request.setCharacterEncoding("utf-8");
//			// id,pass 파라미터 값 가져오기
//			String id = request.getParameter("id");
//			String pass = request.getParameter("pass");
//			
//			// MemberDTO 저장
//			MypageDTO mypageDTO2 = new MypageDTO();
//			mypageDTO2.setId(id);
//			mypageDTO2.setPass(pass);
//	
//			// memberDTO = userCheck(memberDTO2) 메서드 호출
//			mypagedto = new MypageDAO().userCheck(mypageDTO2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mypagedto;
//	}
	
	// 유저 예약 정보 가져오는 메서드
	public List<OrderReservationDTO> getReservationList(HttpServletRequest request) {
		
		System.out.println("AdminService getReservationList()");
		List<OrderReservationDTO> reservationList = null;
		// 유저 번호 가져오기
		int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

		try {
			// getReservationList(u_num) 메서드 호출
			reservationList = new MypageDAO().getReservationList(u_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservationList;
	} // getReservationList
	
	// 유저 예약 정보 변경하는 메서드
	public boolean changeRes(HttpServletRequest request) {

		boolean result = false;
		
		// 리퀘스트 파라미터 값 변수에 저장
		String res_day = request.getParameter("datepicker");
		String res_time = request.getParameter("timepicker");

		int res_num = Integer.parseInt(request.getParameter("res_num"));
		
		// 유저 번호 가져오기
		int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));

		try {
			// changeRes(res_day, res_time, res_num, u_num) 메서드 호출
			result = new MypageDAO().changeRes(res_day, res_time, res_num, u_num);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // changeRes
	
	// 유저 예약 취소 하는 메서드
	public boolean cancelRes(HttpServletRequest request) {
		
		boolean result = false;
		// 리퀘스트 파라미터 값 변수에 저장
		int res_num = Integer.parseInt(request.getParameter("res_num"));
		
		// 유저 번호 가져오기
		int u_num = Integer.parseInt((String) request.getSession().getAttribute("num"));
		
		try {
			// cancelRes(res_num, u_num) 메서드 호출
			result = new MypageDAO().cancelRes(res_num, u_num);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // cancelRes

}// end_of_MypageService

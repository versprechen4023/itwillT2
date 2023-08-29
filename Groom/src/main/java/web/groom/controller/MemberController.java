package web.groom.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.groom.dto.MemberDTO;

import web.groom.service.MemberService;




@WebServlet("*.me") //.me 멤버 어노테이션 매핑 선언
public class MemberController extends HttpServlet {
	
	MemberService ser = null;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 //페이지이동
		
		 //로그인 페이지 이동
		 if (sPath.equals("/login.me")) {
			 webForward(request, response, "member", "login");
	     }// end_of_login.me
		 
		 //로그인 로직 수행
		 if (sPath.equals("/loginPro.me")) {
			
			//멤버서비스 객체생성
			ser = new MemberService();
			
			//메서드호출 및 값 넘겨주기
			MemberDTO memberdto = ser.userCheck(request);
			
			//memberdto 값 존재여부로 로그인 성공여부 확인
			if(memberdto != null) {
				
				System.out.println("로그인성공");
				
				//값이 존재하면 세션에 id저장
				HttpSession session = request.getSession();
				
				//멤버dto값 출력
				System.out.println(memberdto);
				
				//세션에 id, salt, role, userNum 값 저장
				session.setAttribute("id", memberdto.getU_Id());
				session.setAttribute("salt", memberdto.getU_Salt());
				session.setAttribute("role", memberdto.getU_Role());
				session.setAttribute("num", String.valueOf(memberdto.getU_Num()));
				
				//세션저장완료후 메인으로 이동
				response.sendRedirect("main.gr");
			} else {
				response.sendRedirect("loginError.er");
			}
	     }//end_of_loginPro.me
		 
		 //회원가입 페이지 이동
		 if (sPath.equals("/signup.me")) {
			 webForward(request, response, "member", "signup");   
	     }//end_of_singup.me
		 
		 // 회원가입 로직 수행
		 if (sPath.equals("/signupPro.me")) {

			//멤버서비스 객체생성
			ser = new MemberService();
				
			//메서드호출(리퀘스트 값 넘겨주기)
			MemberDTO memberdto = ser.insertMember(request);
			
			if(memberdto != null) {
				System.out.println("회원가입 성공");
				//세션초기화
				HttpSession session = request.getSession();
				session.invalidate();
			} else {
				System.out.println("회원가입 실패");
				//세션초기화
				HttpSession session = request.getSession();
				session.invalidate();
			}
			//로그인 화면이동
			response.sendRedirect("login.me");
				
		 }//end_of_singupPro.me
		 
		 if (sPath.equals("/findid.me")) {
			 webForward(request, response, "member", "findid");   
	     }
		 
         if (sPath.equals("/findidresult.me")) {
	
			 
			 ser = new MemberService();
			 MemberDTO memberDTO = ser.findid(request);
			 String name = request.getParameter("u_name");
			 String email = request.getParameter("u_email");
			 
			 System.out.println(name);
			 System.out.println(email);
			 
			 System.out.println(memberDTO);
			 
			 if ( memberDTO != null ) {
				 
			 request.setAttribute("memberDTO" , memberDTO);
			 webForward(request, response, "member", "findidresult");
				  
				 System.out.println("아이디 찾기 성공 ");
			 } else  {
				 response.sendRedirect("searchError.er");
				 System.out.println("아이디 찾기 실패 ");
			 }
			 
	     }
         
         if (sPath.equals("/findpass.me")) {
			 webForward(request, response, "member", "findpass");   
	     }
         
         if (sPath.equals("/findpassresult.me")) {
			 
        	//멤버서비스 객체생성
 			ser = new MemberService();
 				
 			//메서드호출(리퀘스트 값 넘겨주기)
			MemberDTO memberDTO = ser.findPass(request);
			
			if ( memberDTO != null ) {
				
			//값이 존재하면 세션에 유저 번호 저장
			HttpSession session = request.getSession();
			
			session.setAttribute("num", String.valueOf(memberDTO.getU_Num()));
			
			webForward(request, response, "member", "resetpassword");

		     System.out.println("비밀번호 재설정가능");
			} else  {
		     response.sendRedirect("searchError.er");
			 System.out.println("비밀번호 재설정안됨");
			}
			 
	     }
         
         if (sPath.equals("/resetpassword.me")) {
			 
         	//멤버서비스 객체생성
  			ser = new MemberService();
  			
  			
  			//메서드호출(리퀘스트 값 넘겨주기)
 			MemberDTO memberDTO = ser.resetPass(request);
 			
 			if ( memberDTO != null ) {
 			
 			response.sendRedirect("login.me");

 			System.out.println("비밀번호 재설정됨");
 			} else  {
 			System.out.println("비밀번호 재설정안됨");
 			}
 			
 			
 	     }
		 
		 if (sPath.equals("/modifyinfo.me")) {
			 webForward(request, response, "member", "modifyinfo");   
	     }
		 
		 //로그아웃 로직 수행
		 if (sPath.equals("/logout.me")) {
			//세션초기화
			HttpSession session = request.getSession();
			session.invalidate();
			//메인으로 
			response.sendRedirect("main.gr"); 
	     }//end_of_logout.me
		 
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+pageName+".jsp").forward(request, response);
	}

}

package web.groom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.email.MemberEmail;
import web.groom.email.VerifyEmail;

@WebServlet("*.aj") //.Ajax Ajax관련 어노테이션 매핑 선언
public class AjaxController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
				 	 
		 //AJAX관련 아이디중복검사쪽
		 if (sPath.equals("/test.aj")) {
			 
			     System.out.println("ajax테스트");
			     boolean result = false;
				 response.setContentType("text/html; charset=UTF-8");
				 String id = request.getParameter("id");
				
				  System.out.println(id);
				  String get = "test";
				  PrintWriter out = response.getWriter();
				  if(id.equals(get)){
					  result = true;
				  }else{
					  result = false;
				  }
				  out.print(Boolean.toString(result));
				  out.close();
	     }
		 
		 //AJAX관련 이메일 인증코드 발송
		 if (sPath.equals("/email.aj")) {
			 
			 MemberEmail email = new MemberEmail();
		     email.sendEmail(request); //이메일 값 넘겨주기
         }
		 
		//AJAX관련 이메일 인증번호 검증
		 if (sPath.equals("/verify.aj")) {
			 
			 String userVerificationCode = request.getParameter("verificationCode");
			 
			 //인증번호테스트하기위한 부분
			 System.out.println("ajax인증번호테스트");
			 System.out.println(userVerificationCode);
			 
			 //인증번호 검증을 boolean값으로 가져옴
			 boolean result = new VerifyEmail().verifycationEmail(request);
			 
			 //콜백함수에 최종결과값 출력
			 response.setContentType("text/html; charset=UTF-8");
			 PrintWriter out = response.getWriter();
			 out.print(Boolean.toString(result));
			 out.close();
         }
		
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

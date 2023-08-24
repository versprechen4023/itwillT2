package web.groom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.MemberDTO;

import web.groom.service.MemberService;

@WebServlet("*.or") //.or 예약오더페이지 어노테이션 매핑 선언
public class OrderController extends HttpServlet {
	
	MemberService ser = null;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 //페이지이동
		 if (sPath.equals("/something.or")) {
	            
	     }
		 
		 //예약하기페이지 이동
		 if (sPath.equals("/myorder.or")) {
			 
			 // 유저의 정보를 가져오기위한 멤버서비스 객체생성
			 ser = new MemberService();
			 
			 // 유저 정보 가져오기
			 MemberDTO memberInfo = ser.getMemberInfo(request);
			 
			 // request에 유저 정보 있는 memberInfo 저장
			 request.setAttribute("memberInfo", memberInfo);
			 
			 //임시출력
			 System.out.println(memberInfo);
			 
			 // 멤버인포에 정상적으로 값이 있으면 정보들고 myorder페이지로 페이지이동
			 if(memberInfo != null) {
			 webForward(request, response, "order", "myorder");
			 } else {
				 System.out.println("세션만료됨 에러발생");
			 }
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

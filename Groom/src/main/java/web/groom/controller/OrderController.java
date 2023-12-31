package web.groom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.groom.dto.MemberDTO;
import web.groom.dto.OrderReservationDTO;
import web.groom.dto.OrderinfoDTO;
import web.groom.javascript.JSForward;
import web.groom.service.MemberService;
import web.groom.service.OrderService;

@WebServlet("*.or") //.or 예약오더페이지 어노테이션 매핑 선언
public class OrderController extends HttpServlet {
	
	MemberService ser = null;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		 
		 // 예약하기 페이지 order/myorder.jsp
		 if (sPath.equals("/myorder.or")) {
			 
			 //유저 세션 검증
			 String id = (String)request.getSession().getAttribute("id");
			 
			 //세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			 if (id == null){
				 JSForward.locationHref(response, "로그인 후 이용해주세요", "login.me");
			 } 
			 
			 //정상적으로 로그인되어있을 경우 유저정보를 가져오기위한 로직
			 else {
			 // 유저의 정보를 가져오기위한 멤버서비스 객체생성
			 ser = new MemberService();
			 
			 // 유저 정보 가져오기
			 MemberDTO memberInfo = ser.getMemberInfo(request);
			 
			 // request에 유저 정보 있는 memberInfo 저장
			 request.setAttribute("memberInfo", memberInfo);
			 
			 // 멤버인포에 정상적으로 값이 있으면 정보들고 myorder페이지로 페이지이동
			 if(memberInfo != null) {
			 webForward(request, response, "order", "myorder");
			 } else {
				 JSForward.locationHref(response, "다시 로그인 후 이용해주세요", "login.me");
			 }
			 }
	     } // end_of_myorder.or
		 
		 // 예약하기 최종확인 페이지 order/myorderCheckout.jsp
		 if (sPath.equals("/myorderCheckout.or")) {
			 
			 //유저 세션 검증
			 String id = (String)request.getSession().getAttribute("id");
			 
			 //세션에 id값이 존재하지않을 경우 로그인 페이지로 이동
			 if (id == null){
				 JSForward.locationHref(response, "로그인 후 이용해주세요", "login.me");
			 } 
			 
			 else {
				 
				 //정보들을 가지고 오기 위한 메서드 호출
				 OrderinfoDTO orderInfoDTO = new OrderService().getOrderInfo(request);
				 
				 // request에 오더 정보 있는 orderInfo 저장
				 request.setAttribute("orderInfo", orderInfoDTO);
				 
				 System.out.println(orderInfoDTO);
				 
				 webForward(request, response, "order", "myorderCheckout");
			 }
			 
	     } // end_of_myorderCheckout.or
		 
		 // 예약 처리 및 DB에 저장하기 위한 로직
		 if (sPath.equals("/orderReservation.or")) {
				 
				 // 예약처리 및 결과값 반환을 위한 메서드 호출
				 OrderReservationDTO orderReserv = new OrderService().insertOrderReserv(request);
				 
				if (orderReserv != null) {
					System.out.println("예약처리 완료");
					JSForward.locationHref(response, "예약이 완료되었습니다", "mypage.my");
				} else {
					System.out.println("예약처리 에러발생");
					JSForward.locationHref(response, "예약을 처리하지 못했습니다", "main.gr");
				}
				 

			 
	     } // end_of_orderReservation.or
		 
		 
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

} //end_of_OrderController

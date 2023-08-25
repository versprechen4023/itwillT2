package web.groom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import web.groom.dto.MemberDTO;
import web.groom.dto.OrderDTO;
import web.groom.email.MemberEmail;
import web.groom.email.VerifyEmail;
import web.groom.service.MemberService;
import web.groom.service.OrderService;

@WebServlet("*.aj") // .Ajax Ajax관련 어노테이션 매핑 선언
public class AjaxController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sPath = request.getServletPath();

		// AJAX관련 아이디중복검사쪽
		if (sPath.equals("/checkId.aj")) {

			// 아이디 중복검사를 위한 멤버 서비스 객체생성
			MemberService ser = new MemberService();

			// 아이디 중복검사를 위해 리퀘스트 값 넘김
			MemberDTO memberdto = ser.searchId(request);
			
			// memberdto 객체유무로 id중복검증 확인
			boolean result = (memberdto != null) ? true : false;
			
			// 콜백함수에 최종결과값 출력
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(Boolean.toString(result));
			out.close();
		}
		
		// AJAX관련 전화번호중복검사쪽
		if (sPath.equals("/checkPhone.aj")) {

			// 전화번호 중복검사를 위한 멤버 서비스 객체생성
			MemberService ser = new MemberService();

			// 전화번호 중복검사를 위해 리퀘스트 값 넘김
			MemberDTO memberdto = ser.searchPhone(request);

			// memberdto 객체유무로 전화번호 중복검증 확인
			boolean result = (memberdto != null) ? true : false;

			// 콜백함수에 최종결과값 출력
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(Boolean.toString(result));
			out.close();
		}
		
		// AJAX관련 이메일중복검사쪽
		if (sPath.equals("/checkEmail.aj")) {

			// 이메일 중복검사를 위한 멤버 서비스 객체생성
			MemberService ser = new MemberService();

			// 이메일 중복검사를 위해 리퀘스트 값 넘김
			MemberDTO memberdto = ser.searchEmail(request);

			// memberdto 객체유무로 이메일 중복검증 확인
			boolean result = (memberdto != null) ? true : false;

			// 콜백함수에 최종결과값 출력
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(Boolean.toString(result));
			out.close();
		}

		// AJAX관련 이메일 인증코드 발송
		if (sPath.equals("/email.aj")) {

			MemberEmail email = new MemberEmail();
			email.sendEmail(request); // 이메일 값 넘겨주기
		}

		// AJAX관련 이메일 인증번호 검증
		if (sPath.equals("/verify.aj")) {

			// 인증번호 가져옴
			String userVerificationCode = request.getParameter("verificationCode");

			// 인증번호테스트하기위한 부분
			System.out.println("ajax인증번호테스트");
			System.out.println(userVerificationCode);

			// 인증번호 검증을 boolean값으로 가져옴
			boolean result = new VerifyEmail().verifycationEmail(request);

			// 콜백함수에 최종결과값 출력
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(Boolean.toString(result));
			out.close();
		}
		
		// 예약관련 AJAX테스트
		if (sPath.equals("/test.aj")) {
		    
		    OrderService orderService = new OrderService();
		 	List<OrderDTO> serviceDate = orderService.getServiceDate(request);
		 			
		    JSONArray arr = new JSONArray();
		    
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    
			for(OrderDTO orderDTO : serviceDate) {
				
				JSONObject object = new JSONObject();
				object.put("date", format.format(orderDTO.getDate()));
				// 배열 한칸에 저장
				arr.add(object);
			}

		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter printWriter = response.getWriter();
		    printWriter.println(arr);
		    printWriter.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName)
			throws ServletException, IOException {
		request.getRequestDispatcher("/" + folder + "/" + pageName + ".jsp").forward(request, response);
	}

}

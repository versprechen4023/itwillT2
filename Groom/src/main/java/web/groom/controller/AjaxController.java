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
import web.groom.dto.OrderServiceDTO;
import web.groom.email.MemberEmail;
import web.groom.email.VerifyEmail;
import web.groom.service.AdminService;
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
		
		// AJAX관련 예약에 대한 날짜 비활성화 로직
		if (sPath.equals("/getDate.aj")) {
		    
			//오더 관련 처리를 위한 오더 서비스 객체생성
		    OrderService orderService = new OrderService();
		    
		    // 예약 비활성화 날짜를 리스트로 받아오기
		 	List<OrderDTO> serviceDate = orderService.getServiceDate(request);
		 	
		 	// JSON 배열 객체 생성
		    JSONArray arr = new JSONArray();
		    
		    // 날짜 포맷을 JSON에 맞게 처리하기 위해 변경
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    
		    //orderDTO의 내용을 모두 JSON 오브젝트에 삽입
			for(OrderDTO orderDTO : serviceDate) {
				
				JSONObject object = new JSONObject();
				object.put("date", format.format(orderDTO.getDate()));
				// 배열 한칸에 저장
				arr.add(object);
			}
			
			// 콜백함수에 최종결과값 출력
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter out = response.getWriter();
		    out.print(arr);
		    out.close();
		}
		
		// AJAX관련 예약에 대한 시간 비활성화 로직
		if (sPath.equals("/getTime.aj")) {

			//오더 관련 처리를 위한 오더 서비스 객체생성
			OrderService orderService = new OrderService();
			
			// 예약 비활성화 시간을 리스트로 받아오기
		 	List<OrderDTO> serviceTime = orderService.getServiceTime(request);
		 	
		 	// JSON 배열 객체 생성
		 	JSONArray arr = new JSONArray();
		 	
		 	// 시간 포맷을 JSON에 맞게 처리하기 위해 변경
		 	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		 	
		 	//orderDTO의 내용을 모두 JSON 오브젝트에 삽입
		 	for(OrderDTO orderDTO : serviceTime) {
				
				JSONObject object = new JSONObject();
				object.put("time1", format.format(orderDTO.getTime()));
				object.put("time2", format.format(orderDTO.getAddTime()));
				// 배열 한칸에 저장
				arr.add(object);
			}
		 	
		 	// 콜백함수에 최종결과값 출력
		 	response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter out = response.getWriter();
		    out.print(arr);
		    out.close();
		}
		
		// AJAX관련 서비스 가져오기(서비스명)
		if (sPath.equals("/getService.aj")) {
			
			OrderService orderService = new OrderService();
		 	List<OrderServiceDTO> serviceList = orderService.getServiceList(request);
			
		 	JSONArray arr = new JSONArray();
		 	
		 	for(OrderServiceDTO orderServiceDTO : serviceList) {
				
				JSONObject object = new JSONObject();
				object.put("s_num", orderServiceDTO.getS_num());
				object.put("s_name", orderServiceDTO.getS_name());
				// 배열 한칸에 저장
				arr.add(object);
			}
		 	
		    // 콜백함수에 최종결과값 출력
		 	response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter out = response.getWriter();
		    out.print(arr);
		    out.close();
		}
		
		// AJAX관련 서비스 가져오기(무게쪽)
		if (sPath.equals("/getWeight.aj")) {

			OrderService orderService = new OrderService();
			List<OrderServiceDTO> weightList = orderService.getWeightList(request);

			JSONArray arr = new JSONArray();

			for (OrderServiceDTO orderServiceDTO : weightList) {

				JSONObject object = new JSONObject();
				object.put("s_num", orderServiceDTO.getS_num());
				object.put("s_weight", orderServiceDTO.getS_weight());
				// 배열 한칸에 저장
				arr.add(object);
			}
			
			// 콜백함수에 최종결과값 출력
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(arr);
			out.close();
		}
		
		// AJAX관련 서비스 가져오기(직원명)
		if (sPath.equals("/getManager.aj")) {

			OrderService orderService = new OrderService();
			List<OrderServiceDTO> managerList = orderService.getManagerList(request);

			JSONArray arr = new JSONArray();

			for (OrderServiceDTO orderServiceDTO : managerList) {

				JSONObject object = new JSONObject();
				object.put("emp_num", orderServiceDTO.getEmp_num());
				object.put("emp_name", orderServiceDTO.getEmp_name());
				object.put("emp_grade", orderServiceDTO.getEmp_grade());
				// 배열 한칸에 저장
				arr.add(object);
			}
			
			// 콜백함수에 최종결과값 출력
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(arr);
			out.close();
		}
				
				
		// AJAX관련 서비스 가격 가져오기(상품가격)
		if (sPath.equals("/getPrice.aj")) {
					
			OrderService orderService = new OrderService();
			
			// 최종상품 가격을 가져옴
			int servicePrice = orderService.getServicePrice(request);
			
			// 콜백함수에 최종결과값 출력
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(Integer.toString(servicePrice));
			out.close();
				 	
		}
		
		
		// AJAX관련 예약상태 가져오기(상품가격)
		if (sPath.equals("/statusChange.aj")) {
			System.out.println("예약상태변경");
			
			AdminService adminService = new AdminService();
			
			boolean result = adminService.statusChange(request);

			// 콜백함수에 최종결과값 출력
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(Boolean.toString(result));
			out.close();
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

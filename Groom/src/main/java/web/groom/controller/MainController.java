package web.groom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.gr") //.gr 어노테이션 매핑 선언
public class MainController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI(); // uri의 주소 추출 /Groom/main.gr
		String contextPath = request.getContextPath(); // 프로젝트 명 추출/Groom
		String page = uri.substring(contextPath.length()); //substring을 이용하여 contextPath(/Groom)뒤인 페이지명(main.gr)등을 추출
		
		 //메인화면페이지
		 if (page.equals("/main.gr")) {
	            webForward(request, response, "mainpages", "main");
	     }
		 
		 //멤버페이지
		 if (page.equals("/login.gr")) {
	            webForward(request, response, "member", "login");
	     }
		 
		 if (page.equals("/singup.gr")) {
	            webForward(request, response, "member", "singup");
	     }
		 
		 //보드페이지
		 if (page.equals("/notice.gr")) {
	            webForward(request, response, "board", "notice");
	     }
		 
		 if (page.equals("/qna.gr")) {
	            webForward(request, response, "board", "notice");
	     }
		 
		 //메인페이지관련
		 if (page.equals("/about.gr")) {
	            webForward(request, response, "mainpages", "about");
	     }
		 
		 if (page.equals("/blog.gr")) {
	            webForward(request, response, "mainpages", "blog");
	     }
		 
		 if (page.equals("/contact.gr")) {
	            webForward(request, response, "mainpages", "contact");
	     }
		 
		 if (page.equals("/portfolio.gr")) {
	            webForward(request, response, "mainpages", "portfolio");
	     }
		 
		 if (page.equals("/review.gr")) {
	            webForward(request, response, "mainpages", "review");
	     }
		 
		 if (page.equals("/testabout.gr")) {
	            webForward(request, response, "mainpages", "testabout");
	     }
		 
		 
		 //AJAX관련
		 if (page.equals("/test.gr")) {
			 
			     System.out.println("ajax테스트");
				 response.setContentType("text/html; charset=UTF-8");
				 String id = request.getParameter("id");
				
				  System.out.println(id);
				  String get = "test";
				  PrintWriter out = response.getWriter();
				  if(id.equals(get)){
					  out.print("yes");
				  }else{
					  out.print("no");
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
	
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String page) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+page+".jsp").forward(request, response);
	}

}

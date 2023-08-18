package web.groom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.gr") //.gr 메인 어노테이션 매핑 선언
public class MainController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 //메인화면페이지
		 if (sPath.equals("/main.gr")) {
	            webForward(request, response, "mainsPaths", "main");
	     }
		 
		 //멤버페이지
		 if (sPath.equals("/login.gr")) {
	            webForward(request, response, "member", "login");
	     }
		 
		 if (sPath.equals("/singup.gr")) {
	            webForward(request, response, "member", "singup");
	     }
		 
		 //보드페이지
		 if (sPath.equals("/notice.gr")) {
	            webForward(request, response, "board", "notice");
	     }
		 
		 if (sPath.equals("/qna.gr")) {
	            webForward(request, response, "board", "notice");
	     }
		 
		 //메인페이지관련
		 if (sPath.equals("/about.gr")) {
	            webForward(request, response, "mainsPaths", "about");
	     }
		 
		 if (sPath.equals("/blog.gr")) {
	            webForward(request, response, "mainsPaths", "blog");
	     }
		 
		 if (sPath.equals("/contact.gr")) {
	            webForward(request, response, "mainsPaths", "contact");
	     }
		 
		 if (sPath.equals("/portfolio.gr")) {
	            webForward(request, response, "mainsPaths", "portfolio");
	     }
		 
		 if (sPath.equals("/review.gr")) {
	            webForward(request, response, "mainsPaths", "review");
	     }
		 
		 if (sPath.equals("/testabout.gr")) {
	            webForward(request, response, "mainsPaths", "testabout");
	     }
		 
		 
		 //AJAX관련
		 if (sPath.equals("/test.gr")) {
			 
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

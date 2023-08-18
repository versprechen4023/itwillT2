package web.groom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.aj") //.Ajax Ajax관련 어노테이션 매핑 선언
public class AjaxController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
				 	 
		 //AJAX관련
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

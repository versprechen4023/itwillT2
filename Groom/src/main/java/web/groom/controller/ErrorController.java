package web.groom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.er") //.er 에러페이지 어노테이션 매핑 선언
public class ErrorController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sPath = request.getServletPath();
		
		 //메인화면페이지
		 if (sPath.equals("/loginError.er")) {
	            webForward(request, response, "error", "loginError");

	     }
		 
		 if (sPath.equals("/searchError.er")) {
	            webForward(request, response, "error", "searchError");

	     }
		 
		 if (sPath.equals("/passwordError.er")) {
	            webForward(request, response, "error", "passwordError");

	     }
		 
		 if (sPath.equals("/changeOK.er")) {
	            webForward(request, response, "error", "changeOK");

	     }
		 
		 if (sPath.equals("/mypageError.er")) {
	            webForward(request, response, "error", "mypageError");

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

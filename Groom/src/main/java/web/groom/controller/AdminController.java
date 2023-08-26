package web.groom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.ad") //.ad 관리자페이지 어노테이션 매핑 선언
public class AdminController extends HttpServlet {
	
	RequestDispatcher dispatcher = null; 
//	AdminService adminService = null;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doProcess");
		String sPath = request.getServletPath();
		
		
		if (sPath.equals("/admin.ad")) {
			 webForward(request, response, "admin", "admin_page");
	     }
		if (sPath.equals("/admin_main.ad")) {
			 webForward(request, response, "admin", "admin_main");
	     }
		if (sPath.equals("/admin_userCheck.ad")) {
			 webForward(request, response, "admin", "admin_userCheck");
	     }
		if (sPath.equals("/admin_resCheck.ad")) {
			 webForward(request, response, "admin", "admin_resCheck");
	     }
		if (sPath.equals("/admin_storeCheck.ad")) {
			 webForward(request, response, "admin", "admin_storeCheck");
	     }
		
		
		
		
		
	}// doProcess
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doProcess(request, response);
	}
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folder, String pageName) throws ServletException, IOException {
		request.getRequestDispatcher("/"+folder+"/"+pageName+".jsp").forward(request, response);
	}
}// class

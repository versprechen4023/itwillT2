package com.itwillbst2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbst2.service.MemberService;

public class MemberController extends HttpServlet {
	
	RequestDispatcher dispatcher = null;
	MemberService ser = null;
	
	public void webForward(HttpServletRequest request, HttpServletResponse response, String folderName, String webName) throws ServletException, IOException{
		dispatcher = request.getRequestDispatcher(folderName+"/"+webName+".jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 가상 주소 추출
		String virtualHost = request.getServletPath();
				
		// 메인 페이지 이동
		if (virtualHost.equals("/main.do")) {
			webForward(request, response, "main", "main");
		}//end_of_insert.me
		
	}

}

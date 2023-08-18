package web.groom.controller;

import java.io.File;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("*.up") //.up 어노테이션 매핑 선언
public class UploadController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI(); // uri의 주소 추출 /Groom/main.gr
		String contextPath = request.getContextPath(); // 프로젝트 명 추출/Groom
		String page = uri.substring(contextPath.length()); //substring을 이용하여 contextPath(/Groom)뒤인 페이지명(main.gr)등을 추출
		 
		if (page.equals("/upload.up")) {
			 
			   // 업로드된 파일을 저장할 디렉토리 경로
			    String uploadPath = "C:\\Users\\Administrator\\git\\itwillt2\\Groom\\src\\main\\webapp\\upload";
//			    String uploadPath = request.getServletContext().getRealPath("/test");

		        // 업로드된 파일을 저장할 디렉토리 생성
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		            System.out.println("폴더생김");
		        }
		        
		        // 10메가로 제한
		        int maxSize = 10 * 1024 * 1024;
		        
		        //멀티파트 API를 활용한 파일저장
		        MultipartRequest multi = new MultipartRequest(request,uploadPath,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		        
		        //파일 업로드 확인용
		        File uploadedFile = multi.getFile("file");
		        if (uploadedFile != null) {
		            String fileName = uploadedFile.getName();// 이거 DB에저장하면될듯
		            String fileContentType = multi.getContentType("file"); // 파일 MIME 타입
		            long fileSize = uploadedFile.length(); // 파일 크기
		            String savedFilePath = uploadedFile.getAbsolutePath(); // 저장된 파일의 절대 경로
		            System.out.println(fileName);
		            System.out.println(fileContentType);
		            System.out.println(fileSize);
		            System.out.println(savedFilePath);
		            response.sendRedirect("myFile.jsp");
		        } else {
		            System.out.println("파일없음");
		        }
	     }
		 
		 if (page.equals("/time.up")) {
			 String date = request.getParameter("datepicker");
			 String time = request.getParameter("timepicker");
			 
			 System.out.println(date);
			 System.out.println(time);
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

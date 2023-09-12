package web.groom.javascript;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class JSForward {
	
	// 자바스크립트 메시지 뿌려주면서 이동하는 메서드들
	
	// 히스토리 백 라인
	public static void historyBack(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 로케이션 하이퍼링크 라인
	public static void locationHref(HttpServletResponse response, String msg, String location) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("location.href='" + location + "'");
			out.print("</script>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 창닫기 라인
	public static void windowClose(HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("window.close();");
			out.print("</script>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 창닫기 라인 자식창이 완료되면 부모창 리로드 @오버로딩
	public static void windowClose(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("window.close();");
			out.print("window.opener.location.reload();");
			out.print("</script>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

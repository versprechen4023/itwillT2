package web.groom.email;

import javax.servlet.http.HttpServletRequest;

public class VerifyEmail {
	
	public boolean verifycationEmail (HttpServletRequest request) {
		
		//회원가입 유저로 부터 받은 입력값 가져오기
		String userVerificationCode = request.getParameter("verificationCode");
		
		//인증번호가 발급됬을당시 세션에 저장해둔 인증번호값 호출
	    String sessionVerificationCode = (String) request.getSession().getAttribute("verificationCode");
	    
	    boolean verificationResult = false;
	    
	    //인증번호가 null이아니고 일치하는 경우 true 반환 아닌경우 false반환
	    if (userVerificationCode != null && sessionVerificationCode != null && userVerificationCode.equals(sessionVerificationCode)) {
	      verificationResult = true;
	    }
	    
        return verificationResult;
        
	}
}

package web.groom.email;

import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberEmail {
	
	public void sendEmail (HttpServletRequest request) {
		
		// 인증번호 랜덤 생성
		Random random = new Random();
		int verificationCode = 100000 + random.nextInt(900000); // 100000 이상 999999 이하의 숫자
		
		// 생성된 인증번호를 세션에 저장
		request.getSession().setAttribute("verificationCode", String.valueOf(verificationCode)); //String.valueOf로 문자열 변환
		System.out.println("인증번호 : " + verificationCode);
		
		// 이메일 전송 설정
		String host = "smtp.gmail.com";
		String port = "587";
		String id = "itwillteam2@gmail.com";
		String password = "wazqgqzbqspgolse";
		
		//호스트 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		// 이메일 메시지 작성
		String sendToEmail = request.getParameter("u_email"); //메일을 보낼 대상
		String subject = "GROOM 이메일 인증 코드";
		String content = 
				"GROOM 이메일 인증 코드 입니다"+
				"<br><br>"+
				"인증 코드 : " + verificationCode+
				"<br>"+
				"해당 인증코드를 하단의 인증코드 입력창에 기입하여 주십시오.";
		
		//관리자 아이디 비밀번호 입력값 세션에저장
		Session session = Session.getInstance(props, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(id, password);
		    }
		});
		
		try {
		    Message message = new MimeMessage(session); //관리자 id, 비밀번호 입력
		    message.setFrom(new InternetAddress(id)); //관리자 id
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail)); //보낼사람설정
		    message.setSubject(subject); //제목설정
		    message.setContent(content, "text/html"); //내용설정

		    // 이메일 전송
		    Transport.send(message);
		    System.out.println("이메일 전송 완료");

		} catch (MessagingException e) {
		    System.out.println("이메일 전송 오류");
		}
	}

}

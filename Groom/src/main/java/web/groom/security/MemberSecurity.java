package web.groom.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MemberSecurity {
	
			// 쿼리에서 작동될 암호화관련 메서드
			// 비밀번호 솔트생성 메서드
			public String generateSalt() {
				SecureRandom randomNumber = new SecureRandom(); // 난수생성을 위한 객체생성
				byte[] salt = new byte[16];// 난수의 값을 입력할 바이트 배열 생성(배열크기 16 16진수문자열크기 32)
				randomNumber.nextBytes(salt);// 배열에 값 입력
				return bytesToHex(salt);// 16진수화를 위해 바이트투헥스로 값 리턴
			}
			
			// 솔트 16진수화
			public String bytesToHex(byte[] bytes) { // 솔트, 해시등 바이트 배열 값 가져옴
				
				StringBuilder resultHex = new StringBuilder();//문자열에 반복문을 통해 값을 직접 추가하기위해 객체생성

				for (byte b : bytes) {// for each문 배열크기만큼 반복
					resultHex.append(String.format("%02x", b));// 포맷은 16진수
				}
				return resultHex.toString();// 16진수 문자열화한 바이트배열값 리턴
			}
			
			// 비밀번호 해쉬화를 위한 메서드
			public String hashPassword(String password, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");// 다이제스트 객체생성 SHA-256방식에 따름(길이는 64까지)
				digest.update(hexToBytes(salt));// 다이제스트에 솔트값을 입력
				byte[] hashedBytes = digest.digest(password.getBytes("UTF-8"));
				// 다이제스트(솔트)값과 입력받은 패스워드(getBytes를통해 바이트화)로 알고리즘 연산수행
				// 값을 hashedBytes변수에 저장(바이트배열)
				
				return bytesToHex(hashedBytes);// 16진수화를 위해 바이트투헥스로 값 리턴
			}
			
			// 솔트 16진수를 다시 바이트화 하기위한 메서드
			public byte[] hexToBytes(String hex) {
				
				int hexLength = hex.length();// 16진수 문자열 크기만큼 길이설정
				
				byte[] data = new byte[hexLength / 2];// 16진수크기에 따른 바이트 배열 크기 설정(16진수 문자열 크기의 절반)
				for (int i = 0; i < hexLength; i += 2) {
					data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
				} 
				//16진수 문자열 바이트배열화 작업 
				//16진수 2문자씩 처리함 4비트만큼 << 로이동시키는 것으로 16진수를 10진수화 하는게 가능
				//2번째문자는 비트이동할 필요없이 더해주기만 해도됨(첫번째에서 10진수화 처리하였음)
				//16진수를 10진수화하여 바이트배열에 저장
				
				return data;// 바이트배열 값 리턴
				
			}//암호화 메서드 끝
}

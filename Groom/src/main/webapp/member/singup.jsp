<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
    </head>
	
	<!-- Theme style  -->
	<link rel="stylesheet" href="./css/member_gr.css">
	
	<body>
	
<!-- 로고 자리 -->
       <h1 style="margin-top: 70px; margin-bottom: -10px;"> Groom(로고) </h1>
       	  
<!-- 아이디 -->
         <form>
			<div class="membership">
			  <div>
				<label class="imp">아이디</label>
				<div>
				  <input type="text" id="id" placeholder="아이디를 입력하세요" required name="id" >
				</div>
				<span id="idmsg" style="color:red">중복되는 아이디가 있습니다.</span>
			  </div>
			  
<!-- 비밀번호 -->
			  <div>
				<br><label class="imp">비밀번호</label>
				<div>
				  <input type="password" placeholder="비밀번호를 입력하세요" required>
			    </div>
			    <span id="idmsg" style="color:red">8~15자 영문, 숫자를 입력해주세요.</span>
			  </div>
		
<!-- 비밀번호 확인 -->
			  <div>
				<br><label class="imp">비밀번호 확인</label>
				<div>
				<input type="password" placeholder="비밀번호를 한 번 더 입력하세요" required>
				</div>
				<span id="idmsg" style="color:red">비밀번호가 일치하지 않습니다.</span>
			  </div>
		
<!-- 이름 -->
			  <div>
				<br><label class="imp">이름</label>
				<div>
				<input type="text" placeholder="이름을 입력하세요" required>
				</div>
				<span id="idmsg" style="color:red">한글, 영문 대/소문자를 사용해 주세요.</span>
			  </div>
			  
<!-- 휴대폰 -->
			  <div>
				<br><label class="imp">휴대폰</label>
				<div>
				  <input type="tel" placeholder="숫자만 입력하세요" required>
				  
              <!-- 휴대폰 인증번호 받기 버튼 -->
				  <button type="button" style="font-size: 13px;">인증번호 받기</button>
				  
				</div>
				<span id="idmsg" style="color:red">"-" 없이 입력해주세요.</span>
			  </div>
		
<!-- 주소 -->
			  <div>
				<br><label class="imp">주소</label>
				<div>
				  <input type="text" placeholder="도로명, 지번, 건물명 검색" required>
				  <img src="https://res.kurly.com/kurly/ico/2021/search_20_20_333.svg" alt="search">
				</div>
				<br><span id="idmsg" style="color:red">정확한 주소를 입력해주세요.</span>
		
			  </div>
           </div>
			
			
<!-- 이용약관동의 -->		
              <div class="terms">
			    <label class="imp" required>이용약관동의</label>
		
			  <div>
				<h3><input type="checkbox"><span class="ico"></span>전체 동의합니다.</h3>
				<p> 선택 항목에 동의하지 않은 경우도 회원가입 및 일반적인 서비스를 이용할 수 있습니다.</p>
			  </div>
		
			  <div>
				<input type="checkbox"><span class="ico"></span>이용약관동의<span>(필수)</span>
				<a href="#"></a>
			  </div>
		
			  <div>
				<input type="checkbox"><span class="ico"></span>개인정보 수집·동의<span>(필수)</span>
				<a href="#"></a>        
			  </div>
		
			  <div>
				<input type="checkbox"><span class="ico"></span>개인정보 수집·동의<span>(선택)</span>
				<a href="#"></a>   
			  </div>
			  
			  <div>
				<input type="checkbox"><span class="ico"></span>본인은 만 14세 이상입니다.<span>(필수)</span>
			  </div>
			</div>
		
<!-- 가입하기 버튼 -->
			<button type="submit" style="font-size: 13px;">가입하기</button>
		  </form> 

	</body>
</html>

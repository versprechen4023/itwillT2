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
	
	<!-- 로그인 스타일  -->
	<link rel="stylesheet" href="./css/modifyinfo_gr.css">
	
	
	<body>
	
<!-- 로고 자리 -->
    <h1 style="margin-top: 70px; margin-bottom: 30px;"> Groom(로고) </h1>
       
<!-- 내정보 수정 텍스트 -->  
    <p class="text" style="margin: 80px 600px 30px;">내 정보 수정</p>
    
<!-- 가로선 -->  
    <div class="separator"></div>
       	  
<!-- 아이디 -->
         <form>
			<div class="membership">
			  <div>
				<label class="imp2">아이디</label>
				<div>
				  <input type="text" value="읽기 전용(readonly)" readonly>
				</div>
			  </div>
			  
<!-- 현재 비밀번호 -->
			  <div>
				<br><label class="imp2">현재 비밀번호</label>
				<div>
				  <input type="password" placeholder="현재 비밀번호를 입력하세요" required>
			    </div>
			  </div>
		
<!-- 변경할 비밀번호 -->
			  <div>
				<br><label class="imp2">변경할 비밀번호</label>
				<div>
				<input type="password" placeholder="변경할 비밀번호를 입력하세요" required>
				</div>
			  </div>
		
<!-- 이름 -->
			  <div>
				<br><label class="imp2">이름</label>
				<div>
				<input type="text" placeholder="이름을 입력하세요" required>
				</div>
			  </div>
			  
<!-- 이메일 -->
			  <div>
				<br><label class="imp2">이메일</label>
				<div>
				<input type="text" placeholder="이메일을 입력하세요" required>
				</div>
			  </div>
			  
<!-- 휴대폰번호 -->
			  <div>
				<br><label class="imp2">휴대폰번호</label>
				<div>
				  <input type="tel" placeholder="숫자만 입력하세요" required>
				  
              <!-- 휴대폰 인증번호 받기 버튼 -->
				  <button type="button" style="font-size: 13px;">인증번호 받기</button>
				  
				</div>
			  </div>
			  
           </div>
			
		
<!-- 수정하기 버튼 -->
			<button type="submit" style="font-size: 13px;">수정하기</button>
		  </form> 

	</body>
</html>
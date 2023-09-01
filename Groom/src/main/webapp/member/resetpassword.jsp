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
	<link rel="stylesheet" href="./css/login_gr.css">

	<body>
	  <form action="resetpassword.me" id="resetpass" name="resetpass" method="post">
	  
<!-- 로고 자리 -->		
		<h1 style="margin-top: -55px; margin-bottom: 55px;"> Groom(로고) </h1>
		
<!-- 테두리선 시작 -->
			<div class="membership" style="border: 1px solid #ccc; border-radius: 10px; padding: 30px;">
			 
<!-- 비밀번호 -->
			  <div>
				<br><label class="imp">비밀번호</label>
				<div>
				  <input type="password" id="u_pass" name="u_pass" placeholder="비밀번호를 입력하세요">
			    </div>
			    <span id="passtest"></span>
			  </div>
		
<!-- 비밀번호 확인 -->
			  <div>
				<br><label class="imp">비밀번호 확인</label>
				<div>
				<input type="password" id="u_pass2" name="u_pass2" placeholder="비밀번호를 한 번 더 입력하세요">
				</div>
				<span id="passmsg"></span>
			  </div>
		
			 
<!-- 가로줄 추가 -->
		<hr style="margin: 20px 0; border: 2 solid #ccc;">
		

<!-- 비밀번호 재설정 버튼 -->
		<div id="button">
			<button type="submit" id="submit" name="submit" style="width: 390px; font-size: 16px; margin-left: 0; ">비밀번호 재설정</button>
		   </div>
	    </div>   
<!-- 테두리선 끝 -->

		   	       
<div class="centered-text">
    <p>
        <a href="signup.me" class="custom-text"><b>회원가입</b></a>
        <span class="vertical-line"></span>
        <a href="fineid.me" class="custom-text"><b>아이디 찾기</b></a>
    </p>
</div>
      
    </form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$('#resetpass').submit(function() {
	
	if($('#u_pass').val() == ""){
		$('#passtest').css('color','red');
		$('#passtest').text("비밀번호를 입력해주세요.");
		$('#u_pass').focus();
		return false;
	}
	
	if($('#u_pass2').val() == ""){
		$('#passmsg').css('color','red');
		$('#passmsg').text("비밀번호를 재입력해주세요.");  
		$('#u_pass2').focus();
		return false;
	}

});//submit기능 제어 끝

//비밀번호 정규성 여부 검사
$('#u_pass').keyup(function() {
	  
	  //기존패스워드 수정시 비밀번호 확인부분 강제호출
	  $('#u_pass2').trigger('keyup');
	  var password = $('#u_pass').val();
	  
	  if(!validatePassword(password)){
		$('#passtest').css('color','red');
		$('#passtest').text("비밀번호는 8자이상의 영문, 숫자, 특수문자가 포함되어야 합니다.");
		$('#submit').attr('disabled','disabled');
	    return;
	    
	  } else {
		$('#passtest').text("");
		$('#submit').removeAttr('disabled');
		return;
	  }
	  
	  function validatePassword(password) {
		  var regExp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[#^()_=+@$!%*?&])[A-Za-z\d#^()_=+@$!%*?&]{8,}$/;
		  return regExp.test(password);
	  }
});//비밀번호 정규성 여부 검사 끝

//비밀번호 일치여부 검사
$('#u_pass2').keyup(function() {

	  var pass1 = $('#u_pass').val();
	  var pass2 = $('#u_pass2').val();
	  
	  if(pass1 == pass2 && pass1 !== "" && pass2 !== ""){
		$('#passmsg').css('color','green');
		$('#passmsg').text("비밀번호가 일치합니다.");
	    $('#submit').removeAttr('disabled');
	    return;
	    
	  } else {
		$('#passmsg').css('color','red');
		$('#passmsg').text("비밀번호가 일치하지 않습니다.");
		$('#submit').attr('disabled','disabled');
		return;
	  }
 	   
});//비밀번호 일치여부 검사 끝
</script>
	</body>
</html>
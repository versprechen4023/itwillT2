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
         <form action="signupPro.me" id="signup" name="signup" method="post">
			<div class="membership">
			  <div>
				<label class="imp">아이디</label>
				<div>
				  <input type="text" id="u_id" name="u_id" placeholder="아이디를 입력하세요">
				</div>
				<span id="idmsg"></span>
			  </div>
			  
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
		
<!-- 이름 -->
			  <div>
				<br><label class="imp">이름</label>
				<div>
				<input type="text" id="u_name" name="u_name" placeholder="이름을 입력하세요">
				</div>
				<span id="namemsg"></span>
			  </div>
			  
<!-- 전화번호 -->
			  <div>
				<br><label class="imp">전화번호</label>
				<div>
				<input type="text" id="u_phone" name="u_phone" maxlength="11" placeholder="-없이 전화번호를 입력하세요 예시:01012341234">
				</div>
				<span id="phonemsg"></span>
			  </div>
			  
<!-- 이메일 -->
			  <div>
				<br><label class="imp">이메일</label>
				<div>
				  <input type="email" id="u_email" name="u_email" placeholder="이메일을 입력하세요">
				  
              <!-- 이메일 인증번호 받기 버튼 -->
				  <button type="button" style="font-size: 13px;" id="u_email2" name="u_email2" onclick="sendVerificationEmail();">인증번호 받기</button>
				</div>
				<span id="emailtest"></span>
			  </div>
			  
			  <div>
				<br><label class="imp">인증번호입력</label>
				<div>
				  <input type="text" id="verificationCode" name="verificationCode" readonly>
				</div>
				<span id="emailmsg"></span>
			  </div>
		
            </div>
<!-- 멤버십클래스 끝 -->
		
<!-- 가입하기 버튼 -->
			<button type="submit" id="submit" name="submit" style="font-size: 13px;">가입하기</button>
		  </form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$('#signup').submit(function() {
	
	if($('#u_id').val() == ""){
		$('#idmsg').css('color','red');
		$('#idmsg').text("아이디를 입력해주세요."); 
		$('#u_id').focus();
		return false;
	}
	
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
	
	if($('#u_name').val() == ""){
		$('#namemsg').css('color','red');
		$('#namemsg').text("이름을 입력해주세요.");
		$('#u_name').focus();
		return false;
	}
	
	if($('#u_phone').val() == ""){
		$('#phonemsg').css('color','red');
		$('#phonemsg').text("전화번호를 입력해주세요.");
		$('#u_phone').focus();
		return false;
	}
	
	if($('#u_email').val() == ""){
		$('#emailtest').css('color','red');
		$('#emailtest').text("이메일을 입력해주세요.");
		$('#u_email').focus();
		return false;
	}
	
	if($('#verificationCode').val() == ""){
		$('#emailmsg').css('color','red');
		$('#emailmsg').text("인증번호를 기입하십시오.");
		$('#verificationCode').focus();
		return false;
	}

});//submit기능 제어 끝

//아이디 중복검사
$('#u_id').keyup(function(){
	  
	  var id = $("#u_id").val();
	  
	  if(validateId(id)){
	  $.ajax({
		  type: "POST",
		  url : "checkId.aj",
		  data: {"u_id": id},
		  success:function(data){
			  const result = $.trim(data);
			  if(result=="false" && !id == ""){
			
			  $('#idmsg').css('color','green');
			  $('#idmsg').text("사용가능한 아이디입니다.");
			  $('#submit').removeAttr('disabled');
			  return;
			  }else if ( result=="true" && !id == ""){
			 
			  $('#idmsg').css('color','red');
			  $('#idmsg').text("이미 존재하는 아이디입니다.");
			  $('#submit').attr('disabled','disabled');
			  return;
			  }
		  }//success 콜백함수 종료지점
	  });// ajax
	  if(id == ""){
		  $('#idmsg').css('color','red');
		  $('#idmsg').text("아이디를 입력해주세요.");
		  $('#submit').attr('disabled','disabled');
		  return;
	  }//id값이 빈칸일경우 입력하라는 내용 출력
	  
	  } else{
		$('#idmsg').css('color','red');
		$('#idmsg').text("아이디는 영문, 숫자만 입력가능합니다.");
		$('#submit').attr('disabled','disabled');
		return;
	  }
	  
});// 아이디중복확인 종료

 //아이디 정규식 유효성 검사
 function validateId(id) {
   var regExp = /^[a-zA-Z0-9]*$/;
   return regExp.test(id); //test메서드로 정규식 검사만함
 }
 
//전화번호 중복검사
$('#u_phone').keyup(function() {
 	  
 	  var phone = $('#u_phone').val();
 	  
 	  if(validatePhone(phone)){
 	  $.ajax({
 		  type: "POST",
 		  url : "checkPhone.aj",
 		  data: {"u_phone": phone},
 		  success:function(data){
 			  const result = $.trim(data);
 			  if(result=="false" && !phone == ""){
 			
 			  $('#phonemsg').css('color','green');
 			  $('#phonemsg').text("사용가능한 전화번호입니다.");
 			  $('#submit').removeAttr('disabled');
 			  return;
 			  }else if ( result=="true" && !phone == ""){
 			 
 			  $('#phonemsg').css('color','red');
 			  $('#phonemsg').text("이미 등록된 전화번호입니다.");
 			  $('#submit').attr('disabled','disabled');
 			  return;
 			  }
 		  }//success 콜백함수 종료지점
 	  });// ajax
 	  if(phone == ""){
 		  $('#phonemsg').css('color','red');
 		  $('#phonemsg').text("전화번호를 입력해주세요.");
 		  $('#submit').attr('disabled','disabled');
 		  return;
 	  }//전화번호값이 빈칸일경우 입력하라는 내용 출력
 	  
 	  } else {
 		$('#phonemsg').css('color','red');
 		$('#phonemsg').text("- 없이 올바른 전화번호를 입력해주십시오.");
 		$('#submit').attr('disabled','disabled');
 		return
 	  }
 	  
 });// 전화번호중복확인 종료

  //전화번호 정규식 유효성 검사
  function validatePhone(phone) {
 		 var regExp = /^[0-9]{9,11}$/;
 		 return regExp.test(phone);
  }
//이메일 중복검사
$('#u_email').keyup(function(){
  	  
  	  var email = $("#u_email").val();
  	  
  	  if (validateEmail(email)) {
  	  $.ajax({
  		  type: "POST",
  		  url : "checkEmail.aj",
  		  data: {"u_email": email},
  		  success:function(data){
  			  const result = $.trim(data);
  			  if(result=="false" && !email == ""){
  			
  			  $('#emailtest').css('color','green');
  			  $('#emailtest').text("사용가능한 이메일입니다.");
  			  $('#submit').removeAttr('disabled');
  			  $('#u_email2').removeAttr('disabled');
  			  return;
  			  }else if ( result=="true" && !email == ""){
  			 
  			  $('#emailtest').css('color','red');
  			  $('#emailtest').text("이미 등록된 이메일입니다.");
  			  $('#submit').attr('disabled','disabled');
  			  $('#u_email2').attr('disabled','disabled');
  			  return;
  			  }
  		  }//success 콜백함수 종료지점
  	  });// ajax
  	  if(email == ""){
  		  $('#emailtest').css('color','red');
  		  $('#emailtest').text("이메일을 입력해주세요.");
  		  $('#submit').attr('disabled','disabled');
  		  $('#u_email2').attr('disabled','disabled');
  		  return;
  	  }//이메일값이 빈칸일경우 입력하라는 내용 출력
  	  
  	  } else {
  		$('#emailtest').css('color','red');
  		$('#emailtest').text("유효한 이메일 주소를 입력해주세요.");
  		$('#u_email2').attr('disabled','disabled');
  		return;
  	  }
  	  
  });// 이메일중복확인 종료

  //이메일 정규식 유효성 검사
  function validateEmail(email) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regExp.test(email);
  }
  
//이메일 인증관련
function sendVerificationEmail() {
	var email = $("#u_email").val();

    if (validateEmail(email)) {
      $.ajax({
        type: "POST",
        url: "email.aj",
        data: { email: email },
        success: function(response) {
          alert("이메일로 인증번호가 전송되었습니다.");
          $('#emailtest').text("");
          $("#verificationCode").removeAttr("readonly");
          $('#verificationCode').attr('placeholder','인증번호를 입력해주세요'); 
        },//success 콜백함수 종료지점
        error: function(xhr, status, error) {
          alert("이메일 전송 중 오류가 발생했습니다. 다시 시도해주세요.");
        }//error 콜백함수 종료지점
      });// ajax
    }
 }//이메일인증종료
   
  //이메일 정규식 유효성 검사
  function validateEmail(email) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regExp.test(email); //test메서드로 정규식 검사만함
  }

//이메일인증
$('#verificationCode').keyup(function(){
	
	  $.ajax({
		  url : "verify.aj",
		  data: {"verificationCode": $('#verificationCode').val()},
		  success:function(data){
			  const result = $.trim(data);
			  if(result=="true" && !$('#verificationCode').val() == ""){
			
			  $('#emailmsg').css('color','green');
			  $('#emailmsg').text("인증번호가 일치합니다.");
			  $('#submit').removeAttr('disabled');
			  return;
			  }else if ( result=="false" && !$('#verificationCode').val() == ""){
			 
			  $('#emailmsg').css('color','red');
			  $('#emailmsg').text("인증번호가 일치하지 않습니다.");
			  $('#submit').attr('disabled','disabled');
			  return;
			  }
		  }//success 콜백함수 종료지점
	  });// ajax
}); // 이메일인증번호확인 종료

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

//이름 정규성 여부 검사
$('#u_name').keyup(function() {

	  var name = $('#u_name').val();
	  
	  if(!validateName(name)){
		$('#namemsg').css('color','red');
		$('#namemsg').text("올바른 이름을 입력해주세요 한글, 영/대소문자만 사용가능 합니다.");
		$('#submit').attr('disabled','disabled');
	    return;
	    
	  } else {
		  $('#namemsg').text("");
		  $('#submit').removeAttr('disabled');
		  return;
	  }
	  
	  function validateName(name) {
		 var regExp = /^[a-zA-Z가-힣]+$/;
		 return regExp.test(name);
	  }
});//이름 정규식 여부 검사 끝

</script>
	</body>
</html>

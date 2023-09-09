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
	
	<!-- 아이디찾기 스타일  -->
	<link rel="stylesheet" href="./css/findid_gr.css">
	
	<body>
	  <form action="findidresult.me" id="findid" name="findid" method="post">
	  
<!-- 로고 자리 -->		
	<h1 id="fh5co-logo1"><a href="main.gr">
	<img src="./images/LOGO.png" style="margin-top:-10px; margin-bottom: 5px;"></a></h1>
		
<!-- 테두리선 시작 -->
			<div class="membership" style="border: 1px solid #ccc; border-radius: 10px; padding: 30px;">
			 
<!-- 이름 -->
			  <div>
				<br><label class="imp2" style="font-size: 16px;">이름</label>
				<div>
				<input type="text" id ="u_name"  name ="u_name" placeholder="이름을 입력하세요">
				</div>
				<span id="namemsg"></span>
			  </div>
		
<!-- 이메일 -->
			  <div>
				<br><label class="imp">이메일</label>
				<div>
				  <input type="email" id="u_email" name="u_email" placeholder="이메일을 입력하세요">
				  
              <!-- 이메일 인증번호 받기 버튼 -->
				  <button type="button" style="font-size: 13px;" id="u_email2" name="u_email2" onclick="sendEmail();">인증번호 받기</button>
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
				 
<!-- 가로줄 추가 -->
		<hr style="margin: 20px 0; border: 2 solid #ccc;">
		

<!-- 아이디찾기 버튼 -->
		<div id="button">
			<button type="submit" id="submit" name="submit" style="width: 390px; font-size: 16px; margin-left: 0; ">아이디 찾기</button>
		   </div>
	    </div>   
<!-- 테두리선 끝 -->

		   	       
<div class="centered-text">
    <p>
        <a href="signup.me" class="custom-text"><b>회원가입</b></a>
        <span class="vertical-line"></span>
        <a href="findpass.me" class="custom-text"><b>비밀번호 찾기</b></a>
    </p>
</div>
      
    </form>
    
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$('#findid').submit(function() {
	
	if($('#u_name').val() == ""){
		$('#namemsg').css('color','red');
		$('#namemsg').text("이름을 입력해주세요."); 
		$('#u_name').focus();
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

//이메일 인증관련
// 함수 호출 제어를 위한 변수선언
var canCallFunction = true;

function sendEmail() {
	var email = $("#u_email").val();

    if (validateEmail(email)) {
    	if (canCallFunction) {
    		alert("이메일로 인증번호가 전송되었습니다.");  
    	    canCallFunction = false;
    	    
    	    // 60초후에 인증번호 다시 발송 할 수 있게 setTimeout 함수 사용
    	    setTimeout(function() {
    	    canCallFunction = true;
        	}, 60000);
    	    
      $.ajax({
        type: "POST",
        url: "email.aj",
        data: {"u_email": email },
        success: function(response) {
          $('#emailtest').text("");
          $("#verificationCode").removeAttr("readonly");
          $('#verificationCode').attr('placeholder','인증번호를 입력해주세요'); 
        }//success 콜백함수 종료지점
      });// ajax
    	} else {
      	  alert("이미 인증 코드를 발송하였습니다, 인증코드가 오지 않는다면 60초후에 다시 시도해 주십시오");
        }// 60초 타이머 end if
    } else {
    	alert("잘못된 이메일이 입력되었습니다");
    	return;
    }
 }//이메일인증종료
   
  //이메일 정규식 유효성 검사
  function validateEmail(email) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regExp.test(email);
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

$('#u_name').keyup(function() {

	  var name = $('#u_name').val();
	  
	  if(name != ""){
		$('#namemsg').text("");
	    return;
	  }
});

$('#u_email').keyup(function() {

	  var email = $('#u_email').val();
	  
	  if(email != ""){
		$('#emailtest').text("");
	    return;
	  }
});
</script>
	</body>
</html>
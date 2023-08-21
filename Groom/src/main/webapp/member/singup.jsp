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
				  <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" required name="id" >
				</div>
				<span id="idmsg">test</span>
			  </div>
			  
<!-- 비밀번호 -->
			  <div>
				<br><label class="imp">비밀번호</label>
				<div>
				  <input type="password" placeholder="비밀번호를 입력하세요" required>
			    </div>
			    <span>8~15자 영문, 숫자를 입력해주세요.</span>
			  </div>
		
<!-- 비밀번호 확인 -->
			  <div>
				<br><label class="imp">비밀번호 확인</label>
				<div>
				<input type="password" placeholder="비밀번호를 한 번 더 입력하세요" required>
				</div>
				<span>비밀번호가 일치하지 않습니다.</span>
			  </div>
		
<!-- 이름 -->
			  <div>
				<br><label class="imp">이름</label>
				<div>
				<input type="text" placeholder="이름을 입력하세요" required>
				</div>
				<span>한글, 영문 대/소문자를 사용해 주세요.</span>
			  </div>
			  
<!-- 이메일 -->
			  <div>
				<br><label class="imp">이메일</label>
				<div>
				  <input type="email" id="email" name="email" placeholder="이메일을 입력하세요" required>
				  
              <!-- 이메일 인증번호 받기 버튼 -->
				  <button type="button" style="font-size: 13px;" name="email2" onclick="sendVerificationEmail();">인증번호 받기</button>
				</div>
			  </div>
			  
			  <div>
				<br><label class="imp">인증번호입력</label>
				<div>
				  <input type="text" id="verificationCode" name="verificationCode" required readonly>
				</div>
				<span id="emailmsg">인증번호입력.</span>
			  </div>
		
            </div>
<!--       멤버십클래스 끝 -->
			
			
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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
//아이디 중복검사
$('#id').keyup(function(){
	
	  $.ajax({
		  url : "test.aj",
		  data: {"id": $('#id').val()},
		  success:function(data){
			  const result = $.trim(data);
			  if(result=="true" && !$('#id').val() == ""){
			
			  $('#idmsg').css('color','green');
			  $('#idmsg').text("사용가능한 아이디입니다.");
			  $('#submit').removeAttr('disabled');
			  return;
			  }else if ( result=="false" && !$('#id').val() == ""){
			 
			  $('#idmsg').css('color','red');
			  $('#idmsg').text("이미 존재하는 아이디입니다.");  
			  $('#submit').attr('disabled','disabled');
			  return;
			  }
		  }//success 콜백함수 종료지점
	  });// ajax
	  if($('#id').val() == ""){
		  $('#idmsg').css('color','red');
		  $('#idmsg').text("아이디를 입력해주세요.");  
		  $('#submit').attr('disabled','disabled'); 
		  return;
	  }//id값이 빈칸일경우 입력하라는 내용 출력
 }); // 아이디중복확인 종료
</script>

<script>
//이메일 인증관련
function sendVerificationEmail() {
	var email = $("#email").val();

    if (validateEmail(email)) {
      $.ajax({
        type: "POST",
        url: "email.aj",
        data: { email: email },
        success: function(response) {
          alert("이메일로 인증번호가 전송되었습니다.");
          $("#verificationCode").removeAttr("readonly");
          $('#verificationCode').attr('placeholder','인증번호를 입력해주세요'); 
        },
        error: function(xhr, status, error) {
          alert("이메일 전송 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
      });
    } else {
      alert("유효한 이메일 주소를 입력해주세요.");
    }
 }
   
  //이메일 정규식 유효성 검사
  function validateEmail(email) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regExp.test(email); //이건나중에 test 이름의 자바스크립트 코드 하나 더 만들어서 한번더 포커스되게함
  }
</script>

<script>
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
</script>
	</body>
</html>

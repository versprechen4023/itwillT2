<%@page import="web.groom.dto.MypageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MypageDTO mypageInfo = (MypageDTO)request.getAttribute("mypageInfo");
%>
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
	<%
	// String id=(String)session.getAttribute("id");
	// MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
	%>
<!-- 로고 자리 -->
   <h1 id="fh5co-logo1"><a href="main.gr">
	<img src="./images/LOGO.png" style="margin-top:80px; margin-bottom: 10px;"></a></h1>
       
<!-- 내정보 수정 텍스트 -->  
    <p class="text"  class="myinfo" style="margin-top: 50px;">내 정보 수정</p>
    
<!-- 가로선 -->  
    <div class="separator"></div>
       	  
<!-- 아이디 -->
         <form method="post" action="modifyinfopro.my" id=modify name=modify>
			<div class="membership">
			  <div>
				<label class="imp2">아이디</label>
				<div>
				  <input type="text" id="id" name="id" value="<%=mypageInfo.getId()%>" readonly>
				</div>
			  </div>
			  
		
<!-- 변경할 비밀번호 -->
			  <div>
				<br><label class="imp2">이름</label>
				<div>
				<input type="text" id ="name" name="name" value="<%=mypageInfo.getName()%>" readonly>
				</div>
			  </div>
		
<!-- 이름 -->
			  <div>
				<br><label class="imp2">전화번호</label>
				<div>
				<input type="text" id = "phone" name = "phone" maxlength="11" value="<%=mypageInfo.getPhone()%>">
				</div>
				<span id="phonemsg"></span>
			  </div>
			  
<!-- 이메일 -->
			  <div>
				<br><label class="imp2">이메일</label>
				<div>
				<input type="text" id="email" name="email" value="<%=mypageInfo.getEmail()%>">
				</div>
				<span id="emailtest"></span>
			  </div>
			  
<!-- 휴대폰번호 -->

			  
        
			
		
<!-- 수정하기 버튼 -->
			<button type="button" class="submit" onclick="checkVal()">수정</button>
		  </form>
		  
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>		  
<script type="text/javascript">
var nowEmail = '<%=mypageInfo.getEmail()%>';
var nowPhone = '<%=mypageInfo.getPhone()%>';
var myEmail = document.getElementById("email");
var myPhone = document.getElementById("phone");

//변경사항이있으면 수정이벤트 실행 없다면 뒤로가기
function checkVal() {
	if(myEmail.value == nowEmail && myPhone.value == nowPhone){
		history.back();
	} else {
		document.getElementById("modify").submit();
	}
}
//이메일 중복검사
$('#email').keyup(function(){
  	  
  	  var email = $("#email").val();
      
  	  if(email == ""){
		  $('#emailtest').css('color','red');
		  $('#emailtest').text("변경하실 이메일을 입력해주세요.");
		  $('#submit').attr('disabled','disabled');
		  return;
	  }//이메일값이 빈칸일경우 입력하라는 내용 출력
	  
  	  if(email == nowEmail){
		  $('#emailtest').text("");
		  $('#submit').removeAttr('disabled');
		  return;
	  }//기존 이메일일경우 아무 반응 없음
	  
	  if(validateEmail(email)) {
  	  $.ajax({
  		  type: "POST",
  		  url : "checkEmail.aj",
  		  data: {"u_email": email},
  		  success:function(data){
  			  const result = $.trim(data);
  			  if(result=="false" && !email == ""){
  			
  			  $('#emailtest').css('color','green');
  			  $('#emailtest').text("변경가능한 이메일입니다.");
  			  $('#submit').removeAttr('disabled');
  			  return;
  			  }else if ( result=="true" && !email == "" && !email == nowEmail){
  			 
  			  $('#emailtest').css('color','red');
  			  $('#emailtest').text("이미 등록되어있는 이메일입니다.");
  			  $('#submit').attr('disabled','disabled');
  			  return;
  			  }
  		  }//success 콜백함수 종료지점
  	  });// ajax
  	 
  	  
  	  
  	  } else {
  		$('#emailtest').css('color','red');
  		$('#emailtest').text("유효한 이메일 주소를 입력해주세요.");
  		$('#submit').attr('disabled','disabled');
  		return;
  	  }
  	  
  });// 이메일중복확인 종료

  //이메일 정규식 유효성 검사
  function validateEmail(email) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regExp.test(email);
  }
  
//전화번호 중복검사
  $('#phone').keyup(function() {
   	  
   	 var phone = $('#phone').val();
   	 
   	 if(phone == ""){
  		  $('#phonemsg').css('color','red');
  		  $('#phonemsg').text("전화번호를 입력해주세요.");
  		  $('#submit').attr('disabled','disabled');
  		  return;
  	  }//전화번호값이 빈칸일경우 입력하라는 내용 출력
   	  
   	 if(phone == nowPhone){
		  $('#phonemsg').text("");
		  $('#submit').removeAttr('disabled');
		  return;
	  }//기존 전화번호일경우 아무 반응 없음
	  
   	  if(validatePhone(phone)){
   	  $.ajax({
   		  type: "POST",
   		  url : "checkPhone.aj",
   		  data: {"u_phone": phone},
   		  success:function(data){
   			  const result = $.trim(data);
   			  if(result=="false" && !phone == ""){
   			
   			  $('#phonemsg').css('color','green');
   			  $('#phonemsg').text("변경가능한 전화번호입니다.");
   			  $('#submit').removeAttr('disabled');
   			  return;
   			  }else if ( result=="true" && !phone == ""){
   			 
   			  $('#phonemsg').css('color','red');
   			  $('#phonemsg').text("이미 등록되어있는 전화번호입니다.");
   			  $('#submit').attr('disabled','disabled');
   			  return;
   			  }
   		  }//success 콜백함수 종료지점
   	  });// ajax
   	  
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
</script>

	</body>
</html>
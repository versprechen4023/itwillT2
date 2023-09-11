<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = (String)session.getAttribute("id");
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
	<link rel="stylesheet" href="./css/login_gr.css">

	<body>
	  <form action="readyresetpassPro.my" id="resetpass" name="resetpass" method="post">
	  
<!-- 로고 자리 -->		
	<h1 id="fh5co-logo1"><a href="main.gr">
	<img src="./images/LOGO.png" style="margin-top:-10px; margin-bottom: 10px;"></a></h1>
		
<!-- 테두리선 시작 -->
			<div class="membership" style="border: 1px solid #ccc; border-radius: 10px; padding: 30px;">
<!-- 세션값 아이디 넘기기 -->
			<input type="hidden" id="u_id" name="u_id" value="<%=id%>">
<!-- 비밀번호 -->
			  <div>
				<br><label class="imp">비밀번호 확인</label>
				<div>
				  <input type="password" id="u_pass" name="u_pass" placeholder="비밀번호를 입력하세요">
			    </div>
			    <span id="passtest"></span>
			  </div>
		
			 
<!-- 가로줄 추가 -->
		<hr style="margin: 20px 0; border: 2 solid #ccc;">
		

<!-- 비밀번호 재설정 버튼 -->
		<div id="button">
			<button type="submit" id="submit" name="submit" style="width: 390px; font-size: 16px; margin-left: 0; ">비밀번호 재설정</button>
		   </div>
	    </div>   
<!-- 테두리선 끝 -->

		   	       
<!-- <div class="centered-text">
    <p>
        <a href="signup.me" class="custom-text"><b>회원가입</b></a>
        <span class="vertical-line"></span>
        <a href="fineid.me" class="custom-text"><b>아이디 찾기</b></a>
    </p>
</div> 비밀번호변경페이지 하단에 회원가입 아이디찾기는 필요없을 듯-->
      
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

});//submit기능 제어 끝
</script>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>
</head>

<!-- Theme style  -->
<link rel="stylesheet" href="./css/member_gr.css">

<!-- 로그인 스타일  -->
<link rel="stylesheet" href="./css/login_gr.css">

<body>

	<form action="loginPro.me" id="login" method="post">

		<!-- 로고 자리 -->
	<h1 id="fh5co-logo1"><a href="main.gr">
	<img src="./images/LOGO.png" style="margin-top:-10px; margin-bottom:-5px;"></a></h1>

		<!-- 테두리선 시작 -->
		<div class="membership"
			style="border: 1px solid #ccc; border-radius: 10px; padding: 30px; margin-top: 40px;">


			<!-- 아이디 -->
			<div>
				<label class="imp2" style="font-size: 16px;">아이디</label>
				<div>
					<input type="text" id="u_id" name="u_id" placeholder="아이디를 입력하세요"
						name="id" style="width: 390px;">
				</div>
				<span id="idmsg"></span>
			</div>

			<!-- 비밀번호 -->
			<div>
				<br>
				<label class="imp2" style="font-size: 16px;">비밀번호</label>
				<div>
					<input type="password" id="u_pass" name="u_pass" placeholder="비밀번호를 입력하세요"
						style="width: 390px;">
				</div>
				<span id="passtest"></span>
			</div>


			<!-- 가로줄 추가 -->
			<hr style="margin: 20px 0; border: 2 solid #ccc;">


			<!-- 로그인 버튼 -->
			<div id="button">
				<button type="submit"
					style="width: 390px; font-size: 16px; margin-left: 0;">로그인</button>
			</div>
		</div>
		<!-- 테두리선 끝 -->

	<div class="centered-text">
		<p>
			<a href="signup.me" class="custom-text"><b>회원가입</b></a> 
			<span class="vertical-line"></span> 
			<a href="findid.me" class="custom-text"><b>아이디 찾기</b></a>
			<span class="vertical-line"></span> 
			<a href="findpass.me" class="custom-text"><b>비밀번호 찾기</b></a>
		</p>
	</div>

    </form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$('#login').submit(function() {
	
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

});//submit기능 제어 끝
$('#u_id').keyup(function() {

	  var id = $('#u_id').val();
	  
	  if(id != ""){
		$('#idmsg').text("");
	    return;
	  }
});

$('#u_pass').keyup(function() {

	  var pass = $('#u_pass').val();
	  
	  if(pass != ""){
		$('#passtest').text("");
	    return;
	  }
});

</script>
</body>
</html>

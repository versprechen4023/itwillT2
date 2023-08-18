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
	<link rel="stylesheet" href="./css/signup_gr.css">
	
	<body>
 <h1> 그루미 회원 가입 </h1>
		<form>
			<div class="membership">
			  <div>
				<label class="imp">아이디</label>
				<div>
				  <input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요" required name="id" >
				</div>
				<span id="idmsg">아이디 입력 테스트</span>
			  </div>
		
			  <div>
				<label class="imp">비밀번호</label>
				<input type="password" placeholder="비밀번호를 입력해주세요." required>
			  </div>
		
			  <div>
				<label class="imp">비밀번호 확인</label>
				<input type="password" placeholder="비밀번호를 한번 더 입력해주세요." required>
			  </div>
		
			  <div>
				<label class="imp">이름</label>
				<input type="text" placeholder="이름을 입력해주세요" required>
			  </div>
		
			  <div>
				<label class="imp">휴대폰</label>
				<div>
				  <input type="tel" placeholder="숫자만 입력해주세요" required>
				  <button type="button">인증번호받기</button>
				</div>
			  </div>
		
			  <div>
				<label class="imp">주소</label>
				<div>
				  <input type="text" placeholder="도로명, 지번, 건물명 검색" required>
				  <img src="https://res.kurly.com/kurly/ico/2021/search_20_20_333.svg" alt="search">
				</div>
		
			  </div>
		
			  
		
		  
			</div>
			
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
		
			<button type="submit">가입하기</button>
		
		  </form> 
	<!-- jQuery -->
	<script src="./js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="./js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="./js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="./js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="./js/jquery.flexslider-min.js"></script>
	
	
	<!-- MAIN JS -->
	<script src="./js/main.js"></script>
	
<script type="text/javascript">

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

	</body>
</html>

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
		
<!-- css -->
	<link rel="stylesheet" href="css/icomoon.css">
	<link rel="stylesheet" href="css/admin_gr.css">
	
<body>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
<!-- 		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft"> -->
		<h2 class="fh5co-review-title">
		관리자 페이지</h2>
		
		<div class="row row-bottom-padded-md">
<!-- 관리자 메뉴 -->
<!-- 		<h3 class="admin-select animate-box" data-animate-effect="fadeInLeft"> -->
		<h3 class="admin-select">
		<a href="admin_main.ad">관리자메인</a>
		<a href="admin_userCheck.ad" class="admin-button-active">회원정보</a>
		<a href="admin_resCheck.ad">예약정보</a>
		<a href="admin_storeCheck.ad">지점정보</a></h3>
<!-- [회원정보] 내용 -->
<div class="admin-content">
<!-- <table class="admin-userCheck animate-box" data-animate-effect="fadeInLeft"> -->
<table class="admin-userCheck">
    <tr><th colspan="3">회원 정보</th></tr>
    <tr><td>회원번호</td><td>아이디</td><td>이름</td><td>전화번호</td><td>이메일</td><td>가입일자</td><td>예약횟수</td><td>포인트</td></tr>
    <tr><td>1</td><td>abc1</td><td>딩딩1</td><td>010-1234-5671</td><td>abc1@groom.com</td><td>23.08.22</td><td>4</td><td>10,000</td></tr>
    <tr><td>2</td><td>abc2</td><td>딩딩2</td><td>010-1234-5672</td><td>abc2@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>3</td><td>abc3</td><td>딩딩3</td><td>010-1234-5673</td><td>abc3@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>4</td><td>abc4</td><td>딩딩4</td><td>010-1234-5674</td><td>abc4@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>5</td><td>abc5</td><td>딩딩5</td><td>010-1234-5675</td><td>abc5@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>6</td><td>abc6</td><td>딩딩6</td><td>010-1234-5676</td><td>abc6@groom.com</td><td>23.08.22</td><td>1</td><td>100</td></tr>
    <tr><td>7</td><td>abc7</td><td>딩딩7</td><td>010-1234-5677</td><td>abc7@groom.com</td><td>23.08.22</td><td>2</td><td>5,000</td></tr>
    <tr><td>8</td><td>abc8</td><td>딩딩8</td><td>010-1234-5678</td><td>abc8@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>9</td><td>abc9</td><td>딩딩9</td><td>010-1234-5679</td><td>abc9@groom.com</td><td>23.08.22</td><td>2</td><td>300</td></tr>
    <tr><td>10</td><td>abc10</td><td>딩딩10</td><td>010-1234-5680</td><td>abc10@groom.com</td><td>23.08.22</td><td>4</td><td>10,000</td></tr>
</table>
<!-- <div class="userCheck-next animate-box" data-animate-effect="fadeInLeft"> -->
<div class="userCheck-next">
    <a href="#"><span class="m-tcol-c">&lt;</span></a>
    <a href="#">1</a>
    <a href="#">2</a>
    <a href="#">3</a>
    <a href="#">4</a>
    <a href="#">5</a>
    <a href="#"><span class="m-tcol-c">&gt;</span></a>
</div>
</div>
<!-- [회원정보] 끝 -->
		</div>
		</div>
		</div>

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
	</body>
</html>
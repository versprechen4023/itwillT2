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
<jsp:include page="../inc/aside_admin.jsp"></jsp:include>
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
		<a href="admin_userCheck.ad">회원정보</a>
		<a href="admin_resCheck.ad">예약정보</a>
		<a href="admin_storeCheck.ad" class="admin-button-active">지점정보</a></h3>
<!-- [지점정보] 내용 -->
<div class="admin-content">
<!-- <table class="admin-storeCheck1 animate-box" data-animate-effect="fadeInLeft"> -->
<table class="admin-storeCheck1">
    <tr><th colspan="3">지점 정보는 추가할 내용 더 생각하기</th></tr>
    <tr><td>지점명</td><td>주소</td><td>전화번호</td></tr>
    <tr><td>서면점</td><td>서면어딘가</td><td>051-1234-5678</td></tr>
</table>
<!-- <table class="admin-storeCheck2 animate-box" data-animate-effect="fadeInLeft"> -->
<table class="admin-storeCheck2">
    <tr><td>직원번호</td><td>직급</td><td>직원이름</td></tr>
    <tr><td>1</td><td>원장</td><td>딩딩딩</td></tr>
</table>
</div>
<!-- [지점정보] 끝 -->
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
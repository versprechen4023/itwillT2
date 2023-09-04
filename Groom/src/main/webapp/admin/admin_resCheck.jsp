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
<!-- 	<link href="./css/admin_gr.css" rel="stylesheet" type="text/css">
적용이 안돼서 일단 위에 바로 작성중-->
<!-- 	ㅇ -->
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
		<a href="admin_main.ad" class="admin_menu">관리자메인</a>
		<a href="admin_userCheck.ad">회원정보</a>
		<a href="admin_rescheck.ad" class="admin-button-active">예약정보</a>
		<a href="admin_storeCheck.ad">지점정보</a></h3>
<!-- [예약정보] 내용 -->
<div class="admin-content">
<!-- <table class="admin-resCheck animate-box" data-animate-effect="fadeInLeft"> -->
<table class="admin-resCheck">
    <tr><th colspan="3">예약 정보</th></tr>
    <tr class="font-bold"><td>예약번호</td>
    					  <td>날짜</td>
    					  <td>시간</td>
    					  <td>선택메뉴</td>
    					  <td>매장</td>
    					  <td>담당</td>
    					  <td>예약자</td>
    					  <td>연락처</td>
    					  <td>결제금액</td>
    					  <td></td>
    					  <td></td>
    					  <td></td></tr>
    					  
    <tr><td>1001</td>
    	<td>23.08.22</td>
    	<td>11:00</td>
    	<td>[미용]대형견 15kg</td>
    	<td>서면점</td>
    	<td>원장 딩딩딩</td>
    	<td>동동동</td>
    	<td>010-1234-5678</td>
    	<td>300,000</td>
    	<td>0</td>
    	<td><input type="button" value="완료"></td>
    	<td><input type="button" value="취소"></td></tr>
</table>
<!-- <div class="resCheck-next animate-box" data-animate-effect="fadeInLeft"> -->
<div class="resCheck-next">
    <a href="#"><span class="m-tcol-c">&lt;</span></a>
    <a href="#">1</a>
    <a href="#">2</a>
    <a href="#">3</a>
    <a href="#">4</a>
    <a href="#">5</a>
    <a href="#"><span class="m-tcol-c">&gt;</span></a>
</div>
</div>
<!-- [예약정보] 끝 -->

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
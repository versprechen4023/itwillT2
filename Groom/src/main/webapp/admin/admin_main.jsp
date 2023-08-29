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
	
<body>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
<!-- 		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft"> -->
		<h2 class="fh5co-review-title"">
		관리자 페이지</h2>
		<div class="admin_all">
		<div class="row row-bottom-padded-md">
<!-- 관리자 메뉴 -->
<!-- 		<h3 class="admin-select animate-box" data-animate-effect="fadeInLeft"> -->
		<h3 class="admin-select">
		<a href="admin_main.ad" class="admin-button-active">관리자메인</a>
		<a href="admin_userCheck.ad">회원정보</a>
		<a href="admin_resCheck.ad">예약정보</a>
		<a href="admin_storeCheck.ad">지점정보</a></h3>
<!-- [관리자메인] 내용 -->
<div class="admin-content">
<!-- <table class="admin-main1 animate-box" data-animate-effect="fadeInLeft"> -->
<table class="admin-main1">
    <tr><th colspan="3">현재 현황</th></tr>
    <tr><td>총 회원수</td><td>예약건수</td><td>취소건수</td></tr>
    <tr><td>0명</td><td>0건</td><td>0건</td></tr>
    
    <tr><th colspan="3">지점별 현황</th></tr>
    <tr><td>서면점</td><td>명지점</td><td>율하점</td></tr>
    <tr><td>0건</td><td>0건</td><td>0건</td></tr>
</table>
<!-- <table class="admin-main2 animate-box" data-animate-effect="fadeInLeft"> -->
<table class="admin-main2">      
    <tr><th colspan="3">　</th></tr>
    <tr><th>고객센터　</th>
    	<td><button class="admin-button" onclick="window.location.href='notice.bo'">공지사항</button></td>
    	<td>　</td>
        <td><button class="admin-button" onclick="window.location.href='faq.bo'">자주 묻는 질문</button></td>
        <td>　</td>
        <td><button class="admin-button" onclick="window.location.href='qna.bo'">1:1문의</button></td></tr>
</table>
</div>
<!-- [관리자메인] 끝 -->

		</div>
	</div>
		</div>
		</div>
<!-- ㅇ -->
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
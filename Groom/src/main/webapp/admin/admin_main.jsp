<%@page import="web.groom.dto.AdminDTO"%>
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
<%
AdminDTO adminDTO = (AdminDTO)request.getAttribute("adminDTO");
%>
<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
<!-- 		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft"> -->
		<h2 class="fh5co-review-title"">
		관리자 페이지</h2>
		<div class="admin_all">
		<div class="row row-bottom-padded-md">
<!-- 관리자 메뉴 -->
<div style="width: 100%;">
		<h3 class="admin-select">
		<a href="admin_main.ad" class="admin-button-active">관리자메인</a>
		<a href="admin_userCheck.ad">회원정보</a>
		<a href="admin_resCheck.ad">예약정보</a>
		<a href="admin_storeCheck.ad">지점정보</a></h3>
</div>		
<!-- [관리자메인] 내용 -->
<div class="admin-content-main">

	<div class="admin-content1">
		<table class="admin-main1">
			<tr><th colspan="3">현재 현황</th></tr>
   			<tr class="font-bold border-bottom"><td>총 회원수</td><td>총 리뷰수</td><td>총 예약중</td><td>총 오늘 예약</td></tr>
    		<tr><td><%=adminDTO.getTotal_user() %>명</td><td><%=adminDTO.getTotal_rev() %>개</td><td><%=adminDTO.getTotal_res() %>건</td><td><%=adminDTO.getToday_res() %>건</td></tr>
		</table>
	</div>
	
	<div class="admin-content1">
	
		<div>
		<table class="admin-main2">
			<thead>　</thead>
			<tr><th colspan="2">지점별 현황</th></tr>
			<tr class="font-bold"><td colspan="2">서면점</td></tr>
    		<tr class="font-bold border-bottom"><td>예약중</td><td>오늘예약</td></tr>
    		<tr><td><%=adminDTO.getRes_a() %>건</td><td><%=adminDTO.getToday_res_a() %>건</td></tr>
    	</table>	
    	</div>
    	
    	<div>
		<table class="admin-main2">
			<thead>　</thead>
			<tr><th colspan="2">　</th></tr>
			<tr class="font-bold"><td colspan ="2">명지점</td></tr>
    		<tr class="font-bold border-bottom"><td>예약중</td><td>오늘예약</td></tr>
    		<tr><td><%=adminDTO.getRes_b() %>건</td><td><%=adminDTO.getToday_res_b() %>건</td></tr>
    	</table>	
    	</div>
    	
    	<div>
		<table class="admin-main2">
			<thead>　</thead>
			<tr><th colspan="2">　</th></tr>
			<tr class="font-bold"><td colspan ="2">율하점</td></tr>
    		<tr class="font-bold border-bottom"><td>예약중</td><td>오늘예약</td></tr>
    		<tr><td><%=adminDTO.getRes_c() %>건</td><td><%=adminDTO.getToday_res_c() %>건</td></tr>
    	</table>	
    	</div>
    </div>
    
    <div class="admin-content1">
		<table class="admin-main3">
			<tr><th>　</th></tr>
   			<tr><th>고객센터　→</th>
   				<td><button class="admin-button" onclick="window.location.href='notice.bo'">공지사항</button></td>
   				<td>/</td>
   				<td><button class="admin-button" onclick="window.location.href='faq.bo'">자주 묻는 질문</button></td>
   				<td>/</td>
   				<td><button class="admin-button" onclick="window.location.href='qna.bo'">1:1문의</button>
   				<td>　　　　　　　　　　　　</td></tr>
		</table>
	</div>
	
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
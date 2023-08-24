<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	</head>
	
<!-- 	리뷰 css 추가 -->
	<link rel="stylesheet" href="./css/review_gr.css">
	
	<body>
	
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->



	<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft">
		전체리뷰</h2> <!-- fh5co-review-title 클래스 사용중 아님 -->
		
			

	<div class="row row-bottom-padded-md">
<!-- 리뷰 분류 -->
		<h3 class="review-select animate-box" data-animate-effect="fadeInLeft">
		<a href="#" class="review-active">전체</a>
		<a href="#">미용</a>
		<a href="#">목욕</a>
		<a href="#">산책</a></h3>
		
<!-- div  -->	
		<div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->
		<div class="blog-entry">                             <!-- ↗ 블로그 페이지 이동시 보여지는 칸 이미지 -->
			<a href="#" class="blog-img"><img src="#" class="img-responsive" alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a>
		<div class="review-desc">
			<h3><a href="#">미미</a>
			<small>원장 딩딩동</small><small> / 서면점</small></h3>
			<h3>★★★★☆ 4.0</h3>
			<span class="review_text1"><a>홍길동</a> / <a>23.08.10</a> / <a>10번째 예약</a></span>
			<p class="review_text2">후기내용후기내용후기내용후기내용후기내용후기내용후기내용후기내용후기내용후기내용후기내용후기내용후기내용</p>
			<a href="#" class="lead">더보기 <i class="icon-arrow-right3"></i></a>
		</div>
		</div>
		</div>
<!-- div  -->
		</div>

<!-- 페이징? -->
<div class="prev-next animate-box" data-animate-effect="fadeInLeft">
	<a href="#" class="review-active">1</a>
	<a href="#" class="pgR"><span class="m-tcol-c">></span></a>
</div><!--  -->
</div>


<!-- <div class="prev-next"> -->
<!-- <span class="blind">페이지 네비게이션</span> -->
<!-- <a href="#" class="pgL"><span class="m-tcol-c">이전</span></a> -->
<!-- <a href="#" class="on">11</a> -->
<!-- <a href="#">12</a> -->
<!-- <a href="#">13</a> -->
<!-- <a href="#">14</a> -->
<!-- </div> -->


<!-- 아래 Get in touch -->	
	<div id="get-in-touch">
	<div class="fh5co-narrow-content">
	<div class="row">
	<div class="col-md-4 animate-box" data-animate-effect="fadeInLeft">
		<h1 class="fh5co-heading-colored">?????</h1>
	</div>
	</div>
	<div class="row">
	<div class="col-md-6 col-md-offset-3 col-md-pull-3 animate-box" data-animate-effect="fadeInLeft">
		<p class="fh5co-lead">Check Out Our Dog Grooming Reviews! See how our grooming services have delighted pets and owners alike. Join the happy community and treat your furry friend to a makeover they'll love.</p>
		<p class="btn-boxnew"><a href="#" class="btn btn-primary">???</a></p>
	</div>
	</div>
	</div>
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
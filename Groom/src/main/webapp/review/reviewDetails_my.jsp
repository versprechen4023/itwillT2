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
<style>
.review-content {
	width: 800px;
	margin-left: 10%;
}
.blog-img-0 {
	display: flex;
    justify-content: center;
	text-align: center;
	margin: 0 auto;
    padding: 20px;
}
.blog-img-0 img {
	width: 80%;
	height: auto;
}
/* .review-content-top { */
/* 	border-radius: 10px 10px 0 0; */
/* } */
</style>
	
	<body>
	
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->

	<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft">
		리뷰</h2> <!-- fh5co-review-title 클래스 사용중 아님 -->

	<div class="row row-bottom-padded-md">
<!-- div  -->	
		<div class="review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->
		<div class="blog-entry"> 
		<div class="review-desc" style="background-color: #ccc; "> <!-- border-radius: 10px 10px 0 0; -->
			<h3 class="review_text1"><a>홍길동</a> / <a>23.08.10</a> / <a>10번째 예약</a></h3>
			<h3 style="margin-bottom: 0;"><a>★★★★☆ 4.0점</a><a href="#"> / 미미</a>
			<a>원장 딩딩동</a><a> / 서면점</a></h3>
		</div>
		                            <!-- ↗ 블로그 페이지 이동시 보여지는 칸 이미지 -->
			<a href="#" class="blog-img-0"><img src="./images/dog3.jpg" class="img-responsive" alt="이미지"></a>
		<div class="review-desc">
			<p class="review_text2">후기내용</p>
		</div>
		</div>
		</div>
<!-- div  -->

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
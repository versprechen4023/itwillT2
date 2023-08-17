<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>

	</head>
	
	<!-- 메인페이지 css 오버라이드  -->
	<link rel="stylesheet" href="./css/main_gr.css">
	
	<body>
	<div id="fh5co-page">
        <!-- 사이드바호출 -->
		<jsp:include page="../inc/aside.jsp"></jsp:include>

        <!-- 비디오관련태그 -->
		<div id="fh5co-main">
			<section id="비디오자리">
				<div class="overlay"></div>
				<div class="container-fluid">
				<!-- 비디오 자동실행 오른쪽에 autoplay muted 추가 controls 명시하면 사용자가 비디오 멈출 수 있음-->
					<div class="video-container">
						<video id="video" autoplay loop muted style="max-width: 100%; height: auto;">
							<source src="./images/parkpark.mp4" type="video/mp4">
						</video>
						
						<div class="video-text">이름</div>
					</div>
				</div>
			</section>
            <!-- 비디오관련태그 끝-->
            
            <!-- 둥근이미지관련태그 -->
			<section id="둥근이미지">
				<div class="container px-5">
					<div class="row gx-5 align-items-center">
						<div class="col-lg-6">
							<div class="p-5 text-center">
								<p class="circle-text">실시간 반려동물 미용실 예약 플랫폼</p>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="p-5">
								<img class="img-responsive img-circle" src="./images/dog.jpg"
									alt="..." />
							</div>
						</div>
					</div>
				</div>
			</section>

			<section id="둥근이미지">
				<div class="container px-5">
					<div class="row gx-5 align-items-center">
						<div class="col-lg-6 order-lg-2">
							<div class="p-5">
								<img class="img-responsive img-circle" src="./images/dog.jpg" 
									alt="엑박시출력할텍스트" />
							</div>
						</div>
						<div class="col-lg-6 order-lg-1">
							<div class="p-5">
								<p class="circle-text">무슨무슨 서비스등</p>
							</div>
						</div>
					</div>
				</div>
			</section>
			
			<section id="둥근이미지">
				<div class="container px-5">
					<div class="row gx-5 align-items-center">
						<div class="col-lg-6">
							<div class="p-5 text-center">
								<p class="circle-text">아무글자</p>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="p-5">
								<img class="img-responsive img-circle" src="./images/dog.jpg"
									alt="..." />
							</div>
						</div>
					</div>
				</div>
			</section>
            <!-- 둥근이미지관련태그 끝-->
            
			<div class="fh5co-narrow-content">
				<div class="row row-bottom-padded-md">
					<div class="col-md-4 col-sm-6 col-padding animate-box"
						data-animate-effect="fadeInLeft">
						<div class="blog-entry">
							<!-- HOME 페이지 RECENT BLOG 이미지 칸 -->
							<a href="#" class="blog-img"><img src="./images/img-1.jpg"
								class="img-responsive"
								alt=""></a>
							<div class="desc">
								<h3>
									<a href="#">col-md를 수정해 3개로</a>
								</h3>
								<span><small>by Admin </small> / <small> Web
										Design </small> / <small> <i class="icon-comment"></i> 14
								</small></span>
								<p>Design must be functional and functionality must be
									translated into visual aesthetics</p>
								<a href="#" class="lead">Read More <i
									class="icon-arrow-right3"></i></a>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6 col-padding animate-box"
						data-animate-effect="fadeInLeft">
						<div class="blog-entry">
							<!-- HOME 페이지 RECENT BLOG 이미지 칸 -->
							<a href="#" class="blog-img"><img src="./images/img-2.jpg"
								class="img-responsive"
								alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a>
							<div class="desc">
								<h3>
									<a href="#">줄여</a>
								</h3>
								<span><small>by Admin </small> / <small> Web
										Design </small> / <small> <i class="icon-comment"></i> 14
								</small></span>
								<p>Design must be functional and functionality must be
									translated into visual aesthetics</p>
								<a href="#" class="lead">Read More <i
									class="icon-arrow-right3"></i></a>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6 col-padding animate-box"
						data-animate-effect="fadeInLeft">
						<div class="blog-entry">
							<a href="#" class="blog-img"><img src="./images/img-3.jpg"
								class="img-responsive"
								alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a>
							<div class="desc">
								<h3>
									<a href="#">봤습니다</a>
								</h3>
								<span><small>by Admin </small> / <small> Web
										Design </small> / <small> <i class="icon-comment"></i> 14
								</small></span>
								<p>Design must be functional and functionality must be
									translated into visual aesthetics</p>
								<a href="#" class="lead">이동하기 <i class="icon-arrow-right3"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div><!-- 페이지 엔드 -->

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
	
     <!--스타일시트연계 자바스크립트 -->
     <script src="./js/my.js"></script>

	</body>
</html>


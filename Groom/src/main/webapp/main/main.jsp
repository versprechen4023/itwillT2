<%@page import="java.util.List"%>
<%@page import="web.groom.dto.ReviewDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>

	</head>
	
	<!-- 메인페이지 css 오버라이드  -->
	<link href="./css/review_gr.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="./css/main_gr.css">
	
	<body> <!--  옆에 사이드바(네비바) 주소는 aside.jsp에서 수정해야함.  -->
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
				 <h1>베스트 리뷰</h1>
				<!-- 목록 시작 -->	
<%
List<ReviewDTO> reviewList
=(List<ReviewDTO>)request.getAttribute("reviewList");
SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
if(reviewList != null){
for (ReviewDTO reviewDTO : reviewList){

// ========= 별점 받아서 별출력하는 코드 ㄱ
//int rating = reviewDTO.getRev_rating(); // rev_rating 값 int로 바꾸면 수정하도록
int rating = Integer.parseInt(reviewDTO.getRev_rating());
String stars = "";
for (int i = 1; i <= 5; i++) {
if (i <= rating) {
	stars += "★";
	} else {
		stars += "☆";
		}
}
//enum > 문자
	String s_location = reviewDTO.getS_location();
	String emp_grade = reviewDTO.getEmp_grade();
		String location = "";
	if (s_location.equals("A")) {
	    location = "서면점";
	} else if (s_location.equals("B")) {
	    location = "명지점";
	} else if (s_location.equals("C")) {
	    location = "율하점";
	} else {
	    location = "알 수 없음";
	}
	String grade = "";
	if (emp_grade.equals("A")) {
		grade = "원장";
	} else if (emp_grade.equals("B")) {
		grade = "실장";
	} else if (emp_grade.equals("C")) {
		grade = "수석";
	} else {
		grade = "알 수 없음";
	}
%>		
<!-- 리뷰 목록  -->	
		<div class="col-md-4 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
			<div class="blog-entry">
					<!-- HOME 페이지 RECENT BLOG 이미지 칸 -->
					<div class="img-wrapper"> 
						<a href="reviewContent.re?rev_num=<%=reviewDTO.getRev_num() %>" class="blog-img">
						<img src="upload/<%=reviewDTO.getRev_img_url()%>" class="img-responsive" alt="이미지없음"></a>
					</div>	
					<div class="desc">
						<h3><a><%=reviewDTO.getPro_name() %></a><br>
						<small><%=grade %> <%=reviewDTO.getEmp_name() %></small><small> / <%=location %></small></h3>
						<h3><%=stars %></h3>
						<span class="review_text1"><a><%=reviewDTO.getU_name() %></a> / <a><%=format.format(reviewDTO.getRev_date()) %></a> / <a><%=reviewDTO.getU_count() %>번째 방문</a></span>
						<p class="review_text2"><%=reviewDTO.getRev_content() %></p>
						<a href="reviewContent.re?rev_num=<%=reviewDTO.getRev_num() %>" class="lead">더보기 <i class="icon-arrow-right3"></i></a>
				</div>
			</div>
		</div>
<%
}
}
%>
				
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

	</body>
</html>


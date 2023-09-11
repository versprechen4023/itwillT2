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
							<source src="./images/main3.mp4" type="video/mp4">
						</video>
						
						<!-- <div class="video-text">이름</div> -->
					</div>
				</div>
			</section>
            <!-- 비디오관련태그 끝-->
            
            <!-- 둥근이미지관련태그 -->
			<section id="둥근이미지">
				<div class="container px-5">
					<div class="row gx-5 align-items-center">
						<div class="col-lg-6">
							<div class="text-center1">
								<p class="circle-text">
								<h1>반려동물 미용</h1>
								미용은 반려동물의 외모뿐만 아니라 <br>생활과 건강에도 큰 영향을 미칩니다.<br>
								정기적인 미용은 반려동물의 피부와 <br>털을 건강하게 유지하는 데 도움을 주며,<br>
								털, 발톱, 귀 등을 깔끔하게 관리하고 <br>점검하여 특정 질병이나 감염을<br>
								조기에 발견하고 예방할 수 있습니다. <br>또한 관리되지 않은 긴 털과 발톱을<br>
								정리하여 반려동물의 편안함을 <br>증진시킬 수 있습니다.<br></p>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="p-5">
								<img class="img-responsive img-circle" src="./images/mainimage1.jpg">
									
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
								<img class="img-responsive img-circle" src="./images/mainimage4.png">
							</div>
						</div>
						<div class="col-lg-6 order-lg-1">
							<div class="text-center2">
								<p class="circle-text">
								<h1>반려동물 목욕</h1>
								목욕은 피부 문제 예방 및 건강을 개선하는 데 도움을 줍니다.<br>
								목욕을 통해 먼지, 땀 그리고 다른 미생물로부터<br>반려견의 피부를 깨끗이 유지하며
								알레르기 유발 물질을<br> 제거하고 진드기와 벼룩과 같은 해충의 예방에도 도움을 줄 수 있습니다.
								또한 반려동물의 체취를 제거하고 털을 청결하게 관리하며
								정기적인 목욕은 반려동물의 자연적인 <br>피부 기름층을 유지하는 데 도움이 됩니다.
								<br>이는 피부와 털을 보호하는 역할을 합니다.</p>
							</div>
						</div>
					</div>
				</div>
			</section>
			
			<section id="둥근이미지">
				<div class="container px-5">
					<div class="row gx-5 align-items-center">
						<div class="col-lg-6">
							<div class="text-center3">
							<br>
							<h1>스포팅컷</h1>
								<p class="circle-text">
								다리에 볼륨감을 주는 미용법입니다.<br>
								털이 엉키거나 이물질이 묻기 쉬운 몸통은 <br>상대적으로 짧게 자르고 
								다리털은 둥글게 다듬어 <br>실용성과 심미성을 모두 잡습니다.<br> 
								스포팅컷은 반려견의 체형을 보정하기에도 좋은 스타일입니다.<br> 
								질병 및 수술등으로 한쪽 다리가 왜소해진 강아지라면<br> 
								스포팅컷을 통해 양쪽 다리의 균형감을 맞출 수 있습니다.
								</p>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="p-5">
								<img class="img-responsive img-circle" src="./images/mainimage3.png">
							</div>
						</div>
					</div>
				</div>
			</section>
            <!-- 둥근이미지관련태그 끝-->
            
			<div class="fh5co-narrow-content">
				<div class="row row-bottom-padded-md">
				 <h2>베스트 리뷰</h2>
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
	/* String s_location = reviewDTO.getS_location();
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
	} */
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
						<small><%=reviewDTO.getEmp_grade() %> <%=reviewDTO.getEmp_name() %></small><small> / <%=reviewDTO.getS_location() %></small></h3>
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


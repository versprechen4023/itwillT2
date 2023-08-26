<%@page import="web.groom.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	</head>
	
<!-- 	리뷰상세 css 추가 -->
	<link rel="stylesheet" href="./css/reviewContent_gr.css">
	
<body>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<%
// 줄바꿈 자동으로해서 출력해주는 코드 ㄱ
int rev_num = Integer.parseInt(request.getParameter("rev_num"));
ReviewDTO reviewDTO=(ReviewDTO)request.getAttribute("reviewDTO");
if (reviewDTO != null) {
	String rev_content = reviewDTO.getRev_content();
	rev_content = rev_content.replace("\n", "<br>"); // 줄바꿈 문자를 <br> 태그로 변환

// 별점 받아서 별출력하는 코드 ㄱ
//     int rating = reviewDTO.getRev_rating(); // rev_rating 값 int로 바꾸면 수정하도록
int rating = Integer.parseInt(reviewDTO.getRev_rating());
String stars = "";
for (int i = 1; i <= 5; i++) {
	if (i <= rating) {
		stars += "★";
		} else {
			stars += "☆";
			}
	}
%>


	<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft" style="width: 300px;">
		리뷰</h2><br> <!-- fh5co-review-title 클래스 사용중 아님 -->
	<div class="reviewContent-main">
	
<!-- 내용  -->	
		<div class="review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->

		<div class="content-top">
		<div><p class="user-info"><%=reviewDTO.getU_name() %> / <a><%= stars %></a> / <%=reviewDTO.getRev_date() %> / <%=reviewDTO.getU_count() %>번째 방문</p>
			 <p class="product-info"><%=reviewDTO.getPro_name() %> / <%=reviewDTO.getEmp_grade() %> <%=reviewDTO.getEmp_name() %> / <%=reviewDTO.getS_location() %></p></div>
		<div><input type="button" value="삭제">
			 <input type="button" value="수정"></div>	
		</div>
		<div class="content-middle">
		<p>
		<%=rev_content %>
		</p>
		<br>
		<img src="upload/<%=reviewDTO.getRev_img_url() %>" alt="이미지">
		</div>
		<!-- 답글  -->	
		<div class="re-review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->
		<div class="recontent-top">
		<div><h4 class="recoment1">Groom <%=reviewDTO.getS_location() %></h4>
			 <p class="recoment2">소중한 리뷰 감사합니다!</p></div>
		<div><input type="button" value="답글작성">
			 <input type="button" value="삭제">	
			 <input type="button" value="수정"></div>	
		</div>
		</div>
		<!-- 답글  -->
		</div>
		<br><br>
<!-- 내용 끝  -->
</div>
</div>
		</div>
<%
} else {
%>
	<p>내용 없음.</p>
<%
}
%>	
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
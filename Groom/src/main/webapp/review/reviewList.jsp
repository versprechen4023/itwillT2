<%@page import="web.groom.dto.ReviewDTO"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	</head>
	
	<link rel="stylesheet" href="css/icomoon.css">
<!-- 	리뷰 css 추가 -->
	<link href="./css/review_gr.css" rel="stylesheet" type="text/css">
<style>
.review-active 
</style>	
	<body>
<%
String id = (String)session.getAttribute("id");
String role = (String)session.getAttribute("role"); 
String num = (String)session.getAttribute("num");

List<ReviewDTO> reviewList
=(List<ReviewDTO>)request.getAttribute("reviewList");
//
// =============== 페이징코드 ㄱ
int itemsPerPage = 10; // 페이지당 아이템 수
int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
int startIndex = (currentPage - 1) * itemsPerPage;
int endIndex = Math.min(startIndex + itemsPerPage, reviewList.size());
int totalPages = (int) Math.ceil((double) reviewList.size() / itemsPerPage);

List<ReviewDTO> visibleItems = reviewList.subList(startIndex, endIndex);
%>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->

	<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft">
		전체리뷰</h2> <!-- fh5co-review-title 클래스 사용중 아님 -->
		
<div class="review-select"><!-- 임시버튼 -->
<a href="reviewWrite.re" style="width: 150px">리뷰작성(임시)</a><!-- 임시버튼 -->
</div><!-- 임시버튼 -->

	<div class="row row-bottom-padded-md">
<!-- 리뷰 분류 -->
		<h3 class="review-select animate-box" data-animate-effect="fadeInLeft">
		<a href="reviewList.re?pro_name= " class="review-active"> 전체 </a>
		<a href="reviewList1.re?pro_name=미용">미용</a>
		<a href="reviewList2.re?pro_name=목욕">목욕</a>
		<a href="reviewList3.re?pro_name=스파">스파</a>
		<a href="#">상품명1</a>
		<a href="#">상품명2</a>
		<a href="#">상품명3</a>
		<a href="#">상품명4</a></h3>
		
<!-- 목록 시작 -->	
<%
for (ReviewDTO reviewDTO : visibleItems){

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
%>		
<!-- 리뷰 목록  -->	
		<div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->
		<div class="blog-entry">                             <!-- ↗ 블로그 페이지 이동시 보여지는 칸 이미지 -->
			<a href="reviewContent.re?rev_num=<%=reviewDTO.getRev_num() %>" class="blog-img"><img src="<%=reviewDTO.getRev_img_url() %>" class="img-responsive" alt="이미지없음"></a>
		<div class="review-desc">
			<h3><a><%=reviewDTO.getPro_name() %></a><br>
			<small><%=reviewDTO.getEmp_grade() %> <%=reviewDTO.getEmp_name() %></small><small> / <%=reviewDTO.getS_location() %></small></h3>
			<h3><%=stars %></h3>
			<span class="review_text1"><a><%=reviewDTO.getU_name() %></a> / <a><%=reviewDTO.getRev_date() %></a> / <a><%=reviewDTO.getU_count() %>번째 예약</a></span>
			<p class="review_text2"><%=reviewDTO.getRev_content() %></p>
			<a href="reviewContet.re?rev_num=<%=reviewDTO.getRev_num() %>" class="lead">더보기 <i class="icon-arrow-right3"></i></a>
<%
String re_content = reviewDTO.getRe_content();
if(id != null){
if(role.equals("admin")){
	if(re_content != null){
%>
			<a style="font-size: 5px; color: black;">(답글O)</a>
<% }else{ %>
			<a style="font-size: 5px; color: red;">(답글X)</a>
<%
}}}
%>
		</div>
		</div>
		</div>
<!-- 리뷰 목록 끝  -->
<%
	}
%>
		</div>

<!-- 페이징 코드 5개씩 나눠서 페이징 -->
<div class="prev-next" data-animate-effect="fadeInLeft">
    <% if (currentPage > 1) { %>
        <a href="reviewList.re?pro_name=<%= request.getParameter("pro_name") %>&page=<%= currentPage - 1 %>" class="pgL"><span class="m-tcol-c">&lt;</span></a>
    <% } %>
    <% 
    int startPage = ((currentPage - 1) / 5) * 5 + 1; // 현재 페이지 그룹의 시작 페이지 계산
    int endPage = Math.min(startPage + 4, totalPages); // 현재 페이지 그룹의 마지막 페이지 계산
    for (int i = startPage; i <= endPage; i++) { %>
        <a href="reviewList.re?pro_name=<%= request.getParameter("pro_name") %>&page=<%= i %>" <%= (i == currentPage) ? "class='review-active'" : "" %>><%= i %></a>
    <% } %>
    <% if (currentPage < totalPages) { %>
        <a href="reviewList.re?pro_name=<%= request.getParameter("pro_name") %>&page=<%= currentPage + 1 %>" class="pgR"><span class="m-tcol-c">&gt;</span></a>
    <% } %>
</div>

</div>
</div>
	
	
<script>
document.addEventListener("DOMContentLoaded", function() {
    var links = document.querySelectorAll(".review-select a");

    links.forEach(function(link) {
        link.addEventListener("click", function(event) {
            links.forEach(function(innerLink) {
                innerLink.classList.remove("review-active");
            });
            link.classList.add("review-active");
        });
    });
});
</script>

	
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
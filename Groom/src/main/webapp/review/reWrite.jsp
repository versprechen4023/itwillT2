<%@page import="java.text.SimpleDateFormat"%>
<%@page import="web.groom.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	<%
	String id = (String)session.getAttribute("id");
	String role = (String)session.getAttribute("role"); 
	String num = (String)session.getAttribute("num");
	if (!"admin".equals(role)) {
	%>
	<script>
        window.location.href = "main.gr"; // admin이 아니면 메인으로
    </script>
    <%
    }
    %>
	</head>
	
<!-- 	리뷰상세 css 추가 -->
	<link rel="stylesheet" href="./css/reviewContent_gr.css">
	
<body>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<form action="reWritePro.re" method="post">
<%

SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");

// //줄바꿈 자동으로해서 출력해주는 코드 ㄱ
int rev_num = Integer.parseInt(request.getParameter("rev_num"));
ReviewDTO reviewDTO=(ReviewDTO)request.getAttribute("reviewDTO");

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
//enum > 문자
// 	String s_location = reviewDTO.getS_location();
// 	String emp_grade = reviewDTO.getEmp_grade();
// 		String location = "";
// 	if (s_location.equals("A")) {
// 	    location = "서면점";
// 	} else if (s_location.equals("B")) {
// 	    location = "명지점";
// 	} else if (s_location.equals("C")) {
// 	    location = "율하점";
// 	} else {
// 	    location = "알 수 없음";
// 	}
// 	String grade = "";
// 	if (emp_grade.equals("A")) {
// 		grade = "원장";
// 	} else if (emp_grade.equals("B")) {
// 		grade = "실장";
// 	} else if (emp_grade.equals("C")) {
// 		grade = "수석";
// 	} else {
// 		grade = "알 수 없음";
// 	}
%>
	<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft" style="width: 300px;">
		리뷰</h2><br> <!-- fh5co-review-title 클래스 사용중 아님 -->
	<div class="reviewContent-main">
	
<!-- 내용  -->	
		<div class="review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->

		<div class="content-top">
		<div><p class="user-info"><%=reviewDTO.getU_name() %> / <a><%= stars %></a> / <%=format.format(reviewDTO.getRev_date()) %> / <%=reviewDTO.getU_count() %>번째 방문</p>
			 <p class="product-info"><%=reviewDTO.getPro_name() %> / <%=reviewDTO.getEmp_grade() %> <%=reviewDTO.getEmp_name() %> / <%=reviewDTO.getS_location() %></p></div>
			 
		<div>
<%
if(id != null){
	int u_num = reviewDTO.getU_num() ; // 리뷰의 작성자 번호
	if (num.equals(String.valueOf(u_num))) {
%>		
<%-- 		<input type="button" value="삭제" onclick="really('<%=reviewDTO.getRev_num()%>')"> --%>
<%-- 		<input type="button" value="수정" onclick="location.href='reviewUpdate.re?rev_num=<%=reviewDTO.getRev_num()%>'">	 --%>
<%}}%>
		</div>
		</div>
		
		<div class="content-middle">
<%
if (reviewDTO != null) {
	String rev_content = reviewDTO.getRev_content();
	rev_content = rev_content.replace("\n", "<br>"); // 줄바꿈 문자를 <br> 태그로 변환
%>			
		<p><%=rev_content %></p>
<%
}
%>
		<br>
		<%
		String rev_img_url = reviewDTO.getRev_img_url();
		if (rev_img_url != null) {
		%>
		<img src="upload/<%=reviewDTO.getRev_img_url() %>" alt="이미지">
		<%
		}else {
		%>
		
		<%
		}
		%>
		</div>
		<!-- 답글  -->	
		<div class="re-review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->

		<div class="recontent-top-re">
<%
String re_content = reviewDTO.getRe_content();
%>
		<div class="left">
		<h4 class="recoment1">Groom <%=reviewDTO.getS_location()%></h4>
		</div>
		<div class="right">
<%
if(id != null){
	if(role.equals("admin")){
%>
			  <input type="submit" value="답글등록">
<%
if(re_content != null){ // 내용 null이 아니어야 답글 보여요
%>	
			  <input type="button" value="삭제">	
			  <input type="button" value="수정">
<%}}}%>
		</div>
		</div>
		
		<div>
		<textarea rows="30" cols="10" class="recoment3" name="re_content"><%=re_content %></textarea>
		</div>
		<input type="hidden" name="rev_num" value="<%=reviewDTO.getRev_num()%>">
		</div>
		<!-- 답글  -->
		</div>
		<br><br>
<!-- 내용 끝  -->
</div>
</div>
</div>

<script>
function really(rev_num) {
    var result = confirm("정말로 삭제하시겠습니까?");
    if (result) {
        location.href = 'reviewDelete.re?rev_num=' + rev_num;
    }
}
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
	<input type="hidden" name="rev_num" value="<%=reviewDTO.getRev_num()%>">
	</form>
	</body>
</html>
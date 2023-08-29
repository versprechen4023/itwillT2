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
String id = (String)session.getAttribute("id");
String role = (String)session.getAttribute("role"); 
String num = (String)session.getAttribute("num"); 

// 줄바꿈 자동으로해서 출력해주는 코드 ㄱ
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
%>
	<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft" style="width: 300px;">
		리뷰</h2><br> <!-- fh5co-review-title 클래스 사용중 아님 -->
	<div class="reviewContent-main">
	
<!-- 내용  -->	
		<div class="review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->

		<div class="content-top">
		<div><a style="display: none;"><%=reviewDTO.getRev_num() %></a><p class="user-info"><%=reviewDTO.getU_name() %> / <a><%= stars %></a> / <%=reviewDTO.getRev_date() %> / <%=reviewDTO.getU_count() %>번째 방문</p>
			 <p class="product-info"><%=reviewDTO.getPro_name() %> / <%=reviewDTO.getEmp_grade() %> <%=reviewDTO.getEmp_name() %> / <%=reviewDTO.getS_location() %></p></div>
			 
		<div>
<%
if(id != null){
	if(role.equals("admin")){
%>
<input type="button" value="(관리자)&#10;삭제" onclick="really1('<%=reviewDTO.getRev_num()%>')"
style="font-size: 5px; padding: 1px;">
<%}} %>

<%
String re_content = reviewDTO.getRe_content();
if(id != null){
	int u_num = reviewDTO.getU_num() ; // 리뷰의 작성자 번호
	if (num.equals(String.valueOf(u_num))) {
		if (re_content == null){
%>		
		<input type="button" value="삭제" onclick="really1('<%=reviewDTO.getRev_num()%>')">
		<input type="button" value="수정" onclick="location.href='reviewUpdate.re?rev_num=<%=reviewDTO.getRev_num()%>'">
			
<%}else if(re_content != ""){%>
<form>
    <input type="button" value="삭제" onclick="reviewDeletePoint();">
</form>
		<input type="button" value="수정" onclick="msg1()" style="background: gray;">
<%}}}%>
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
		<img src="upload/<%=reviewDTO.getRev_img_url() %>" alt="이미지">
		</div>
		<!-- 답글  -->	
		<div class="re-review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->
		<div class="recontent-top">
		<div>
<%
if(re_content != null){ // 내용 null이 아니어야 답글 보여요
%>		
		<h4 class="recoment1">Groom <%=reviewDTO.getS_location() %> <a><%=reviewDTO.getRe_date() %></a></h4>
		<p class="recoment2"><%=reviewDTO.getRe_content() %></p>
<%
}
%>		
		</div>
		<div>
<%
if(id != null){
	if(role.equals("admin")){
%>
<%
if(re_content != null){ // 내용 null이 아니어야 답글 보여요
%>	
			  <input type="button" value="삭제" onclick="really2('<%=reviewDTO.getRev_num()%>')">	
			  <input type="button" value="수정" onclick="location.href='reUpdate.re?rev_num=<%=reviewDTO.getRev_num() %>'">
<%} else if(re_content == null) {%>
	<input type="button" value="답글작성" onclick="location.href='reWrite.re?rev_num=<%=reviewDTO.getRev_num() %>'">
<%}}}%>
		</div>	
		</div>
		</div>
		<!-- 답글  -->
		</div>
		<br><br>
<!-- 		테스트 공간 -->


<!-- 내용 끝  -->
</div>
</div>
</div>


<script>
function msg1() { // [리뷰수정](불가)
    alert("포인트가 지급된 리뷰는 수정이 불가능합니다.");
  }
  
function really1(rev_num) { // [리뷰삭제] (답글X), (관리자)
    var result = confirm("정말로 삭제할까요?");
    if (result) {
        location.href = 'reviewDelete.re?rev_num=' + rev_num;
    }
}
function msg2(rev_num) { // [리뷰삭제] (답글O)
	var msg = "정말 삭제할까요?\n* 삭제된 리뷰 복구 불가\n* 지급된 포인트는 회수됩니다";
    var result = confirm(msg);
    if(result) {
    	location.href = 'reviewDeletePoint.re?rev_num=' + rev_num;
    }
  }
function really2(rev_num) { // [답글삭제]
    var result = confirm("정말로 삭제할까요?");
    if (result) {
        location.href = 'reDelete.re?rev_num=' + rev_num;
    }
}

function reviewDeletePoint() {
    var msg = "정말 삭제할까요?\n* 삭제된 리뷰 복구 불가\n* 지급된 포인트는 회수됩니다";
    var confirmed = confirm(msg);

    if (confirmed) {
        var rev_num = <%=reviewDTO.getRev_num() %>; // reviewDTO.getRev_num() 값으로 변경
        var url = 'reviewDeletePoint.re?rev_num=' + rev_num;
        window.location.href = url;
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
	</body>
</html>
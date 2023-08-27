<%@page import="web.groom.dto.MemberDTO"%>
<%@page import="java.util.List"%>
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
	
<body>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<%
String id = (String)session.getAttribute("id");
String role = (String)session.getAttribute("role"); 
String num = (String)session.getAttribute("num"); 

if(id == null){
	response.sendRedirect("login.me");
}else{
	if(!(role.equals("admin"))){
		response.sendRedirect("main.gr");
	}
}

List<MemberDTO> memberList =
(List<MemberDTO>)request.getAttribute("memberList");

//아래 코드는 페이징코드
int itemsPerPage = 20; // 페이지당 아이템 수
int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
int startIndex = (currentPage - 1) * itemsPerPage;
int endIndex = Math.min(startIndex + itemsPerPage, memberList.size());
int totalPages = (int) Math.ceil((double) memberList.size() / itemsPerPage);

List<MemberDTO> visibleItems = memberList.subList(startIndex, endIndex);
%>
<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title">
		관리자 페이지</h2>
		
		<div class="row row-bottom-padded-md">
<!-- 관리자 메뉴 -->
		<h3 class="admin-select">
		<a href="admin_main.ad">관리자메인</a>
		<a href="admin_userCheck.ad" class="admin-button-active">회원정보</a>
		<a href="admin_resCheck.ad">예약정보</a>
		<a href="admin_storeCheck.ad">지점정보</a></h3>
<!-- [회원정보] 내용 -->
<div class="admin-content">
<table class="admin-userCheck">
    <tr><th colspan="3">회원 정보</th></tr>
    <tr><td>회원번호</td>
    	<td>권한</td>
    	<td>아이디</td>
    	<td>이름</td>
    	<td>전화번호</td>
    	<td>이메일</td>
    	<td>가입일자</td>
    	<td>예약횟수</td>
    	<td>포인트</td></tr>
<%
for(MemberDTO memberDTO : visibleItems) { 
%>
    <tr><td><%=memberDTO.getNum() %></td>
    	<td><%=memberDTO.getRole() %></td>
    	<td><%=memberDTO.getId() %></td>
    	<td><%=memberDTO.getName() %></td>
    	<td><%=memberDTO.getPhone() %></td>
    	<td><%=memberDTO.getEmail() %></td>
    	<td><%=memberDTO.getRegDate() %></td>
    	<td><%=memberDTO.getCount() %></td>
    	<td><%=memberDTO.getPoint() %></td></tr>
<%
}
%>
</table>
<!-- 페이징 코드 5개씩 나눠서 페이징 -->
<div class="userCheck-next" data-animate-effect="fadeInLeft">
    <% if (currentPage > 1) { %>
        <a href="?page=<%= currentPage - 1 %>" class="pgL"><span class="m-tcol-c">&lt;</span></a>
    <% } %>
    <% 
    int startPage = ((currentPage - 1) / 5) * 5 + 1; // 현재 페이지 그룹의 시작 페이지 계산
    int endPage = Math.min(startPage + 4, totalPages); // 현재 페이지 그룹의 마지막 페이지 계산
    for (int i = startPage; i <= endPage; i++) { %>
        <a href="?page=<%= i %>" <%= (i == currentPage) ? "class='review-active'" : "" %>><%= i %></a>
    <% } %>
    <% if (currentPage < totalPages) { %>
        <a href="?page=<%= currentPage + 1 %>" class="pgR"><span class="m-tcol-c">&gt;</span></a>
    <% } %>
</div>
</div>
<!-- [회원정보] 끝 -->
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
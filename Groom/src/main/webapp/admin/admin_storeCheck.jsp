<%@page import="java.text.SimpleDateFormat"%>
<%@page import="web.groom.dto.AdminDTO"%>
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
<style>
.store-closed {
padding: 0;
color: black;
font-size:14px;
text-align: center;
width: 60px;
height: 30px;
}
.emp-closed {
display: flex;
justify-content: center;
align-items: center;

padding: 0;
color: black;
font-size:14px;
text-align: center;
width: 60px;
height: 30px;
}
.emp-next{
width:750px;
}
.closed-td {
width: 40px;
}
</style>

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
List<AdminDTO> empList =
(List<AdminDTO>)request.getAttribute("empList");
//아래 코드는 페이징코드
int itemsPerPage = 30; // 페이지당 아이템 수
int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
int startIndex = (currentPage - 1) * itemsPerPage;
int endIndex = Math.min(startIndex + itemsPerPage, empList.size());
int totalPages = (int) Math.ceil((double) empList.size() / itemsPerPage);

List<AdminDTO> visibleItems = empList.subList(startIndex, endIndex);
%>

<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title">관리자 페이지</h2>
		
		<div class="row row-bottom-padded-md">
<!-- 관리자 메뉴 -->
		<h3 class="admin-select">
		<a href="admin_main.ad">관리자메인</a>
		<a href="admin_userCheck.ad">회원정보</a>
		<a href="admin_resCheck.ad">예약정보</a>
		<a href="admin_storeCheck.ad" class="admin-button-active">지점정보</a></h3>

<div class="admin-content">
<!-- [직원목록] -->
<table class="admin-storeCheck1">
    <tr><th colspan="3">매장정보</th></tr>
    <tr class="font-bold border-bottom"><td>지점명</td><td>주소</td><td>전화번호</td><td class="closed-td">휴무일</td></tr>
    <tr><td>서면점</td><td>부산광역시 부산진구 양정로 42번길 15</td><td>051-1234-5678</td>
    	<td><input type="button" value="선택"
	         onclick="openDisDayDate1(); return false;">
	         </td></tr>
    <tr><td>명지점</td><td>부산광역시 서구 신호로 98번길 7</td><td>051-1234-5678</td>
    	<td><input type="button" value="선택"
	         onclick="openDisDayDate2(); return false;"></td></tr>
    <tr><td>율리점</td><td>부산광역시 동래구 명륜로 13번길 22</td><td>051-1234-5678</td>
    	<td><input type="button" value="선택"
	         onclick="openDisDayDate3(); return false;"></td></tr>
</table>

<table class="admin-storeCheck2">
	<tr><th colspan="7">직원정보</th></tr>
    <tr class="font-bold border-bottom"><td>번호</td>
    	<td>지점명</td>
    	<td>직급</td>
    	<td>직원이름</td>
    	<td>추가요금</td>
    	<td>전화번호</td>
    	<td>이메일</td>
    	<td>입사일</td>
    	<td class="closed-td">휴무일</td>
    	<td class="closed-td">시간</td></tr>
<%
SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
for(AdminDTO adminDTO : visibleItems) {
	String s_location = adminDTO.getS_location();
	String emp_grade = adminDTO.getEmp_grade();
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
    <tr><td><%=adminDTO.getNumber() %></td>
    	<td><%=location %></td>
    	<td><%=grade %></td>
    	<td><%=adminDTO.getEmp_name() %></td>
    	<td><%=adminDTO.getEmp_extrafee() %></td>
    	<td><%=adminDTO.getEmp_phone() %></td>
    	<td><%=adminDTO.getEmp_email() %></td>
    	<td><%=format.format(adminDTO.getEmp_date()) %></td>
    	<td style="text-align: center;">
    		<input type="button" value="선택"
	         onclick="openDisDay(<%= adminDTO.getEmp_num()%>,<%= adminDTO.getS_num()%>)">
		</td>
		<td style="text-align: center;">
    		<input type="button" value="선택"
	         onclick="openDisDaytime(<%= adminDTO.getEmp_num()%>,<%= adminDTO.getS_num()%>)">
		</td></tr>
<%
}
%>
</table>
<!-- 페이징 코드 5개씩 나눠서 페이징 -->
<div class="userCheck-next emp-next" data-animate-effect="fadeInLeft">
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
<!-- [직원목록] 끝 -->
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
<script type="text/javascript">
function openDisDayDate1(){
	window.open('storeDisDay.ad?s_num=1', '_blank', 'width=360px, height=360px, left=600px, top=300px');
}
function openDisDayDate2(){
	window.open('storeDisDay.ad?s_num=2', '_blank', 'width=360px, height=360px, left=600px, top=300px');
}
function openDisDayDate3(){
	window.open('storeDisDay.ad?s_num=3', '_blank', 'width=360px, height=360px, left=600px, top=300px');
}
function openDisDaytime(emp_num, s_num) {
    var url = 'empDisTime.ad?emp_num='+emp_num+'&s_num='+s_num;
    var newWindow = window.open(url, '_blank', 'width=360px, height=360px, left=600px, top=300px');
}
function openDisDay(emp_num, s_num) {
    var url = 'empDisDay.ad?emp_num='+emp_num+'&s_num='+s_num;
    var newWindow = window.open(url, '_blank', 'width=360px, height=360px, left=600px, top=300px');
}
</script>
</body>
</html>
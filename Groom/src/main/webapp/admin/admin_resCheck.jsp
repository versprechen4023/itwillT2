<%@page import="web.groom.dto.OrderReservationDTO"%>
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
<!-- 	<link href="./css/admin_gr.css" rel="stylesheet" type="text/css">
적용이 안돼서 일단 위에 바로 작성중-->
<!-- 	ㅇ -->
<body>
<%
List<OrderReservationDTO> reservationList =
(List<OrderReservationDTO>)request.getAttribute("reservationList");

//아래 코드는 페이징코드
int itemsPerPage = 20; // 페이지당 아이템 수
int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
int startIndex = (currentPage - 1) * itemsPerPage;
int endIndex = Math.min(startIndex + itemsPerPage, reservationList.size());
int totalPages = (int) Math.ceil((double) reservationList.size() / itemsPerPage);

List<OrderReservationDTO> visibleItems = reservationList.subList(startIndex, endIndex);

%>



<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
<!-- 		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft"> -->
		<h2 class="fh5co-review-title">
		관리자 페이지</h2>
		
		<div class="row row-bottom-padded-md">
<!-- 관리자 메뉴 -->
<!-- 		<h3 class="admin-select animate-box" data-animate-effect="fadeInLeft"> -->
		<h3 class="admin-select">
		<a href="admin_main.ad" class="admin_menu">관리자메인</a>
		<a href="admin_userCheck.ad">회원정보</a>
		<a href="admin_rescheck.ad" class="admin-button-active">예약정보</a>
		<a href="admin_storeCheck.ad">지점정보</a></h3>
<!-- [예약정보] 내용 -->
<div class="admin-content">
<!-- <table class="admin-resCheck animate-box" data-animate-effect="fadeInLeft"> -->
<table class="admin-resCheck">
    <tr><th colspan="3">예약 정보</th></tr>
    <tr class="font-bold"><td>번호</td>
    					  <td>날짜</td>
    					  <td>시간</td>
    					  <td>선택메뉴</td>
    					  <td>매장</td>
    					  <td>담당</td>
    					  <td>예약자</td>
    					  <td>연락처</td>
    					  <td>상품가격</td>
    					  <td>사용한<br>포인트</td>
    					  <td>최종<br>결제금액</td>
    					  <td>상태</td>
    					  <td></td>
    					  <td></td></tr>
<%
for(OrderReservationDTO orderReservationDTO : visibleItems) { 
%>    					  
    <tr><td><%=orderReservationDTO.getRes_num() %></td>
    	<td><%=orderReservationDTO.getRes_day() %></td>
    	<td><%=orderReservationDTO.getRes_time() %></td>
    	<td>[<%=orderReservationDTO.getPro_name() %>]<%=orderReservationDTO.getPet_size() %> <%=orderReservationDTO.getPet_weight() %></td>
    	<td><%=orderReservationDTO.getS_location() %></td>
    	<td><%=orderReservationDTO.getEmp_grade() %> <%=orderReservationDTO.getEmp_name() %></td>
    	<td><%=orderReservationDTO.getU_name() %></td>
    	<td><%=orderReservationDTO.getU_phone() %></td>
    	<td><%=orderReservationDTO.getRes_price()+orderReservationDTO.getRes_point() %></td>
    	<td><%=orderReservationDTO.getRes_point() %></td>
    	<td><%=orderReservationDTO.getRes_price() %></td>
    	<td class="status"><%=orderReservationDTO.getRes_status() %></td>
    	<td><input type="button" value="완료" onclick="statusChange(<%=orderReservationDTO.getRes_num()%>)"></td>
    	<td><input type="button" value="취소"></td></tr>
<%
}
%>    	
</table>
<div class="resCheck-next">
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
<!-- [예약정보] 끝 -->

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
var selected_r = "";

function statusChange(status) {
	alert(status);
	selected_r = status;
	
	$.ajax({
    	type: "GET",
        url: 'statusChange.aj',
        data: {"res_status":selected_r}, // 선택된 값을 서버로 전송
        success: function(result) {
   			  const data = $.trim(result);
   			  if(data=="true"){
   				  alert("저장완료");
   				  location.reload();
   			  }else {
   				  alert("저장실패");
   			  }		
        }
    });////endAJAX(서비스리스트)
}

</script>

</body>
</html>
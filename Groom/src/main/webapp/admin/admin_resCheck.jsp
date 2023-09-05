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
int itemsPerPage = 10; // 페이지당 아이템 수
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
    <tr class="font-bold border-bottom"><td>번호</td>
    					  <td>날짜/시간</td>
    					  <td>선택메뉴</td>
    					  <td>매장</td>
    					  <td>담당</td>
    					  <td>예약자</td>
    					  <td>연락처</td>
    					  <td>상품가격</td>
    					  <td>사용한<br>포인트</td>
    					  <td>최종<br>결제금액</td>
    					  <td>상태</td>
    					  <td style="background: #E2E2E2;">완료</td>
    					  <td style="background: #E2E2E2;">취소</td>
    					  <td style="background: #E2E2E2;">대기</td>
    					  <td colspan="2">포인트</td>
    					  <td>확인</td></tr>
<%
for(OrderReservationDTO orderReservationDTO : visibleItems) { 
String res_day = orderReservationDTO.getRes_day(); // 예약날짜
String format_res_day = res_day.replace("-", ".").substring(2);
String res_time = orderReservationDTO.getRes_time(); // 예약시간
String format_res_time = res_time.substring(0, 5);

//enum > 문자
	String s_location = orderReservationDTO.getS_location();
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
	
	String emp_grade = orderReservationDTO.getEmp_grade();
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
	
	int res_status = orderReservationDTO.getRes_status();
	String status = "";
	if (res_status == 0) {
		status = "△";
	} else if (res_status == 1) {
		status = "○";
	} else if (res_status == 2) {
		status = "✕";
	} else {
		status = "알 수 없음";
	}
	
	int res_point_status = orderReservationDTO.getRes_point_status();
	String point_status = "";
	if (res_point_status == 0) {
		point_status = "미지급";
	} else if (res_point_status == 1) {
		point_status = "완료!";
	} else {
		point_status = "오류";
	}
%>    					  
    <tr><td><%=orderReservationDTO.getRes_num() %></td>
    	<td><%=format_res_day %> <%=format_res_time %></td>
    	<td class="text-left">[<%=orderReservationDTO.getPro_name() %>] <%=orderReservationDTO.getPet_size() %> <%=orderReservationDTO.getPet_weight() %></td>
    	<td><%=location %></td>
    	<td><%=grade %> <%=orderReservationDTO.getEmp_name() %></td>
    	<td><%=orderReservationDTO.getU_name() %></td>
    	<td><%=orderReservationDTO.getU_phone() %></td>
    	<td><%=orderReservationDTO.getRes_price()+orderReservationDTO.getRes_point() %></td>
    	<td><%=orderReservationDTO.getRes_point() %></td>
    	<td style="color: red;"><%=orderReservationDTO.getRes_price() %></td>
    	<td class="status font-bold"><%=status %></td>
    	<td>
    	    <input type="button" value="○" class="status-button"
    	     onclick="confirmStatusComplete(<%=orderReservationDTO.getRes_num()%>)">
        </td>
    	<td>
    	    <input type="button" value="✕" class="status-button"
    		 onclick="confirmStatusCancel(<%=orderReservationDTO.getRes_num()%>)">
    	</td>
    	<td>
    	    <input type="button" value="△" class="status-button"
    		 onclick="confirmStatusUnprocessed(<%=orderReservationDTO.getRes_num()%>)">
    	</td>
    	<td>
    	<%
    	if(res_point_status == 1){
    	%>
    		<input type="button" value="지급" class="disabled-button">
    		
    	<%
    	} else if (res_point_status == 0){
    	%>
    		<input type="button" value="지급" class="point-button"
    		 onclick="confirmPointConfirm(<%=orderReservationDTO.getRes_num()%>)">
    	<%
    	}
    	%>
    	</td>
    	<td>
    	<%
    	if(res_point_status == 1){
    	%>
    		<input type="button" value="회수" class="point-button"
    		 onclick="confirmPointReturn(<%=orderReservationDTO.getRes_num()%>)">
    	<%
    	} else if (res_point_status == 0){
    	%>
    		<input type="button" value="회수" class="disabled-button">
    	<%
    	}
    	%>
		</td>
    	<td><%=point_status %></td>
    	</tr>
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
var selected_a = "";
function confirmStatusComplete(status) {
    selected_a = status;
    if (confirm("[완료]상태로 변경합니다.\n*포인트가 지급됩니다.")) {
        statusComplete(selected_a);
    }
}
function statusComplete(status) {
// 	alert(status);
	selected_a = status;
	$.ajax({
    	type: "GET",
        url: 'statusComplete.aj',
        data: {"res_status":selected_a}, // 선택된 값을 서버로 전송
        success: function(result) {
   			  const data = $.trim(result);
   			  if(data=="true"){
//    				  alert("저장완료");
   				  location.reload();
   			  }else {
   				  alert("저장실패");
   			  }		
        }
	});
}// [예약상태 >> 완료]

var selected_b = "";
function confirmStatusCancel(status) {
    selected_b = status;
    if (confirm("[취소]상태로 변경합니다.\n*포인트가 회수됩니다.")) {
    	statusCancel(selected_b);
    }
}
function statusCancel(status) {
//   	alert(status);
   	selected_b = status;
   	$.ajax({
       	type: "GET",
        url: 'statusCancel.aj',
        data: {"res_status":selected_b}, // 선택된 값을 서버로 전송
        success: function(result) {
      		const data = $.trim(result);
      		if(data=="true"){
// 				alert("저장완료");
      			location.reload();
       		}else {
       			 alert("저장실패");
       		}		
      }
	});
}// [예약상태 >> 취소]

var selected_c = "";
function confirmStatusUnprocessed(status) {
    selected_c = status;
    if (confirm("[예약중]상태로 변경합니다.")) {
        statusUnprocessed(selected_c);
    }
}
function statusUnprocessed(status) {
//   	alert(status);
   	selected_c = status;
   	$.ajax({
       	type: "GET",
        url: 'statusUnprocessed.aj',
        data: {"res_status":selected_c}, // 선택된 값을 서버로 전송
        success: function(result) {
      		const data = $.trim(result);
      		if(data=="true"){
// 				alert("저장완료");
      			location.reload();
       		}else {
       			 alert("저장실패");
       		}		
      }
	});
}// [예약상태 >> 미처리]

var point_a = "";
function confirmPointConfirm(point_status) {
	point_a = point_status;
    if (confirm("포인트를 지급합니다.")) {
    	pointConfirm(point_a);
    }
}
function pointConfirm(point_status) {
//   	alert(point_status);
   	point_a = point_status;
   	$.ajax({
       	type: "GET",
        url: 'pointConfirm.aj',
        data: {"res_num_a":point_a}, // 선택된 값을 서버로 전송
        success: function(result) {
      		const data = $.trim(result);
      		if(data=="true"){
// 				alert("저장완료");
      			location.reload();
       		}else {
       			 alert("저장실패");
       		}		
      }
	});
}// [포인트 지급]

var point_b = "";
function confirmPointReturn(point_status) {
	point_b = point_status;
    if (confirm("포인트를 회수합니다.")) {
    	pointReturn(point_b);
    }
}
function pointReturn(point_status) {
//   	alert(point_status);
   	point_b = point_status;
   	$.ajax({
       	type: "GET",
        url: 'pointReturn.aj',
        data: {"res_num_b":point_b}, // 선택된 값을 서버로 전송
        success: function(result) {
      		const data = $.trim(result);
      		if(data=="true"){
// 				alert("저장완료");
      			location.reload();
       		}else {
       			 alert("저장실패");
       		}		
      }
	});
}// [포인트 회수]
</script>

</body>
</html>
<%@page import="web.groom.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
</head>
<body>
<!-- 	reviewDetails css 추가 -->
	<link rel="stylesheet" href="./css/reviewDetails_gr.css">
	<link rel="stylesheet" href="./css/style.css">
	<link rel="stylesheet" href="./css/aside_gr.css">
	
<!-- 사이드바호출 -->
	<jsp:include page="../inc/aside.jsp"></jsp:include>
	
<%
//세션에서 로그인정보 가져오기?
// String id=(String)session.getAttribute("id");

ReviewDTO reviewDTO=(ReviewDTO)request.getAttribute("reviewDTO");
%>

	<div id="fh5co-page">
	<div id="fh5co-main">
		<h4>리뷰</h4>
	<div class="container">
 	<div class="table-container">
    	<table>
      	<tr><td class="w1"><%=reviewDTO.getU_name() %> / <%=reviewDTO.getRev_date() %> / <%=reviewDTO.getU_count() %>번째 예약 
	    	<button class="wbtn">닫기</button> 
	    	<button class="wbtn">삭제</button>
		    <button class="wbtn">수정</button></td></tr>
		<tr><td class="w2">★★★★★ <%=reviewDTO.getRev_rating() %>.0점	/ <%=reviewDTO.getPro_name() %>  / <%=reviewDTO.getEmp_grade() %> <%=reviewDTO.getEmp_name() %> / <%=reviewDTO.getS_location() %></td></tr>
		<tr><td><img src="<%=reviewDTO.getRev_img_url() %>" alt="펫사진" class="reviewPet"></td></tr>
		<tr><td class="w3"><%=reviewDTO.getRev_content() %></td></tr>    
   		</table>
	</div>
	</div>
	
	<div class="container">
	<div class="table-container">
		<table>
		<tr><td class="w4">Groom 서면점
			<button class="wbtn">삭제</button>
	    	<button class="wbtn">수정</button>
	    	<button class="wbtn">글쓰기</button></td></tr>
		<tr><td class="w5">답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글답글</td></tr>
		</table>
	</div>
	</div>
	
	</div>
	</div>
</body>
</html>
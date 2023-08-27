<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	</head>
	
	<!-- 	추가한거!!! -->
	<link rel="stylesheet" href="./css/qna_gr.css">
	
	<!-- 사이드바호출 -->
	<jsp:include page="../inc/aside.jsp"></jsp:include>
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
	

	
<!-- 여기부터 -->
	<div id="fh5co-main">	
<head>
<title>질문게시판</title>
</head>
<body>
<h2>Q&A</h2>
<table id="qtable1">
<tr id="qtr">
<th id="lnum">글번호</th>
<th id="lsub">제목</th>
<th id="lname">작성자</th>
<th id="ldate">작성일</th>
<th id="lre">답글</th>
</tr>

</table>
<table id="qtable2">
<tr><td>

<div class="pagination">
	 <a href="#">1</a>
	 <a href="#">2</a>
	 <a href="#">3</a>
	 <a href="#">4</a>
	 <a href="#">5</a>
	</div>
</td></tr>
	
	<tr><td>	
	<div class="search-form">
		<form action="./noticeList.no" method="get">
		 <div class="combo-box">
				<select id="combo-select">
					<option value="option1">이용문의</option>
					<option value="option2">서비스</option>
					<option value="option3">결제</option>
					<option value="option4">기타</option>
				</select>					
			</div>		 	
		 <input type="text" name="keyWord" size=30 placeholder="검색어를 입력하세요" id="searchkey">
		 <input type="submit" value="검색" id="searchbtn">
		 <input type="button" value="글쓰기" onclick="글쓰는페이지로" id="writebtn">
		 <input type="button" value="답글X" onclick="답글작성안한 글만 출력" id="rebtn">
		 </form>
	</div>
	</td></tr>
	
	</table>
	</body>
</html>



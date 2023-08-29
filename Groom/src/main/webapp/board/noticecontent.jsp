<%@page import="web.groom.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/head.jsp"></jsp:include>


</head>

<jsp:include page="../inc/aside.jsp"></jsp:include>

<link rel="stylesheet" href="./css/noticecontent_gr.css">

<body>
<%

String id=(String)session.getAttribute("id");
// QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO"); 자기가 만든 DTO에 맞게 바꾸셔야 합니다. 

%>

	<div id="fh5co-main">

<h1 class="headh1">공지사항</h1>
<hr>
<table id="notice" border="1">
<tr><td class="qnawriter">글쓴이</td><td class="vwriter">boardDTO.getName()  </td></tr>
<tr><td class="qnatitle">글제목</td><td class="vtitle">boardDTO.getSubject() </td></tr>
<tr><td class="qnacategory">분류</td><td class="vwriter">boardDTO.getCategory()  </td></tr>
<tr><td class="qnacontent">내용</td><td class="vcontent">boardDTO.getContent() </td></tr>


</table>

<div class="btn"> 
   <button type="button" value="삭제" class="deletebtn" onclick="location.href='qnadelete.bo'"> 삭제</button>
   <button type="button" value="수정" class="modifybtn" onclick="location.href='qnamodify.bo'"> 수정 </button>
 

 <button type="button" value="목록" class="listbtn" onclick="location.href='notice.bo'"> 목록 </button>
 
 
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
	
</body>
</html>
<%@page import="web.groom.dto.MemberDTO"%>
<%@page import="web.groom.dto.Board1DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>

</head>

<!-- 사이드바호출 -->
<jsp:include page="../inc/aside.jsp"></jsp:include>

<link rel="stylesheet" href="./css/qnacontent_gr.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/aside_gr.css">

<body>
<%

String id=(String)session.getAttribute("id");
String role=(String)session.getAttribute("role");

// QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO"); 자기가 만든 DTO에 맞게 바꾸셔야 합니다. 
Board1DTO boardDTO = (Board1DTO)request.getAttribute("boardDTO");
//---
MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
%>

	<div id="fh5co-main">

<h1 class="headh1">공지사항</h1>
<hr>
<input type="hidden" name="n_num" value="<%=boardDTO.getN_num() %>">
<table id="notice" border="1">
<!-- <tr><td class="qnawriter">글쓴이</td><td class="vwriter">  </td></tr> -->
<tr><td class="qnatitle">글제목</td><td class="vtitle"><%=boardDTO.getN_title() %> </td></tr>
<!-- <tr><td class="qnacategory">분류</td><td class="vwriter">boardDTO.getCategory()  </td></tr> -->
<tr><td class="qnacontent">내용</td><td class="vcontent">
	<img src="upload/<%=boardDTO.getN_img_url() %>" alt="이미지">
	<br><br>
	<%=boardDTO.getN_content() %> </td></tr>


</table>

<div class="btn"> 
<%
if(id != null){
	if(role.equals("admin")){
		%>
   <button type="button" value="삭제" class="deletebtn" 
	onclick="location.href='noticeDelete.bo?n_num=<%=boardDTO.getN_num()%>'"> 삭제</button>
   <button type="button" value="수정" class="modifybtn" 
   	onclick="location.href='noticeUpdate.bo?n_num=<%=boardDTO.getN_num()%>'"> 수정 </button>
 <%
	}
}
%>

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
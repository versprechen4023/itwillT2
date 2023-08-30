<%@page import="web.groom.dto.Board1DTO"%>
<%@page import="web.groom.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/head.jsp"></jsp:include>

<style>




</style>
</head>

<jsp:include page="../inc/aside.jsp"></jsp:include>
<link rel="stylesheet" href="./css/qnacontent_gr.css">
<body>
<%

String id=(String)session.getAttribute("id");
String role=(String)session.getAttribute("role");

Board1DTO boardDTO = (Board1DTO)request.getAttribute("boardDTO");
%>

	<div id="fh5co-main">

<h1 class="headh1">자주 묻는 질문 </h1>
<hr>
<input type="hidden" name="faq_num" value="<%=boardDTO.getFaq_num() %>">
<table id="notice" border="1">
<!-- <tr><td class="qnawriter">글쓴이</td><td class="vwriter">boardDTO.getName()  </td></tr>  우리가 예시로 들꺼기 때문에 아무렇게나 -->
<tr><td class="qnatitle">글제목</td><td class="vtitle" >boardDTO.getSubject() </td></tr>  <!--  단 제목과 내용은 어디서 참고해서 넣을것 -->
<!-- <tr><td class="qnacategory">분류</td><td class="vwriter"> boardDTO.getCategory()  </td></tr>  받아오는게 아니라 우리가 입력 -->
<tr><td class="qnacontent">내용</td><td class="vcontent">
	<img src="upload/<%=boardDTO.getFaq_img_url() %>" alt="이미지">
	<br><br>
	<%=boardDTO.getFaq_content() %> </td></tr>

</table>

<div class="btn"> 

<%
if(id != null){
	if(role.equals("admin")){
		%>
   <button type="button" value="삭제" class="deletebtn" 
	onclick="location.href='faqDelete.bo?faq_num=<%=boardDTO.getFaq_num() %>'"> 삭제</button>
   <button type="button" value="수정" class="modifybtn" 
   	onclick="location.href='faqUpdate.bo?faq_num=<%=boardDTO.getFaq_num() %>'"> 수정 </button>
 <%
	}
}
%>

 <button type="button" value="목록" class="listbtn" onclick="location.href='faq.bo'"> 목록 </button>
 
 
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
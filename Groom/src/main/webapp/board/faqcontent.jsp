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
QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO");

%>

	<div id="fh5co-main">

<h1 class="headh1">자주 묻는 질문 </h1>
<hr>
<table id="notice" border="1">
<tr><td class="qnawriter">글쓴이</td><td class="vwriter">boardDTO.getName()  </td></tr> <!--  우리가 예시로 들꺼기 때문에 아무렇게나 -->
<tr><td class="qnatitle">글제목</td><td class="vtitle" >boardDTO.getSubject() </td></tr>  <!--  단 제목과 내용은 어디서 참고해서 넣을것 -->
<tr><td class="qnacategory">분류</td><td class="vwriter"> boardDTO.getCategory()  </td></tr> <!--  받아오는게 아니라 우리가 입력 -->
<tr><td class="qnacontent">내용</td><td class="vcontent">boardDTO.getContent() </td></tr>

</table>

<div class="btn"> 

		
   <button type="button" value="삭제" class="deletebtn" onclick="location.href='qnadelete.bo?num='"> 삭제</button> <!--  꺽쇄해서 입력 -->
   <button type="button" value="수정" class="modifybtn" onclick="location.href='qnamodify.bo?num='"> 수정 </button><!--  꺽쇄해서 입력 -->
 

 <button type="button" value="목록" class="listbtn" onclick="location.href='qna.bo'"> 목록 </button>
 
 
   </div>
  <!--  일반 사용자  -->
   
   <h1 class="ansheadh1">답변</h1>
<hr>
<table id="notice" border="1">
<tr><td class="qnawriter">글쓴이</td><td class="vwriter"> 관리자 </td></tr> <!--  관리자로 되어야 함  -->
<tr><td class="ansdate">작성일</td><td class="vansdate"> 작성일 </td></tr>  <!--  작성자가 입력한 날짜를 받아와야함 -->
<tr><td class="qnawriter">분류</td><td class="vwriter"> 이용 문의 </td></tr> <!--  관리자로 되어야 함  -->
<tr><td class="anscontent">내용</td><td class="vcontent"> 내용 </td></tr> <!--  관리지가 답변한 내용이 없으면 ? 없습니다.라고 뜨게해야함 -->
</table>

   </div> 
   
   <!-- 
   
   if(id != null){
	if(id.equals(boardDTO.getName())){
		%>
<input type="button" value="글수정" onclick="location.href='update.bo?num=<%-- <%=boardDTO.getNum()%>'">
<%-- <input type="button" value="글삭제" onclick="location.href='delete.bo?num=<%=boardDTO.getNum()%>'">		--%>
		
	}
}  -->






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
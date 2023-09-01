<%@page import="web.groom.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/head.jsp"></jsp:include>


</head>

<jsp:include page="../inc/aside.jsp"></jsp:include>
<link rel="stylesheet" href="./css/qnacontent_gr.css">
<body>
<%

String id=(String)session.getAttribute("id");
QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO");
String role=(String)session.getAttribute("role");

%>

	<div id="fh5co-main">

<h1 class="headh1">Q&A</h1>
<hr>
<table id="notice" border="1">

<tr><td class="qnawriter">글쓴이</td><td class="vwriter"><%=qnaDTO.getId() %></td></tr>
<tr><td class="qnatitle">글제목</td><td class="vtitle"><%=qnaDTO.getTitle() %></td></tr>
<tr><td class="qnacategory">분류</td><td class="vwriter"><%=qnaDTO.getCategory() %></td></tr>
<tr><td class="qnacontent">내용</td><td class="vcontent">
    <img src="upload/<%=qnaDTO.getQnaimgurl() %>" alt="이미지">
    <br><br>
    <%=qnaDTO.getContent() %> </td></tr>

<%-- <%-- <tr><td>첨부파일</td><td><!--  <a href="uploadPath/<%=boardDTO.getFile() %>"  download ></a>  <%=boardDTO.getFile() %> --%>
<%--       										<img src="uploadPath/<%=boardDTO.getFile() %> " width="200" height="200"> --> --%>
<!--                                           </td></tr>     -->
<%-- <tr><td>내용</td><td><!--  <%=boardDTO.getContent() %> --></td></tr>     --%>

</table>

<div class="btn"> 
<%
if(id != null){
	if(id.equals(qnaDTO.getId())){
	 %>
		
   <button type="button" value="삭제" class="deletebtn" onclick="location.href='qnaDelete.bo?qna_num=<%=qnaDTO.getQnanum()%>'"> 삭제</button>
   <button type="button" value="수정" class="modifybtn" onclick="location.href='qnaUpdate.bo?qna_num=<%=qnaDTO.getQnanum()%>'"> 수정 </button>
 
<%
 	   }
 	}  
%>

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

<div class="btn"> 
<%-- <%  --%>
<!--   if(id != null){ -->
<!--  if(id.equals(boardDTO.getName())){ -->	
<%-- 		%> --%>


								<%
							if (id != null) {
								if (role.equals("admin")) {
							%>
							
						
							
							
<%-- 	   <button type="button" value="삭제" class="deletebtn" onclick="really1('<%=qnaDTO.getQnanum()%>')"> 삭제</button> --%>
	   <button type="button" value="삭제" class="deletebtn" onclick="location.href='reDelete.bo?qna_num=<%=qnaDTO.getQreref()%>'"> 삭제</button>
       <button type="button" value="수정" class="modifybtn" onclick="location.href='qnaUpdate.bo'"> 수정 </button>
       <button type="button" value="확인" class="listbtn" onclick="location.href='qna.bo'"> 확인 </button>
							<%
							}}
							%>




<!-- 		                <input type="button" value="수정" class="deletebtn" -->
<%-- 			                 onclick="location.href='qnaUpdate.bo?qna_num=<%=qnaDTO.getQnanum()%>'"> --%>
			                 
<!-- 			                <input type="button" value="삭제" class="modifybtn" -->
<%-- 			                 onclick="really1('<%=qnaDTO.getQnanum()%>')"> --%>
			                 
<!-- 			                <input type="button" value="목록" class="listbtn" -->
<!-- 			                 onclick="location.href='qna.bo'"> -->
			                 
		

 
<%--   <%  	 --%>
<!-- 	   }  --> 
<!-- 	}  --> 
<%-- %> --%>

 
 
   </div>

   </div> 
   
   <!-- 
   
   if(id != null){
	if(id.equals(boardDTO.getName())){
		%>
<input type="button" value="글수정" onclick="location.href='update.bo?num=<%-- <%=boardDTO.getNum()%>'">
<%-- <input type="button" value="글삭제" onclick="location.href='delete.bo?num=<%=boardDTO.getNum()%>'">		--%>
		
	}
}  -->


<script>
function really1(qnanum) { //글삭제
    var result = confirm("정말로 삭제하시겠습니까?");
    if (result) {
        location.href = 'qnaDelete.bo?qna_num=' + qnanum;
    }
}
function really2(qnanum) { //답글삭제
    var result = confirm("정말로 삭제하시겠습니까?");
    if (result) {
        location.href = 'qnaDelete.bo?qna_num=' + qnanum;
    }
}
</script>



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
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="web.groom.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/head.jsp"></jsp:include>

<link rel="stylesheet" href="./css/qnacontent_gr.css">
</head>

<jsp:include page="../inc/aside.jsp"></jsp:include>

<body>
<%

String id=(String)session.getAttribute("id");
QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO");
String role=(String)session.getAttribute("role");
SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");


%>

	<div id="fh5co-main">

<h2 class="headh1">Q&A</h2>
<hr>
<table id="notice" border="1">

<tr><td class="qnawriter">글쓴이</td><td class="vwriter"><%=qnaDTO.getId() %></td></tr>
<tr><td class="qnacategory">분류</td><td class="vwriter"><%=qnaDTO.getCategory() %></td></tr>
<tr><td class="qnatitle">글제목</td><td class="vtitle"><%=qnaDTO.getTitle() %></td></tr>
<tr><td class="qnacategory">작성일</td><td class="vwriter"><%=format.format(qnaDTO.getDate())%></td></tr>
<tr><td class="qnacontent">내용</td><td class="vcontent">
    <%
String imgURL = qnaDTO.getQnaimgurl(); // 이미지 URL 가져오기

String imgTag = (imgURL != null) ? "<img src=\"upload/" + imgURL + "\">" : "";
%>

<%= imgTag %>
    <br> 
    <%=qnaDTO.getContent() %> </td></tr>

</table>

<div class="btn"  style="text-align: left; margin-left: 965px; margin-top: -10px;"> 

		<%
		 if( id != null ) {
			 if(id.equals(qnaDTO.getId())) {
				%>
				  <button type="button" value="삭제" class="deletebtn" onclick="location.href='qnaDelete.bo?qna_num=<%=qnaDTO.getQnanum()%>'"> 삭제</button>
   <button type="button" value="수정" class="modifybtn" onclick="location.href='qnaUpdate.bo?qna_num=<%=qnaDTO.getQnanum()%>'"> 수정 </button>
				<button type="button" value="목록" class="listbtn" onclick="location.href='qna.bo'"> 목록 </button>
				<%  
			 }
		 }
		%>
  
   

 
   </div>
<!--    ---------------------------------------------- -->
<div class="btn"  style="text-align: left; margin-left: 1105px; margin-top: -36px" > 
 		<% 
 		if(id == null || !id.equals(qnaDTO.getId())) {
 			%>
 			<button type="button" value="목록" class="listbtn" onclick="location.href='qna.bo'"> 목록 </button>
			<% 
 		}
 			%>
 			  </div>
<!--    ---------------------------------------------- -->   
   
<%
String content = qnaDTO.getRecontent()==null?"":qnaDTO.getRecontent();
%>
   <%
   if(qnaDTO.getRecontent() != null ){

	   %>
	      <h2 class="ansheadh1">답변</h2>
<hr>

<table id="notice" border="1">
<tr><td class="qnawriter">글쓴이</td><td class="vwriter"> 관리자 </td></tr> <!--  관리자로 되어야 함  -->
<tr><td class="anscontent">내용</td><td class="vcontent" > <%=content %></td></tr> <!--  관리지가 답변한 내용이 없으면 ? 없습니다.라고 뜨게해야함 -->
</table>

<div class="btn" style="text-align: left; margin-left: 990px;margin-top: -10px;" > 
		
          <!--  qna답변  -->
       <button type="button" value="답변" class="answerbtn" onclick="location.href='qnaRe.bo?qna_num=<%=qnaDTO.getQnanum()%>'"> 답변 및 수정 </button>
       <button type="button" value="목록" class="listbtn" onclick="location.href='qna.bo'"> 목록 </button>
   </div>
	   <%
   } else { 
	   if ( id != null ) {
		if( role.equals("admin")){   
	   
   %>
   <h2 class="ansheadh1">답변</h2>
<hr>
<table id="notice" border="1">
<tr><td class="qnawriter">글쓴이</td><td class="vwriter"> 관리자 </td></tr> <!--  관리자로 되어야 함  -->
<tr><td class="anscontent">내용</td><td class="vcontent" > <%=content %></td></tr> <!--  관리지가 답변한 내용이 없으면 ? 없습니다.라고 뜨게해야함 -->
</table>

<div class="btn" style="text-align: left; margin-left: 990px; margin-top: -10px;" > 
		
          <!--  qna답변  -->
       <button type="button" value="답변" class="answerbtn" onclick="location.href='qnaRe.bo?qna_num=<%=qnaDTO.getQnanum()%>'"> 답변 및 수정 </button>
       <button type="button" value="목록" class="listbtn" onclick="location.href='qna.bo'"> 목록 </button>
   </div>
<%
   }
   }
   }
%>

   </div> 
  


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






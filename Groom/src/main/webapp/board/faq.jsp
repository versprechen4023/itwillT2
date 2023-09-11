<%@page import="java.text.SimpleDateFormat"%>
<%@page import="web.groom.dto.PageDTO"%>
<%@page import="web.groom.dto.Board1DTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
	<head>
	<!-- 헤드호출 -->
		<jsp:include page="../inc/head.jsp"></jsp:include>
	

	
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
	
	<!-- 	 css 추가 -->
	<link rel="stylesheet" href="./css/notice_gr.css">
</head>



<body>	
<div id="fh5co-page">
	<div id="fh5co-main">

<%
String role=(String)session.getAttribute("role");
String id=(String)session.getAttribute("id");

List<Board1DTO> faq=(List<Board1DTO>)request.getAttribute("faq");

PageDTO pageDTO=(PageDTO)request.getAttribute("pageDTO");
%>


<h2 onclick="location.href='faq.bo'">FAQ</h2>
<hr>
	<div>
	 <table id="qtable1">
		 <tr id="qtr">
		 <th id="lnum">글번호</th>
		 <th id="lsub">제목</th>
		 <th id="ldate">작성일</th>
	 </tr>
	 
<%
SimpleDateFormat format =new SimpleDateFormat("yyyy.MM.dd");
    for(int i=0;i<faq.size();i++){
    	Board1DTO boardDTO=faq.get(i);
    	%>
        <tr id="qtr" onclick="location.href='faqContent.bo?faq_num=<%=boardDTO.getFaq_num() %>'">
             <td><%=boardDTO.getFaq_num() %></td>
             <td id="subject"><%=boardDTO.getFaq_title() %></td>
             <td><%=format.format(boardDTO.getFaq_date()) %>	</td>
        </tr>
        <%
    }
    %>   	 
	 </table>
	</div>
	
	<!-- 목록이랑 페이지번호 사이 띄우는? -->
	<div class="clear"></div>
	
<!-- 페이지================================================== -->
<div id="qtable2">
	<div class="pagination">
<%
// 시작페이지 1페이지 Prev 없음
// 시작페이지 11,21,31 Prev 보임
if(pageDTO.getStartPage() > pageDTO.getPageBlock()){
	%>
	<a href="faq.bo?pageNum=<%=pageDTO.getStartPage()-pageDTO.getPageBlock()%>">&lt;</a>
	<%
}
%> 	
	
<%
for(int i=pageDTO.getStartPage();i<=pageDTO.getEndPage();i++){
	%>
	<a href="faq.bo?pageNum=<%=i%>"><%=i %></a> 
	<%
}
%>

<%
//끝페이지번호  전체페이지수 비교 => 전체페이지수 크면 => Next보임
if(pageDTO.getEndPage() < pageDTO.getPageCount()){
	%>
	<a href="faq.bo?pageNum=<%=pageDTO.getStartPage()+pageDTO.getPageBlock()%>">&gt;</a>
	<%
}
%>
</div>
</div>
<!-- ================================================== -->


	<table id="qtable2">
<!-- 	 <div class="pagination"> -->
<!-- 		 <a href="#">1</a> -->
<!-- 		 <a href="#">2</a> -->
<!-- 		 <a href="#">3</a> -->
<!-- 		 <a href="#">4</a> -->
<!-- 		 <a href="#">5</a> -->
<!-- 		</div> -->
<!-- 	 </td></tr> -->
		
		<tr><td>	
		<div class="search-form">
			<form action="faqSearch.bo" method="get">		 	 	
			 <input type="text" name="search" size=60 placeholder="검색어를 입력하세요" id="searchkey">
			 <input type="submit" value="검색" id="searchbtn">
			 </form>
		<%
if(id != null){
	if(role.equals("admin")){
		%>
			    <input type="button" value="글쓰기" onclick="location.href='faqWrite.bo'" id="writebtn">
			</div>
<%
	}
}
%>
		</td></tr>
	
	</table>	
		
</div>
</div>
</body>
</html>


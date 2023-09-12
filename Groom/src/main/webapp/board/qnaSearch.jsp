<%@page import="web.groom.dto.QnaDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="web.groom.dto.PageDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html class="no-js"> 
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	
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
	

<title>질문게시판</title>
</head>

<body>
<%
String role=(String)session.getAttribute("role");
String id=(String)session.getAttribute("id");

List<QnaDTO> qna=(List<QnaDTO>)request.getAttribute("qna");
PageDTO pageDTO=(PageDTO)request.getAttribute("pageDTO");
%>
<div id="fh5co-page">
	<div id="fh5co-main">
	
	
<h2 onclick="location.href='qna.bo'"> Q&A</h2>
<hr>
<table id="qtable1">
<tr id="qtr">
<th id="lnum">글번호</th>
<th id="lsub">제목</th>
<th id="lname">작성자</th>
<th id="ldate">작성일</th>
<th id="lre">답글</th>
</tr>

 <%
 SimpleDateFormat format =new SimpleDateFormat("yyyy.MM.dd HH:mm");
        for(int i=0; i<qna.size(); i++){
        	QnaDTO qnaDTO = qna.get(i);    
        %>
       <tr id="qtr" onclick="location.href='qnaContent.bo?qna_num=<%=qnaDTO.getQnanum()%>'">
            <td><%=qnaDTO.getQnanum() %></td>
            <td id="subject"><%=qnaDTO.getTitle() %></td>
            <td><%=qnaDTO.getId() %>	</td>
            <td><%=format.format(qnaDTO.getDate()) %>	</td>
            <% 
            if(qnaDTO.getQreans()==0){
            %><td id="answer">미답변</td>
            <% }else {%>
            	<td id="answer">답변완료</td><%
            	
            }%>
        </tr>
        <%
        }
        %>
</table>
<!-- 목록이랑 페이지번호 사이 띄우는? -->
<div class="clear"></div>


<!-- 페이지================================================== -->
<table id="qtable2">
<tr><td>
	<div class="pagination">
	
		<%
// 시작페이지 1페이지 Prev 없음
// 시작페이지 11,21,31 Prev 보임
if(pageDTO.getStartPage() > pageDTO.getPageBlock()){
	%>
	<a href="qna.bo?pageNum=<%=pageDTO.getStartPage()-pageDTO.getPageBlock()%>">&lt;</a>
	<%
}
%> 
<!-- 서치 -->
<%
for(int i=pageDTO.getStartPage();i<=pageDTO.getEndPage();i++){
	%>
	<a href="qnaSearch.bo?pageNum=<%=i%>&search=<%=pageDTO.getSearch()%>"><%=i %></a> 
	<%
}
%>

<%
//끝페이지번호  전체페이지수 비교 => 전체페이지수 크면 => Next보임
if(pageDTO.getEndPage() < pageDTO.getPageCount()){
	%>
	<a href="qna.bo?pageNum=<%=pageDTO.getStartPage()+pageDTO.getPageBlock()%>">&gt;</a>
	<%
}
%>
		
	</div>
</td></tr>
	
<!-- ================================================== -->
	
	<table id="qtable2">
	<tr><td>
		
	<div class="search-form">
		<form action="qnaSearch.bo" method="get">
		 <div class="combo-box">
				<select id="combo-select">
					<option value="option1">이용문의</option>
					<option value="option2">서비스</option>
					<option value="option3">결제</option>
					<option value="option4">기타</option>
				</select>					
			</div>		 	
			
			
		 <input type="text" name="search" size=45 placeholder="검색어를 입력하세요" id="searchkey">
		 <input type="submit" value="검색" id="searchbtn">
		 
		 		 		 <%
if(id != null){{
		%>
		<input type="button" value="글쓰기" onclick="location.href='qnaWrite.bo'" id="writebtn">
			  

<%
	}
}
%>
		 
		 
<!-- 		 <input type="button" value="글쓰기" onclick="location.href='qnaWrite.bo'" id="writebtn"> -->
<!-- 		 <input type="button" value="답글X" onclick="location.href='qnaNoanswer.bo'" id="rebtn"> -->


		 </form>
		 
	</td></tr>
	
	</table>
	</body>
</html>




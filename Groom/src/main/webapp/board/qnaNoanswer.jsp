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
	
	
<h2>Q&A</h2>

<table id="qtable1">
<tr id="qtr">
<th id="lnum">글번호</th>
<th id="lsub">제목</th>
<th id="lname">작성자</th>
<th id="ldate">작성일</th>
<th id="lre">답글</th>
</tr>

 <%
 SimpleDateFormat format =new SimpleDateFormat("yyyy.MM.dd");
        for(int i=0; i<qna.size(); i++){
        	QnaDTO qnaDTO = qna.get(i);    
        %>
       <tr id="qtr" onclick="location.href='qnacontent.bo?qna_num=<%=qnaDTO.getQnanum()%>'">
            <td><%=qnaDTO.getQnanum() %></td>
            <td id="subject"><%=qnaDTO.getTitle() %></td>
            <td><%=qnaDTO.getId() %>	</td>
            <td><%=qnaDTO.getDate() %>	</td>
            <% 
            if(qnaDTO.getQreans()==0){
            %><td>X</td>
            <% }else {%>
            	<td>O</td><%
            	
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
	<a href="qna.bo?pageNum=<%=pageDTO.getStartPage()-pageDTO.getPageBlock()%>">Prev</a>
	<%
}
%> 

<%
for(int i=pageDTO.getStartPage();i<=pageDTO.getEndPage();i++){
	%>
	<a href="qna.bo?pageNum=<%=i%>"><%=i %></a> 
	<%
}
%>

<%
//끝페이지번호  전체페이지수 비교 => 전체페이지수 크면 => Next보임
if(pageDTO.getEndPage() < pageDTO.getPageCount()){
	%>
	<a href="qna.bo?pageNum=<%=pageDTO.getStartPage()+pageDTO.getPageBlock()%>">Next</a>
	<%
}
%>
		
	</div>
</td></tr>
	
<!-- ================================================== -->
	
	<table id="qtable2">
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
			
			
		 <input type="text" name="keyWord" size=80 placeholder="검색어를 입력하세요" id="searchkey">
		 <input type="submit" value="검색" id="searchbtn">
<!-- 		 <input type="button" value="글쓰기" onclick="location.href='qnaWrite.bo'" id="writebtn"> -->
		 <input type="button" value="답글X" onclick="location.href='qna.bo'" id="rebtn">


		 </form>
		 
		 <%
if(id != null){
	if(role.equals("admin")){
		%>
		<input type="button" value="글쓰기" onclick="location.href='qnaWrite.bo'" id="writebtn">

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




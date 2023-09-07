<%@page import="web.groom.dto.AdminDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<link rel="stylesheet" href="css/check_disdate_gr.css">
<body>
<%
List<AdminDTO> empDisDaysList =
(List<AdminDTO>)request.getAttribute("empDisDaysList");
//아래 코드는 페이징코드
int itemsPerPage = 10; // 페이지당 아이템 수
int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
int startIndex = (currentPage - 1) * itemsPerPage;
int endIndex = Math.min(startIndex + itemsPerPage, empDisDaysList.size());
int totalPages = (int) Math.ceil((double) empDisDaysList.size() / itemsPerPage);

List<AdminDTO> visibleItems = empDisDaysList.subList(startIndex, endIndex);
%>
<div>
<div class="flex-container">
<p class="left-align">* 직원 휴무 현황</p>
<input type="button" value="닫기" onclick="window.close()" class="right-align">
</div>
<table class="discheck-table">
    <tr class="discheck-head"><td>지점명</td><td>직원이름</td><td>휴무일</td><td>취소</td></tr>
<%
for(AdminDTO adminDTO : visibleItems) {
String dis_day = adminDTO.getDis_day(); //
String format_dis_day = dis_day.replace("-", ".").substring(2);
int dis_day_num = adminDTO.getDis_day_num();
%>
	<tr>
		<td><%=adminDTO.getS_location() %></td>
		<td><%=adminDTO.getEmp_name() %></td>
		<td><%=format_dis_day %></td>
<!-- 		<td class="center-align"><input type="button" value="✕" class="discancle-button"></td> -->
		<td class="center-align">
			<input type="button" value="✕" class="discancle-button"
			 onclick="cancelCheck1('<%=dis_day_num%>')">
		</td>
	</tr>
<%
}
%>
</table>
<div class="disCheck-next">
    <% if (currentPage > 1) { %>
        <a href="?page=<%= currentPage - 1 %>" class="pgL"><span class="m-tcol-c">&lt;</span></a>
    <% } %>
    <% 
    int startPage = ((currentPage - 1) / 5) * 5 + 1; // 현재 페이지 그룹의 시작 페이지 계산
    int endPage = Math.min(startPage + 4, totalPages); // 현재 페이지 그룹의 마지막 페이지 계산
    for (int i = startPage; i <= endPage; i++) { %>
        <a href="?page=<%= i %>" <%= (i == currentPage) ? "class='review-active'" : "" %>><%= i %></a>
    <% } %>
    <% if (currentPage < totalPages) { %>
        <a href="?page=<%= currentPage + 1 %>" class="pgR"><span class="m-tcol-c">&gt;</span></a>
    <% } %>
</div>	
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
function cancelCheck1(dis_day_num) { //
	var result = confirm("삭제합니다.");
	if (result) {
		$.ajax({
	    	type: "GET",
	        url: 'del_EmpDisDays.aj',
	        data: {"dis_day_num":dis_day_num}, // 선택된 값을 서버로 전송
	        success: function(result) {
	   			  const data = $.trim(result);
	   			  if(data=="true"){
// 	    				  alert("저장완료");
	   				  location.reload();
	   			  }else {
	   				  alert("저장실패");
	   			  }		
	        }
		});
// 		location.href = 'del_EmpDisDays.ad?dis_day_num='+dis_day_num;
	}
}
</script>
</body>
</html>
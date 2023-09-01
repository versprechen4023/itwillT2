<%@page import="web.groom.dto.MypageDTO"%>
<%@page import="web.groom.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
MypageDTO mypageInfo = (MypageDTO)request.getAttribute("mypageInfo");
MypageDTO mypagepetInfo = (MypageDTO)request.getAttribute("mypagepetInfo");
List<MypageDTO> mypetList =
(List<MypageDTO>)request.getAttribute("mypetList");

//아래 코드는 페이징코드
int itemsPerPage = 5; // 페이지당 아이템 수
int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
int startIndex = (currentPage - 1) * itemsPerPage;
int endIndex = Math.min(startIndex + itemsPerPage, mypetList.size());
int totalPages = (int) Math.ceil((double) mypetList.size() / itemsPerPage);

List<MypageDTO> visibleItems = mypetList.subList(startIndex, endIndex);
%>


    
<!DOCTYPE html>
<html>
<head>
<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
</head>
<body>
<!-- 	mypage css 추가 -->
	<link rel="stylesheet" href="./css/mypage_gr.css">
	
	<link rel="stylesheet" href="./css/style.css">
	<link rel="stylesheet" href="./css/aside_gr.css">

	
<div id="fh5co-page">
<div id="fh5co-main">
<!-- 사이드바호출 -->
	<jsp:include page="../inc/aside.jsp"></jsp:include>

<h2 class="myh2">내 정보</h2>

<hr>

<div class="container">
  <div class="table-container1">
  
<table class="user">
<p class="h">회원정보</p>
	<tr>
	    <td class="bold-cell">아이디</td>
	    <td><%=mypageInfo.getId()%></td>
	</tr>
	<tr>
	    <td class="bold-cell">이름</td>
	    <td><%=mypageInfo.getName() %></td>
	</tr>
	<tr>
	    <td class="bold-cell">이메일</td>
	    <td><%=mypageInfo.getEmail() %></td>
	</tr>
	<tr>
	    <td class="bold-cell">전화번호</td>
	    <td><%=mypageInfo.getPhone() %></td>
	</tr>
	<tr>
	    <td class="bold-cell">나의 리뷰</td>
	    <td><input type="button" value="리뷰관리" onclick="location.href='myReviewList.re'"></td>
	    
	</tr>
	<tr>
	    <td class="bold-cell">포인트</td>
	    <td><%=mypageInfo.getPoint() %></td>
	</tr>
</table>
<input type="button" value="회원탈퇴" class="mbtn mbtn-left" onclick="location.href='withdraw.my'">
<input type="button" value="비밀번호 변경" class="mbtn mbtn-center" onclick="location.href='resetpassword.my'">
<input type="button" value="정보수정" class="mbtn mbtn-right" onclick="location.href='modifyinfo.my'">


</div>

<div class="table-container2">
<table class="pet">
<p class="h">대표반려동물 정보
	<input type="button" value=" + " class="mbtn" onclick="location.href='insertmypet.my'"></p>
	
<tr>
	    <td class="bold-cell">이름</td>
	    <td><%=mypagepetInfo.getPetName()%></td>
	</tr>
	<tr>
	    <td class="bold-cell">품종</td>
	    <td><%=mypagepetInfo.getPetBreed()%></td>
	</tr>
	<tr>
	    <td class="bold-cell">성별</td>
	    <td><%=mypagepetInfo.getPetGender()%></td>
	</tr>
	<tr>
	    <td class="bold-cell">중성화 여부</td>
	    <td><%=mypagepetInfo.getPetNeuter() %></td>
	</tr>
	<tr>
	    <td class="bold-cell">특이사항</td>
	    <td><%=mypagepetInfo.getPetComment()%></td>
	</tr>

</table>

</div>


<div class="table-container3">
<p class="h">반려동물정보
<table class="petlist">
  
    <tr><td class="bold-cell">이름</td>
    	<td class="bold-cell">품종</td>
    	<td class="bold-cell">성별</td>
    	<td class="bold-cell">중성화여부</td>
    	<td class="bold-cell">특이사항</td>
</tr>

<%
for(MypageDTO mypageDTO : visibleItems) { 
%>
    <tr><td><%=mypageDTO.getPetName() %></td>
    	<td><%=mypageDTO.getPetBreed() %></td>
    	<td><%=mypageDTO.getPetGender() %></td>
    	<td><%=mypageDTO.getPetNeuter() %></td>
    	<td><%=mypageDTO.getPetComment() %></td>
</tr>
<%
}
%>
</table>
<form action="updatemypet.my" id="updatepet" method="post" class="pet-form">
    <!-- 선택 상자와 버튼을 가로로 배치하고, 내용을 수직으로 중앙 정렬 -->
    <div class="selectNbtn">
     내 반려동물: 　
    <select id="petlist" name="petlist">
        <%
        for(MypageDTO mypageDTO : visibleItems) { 
        %>
        <option value="<%=mypageDTO.getPetNum() %>"><%=mypageDTO.getPetName()%></option>
        <%
        }
        %>
    </select>
    <!-- 정보수정 버튼 -->
    <input type="submit" value="정보수정/삭제" class="mbtn">
</div>
</form>
<!-- 페이징 코드 5개씩 나눠서 페이징 -->
<div class="petlist-next" data-animate-effect="fadeInLeft">
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




<div class="table-container4">
<p class="h">예약내역

<table class="reservation">
  
    <tr><td class="bold-cell">예약번호</td>
    	<td class="bold-cell">예약날짜</td>
    	<td class="bold-cell">예약시간</td>
       	<td class="bold-cell">선택메뉴</td>
    	<td class="bold-cell">예약매장</td>
    	<td class="bold-cell">담당직원</td>
    	<td class="bold-cell">예약자</td>
    	<td class="bold-cell">연락처</td>
    	<td class="bold-cell">결제금액</td>
    	<td class="bold-cell">예약상태</td>
    	

</tr>

<!-- 여기 예약내역정보 입력
 지금은 반려동물등록정보리스트 입력해둔 상태 -->
<%
for(MypageDTO mypageDTO : visibleItems) { 
%>
    <tr><td><%=mypageDTO.getPetName() %></td>
    	<td><%=mypageDTO.getPetBreed() %></td>
    	<td><%=mypageDTO.getPetGender() %></td>
    	<td><%=mypageDTO.getPetName() %></td>
    	<td><%=mypageDTO.getPetName() %></td>
    	<td><%=mypageDTO.getPetName() %></td>
    	<td><%=mypageInfo.getName() %></td>
    	<td><%=mypageInfo.getPhone() %></td>
    	<td><%=mypageDTO.getPetName() %></td>
    	<td><%=mypageDTO.getPetName() %></td>
</tr>
<%
}
%>
</table>
<!-- 페이징 코드 5개씩 나눠서 페이징 -->
<div class="petlist-next" data-animate-effect="fadeInLeft">
    <% if (currentPage > 1) { %>
        <a href="?page=<%= currentPage - 1 %>" class="pgL"><span class="m-tcol-c">&lt;</span></a>
    <% } %>
    <% 
    int startPage2 = ((currentPage - 1) / 5) * 5 + 1; // 현재 페이지 그룹의 시작 페이지 계산
    int endPage2 = Math.min(startPage + 4, totalPages); // 현재 페이지 그룹의 마지막 페이지 계산
    for (int i = startPage; i <= endPage; i++) { %>
        <a href="?page=<%= i %>" <%= (i == currentPage) ? "class='review-active'" : "" %>><%= i %></a>
    <% } %>
    <% if (currentPage < totalPages) { %>
        <a href="?page=<%= currentPage + 1 %>" class="pgR"><span class="m-tcol-c">&gt;</span></a>
    <% } %>
</div>
</div>

</div>

</div>
</body>
</html>
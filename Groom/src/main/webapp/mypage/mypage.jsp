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
<h2>내 정보</h2>

<hr>
<div class="container">
  <div class="table-container1">
  
<table class="user">
<p class="h">회원정보</p>
	<tr>
	    <td>아이디</td>
	    <td><%=mypageInfo.getId()%></td>
	</tr>
	<tr>
	    <td>이름</td>
	    <td><%=mypageInfo.getName() %></td>
	</tr>
	<tr>
	    <td>이메일</td>
	    <td><%=mypageInfo.getEmail() %></td>
	</tr>
	<tr>
	    <td>전화번호</td>
	    <td><%=mypageInfo.getPhone() %></td>
	</tr>
	<tr>
	    <td>나의 리뷰</td>
	    <td><input type="button" value="리뷰관리"></td>
	</tr>
	<tr>
	    <td>포인트</td>
	    <td><%=mypageInfo.getPoint() %></td>
	</tr>
</table>
<input type="button" value="정보수정" class="mbtn" onclick="location.href='modifyinfo.me'">
</div>

<div class="table-container2">
<table class="pet">
<p class="h">대표반려동물 정보
	<input type="button" value=" + " class="mbtn" onclick="location.href='insertmypet.my'"></p>

	<!--   <tr>
	  	펫사진
	   <td rowspan="2" id="mpImg"><img src="./images/loc.png" alt="펫사진" ></td>
	    펫정보
	    <td> 콜라 (남 · 비글 · 소/중형) </td>
	  </tr>
	  <tr>
	  펫 주의사항
	    <td>꼬리 쪽을 만지면 예민해져요</td>
	  </tr>
	  
	  <tr>
	  	펫사진
	    <td rowspan="2" id="mpImg"><img src="./images/loc.png" alt="펫사진" ></td>
	    펫정보
	    <td class="petInfo"> 만두 (여 · 말티즈 · 소/중형)</td>
	  </tr>
	  <tr>
	  펫 주의사항
	    <td>뒷다리가 약해요 조심해주세요</td>
	  </tr> 
	  원래 만들어주셨던 반려동물 정보 테이블. 반려동물 정보 등록 페이지에 맞춰 아래와 같이 변경-->
<tr>
	    <td>이름</td>
	    <td><%=mypagepetInfo.getPetName()%></td>
	</tr>
	<tr>
	    <td>품종</td>
	    <td><%=mypagepetInfo.getPetBreed()%></td>
	</tr>
	<tr>
	    <td>성별</td>
	    <td><%=mypagepetInfo.getPetGender()%></td>
	</tr>
	<tr>
	    <td>중성화 여부</td>
	    <td><%=mypagepetInfo.getPetNeuter() %></td>
	</tr>
	<tr>
	    <td>특이사항</td>
	    <td><%=mypagepetInfo.getPetComment()%></td>
	</tr>

</table>
	<select class="form-control" id="storelist" name="storelist">
											<option value="" disabled selected>매장을 선택하세요</option>
											<option value="1">서면점</option>
											<option value="2">명지점</option>
											<option value="3">율하점</option>
										</select>
<input type="button" value="정보수정" class="mbtn" onclick="location.href='updatemypet.my'">
</div>

<div>
<table class="petlist">
<p class="h">반려동물정보
   
    <tr><td>이름</td>
    	<td>품종</td>
    	<td>성별</td>
    	<td>중성화여부</td>
    	<td>특이사항</td>
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
</



<div class="table-container3">
<table class="reservation">
	<p class="h">예약내역 
		<select id="sel">
    		<option value="">이름</option>
   			<option value="1">콜라</option>
   			<option value="2">만두</option>
   		</select>
   	</p>

	<tr>
<!-- 	예약상태 -->
	    <td class="mm">예약</td> 
<!-- 	예약 지점 -->
	    <td class="mm">서면점</td> 
<!-- 	    예약일 -->
	    <td class="mm">2023.7.3(월) / 14:00</td>
<!-- 	    예약한 서비스 -->
	    <td class="mm">미용</td>
<!-- 	    가격 -->
	    <td class="mm">53,000원</td>
	</tr>
</table>
</div>
</div>
</div>

</div>
</body>
</html>
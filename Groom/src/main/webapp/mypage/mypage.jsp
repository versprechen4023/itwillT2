<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	    <td>itwill02</td>
	</tr>
	<tr>
	    <td>이름</td>
	    <td>조현민</td>
	</tr>
	<tr>
	    <td>이메일</td>
	    <td>itwll@naver.com</td>
	</tr>
	<tr>
	    <td>전화번호</td>
	    <td>010-1111-1111</td>
	</tr>
	<tr>
	    <td>나의 리뷰</td>
	    <td><input type="button" value="리뷰관리"></td>
	</tr>
	<tr>
	    <td>포인트</td>
	    <td>120p</td>
	</tr>
</table>
<input type="button" value="정보수정" class="mbtn">
</div>

<div class="table-container2">
<table class="pet">
<p class="h">반려동물 정보
	<input type="button" value=" + " class="mbtn"></p>

	  <tr>
	<!--   	펫사진 -->
	   <td rowspan="2" id="mpImg"><img src="./images/loc.png" alt="펫사진" ></td>
	<!--     펫정보 -->
	    <td> 콜라 (남 · 비글 · 소/중형) </td>
	  </tr>
	  <tr>
	<!--   펫 주의사항 -->
	    <td>꼬리 쪽을 만지면 예민해져요</td>
	  </tr>
	  
	  <tr>
	<!--   	펫사진 -->
	    <td rowspan="2" id="mpImg"><img src="./images/loc.png" alt="펫사진" ></td>
	<!--     펫정보 -->
	    <td class="petInfo"> 만두 (여 · 말티즈 · 소/중형)</td>
	  </tr>
	  <tr>
	<!--   펫 주의사항 -->
	    <td>뒷다리가 약해요 조심해주세요</td>
	  </tr>
</table>
<input type="button" value="정보수정" class="mbtn">
</div>

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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>
</head>
<link rel="stylesheet" href="./css/noticeWrite_gr.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/aside_gr.css">
<body>
<!-- noticeWrite css 추가 -->

<!-- 사이드바호출 -->
<jsp:include page="../inc/aside.jsp"></jsp:include>
<div id="fh5co-page">
<div id="fh5co-main">
<h2>FAQ</h2>
<hr>

<form id="nwf">
	<table>
		<tr><th class="nwth">제목</th></tr>
		<tr><td><input type="text" class="sub" placeholder="제목을 입력해 주세요"></td></tr>
	</table>
	<table>	
		<tr><th class="nwth">내용</th></tr>
		<tr><td><textarea class="cont" placeholder="내용을 입력해 주세요"></textarea></td></tr>
		
		<tr><td>
		<!-- 아이콘(이미지) -->
		<div class="input-group file-upload-container">
	      	<img class="img_img" src="../images/photo.png" 
	      		style="height: 15px; width: auto; margin-right: 5px;">    
	    	<p class="img_text" style="margin: 0;">사진/동영상 추가</p>
	<!-- 		<input id="exampleInputFile"> -->
		</div>		
		</td></tr>
	</table>
	<!-- 	/* 버튼 ================================================================== */	 -->
	<div class="buttons">
		<button src="FAQ목록페이지" class="nwbtn">목록</button>
		<button type="submit" class="nwbtn">확인</button>
		<button class="nwbtn">수정</button>
		<button class="nwbt	n">삭제</button>
	</div>
<!-- 	/* 버튼 ================================================================== */ -->
</form>

</div>
</div>
</body>
</html>

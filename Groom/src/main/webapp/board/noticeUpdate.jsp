<%@page import="web.groom.dto.Board1DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>
</head>
<!-- noticeWrite css 추가 -->
<link rel="stylesheet" href="./css/noticeWrite_gr.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/aside_gr.css">
<body>

<!-- 사이드바호출 -->
<jsp:include page="../inc/aside.jsp"></jsp:include>
<div id="fh5co-page">
<div id="fh5co-main">

<%
String role = (String)session.getAttribute("role");
Board1DTO boardDTO = (Board1DTO)request.getAttribute("boardDTO");
%>
<h2  onclick="location.href='notice.bo'">공지사항 수정</h2>
<hr>

<form id="nwf" method="post" action="noticeUpdatePro.bo" enctype="multipart/form-data">
<input type="hidden" name="n_num" value="<%=boardDTO.getN_num() %>">
	<table>
		<tr><th class="nwth">제목</th></tr>
		<tr><td><input type="text" class="sub"  name="n_title"
			value="<%=boardDTO.getN_title()%>"></td></tr>
	</table>
	<table>	
		<tr><th class="nwth">내용</th></tr>
		<tr><td><textarea class="cont" name="n_content"><%=boardDTO.getN_content()%></textarea></td></tr>
		
		<tr><td>
		<!-- 아이콘(이미지) -->
		<div class="input-group file-upload-container">
	      	<img class="img_img" src="./images/photo.png" 
	      		style="height: 15px; width: auto; margin-right: 5px;"
	      		onclick="triggerFileInput()">   
<!-- 	    	<p class="img_text" style="margin: 0;">사진/동영상 추가</p> -->
				<input type="hidden" name="oldfile" value="<%=boardDTO.getN_img_url() %>">
				<input type="file" id="fileInput" name="n_img_url" accept=".png, .jpg, .jpeg, .gif" style="display: none">
				<div id="fileInfoDisplay">　<%=boardDTO.getN_img_url() %></div>
		</div>			
		</td></tr>
	</table>
	<!-- 	/* 버튼 ================================================================== */	 -->
	<div class="buttons">
<!-- 		<button type="button" onclick="location.href='notice.bo'" id="writebtn" class="nwbtn">목록</button> -->
		<button type="submit" class="nwbtn">수정</button>
<!-- 		<button class="nwbtn">수정</button> -->
<!-- 		<button class="nwbtn">삭제</button> -->
	</div>
<!-- 	/* 버튼 ================================================================== */ -->
</form>



<script type="text/javascript">
//============================ 파일첨부	
function triggerFileInput() { // 이미지 클릭 시 파일 입력(input) 엘리먼트 클릭
	const fileInput = document.getElementById('fileInput');
	fileInput.click(); // 파일 입력 엘리먼트 클릭 이벤트 발생
	}
// 파일 입력(input) 엘리먼트의 값이 변경되었을 때 실행되는 함수
	document.getElementById('fileInput').addEventListener('change', function(event) {
		const selectedFile = event.target.files[0]; // 선택된 파일 가져오기
		const fileInfoDisplay = document.getElementById('fileInfoDisplay');
		if (selectedFile) {
// 선택된 파일이 허용된 확장자를 가지는지 검증
			const allowedExtensions = /(\.png|\.jpg|\.jpeg|\.gif)$/i;
			if (!allowedExtensions.exec(selectedFile.name)) {
				alert('png, jpg, gif 파일만 첨부할 수 있습니다.');
				resetFileInput();
				return;
				}
// 파일명을 표시
			fileInfoDisplay.textContent = selectedFile.name;
			} else {
// 파일 선택이 해제되었을 때
				fileInfoDisplay.textContent = '선택된 파일 없음';
				}
		});
// 파일 입력(input) 엘리먼트 초기화
		function resetFileInput() {
			const fileInput = document.getElementById('fileInput');
			fileInput.value = ''; // 파일 선택 해제
			}

</script>
</div>
</div>
</body>
</html>

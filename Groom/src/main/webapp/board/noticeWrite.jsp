<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

/* 폼전체를 가운데정렬 */
#nwf {
text-align: center;
width: 80%;
}
/* 	각각 요소의 크기 */
.sub, .cont, .file-upload-container {
width: 80%;
padding: 10px;
margin-top: 20px;
}

/* 버튼 ================================================================== */
.buttons {
width: 83%;
/* padding: 10px; */
margin-top: 20px;
position: relative;
overflow: hidden;
display: inline-block;
/* border-radius: 5px; */
/* border: 2px solid black; */
}
/* ====================================================================== */


/* 내용 세부 조절 */
.cont {
height: 300px;
border: 2px solid black;
border-radius: 10px;
resize: none;
}
/* 제목 세부 조절 */
.sub {
border: 2px solid black;
border-radius: 5px;
border-right: 10%;
		margin-top: 50px;
}
/* 버튼 세부 조절 */
.nwbtn {
background-color: black;
color: white;
padding:8px 15px;
border: none;
border-radius: 5px;
/* 파일선택과 버튼을 띄움 */
/* margin-top: 10px; */
		float: right;
/* 		버튼사이 간격 */
		margin-right: 5px;
}
.nwbtnCtrl {
	width: 80%;
	border-right: 10%;
	border: 2px solid black;
/* 	border: none; */
}

/* /* 목록버튼 */ 
/* .nwbtn2 { */
/* 	 background-color: black; */
/* color: white; */
/* padding: 8px 15px; */
/* border: none; */
/* border-radius: 5px; */
/* /* 파일선택과 버튼을 띄움 */ 
/* margin-top: 10px; */
/* 	 display: block; */
/* /* 	 margin-left: 550px; */ 
/* text-align: center; */
/* } */
	
	
.file-upload-container {
position: relative;
overflow: hidden;
display: inline-block;
border-radius: 5px;
border: 2px solid black;
}
.file-upload {
position: absolute;
top: 0;
left: 0;
width: 100%;
height: 100%;
opacity: 0;
}

	
</style>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>
</head>
<body>
<!-- noticeWrite css 추가 -->
<link rel="stylesheet" href="../css/noticeWrite_gr.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/aside_gr.css">
<!-- 사이드바호출 -->
<jsp:include page="../inc/aside.jsp"></jsp:include>
<div id="fh5co-page">
<div id="fh5co-main">
<h2>공지사항</h2>
<hr>

<form id="nwf">
	<input type="text" class="sub" value="제목을 입력해 주세요">
	<textarea class="cont">내용을 입력해 주세요</textarea>
	
	<div class="input-group file-upload-container">
		<label class="file-upload" for="exampleInputFile">파일 선택</label>
		<input type="file" id="exampleInputFile">
	</div>
<!-- 	/* 버튼 ================================================================== */	 -->
	<div class="buttons">
		<button src="공지목록페이지" class="nwbtn">목록</button>
		<button type="submit" class="nwbtn">확인</button>
		<button class="nwbtn">수정</button>
		<button class="nwbtn">삭제</button>
	</div>
<!-- 	/* 버튼 ================================================================== */ -->

	
</form>
</div>
</div>
</body>
</html>

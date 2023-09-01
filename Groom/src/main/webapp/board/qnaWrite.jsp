<%@page import="web.groom.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>

<style>
@font-face {
	font-family: 'Godo';
	font-style: normal;
	font-weight: 400;
	src:
		url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff2')
		format('woff2'),
		url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff')
		format('woff');
}

#fh5co-main {
	position: relative;
	height: 100%;
}

#fh5co-main h2 {
	position: absolute;
	top: 0;
	left: 0;
	margin: 0;
	padding: 40px;
	background-color: #FAF5FA;
	font-family: 'Godo';
	color: black;
	margin-left: 5px;
}

.fh5co-narrow-content {
	background-color: #FAF5FA;
	padding: 20px;
	position: relative; /* Add relative positioning */
}

.abc {
	margin-top: 50px;
	margin-left: 40px;
	color: black;
}

.abc input, .abc textarea {
	margin-bottom: 10px;
	width: 700px;
	font-family: 'Godo';
	font-size : 18px;
}

.abc qnasubject {
	width: 800px;
}

.qnawriter {
	
}

.qnacontent {
	height: 200px;
}

.qnasubject {
	height: 50px;
}

.abc qnaimage {
	width: 50px;
}

.qnasubmit {
	margin: 0 auto;
	display: block;
	text-align: center; /* Center-align the text inside the button */
}

.qna-buttons {
	text-align: center;
	position: absolute;
	bottom: 20px; /* Adjust the bottom position */
	left: 50%; /* Move to the horizontal center */
	transform: translateX(-50%); /* Center horizontally */
}

.qna-buttons {
	text-align: center;
	position: absolute;
	bottom: 20px;
	left: 50%;
	transform: translateX(-50%);
}

.button-container {
	display: inline-block; /* Display the buttons side by side */
	margin-left: 10px; /* Add some space between the buttons */
}

.hrsolid {
	width: 90%;
	border: 0px;
	border-top: 1px solid black;
	margin-top: 60px; /* Add margin to position it below the Q&A section */
	margin-left: 45px;
	
}

.abc p {
	font-size: 20px;
	font-family: 'Godo';
	margin-bottom: 5px; /* Reduce the space below the paragraph */
	margin-top: 20px;
	color: black;
}

.abc input {
	margin-bottom: 10px;
	width: 700px;
	font-family: 'Godo';
	height: 50px;
	border-radius: 5px
}

.abc textarea {
	margin-bottom: 10px;
	width: 700px;
	font-family: 'Godo';
	resize: none;
	border-radius: 5px;
	font-size: 17px;
}

.button-container {
    display: flex;
    justify-content: space-between;
    width: auto;
    margin: 0 auto;
	
}

.review-input-img {
    display: flex;
    text-align: left;
    align-items: center;
    justify-content: left;
    height: 40px;
    border: 1px solid black;
    width: 200px;
    border-radius: 10px;
    width: fit-content;
}
.review-input-img1 {
    height: 30px;
    width: auto;
    margin-left: 10px;

}

#fileInfoDisplay {
	margin-left: 10px;
    color: black;
    font-size: 16px;
    font-family: 'Godo';
    margin-right: 10px;
}



.category {
	display: inline-block;
	max-width: 100%;
	margin-bottom: 5px;
	font-weight: bold;
	font-size: 20px;
	font-family: 'Godo';
	width: 55px;
}

#qna_category {
	height: 35px;
	width: 85px;
	text-align: center;
	font-family: 'Godo';
	border: 1px solid black;
	border-radius: 20px;
    color: black;
}

.file-info-container {
	display: flex;
	align-items: center; /* Center vertically */
}



.button-container {
	display: flex; /* Display the buttons side by side */
	justify-content: space-between; /* Add space between the buttons */
	margin-left: 10px;
	/* Add some space between the buttons and other content */
	width: 250px; /* Adjust the width as needed */
}

.qnasubmit, .qnalist {
    width: fit-content;
    letter-spacing: 2px;
    font-size: 18px;
    font-family: 'Godo';
    color: white;
    background-color: black;
}
}
</style>
</head>
<body>
	<%
	// 로그인한 상태에서 글을 써야하기 때문에 세션에서 로그인정보 가져오기
	String id = (String) session.getAttribute("id");
	%>

	<jsp:include page="../inc/aside.jsp"></jsp:include>

	<div id="fh5co-main">

		<div class="fh5co-narrow-content">
			<h2 class="h2">Q&A</h2>

			<form action="qnaWritePro.bo" method="post" class="qnawrite" enctype="multipart/form-data">
<!--                 <form id="nwf" method="post" class="qnawrite" action="qnaWritePro.bo" enctype="multipart/form-data"> -->
				<hr class="hrsolid">
				<div class="abc">
					<!--  name으로 값을 넘겨주기 때문에 서비스단에 form 안에 name과 String (name) = request.getparmeter("(name)"); 가 일치해야함 -->
					<input type="hidden" name="u_id" value="<%=id%>" id="u_id">

					<div>
						<label class="category"> 분류: </label> <select name="qna_category"
							id="qna_category">
							<option value="선택" disabled>분류</option>
							<option value="A">서비스</option>
							<option value="B">결제</option>
							<option value="C">이용문의</option>
							<option value="D">기타</option>

						</select>

					</div>
					<!--  category -->

					<div>
						<p class="p">글 제목</p>
						<input type="text" class="qna_title" placeholder="제목을 입력해주세요."
							name="qna_title" maxlength="50">
					</div>

					<div>
						<p>글 내용</p>
						<textarea cols="108" rows="10" placeholder="Input some text."
							name="qna_content"></textarea>
					</div>

					<div>
					<p>첨부 파일</p>
						<div class="review-input-img">
							<img src="./images/photo.png" class="review-input-img1"
								onclick="triggerFileInput()"> <input type="file"
								id="fileInput" name="qna_img_url"
								accept=".png, .jpg, .jpeg, .gif" style="display: none">
							<div id="fileInfoDisplay">선택된 파일 없음</div>

						</div>
					</div>

				</div>



				<div class="qna-buttons">
					<div class="button-container">
						<input type="submit" value="확인" class="qnasubmit" >
						<button onclick="location.href='qna.bo'" class="qnalist">목록</button>
					</div>
				</div>

			</form>

		</div>
	</div>
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
	
	<script>
// ============================ 파일첨부	
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
	
</body>
</html>
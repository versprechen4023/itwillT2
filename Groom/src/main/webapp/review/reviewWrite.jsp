<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>
</head>

<!-- Theme style  -->
<link rel="stylesheet" href="./css/member_gr.css">
<!-- 리뷰작성페이지 스타일  -->
<link rel="stylesheet" href="./css/reviewWrite_gr.css">

<body>
<form action="reviewWritePro.re" method="post" enctype="multipart/form/data">
	<div class="review-write-main">
	<div style="text-align: center;">
		<h1 class="logo"> Groom(로고) </h1>
	<div><p class="title">리뷰 작성</p></div><br>
	<div class="divider"></div>
<!-- 별점 -->
	<div>
	<p>서비스는 만족하셨나요?</p><br><br>
	<div class="review-star">
    	<div class="star empty-star" data-rating="1"></div>
    	<div class="star empty-star" data-rating="2"></div>
    	<div class="star empty-star" data-rating="3"></div>
    	<div class="star empty-star" data-rating="4"></div>
    	<div class="star empty-star" data-rating="5"></div>
	</div>
	</div>
	<input type="text" name="rev_rating" class="star-rating" name="rev_rating" readonly>
<!-- 리뷰 내용 작성 -->
	<br><br><br><br>
	<div><p>솔직한 서비스의 리뷰를 남겨주세요.</p></div><br>
	<div>
		<textarea name="rev_content" id="review-message" cols="30" rows="7" class="form-control" placeholder="내용을 입력해주세요"></textarea>
	</div><br>
<!-- 이미지 파일 첨부 -->
	<div class="review-input-img">
		<img src="./images/photo.png" class="review-input-img1" onclick="triggerFileInput()">
		<input type="file" id="fileInput" name="rev_img_url" accept=".png, .jpg, .jpeg, .gif" style="display: none">
		<div id="fileInfoDisplay">　선택된 파일 없음</div>
	</div><br>
<!-- 포인트 안내 -->
	<div><p>* 리뷰 작성 시 n포인트 적립</p></div><br>
	<div class="buttons">
<!-- ///////////// 취소 일단 reviewList로 이동 나중에 변경하기 ///////////////////////////////////// -->
		<input type="button" class="cancle" value="취소" onClick="location.href='reviewList.re'"> 
		<input type="submit" class="submit" value="등록">
	</div>
	</div>
	</div>
<br><br><br>
</form>

<script>
// ============================ 별점부분
const stars = document.querySelectorAll('.star');
const revRatingInput = document.querySelector('input[name="rev_rating"]');
	stars.forEach((star, index) => {
	star.addEventListener('click', () => {
		const rating = index + 1;
		for (let i = 0; i < stars.length; i++) {
			if (i < rating) {
				stars[i].classList.add('filled-star');
				stars[i].classList.remove('empty-star');
				} else {
					stars[i].classList.remove('filled-star');
					stars[i].classList.add('empty-star');
					}
			}
		revRatingInput.value = rating; // Set the input value
		});
	});	
	
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
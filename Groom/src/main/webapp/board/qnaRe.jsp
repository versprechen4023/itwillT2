<%@page import="web.groom.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>

<link rel="stylesheet" href="./css/qnaWrite_gr.css">
</head>
<body>
	<%
	// 로그인한 상태에서 글을 써야하기 때문에 세션에서 로그인정보 가져오기
	String id = (String) session.getAttribute("id");
	String role = (String)session.getAttribute("role");
	String num = (String)session.getAttribute("num"); 
	QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO");
	%>

	<jsp:include page="../inc/aside.jsp"></jsp:include>
<div id="fh5co-page">
	<div id="fh5co-main">

<!-- 		<div class="fh5co-narrow-content"> -->
			<h2 onclick="location.href='qna.bo'">Q&A</h2>
		<hr>
			<form action="qnaReWritePro.bo" method="post"  id="nwf" enctype="multipart/form-data">
<!--                 <form id="nwf" method="post" class="qnawrite" action="qnaWritePro.bo" enctype="multipart/form-data"> -->
<!-- 				<hr class="hrsolid"> -->
				<div class="abc">
					<!--  name으로 값을 넘겨주기 때문에 서비스단에 form 안에 name과 String (name) = request.getparmeter("(name)"); 가 일치해야함 -->
					<input type="hidden" name="u_id" value="<%=id%>" id="u_id">
					<input type="hidden" name="qna_num" value="<%=qnaDTO.getQnanum()%>" id="qna_num">

<!-- 					<div> -->
<!-- 						<label class="category"> 분류: </label> <select name="qna_category" -->
<!-- 							id="qna_category"> -->
<!-- 							<option value="선택" disabled>분류</option> -->
<!-- 							<option value="A">서비스</option> -->
<!-- 							<option value="B">결제</option> -->
<!-- 							<option value="C">이용문의</option> -->
<!-- 							<option value="D">기타</option> -->

<!-- 						</select> -->

<!-- 					</div> -->
					<!--  category -->

					<div>
						<p>답변</p>
						<%
						if(qnaDTO.getRecontent() != null){
							%>
							<textarea cols="108" rows="10" placeholder="답변을 입력해주세요"
							name="re_content" class="qna_title"><%=qnaDTO.getRecontent() %></textarea>
							<% 
						}else{
							%>
							<textarea cols="108" rows="10" placeholder="답변을 입력해주세요"
							name="re_content" class="qna_title"></textarea>
							<% 
						}
						%>
						
					</div>

<!-- 					<DIV> -->
<!-- 						<DIV CLASS="REVIEW-INPUT-IMG"> -->
<!-- 							<IMG SRC="./IMAGES/PHOTO.PNG" CLASS="REVIEW-INPUT-IMG1" -->
<!-- 							STYLE="HEIGHT: 15PX; WIDTH: AUTO; MARGIN-RIGHT: 5PX;" -->
<!-- 								ONCLICK="TRIGGERFILEINPUT()"> <INPUT TYPE="FILE" -->
<!-- 								ID="FILEINPUT" NAME="QNA_IMG_URL" -->
<!-- 								ACCEPT=".PNG, .JPG, .JPEG, .GIF" STYLE="DISPLAY: NONE"> -->
<!-- 							<DIV ID="FILEINFODISPLAY">선택된 파일 없음</DIV> -->

<!-- 						</DIV> -->
<!-- 					</DIV> -->

				</div>



				<div class="qna-buttons">
<!-- 					<div class="button-container"> -->
						<input type="submit" value="확인" class="qnasubmit" >
<!-- 						<button onclick="location.href='qna.bo'" class="qnalist">목록</button> -->
					</div>
<!-- 				</div> -->

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
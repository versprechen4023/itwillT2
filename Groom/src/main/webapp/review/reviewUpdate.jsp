<%@page import="web.groom.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	</head>
	
<!-- 	리뷰상세 css 추가 -->
	<link rel="stylesheet" href="./css/reviewContent_gr.css">
	
<body>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<form action="reviewUpdatePro.re" method="post" enctype="multipart/form-data">
<%
String id = (String)session.getAttribute("id");
String role = (String)session.getAttribute("role"); 
String num = (String)session.getAttribute("num"); 

// 줄바꿈 자동으로해서 출력해주는 코드 ㄱ
int rev_num = Integer.parseInt(request.getParameter("rev_num"));
ReviewDTO reviewDTO=(ReviewDTO)request.getAttribute("reviewDTO");

// 별점 받아서 별출력하는 코드 ㄱ
//     int rating = reviewDTO.getRev_rating(); // rev_rating 값 int로 바꾸면 수정하도록
int rating = Integer.parseInt(reviewDTO.getRev_rating());
String stars = "";
for (int i = 1; i <= 5; i++) {
	if (i <= rating) {
		stars += "★";
		} else {
			stars += "☆";
			}
	}
%>
	<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft" style="width: 300px;">
		리뷰</h2><br> <!-- fh5co-review-title 클래스 사용중 아님 -->
	<div class="reviewContent-main">
	
<!-- 내용  -->	
		<div class="review-content animate-box" data-animate-effect="fadeInLeft"> <!-- fadeinleft가 왼쪽에서부터 보여지게 -->

		<div class="content-top">
		<div><p class="user-info"><%=reviewDTO.getU_name() %> / <a><%= stars %></a> / <%=reviewDTO.getRev_date() %> / <%=reviewDTO.getU_count() %>번째 방문</p>
			 <p class="product-info"><%=reviewDTO.getPro_name() %> / <%=reviewDTO.getEmp_grade() %> <%=reviewDTO.getEmp_name() %> / <%=reviewDTO.getS_location() %></p></div>
			 
		<div>
<%
if(id != null){
	int u_num = reviewDTO.getU_num() ; // 리뷰의 작성자 번호
	if (num.equals(String.valueOf(u_num))) {
%>		
<%-- 		<input type="button" value="삭제" onclick="really('<%=reviewDTO.getRev_num()%>')"> --%>
		<input type="submit" value="수정완료">	
<%}}%>
		</div>
		</div>
		
		<div class="content-middle">
<%
if (reviewDTO != null) {
	String rev_content = reviewDTO.getRev_content();
	rev_content = rev_content.replace("\n", "<br>"); // 줄바꿈 문자를 <br> 태그로 변환
%>			
		<div>
		<textarea rows="30" cols="10" class="recoment4" name="rev_content"><%=reviewDTO.getRev_content() %></textarea>
		</div>
		
<%
}
%>
		<br>
		<img src="upload/<%=reviewDTO.getRev_img_url() %>" alt="이미지">
		</div>
		
		<!-- 이미지 파일 첨부 -->
	<div class="review-input-img">
		<img src="./images/photo.png" class="review-input-img1" onclick="triggerFileInput()">
		<input type="file" id="fileInput" name="rev_img_url" accept=".png, .jpg, .jpeg, .gif" style="display: none">
		<div id="fileInfoDisplay">　<%=reviewDTO.getRev_img_url() %>
		</div>
	</div><br>
		</div>
		<br><br>
<!-- 내용 끝  -->
	</div>
	</div>
	</div>
<input type="hidden" name="rev_num" value="<%=reviewDTO.getRev_num()%>">
</form>



<script>
// function really(rev_num) {
//     var result = confirm("정말로 삭제하시겠습니까?");
//     if (result) {
//         location.href = 'reviewDelete.re?rev_num=' + rev_num;
//     }
// }
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
	
	<!-- MAIN JS -->
	<script src="./js/main.js"></script>
	
	
	</body>
</html>
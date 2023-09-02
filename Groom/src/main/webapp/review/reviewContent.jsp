<%@page import="java.text.SimpleDateFormat"%>
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
<style>
.review-buttons1 {
	display: flex;
	justify-content: space-between;
}

.buttons1 {
	height: 30px;
}

.buttons2 {
	background: gray;
	height: 30px;
}
</style>
<body>
	<!-- =============================  네비게이션바 ============================= -->
	<jsp:include page="../inc/aside.jsp"></jsp:include>
	<!-- =============================  네비게이션바 ============================= -->
	<%
	String id = (String) session.getAttribute("id");
	String role = (String) session.getAttribute("role");
	String num = (String) session.getAttribute("num");
	SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
	
	// 줄바꿈 자동으로해서 출력해주는 코드 ㄱ
	int rev_num = Integer.parseInt(request.getParameter("rev_num"));
	ReviewDTO reviewDTO = (ReviewDTO) request.getAttribute("reviewDTO");
	
	// enum > 문자
	String s_location = reviewDTO.getS_location();
	String emp_grade = reviewDTO.getEmp_grade();
		String location = "";
	if (s_location.equals("A")) {
	    location = "서면점";
	} else if (s_location.equals("B")) {
	    location = "명지점";
	} else if (s_location.equals("C")) {
	    location = "율하점";
	} else {
	    location = "알 수 없음";
	}
	String grade = "";
	if (emp_grade.equals("A")) {
		grade = "원장";
	} else if (emp_grade.equals("B")) {
		grade = "실장";
	} else if (emp_grade.equals("C")) {
		grade = "수석";
	} else {
		grade = "알 수 없음";
	}
	
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
	<div id="fh5co-main">
		<!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
		<div class="fh5co-narrow-content">
			<h2 class="fh5co-review-title animate-box"
				data-animate-effect="fadeInLeft" style="width: 300px;">리뷰</h2>
			<br>
			<!-- fh5co-review-title 클래스 사용중 아님 -->
			<div class="reviewContent-main">
				<div>
					<!-- 내용  -->
					<div class="review-content animate-box"
						data-animate-effect="fadeInLeft">
						<div class="content-top">
							<div>
								<a style="display: none;"><%=reviewDTO.getRev_num()%></a>
								<p class="user-info"><%=reviewDTO.getU_name()%>
									/ <a><%=stars%></a> /
									<%=format.format(reviewDTO.getRev_date())%>
									/
									<%=reviewDTO.getU_count()%>번째 방문 / 조회수
									<%=reviewDTO.getRev_count()%>
								</p>
								<p class="product-info"><%=reviewDTO.getPro_name()%>
									/
									<%=grade%>
									<%=reviewDTO.getEmp_name()%>
									/
									<%=location%></p>
							</div>
							<div class="review-buttons1">
								<%
								String re_content = reviewDTO.getRe_content();
								if (id != null) {
									int u_num = reviewDTO.getU_num(); // 리뷰의 작성자 번호
									if (num.equals(String.valueOf(u_num))) {
										if (re_content == null) {
								%>
								<input type="button" value="수정" class="buttons1"
									onclick="location.href='reviewUpdate.re?rev_num=<%=reviewDTO.getRev_num()%>'">
								<input type="button" value="삭제" class="buttons1"
									onclick="really1('<%=reviewDTO.getRev_num()%>')">
								<%
								} else if (re_content != "") {
								%>
								<input type="button" value="수정" class="buttons2"
									onclick="msg1()">
								<form>
									<input type="button" value="삭제" class="buttons1"
										onclick="reviewDeletePoint();">
								</form>
								<%
								}
								}
								}
								%>

								<%
								if (id != null) {
									if (role.equals("admin")) {
								%>
								<input type="button" value="(관리자)&#10;삭제"
									onclick="really1('<%=reviewDTO.getRev_num()%>')"
									style="font-size: 5px; padding: 1px; background: maroon;">
								<%
								}
								}
								%>
							</div>
						</div>

						<div class="content-middle">
							<%
							if (reviewDTO != null) {
								String rev_content = reviewDTO.getRev_content();
								rev_content = rev_content.replace("\n", "<br>"); // 줄바꿈 문자를 <br> 태그로 변환
							%>
							<p><%=rev_content%></p>
							<%
							}
							%>
							<br>
							<%
							String rev_img_url = reviewDTO.getRev_img_url();
							if (rev_img_url != null) {
							%>
							<img src="upload/<%=reviewDTO.getRev_img_url()%>" alt="이미지">
							<%
							} else {
							%>
							
							<%
							}
							%>
						</div>
						<!-- 답글있던자리 -->
					</div>
					<!-- 답글 ////////////////////////  -->
					<div class="re-review-content animate-box"
						data-animate-effect="fadeInLeft"
						style="margin-top: 30px; width: 100%">
						<div class="recontent-top">
							<div>
								<%
								if (re_content != null) { // 내용 null이 아니어야 답글 보여요
								%>
								<h4 class="recoment1">
									Groom
									<%=location%>
									<a><%=format.format(reviewDTO.getRe_date())%></a>
								</h4>
								<%
								if (reviewDTO != null) {
									String rev_content = reviewDTO.getRe_content();
									re_content = re_content.replace("\n", "<br>"); // 줄바꿈 문자를 <br> 태그로 변환
								%>
								<p class="recoment2">


									<%=re_content%></p>
								<%
								}
								%>
								<%
								}
								%>
							</div>
							<div>
								<%
								if (id != null) {
									if (role.equals("admin")) {
								%>
								<%
								if (re_content != null) { // 내용 null이 아니어야 답글 보여요
								%>
								<input type="button" value="삭제"
									onclick="really2('<%=reviewDTO.getRev_num()%>')"> <input
									type="button" value="수정"
									onclick="location.href='reUpdate.re?rev_num=<%=reviewDTO.getRev_num()%>'">
								<%
								} else if (re_content == null) {
								%>
								<input type="button" value="답글작성"
									onclick="location.href='reWrite.re?rev_num=<%=reviewDTO.getRev_num()%>'">
								<%
								}
								}
								}
								%>
							</div>
						</div>
					</div>
				</div>
				<!-- 답글 ////////////////////////  -->
				<br> <br>
				<!-- 		테스트 공간 -->

				<!-- 내용 끝  -->
			</div>
		</div>
	</div>

	<script>
		function msg1() { // [리뷰수정](불가)
			alert("포인트가 지급된 리뷰는 수정이 불가능합니다.");
		}
		function really1(rev_num) { // [리뷰삭제] (답글X), (관리자)
			var result = confirm("정말로 삭제할까요?");
			if (result) {
				location.href = 'reviewDelete.re?rev_num=' + rev_num;
			}
		}
		function msg2(rev_num) { // [리뷰삭제] (답글O)
			var msg = "정말 삭제할까요?\n* 삭제된 리뷰 복구 불가\n* 지급된 포인트는 회수됩니다";
			var result = confirm(msg);
			if (result) {
				location.href = 'reviewDeletePoint.re?rev_num=' + rev_num;
			}
		}
		function really2(rev_num) { // [답글삭제]
			var result = confirm("정말로 삭제할까요?");
			if (result) {
				location.href = 'reDelete.re?rev_num=' + rev_num;
			}
		}
		function reviewDeletePoint() {
			var msg = "정말 삭제할까요?\n* 삭제된 리뷰 복구 불가\n* 지급된 포인트는 회수됩니다";
			var confirmed = confirm(msg);
			if (confirmed) {
				var rev_num =
	<%=reviewDTO.getRev_num()%>
		; // reviewDTO.getRev_num() 값으로 변경
				var url = 'reviewDeletePoint.re?rev_num=' + rev_num;
				window.location.href = url;
			}
		}
		// //
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
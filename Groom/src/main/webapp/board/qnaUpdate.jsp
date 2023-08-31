
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
}

.abc input, .abc textarea {
	margin-bottom: 10px;
	width: 700px;
	font-family: 'Godo';
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
}

.abc p {
	font-size: 20px;
	font-family: 'Godo';
	margin-bottom: 5px; /* Reduce the space below the paragraph */
}
</style>
</head>
<body>
	<%
	// 로그인한 상태에서 글을 써야하기 때문에 세션에서 로그인정보 가져오기
	String id = (String) session.getAttribute("id");
	String role = (String)session.getAttribute("role");
	QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO");
	%>

	<jsp:include page="../inc/aside.jsp"></jsp:include>

	<div id="fh5co-main">

		<div class="fh5co-narrow-content">
			<h2 class="h2">Q&A</h2>

			<form action="qnawritePro.bo" method="post" class="qnawrite">
				<hr class="hrsolid">
				<div class="abc">
                        <!--  name으로 값을 넘겨주기 때문에 서비스단에 form 안에 name과 String (name) = request.getparmeter("(name)"); 가 일치해야함 -->
					<input type="hidden" name=" u_id" value="<%=id%>"  id="u_id" >

					<div>
						<p class="p">글 제목</p>
						<input type="text" class="qna_title" placeholder="제목을 입력해주세요." name="qna_title" value="<%=qnaDTO.getTitle() %>">
					</div>

					<div>
						<p>글 내용</p>
						<textarea cols="108" rows="10" placeholder="Input some text." name ="qna_content"><%=qnaDTO.getContent() %></textarea>
					</div>

					<div>
					<p> 첨부 파일 </p>
						<input type="text" class="qnaimage" placeholder="사진을 첨부해주세요" name="qna_img_url"><%=qnaDTO.getQnaimgurl() %>
					</div>

				</div>


				<div class="qna-buttons">
					<input type="submit" value="수정" class="내용을 입력해주세요.">
					<div class="button-container">
						
					</div>
				</div>
			</form>
               <button onclick="location.href='qna.bo'" class="qnalist">목록</button>
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
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

	<head>
<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
	</head>
		
<!-- css -->
	<link rel="stylesheet" href="css/icomoon.css">
	<link rel="stylesheet" href="css/admin_gr.css">
	
<body>
<!-- =============================  네비게이션바 ============================= -->	
<jsp:include page="../inc/aside.jsp"></jsp:include>
<!-- =============================  네비게이션바 ============================= -->
<div id="fh5co-main"> <!-- 블로그 페이지 이미지 테두리? 변경시  stycle.css 481 .blog-entry .blog-img 에서 css 코드 추가 -->
	<div class="fh5co-narrow-content">
		<h2 class="fh5co-review-title animate-box" data-animate-effect="fadeInLeft">
		관리자 페이지</h2>
		<div class="admin_all">
		<div class="row row-bottom-padded-md">
<!-- 관리자 메뉴 -->
		<h3 class="admin-select animate-box" data-animate-effect="fadeInLeft">
		<a href="admin_main.ad">관리자메인</a>
		<a href="admin_userCheck.ad" class="admin-button-active">회원정보</a>
		<a href="admin_resCheck.ad" class="admin-button-active">예약정보</a>
		<a href="admin_storeCheck.ad">지점정보</a></h3>
<!-- [관리자메인] 내용 -->
<div class="admin-content">
<table class="admin-main1 animate-box" data-animate-effect="fadeInLeft">
    <tr><th colspan="3">현재 현황</th></tr>
    <tr><td>총 회원수</td><td>예약건수</td><td>취소건수</td></tr>
    <tr><td>0명</td><td>0건</td><td>0건</td></tr>
    
    <tr><th colspan="3">지점별 현황</th></tr>
    <tr><td>서면점</td><td>명지점</td><td>율하점</td></tr>
    <tr><td>0건</td><td>0건</td><td>0건</td></tr>
</table>
<table class="admin-main2 animate-box" data-animate-effect="fadeInLeft">   
    <tr><th colspan="3">고객센터</th></tr>
    <tr><td>　</td>
    	<td><button class="admin-button">공지사항</button></td>
        <td><button class="admin-button">자주 묻는 질문</button></td>
        <td><button class="admin-button">1:1문의</button></td></tr>
</table>
</div>
<!-- [관리자메인] 끝 -->
<a>======================================= 경계선 =========================================</a>
<!-- [회원정보] 내용 -->
<div class="admin-content">
<table class="admin-userCheck animate-box" data-animate-effect="fadeInLeft">
    <tr><th colspan="3">회원 정보</th></tr>
    <tr><td>회원번호</td><td>아이디</td><td>이름</td><td>전화번호</td><td>이메일</td><td>가입일자</td><td>예약횟수</td><td>포인트</td></tr>
    <tr><td>1</td><td>abc1</td><td>딩딩1</td><td>010-1234-5671</td><td>abc1@groom.com</td><td>23.08.22</td><td>4</td><td>10,000</td></tr>
    <tr><td>2</td><td>abc2</td><td>딩딩2</td><td>010-1234-5672</td><td>abc2@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>3</td><td>abc3</td><td>딩딩3</td><td>010-1234-5673</td><td>abc3@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>4</td><td>abc4</td><td>딩딩4</td><td>010-1234-5674</td><td>abc4@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>5</td><td>abc5</td><td>딩딩5</td><td>010-1234-5675</td><td>abc5@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>6</td><td>abc6</td><td>딩딩6</td><td>010-1234-5676</td><td>abc6@groom.com</td><td>23.08.22</td><td>1</td><td>100</td></tr>
    <tr><td>7</td><td>abc7</td><td>딩딩7</td><td>010-1234-5677</td><td>abc7@groom.com</td><td>23.08.22</td><td>2</td><td>5,000</td></tr>
    <tr><td>8</td><td>abc8</td><td>딩딩8</td><td>010-1234-5678</td><td>abc8@groom.com</td><td>23.08.22</td><td>0</td><td>0</td></tr>
    <tr><td>9</td><td>abc9</td><td>딩딩9</td><td>010-1234-5679</td><td>abc9@groom.com</td><td>23.08.22</td><td>2</td><td>300</td></tr>
    <tr><td>10</td><td>abc10</td><td>딩딩10</td><td>010-1234-5680</td><td>abc10@groom.com</td><td>23.08.22</td><td>4</td><td>10,000</td></tr>
</table>
<div class="userCheck-next animate-box" data-animate-effect="fadeInLeft">
    <a href="#"><span class="m-tcol-c">&lt;</span></a>
    <a href="#">1</a>
    <a href="#">2</a>
    <a href="#">3</a>
    <a href="#">4</a>
    <a href="#">5</a>
    <a href="#"><span class="m-tcol-c">&gt;</span></a>
</div>
</div>
<!-- [회원정보] 끝 -->
<a>======================================= 경계선 =========================================</a>
<!-- [예약정보] 내용 -->
<div class="admin-content">
<table class="admin-resCheck animate-box" data-animate-effect="fadeInLeft">
    <tr><th colspan="3">예약 정보</th></tr>
    <tr><td>예약번호</td><td>날짜</td><td>시간</td><td>선택메뉴</td><td>매장</td><td>담당</td><td>예약자</td><td>연락처</td><td>결제금액</td></tr>
    <tr><td>1001</td><td>23.08.22</td><td>11:00</td><td>[미용]대형견 15kg</td><td>서면점</td><td>원장 딩딩딩</td><td>동동동</td><td>010-1234-5678</td><td>300,000</td></tr>
</table>
<div class="resCheck-next animate-box" data-animate-effect="fadeInLeft">
    <a href="#"><span class="m-tcol-c">&lt;</span></a>
    <a href="#">1</a>
    <a href="#">2</a>
    <a href="#">3</a>
    <a href="#">4</a>
    <a href="#">5</a>
    <a href="#"><span class="m-tcol-c">&gt;</span></a>
</div>
</div>
<!-- [예약정보] 끝 -->
<a>======================================= 경계선 =========================================</a>
<!-- [지점정보] 내용 -->
<div class="admin-content">
<table class="admin-storeCheck1 animate-box" data-animate-effect="fadeInLeft">
    <tr><th colspan="3">지점 정보는 추가할 내용 더 생각하기</th></tr>
    <tr><td>지점명</td><td>주소</td><td>전화번호</td></tr>
    <tr><td>서면점</td><td>서면어딘가</td><td>051-1234-5678</td></tr>
</table>
<table class="admin-storeCheck2 animate-box" data-animate-effect="fadeInLeft">
    <tr><td>직원번호</td><td>직급</td><td>직원이름</td></tr>
    <tr><td>1</td><td>원장</td><td>딩딩딩</td></tr>
</table>
</div>
<!-- [지점정보] 끝 -->
<a>======================================= 경계선 =========================================</a>
<!-- [QnA목록] 내용? -->
<br>
<a>관리자 메인에서 QnA버튼 누르면 QnA로 이동?</a><br>
<a>목록 출력하고 누르면 QnA보는페이지로 이동 → QnA작성버튼 → QnA작성 페이지?</a><br>
<a>여기서 목록 출력하지 말고 QnA 목록 페이지에서만 뽑으면 되지 않을까</a><br>
<a>네비게이션바에 '관리자 페이지로' 버튼을 추가하는게 어떨까?</a><br>
<a>(공지사항, FAQ, QnA 페이지로 이동 후 다시 돌아오기 편하도록)</a><br>
<br>
<!-- [QnA목록] 끝-->
</div>
		</div>
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
	
	<!-- MAIN JS -->
	<script src="./js/main.js"></script>
	</body>
</html>
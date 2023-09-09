<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
   String id = (String)session.getAttribute("id");
   String role = (String)session.getAttribute("role"); 
   String num = (String)session.getAttribute("num"); 
%>
<link rel="stylesheet" href="./css/aside_gr.css">

<aside id="fh5co-aside" role="complementary"
	class="border js-fullheight">
   
    
<!-- 로고 -->
	<h1 id="fh5co-logo"><a href="main.gr">
	<img src="./images/LOGO.png" width="125px" height="80px" href="main.gr"></a></h1>

                 

<%
if(id == null){
%>
	<p id="fh5co-main-login_signup" role="navigation"><a href="login.me">로그인</a>  |  <a href="signup.me">회원가입</a></p>
<%} else {
    %><p id="fh5co-main-login_signup" role="navigation"><a href="logout.me">로그아웃</a>  |  <a href="mypage.my">마이페이지</a></p>
<%  }  %>

<%
if(id != null){
	if(role.equals("admin")){
%>
	<p id="fh5co-main-to_adminpage"><a href="admin_main.ad" class="admin-link">관리자페이지</a></p>
<%}}%>
	  
	<nav id="fh5co-main-menu" role="navigation">
		<ul>
			<li><a href="storeInfo.gr">매장안내</a></li>
			<li><a href="portfolio.gr">이용안내</a></li>
			<li><a href="myorder.or">예약하기</a></li>
			<li><a href="reviewList.re">이용후기</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">게시판 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="notice.bo">공지사항</a></li>
					<li><a href="faq.bo">FAQ</a></li>
					<li><a href="qna.bo">Q&A</a></li>
					<li><a href="changeRes.my">contact</a></li>

				</ul></li>
		</ul>
	</nav>

<div class="fh5co-footer">
    <p>
        <small class="nav-bottom-text">대표이사 : 조현민<br>

        사업자등록번호 : 735-90-25263<br>
        주소: 부산광역시 부산진구 동천로 109<br>

<!--         대표전화 : 051-803-0909<br><br> --><br>
        Copyright &copy;
        <script>document.write(new Date().getFullYear());</script><br>
        All rights reserved Groom<br>

<!--         <a href="about.gr">about</a><br> -->
<!--         <a href="about.html">Copyright © 2023 All rights reserved Groom</a> -->
        </small>
    </p>
    <ul class="icon">
        <li><a href="#"><i class="icon-facebook2" ></i></a></li>
        <li><a href="#"><i class="icon-twitter2"></i></a></li>
        <li><a href="#"><i class="icon-instagram"></i></a></li>
        <li><a href="#"><i class="icon-linkedin2"></i></a></li>
    </ul>
</div>


</aside>
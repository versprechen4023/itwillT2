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
    
	<h1 id="fh5co-logo"><a href="main.gr">Groom </a></h1>
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
	<p id="fh5co-main-to_adminpage"><a href="admin_main.ad">관리자페이지</a></p>
<%}}%>
	  
	<nav id="fh5co-main-menu" role="navigation">
		<ul>
			<li><a href="blog.gr">ABOUT</a></li>
			<li><a href="portfolio.gr">이용안내</a></li>
			<li><a href="myorder.or">예약하기</a></li>
			<li><a href="reviewList.re?pro_name= ">리뷰</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">카테고리 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="notice.bo">공지사항</a></li>
					<li><a href="qna.bo">qna</a></li>
					<li><a href="contact.gr">contact</a></li>
				</ul></li>
		</ul>
	</nav>

<div class="fh5co-footer">
    <p>
        <small class="nav-bottom-text">대표:itwillT2<br>
        사업자등록번호:1234--<br>
        주소:부산광역시 부산진구 부전동<br>
        <br>
        <a href="about.html">이용약관</a><br>
        <a href="about.html">개인정보처리방침</a>
        </small>
    </p>
    <ul>
        <li><a href="#"><i class="icon-facebook2"></i></a></li>
        <li><a href="#"><i class="icon-twitter2"></i></a></li>
        <li><a href="#"><i class="icon-instagram"></i></a></li>
        <li><a href="#"><i class="icon-linkedin2"></i></a></li>
    </ul>
</div>


</aside>
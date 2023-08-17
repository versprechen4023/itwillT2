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
	
<!-- 	login css 추가 -->
	<link rel="stylesheet" href="./css/login_gr.css">
	
	<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<aside id="fh5co-aside" role="complementary" class="border js-fullheight">

			<h1 id="fh5co-logo"><a href="index.html">Groom</a></h1>
			<nav id="fh5co-main-menu" role="navigation">
				<ul>
					<li><a href="index.html">로그인</a>|<a href="index.html">회원가입</a></li>
					<li><a href="blog.html">Blog</a></li>
					<li><a href="portfolio.html">Portfolio</a></li>
					<li><a href="about.html">About</a></li>
					<li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">카테고리
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">공지사항</a></li>
                        <li><a href="#">게시판</a></li>
                    </ul>
                	</li>
				</ul>
			</nav>

			<div class="fh5co-footer">
				<p><small>&copy; <script>document.write(new Date().getFullYear());</script> Blend Free HTML5. All Rights Reserved.</small></p>
				<ul>
					<li><a href="#"><i class="icon-facebook2"></i></a></li>
					<li><a href="#"><i class="icon-twitter2"></i></a></li>
					<li><a href="#"><i class="icon-instagram"></i></a></li>
					<li><a href="#"><i class="icon-linkedin2"></i></a></li>
				</ul>
			</div>

		</aside>

<!-- -->
		<div class="container">
  <div class="welcome">
    <div class="leftbox">

      <div class="signin">
        <h1>GROOM</h1>
        <form class="more-padding" autocomplete="off">
          <input type="text" placeholder="사용자이름">
          <input type="password" placeholder="비밀번호">
          <div class="checkbox">
            <input type="checkbox" id="remember" /><label for="remember">사용자이름 기억하기</label>
          </div>

		<button class="button submit" id="loging">로그인</button>
		<button class="button signup" id="signup">회원가입</button>
        <!-- 로그인버튼과 회원가입 버튼이 수평이 안됨 -->
        
         <div class="forgot">
          <a href="새로운팝업창이나 페이지이동">사용자이름/비밀번호를 잊어버리셨나요?</a>
        </div>
        </form>
        
        
      </div>
    </div>
       <div class="rightbox">
      <h2 class="title"><span>이 달</span>의 행사</h2>
      <p class="desc"> 신규미용고객 <span>20% 할인</span></p>
      <img class="dog" src="images/2.jpg"/>
      <p class="account">행사기간 : 23.08.01 - 23.08.31</p>
      
    </div>
  </div>
 </div>

</div>
		
		
	

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	
	
	<!-- MAIN JS -->
	<script src="js/main.js"></script>

	</body>
</html>


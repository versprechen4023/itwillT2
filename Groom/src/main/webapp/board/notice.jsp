<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
	
	<!-- 	추가한거!!! -->
	<link rel="stylesheet" href="./css/notice.css">
	
	<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<aside id="fh5co-aside" role="complementary" class="border js-fullheight">

			<h1 id="fh5co-logo"><a href="index.html">로고 </a></h1>
			<nav id="fh5co-main-menu" role="navigation">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li class="fh5co-active"><a href="blog.html">Blog</a></li>
					<li><a href="portfolio.html">Portfolio</a></li>
					<li><a href="about.html">About</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul>
			</nav>

			<div class="fh5co-footer">
				<p><small>&copy; 2016 Blend Free HTML5. All Rights Reserved.</span> <span>Designed by <a href="http://freehtml5.co/" target="_blank">FreeHTML5.co</a> </span> <span>Demo Images: <a href="https://unsplash.com/" target="_blank">Unsplash</a></span></small></p>
				<ul>
					<li><a href="#"><i class="icon-facebook2"></i></a></li>
					<li><a href="#"><i class="icon-twitter2"></i></a></li>
					<li><a href="#"><i class="icon-instagram"></i></a></li>
					<li><a href="#"><i class="icon-linkedin2"></i></a></li>
				</ul>
			</div>

		</aside>

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
	
	
	
<!-- 여기부터 -->





	<div id="fh5co-main">
<head>
    <title>공지게시판</title>
    <style>
    @charset "UTF-8";
    	h2{
    		margin-left: 60px;
    		margin-top: 30px;
    		font-weight: bold;
    	}
        table {
            border-collapse: collapse;
            width: 85%;
/*             border: 1px solid #ddd; */
            margin-top: 20px;
            margin-left: 90px;
            background-color: white;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #ddd;
        }

        tr:hover {
            background-color: rgb(241, 241, 241);
        }
        
/*         검색창 */
        #searchkey{
			margin-left: 300px;
			margin-top: 20px;
			padding:6px;
			border: 2px solid #ddd;						
        }
        
/*         버튼 */
        #searchbtn{
        	border: none;
        	background-color: #228896;
        	color:white;
        	border-radius: 5px;
        	padding: 6px 15px;
        	margin-left: 10px;
        }
/*         #writebtn{ */
/*         	border: none; */
/*         	background-color: #228896; */
/*         	color:white; */
/*         	border-radius: 5px; */
/*         	padding: 6px 15px; */
/*         	margin-left: 280px; */
/*         } */

/* 		th간 간격 */
        #lnum{
        	width: 7%;
        }
        #lsub{
        	width: 65%;
        }
        #ldate{
        	width: 20%;
        }
        #lcount{
        	width: 8%;
        }
        #subject{
        	text-align: left;
        }
        
        /*        페이지  */
        .pagination {
		    display: flex;  /* 주축(main axis)과 교차축(cross axis)을 기반으로 유연하게 배치하고 정렬 */
		    justify-content: center; /* 주축을 기준으로 중앙 정렬 */
  			align-items: center; /* 교차축을 기준으로 중앙 정렬 */
		    margin-top: 20px;
		}
	
		.pagination a {
		    color: #228896;
		    padding: 6px 12px;
		    margin: 0 5px;
		    border: 1px solid #228896;
		    text-decoration: none;
		    border-radius: 5px;
		}
	
		.pagination a:hover {
		    background-color: #228896;
		    color: white;
		}
    </style>
</head>
<body>
    <h2>공지사항</h2>
<div>
    <table>
        <tr>
            <th id="lnum">글번호</th>
            <th id="lsub">제목</th>
            <th id="ldate">작성일</th>
            <th id="lcount">조회수</th>
        </tr>
        <tr>
            <td>10</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>9</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>8</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>7</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>6</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>5</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>4</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>3</td>
            <td id="subject"><a href="#">	</a></td>
            <td>	</td>
            <td>	</td>
        </tr>
        <tr>
            <td>2</td>
            <td id="subject"><a href="#">두 번째 게시물</a></td>
            <td>2023-08-09</td>
            <td>50</td>
        </tr>
        <tr>
            <td>1</td>
            <td id="subject"><a href="#">첫 번째 게시물</a></td>
            <td>2023-08-09</td>
            <td>100</td>
        </tr>
       
    </table>
</div> 

		<div class="pagination">
		    <a href="#">1</a>
		    <a href="#">2</a>
		    <a href="#">3</a>
		    <a href="#">4</a>
		    <a href="#">5</a>
		</div>
		
		<div class="search-form">
		    <form action="./noticeList.no" method="get">
		        <input type="text" name="keyWord" size=50 placeholder="검색어를 입력하세요" id="searchkey">
		        <input type="submit" value="검색" id="searchbtn">
<!-- 		    <input type="button" value="글쓰기" onclick="글쓰는페이지로" id="writebtn"> -->
		    </form>
		</div>
</div>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>
</head>
<body>
<!-- 	mypage css 추가 -->
	<link rel="stylesheet" href="./css/notice_gr.css">
	
	<link rel="stylesheet" href="./css/style.css">
	<link rel="stylesheet" href="./css/aside_gr.css">
<!-- 사이드바호출 -->
	<jsp:include page="../inc/aside.jsp"></jsp:include>
	
<div id="fh5co-page">
	<div id="fh5co-main">

<h2>FAQ</h2>
	<div>
	 <table id="qtable1">
	 <tr id="qtr">
	 <th id="lnum">글번호</th>
	 <th id="lsub">제목</th>
	 <th id="ldate">작성일</th>
	 <th id="lcount">조회수</th>
	 </tr>
	 <tr id="qtr">
	 <td>10</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>9</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>8</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>7</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>6</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>5</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>4</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>3</td>
	 <td id="subject"><a href="#">	</a></td>
	 <td>	</td>
	 <td>	</td>
	 </tr>
	 <tr id="qtr">
	 <td>2</td>
	 <td id="subject"><a href="#">두 번째 게시물</a></td>
	 <td>2023-08-09</td>
	 <td>50</td>
	 </tr>
	 <tr id="qtr">
	 <td>1</td>
	 <td id="subject"><a href="#">첫 번째 게시물</a></td>
	 <td>2023-08-09</td>
	 <td>100</td>
	 </tr>
	
	 </table>
	</div>
	<table id="qtable2">
	 <tr><td>
	 <div class="pagination">
		 <a href="#">1</a>
		 <a href="#">2</a>
		 <a href="#">3</a>
		 <a href="#">4</a>
		 <a href="#">5</a>
		</div>
	 </td></tr>
		
		<tr><td>	
		<div class="search-form">
			<form action="qnaList.no" method="get">		 	 	
			 <input type="text" name="keyWord" size=80 placeholder="검색어를 입력하세요" id="searchkey">
			 <input type="submit" value="검색" id="searchbtn">
	 		 <input type="button" value="글쓰기" onclick="location.href='faqWrite.bo'" id="writebtn">
			 </form>
		</div>
		</td></tr>
	
	</table>	
		
</div>
</div>
</body>
</html>


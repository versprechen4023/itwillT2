<%@page import="web.groom.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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

* {
      font-family: 'Godo';
      
}
#fh5co-main {
	position: relative;
	height: 1500px;
}

.headh1{
 margin-left: 97px;
 margin-top : 40px;
 font-family: 'Godo';
}



hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #000;
    width: 80%;
    margin-left: 100px;
    margin-bottom : 50px;
}

table { 
margin-bottom: 100px;
margin-left : 100px;
}

.qnawriter {
text-align: center;
     letter-spacing: 2px;
     width : 40px;
     height: 50px;
     font-size:20px;
     
}

.qnatitle {
    text-align:center;
     letter-spacing: 2px;
     width: 40px;
     height: 50px;
     font-size:20px;
     
     
}

.qnacategory {
    text-align:center;
     letter-spacing: 2px;
     width: 40px;
     height: 50px;
     font-size:20px;
     
     
}

.qnacontent {
text-align: center;
	letter-spacing: 2px;
	width : 150px;
	height : 300px;
	font-size: 20px;
	
	
}

.vtitle {
   width: 6px;
    font-size: 20px;
    padding-left: 10px;

}

.vwriter {
width: 1000px;
    font-size: 20px;
    padding-left: 10px;

}

.vcontent { 

    font-size: 20px;
    padding-left: 10px;


}

.btn {

    margin-right: 4px;
    margin-bottom: 4px;
    font-family: "Roboto", Arial, sans-serif;
    font-size: 20px;
    font-weight: 400;
    text-transform: uppercase;
    letter-spacing: 1px;
    -webkit-border-radius: 0px;
    -moz-border-radius: 0px;
    -ms-border-radius: 0px;
    border-radius: 0px;
    -webkit-transition: 0.5s;
    -o-transition: 0.5s;
    transition: 0.5s;
    padding: 8px 20px !important;
    float: right;
    margin-right:255px;
    font-family: 'Godo' !important;
}

.deletebtn {
    background-color: black; /* 버튼 배경색을 초록색으로 설정 */
    color: white; /* 글자 색상을 하얀색으로 설정 */
    border: none; /* 테두리 없음 */
    padding: 10px 20px; /* 안쪽 여백 설정 */
    font-size: 16px; /* 글자 크기 설정 */
    cursor: pointer; /* 커서 스타일을 포인터로 변경하여 버튼처럼 보이게 함 */
    margin-left : 10px;
    border-radius:10px;
}

/* 수정 버튼 스타일 */
.modifybtn {
    background-color: black; /* 버튼 배경색을 초록색으로 설정 */
    color: white; /* 글자 색상을 하얀색으로 설정 */
    border: none; /* 테두리 없음 */
    padding: 10px 20px; /* 안쪽 여백 설정 */
    font-size: 16px; /* 글자 크기 설정 */
    cursor: pointer; /* 커서 스타일을 포인터로 변경하여 버튼처럼 보이게 함 */
    margin-left : 10px;
    border-radius:10px;
    
}


.listbtn {
    background-color: black; /* 버튼 배경색을 초록색으로 설정 */
    color: white; /* 글자 색상을 하얀색으로 설정 */
    border: none; /* 테두리 없음 */
    padding: 10px 20px; /* 안쪽 여백 설정 */
    font-size: 16px; /* 글자 크기 설정 */
    cursor: pointer; /* 커서 스타일을 포인터로 변경하여 버튼처럼 보이게 함 */
    margin-left : 10px;
    border-radius:10px;

  }
  
  .ansheadh1 {
   
    margin-left: 97px;
 margin-top : 230px;
 font-family: 'Godo';
  
  }
  
  .anscontent { 
    text-align: center;
	letter-spacing: 2px;
	width : 150px;
	height : 300px;
	font-size: 20px;
  
  }
  
  .ansdate {
  
     text-align: center;
     letter-spacing: 2px;
     width : 40px;
     height: 50px;
     font-size:20px;
  }
  
  .vansdate{
    font-size:20px;
    padding-left : 10px;
  
  }
  



</style>
</head>

<jsp:include page="../inc/aside.jsp"></jsp:include>

<body>
<%

String id=(String)session.getAttribute("id");
QnaDTO qnaDTO = (QnaDTO)request.getAttribute("qnaDTO");

%>

	<div id="fh5co-main">

<h1 class="headh1">Q&A</h1>
<hr>
<table id="notice" border="1">

<tr><td class="qnawriter">글쓴이</td><td class="vwriter"><%=qnaDTO.getId() %></td></tr>
<tr><td class="qnatitle">글제목</td><td class="vtitle"><%=qnaDTO.getTitle() %></td></tr>
<tr><td class="qnacategory">분류</td><td class="vwriter"><%=qnaDTO.getCategory() %></td></tr>
<tr><td class="qnacontent">내용</td><td class="vcontent"><%=qnaDTO.getContent() %></td></tr>

<%-- <%-- <tr><td>첨부파일</td><td><!--  <a href="uploadPath/<%=boardDTO.getFile() %>"  download ></a>  <%=boardDTO.getFile() %> --%>
<%--       										<img src="uploadPath/<%=boardDTO.getFile() %> " width="200" height="200"> --> --%>
<!--                                           </td></tr>     -->
<%-- <tr><td>내용</td><td><!--  <%=boardDTO.getContent() %> --></td></tr>     --%>

</table>

<div class="btn"> 
<%-- <%  --%>
<!--   if(id != null){ -->
<!--  if(id.equals(boardDTO.getName())){ -->	
<%-- 		%> --%>
		
   <button type="button" value="삭제" class="deletebtn" onclick="location.href='qnadelete.bo'"> 삭제</button>
   <button type="button" value="수정" class="modifybtn" onclick="location.href='qnamodify.bo'"> 수정 </button>
 
<%--   <%  	 --%>
<!-- 	   }  --> 
<!-- 	}  --> 
<%-- %> --%>
 <button type="button" value="목록" class="listbtn" onclick="location.href='list.bo'"> 목록 </button>
 
 
   </div>
  <!--  일반 사용자  -->
   
   <h1 class="ansheadh1">답변</h1>
<hr>
<table id="notice" border="1">
<tr><td class="qnawriter">글쓴이</td><td class="vwriter"> 관리자 </td></tr> <!--  관리자로 되어야 함  -->
<tr><td class="ansdate">작성일</td><td class="vansdate"> 작성일 </td></tr>  <!--  작성자가 입력한 날짜를 받아와야함 -->
<tr><td class="qnawriter">분류</td><td class="vwriter"> 이용 문의 </td></tr> <!--  관리자로 되어야 함  -->
<tr><td class="anscontent">내용</td><td class="vcontent"> 내용 </td></tr> <!--  관리지가 답변한 내용이 없으면 ? 없습니다.라고 뜨게해야함 -->
</table>

<div class="btn"> 
<%-- <%  --%>
<!--   if(id != null){ -->
<!--  if(id.equals(boardDTO.getName())){ -->	
<%-- 		%> --%>
		
   <button type="button" value="삭제" class="deletebtn" onclick="location.href='qnadelete.bo'"> 삭제</button>
   <button type="button" value="수정" class="modifybtn" onclick="location.href='qnamodify.bo'"> 수정 </button>
 
<%--   <%  	 --%>
<!-- 	   }  --> 
<!-- 	}  --> 
<%-- %> --%>
 <button type="button" value="목록" class="listbtn" onclick="location.href='list.bo'"> 목록 </button>
 
 
   </div>

   </div> 
   
   <!-- 
   
   if(id != null){
	if(id.equals(boardDTO.getName())){
		%>
<input type="button" value="글수정" onclick="location.href='update.bo?num=<%-- <%=boardDTO.getNum()%>'">
<%-- <input type="button" value="글삭제" onclick="location.href='delete.bo?num=<%=boardDTO.getNum()%>'">		--%>
		
	}
}  -->


<script>
function really1(qnanum) { //글삭제
    var result = confirm("정말로 삭제하시겠습니까?");
    if (result) {
        location.href = 'qnaDelete.bo?qna_num=' + qnanum;
    }
}
function really2(qnanum) { //답글삭제
    var result = confirm("정말로 삭제하시겠습니까?");
    if (result) {
        location.href = 'qnaDelete.bo?qna_num=' + qnanum;
    }
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
	
</body>
</html>






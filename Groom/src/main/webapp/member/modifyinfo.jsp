<%@page import="web.groom.dto.MemberDTO"%>
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
	
	<!-- Theme style  -->
	<link rel="stylesheet" href="./css/member_gr.css">
	
	<!-- 로그인 스타일  -->
	<link rel="stylesheet" href="./css/modifyinfo_gr.css">
	
	
	<body>
	<%
	// String id=(String)session.getAttribute("id");
	// MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
	%>
<!-- 로고 자리 -->
    <h1 id="fh5co-logo1"><a href="main.gr">
	<img src="./images/LOGO.png" style="margin-top:-10px; margin-bottom: 10px;"></a></h1>
    
       
<!-- 내정보 수정 텍스트 -->  
    <p class="text"  class="myinfo">내 정보 수정</p>
    
<!-- 가로선 -->  
    <div class="separator"></div>
       	  
<!-- 아이디 -->
         <form>
			<div class="membership">
			  <div>
				<label class="imp2">아이디</label>
				<div>
				  <input type="text" >
				</div>
			  </div>
			  
		
<!-- 변경할 비밀번호 -->
			  <div>
				<br><label class="imp2">변경할 비밀번호</label>
				<div>
				<input type="password" placeholder="변경할 비밀번호를 입력하세요" required>
				</div>
			  </div>
		
<!-- 이름 -->
			  <div>
				<br><label class="imp2">이름</label>
				<div>
				<input type="text">
				</div>
			  </div>
			  
<!-- 이메일 -->
			  <div>
				<br><label class="imp2">이메일</label>
				<div>
				<input type="text" >
				</div>
			  </div>
			  
<!-- 휴대폰번호 -->

			  
        
			
		
<!-- 수정하기 버튼 -->
			<button type="submit"  class="submit" onclick="location.href='modifyinfoPro.me'">수정하기 </button>
		  </form> 

	</body>
</html>
<%@page import="web.groom.dto.MemberDTO"%>
<%@page import="web.groom.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../inc/head.jsp"></jsp:include>
	
</head>

<link rel="stylesheet" href="./css/member_gr.css">
	
	<!-- 아이디결과  스타일  -->
	<link rel="stylesheet" href="./css/findidresult_gr.css">
	
	
<body>
<%

    
   MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
     %>



  <form name="findidresult" method="post"  class="finidresult">
     <h1> 회원님의 아이디를 찾았습니다!</h1>
     <div class= "membership" > 
     
      <% 
      if ( memberDTO != null) { 
      %>
      
      	<div class = "found-success">
	      <p>  회원님의 아이디는 </p>   <div class ="idresult"><%=memberDTO.getU_Id()%></div> <p>  입니다 </p>
	     
	 
	     
	     <% 
	     } else {
	     
	     %>
	     <div class = "found-login">
 		    <input type="button" id="btnLogin" value="로그인" onClick = 'login()'/>
       	</div>
       
       
        <div class = "container">
      	<div class = "found-fail">
	      <h4>  등록된 정보가 없습니다 </h4>  
	     </div>
	     <div class = "found-login">
 		    <input type="button" id="btnback" value="다시 찾기" onClick="history.back()"/>
 		    <input type="button" id="btnjoin" value="회원가입" onClick="joinin()"/>
       	</div>
       </div>
       <%
	     }
       %>
        <hr style="margin: 20px 0; border: 2 solid #ccc;">
        
       <div id="button">
			<button type="submit" style="width: 390px; font-size: 16px; margin-left: 0; ">아이디 찾기</button>
		   </div>
	    </div>   
      </div>
      </form>
   <!--  
   
   로고 자리 	
		<h1 style="margin-top: -55px; margin-bottom: 55px;"> Groom(로고) </h1>
		
 테두리선 시작 
			<div class="membership" style="border: 1px solid #ccc; border-radius: 10px; padding: 30px;">
			 
 이름 
			  <div>
				<br><label class="imp2" style="font-size: 16px;">이름</label>
				<div>
				<input type="text"  id ="u_name"  name ="u_name" placeholder="이름을 입력하세요" required >
				</div>
			  </div>
		
 이메일 
			  <div>
				<br><label class="imp2"  style="font-size: 16px;">이메일</label>
				<div>
				<input type="text" id="u_email" name="u_email"placeholder="이메일을 입력하세요" required>
				</div>
			  </div>
				 
 가로줄 추가 
		<hr style="margin: 20px 0; border: 2 solid #ccc;">
		

 아이디찾기 버튼
		<div id="button">
			<button type="submit" style="width: 390px; font-size: 16px; margin-left: 0; ">아이디 찾기</button>
		   </div>
	    </div>   
테두리선 끝 
   
   
   
   -->
</body>
</html>


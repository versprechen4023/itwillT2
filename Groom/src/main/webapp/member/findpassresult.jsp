<%@page import="web.groom.dto.MemberDTO"%>
<%@page import="web.groom.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

    
   MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
     %>


  <form name="findidresult" method="post">
      <% 
      if ( memberDTO != null) { 
      %>
      <div class = "container">
      	<div class = "found-success">
	      <h4>  회원님의 아이디는 </h4>  
	      <div class ="found-id"><%= memberDTO.getId() %></div>
	      <h4>  입니다 </h4>
	     </div>
	     
	     <% 
	     } else {
	     
	     %>
	     <div class = "found-login">
 		    <input type="button" id="btnLogin" value="로그인" onClick = 'login()'/>
       	</div>
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

      </form>
   
</body>
</html>

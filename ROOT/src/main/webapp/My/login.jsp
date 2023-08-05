<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
String userId = (String)session.getAttribute("userId"); // userId값 받아오기
if (session != null && session.getAttribute("userId") != null){ //userId값이 존재한다면 Welcome.jsp로 리다이렉트
	response.sendRedirect("main.do");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<form action="login.do" method="post" name="fr">
아이디 : <input type="text" name="id"><br>
비밀번호 : <input type="password" name="pass"><br>
<input type="submit" value="로그인">
<input type="button" value="회원가입" onclick="fun()">
<script type="text/javascript">
function fun() {
	location.href='SignUpPage.do';
}
</script>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>이미지 표시 예제</title>
</head>
<body>
<!-- 데이터베이스에서 가져온 이미지 경로 -->
<%
 String myImg = "dog.jpg";// 데이터베이스에서 이미지 경로 가져오기
%>
<img src="./upload/<%=myImg%>" alt="이미지">
<img src="./upload/dog.jpg" alt="이미지">
</body>
</html>
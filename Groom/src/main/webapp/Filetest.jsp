<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>파일업로드 테스트</title>
</head>
<body>
<form action="upload.up" method="post" enctype="multipart/form-data">
<input type="file" name="file">
<input type="submit" value="업로드">
</form>
</body>
</html>
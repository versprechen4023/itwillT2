<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- 헤드호출 -->
    <jsp:include page="../inc/head.jsp"></jsp:include>
    <link rel="stylesheet" href="./css/noticeWrite_gr.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/aside_gr.css">
</head>
<body>
    <!-- noticeWrite css 추가 -->

    <!-- 사이드바호출 -->
    <jsp:include page="../inc/aside.jsp"></jsp:include>
    <div id="fh5co-page">
        <div id="fh5co-main">
            <h2>공지사항</h2>
            <hr>

            <form id="nwf" action="noticeWritePro.bo" method="post">
                <input type="text" class="sub" value="제목을 입력해 주세요" name="n_title">
                <textarea class="cont" name="n_content">내용을 입력해 주세요</textarea>

                <div class="input-group file-upload-container">
                    <label class="file-upload" for="exampleInputFile">파일 선택</label>
                    <input type="file" id="exampleInputFile" name="n_img_url">
                </div>

                <!-- 버튼 ================================================================== -->
                
                    
                    <div class="buttons">
                     <input type="submit" class="nwbtn" value="작성">
                </div>
                <!-- 버튼 ================================================================== -->
            </form>
        </div>
    </div>
</body>
</html>
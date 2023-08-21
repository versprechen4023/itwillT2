<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>
</head>

<!-- Theme style  -->
<link rel="stylesheet" href="./css/member_gr.css">

<!-- 리뷰작성페이지 스타일  -->
<link rel="stylesheet" href="./css/writereview_gr.css">

<body>

<!-- 로고 자리 -->
<div style="text-align: center;">
    <h1 style="margin-top: 60px; margin-bottom: 85px;"> Groom(로고) </h1>
    
    
    
<!-- 리뷰쓰기(텍스트) -->
<p style="text-align: left; font-size: 23px; margin-bottom: 25px;">리뷰 쓰기</p>



<!-- 가로선 -->
<div class="divider" style="width: 410px;"></div>



<!-- 서비스는 만족하셨나요? -->
<div style="text-align: center; margin: 30px 20px;">
  <p>서비스는 만족하셨나요?</p>
</div>



<!-- ★★★★★ -->
<div style="display: flex; justify-content: center; margin-top: 10px; margin-bottom: 50px;">
  <div class="star">&#9734;</div>
  <div class="star">&#9734;</div>
  <div class="star">&#9734;</div>
  <div class="star">&#9734;</div>
  <div class="star">&#9734;</div>
</div>



<!-- 어떤 점이 좋았나요? -->
<div style="text-align: center;">
  <p>어떤 점이 좋았나요?</p>
</div>



<!-- 내용 작성칸 -->
<div style="text-align: center;">
  <textarea style="border: 1px solid #ccc; padding: 10px;" rows="30" cols="50" placeholder="내용을 입력해주세요"></textarea>
</div>



<!-- 사진/동영상 추가 -->
<div style="text-align: center; margin-top: 20px;">
  <div style="width: 340px; height: 30px; border: 1px solid #ccc; margin-bottom: 20px; display: flex;
              align-items: center; justify-content: center; margin-left: 35px;">
     
     <!-- 아이콘(이미지) -->
      <img src="./images/photo.png" style="height: 15px; width: auto; margin-right: 5px;">
      
    <p style="margin: 0;">사진/동영상 추가</p>
  </div>
</div>



<!-- 적립 안내 문구 -->
<div style="text-align: center;">
  <p>* 리뷰 작성 시 결제금액의 1% 적립</p>
</div>



<!-- 취소, 등록 버튼 -->
<div style="display: flex; justify-content: center; margin-top: 20px;">
  <button style="padding: 10px 30px; background-color: black; border: none; border-radius: 5px; font-size: 14px;">취소</button>
  <button style="padding: 10px 30px; background-color: black; border: none; border-radius: 5px; font-size: 14px;  margin-right: 10px;">등록</button>
</div>
</div>




<script>
  const stars = document.querySelectorAll('.star');

  stars.forEach((star, index) => {
    star.addEventListener('click', () => {
      for (let i = 0; i <= index; i++) {
        stars[i].innerHTML = '&#9733;'; // Filled star
      }
      for (let i = index + 1; i < stars.length; i++) {
        stars[i].innerHTML = '&#9734;'; // Empty star
      }
    });
  });
</script>
</body>
</html>
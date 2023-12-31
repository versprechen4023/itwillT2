<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" type="text/css" href="./css/mypet_gr.css">

</head>
<body>

<div class="logo">
  <img src="./images/logo01.png" alt="logo" width="100%" height="225px">
</div>

<div class="content-wrapper">
  <div class="container">
    <h2>반려동물 정보 등록</h2>
    
    <div>
      <label for="petname" class="labelstyle">이름</label><br>
      <input id="petname" name="petname" type="text" placeholder="이름을 입력해주세요"><br>
    </div>
    
    <div>
      <label for="breed" class="labelstyle">품종</label><br>
      <input id="breed" name="breed" type="text" placeholder="품종을 입력해주세요"><br>
    </div>
    
<!--     	성별 및 중성화여부 드롭다운방식 -->
<!--     <div> -->
<!--       <label for="gender" class="labelstyle">성별 및 중성화여부</label> <br> -->
<!--       <select name="gender" id="gender"> -->
<!--         <option value="select">--- 선택해주세요 ---</option> -->
<!--         <option value="maleyes">수컷 / 중성화O</option> -->
<!--         <option value="maleno">수컷 / 중성화X</option> -->
<!--         <option value="femaleyes">암컷 / 중성화O</option> -->
<!--         <option value="femaleno">암컷 / 중성화X</option> -->
<!--       </select> -->
<!--     <br> -->
<!--     </div> -->

    
    <!-- 성별 및 중성화여부 라디오 버튼 그룹 -->
      <label for="gender" class="labelstyle">성별</label>
    <div class="select">
      <input type="radio" id="male" name="gender"><label for="male">수컷</label>
      <input type="radio" id="female" name="gender"><label for="female">암컷</label>
    </div>

    <!-- 중성화 여부 라디오 버튼 그룹 -->
    <label for="neuter" class="labelstyle">중성화 여부</label>
    <div class="select">
      <input type="radio" id="yes" name="neuter"><label for="yes">유</label>
      <input type="radio" id="no" name="neuter"><label for="no">무</label>
    </div>

    
  <label for="content" class="labelstyle">특이사항</label><br>
  <div class="editable-area" contenteditable="true" data-placeholder="내용을 입력하세요"></div>
		<br>
<!--   <textarea name="content" cols="30" rows="8" placeholder="내용을 입력해주세요"></textarea> -->


    <br>
    <div id="button" style="text-align: center;">
      <button type="submit">등록</button>
    </div>
  </div>
</div>

</body>
</html>
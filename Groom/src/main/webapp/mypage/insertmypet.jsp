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


	<h1 id="fh5co-logo1"><a href="main.gr">
	<img src="./images/LOGO.png" style="margin-top:-10px; margin-bottom: 155px; width: 130px; height: 80px;"></a></h1>
 <form action="insertmypetPro.my" id="insertmypet" name="insertmypet" method="post">



<div class="content-wrapper">
  <div class="container">
    <h2>반려동물 정보 등록</h2>
    
    <div>
      <label for="petname" class="labelstyle">이름</label><br>
      <input id="petname" name="petname" type="text" placeholder="이름을 입력해주세요"><br><br>
<!--  반려동물 이름 공란시 sumbit 제어 "반려동물 이름 입력해주세요 출력" -->
 	  <span id="error-message" style="color: red; font-size: 12px; margin-top:-10px;"></span>
    </div>
    
    <div>
      <label for="breed" class="labelstyle">품종</label><br>
      <input id="petbreed" name="petbreed" type="text" placeholder="품종을 입력해주세요"><br><br>
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
      <input type="radio" value="M" id="male" name="petgender" checked><label for="male">수컷</label>
      <input type="radio" value="F" id="female" name="petgender"><label for="female">암컷</label><br>
    </div><br>

    <!-- 중성화 여부 라디오 버튼 그룹 -->
    <label for="neuter" class="labelstyle">중성화 여부</label>
    <div class="select">
      <input type="radio" value="Y" id="yes" name="petneuter"><label for="yes">유</label>
      <input type="radio" value="N" id="no" name="petneuter" checked><label for="no">무</label><br>
    </div><br>

    
  <label for="content" class="labelstyle">특이사항</label><br>
<!--   <div class="editable-area" id="petcomment" name="petcomment" contenteditable="true" data-placeholder="내용을 입력하세요"></div> -->
	
  <textarea class="comment" name="petcomment" cols="30" rows="8" name = "petcomment" placeholder="내용을 입력해주세요"></textarea>


    <br>
    <div id="button" style="text-align: center;">
      <button type="submit" >등록</button>
    </div>
  </div>
</div>
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$('#insertmypet').submit(function(event) {
    if ($('#petname').val() == "") {
        event.preventDefault(); // 폼 제출을 중단합니다.
        $('#petname').css('border-color', 'red'); // 필드 주변을 빨간색으로 표시합니다.
        $('#error-message').text("반려동물 이름을 입력해주세요."); // 오류 메시지를 설정합니다.
        $('#petname').focus();
    }
});//반려동물 이름 입력 안했을 때만. submit기능 제어 
</script>
</body>
</html>
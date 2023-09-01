<%@page import="web.groom.dto.MypageDTO"%>
<%@page import="web.groom.service.MypageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MypageDTO mypageInfo = (MypageDTO)request.getAttribute("mypageInfo");
MypageDTO mypagepetInfo = (MypageDTO)request.getAttribute("mypagepetInfo");
MypageDTO mypageDTOTest = (MypageDTO)request.getAttribute("mypageDTOTest");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" type="text/css" href="./css/mypet_gr.css">

</head>
<body>
 <form action="updatemypetPro.my" id="updatemypet" name="updatemypet" method="post">
<div class="logo">
  <img src="./images/logo01.png" alt="logo" width="100%" height="225px">
</div>

<div class="content-wrapper">
  <div class="container">
    <h2>반려동물 정보 수정</h2>
    
    <div>
      <label for="petname" class="labelstyle">이름</label><br>
      <input id="petname" name="petname" type="text" value="<%=mypageDTOTest.getPetName()%>" ><br>
<!--  반려동물 이름 공란시 sumbit 제어 "반려동물 이름 입력해주세요 출력" -->
 	  <span id="error-message" style="color: red; font-size: 12px"></span>
    </div>
    
    <div>
      <label for="breed" class="labelstyle">품종</label><br>
      <input id="petbreed" name="petbreed" type="text" value="<%=mypageDTOTest.getPetBreed()%>"><br>
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
    <div class="select" id = "gender">
      <input type="radio" value="M" id="male" name="petgender" value="M"><label for="male">수컷</label>
      <input type="radio" value="F" id="female" name="petgender" value="F"><label for="female">암컷</label>
    </div>

    <!-- 중성화 여부 라디오 버튼 그룹 -->
    <label for="neuter" class="labelstyle">중성화 여부</label>
    <div class="select" id = "netuer">
      <input type="radio" value="Y" id="yes" name="petneuter" value="Y"><label for="yes">유</label>
      <input type="radio" value="N" id="no" name="petneuter" value="N"><label for="no">무</label>
    </div>

    
  <label for="content" class="labelstyle">특이사항</label><br>
<!--   <div class="editable-area" id="petcomment" name="petcomment" contenteditable="true" data-placeholder="내용을 입력하세요"></div> -->
	
  <textarea class="comment" name="petcomment" cols="30" rows="8" name = "petcomment" ><%=mypageDTOTest.getPetComment()%></textarea>


    <br>
    <div id="button" style="text-align: center;">
      <button type="submit" >수정</button>
     <button type="button" value="삭제" class="btn"
       onclick="deletePet(<%=mypageDTOTest.getPetNum()%>)"> 삭제 </button>
    </div>
  </div>
</div>
	<input type="hidden" id="pet_num" name ="pet_num" value="<%=mypageDTOTest.getPetNum()%>">
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
var DBGender = "<%=mypageDTOTest.getPetGender()%>";
var DBNeuter = "<%=mypageDTOTest.getPetNeuter()%>";

if (DBGender == "M") {
    document.getElementById("male").checked = true;  // "수컷" 라디오 버튼을 선택합니다.
} else if (MyGender === "F") {
    document.getElementById("female").checked = true;  // "암컷" 라디오 버튼을 선택합니다.
}

if (DBNeuter == "Y") {
    document.getElementById("yes").checked = true;
} else if (DBNeuter === "N") {
    document.getElementById("no").checked = true;
}

$('#insertmypet').submit(function(event) {
    if ($('#petname').val() == "") {
        event.preventDefault(); // 이름 공란일시 폼 제출 멈춤.
        $('#petname').css('border-color', 'red'); // 필드 주변을 빨간색으로 표시합니다.
        $('#error-message').text("반려동물 이름을 입력해주세요."); // 오류 메시지를 설정합니다.
        $('#petname').focus();
    }
});//반려동물 이름 입력 안했을 때만. submit기능 제어 

function deletePet(petNum) {
    if (confirm("정말로 정보를 삭제하시겠습니까?")) {
        location.href = 'deletemypet.my?pet_num=' + petNum;
    }
}
</script>
</body>
</html>
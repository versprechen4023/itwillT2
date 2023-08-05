<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String userId = (String)session.getAttribute("userId"); // userId값 받아오기
if (session != null && session.getAttribute("userId") != null){ //userId값이 존재한다면 index.do로 리다이렉트
	out.println("<script>alert('이미 로그인 되어 있습니다');</script>");
    out.println("<script>location.href='index.do'</script>");
}
out.println(userId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<style type="text/css">

*{
margin:0px;
padding:0px;
}
body{
background-color: #ccc;
padding:20px;
}
#signup{
background-color: white;
border:1px;
border-style:solid;
border-color:#222;
border-radius: 5px;
padding:20px;
width:400px;
margin:0px auto;
}
#signup fieldset{
border:1px;
border-style:solid;
border-color:#ccc;
margin-bottom: 30px;
}
#signup fieldset:last-of-type{
border:none;
}
#signup legend{
font-size:16px;
font-weight:bold;
padding-left: 5px;
padding-bottom: 10px;
}
#signup ul li{
line-height:30px;
list-style-type: none;
padding-top: 5px;
padding-bottom: 5px;
padding-left: 10px;
padding-right: 10px;
margin-bottom: 2px;
}
#signup label{
width:110px;
font-size:13px;
float:left;
}
#signup input:not([type=submit]){
border:1px;
border-style:solid;
border-color:#ccc;
border-radius: 3px;
font-size:13px;
padding:5px;
width:200px;
}
#signup input[type="submit"]{
border:1px;
border-style:solid;
border-color:#222;
border-radius: 20px;
font-size: 16px;
letter-spacing:1px;
margin:0px auto;
padding-top: 7px;
padding-bottom: 7px;
padding-left: 25px;
padding-right: 25px;
display:block;
}
#signup input[required]{
border:1px;
border-style:solid;
border-color:red;
}
#signup input[readonly]{
border:none;
}
#signup input[type="submit"]:hover{
background-color: #222;
color:#fff;
}
</style>
</head>
<body>
<form id="signup" action="SingUp.do" method="post" name="fr" id="fr">
    <fieldset>
      <legend>로그인 정보</legend>
      <ul>
        <li>
          <label for="user_id">아이디</label>
          <input id="user_id" name="user_id" id="user_id" type="text" required autofocus>
          <input type="button" value="Check" id="idtest" style="width:50px">
        </li>
        <li>
          <label for="pwd1">비밀번호</label>
          <input id="pwd1" name="pwd1" id="pwd1" type="password" required>
        </li>
        <li>
          <label for="pwd2">비밀번호 확인</label>
          <input id="pwd2" name="pwd2" id="pwd2" type="password" required>
        </li>  
      </ul>
    </fieldset>
    <fieldset>
      <legend>개인 정보</legend>
      <ul>
        <li>
          <label for="fullname">이름</label>
          <input id="fullname" name="fullname" id="fullname" type="text" placeholder="10자이하 공백없이" required>
        </li>
        <li>
          <label for="email">메일 주소</label>
          <input id="email" name="email" id ="email" type="email" placeholder="abcd@domain.com" required autocomplete="off">
        </li>
        <li>
          <label for="tel">연락처</label>
          <input id="tel" name="tel" type="tel" autocomplete="off">
        </li>  
      </ul>
      <div id ='check'></div>
    </fieldset>
    <fieldset>
    	<input type="submit" value="회원가입" id="send">
    </fieldset>
  </form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

var idcheck = -1;

$(function(){
	$("#idtest").click(function(){
	var checksum = $("#user_id").val();
	if(checksum == ''){
		$('#check').css('color', 'red')
		$("#check").html("아이디를 입력해 주세요")
		idcheck = -1;
		$('#user_id').focus()
		return false;
	}
 	var param = {user_id:$("#user_id").val()}
	
	$.ajax({
		type: "POST",
		url: "My/idCheck.jsp",
		data: param,
		success: function(data){
			let data1 = $.trim(data);
			
			if(data1 === 'check'){
				$('#check').css('color', 'red')
				$("#check").html("이미 존재하는 아이디 명입니다")
				idcheck = -1;
			} else {
				$('#check').css('color', 'black')	
				$("#check").html("사용 가능한 아이디 입니다")
				idcheck = 1;
			}
		},
		error : function(e){
			console.log(e)
			alert("error")
		}
	})
	})
})

	$(function() {
		$('#send').click(function() {
			$('#check').css('color', 'red')
			
			if ($('#user_id').val() == '') {
				$('#check').html("아이디를 입력해 주세요")
				$('#user_id').focus()
				return false;
			}
			
			if(idcheck === -1){
				$('#check').html("아이디 중복확인 하여 주십시오")
				return false
			}

			if ($('#pwd1').val() == '') {
				$('#check').html("비밀번호를 입력해 주세요")
				$('#pwd1').focus()
				return false;
			}

			if ($('#pwd2').val() == '') {
				$('#check').html("비밀번호 확인을 입력해 주세요")
				$('#pwd2').focus()
				return false;
			}

			if ($('#fullname').val() == '') {
				$('#check').html("이름을 입력해 주세요")
				$('#fullname').focus()
				return false;
			}

			if ($('#fullname').val().length >= 11) {
				$('#check').html("이름은 최대10자까지만 허용합니다")
				$('#fullname').focus()
				return false;
			}

			if ($('#email').val() == '') {
				$('#check').html("이메일을 입력해 주세요")
				$('#email').focus()
				return false;
			}

			if ($('#pwd1').val() !== $('#pwd2').val()) {
				$('#check').html("비밀번호와 확인부분이 일치하지 않습니다")
				$('#pwd1').focus()
				return false;
			}

		})
	})
</script>
</body>
</html>
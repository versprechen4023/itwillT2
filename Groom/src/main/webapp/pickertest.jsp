<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="time.up" method="post">
    <label for="datepicker">날짜 선택:</label>
    <input type="text" id="datepicker" name="datepicker">
    <br>
    <label for="timepicker">시간 선택:</label>
    <input type="time" id="timepicker" name="timepicker">
    <br>
    <input type="submit" value="전송">
</form>
<script>
$(document).ready(function() {
	
	//날짜구하는함수
	var currentDate = new Date();
	var currentYear = currentDate.getFullYear();
	var currentMonth = currentDate.getMonth();
	var currentDate = currentDate.getDate();
	    
	$("#datepicker").datepicker({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    dayNames: ['일','월','화','수','목','금','토'],
    dayNamesShort: ['일','월','화','수','목','금','토'],
    dayNamesMin: ['일','월','화','수','목','금','토'],
    showMonthAfterYear: true,
    changeMonth: true,
    changeYear: true,
    yearSuffix: '년',
    
    //날짜 비활성화 관련 함수들
    beforeShowDay: function(date) {
       var day = date.getDay();
       var dateString = $.datepicker.formatDate('yy-mm-dd', date); // 선택한 날짜를 형식에 맞게 문자열로 변환
       var disabledDates = ["2023-08-17"]; 
       
// 비활성화할 날짜들 배열로 저장 나중에 서버에서가져오면댈듯 
//        ArrayList<String> data = new ArrayList<>();
//        data.add("2023-08-17");
//        data.add("2023-08-18");
//        data.add("2023-08-19");
//        request.setAttribute("data", data);
// var disabledDates = [
<%--             <% for (String date : data) { %> --%>
<%--                 "<%= date %>", --%>
<%--             <% } %> --%>
//         ]; 같은느낌?

       var isDisabled = ($.inArray(dateString, disabledDates) !== -1);
       return [(day !== 0 && day !== 6 && !isDisabled)]; // 일요일(0)과 토요일(6)을 제외한 날짜만 선택 가능
   },
   
   //날짜 제한 관련
   //년도범위 년도 : 년도 형식 2021 : 2023이라면 2021~2023까지 활성
   yearRange: currentYear + ':' + (currentYear + 1),
   // 최소 날짜 범위 년도, 달, 일로되어있음 지금은 23년8월17일로 되어있어서 8월18일부터 선택가능
   minDate: new Date(currentYear, currentMonth, currentDate+1),
   // 최대 날짜 범위 년도, 달, 일로되어있음 지금은 23년8월+4(12월까지)하고 최대날짜는 31일로 되어있음 문제가있는데 년도가 넘어가면 계산식이바뀜
   maxDate: new Date(currentYear, currentMonth + 4, 31)
   
	});

});
</script>
</body>
</html>
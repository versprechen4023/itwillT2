<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/adminDisdate_gr.css">
<body>
<form action="admin_disday.ad" id="admin_disday" name="admin_disday" method="get">
<div class="pick-main">
<div class="pick-button">
<input type="text" id="datepicker" name="datepicker" placeholder="휴무일 선택" readonly>
</div>
<div class="pickconfirm-button">
<input type="submit" value="등록">
<input type="button" value="취소" onClick="window.close()">
</div>
</div>
<input type="hidden" id="s_num" name="s_num" value="${param.s_num}">
<%-- 밸류에는 지점값추가(${param.s_num}) 하면됨 --%>
</form>

<!-- 데이트피커 타임피커를 사용하기위한 j쿼리 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!--  데이트피커 커스텀 css-->
<link rel="stylesheet" type="text/css" href="./css/datepicker_gr.css">
<script>
// 데이트피커에서 비활성화할 날짜
var disabledDates = []; // 여기에 비활성화될 데이터들 JSON으로 가져와서 넣기

// 지점번호 변수값
var selectedStore = $('#s_num').val();

// 날짜 구하는 함수
var currentDate = new Date();
var currentYear = currentDate.getFullYear();
var currentMonth = currentDate.getMonth();
var currentDateVal = currentDate.getDate();

// 데이트피커 초기화
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
  yearSuffix: '년',

  // 날짜 비활성화 관련 함수들
  beforeShowDay: function(date) {
    var day = date.getDay();
    var dateString = $.datepicker.formatDate('yy-mm-dd', date); // 날짜를 형식에 맞게 문자열로 변환

    var isDisabled = ($.inArray(dateString, disabledDates) !== -1);//비활성화될 데이터들 데이터 변환해서 최종적으로 일자 보여주기전에 비활성화해서 반환
    return [(day !== 0 && day !== 6 && !isDisabled)]; // 일요일(0)과 토요일(6)을 제외한 날짜만 선택 가능
  },

  // 날짜 제한 관련
  // 년도 범위 년도 : 년도 형식 2021 : 2023이라면 2021~2023까지 활성
  yearRange: currentYear + ':' + (currentYear + 1),
  // 최소 날짜 범위 년도, 달, 일로 되어있음. 지금은 23년 8월 17일로 되어있어서 8월 18일부터 선택 가능
  minDate: new Date(currentYear, currentMonth, currentDateVal + 1),
  
  onSelect: function() { // 날짜를 선택해도 바로 데이터피커창 활성화
      var $this = $(this);
      setTimeout(function() {
          $this.datepicker('show');
      }, 1);
  }//
});
//데이트피커를 초기에 열기 (창 열자마자 활성화)
// $("#datepicker").datepicker("show");

// 데이트피커 AJAX 처리
$.ajax({
type: "GET",
url: 'getAdDate.aj',
data: { "selectedStore": selectedStore }, // 선택된 값을 서버로 전송
dataType: 'json',
success: function (result) {
disabledDates = result.map(function (item) {
return item.date;
});
$("#datepicker").focus();
}
});
</script>
</body>
</html>
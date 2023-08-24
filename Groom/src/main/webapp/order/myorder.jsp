<%@page import="web.groom.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDTO memberInfo = (MemberDTO)request.getAttribute("memberInfo");
%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>

	</head>
	
	<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<!-- 사이드바호출 -->
		<jsp:include page="../inc/aside.jsp"></jsp:include>

		<div id="fh5co-main">
			<div class="fh5co-narrow-content animate-box" data-animate-effect="fadeInLeft">
				
				<div class="row">
					<div class="col-md-4">
						<h2>예약주문</h2>
					</div>
				</div>
				<form action="">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<p>예약자명</p>
										<input type="text" class="form-control" value="<%=memberInfo.getName() %>" readonly>
									</div>
									<div class="form-group">
									    <p>연락처</p>
										<input type="text" class="form-control" value="<%=memberInfo.getPhone() %>" readonly>
									</div>
								</div>
								<div class="col-md-3">
								    <div class="form-group">
								    	<p>매장선택</p>
										<select class="form-control" id="list" name="list">
											<option value="test">매장을 선택하세요</option>
											<option value="A">서면점</option>
											<option value="B">김해점</option>
										</select>
									</div>
									<div class="form-group">
										<p>서비스선택</p>
										<select class="form-control" id="list" name="list">
											<option value="test">서비스를 선택하세요</option>
											<option value="A">[미용]대형견 15KG</option>
										</select>
									</div>
										<p>날짜선택</p>
									<div class="form-group">
										<input type="text" id="datepicker" name="datepicker" class="form-control" placeholder="날짜" readonly>
									</div>
										<p>예약선택</p>
									<div class="form-group">
										<input type="text" id="timepicker" name="timepicker" class="form-control" placeholder="타임피커구현필요" readonly>
									</div>
										<p>예상예약요금</p>
									<div class="form-group">
										<input type="text" class="form-control" value="여기에 JSP태그" readonly>
									</div>
								</div>
								
								<div class="col-md-3">
									<p>요구사항기입</p>
									<div class="form-group">
										<textarea name="" id="message" cols="30" rows="7" class="form-control" placeholder="전달할말"></textarea>
									</div>
									
									<div class="form-group">
										<input type="submit" class="btn btn-primary btn-md" value="예약하기">
									</div>
								</div>
								
							</div>
						</div>
						
					</div>
				</form>
				
				<div class="fh5co-more-contact">
				<div class="fh5co-narrow-content">
					<div class="row">
						<div class="col-md-4">
							<div class="fh5co-feature fh5co-feature-sm animate-box" data-animate-effect="fadeInLeft">
								<div class="fh5co-icon">
									<i class="icon-globe"></i>
								</div>
								<div class="fh5co-text">
									<p><a href="#">info@domain.com</a></p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="fh5co-feature fh5co-feature-sm animate-box" data-animate-effect="fadeInLeft">
								<div class="fh5co-icon">
									<i class="icon-map"></i>
								</div>
								<div class="fh5co-text">
									<p>198 West 21th Street, Suite 721 New York NY 10016</p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="fh5co-feature fh5co-feature-sm animate-box" data-animate-effect="fadeInLeft">
								<div class="fh5co-icon">
									<i class="icon-phone"></i>
								</div>
								<div class="fh5co-text">
									<p><a href="tel://">+123 456 7890</a></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			</div>
				
		</div>
	</div>

	<!-- jQuery -->
	<script src="./js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="./js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="./js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="./js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="./js/jquery.flexslider-min.js"></script>
	
	
	<!-- MAIN JS -->
	<script src="./js/main.js"></script>
	

<!-- 데이트피커 타임피커를 사용하기위한 j쿼리 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!--  데이트피커 커스텀 css-->
<link rel="stylesheet" type="text/css" href="./css/datepicker_gr.css">
	
<script type="text/javascript">
//사용자가 매장을 선택하면 매장선택값을 AJAX로 처리 -> DB조회후 특정매장에 따른 예약 가능날짜를반환 -> 사용자가 예약 날짜 선택가능
//사용자가 예약 날짜를 선택하면 예약날짜선택값을 AJAX로처리 -> DB조회후 선택한 날짜에 대한 예약가능 시간을 반환 -> 사용자가 예약 시간 선택가능
//사용자가 예약 시간을 선택하면 예약시간선택값을 AJAX로처리 -> DB조회후 시간이 야간일경우 금액추가? 자바단에서 최종금액계산 -> 사용자에게 예상금액 반환후 표시

//기존 템플릿 J쿼리충돌 해결 함수
var $j = jQuery.noConflict();

$j(document).ready(function() {
	
	//날짜구하는함수
	var currentDate = new Date();
	var currentYear = currentDate.getFullYear();
	var currentMonth = currentDate.getMonth();
	var currentDate = currentDate.getDate();
	    
	$j("#datepicker").datepicker({
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
    
    //날짜 비활성화 관련 함수들
    beforeShowDay: function(date) {
       var day = date.getDay();
       var dateString = $j.datepicker.formatDate('yy-mm-dd', date); // 선택한 날짜를 형식에 맞게 문자열로 변환
       var disabledDates = ["2023-08-21"]; 
       
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
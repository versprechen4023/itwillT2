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
    <!-- 	css설정 -->
	<link rel="stylesheet" href="./css/myorder_gr.css">
	
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
				<form action="myorderCheckout.or" id="checkout" method="post">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<p>예약자명</p>
										<input type="text" class="form-control" id="name" name="name" value="<%=memberInfo.getU_Name()%>" readonly>
									</div>
									<div class="form-group">
									    <p>연락처</p>
										<input type="text" class="form-control" id="phone" name="phone" value="<%=memberInfo.getU_Phone()%>" readonly>
									</div>
									<div class="form-group">
										<p>예상예약 요금</p>
										<input type="text" class="form-control" id="price" name="price" readonly>
									</div>
									<div class="form-group">
										<p>포인트 사용</p>
										<input type="text" class="form-control" id="point" name="point" value="0">
										내 포인트 = <%=memberInfo.getU_Point()%><br>
										포인트 사용<input type="checkbox" id="pointcheck" name="pointcheck">
									</div>
								</div>
								<div class="col-md-3">
								    <div class="form-group">
								    	<p>예약매장 선택</p>
										<select class="form-control" id="storelist" name="storelist">
											<option value="" disabled selected>매장을 선택하세요</option>
											<option value="1">서면점</option>
											<option value="2">명지점</option>
											<option value="3">율하점</option>
										</select>
									</div>
									<div class="form-group">
										<p>견종 선택</p>
										<select class="form-control" id="petlist" name="petlist" disabled>
											<option value="" disabled selected>견종을 선택하세요</option>
											<option value="1">소형견</option>
											<option value="2">중형견</option>
											<option value="3">대형견</option>
										</select>
									</div>
									<div class="form-group">
										<p>서비스 선택</p>
										<select class="form-control custom-select" id="servicelist" name="servicelist" disabled>
											<option value="" disabled selected>서비스를 선택하세요</option>
										</select>
									</div>
									<div class="form-group">
										<p>무게 선택</p>
										<select class="form-control custom-select" id="weightlist" name="weightlist" disabled>
											<option value="" disabled selected>무게를 선택하세요</option>
										</select>
									</div>
									<div class="form-group">
										<p>직원 선택</p>
										<select class="form-control custom-select" id="managerlist" name="managerlist" disabled>
											<option value="" disabled selected>담당직원을 선택하세요</option>
										</select>
									</div>
								</div>
								
								<div class="col-md-3">
									<div class="form-group">
										<p>예약날짜 선택</p>
										<input type="text" id="datepicker" name="datepicker" class="form-control" placeholder="예약일을 선택하세요" disabled readonly>
									</div>
									<div class="form-group">
										<p>예약시간 선택</p>
										<input type="text" id="timepicker" name="timepicker" class="form-control" placeholder="예약시간을 선택하세요" disabled>
									</div>
								</div>
								
								<div class="col-md-3">
									<div class="form-group">
										<p>요청사항작성</p>
										<textarea id="message" name="message" cols="30" rows="7" class="form-control" placeholder="요청사항이 있으면 기입해 주십시오"></textarea>
									</div>
									
								</div>
								
							</div>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-md" value="예약하기">
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

<!-- jQuery Timepicker 라이브러리 추가 -->
<link rel="stylesheet" href="./css/jquery.timepicker.css">
<script src="./js/jquery.timepicker.js"></script>


<script type="text/javascript">
//사용자가 매장을 선택하면 매장선택값을 AJAX로 처리 -> DB조회후 특정매장에 따른 예약 가능날짜를반환 -> 사용자가 예약 날짜 선택가능
//사용자가 예약 날짜를 선택하면 예약날짜선택값을 AJAX로처리 -> DB조회후 선택한 날짜에 대한 예약가능 시간을 반환 -> 사용자가 예약 시간 선택가능
//사용자가 예약 시간을 선택하면 예약시간선택값을 AJAX로처리 -> DB조회후 시간이 야간일경우 금액추가? 자바단에서 최종금액계산 -> 사용자에게 예상금액 반환후 표시

//기존 템플릿 J쿼리충돌 해결 함수
var $j = jQuery.noConflict();

// 데이트피커에서 비활성화할 날짜
var disabledDates = []; //여기에 비활성화활 데이터들 JSON으로 가져와서 넣기

// 타임피커에서 비활성화할 시간
var disabledTimes = []; //여기에 비활성화할 데이터를 JSON으로 가져온다 형식의 예제는 "20:00", "20:01"

// 전역변수 용
var storelist = document.getElementById("storelist");
var petlist = document.getElementById("petlist");
var servicelist = document.getElementById("servicelist");
var weightlist = document.getElementById("weightlist");
var managerlist = document.getElementById("managerlist");
var datepicker = document.getElementById("datepicker");
var timepicker = document.getElementById("timepicker");
var price = document.getElementById("price");
var point = parseInt(document.getElementById("point").value);
	
//제이쿼리 함수 시작 지점
$j(document).ready(function() {
	
	//서브밋 기능 제어 함수 //셀렉트태그 부분 null이라고 잘뜨는데 if문안됨
    $j('#checkout').submit(function() {
		alert("서브밋 제어 실행");
        
    	if($j('#storelist').val() == ""){
    		alert("예약할 지점을 선택하여 주십시오"); 
    		return false;
    	}
    	if($j("#servicelist").val() == ""){
    		alert("서비스를 선택하여 주십시오"); 
    		return false;
    	}
    	if($j("#petlist").val() == ""){
    		alert("견종을 선택하여 주십시오"); 
    		return false;
    	}
    	if($j("#weightlist").val() == ""){
    		alert("무게를 선택하여 주십시오"); 
    		return false;
    	}
    	if($j("#managerlist").val() == ""){
    		alert("담당 직원을 선택하여 주십시오"); 
    		return false;
    	}
    	if($j('#datepicker').val() == ""){
    		alert("예약일을 선택해주십시오"); 
    		return false;
    	}
    	if($j('#timepicker').val() == ""){
    		alert("예약할 시간을 선택하여 주십시오"); 
    		return false;
    	}

    });//submit기능 제어 끝
    
	//포인트 관련 함수
	$j('#point').keyup(function() {
		  point = $j('#point').val();//포인트 입력값 갱신
		  $j('#pointcheck').prop('checked', false);//입력값 갱신될때마다 포인트사용체크해제
		  managerlist.value = "";//금액을 재설정하기위해 초기화
		  datepicker.value = "";
		  timepicker.value ="";
		  $j("#datepicker").attr('disabled','disabled');
	});
	
	$j('#pointcheck').change(function() {
		managerlist.value = "";//금액을 재설정하기위해 초기화
		datepicker.value = "";
		timepicker.value ="";
		$j("#datepicker").attr('disabled','disabled');//날짜 입력 초기화
	});
	
	$j('#point').on('input', function() {
	    var myPoint = <%=memberInfo.getU_Point()%>; // 포인트의 상한값
	    var inputValue = parseInt($j(this).val()); // 입력 필드의 값을 정수로 고정

	    if (isNaN(inputValue)) {
	        inputValue = 0; // 입력값이 숫자가 아니라면 0으로 설정
	    }

	    if (inputValue < 0) {
	        inputValue = 0; // 0 미만의 값은 0으로 설정
	    } else if (inputValue > myPoint) {
	        inputValue = myPoint; // 상한값을 넘는 값은 상한값으로 설정
	    }

	    $j(this).val(inputValue); // 입력 필드의 값을 처리한 값으로 업데이트
	});
	
	// 지점선택에대한 함수
	$j('#storelist').change(function() {
		
		// 지점이 선택될시 사용을 위해 disabled 삭제
		$j("#petlist").removeAttr("disabled");
		$j("#servicelist").removeAttr("disabled");
		$j("#weightlist").removeAttr("disabled");
		$j("#managerlist").removeAttr("disabled");
		
		//지점이 바뀔경우 다른 입력값 모두 초기화 및 시간 날짜 입력 비활성화
		petlist.value = "";
		
		servicelist.value ="";
		
		weightlist.value = "";
		
		managerlist.value = "";
		
		datepicker.value = "";
		
		timepicker.value ="";
		
		price.value ="";
		
		for (var i = servicelist.options.length - 1; i >= 0; i--) {
    	    if (servicelist.options[i].value !== "") {
    	    	servicelist.remove(i);
    	    }
    	}
		for (var i = weightlist.options.length - 1; i >= 0; i--) {
    	    if (weightlist.options[i].value !== "") {
    	    	weightlist.remove(i);
    	    }
    	}
		for (var i = managerlist.options.length - 1; i >= 0; i--) {
    	    if (managerlist.options[i].value !== "") {
    	    	managerlist.remove(i);
    	    }
    	}
		
		$j("#datepicker").attr('disabled','disabled');
    	$j("#timepicker").attr('disabled','disabled');
    	
		// 지점선택 서비스 밸류값 가져오기
        var selectedStore = $j(this).val();
        
        // 서비스 종류를 얻기 위한 AJAX 요청.
        $j.ajax({
        	type: "GET",
            url: 'getService.aj',
            data: {"selectedStore":selectedStore}, // 선택된 값을 서버로 전송
            success: function(result) {
            	result.forEach(function(service) {
            	    var option = document.createElement("option");
            	    option.value = service.s_num;
            	    option.text = service.s_name;
            	    servicelist.appendChild(option);
            	});
            }
        });
        
     	// 무게 쪽을 얻기 위한 AJAX 요청.
        $j.ajax({
        	type: "GET",
            url: 'getWeight.aj',
            data: {"selectedStore":selectedStore}, // 선택된 값을 서버로 전송
            success: function(result) {
            	result.forEach(function(weight) {
            	    var option = document.createElement("option");
            	    option.value = weight.s_num;
            	    option.text = weight.s_weight;
            	    weightlist.appendChild(option);
            	});
            }
        });
     	
        // 직원 쪽을 얻기 위한 AJAX 요청.
        $j.ajax({
        	type: "GET",
            url: 'getManager.aj',
            data: {"selectedStore":selectedStore}, // 선택된 값을 서버로 전송
            success: function(result) {
            	result.forEach(function(manager) {
            	    var option = document.createElement("option");
            	    var fullName = manager.emp_grade + ' ' + manager.emp_name;
            	    option.value = manager.emp_num;
            	    option.text = fullName;
            	    managerlist.appendChild(option);
            	});
            }
        });
     	
	});
	
	// 날짜구하는함수
	var currentDate = new Date();
	var currentYear = currentDate.getFullYear();
	var currentMonth = currentDate.getMonth();
	var currentDate = currentDate.getDate();
	
	// 데이트피커 초기화
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
    
    // 날짜 비활성화 관련 함수들
    beforeShowDay: function(date) {
       var day = date.getDay();
       var dateString = $j.datepicker.formatDate('yy-mm-dd', date); // 날짜를 형식에 맞게 문자열로 변환

       var isDisabled = ($j.inArray(dateString, disabledDates) !== -1);//비활성화될 데이터들 데이터 변환해서 최종적으로 일자 보여주기전에 비활성화해서 반환
       return [(day !== 0 && day !== 6 && !isDisabled)]; // 일요일(0)과 토요일(6)을 제외한 날짜만 선택 가능
   	},
   
   	// 날짜 제한 관련
   	// 년도범위 년도 : 년도 형식 2021 : 2023이라면 2021~2023까지 활성
  	 yearRange: currentYear + ':' + (currentYear + 1),
   	// 최소 날짜 범위 년도, 달, 일로되어있음 지금은 23년8월17일로 되어있어서 8월18일부터 선택가능
   	minDate: new Date(currentYear, currentMonth, currentDate+1),
   	// 최대 날짜 범위 +숫자m은 달제한 +숫자w는 주제한
   	maxDate: "+3w",
   	
   	//지점값, 매니저값, 날짜 넘겨주고 비활성화할 시간을 넣을 AJAX호출
   	onSelect: function(selectedDate) {
		
   		var selectedStore = $j('#storelist').val();
   		var selectedManager = $j("#managerlist").val();
        // 여기서부터 데이트피커 AJAX처리
        $j.ajax({
            type: "GET",
            url: "getTime.aj",
            data: {"selectedStore":selectedStore, "selectedManager":selectedManager, "selectedDate":selectedDate},
            dataType: 'json',
            success: function(result) {
            	
            	// 변수 초기화 작업
                disabledTimes = [];
            	
            	// json 배열만큼 변수에 값추가 반복 첫번째값은 time1(17:00:00등) 두번째인자값은 time2(17:01:00)
            	for (var i = 0; i < result.length; i++) {
                    disabledTimes.push([result[i].time1, result[i].time2]);
                }
            	// 타임피커 선택을 가능하게하기 위해 disabled 해제
            	$j("#timepicker").removeAttr("disabled");
            	
                // 날짜가 제대로 입력되고 비활성화 할 시간이 적용되었다면 타임피커 호출
                $j('#timepicker').timepicker({
                  timeFormat: 'H:i',
                  step: 60,
                  minTime: '10:00', // 최소 시간
                  maxTime: '18:00', // 최대 시간
                  disableTextInput : true, //텍스트입력불가
                  listWidth : 1, //크기조정
                  disableTimeRanges: disabledTimes //비활성화할시간 변수에서 호출
                });
                
            }
        });
    },
   
	});
	 
    //가격계산에 대한 AJAX처리
    $j('#servicelist, #petlist, #weightlist, #managerlist').change(function() {
    	
        // 서비스 가격 계산을 위한 밸류값 가져오기
        var selectedService = $j("#servicelist").val();
        var selectedWeight = $j("#weightlist").val();
        
        //서비스와 무게따른 가격계산을 위한 값
        var selectedPrice = parseInt(selectedService) + parseInt(selectedWeight)-1;
        
        //견종에 따른 추가 계산을 위한 값
        var selectedPet = $j("#petlist").val();
        
        //직원에 따른 추가 계산을 위한 값
        var selectedManager = $j("#managerlist").val();
        
        //가격을 얻기 위한 AJAX 요청.
        if(!selectedService == "" && !selectedWeight == "" && !selectedPet == "" && !selectedManager == ""){
          	
        $j.ajax({
        	type: "GET",
            url: 'getPrice.aj',
            data: {"selectedPet": selectedPet, "selectedPrice":selectedPrice, "selectedManager":selectedManager}, // 선택된 값을 서버로 전송
            success: function(result) {
            	//포인트가 0이아니고 포인트 사용에 체크되어있으면 가격 계산
            	if(!point == "" && $('#pointcheck').prop('checked')){
            		result = result - point
            	} 
            	price.value = result;

            }
        });
        
        }
    });
    
  	//날짜선택에 대한 AJAX처리
    $j('#managerlist').change(function() {
		
    	//직원 선택시 예약 일자 초기화
		datepicker.value = "";
		timepicker.value ="";
		$j("#timepicker").attr('disabled','disabled');
		
		// 변수 초기화 작업
        disabledDates = [];
		
        // if문 및 지점번호 값 전송
        var selectedStore = $j('#storelist').val();
        var selectedService = $j("#servicelist").val();
        var selectedPet = $j("#petlist").val();
        var selectedWeight = $j("#weightlist").val();
        var selectedManager = $j("#managerlist").val();
        
        // 날짜 비활성화를 위한 AJAX 요청.
        if(!selectedStore == "" && !selectedService == "" && !selectedPet == "" && !selectedWeight == "" && !selectedManager == "")
        $j.ajax({
        	type: "GET",
            url: 'getDate.aj',
            data: {"selectedStore": selectedStore}, // 선택된 값을 서버로 전송
            dataType: 'json',
            success: function(result) {
            	$j("#datepicker").removeAttr("disabled");
            	disabledDates = result.map(function(item) {
                    return item.date;
                });
        
            }
        });
    });
  
});

</script>

	</body>
</html>
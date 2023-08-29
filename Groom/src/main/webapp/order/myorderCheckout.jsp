<%@page import="web.groom.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String name = request.getParameter("name");
String phone = request.getParameter("phone");
String storelist = request.getParameter("storelist");
String servicelist = request.getParameter("servicelist");
String datepicker = request.getParameter("datepicker");
String timepicker = request.getParameter("timepicker");
String price = request.getParameter("price");
String message = request.getParameter("message");
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
						<h2>예약내용 최종확인</h2>
					</div>
				</div>
				<form action="myorderCheckout.or" id="checkout" method="post">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<p>예약자명</p>
										<input type="text" class="form-control" id="name" name="name" value="<%=name%>" readonly>
									</div>
									<div class="form-group">
									    <p>연락처</p>
										<input type="text" class="form-control" id="phone" name="phone" value="<%=phone%>" readonly>
									</div>
								</div>
								<div class="col-md-3">
								    <div class="form-group">
								    	<p>선택 매장명</p>
										<input type="text" class="form-control" id="storelist" name="storelist" value="<%=storelist%>" readonly>
									</div>
									<div class="form-group">
										<p>선택 서비스명</p>
										<input type="text" class="form-control" id="servicelist" name="servicelist" value="<%=servicelist%>" readonly>
									</div>
										<p>선택 예약날짜</p>
									<div class="form-group">
										<input type="text" id="datepicker" name="datepicker" class="form-control" value="<%=datepicker%>" readonly>
									</div>
										<p>선택 예약시간</p>
									<div class="form-group">
										<input type="text" id="timepicker" name="timepicker" class="form-control" value="<%=timepicker%>" readonly>
									</div>
										<p>예약요금</p>
									<div class="form-group">
										<input type="text" id="price" name="price" class="form-control" value="<%=price%>" readonly>
									</div>
								</div>
								
								<div class="col-md-3">
									<p>기입하신 요구사항</p>
									<div class="form-group">
										<textarea id="message" name="message" cols="30" rows="7" class="form-control" readonly><%=message%></textarea>
									</div>
								
<!-- 									<div class="form-group"> -->
<!-- 										<input type="submit" class="btn btn-primary btn-md" value="예약하기"> -->
<!-- 									</div> -->
								</div>
						
							</div>
						</div>
						
					</div>
				</form>
			<div class="fh5co-more-contact">
			예약내용을 확인하십시오 위의내역으로 틀림 없습니까?
				<div class="fh5co-narrow-content">
					<div class="row">
						<div class="col-md-4">
							<button type="button" class="btn btn-primary my-class" id="call_api" onclick="callAPI()">카드 결제</button>
							<button type="button" class="btn btn-primary" id="call_api_kakao" onclick="callAPIKakao()">카카오페이 결제</button>
						</div>
						
					</div>
				</div>
			</div>
			
			</div>
				
		</div>
		
		</div>

	<!-- 아임포트 api 사용하기위한 api자바스크립트, J쿼리 호출 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>				
					
<script>
let val = "<%=price%>" //여기서 일단 가격 조정 추후에 DB에서 가져와야함?
let name = "<%=servicelist%>" // 서비스나 물건이름 여기서일단 설정
let payment = "" //페이먼트 설정 html5_inicis, kakaopay, naverco

function callAPI(){
	payment = "html5_inicis";
	confirmPayment();
}

function callAPIKakao(){
	payment = "kakaopay";
	confirmPayment();
}

function confirmPayment() {
	var confirmResult = confirm("결제를 진행하시겠습니까?");
	if (confirmResult) {
		callImportAPI();
	}
}

function callImportAPI() {
    
        var IMP = window.IMP; // 생략가능
        IMP.init('imp70038768'); 
        // 'IMP.init의 값은 내 식별코드를 넣어야함'
        // iamport 관리자 페이지 -> 결제연동 -> 내식별코드
        IMP.request_pay({
            pg: payment, // 페이먼트 어떤거 사용할지 명시 우선 변수에서 가져옴.
            /*  
                'naverco' : 네이버페이//네이버페이의 경우 별도의 KEY가 필요해서 까다로움
                'kakaopay':카카오페이
                html5_inicis':이니시스(웹표준결제)
                'danal':다날
                'payco':페이코
                */
            pay_method: 'card',
            /*  
                //카드 말고 다른거 쓸 필요가 있을까 싶음?
                'samsung':삼성페이, 
                'card':신용카드, 
                'trans':실시간계좌이체,
                'vbank':가상계좌,
                'phone':휴대폰소액결제 
            */
            merchant_uid: 'merchant_' + new Date().getTime(),
            /* 
                merchant_uid에 // 상점에서 생성한 고유 주문번호가 들어가야한다고 가이드에되어있는데 고유주문번호는 내DB에서쓰는건가?
                
             */
            name: name,
            //결제창에서 보여질 이름
            //코드레스나 다른팀에서 api사용하는코드 분석해서 DB에서 값을 가져올 필요가있음
            amount: val, 
            //가격 
//             buyer_email: 'iamport@siot.do',
//             buyer_name: 'itwillbsT2',
//             buyer_tel: '010-1234-5678',
//             buyer_addr: '부산광역시 부산진구 부전동',
//             buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'
            /*  
                모바일 결제시,
                결제가 끝나고 리디렉션될 URL 지정
                */
                
//           import 카카오페이 가이드 https://developers.portone.io/docs/ko/pg/simple/kakopay
        }, function (response) {
            console.log(response); //ajax처럼 콜백 성공유무
            if (response.success) {
                var msg = '결제완료시 내용 표시 가능 ';
                msg += '고유ID : ' + response.imp_uid;
                msg += '상점 거래ID : ' + response.merchant_uid;
                msg += '결제 금액 : ' + response.paid_amount;
                msg += '카드 승인번호 : ' + response.apply_num;
            } else {
                var msg = '결제실패 내용들어가는곳 : ';
                msg += response.error_msg;
            }
            alert(msg); // 최종메세지 출력
        });
    }
</script>

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
</body>
</html>
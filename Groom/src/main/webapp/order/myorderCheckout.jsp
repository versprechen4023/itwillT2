<%@page import="web.groom.dto.OrderinfoDTO"%>
<%@page import="web.groom.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

OrderinfoDTO orderInfo = (OrderinfoDTO)request.getAttribute("orderInfo");
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
<!-- 	css설정 -->
	<link rel="stylesheet" href="./css/myorder_gr.css">	
<body>
 <!-- 사이드바호출 -->
		<jsp:include page="../inc/aside.jsp"></jsp:include>   
	
<div id="fh5co-page">
	<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
<div id="fh5co-main">
<div class="fh5co-narrow-content">
	<h2>최종 결제내용</h2>

<form action="orderReservation.or" id="checkout" method="post">
<div class="order-form">
<!--   예약자정보 첫 번째 줄	 -->
<div class="order-container">
<div class="form-group">
	<p>예약자</p>
	<input type="text" class="form-control" id="name" name="name" value="<%=orderInfo.getU_name()%>" readonly>
</div>
<div class="form-group">
	<p>연락처</p>
	<input type="text" class="form-control" id="phone" name="phone" value="<%=orderInfo.getU_phone()%>" readonly>
</div>
<div class="form-group">
	<p>이메일</p>
	<input type="text" class="form-control form-control2" id="email" name="email" value="${param.email}" readonly>
</div>
</div><!-- 첫 번째 줄 끝 -->

<!--   상품정보 두 번째 줄	 -->	
<div class="order-container">
<div class="form-group">
	<p>매장</p>
	<input type="text" class="form-control" id="storelist" name="storelist" value="<%=orderInfo.getS_location()%>" readonly>
</div>
<div class="form-group">
	<p>견종</p>
	<input type="text" class="form-control" id="petlist" name="petlist" value="<%=orderInfo.getPet_size()%>" readonly>
</div>
<div class="form-group">
	<p>서비스</p>
	<input type="text" class="form-control" id="servicelist" name="servicelist" value="<%=orderInfo.getPro_name()%>" readonly>
</div>
<div class="form-group">
	<p>무게</p>
	<input type="text" class="form-control" id="weightlist" name="weightlist" value="<%=orderInfo.getPet_weight()%>" readonly>
</div>
</div><!--  두 번째 줄 끝  -->	

<!--  포인트, 디자이너, 시간선택 세 번째 줄  -->
<div class="order-container">
<div class="form-group">
	<p>사용 포인트</p>
	<input type="text" class="form-control" id="res_point" name="res_point" value="<%=orderInfo.getRes_point()%>" readonly>
</div>
<div class="form-group">
	<p>디자이너</p>
	<input type="text" class="form-control" id="managerlist" name="managerlist" value="<%=orderInfo.getEmp_name()%>" readonly>
</div>
<div class="form-group">
	<p>날짜</p>
	<input type="text" id="res_day" name="res_day" class="form-control" value="<%=orderInfo.getRes_day()%>" readonly>
</div>
<div class="form-group">
	<p>시간</p>
	<input type="text" id="res_time" name="res_time" class="form-control" value="<%=orderInfo.getRes_time()%>" readonly>
</div>
</div><!--  세 번째 줄 끝  -->	

<!--  요청사항, 금액, 버튼 네 번째 줄  -->
<div class="order-container">
<div class="form-group nomargin">
	<p>요청사항</p>
	<textarea id="res_u_req" name="res_u_req" cols="30" rows="7" class="form-control" readonly><%=orderInfo.getRes_u_req()%></textarea>
</div>
<div class="div2">
<div class="div3">
	<div class="form-group" style="margin-left: 270px;">
		<p class="text-black">결제 금액</p>
		<input type="text" class="form-control text-red" id="res_price" name="res_price" value="<%=orderInfo.getRes_price()%>" readonly>
	</div>
</div>
			<c:choose>
				<c:when test="${param.price == 0}">
					<div class="center-button">
						<button type="button" class="btn btn-primary my-class" id="call_api" onclick="callconfirm()">예약하기</button>
						<button type="button" class="btn btn-primary" id="cancel" onclick="backmain()">예약취소</button>
					</div>	
				</c:when>
				<c:otherwise> <!-- 아래 세 버튼 클래스변경 (btn btn-primary 제거) -->
					<div class="center-button">
						<button type="button" class="order-btn my-class" id="call_api" onclick="callAPI('html5_inicis')">카드 결제</button>
						<button type="button" class="order-btn order-btn2" id="call_api_kakao" onclick="callAPI('kakaopay')">카카오페이 결제</button>
						<button type="button" class="order-btn order-btn3" id="cancel" onclick="backmain()">결제취소</button>
					</div>
				</c:otherwise>
			</c:choose>
</div>
<!-- 		결제가 완료되면 서브밋되게 할거임 -->
<!-- 		<div class="form-group"> -->
<!-- 			<input type="submit" class="btn btn-primary btn-md" value="예약하기"> -->
<!-- 		</div> -->
</div>
					<input type="hidden" id="s_num" name="s_num" value="<%=orderInfo.getS_num()%>">
					<input type="hidden" id="pro_id1" name="pro_id1" value="<%=orderInfo.getPro_id1()%>">
					<input type="hidden" id="pro_id2" name="pro_id2" value="<%=orderInfo.getServeiceNum()%>">
					<input type="hidden" id="emp_num" name="emp_num" value="<%=orderInfo.getEmp_num()%>">
					<input type="hidden" id="u_point" name="u_point" value="${param.u_point}">
					<input type="hidden" id="res_method" name="res_method" value="">
				</div>
				</form>
		</div>
		</div>
		</div>

	<!-- 아임포트 api 사용하기위한 api자바스크립트, J쿼리 호출 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>				
					
<script>
let val = "<%=orderInfo.getRes_price()%>" //여기서 일단 가격 조정 추후에 DB에서 가져와야함?
let name = "<%=orderInfo.getPro_name()%>" // 서비스나 물건이름 여기서일단 설정
let payment = "" //페이먼트 설정 html5_inicis, kakaopay, naverco
let res_method = ""

function callAPI(pay){
	payment = pay;
	confirmPayment();
}

function backmain(){
	var confirmResult = confirm("결제를 취소하고 메인으로 돌아가시겠습니까?");
	if (confirmResult) {
		location.href='main.gr';
	}
}

function callconfirm(){
	var confirmResult = confirm("예약하시겠습니까?");
	if (confirmResult) {
		document.getElementById("checkout").submit();
	}
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
            merchant_uid: '고유주문번호 : ' + new Date().getTime(),
            /* 
                merchant_uid에 // 상점에서 생성한 고유 주문번호가 들어가야한다고 가이드에되어있는데 고유주문번호는 내DB에서쓰는건가?
                		//보기에는 시간만 보임
                
             */
            name: name,
            //결제창에서 보여질 이름 일단 DB에서 가져옴
            amount: val, 
            //가격 
            buyer_email: '${param.email}',
            buyer_name: '<%=orderInfo.getU_name()%>',
//             buyer_tel: '010-1234-5678',
//             buyer_addr: '부산광역시 부산진구 부전동',
//             buyer_postcode: '123-456',
            m_redirect_url: 'mypage.my'
            /*  
                모바일 결제시,
                결제가 끝나고 리디렉션될 URL 지정
                */
                
//           import 카카오페이 가이드 https://developers.portone.io/docs/ko/pg/simple/kakopay
        }, function (response) {
            console.log(response); //ajax처럼 콜백 성공유무
            if (response.success) {
//                 var msg = '결제완료시 내용 표시 가능 ';
//                 msg += '고유ID : ' + response.imp_uid;
//                 msg += '상점 거래ID : ' + response.merchant_uid;
//                 msg += '결제 금액 : ' + response.paid_amount;
//                 msg += '카드 승인번호 : ' + response.apply_num;
            	if(payment == "html5_inicis"){
                	res_method = "카드결제";
                } else if(payment == "kakaopay"){
                	res_method = "카카오페이 결제"
                }
            	
            	//결제 수단 변수 삽입
                document.getElementById("res_method").value = res_method;
            	
             	// submit 실행
                document.getElementById("checkout").submit();
            	
            } else {
//                 var msg = '결제실패 내용들어가는곳 : ';
//                 msg += response.error_msg;
            }
//             alert(msg); // 최종메세지 출력
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
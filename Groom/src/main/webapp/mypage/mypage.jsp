<%@page import="web.groom.dto.MypageDTO"%>
<%@page import="web.groom.dto.MemberDTO"%>
<%@page import="web.groom.dto.OrderReservationDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
MypageDTO mypageInfo = (MypageDTO) request.getAttribute("mypageInfo");
// MypageDTO mypagepetInfo = (MypageDTO)request.getAttribute("mypagepetInfo"); 기존의 펫리스트는 이제 mypetList에서 호출
List<MypageDTO> mypetList = (List<MypageDTO>) request.getAttribute("mypetList");

List<OrderReservationDTO> reservationList = (List<OrderReservationDTO>) request.getAttribute("reservationList");

//아래 코드는 페이징코드
int itemsPerPage = 5; // 페이지당 아이템 수
int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
int startIndex = (currentPage - 1) * itemsPerPage;
int endIndex = Math.min(startIndex + itemsPerPage, reservationList.size());
int totalPages = (int) Math.ceil((double) reservationList.size() / itemsPerPage);

List<OrderReservationDTO> visibleItems = reservationList.subList(startIndex, endIndex);
%>



<!DOCTYPE html>
<html>
<head>
<!-- 헤드호출 -->
<jsp:include page="../inc/head.jsp"></jsp:include>
</head>
<body>
	<!-- 	mypage css 추가 -->
	<link rel="stylesheet" href="./css/mypage_gr.css">

	<link rel="stylesheet" href="./css/style.css">
	<link rel="stylesheet" href="./css/aside_gr.css">


	<div id="fh5co-page">
		<div id="fh5co-main">
			<!-- 사이드바호출 -->
			<jsp:include page="../inc/aside.jsp"></jsp:include>

			<h2 class="myh2">내 정보</h2>

			<hr>

			<div class="container">
				<div class="table-container1">

					<table class="user">
						<p class="h">회원 정보</p>
						<tr>
							<td class="bold-cell">아이디</td>
							<td><%=mypageInfo.getId()%></td>
						</tr>
						<tr>
							<td class="bold-cell">이름</td>
							<td><%=mypageInfo.getName()%></td>
						</tr>
						<tr>
							<td class="bold-cell">이메일</td>
							<td><%=mypageInfo.getEmail()%></td>
						</tr>
						<tr>
							<td class="bold-cell">전화번호</td>
							<td><%=mypageInfo.getPhone()%></td>
						</tr>
						<tr>
							<td class="bold-cell">나의 리뷰</td>
							<td><input type="button" value="리뷰관리"
								style="background-color: rgb(232, 232, 232); color: dodgerblue; border: none; border-radius: 10px;"
								onclick="location.href='myReviewList.re?u_num=<%=mypageInfo.getNum()%>'"></td>

						</tr>
						<tr>
							<td class="bold-cell">포인트</td>
							<td><%=mypageInfo.getPoint()%></td>
						</tr>
					</table>
					<input type="button" value="회원탈퇴" class="mbtn mbtn-left"
						onclick="location.href='withdraw.my'"> <input
						type="button" value="비밀번호 변경" class="mbtn mbtn-center"
						onclick="location.href='readyresetpass.my'"> <input
						type="button" value="정보수정" class="mbtn mbtn-right"
						onclick="location.href='modifyinfo.my'">


				</div>

				<div class="table-container2">
					<table class="pet">
						<p class="h">대표 반려동물 정보</p>
						<!--     mypetList가 null인지 검증 null 이 아니라면 맨위에있는 대표반려동물을 출력 -->

						<c:if test="${not empty mypetList}">
							<tr>
								<td class="bold-cell">이름</td>
								<td>${mypetList[0].petName}</td>
							</tr>
							<tr>
								<td class="bold-cell">품종</td>
								<td>${mypetList[0].petBreed}</td>
							</tr>
							<tr>
								<td class="bold-cell">성별</td>
								<td>${mypetList[0].petGender}</td>
							</tr>
							<tr>
								<td class="bold-cell">중성화 여부</td>
								<td>${mypetList[0].petNeuter}</td>
							</tr>
							<tr>
								<td class="bold-cell">특이사항</td>
								<td>${mypetList[0].petComment}</td>
							</tr>
						</c:if>

						<!-- mypetList가 null인지 검증 null이라면 입력해주세요 등 출력 -->

						<c:if test="${empty mypetList}">
							<tr>
								<td class="bold-cell">이름</td>
								<td>이름을 입력해주세요</td>
							</tr>
							<tr>
								<td class="bold-cell">품종</td>
								<td>품종을 등록해주세요</td>
							</tr>
							<tr>
								<td class="bold-cell">성별</td>
								<td>성별을 등록해주세요</td>
							</tr>
							<tr>
								<td class="bold-cell">중성화 여부</td>
								<td>중성화 여부를 등록해주세요</td>
							</tr>
							<tr>
								<td class="bold-cell">특이사항</td>
								<td>특이사항을 입력해주세요</td>
							</tr>
						</c:if>

					</table>
				</div>



				<div class="table-container3">
					<br>
					<p class="h">
						반려동물 정보 <input type="button" value=" + " class="mbtn"
							onclick="location.href='insertmypet.my'">
					<table class="petlist">

						<tr>
							<td class="bold-cell">이름</td>
							<td class="bold-cell">품종</td>
							<td class="bold-cell">성별</td>
							<td class="bold-cell">중성화여부</td>
							<td class="bold-cell">특이사항</td>
						</tr>

						<%
						for (MypageDTO mypageDTO : mypetList) {
						%>
						<tr>
							<td><%=mypageDTO.getPetName()%></td>
							<td><%=mypageDTO.getPetBreed()%></td>
							<td><%=mypageDTO.getPetGender()%></td>
							<td><%=mypageDTO.getPetNeuter()%></td>
							<td><%=mypageDTO.getPetComment()%></td>
						</tr>
						<%
						}
						%>
					</table>
					<br>
					<br>
					<!-- 펫이 있을때만 표시 -->
					<c:if test="${not empty mypetList}">
						<form action="updatemypet.my" id="updatepet" method="post"
							class="pet-form">
							<!-- 선택 상자와 버튼을 가로로 배치하고, 내용을 수직으로 중앙 정렬 -->
							<div class="selectNbtn">
								내 반려동물: <select id="petlist" name="petlist">
									<%
									for (MypageDTO mypageDTO : mypetList) {
									%>
									<option value="<%=mypageDTO.getPetNum()%>"><%=mypageDTO.getPetName()%></option>
									<%
									}
									%>
								</select>
								<!-- 정보수정 버튼 -->

								<input type="submit" value="정보수정/삭제" class="mbtn">

							</div>
						</form>
					</c:if>


				</div>




				<div class="table-container4">
					<p class="h">예약내역
					<table class="reservation">

						<tr>
							<td class="bold-cell">예약<br>번호
							</td>
							<td class="bold-cell">날짜/시간</td>
							<td class="bold-cell">선택메뉴</td>
							<td class="bold-cell">매장</td>
							<td class="bold-cell">담당직원</td>
							<td class="bold-cell">상품<br>가격
							</td>
							<td class="bold-cell">사용한<br>포인트
							</td>
							<td class="bold-cell">최종<br>결제금액
							</td>
							<td class="bold-cell">상태</td>
							<td style="background: #E2E2E2;">예약<br>취소
							</td>
							<td style="background: #E2E2E2;">일정<br>변경
							</td>
							<td style="background: #E2E2E2;">리뷰<br>작성
							</td>
							<td class="bold-cell">포인트<br>지급여부
							</td>
							<td class="bold-cell">예약까지<br>남은시간
							</td>
						</tr>


						<%
						for (OrderReservationDTO orderReservationDTO : visibleItems) {
							String res_day = orderReservationDTO.getRes_day(); // 예약날짜
							String format_res_day = res_day.replace("-", ".").substring(2);
							String res_time = orderReservationDTO.getRes_time(); // 예약시간
							String format_res_time = res_time.substring(0, 5);
							String dayAndTime = res_day + " " + res_time;

							// //enum > 문자
							// 	String s_location = orderReservationDTO.getS_location();
							// 		String location = "";
							// 	if (s_location.equals("A")) {
							// 	    location = "서면점";
							// 	} else if (s_location.equals("B")) {
							// 	    location = "명지점";
							// 	} else if (s_location.equals("C")) {
							// 	    location = "율하점";
							// 	} else {
							// 	    location = "알 수 없음";
							// 	}

							// 	String emp_grade = orderReservationDTO.getEmp_grade();
							// 	String grade = "";
							// 	if (emp_grade.equals("A")) {
							// 		grade = "원장";
							// 	} else if (emp_grade.equals("B")) {
							// 		grade = "실장";
							// 	} else if (emp_grade.equals("C")) {
							// 		grade = "수석";
							// 	} else {
							// 		grade = "알 수 없음";
							// 	}

							int res_status = orderReservationDTO.getRes_status();
							String status = "";
							if (res_status == 0) {
								status = "예약대기";
							} else if (res_status == 1) {
								status = "예약완료";
							} else if (res_status == 2) {
								status = "예약취소";
							} else {
								status = "알 수 없음";
							}

							int res_point_status = orderReservationDTO.getRes_point_status();
							String point_status = "";
							if (res_point_status == 0) {
								point_status = "미지급";
							} else if (res_point_status == 1) {
								point_status = "완료!";
							} else {
								point_status = "오류";
							}
						%>
						<tr>
							<td><%=orderReservationDTO.getRes_num()%></td>
							<td><%=format_res_day%> <%=format_res_time%></td>
							<td class="text-left">[<%=orderReservationDTO.getPro_name()%>]
								<%=orderReservationDTO.getPet_size()%> <%=orderReservationDTO.getPet_weight()%></td>
							<td><%=orderReservationDTO.getS_location()%></td>
							<td><%=orderReservationDTO.getEmp_grade()%> <%=orderReservationDTO.getEmp_name()%></td>

							<td><%=orderReservationDTO.getRes_price() + orderReservationDTO.getRes_point()%></td>
							<td><%=orderReservationDTO.getRes_point()%></td>
							<td style="color: red;"><%=orderReservationDTO.getRes_price()%></td>
							<td class="status font-bold"><%=status%></td>
							<td><input type="button" value="취소" class="status-button"
								onclick="isCanceled(<%=orderReservationDTO.getRes_num()%>,<%=orderReservationDTO.getRes_status()%>)">
							</td>
							<td><input type="button" value="변경" class="status-button"
								onclick="isCanChange('<%=orderReservationDTO.getRes_day()%>', '<%=orderReservationDTO.getRes_time()%>', <%=orderReservationDTO.getRes_num()%>, 
    		 <%=orderReservationDTO.getS_num()%>, <%=orderReservationDTO.getEmp_num()%>,<%=orderReservationDTO.getRes_status()%>)">
							</td>
							<td><input type="button" value="작성" class="status-button"
								onclick="openReviewWrite(<%=mypageInfo.getNum() %>,<%=orderReservationDTO.getRes_num()%>,
  		<%=orderReservationDTO.getRes_status()%>)">
							</td>
							<td><%=point_status%></td>
							<td id=<%=orderReservationDTO.getRes_num()%>
								data-value="<%=dayAndTime%>" data-value2="<%=res_status%>"
								class="myDay"></td>
						</tr>
						<%
						}
						%>
					</table>
					<!-- 페이징 코드 5개씩 나눠서 페이징 -->
					<div class="resCheck-next">
						<%
						if (currentPage > 1) {
						%>
						<a href="?page=<%=currentPage - 1%>" class="pgL"><span
							class="m-tcol-c">&lt;</span></a>
						<%
						}
						%>
						<%
						int startPage = ((currentPage - 1) / 5) * 5 + 1; // 현재 페이지 그룹의 시작 페이지 계산
						int endPage = Math.min(startPage + 4, totalPages); // 현재 페이지 그룹의 마지막 페이지 계산
						for (int i = startPage; i <= endPage; i++) {
						%>
						<a href="?page=<%=i%>"
							<%=(i == currentPage) ? "class='review-active'" : ""%>><%=i%></a>
						<%
						}
						%>
						<%
						if (currentPage < totalPages) {
						%>
						<a href="?page=<%=currentPage + 1%>" class="pgR"><span
							class="m-tcol-c">&gt;</span></a>
						<%
						}
						%>
					</div>
				</div>

			</div>

		</div>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">

// 예약 일자 남은 시간 보여주는 함수
function RemainingTime() {
	  //td 선택자지정
	  var myDay = document.querySelectorAll('.myDay');
	  myDay.forEach(function(myDay) {
		  // 이너 html을 위한 변수에 td id값 할당
		  var id = myDay.id;
		  //조건 문을 위한 예약상태값을 받아서 할당
		  var myIf = myDay.getAttribute('data-value2');
		  // 예약중일때만 출력함
		  if(myIf == 0){
		  	// 시간 계산을 위한 td에서 밸류값을 받아서 할당
		  	var value = myDay.getAttribute('data-value');
		  	// 현재 일자 변수에 할당
		  	var currentDate = new Date();
		  	// 계산할 일자 변수에 할당
		  	var targetDate = new Date(value);
		  
		  	// 현재 일자와 받아온 일자를 계산
		  	var timeDifference = targetDate - currentDate;
		  
		 	// timeDifference가 음수인 경우(지나간 일자인 경우) 0으로 설정
		  	if (timeDifference < 0) {
			  	// 시간이 다되었을 경우 아무것도 안보임
			  	document.getElementById(id).innerHTML = "";
		  	} else {
		  
		  	// 밀리초 형태를 다시 포맷형식에 맞게끔 변환
		  	var days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
		  	var hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		  	var minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
		  	var seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);
		  
		  	// 이너html로 텍스트값 출력
		  	document.getElementById(id).innerHTML = days+"일"+hours+"시간"+minutes+"분"+seconds+"초";
		  	}
		  }
	  });
}
// 일자 보이게 함수 최초기동
RemainingTime();
// 이후 1초에 한번씩 함수 반복 실행
setInterval(RemainingTime, 1000);
// end_of_RemainingTime

// 리뷰작성 버튼. 예약완료시에만 작성 가능.
function openReviewWrite(u_num, res_num, res_status) {
    if (res_status == 1) {
        var url = 'reviewWrite.re?u_num=' + u_num + '&res_num=' + res_num;
        var newWindow = window.open(url, '_blank', 'width=630px, height=940px');
    } else {
        alert("예약완료 시에만 리뷰 작성이 가능합니다.")
    }
}

//예약 취소 AJAX처리
function isCanceled(res_num, res_status) {
// alert (res_num);	
//alert(res_status);
if (res_status == 0) {
$.ajax({
  type: "GET",
  url: 'cancelRes.aj',
  data: {"res_num": res_num}, // 선택된 값을 서버로 전송
  success: function(data) {
	  const result = $.trim(data);
	  if (result == "true") {
		alert("예약취소되었습니다.");
		  location.reload();
	}else if(result == "false"){
		alert("실패했습니다.");
		
	}

  }
  
});// end ajax
}else{
	alert("예약 대기 상태일 경우만 예약 취소가 가능합니다.")
}
}//function

function isCanChange(userDate, userTime, res_num, s_num, emp_num,res_status) {
	//alert(res_status)
if (res_status == 0) {
	

	// 예약 일정 변경 유무 AJAX처리
	$.ajax({
	  type: "GET",
	  url: 'getChange.aj',
	  data: {"userDate": userDate, "userTime": userTime}, // 선택된 값을 서버로 전송
	  success: function(data) {
		  const result = $.trim(data);
		  
		  
		  if(result=="true"){
// 		  location.href = 'changeRes.my?res_num='+res_num+'&s_num='+s_num+'&emp_num='+emp_num;
		  window.open('changeRes.my?res_num='+res_num+'&s_num='+s_num+'&emp_num='+emp_num, '_blank', 'width=360px, height=360px, left=600px, top=300px');
		  
 		  }else if ( result=="false"){
 		  alert("예약시간까지 2시간 미만일경우 예약 일정을 변경 하실 수 없습니다");
 		  }
	  }
	
	});// end ajax
}else {
	alert("예약 대기 상태일 경우만 일정 변경이 가능합니다.")
}
}// end function
</script>
</body>
</html>
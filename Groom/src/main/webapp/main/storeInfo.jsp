<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<!-- 헤드호출 -->
	<jsp:include page="../inc/head.jsp"></jsp:include>

	</head>
	
	<!-- css추가  -->
	<link rel="stylesheet" href="./css/about_gr.css">
	
	<body>
	<div id="fh5co-page">
		<!-- 사이드바호출 -->
		<jsp:include page="../inc/aside.jsp"></jsp:include>

<!-- 매장 안내 -->

<div id="fh5co-main">
    <div class="fh5co-narrow-content">
        <h1 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft" style="font-size: 30px;">매장 안내</h1>
        <div class="row">
            <div style="padding-left: 190px; padding-top: 30px" class="this">
            
<!-- 서면점-->
                <div style="display: inline-block; vertical-align: top; margin-right: 110px;">
                
                
                    <!-- 사진 -->
                    <img src="./images/img-1.jpg" style="display: block; width: 250px; height: 320px; padding-bottom: 20px">
                    
                    
                    <div style="display: flex; align-items: center;">
                        <h1 style="font-size: 21px; margin-left: 8px; margin-top: 17px; margin-bottom: 20px; font-weight: bold;">서면점</h1>
                       
                       
                    <!-- 리뷰보기 버튼 -->
                        <button type="button" style="background-color: #ccc; color: white; border: none;
                                border-radius: 5px; padding: 6px 11px; cursor: pointer; margin-top: 12px; margin-left: 100px;">리뷰보기</button>
                    </div>
                    
                    
                    <!-- 가로줄 -->
                    <hr style="width: 260px; border-color: black; border-width: 1px; margin-top: 3px; margin-left: -5px;">
                    
                    
                    <!-- 지점 정보 -->
                    <ul>
                        <li style="padding: 2px; color: black;">운영시간 10:00-18:00</li>
                        <li style="padding: 2px; color: black;">월요일 휴무</li>
                        <li style="padding: 2px; color: black;">010-1111-1234</li>
                        <li style="padding: 2px; color: black;">부산광역시 부산진구 동천로</li>
                    </ul>
                </div>
                
                
<!-- 명지점-->
                      <div style="display: inline-block; vertical-align: top; margin-right: 110px;">
                      
                      
                    <!-- 사진 -->
                    <img src="./images/img-1.jpg" style="display: block; width: 250px; height: 320px; padding-bottom: 20px">
                    
                    
                    <div style="display: flex; align-items: center;">
                        <h1 style="font-size: 21px; margin-left: 8px; margin-top: 17px; margin-bottom: 20px; font-weight: bold;">명지점</h1>
                        
                        
                    <!-- 리뷰보기 버튼 -->
                        <button type="button" style="background-color: #ccc; color: white; border: none;
                               border-radius: 5px; padding: 6px 11px; cursor: pointer; margin-top: 12px; margin-left: 100px;">리뷰보기</button>
                    </div>
                    
                    
                    <!-- 가로줄 -->
                    <hr style="width: 260px; border-color: black; border-width: 1px; margin-top: 3px; margin-left: -5px;">
                    
                    
                    <!-- 지점 정보 -->
                    <ul>
                        <li style="padding: 2px; color: black;">운영시간 10:00-18:00</li>
                        <li style="padding: 2px; color: black;">월요일 휴무</li>
                        <li style="padding: 2px; color: black;">010-1111-1234</li>
                        <li style="padding: 2px; color: black;">부산광역시 부산진구 동천로</li>
                    </ul>
                </div>

                
<!-- 율하점-->
               <div style="display: inline-block; vertical-align: top; margin-right: 105px;">
               
               
                    <!-- 사진 -->
                    <img src="./images/img-1.jpg" style="display: block; width: 250px; height: 320px; padding-bottom: 20px">
                    
                    
                    <div style="display: flex; align-items: center;">
                        <h1 style="font-size: 21px; margin-left: 8px; margin-top: 17px; margin-bottom: 20px; font-weight: bold;">율하점</h1>
                        
                        
                    <!-- 리뷰보기 버튼 -->   
                        <button type="button" style="background-color: #ccc; color: white; border: none;
                               border-radius: 5px; padding: 6px 11px; cursor: pointer; margin-top: 12px; margin-left: 100px;">리뷰보기</button>
                    </div>
                    
                    
                    <!-- 가로줄 -->
                    <hr style="width: 260px; border-color: black; border-width: 1px; margin-top: 3px; margin-left: -5px;">
                    
                    
                    <!-- 지점 정보 -->
                    <ul>
                        <li style="padding: 2px; color: black;">운영시간 10:00-18:00</li>
                        <li style="padding: 2px; color: black;">월요일 휴무</li>
                        <li style="padding: 2px; color: black;">010-1111-1234</li>
                        <li style="padding: 2px; color: black;">부산광역시 부산진구 동천로</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
  
    
<!--  예약하기 버튼 -->

    <div style="display: flex; justify-content: center; align-items: center; height: 10vh;">
    <button type="button" style="background-color: black; color: white; border: none; margin-left: -50px;
           border-radius: 5px; padding: 14px 9px; cursor: pointer; width: 1000px; font-size: 15px; font-weight: bold;">예약하기</button>
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

	</body>
</html>
	
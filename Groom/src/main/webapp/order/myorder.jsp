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
	
	<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<!-- 사이드바호출 -->
		<jsp:include page="../inc/aside.jsp"></jsp:include>

		<div id="fh5co-main">
			<div class="fh5co-narrow-content animate-box" data-animate-effect="fadeInLeft">
				
				<div class="row">
					<div class="col-md-4">
						<h2>Get In Touch</h2>
					</div>
				</div>
				<form action="">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="예약자명">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="연락처">
									</div>
								</div>
								<div class="col-md-3">
								    <div class="form-group">
										<select class="" id="list" name="list">
											<option value="test">매장을 선택하세요</option>
											<option value="A">서면점</option>
											<option value="B">김해점</option>
										</select>
									</div>
									<div class="form-group">
										<select class="" id="list" name="list">
											<option value="test">예약할 서비스를 선택하세요</option>
											<option value="A">[미용]대형견 15KG</option>
										</select>
									</div>
									<div class="form-group">
										<input type="text" id="datepicker" name="datepicker" class="form-control" placeholder="날짜">
									</div>
									<div class="form-group">
										<input type="text" id="timepicker" name="timepicker" class="form-control" placeholder="시간">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="예상금액">
									</div>
								</div>
								
								<div class="col-md-3">
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
	
    <!-- 타임피커 관련 J쿼리 호출 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<script type="text/javascript">
	//기존 템플릿 J쿼리충돌 해결 함수
    jQuery.noConflict();

	</script>

	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SBank</title>
<script type="text/javascript">
function doWrite() {
	let f = document.wForm;
	
	
	balance=Number(f.balance.value)
	
	if (balance < 0) {
		alert('0원 혹은 양수를 입력하셔야 합니다.');
		f.balance.focus();
		return false;
	}
	
	
	if (f.pwd.value == '') {
		alert('비밀번호를 입력하세요');
		f.pwd.focus();
		return false;
	}
	
	
	return true;
}
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by freehtml5.co" />
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="freehtml5.co" />

<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700"
	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="/Bank/css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="/Bank/css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="/Bank/css/bootstrap.css">

<!-- Magnific Popup -->
<link rel="stylesheet" href="/Bank/css/magnific-popup.css">

<!-- Flexslider  -->
<link rel="stylesheet" href="/Bank/css/flexslider.css">

<!-- Theme style  -->
<link rel="stylesheet" href="/Bank/css/style.css">

<!-- Modernizr JS -->
<script src="/Bank/js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

</head>
<body>

	<div class="fh5co-loader"></div>

	<div id="page">

		<header>
			<jsp:include page="/jsp/include/topmenu.jsp" />
		</header>




		<!-- <div class="container-wrap" style="height: 100px;"> -->
		<!-- 			<aside id="fh5co-hero" style="background-image: url(/Bank/images/img_bg_1.jpg);">
					<h1 style="text-align: center;">인덱스</h1>
				<div class="container-fluid">
				</div>
			</aside>     -->
		<!-- <br> <br><br> <br> -->
		<div class="container-wrap">
			<div id="fh5co-hero"
				style="background-image: url(/Bank/images/img_bg_1.jpg); opacity: 0.4 !important;">
				<div id="fh5co-services"
					style="height: 600px; border-color: fuchsia;">
					<div class="row" style="height: 570px; border-color: fuchsia;">
						<!-- row에 대한 css 주석처리해둠! brootstrap.css파일 -->
						<div align="center">
							<!-- 여기 고고자아 -->
							<h3>오픈뱅킹 - 계좌 연동</h3>
							<form action="<%=request.getContextPath()%>/insertAccountProcess.do" method="post" onsubmit="return doWrite()"
								name="wForm">
								<table border="1" style="width: 80%">
									<tr>
										<th width="23%">아이디</th>
										<td>${userVO.id}</td>
										<!-- name=value 로 날아가기 때문에 나중에 어떤건지 알아보기 위해선 name필요 -->
									</tr>
									<tr>
										<th width="23%">개설은행</th>
										<td><input type="text" name="bank_name" size="100%"></td>
									</tr>
									<tr>
										<th width="23%">계좌번호</th>
										<td><input type="text" name="account_num" size="100%" placeholder="- 기호없이 숫자만 입력해주세요"></td>
									</tr>
									<tr>
										<th width="23%">잔액</th>
										<td><input type="text" name="balance" size="100%"></td>
									</tr>
									<tr>
										<th width="23%">계좌 별명</th>
										<td><input type="text" name="nick_name" size="100%"></td>
									</tr>
									<input type="hidden" name="id" value="${userVO.id}">
								</table><br> 
								<input type="submit" value="등록"> 
								<input type="button" value="목록" onclick="doList()" id="btnList">
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>


		<footer>
			<%@ include file="/jsp/include/footer.jsp"%>
		</footer>



		<div class="gototop js-top">
			<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
		</div>
		<!-- </div> -->
		<!-- END container-wrap -->


	</div>
	<!-- jQuery -->
	<script src="/Bank/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/Bank/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="/Bank/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/Bank/js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="/Bank/js/jquery.flexslider-min.js"></script>
	<!-- Magnific Popup -->
	<script src="/Bank/js/jquery.magnific-popup.min.js"></script>
	<script src="/Bank/js/magnific-popup-options.js"></script>
	<!-- Counters -->
	<script src="/Bank/js/jquery.countTo.js"></script>
	<!-- Main -->
	<script src="/Bank/js/main.js"></script>
</body>
</html>
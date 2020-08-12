<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SBank</title>
<script type="text/javascript">
	window.onload = function() {
		account_num= <%= request.getParameter("account_num") %>;
		//alert("account_num : "+account_num);
		let httpRequest = null;
		httpRequest = getXMLHttpRequest();
		httpRequest.onreadystatechange = callbackFunc;
		//httpRequest.open('POST', '/Bank/jsp/account/checkbalacne.jsp', true);
		httpRequest.open('POST', '/Bank/checkbalacne.do', true);
		httpRequest.setRequestHeader('content-type','application/x-www-form-urlencoded');
		httpRequest.send('account_num='+account_num);
		let chkMsg1 = document.getElementById("chkMsg1");
		$("#chkMsg2").text(httpRequest.responseText);

		function getXMLHttpRequest() {

			if (window.XMLHttpRequest) {
				return new XMLHttpRequest();
			}
			if (window.ActiveXObject) {
				return new ActiveXObject("Microsoft.XMLHTTP");
			}

			return null;
		}
		function callbackFunc() {
			let msg = '';
			switch (httpRequest.readyState) {
			case 1:
				msg += 'Loading...\n';
				break;
			case 2:
				msg += 'Loaded...\n';
				break;
			case 3:
				msg += 'Interactive...\n';
				break;
			case 4:
				msg += 'Complete...\n';
				// 서버 응답 후 상태코드 확인
				if (httpRequest.status == 200) {
					msg += '웹서버에서 정상적으로 수행완료...\n';
					msg += '실행결과 : ' + httpRequest.responseText + '\n';
					$("#chkMsg1").text(httpRequest.responseText);
					$("#chkMsg2").text(httpRequest.responseText);
				} else {
					msg += '웹서버에서 오류 발생...\n';
					msg += '오류코드 : ' + httpRequest.status + '\n';
					msg += '오류내용 : ' + httpRequest.statusText + '\n';
				}
				break;
			}
			debugTrace(msg);
		}
		function debugTrace(msg) {
			let debug = document.getElementById("debug");
			//debug.value += msg;
		}
		function sendProcess(method, param) {
			let msg = "==================================================================\n";
			msg += method + "/Bank/jsp/user/checkid.jsp?name=" + param + '\n';
			debugTrace(msg);

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = callbackFunc;

			let url = "param2.jsp";
			param = encodeURIComponent(param); // 익스플로러에서 한글 동작하지 않아서 인코딩해주기
			let args = 'name=' + param;
			if (method == "GET") {
				httpRequest.open(method, url + '?' + args, true);
				httpRequest.send(null);
			} else if (method == "POST") {
				httpRequest.open(method, url, true);
				httpRequest.setRequestHeader('content-type',
						'application/x-www-form-urlencoded');
				//form-data 형식으로 보내기 위해여 content-type수정. 이 부분 하지 않으면 Request Payload 방식이다.
				httpRequest.send(args);
			}

		}
		function keyevent2(account_num) {
			var keycode = event.keyCode.value;
			//alert(account_num);
			var checkbalance = document.getElementById("id").value;
			httpRequest = getXMLHttpRequest();

			httpRequest.onreadystatechange = callbackFunc;
			httpRequest.open('POST', '/Bank/jsp/user/checkid.jsp', true);
			httpRequest.setRequestHeader('content-type',
					'application/x-www-form-urlencoded');
			//form-data 형식으로 보내기 위해여 content-type수정. 이 부분 하지 않으면 Request Payload 방식이다.
			httpRequest.send('checkid=' + checkid);
			//alert(httpRequest.responseText);
			let chkMsg1 = document.getElementById("chkMsg1");
			//msg=httpRequest.responseText;
			$("#chkMsg2").text(httpRequest.responseText);
			//chkMsg2.html += msg;

		}

		var formm = document.getElementById("formm");
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
							<h3>계좌이체</h3>
							<form action="<%=request.getContextPath()%>/transferProcess.do" method="post" id="formm">
							<table border="1" style="width: 80%">
							<tr>
								<th>이체 할 계좌</th>
								<td><input type="text" name="bank_name" style="width: 50%;" placeholder="이체할 계좌번호를 입력하세요"></td>
							</tr>
							<tr>
								<th>이체 할 금액</th>
								<td>
									<%-- <input type="text" id="check" name="withdrawbalance" placeholder="출금할 금액을 입력하세요" onfocus="keyevent2(<%= request.getParameter("account_num") %>);" /> --%>
									<input type="text" id="check" name="withdrawbalance" style="width: 50%;" placeholder="출금할 금액을 입력하세요" blur="keyevent2(<%= request.getParameter("account_num") %>);" />
									<span id = "chkMsg2" name = "chkMsg2"></span>
								</td>
							</tr>
								<input type="hidden" name="account_num" value="<%= request.getParameter("account_num") %>">
							</table>
								<input type="submit" value="이체하기">
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
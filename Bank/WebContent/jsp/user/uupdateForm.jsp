<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SBank</title>
<script type="text/javascript">
function initt(){
	setComboValue("${user.email_domain}");


}
function setComboValue(val) 
{	
	//alert(val);
    var selectMail = document.getElementById('email_domain'); // select 아이디를 가져온다.
    for (i = 0, j = selectMail.length; i < j; i++)  // select 하단 option 수만큼 반복문 돌린다.
    {
        if (selectMail.options[i].value == val)  // 입력된값과 option의 value가 같은지 비교
        {
            selectMail.options[i].selected = true; // 같은경우라면 체크되도록 한다.
            break;
        }
    }
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
<body onload="initt()">

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
							<h3>개인정보 수정</h3>
							<form action="<%=request.getContextPath()%>/uupdateProcess.do" method="post" onsubmit="return doWrite()"
								name="wForm">
								<table border="1" style="width: 80%">
									<tr>
										<th width="23%">아이디</th>
										<td>${user.id}</td>
										<!-- name=value 로 날아가기 때문에 나중에 어떤건지 알아보기 위해선 name필요 -->
									</tr>
									<tr>
										<th width="23%">비밀번호</th>
										<td><input type="text" name="pwd" size="100%" value="${user.password}"></td>
									</tr>
									<tr>
										<th width="23%">이름</th>
										<td><input type="text" name="name" size="100%" value="${user.name}"></td>
									</tr>
									<tr>
										<th width="23%">이메일</th>
										<td><input type="text" name="email_id" size="45%" value="${user.email_id}">@
											<select name="email_domain" id="email_domain" >
     											<option value="naver.com">naver.com</option>
     											<option value="daum.net">daum.net</option>
     											<option value="google.com">google.com</option>
    										</select>
										</td>
									</tr>
									<tr>
										<th width="23%">핸드폰 번호</th>
										<td>
											<input type="text" name="tel1" size="30%" value="${user.tel1}"> - 
											<input type="text" name="tel2" size="30%" value="${user.tel2}"> - 
											<input type="text" name="tel3" size="30%" value="${user.tel3}">
										</td>
									</tr>
									<tr>
										<th width="23%">우편번호</th>
										<td><input type="text" name="post" size="100%" value="${user.post}"></td>
									</tr>
									<tr>
										<th width="23%">집도로명주소</th>
										<td><input type="text" name="basic_addr" size="100%" value="${user.basic_addr}"></td>
									</tr>
									<tr>
										<th width="23%">집상세주소</th>
										<td><input type="text" name="detail_addr" size="100%" value="${user.detail_addr}"></td>
									</tr>
								</table>
								<br> <input type="submit" value="등록"> 
								<input type="hidden" name="id" value= "${ userVO.id }" >
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
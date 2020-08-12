<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SBank</title>
<script type="text/javascript">
	function doAction(type){
		//location.href = "writeForm.jsp" //상대경로
		//location.href = "/Mission-WEB/board/list.jsp"    //절대경로
		switch(type){
		case 'U':	
			location.href = "/Bank/updateBoard.do?no=${param.no}"
			break;
		case 'D':
			if(confirm('삭제하시겠습니까?')){
				location.href="/Bank/boardDelete.do?no=${param.no}";
			}
			break;
		case 'R':
			if(confirm('답글을 작성하시겠습니까?')){
				location.href="/Bank/rewrite.do?no=${param.no}";
			}
			break;
		case 'L':
			location.href = "/Bank/boardList.do"
			break;
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
							<h3>게시글 내용</h3>
		<hr>
		<table border="1">
			<tr>
				<th width="12%">번호</th>
				<td>${boardDetail.no}</td>
			</tr>
			<tr>
				<th width="12%">제목</th>
				<td>${boardDetail.title}</td>
			</tr>
			<tr>
				<th width="12%">글쓴이</th>
				<td>${boardDetail.writer}</td>
			</tr>
			<tr>
				<th width="12%">내용</th>
				<td>${boardDetail.content}</td>
			</tr>
			<tr>
				<th width="12%">등록일</th>
				<td>${boardDetail.regDate}</td>
			</tr>
			<tr>
				<th width="12%">조회수</th>
				<td>${boardDetail.viewCnt}</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:forEach items="${ fileList }" var="file">
						<a href="/Bank/upload/${file.fileSaveName}">
						${file.fileOriName}
						</a>
						 (${file.fileSize} bytes) 
						 <form action="<%=request.getContextPath()%>/filedown.do" method="post">
						 	<input type="hidden" value="${file.fileSaveName}" name="fileSaveName">
						 	<input type="hidden" value="${file.fileOriName}" name="fileOriName">
						 	<input type="hidden" value="${boardDetail.no}" name="boardno">
						 	<input type="submit" value="다운받기">
						 </form>
						 
						 <!-- <a href="filedown.jsp">파일 다운받기</a> --> <br>
						 
					</c:forEach>
				</td>
			</tr>
		</table>
		<br>
		<form action="<%=request.getContextPath()%>/rewrite.do" method="post">
		<c:if test="${ boardDetail.writer eq userVO.id }">
			<input type="button" value="수정" onclick="doAction('U')"> &nbsp;&nbsp;
			<input type="button" value="삭제" onclick="doAction('D')"> &nbsp;&nbsp;
		</c:if>
			<!-- <input type="button" value="답글작성" onclick="doAction('R')"> &nbsp;&nbsp; -->
			<input type="hidden" name="no" value="${param.no}">
			<input type="hidden" name="originno" value="${boardDetail.originno}">
			<input type="submit" value="답글등록">
		<input type="button" value="목록" onclick="doAction('L')"> &nbsp;&nbsp;
		</form>
							<br>
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

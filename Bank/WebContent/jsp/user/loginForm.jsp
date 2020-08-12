<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SBank</title>
<script type = "text/javascript" src = "https://developers.kakao.com/sdk/js/kakao.min.js" >  </script>
<script type = "text/javascript">
    document.addEventListener("DOMContentLoaded", function () {
    // @details 카카오톡 Developer API 사이트에서 발급받은 JavaScript Key
    Kakao.init("");  //*********발급받은키 넣기
    // @breif 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
        container: "#kakao-login-btn",
        success: function (authObj) {
            // console.log( authObj );
            Kakao.API.request({
                url: "/v2/user/me",
                success: function (res) {
                    // console.log( res );
                    // @breif 아이디
                    //document.getElementById("kakaoIdentity").innerHTML = res.id;

                    // @breif 닉네임
                    //document.getElementById("kakaoNickName").innerHTML = res.properties.nickname;
                    var url = '/Bank/loginProcess.do?kid=';
                    //alert(res.properties.nickname);
                    url += res.properties.nickname;
                	location.href = url;
                },
                fail: function (error) {
                    alert(JSON
                        .stringify(error));
                }
            });
        },
        fail: function (error) {
            alert(JSON.stringify(error));
        }
    });
});
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
      
   Website:       http://freehtml5.co/
   Email:          info@freehtml5.co
   Twitter:       http://twitter.com/fh5co
   Facebook:       https://www.facebook.com/fh5co

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
      <!--          <aside id="fh5co-hero" style="background-image: url(/Bank/images/img_bg_1.jpg);">
               <h1 style="text-align: center;">인덱스</h1>
            <div class="container-fluid">
            </div>
         </aside>     -->
      <!-- <br> <br><br> <br> -->
      <div class="container-wrap">
         <div id="fh5co-hero" style="background-image: url(/Bank/images/img_bg_1.jpg); opacity:0.4!important;">
            <div id="fh5co-services"
               style="height: 600px; border-color: fuchsia;">
               <div class="row" style="height: 570px; border-color: fuchsia;">
                  <!-- row에 대한 css 주석처리해둠! brootstrap.css파일 -->
                  <!-- 여기 고치자아 -->

                  <div align="center">
                  <h3>로그인</h3>
                  <br>

                  <br>
                  <!-- form은 이름으로 접근하기 위해서 js에서 name 속성 넣음 -->
                  <form action="<%=request.getContextPath()%>/loginProcess.do"
                     method="post" name="lform">
                     <table style="width: 40%">
                        <tr>
                           <th>ID</th>
                           <td><input type="text" name="id"></td>
                           <td rowspan="2">
                     			<button type="submit">로그인</button>
                           </td>
                        </tr>
                        <tr>
                           <th>PASSWORD</th>
                           <td><input type="password" name="password"></td>
                        </tr>
                     </table>
                     <br>
                  </form>
                  <br><br>
                  <a id="kakao-login-btn"></a><br>
                  
                  <!-- <div>
						카카오 아이디 : <span id="kakaoIdentity"></span>
				  </div>
				  <div>
		 				닉네임 : <span id="kakaoNickName"></span>
	              </div> -->
                  
                  
                  
                  
                  
                  
                  
                  
                  
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

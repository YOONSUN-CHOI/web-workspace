<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="fh5co-nav" role="navigation">
	<div class="container-wrap"
		style="height: 70px; background-image: url(/Bank/images/img_bg_2.jpg);">
		<div class="top-menu">
			<div class="row">
				<div class="col-xs-2">
					<div id="fh5co-logo">
						<%-- <a href="<%=request.getContextPath()%>/index.do">SBank</a> --%>
						<a href="<%=request.getContextPath()%>/index.do">
							<IMG src="/Bank/images/SBANK.png" style="width:45%; height: 45%">
						</a>
					</div>
				</div>
				<div class="col-xs-10 text-right menu-1">
					<ul>
						<%-- <li class="active"><a href="<%=request.getContextPath()%>/index.do">Home</a></li> --%>
						<li class="has-dropdown"><a href="<%=request.getContextPath()%>/myAccount.do?id=${userVO.id}">계좌</a>
							<ul class="dropdown">
								<li><a href="<%=request.getContextPath()%>/myAccount.do?id=${userVO.id}">내계좌</a></li>
								<li><a href="<%=request.getContextPath()%>/createAccount.do">계좌 개설</a></li>
								<li><a href="<%=request.getContextPath()%>/insertAccount.do">오픈뱅킹 - 계좌 연동</a></li>
								<li><a href="<%=request.getContextPath()%>/myAllAccount.do?id=${userVO.id}">오픈뱅킹 - 계좌 확인</a></li>
							</ul>
						</li>
						<li><a href="<%=request.getContextPath()%>/boardList.do">Q&A</a></li>
						<li class="active"><a href="<%=request.getContextPath()%>/about.do">About SBANK</a></li>

						<c:if test="${empty userVO }">
							
						<li class="has-dropdown"><a href="<%=request.getContextPath()%>/login.do">로그인</a><ul class="dropdown">
							
								<li><a href="<%=request.getContextPath()%>/login.do">로그인</a></li>
								<li><a href="<%=request.getContextPath()%>/join.do">회원가입</a></li>
							</ul>
						</li>
							<br>
						</c:if>
						<c:if test="${not empty userVO }">
							<li><a href="<%=request.getContextPath()%>/mypage.do?id=${userVO.id}">마이페이지</a></li>
							<li><a href="<%=request.getContextPath()%>/logout.do">로그아웃</a></li><br>
							<li><c:out value="${userVO.name}님 환영합니다!" /></li>
						</c:if>
					</ul>
				</div>
			</div>

		</div>
	</div>
</nav>
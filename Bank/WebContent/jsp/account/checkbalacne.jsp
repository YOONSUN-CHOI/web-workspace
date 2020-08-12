<%@page import="kr.ac.kopo.account.dao.AccountDAO"%>
<%@page import="kr.ac.kopo.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	if(request.getMethod().equalsIgnoreCase("POST"))
		request.setCharacterEncoding("utf-8");

	//System.out.print("AJAX로부터 넘어온 아이디 :");
	String account_num_str = request.getParameter("account_num");
	//int account_num = Integer.parseInt(account_num_str);
	//System.out.println(checkid);
	
	
	AccountDAO dao = new AccountDAO();
	int resultOfBalance = dao.checkBalance(account_num_str);
	System.out.print("결과 :");
	System.out.println(resultOfBalance);
	pageContext.setAttribute("resultOfBalance", resultOfBalance);
%> --%>
<%-- 이체가능 잔액은 ${request.resultOfBalance}원입니다. --%>
이체가능한 잔액은 ${resultOfBalance}원 입니다.
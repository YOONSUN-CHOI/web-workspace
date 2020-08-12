<%@page import="kr.ac.kopo.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(request.getMethod().equalsIgnoreCase("POST"))
		request.setCharacterEncoding("utf-8");

	//System.out.print("AJAX로부터 넘어온 아이디 :");
	String checkid = request.getParameter("checkid");
	//System.out.println(checkid);
	
	
	BoardDAO dao = new BoardDAO();
	String resultOfCheckId = dao.checkId(checkid);
	System.out.print("결과 :");
	System.out.println(resultOfCheckId);
	pageContext.setAttribute("resultOfCheckId", resultOfCheckId);
%>
${resultOfCheckId}
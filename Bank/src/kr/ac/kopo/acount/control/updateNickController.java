package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;

public class updateNickController implements Controller   {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("update nick controller 진입");
		String account_num=request.getParameter("account_num");
		return "/jsp/account/updateNick.jsp?account_num="+account_num;
	}
	
}

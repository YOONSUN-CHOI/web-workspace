package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;

public class depositController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("depositController 진입");
		String account_num=request.getParameter("account_num");
		return "/jsp/account/deposit.jsp?account_num="+account_num;
	}
	
}

package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;

public class accountTransferController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("accountTransferController 진입");
		String account_num=request.getParameter("account_num");
		return "/jsp/account/accountTransfer.jsp?account_num="+account_num;
	}
	
}

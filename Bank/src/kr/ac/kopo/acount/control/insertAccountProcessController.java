package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;

public class insertAccountProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String bank_name = request.getParameter("bank_name");
		String account_num = request.getParameter("account_num");
		String str_balance = request.getParameter("balance");
		int balance = Integer.parseInt(str_balance);
		String nick_name = request.getParameter("nick_name");
		
		
		
		System.out.println(id);
		System.out.println(bank_name);
		System.out.println(account_num);
		System.out.println(balance);
		System.out.println(nick_name);
		
		
		
		AccountVO accountVO = new AccountVO(id,bank_name,account_num,balance,nick_name);
		/*
		 * accountVO.setId(id); accountVO.setBank_name(bank_name);
		 * accountVO.setAccount_num(account_num); accountVO.setBalance(balance);
		 * accountVO.setNick_name(nick_name);
		 */
		
		AccountDAO dao = new AccountDAO();
		dao.insert(accountVO);
		
		String url = request.getContextPath() + "/myAllAccount.do?id="+id;
		request.setAttribute("url", url);
		
		return "/jsp/account/insertProcess.jsp";
	}
	
}

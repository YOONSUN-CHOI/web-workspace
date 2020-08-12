package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;

public class withdrawProcessController implements Controller{
	
	
	
	
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("withdrawProcessController 진입");
		String withdrawbalance_str = request.getParameter("withdrawbalance");
		int withdrawbalance = Integer.parseInt(withdrawbalance_str);
		String account_num = request.getParameter("account_num");
		
		System.out.println(withdrawbalance);
		System.out.println(account_num);
		
		AccountVO AccountVO = new AccountVO();
		AccountDAO dao = new AccountDAO();
		
		AccountVO.setAccount_num(account_num);
		AccountVO.setBalance(withdrawbalance);
		dao.withdraw(AccountVO);
		
		String url = request.getContextPath() + "/myAccount.do";
		request.setAttribute("url", url);
		
		return "/jsp/account/withdrawProcess.jsp";
	}

	
}

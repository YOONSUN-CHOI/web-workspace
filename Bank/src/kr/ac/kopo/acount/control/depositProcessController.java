package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;

public class depositProcessController implements Controller {
	
	
	
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("depositProcessController 진입");
		String depositbalance_str = request.getParameter("depositbalance");
		int depositbalance = Integer.parseInt(depositbalance_str);
		String account_num = request.getParameter("account_num");
		
		System.out.println(depositbalance);
		System.out.println(account_num);
		
		AccountVO AccountVO = new AccountVO();
		AccountDAO dao = new AccountDAO();
		
		AccountVO.setAccount_num(account_num);
		AccountVO.setBalance(depositbalance);
		dao.deposit(AccountVO);
		
		String url = request.getContextPath() + "/myAccount.do";
		request.setAttribute("url", url);
		
		//return "/jsp/account/depositProcess.jsp?id=${userVO.id}";
		return "/jsp/account/depositProcess.jsp";
		

		
	}

}

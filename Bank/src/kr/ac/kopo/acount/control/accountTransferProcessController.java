package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.user.vo.UserVO;

public class accountTransferProcessController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("accountTransferProcessController 진입");
		String withdrawbalance_str = request.getParameter("withdrawbalance");
		int withdrawbalance = Integer.parseInt(withdrawbalance_str);
		String account_num = request.getParameter("account_num");
		String bank_name = request.getParameter("bank_name"); // 입금할 계좌
		
		System.out.println(withdrawbalance);
		System.out.println(account_num);
		System.out.println(bank_name);
		
		AccountVO AccountVO = new AccountVO();
		AccountDAO dao = new AccountDAO();
		
		AccountVO.setAccount_num(account_num);
		AccountVO.setBalance(withdrawbalance);
		AccountVO.setBank_name(bank_name); // 입금할 계좌
		dao.accountTransfer(AccountVO);
		
		HttpSession session = request.getSession();
	    UserVO userrVO = (UserVO) session.getAttribute("userVO");
	    String sessionid = userrVO.getId();
	    System.out.println("sessionid"+sessionid);
		
		String url = request.getContextPath() + "/myAccount.do?id="+sessionid;
		request.setAttribute("url", url);
		
		return "/jsp/account/accountTransferProcess.jsp";
	}
	
}

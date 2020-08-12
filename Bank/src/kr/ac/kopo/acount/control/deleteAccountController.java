package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.user.vo.UserVO;

public class deleteAccountController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub\
		
		System.out.println("deleteAccountController 진입");
		String account_num=request.getParameter("account_num");
		System.out.println(account_num);
		
		AccountDAO dao = new AccountDAO();
		dao.deleteAccount(account_num);
		
		HttpSession session = request.getSession();
	      
	    UserVO userVO = (UserVO) session.getAttribute("userVO");
	      
	     
	      String sessionid = userVO.getId();
		
		
		String url = request.getContextPath() + "/myAccount.do?id="+sessionid;
		request.setAttribute("url", url);
		
		return "/jsp/account/deleteAccount.jsp";
	}
	
}

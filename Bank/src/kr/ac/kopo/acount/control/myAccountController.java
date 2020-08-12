package kr.ac.kopo.acount.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;

public class myAccountController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("myaccount controller 진입");
		
		String id = request.getParameter("id");
		AccountDAO dao = new AccountDAO();
		
		List<AccountVO> list = dao.searchAccount(id);
		
		request.setAttribute("list", list);
		
		return "/jsp/account/myAccount.jsp";
	}
	
	

}

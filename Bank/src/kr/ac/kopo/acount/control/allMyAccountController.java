package kr.ac.kopo.acount.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;

public class allMyAccountController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("allMyAccountController 진입");
		
		String id = request.getParameter("id");
		System.out.println("allMyAccountController"+id);
		AccountDAO dao = new AccountDAO();
		
		List<AccountVO> listt = dao.searchAllAccount(id);
		
		request.setAttribute("listt", listt);
		
		return "/jsp/account/allMyAccount.jsp";
	}
}

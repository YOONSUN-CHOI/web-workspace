package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;

public class checkbalacneController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("checkbalacneController 진입");
		
		if(request.getMethod().equalsIgnoreCase("POST"))
			request.setCharacterEncoding("utf-8");

		//System.out.print("AJAX로부터 넘어온 아이디 :");
		String account_num_str = request.getParameter("account_num");
		//int account_num = Integer.parseInt(account_num_str);
		System.out.println("계좌번호 : "+account_num_str);
		
		
		AccountDAO dao = new AccountDAO();
		int resultOfBalance = dao.checkBalance(account_num_str);
		System.out.print("결과 :");
		System.out.println(resultOfBalance);
		request.setAttribute("resultOfBalance", resultOfBalance);

		return "/jsp/account/checkbalacne.jsp";
	}
	
}

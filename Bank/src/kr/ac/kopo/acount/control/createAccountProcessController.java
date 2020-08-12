package kr.ac.kopo.acount.control;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;

public class createAccountProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String bank_name = request.getParameter("bank_name");
		String str_balance = request.getParameter("balance");
		int balance = Integer.parseInt(str_balance);
		String nick_name = request.getParameter("nick_name");
		
		String account_num="";
		Random r = new Random();
		for(int i=0; i<3 ; i++) {  /////여기여기여기 수정!
			account_num += Integer.toString(r.nextInt(10));
		}
		//account_num+="-";
		for(int i=0; i<6 ; i++) {  /////여기여기여기 수정!
			account_num += Integer.toString(r.nextInt(10));
		}
		//account_num+="-";
		for(int i=0; i<3 ; i++) {  /////여기여기여기 수정!
			account_num += Integer.toString(r.nextInt(10));
		}
		
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
		dao.create(accountVO);
		
		String url = request.getContextPath() + "/myAccount.do?id="+id;
		request.setAttribute("url", url);
		
		return "/jsp/account/createProcess.jsp";
	}
	
}

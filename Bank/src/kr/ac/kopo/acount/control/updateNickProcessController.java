package kr.ac.kopo.acount.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.user.vo.UserVO;

public class updateNickProcessController implements Controller    {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("updateNickProcessController 진입");
		
		request.setCharacterEncoding("utf-8");
		
		String nick_name = request.getParameter("nick_name");
		String account_num = request.getParameter("account_num");
		
		System.out.println(nick_name);
		System.out.println(account_num);
		
		AccountVO AccountVO = new AccountVO();
		AccountDAO dao = new AccountDAO();
		
		AccountVO.setAccount_num(account_num);
		AccountVO.setNick_name(nick_name);
		dao.updateNick(AccountVO);
		HttpSession session = request.getSession();
	      UserVO userrVO = (UserVO) session.getAttribute("userVO");
	      String sessionid = userrVO.getId();
	      System.out.println("sessionid"+sessionid);
		
		String url = request.getContextPath() + "/myAccount.do?id="+sessionid;
		request.setAttribute("url", url);
		
		//return "/jsp/account/myAccount.jsp?id=${userVO.id}";
		return "/jsp/account/updateNickProcess.jsp";
	}
	
}

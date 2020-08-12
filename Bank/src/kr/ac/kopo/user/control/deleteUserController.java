package kr.ac.kopo.user.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.user.dao.UserDAO;

public class deleteUserController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub\
		
		request.setCharacterEncoding("utf-8");
		System.out.println("deleteUserController 진입");
		String id =request.getParameter("id");
		System.out.println(id);
		
		UserDAO dao = new UserDAO();
		dao.deleteUser(id);
		
		String url = request.getContextPath() + "/index.do";
		request.setAttribute("url", url);
		
		return "/jsp/user/deleteUser.jsp";
	}
}

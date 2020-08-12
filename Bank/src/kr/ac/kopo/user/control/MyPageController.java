package kr.ac.kopo.user.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.user.dao.UserDAO;
import kr.ac.kopo.user.vo.UserVO;

public class MyPageController implements Controller  {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("mypagecontroller진입");
		
		String id = request.getParameter("id");
		System.out.println("id받아오는지 테스트 : "+id);
		
		UserDAO dao = new UserDAO();
		UserVO user = dao.selectById(id);
		user = dao.aes_dec(user);
		request.setAttribute("user", user);
		
		return "/jsp/user/myPage.jsp";
	}
}

package kr.ac.kopo.user.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.user.dao.UserDAO;
import kr.ac.kopo.user.vo.UserVO;

public class UserUpdateProcessController  implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserUpdateProcessController 진입");

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email_id = request.getParameter("email_id");
		String email_domain = request.getParameter("email_domain");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String post = request.getParameter("post");
		String basic_addr = request.getParameter("basic_addr");
		String detail_addr = request.getParameter("detail_addr");

		UserVO joinVO = new UserVO();
		UserDAO dao = new UserDAO();
		
		System.out.println("id : "  + id);
		System.out.println("pwd : "  + pwd);
		System.out.println("name : "  + name);
		System.out.println("email_id : "  + email_id);
		System.out.println("email_domain : "  + email_domain);
		System.out.println("tel1 : "  + tel1);
		System.out.println("content : "  + tel2);
		System.out.println("content : "  + tel3);
		System.out.println("content : "  + post);
		System.out.println("basic_addr : "  + basic_addr);
		System.out.println("detail_addr : "  + detail_addr);

		
		String aes = dao.aes(pwd);
		
		joinVO.setId(id);
		joinVO.setPassword(aes);
		joinVO.setName(name);
		joinVO.setEmail_id(email_id);
		joinVO.setEmail_domain(email_domain);
		joinVO.setTel1(tel1);
		joinVO.setTel2(tel2);
		joinVO.setTel3(tel3);
		joinVO.setPost(post);
		joinVO.setBasic_addr(basic_addr);
		joinVO.setDetail_addr(detail_addr);

		dao.update(joinVO);
		
		HttpSession session = request.getSession();
		UserVO userrVO = (UserVO) session.getAttribute("userVO");
		String sessionid = userrVO.getId();

		String url = request.getContextPath() + "/mypage.do?id="+sessionid;
		//request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/jsp/user/uupdateProcess.jsp";
	}
}

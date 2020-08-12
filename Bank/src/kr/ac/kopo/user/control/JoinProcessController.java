package kr.ac.kopo.user.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.user.dao.UserDAO;
import kr.ac.kopo.user.vo.UserVO;

public class JoinProcessController implements Controller  {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("joinprocess 진입");

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

		System.out.println("title : "  + id);
		System.out.println("writer : "  + pwd);
		System.out.println("content : "  + name);
		System.out.println("content : "  + email_id);
		System.out.println("content : "  + email_domain);
		System.out.println("content : "  + tel1);
		System.out.println("content : "  + tel2);
		System.out.println("content : "  + tel3);
		System.out.println("content : "  + post);
		System.out.println("content : "  + basic_addr);
		System.out.println("content : "  + detail_addr);
		
		UserVO joinVO = new UserVO();
		UserDAO dao = new UserDAO();
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

		dao.join(joinVO);
		
		String url = request.getContextPath() + "/login.do";
		request.setAttribute("url", url);

		return "/jsp/user/joinProcess.jsp";
	}
}

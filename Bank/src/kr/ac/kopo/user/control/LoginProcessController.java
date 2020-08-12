package kr.ac.kopo.user.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.user.dao.UserDAO;
import kr.ac.kopo.user.vo.UserVO;

public class LoginProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("loginprocess 진입");

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String kid = request.getParameter("kid");

		System.out.println("id"+id);
		System.out.println("pwd"+password);
		System.out.println("kid"+kid);
		
		UserVO loginVO = new UserVO();
		UserVO userVO = new UserVO();
		String setid = "";
		
		if(kid == null) {
			UserDAO dao = new UserDAO();
			String hash_pwd = dao.aes(password);
			
			loginVO.setId(id);
			loginVO.setPassword(hash_pwd);
			
			userVO = dao.login(loginVO);
			//setid=userVO.getId();
		} else {
			userVO.setId(kid);
			userVO.setName(kid);
			userVO.setType("U");
			//setid=kid;
		}
		
		
		//setid=userVO.getId();
		
		 if(userVO.getType() != "N") { 
			 setid=userVO.getId(); 
		}
		 
		
		
		String msg = "";
		String url = "";
		if (setid.length()<1) {
			msg = "로그인을 실패하였습니다. 로그인 페이지로 이동합니다";
			url = request.getContextPath() + "/login.do";
		} else {
			msg = "로그인 성공하였습니다.";
			url = request.getContextPath()+"/myAccount.do?id="+setid;
			HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
		}
		//System.out.println(msg);
		//System.out.println(url);
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		//return "/jsp/user/loginProcess.jsp";
		//return "redirect:" + url +"?msg="+ URLEncoder.encode(msg,"utf-8") ;
		return "redirect:" + url;
	}

}

package kr.ac.kopo.board.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.user.dao.UserDAO;

public class boardDeleteController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		System.out.println("boardDeleteController 진입");
		String no_str =request.getParameter("no");
		int no = Integer.parseInt(no_str);
		System.out.println(no);
		
		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(no);
		
		String url = request.getContextPath() + "/boardList.do";
		request.setAttribute("url", url);
		
		return "/jsp/board/deleteBoard.jsp";
	}
	
}

package kr.ac.kopo.board.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.user.dao.UserDAO;
import kr.ac.kopo.user.vo.UserVO;

public class updateBoardFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("updateBoardFormController진입");
		
		String no_str=request.getParameter("no");
		int no = Integer.parseInt(no_str);
		BoardDAO dao = new BoardDAO();
		BoardVO boardDetail = dao.selectByNo2(no);
		request.setAttribute("boardDetail", boardDetail);
		
		return "/jsp/board/boardUpdateForm.jsp";
	}
	
}

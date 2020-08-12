package kr.ac.kopo.board.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardVO;

public class boardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
System.out.println("boardListController 진입");
		
		BoardDAO dao = new BoardDAO();
		
		List<BoardVO> boardlist = dao.selectAllBoard();
		
		request.setAttribute("boardlist", boardlist);
		
		
		
		return "/jsp/board/boardlist.jsp";
	}
	
}

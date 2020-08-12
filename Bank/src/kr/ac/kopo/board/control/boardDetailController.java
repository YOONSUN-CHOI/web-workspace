package kr.ac.kopo.board.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardFileVO;
import kr.ac.kopo.board.vo.BoardVO;

public class boardDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("boardDetailController진입");
		
		
		
		
		
		////////////////
		
		String strno =  request.getParameter("no");
		int no = Integer.parseInt(strno);
		
		BoardDAO dao = new BoardDAO();
		
		//조회수 증가
		String type = request.getParameter("type");
		if(type != null && type.equals("list"))
		dao.incrementViewCnt(no);
		
		//게시판 번호에 의한 게시물 조회
		BoardVO boardDetail = dao.selectByNo2(no);
		//System.out.println("here");
		
		
		
		//첨부파일 조회
		List<BoardFileVO> fileList = dao.selectFileByNo(no);
		
		
		
		
		//공유영역(pageContext)에 board 게시물 등록
		request.setAttribute("boardDetail", boardDetail);
		request.setAttribute("fileList", fileList);
		
		//////////////
		
		return "/jsp/board/boardDetail.jsp";
	}
}

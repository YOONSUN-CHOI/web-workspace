package kr.ac.kopo.board.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;

public class boardReWriteController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String originno_str = request.getParameter("originno");
		int originno = Integer.parseInt(originno_str);
		
		return "/jsp/board/reWriteForm.jsp?originno="+originno;
	}

}

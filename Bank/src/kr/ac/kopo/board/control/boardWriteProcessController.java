package kr.ac.kopo.board.control;

import com.josephoconnell.html.HTMLInputFilter;
import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardFileVO;
import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.util.KopoFileNamePolicy;

public class boardWriteProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("boardWriteProcessController 진입");
		
	 	request.setCharacterEncoding("utf-8"); 
		// post방식은 바디로 와서 인코딩 바꿔야함.  get은 url로 오기에 인코딩 바꿀것없음
		
		//String saveFolder = "C:/Users/HP/OneDrive - 한국폴리텍대학/폴리텍/web-workspace/Bank/WebContent/upload";
		String saveFolder = "C:/Users/cysun31/OneDrive - 한국폴리텍대학/폴리텍/web-workspace222/Bank/WebContent/upload";
		//실제로는 톰캣서버에 저장됨
		
		MultipartRequest multi = new MultipartRequest(request,
														saveFolder, 
														1024*1024*3, 
														"utf-8", 
														new KopoFileNamePolicy()
														);
		
		
		// 1단계 게시물 저장(t_board)
		//String title = request.getParameter("title"); // 이 방식은 에러난다. 왜? name=value 형태가 아니라 매직넘버 형태이기 때문. 파싱을 위해 MultipartRequest 사용해야 한다.
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		int BoardNo = dao.selectBoardNo(); // 시퀀스 먼저 받아온 후 t_Board에도 넣고 파일테이들에도 넣고
		
		
		
		///////////////////// 필터 연습   /////////////////
		
		String input = "<script>alert('xss');</script>";
		String clean = new HTMLInputFilter().filter( input );
		
		System.out.println("clean : "+clean);
		
		
		
		//////////////필터 연습 끝 /////////////
		
		
		
		title = new HTMLInputFilter().filter( title );
		writer = new HTMLInputFilter().filter( writer );
		content = new HTMLInputFilter().filter( content );
		
		
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setNo(BoardNo);
		
		
		dao.insertBoard(board);
		
		
		
		
		//2단계 : 첨부파일 저장(t_board_file)
		Enumeration<String> files = multi.getFileNames();
		while(files.hasMoreElements()){
			String filename = files.nextElement();
			File f = multi.getFile(filename);
			if(f != null){ // 첨부된 파일이 있다면
				String fileOriName = multi.getOriginalFileName(filename);
				String fileSaveName = multi.getFilesystemName(filename);
				int fileSize = (int)f.length();
				
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setFileOriName(fileOriName);
				fileVO.setFileSaveName(fileSaveName);
				fileVO.setFileSize(fileSize);
				fileVO.setBoardNo(BoardNo); //외래키에 해당 게시물 번호
				
				System.out.println("write.jsp");
				System.out.println(fileOriName);
				System.out.println(fileSaveName);
				System.out.println(fileSize);
				System.out.println(BoardNo);
				
				dao.insertFile(fileVO);
			}
		}
		
		
		String url = request.getContextPath() + "/boardList.do";
		request.setAttribute("url", url);
		
		return "/jsp/board/writeProcess.jsp";
	}
	
}

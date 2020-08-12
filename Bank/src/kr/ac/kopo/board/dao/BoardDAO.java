package kr.ac.kopo.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.board.vo.BoardFileVO;
import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class BoardDAO {

	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String user = "scott";
	private String password = "tiger";

	/**
	 * 게시물 번호에 해당하는 게시물 조회 서비스
	 * 
	 * @param no 조회할번호
	 * @return 해당 게시물
	 */
public BoardVO selectByNo(int no) {
		
		Connection 			conn = null;
		PreparedStatement 	pstmt = null;
		BoardVO 			board = null;
		
		try {
			conn = new ConnectionFactory().getConnection(url, user, password);
			StringBuilder sql = new StringBuilder();
			sql.append(" select no, title, writer, content, view_cnt ");
			sql.append("       , to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append("  from B_BOARD ");
			sql.append(" where no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int boardNo = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String content = rs.getString("content");
				int viewCnt = rs.getInt("view_cnt");
				String regDate = rs.getString("reg_date");
				
				board = new BoardVO(boardNo, title, writer, content, viewCnt, regDate);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return board;
	}
	
	
	
	
	
	
	
	
public BoardVO selectByNo2(int no) {
	
	Connection 			conn = null;
	PreparedStatement 	pstmt = null;
	BoardVO 			board = null;
	
	try {
		conn = new ConnectionFactory().getConnection(url, user, password);
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select no,title,writer,content,originno,view_cnt, case\r\n" + 
				"    when  to_char(reg_date,'YYYYMMDD')=TO_CHAR(SYSDATE, 'YYYYMMDD') then to_char(reg_date,'hh24-mi-ss')\r\n" + 
				"    else to_char(reg_date,'yyyy-mm-dd')\r\n" + 
				"    end as reg_date\r\n" + 
				"from B_BOARD where no = ? ");
		

		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			int boardNo = rs.getInt("no");
			String title = rs.getString("title");
			String writer = rs.getString("writer");
			String content = rs.getString("content");
			int viewCnt = rs.getInt("view_cnt");
			String regDate = rs.getString("reg_date");
			int originno = rs.getInt("originno");
			
			System.out.println(boardNo);
			System.out.println(title);
			System.out.println(writer);
			System.out.println(content);
			System.out.println(viewCnt);
			System.out.println(regDate);
			
			//board = new BoardVO(boardNo, title, writer, content, viewCnt, regDate);
			
			board = new BoardVO();
			board.setNo(boardNo);
			board.setTitle(title);
			board.setWriter(writer);
			board.setContent(content);
			board.setViewCnt(viewCnt);
			board.setRegDate(regDate);
			board.setOriginno(originno);

		}
		
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		JDBCClose.close(conn, pstmt);
	}
	return board;
}
	
	
	
	
	
	
	
	public List<BoardVO> selectAllBoard(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = new ConnectionFactory().getConnection(url, user, password);

			StringBuilder sql = new StringBuilder();
			sql.append(" select no, title, writer,originno,groupord,grouplayer,view_cnt, to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append("  from B_BOARD ");
			sql.append(" order by originno asc , groupord desc , no asc ");

			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				int originno = rs.getInt("originno");
				int groupord = rs.getInt("groupord");
				int grouplayer = rs.getInt("grouplayer");
				int view_cnt =rs.getInt("view_cnt");
				String regDate = rs.getString("reg_date");

				BoardVO board = new BoardVO(no, title, writer,originno,groupord,grouplayer,view_cnt, regDate);
				list.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return list;
	}
	
	
	
	
	
public List<BoardVO> selectAllBoard2(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = new ConnectionFactory().getConnection(url, user, password);

			StringBuilder sql = new StringBuilder();
			sql.append(" select no,title,writer,content,view_cnt, case\r\n" + 
					"    when  to_char(reg_date,'YYYYMMDD')=TO_CHAR(SYSDATE, 'YYYYMMDD') then to_char(reg_date,'hh24-mi-ss')\r\n" + 
					"    else to_char(reg_date,'yyyy-mm-dd')\r\n" + 
					"    end as reg_date\r\n" + 
					"from t_board order by no");


			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String regDate = rs.getString("reg_date");
				int view_cnt = rs.getInt("view_cnt");

				BoardVO board = new BoardVO(no, title, writer, regDate,view_cnt);
				list.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return list;
	}
	
	
	
	
	
	
	/**
	 * 새로운 게시물 등록서비스
	 */
	public void insertBoard(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into b_board(no, title, writer, content,originno,groupord,grouplayer ) ");
			sql.append(" values(?,?,?,?,?,?,?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, board.getNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getContent());
			pstmt.setInt(5, board.getNo());
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 0);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	/**
	 * 답글 등록 서비스
	 * @param board
	 */
	public void insertBoard2(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into b_board(no, title, writer, content,originno,groupord,grouplayer ) ");
			sql.append(" values(?,?,?,?,?,?,?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, board.getNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getContent());
			pstmt.setInt(5, board.getOriginno());
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 0);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	
	/**
	 * 게시판 삭제 서비스
	 * @param no 삭제할 게시물 번호
	 */
	public void deleteBoard(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection(url, user, password);

			StringBuilder sql = new StringBuilder();
			sql.append(" delete B_BOARD where no=? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			


		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	

	
	
	public void increcnt(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int viewcnt=0;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select view_cnt from t_board where no=? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				viewcnt=rs.getInt(1);
		 	} 
			
			//System.out.println("올리기 전의 조회수 : "+viewcnt);
			
			StringBuilder sql2 = new StringBuilder();
			sql2.append(" update t_board set view_cnt = ? where no=? ");
			
			System.out.println();
			
			pstmt = conn.prepareStatement(sql2.toString());
			pstmt.setInt(1, (viewcnt+1) );
			pstmt.setInt(2, no);

			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	
	
	
	
	
	
	
	public void updateBoard(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			System.out.println("title : "  + board.getTitle());
			System.out.println("writer : "  + board.getWriter());
			System.out.println("content : "  + board.getContent());
			System.out.println("no: "  + board.getNo());
			System.out.println("=====");
			
			
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" update B_BOARD set title=?,content=? where no=? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	
	/**
	 * seq_t_board_no 생성하는 기능
	 */
	public int selectBoardNo() {

		String sql = " select seq_b_board_no.nextval from dual ";

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);


		} catch (Exception e) {
			e.printStackTrace();
		} 

		return 0;
	}
	
	
	public void incrementViewCnt(int no) {
		StringBuilder sql = new StringBuilder();
		sql.append("update B_BOARD ");
		sql.append("   set view_cnt = view_cnt + 1 ");
		sql.append(" where no = ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 답글 등록 전 originno 같은 데이터는 
	 * @param board
	 */
	public void updategroupord(int originno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" update B_BOARD set groupord = groupord + 1 where originno=? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, originno);

			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
public String checkId(String checkid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection(url, user, password);

			StringBuilder sql = new StringBuilder();
			sql.append(" select * from B_MEMBER where id=? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, checkid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return "사용할수 없는 아이디 입니다.";
			}else {
				return "사용 가능한 아이디 입니다.";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return "error";
	}
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////
	//    첨부파일 CRUD                                                        //
	//////////////////////////////////////////////////////////////////////////
	
	public void insertFile(BoardFileVO fileVO) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into b_board_file(no, board_no, file_ori_name, "
				+ "file_save_name, file_size ) values (seq_b_board_file_no.nextval,?,?,?,?) ");
	
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, fileVO.getBoardNo());
			pstmt.setString(2, fileVO.getFileOriName());
			pstmt.setString(3, fileVO.getFileSaveName());
			pstmt.setInt(4, fileVO.getFileSize());
			pstmt.executeUpdate();

			System.out.println("dao!!!");
			System.out.println(fileVO.getFileOriName());
			System.out.println(fileVO.getFileSaveName());
			System.out.println(fileVO.getFileSize());
			System.out.println(fileVO.getBoardNo());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	
	
	
	
	
	public List<BoardFileVO> selectFileByNo(int boardNo){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select no, file_ori_name, file_save_name, file_size "
				+ " from b_board_file where board_no=? ");
		List<BoardFileVO> fileList = new ArrayList<BoardFileVO>();
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setNo(rs.getInt("no"));
				fileVO.setFileOriName(rs.getString("file_ori_name"));
				fileVO.setFileSaveName(rs.getString("file_save_name"));
				fileVO.setFileSize(rs.getInt("file_size"));
				fileList.add(fileVO);
				
				System.out.println(rs.getString("file_ori_name"));
				System.out.println(rs.getString("file_save_name"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fileList;
	}
	
	
	
	
	
}
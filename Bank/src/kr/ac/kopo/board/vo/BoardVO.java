package kr.ac.kopo.board.vo;

public class BoardVO {
	private int no;
	private String title;
	private String writer;
	private String content;
	private int originno;
	private int groupord;
	private int grouplayer;
	private int viewCnt;
	private String regDate;
	
	
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public BoardVO(int no, String title, String writer, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.regDate = regDate;
	}
	
	public BoardVO(int no, String title, String writer, String regDate,int viewCnt) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
	}
	
	public BoardVO(int no, String title, String writer, String regDate, String content) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.regDate = regDate;
		this.content = content;
	}//no,title,writer,regDate,content


	public BoardVO(int no, String title, String writer, String content, int viewCnt, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
	}
	
	
	                    
	public BoardVO(int no, String title, String writer, int originno, int groupord, int grouplayer,
			int viewCnt, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.originno = originno;
		this.groupord = groupord;
		this.grouplayer = grouplayer;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
	}

	public BoardVO(int no, String title, String writer, String content, int originno, int groupord, int grouplayer,
			int viewCnt, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.originno = originno;
		this.groupord = groupord;
		this.grouplayer = grouplayer;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
	}
	
	
	
	
	
	
	
	
	
	
	


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getOriginno() {
		return originno;
	}


	public void setOriginno(int originno) {
		this.originno = originno;
	}


	public int getGroupord() {
		return groupord;
	}


	public void setGroupord(int groupord) {
		this.groupord = groupord;
	}


	public int getGrouplayer() {
		return grouplayer;
	}


	public void setGrouplayer(int grouplayer) {
		this.grouplayer = grouplayer;
	}


	public int getViewCnt() {
		return viewCnt;
	}


	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", originno="
				+ originno + ", groupord=" + groupord + ", grouplayer=" + grouplayer + ", viewCnt=" + viewCnt
				+ ", regDate=" + regDate + "]";
	}
	
	

	
	
}

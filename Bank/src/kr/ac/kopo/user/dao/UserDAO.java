package kr.ac.kopo.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import kr.ac.kopo.user.vo.UserVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class UserDAO {
	public UserVO loginChk(UserVO loginVO) {

		return null;
	}
	
	
	/**
	 * 로그인 하는 메소드
	 * @param loginVO
	 * @return userVO
	 */
	public UserVO login(UserVO loginVO) {

		UserVO userVO = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select id, type, name ");
		sql.append("  from b_member ");
		sql.append(" where id = ? and password = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, loginVO.getId());
			pstmt.setString(2, loginVO.getPassword());

			ResultSet rs = pstmt.executeQuery();

			userVO = new UserVO();
			if (rs.next()) {
				userVO.setId(rs.getString("id"));
				userVO.setType(rs.getString("type"));
				userVO.setName(rs.getString("name"));
			}
			else {
				userVO.setType("N");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userVO;
	}
	
	
	
	
	/**
	 * 회원가입
	 * @param joinVO
	 * @return
	 */
	public void join(UserVO joinVO) {

		UserVO userVO = null;

		StringBuilder sql = new StringBuilder();
		//StringBuilder sql2 = new StringBuilder();
		
		//sql2.append(" select STANDARD_HASH ( ?, 'SHA256') from dual ");		
		
		sql.append(" insert into b_member(ID, PASSWORD,NAME, EMAIL_ID, EMAIL_DOMAIN, TEL1, TEL2,TEL3,POST, BASIC_ADDR, DETAIL_ADDR   ) ");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?) ");
		

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, joinVO.getId());
			pstmt.setString(2, joinVO.getPassword());
			pstmt.setString(3, joinVO.getName());
			pstmt.setString(4, joinVO.getEmail_id());
			pstmt.setString(5, joinVO.getEmail_domain());
			pstmt.setString(6, joinVO.getTel1());
			pstmt.setString(7, joinVO.getTel2());
			pstmt.setString(8, joinVO.getTel3());
			pstmt.setString(9, joinVO.getPost());
			pstmt.setString(10, joinVO.getBasic_addr());
			pstmt.setString(11, joinVO.getDetail_addr());

			pstmt.executeUpdate();



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * id 기준 모든 정보 조회
	 * @param id
	 * @return
	 */
	public UserVO selectById(String id) {

		UserVO member = new UserVO();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append("  from B_MEMBER ");
		sql.append(" where id=? ");
		
		try(Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setEmail_id(rs.getString("email_id"));
				member.setEmail_domain(rs.getString("email_domain"));
				member.setTel1(rs.getString("tel1"));
				member.setTel2(rs.getString("tel2"));
				member.setTel3(rs.getString("tel3"));
				member.setPost(rs.getString("post"));
				member.setBasic_addr(rs.getString("basic_addr"));
				member.setDetail_addr(rs.getString("detail_addr"));
				member.setType(rs.getString("type"));
				member.setReg_date(rs.getString("reg_date"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 개인정보 수정
	 * @param member
	 */
	public void update(UserVO member) {


		StringBuilder sql = new StringBuilder();
		
		sql.append(" update B_MEMBER set NAME=?, PASSWORD=?, EMAIL_ID=?, EMAIL_DOMAIN=?, TEL1=?, TEL2=?, TEL3=?, POST=?,      ");
		sql.append(" BASIC_ADDR=?, DETAIL_ADDR=?    where ID=? ");
		
		try(Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getEmail_id());
			pstmt.setString(4, member.getEmail_domain());
			pstmt.setString(5, member.getTel1());
			pstmt.setString(6, member.getTel2());
			pstmt.setString(7, member.getTel3());
			pstmt.setString(8, member.getPost());
			pstmt.setString(9, member.getBasic_addr());
			pstmt.setString(10, member.getDetail_addr());
			pstmt.setString(11, member.getId());
			pstmt.executeUpdate();
			System.out.println(member.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 회원 탈퇴
	 * @param account_num
	 */
	public void deleteUser(String id) {

		UserVO userVO = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM B_MEMBER where id=?  ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);

			
			System.out.println(id);


			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("회원탈퇴실패");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * pwd 해쉬 복호화!
	 * @param ipt
	 * @return
	 */
	public String makehash(String ipt) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select STANDARD_HASH ( ?, 'SHA256') as result from dual  ");
		String hash ="";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, ipt);

			
			System.out.println("ipt:"+ipt);


			ResultSet rs = pstmt.executeQuery();
			

			
			if (rs.next()) {
				hash=rs.getString("result");
				System.out.println("result : "+hash);
			}

		} catch (Exception e) {
			System.out.println("해쉬 생성 실패");
			e.printStackTrace();
		}
		
		
		return hash;
	}
	
	
	
	/**
	 * aes 암호화
	 * @param ipt
	 * @return
	 */
	public String aes(String ipt) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select ENCRYPTION_AES.ENC_AES(?) as result from dual  ");
		String aes_result ="";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, ipt);

			
			System.out.println("ipt:"+ipt);


			ResultSet rs = pstmt.executeQuery();
			

			
			if (rs.next()) {
				aes_result=rs.getString("result");
				System.out.println("result : "+aes_result);
			}

		} catch (Exception e) {
			System.out.println("aes 암호화 실패");
			e.printStackTrace();
		}
		
		
		return aes_result;
	}
	
	
	
	/**
	 * aes 복호화
	 * @param loginVO
	 * @return
	 */
	public UserVO aes_dec(UserVO userVO) {

		

		StringBuilder sql = new StringBuilder();
		sql.append(" select ENCRYPTION_AES.DEC_AES(?) as result from dual ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, userVO.getPassword());
			ResultSet rs = pstmt.executeQuery();

			
			if (rs.next()) {
				userVO.setPassword(rs.getString("result"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userVO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package kr.ac.kopo.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.user.vo.UserVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class AccountDAO {
	
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	/**
	 * 계좌 개설 - 새로운 계좌 개설
	 * @param joinVO
	 */
	public void create(AccountVO accountVO) {

		UserVO userVO = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into b_account(id, bank_name,account_num, balance, nick_name) ");
		sql.append(" values(?,?,?,?,?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, accountVO.getId());
			pstmt.setString(2, accountVO.getBank_name());
			pstmt.setString(3, accountVO.getAccount_num());
			pstmt.setInt(4, accountVO.getBalance());
			pstmt.setString(5, accountVO.getNick_name());
			
			System.out.println(accountVO.getId());
			System.out.println(accountVO.getBank_name());
			System.out.println(accountVO.getAccount_num());
			System.out.println(accountVO.getBalance());
			System.out.println( accountVO.getNick_name());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌생성실패");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * SBANK에 있는 내 모든 계좌 조회 
	 * @return
	 */
	public List<AccountVO> searchAccount(String id) {
		List<AccountVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from B_ACCOUNT where id=? and bank_name='SBANK'  ");
		
		try(Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);

			String bank_name = "";
			int balance = 0;
			String nickname = "";
			String account_num="";
			String reg_date="";

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id=rs.getString("id");
				bank_name = rs.getString("bank_name");
				account_num = rs.getString("account_num");
				balance = rs.getInt("balance");
				nickname = rs.getString("nick_name");
				reg_date = rs.getString("reg_date");
				AccountVO account = new AccountVO(id, bank_name, account_num,balance,nickname,reg_date);
				list.add(account);	
			}
		} catch (Exception e) {
			System.out.println("계좌 검색 실패");
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 계좌 별칭 수정
	 * @param accountVO
	 */
	public void updateNick(AccountVO accountVO) {

		UserVO userVO = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" update b_account set nick_name=? where account_num=?  ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, accountVO.getNick_name());
			pstmt.setString(2, accountVO.getAccount_num());

			
			System.out.println(accountVO.getNick_name());
			System.out.println(accountVO.getAccount_num());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌 별칭 수정실패");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 계좌 삭제
	 * @param account_num
	 */
	public void deleteAccount(String account_num) {

		UserVO userVO = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM B_ACCOUNT where account_num=?  ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, account_num);

			
			System.out.println(account_num);


			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌 별칭 수정실패");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	/**
	 * 입금
	 * @param accountVO
	 */
	public void deposit(AccountVO accountVO) {
		
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select balance from B_ACCOUNT where account_num = ?  ");

			pstmt = conn.prepareStatement(sql.toString());
			String account_num = accountVO.getAccount_num();
			int addbalance = accountVO.getBalance();

			
			pstmt.setString(1, account_num);
			String user_name = "";
			int balance = 0;
			String nickname = "";

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				balance = rs.getInt(1);
			}

			int set_balance = addbalance + balance;
			System.out.println("기존잔액:"+balance);
			System.out.println("입금액:"+addbalance);
			System.out.println("set_balance:"+set_balance);
			

			StringBuilder sql2 = new StringBuilder();
			sql2.append(" update B_ACCOUNT set balance= ? where account_num= ? ");

			pstmt = conn.prepareStatement(sql2.toString());
			pstmt.setInt(1, set_balance);
			pstmt.setString(2, account_num);
			pstmt.executeUpdate();
			System.out.println(addbalance + "원 입금 후 " + account_num + " 계좌의 잔액은 " + set_balance + "원 입니다.");


		} catch (Exception e) {
			System.out.println("입금 실패");
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	
	
	/**
	 * 출금
	 * @param newAccount
	 */
	public void withdraw(AccountVO accountVO) {
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select balance from B_ACCOUNT where account_num = ?  ");

			pstmt = conn.prepareStatement(sql.toString());
			
			String account_num = accountVO.getAccount_num();
			int subbalance = accountVO.getBalance();

			pstmt.setString(1, account_num);
			String user_name = "";
			int balance = 0;
			String nickname = "";

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				balance = rs.getInt(1);
			}

			if (subbalance > balance) {
				System.out.println("계좌의 잔금이 원하는 출금액보다 작습니다. ");
			} else {
				int set_balance = balance - subbalance;

				StringBuilder sql2 = new StringBuilder();
				sql2.append(" update B_ACCOUNT set balance= ? where account_num= ? ");

				pstmt = conn.prepareStatement(sql2.toString());
				pstmt.setInt(1, set_balance);
				pstmt.setString(2, account_num);
				pstmt.executeUpdate();
				System.out.println(subbalance + "원 출금 후 " + account_num + " 계좌의 잔액은 " + set_balance + "원 입니다.");
			}


		} catch (Exception e) {
			System.out.println("출금 실패");
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	
	/**
	 * 계좌이체
	 * @param newAccount
	 * @throws SQLException 
	 */
	public void accountTransfer(AccountVO newAccount) throws SQLException  {
		conn = new ConnectionFactory().getConnection();
		
		try {
			conn.setAutoCommit(false); // 기본으로는 true로 지정되어 있는값,
										// 트랜잭션을 위해 false값 지정!
			
			/* 변수 세팅! */
			String account_num = newAccount.getAccount_num(); //출금할 계좌
			int subbalance = newAccount.getBalance();         //뺄돈
 
			String bank_name = newAccount.getBank_name();     //입금할 계좌
			int addbalance = newAccount.getBalance();        //더할 돈!
			
			/*
			 * AccountVO newAccount2 = new AccountVO();
			 * newAccount2.setAccount_num(bank_name); newAccount2.setBalance(addbalance);
			 * newAccount2.setId();
			 */
			
			
			/*출금 로직*/
			StringBuilder sql = new StringBuilder();
			sql.append(" select balance from B_ACCOUNT where account_num = ?  ");

			pstmt = conn.prepareStatement(sql.toString());



			
			pstmt.setString(1, account_num);
			String user_name = "";
			int balance = 0;
			String nickname = "";

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				balance = rs.getInt(1);
			}
			
			pstmt.clearParameters();

			//if (subbalance > balance) {
				//System.out.println("계좌의 잔금이 원하는 출금액보다 작습니다. ");
			//} else {   
				int set_balance = balance - subbalance;

				StringBuilder sql2 = new StringBuilder();
				sql2.append(" update B_ACCOUNT set balance= ? where account_num= ? ");

				pstmt = conn.prepareStatement(sql2.toString());
				pstmt.setInt(1, set_balance);
				pstmt.setString(2, account_num);
				pstmt.executeUpdate();
				System.out.println(subbalance + "원 출금 후 " + account_num + " 계좌의 잔액은 " + set_balance + "원 입니다.");
			//}
			
			
			pstmt.clearParameters();
			
			
			/*
			 * 입금 로직
			 */
			StringBuilder sql3 = new StringBuilder();
			sql3.append(" select balance from B_ACCOUNT where account_num = ?  ");

			pstmt = conn.prepareStatement(sql3.toString());

			
			pstmt.setString(1, bank_name);
			user_name = "";
			//balance = 0;
			nickname = "";

			ResultSet rs3 = pstmt.executeQuery(); // 에러!
			while (rs3.next()) {

				balance = rs3.getInt("balance");//
			}

			int set_balance2 = addbalance + balance;

			StringBuilder sql4 = new StringBuilder();
			sql4.append(" update B_ACCOUNT set balance= ? where account_num= ? ");
			
			pstmt.clearParameters();
			pstmt = conn.prepareStatement(sql4.toString());
			pstmt.setInt(1, set_balance2);
			pstmt.setString(2, bank_name);
			pstmt.executeUpdate();
			System.out.println(addbalance + "원 입금 후 " + account_num + " 계좌의 잔액은 " + set_balance2 + "원 입니다.");
			
			
			conn.commit();
			

		} catch (Exception e) {
			conn.rollback();
			System.out.println("이체실패");
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	
	
	
	/**
	 * 오픈뱅킹 타 은행 계좌 연동 - 삽입
	 * @param accountVO
	 */
	public void insert(AccountVO accountVO) {

		UserVO userVO = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into b_account(id, bank_name,account_num, balance, nick_name) ");
		sql.append(" values(?,?,?,?,?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, accountVO.getId());
			pstmt.setString(2, accountVO.getBank_name());
			pstmt.setString(3, accountVO.getAccount_num());
			pstmt.setInt(4, accountVO.getBalance());
			pstmt.setString(5, accountVO.getNick_name());
			
			System.out.println(accountVO.getId());
			System.out.println(accountVO.getBank_name());
			System.out.println(accountVO.getAccount_num());
			System.out.println(accountVO.getBalance());
			System.out.println( accountVO.getNick_name());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌연동실패 - 오픈뱅킹");
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 오픈뱅킹 - 나의 모든 계좌 확인
	 * @param id
	 * @return
	 */
	public List<AccountVO> searchAllAccount(String id) {
		List<AccountVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from B_ACCOUNT where id=? ");
		
		try(Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);
			System.out.println("setstring:"+id);

			String bank_name = "";
			int balance = 0;
			String nickname = "";
			String account_num="";
			String reg_date="";

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id=rs.getString("id");
				bank_name = rs.getString("bank_name");
				account_num = rs.getString("account_num");
				balance = rs.getInt("balance");
				nickname = rs.getString("nick_name");
				reg_date = rs.getString("reg_date");
				AccountVO account = new AccountVO(id, bank_name, account_num,balance,nickname,reg_date);
				list.add(account);	
				
				/*
				 * System.out.println(rs.getString("id"));
				 * System.out.println(rs.getString("bank_name"));
				 * System.out.println(rs.getString("account_num"));
				 * System.out.println(rs.getInt("balance"));
				 * System.out.println(rs.getString("nick_name"));
				 * System.out.println(rs.getString("reg_date"));
				 */
			}
		} catch (Exception e) {
			System.out.println("계좌 검색 실패");
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * 이체 시 잔액 확인
	 * @param account_num
	 * @return
	 */
	public int checkBalance(String account_num) {
		List<AccountVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select balance from B_ACCOUNT where account_num=? ");
		
		int resultBalance=0; 
		
		try(Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, account_num);



			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				resultBalance=rs.getInt("balance");

	
				
				/*
				 * System.out.println(rs.getString("id"));
				 * System.out.println(rs.getString("bank_name"));
				 * System.out.println(rs.getString("account_num"));
				 * System.out.println(rs.getInt("balance"));
				 * System.out.println(rs.getString("nick_name"));
				 * System.out.println(rs.getString("reg_date"));
				 */
			}
		} catch (Exception e) {
			System.out.println("계좌 검색 실패");
			e.printStackTrace();
		}
		return resultBalance;
	}
	
	
	
	
	
	
	
	
	
}

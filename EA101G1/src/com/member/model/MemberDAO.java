package com.member.model;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

public class MemberDAO implements MemberDAO_interface {
	
	private static javax.sql.DataSource ds = null;
	
	static {
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO member (mem_id, mem_email, mem_pass, mem_name, mem_icon, mem_phone, mem_addr, bank_acc, card_no, card_yy, card_mm, card_sec, mem_autho, mem_bonus, mem_joindat, mem_birth, mem_warn) VALUES ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT mem_id, mem_email, mem_pass, mem_name, mem_icon, mem_phone, mem_addr, bank_acc, card_no, card_yy, card_mm, card_sec, mem_autho, mem_bonus, to_char(mem_joindat, 'yyyy-mm-dd') mem_joindat, to_char(mem_birth, 'yyyy-mm-dd') mem_birth, mem_warn FROM member ORDER BY mem_id";
	private static final String GET_ONE_STMT = "SELECT mem_id, mem_email, mem_pass, mem_name, mem_icon, mem_phone, mem_addr, bank_acc, card_no, card_yy, card_mm, card_sec, mem_autho, mem_bonus, to_char(mem_joindat, 'yyyy-mm-dd') mem_joindat, to_char(mem_birth, 'yyyy-mm-dd') mem_birth, mem_warn FROM member WHERE mem_id = ?";
	private static final String DELETE = "DELETE FROM member WHERE mem_id = ?";
	private static final String UPDATE = "UPDATE member SET mem_email = ?, mem_pass = ?, mem_name = ?, mem_icon = ?, mem_phone = ?, mem_addr = ?, bank_acc = ?, card_no = ?, card_yy = ?, card_mm = ?, card_sec = ?, mem_autho = ?, mem_bonus = ?, mem_joindat = ?, mem_birth = ?, mem_warn = ? WHERE mem_id = ?";
	private static final String LOGIN = "SELECT mem_id FROM member WHERE mem_email = ?";
	

	@Override
	public String insert(MemberVO memberVO) {
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		String generatedKey = "";
		
		try {
			con = ds.getConnection();
			String[] cols = {"mem_id"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, memberVO.getMem_email());
			pstmt.setString(2, memberVO.getMem_pass());
			pstmt.setString(3, memberVO.getMem_name());
			pstmt.setBytes(4, memberVO.getMem_icon());
			pstmt.setString(5, memberVO.getMem_phone());
			pstmt.setString(6, memberVO.getMem_addr());
			pstmt.setString(7, memberVO.getBank_acc());
			pstmt.setString(8, memberVO.getCard_no());
			pstmt.setString(9, memberVO.getCard_yy());
			pstmt.setString(10, memberVO.getCard_mm());
			pstmt.setString(11, memberVO.getCard_sec());
			pstmt.setInt(12, memberVO.getMem_autho());
			pstmt.setInt(13, memberVO.getMem_bonus());
			pstmt.setDate(14, memberVO.getMem_joindat());
			pstmt.setDate(15, memberVO.getMem_birth());
			pstmt.setInt(16, memberVO.getMem_warn());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			rs.next();
			generatedKey = rs.getString(1);
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return generatedKey;
	}

	@Override
	public void update(MemberVO memberVO) {
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memberVO.getMem_email());
			pstmt.setString(2, memberVO.getMem_pass());
			pstmt.setString(3, memberVO.getMem_name());
			pstmt.setBytes(4, memberVO.getMem_icon());
			pstmt.setString(5, memberVO.getMem_phone());
			pstmt.setString(6, memberVO.getMem_addr());
			pstmt.setString(7, memberVO.getBank_acc());
			pstmt.setString(8, memberVO.getCard_no());
			pstmt.setString(9, memberVO.getCard_yy());
			pstmt.setString(10, memberVO.getCard_mm());
			pstmt.setString(11, memberVO.getCard_sec());
			pstmt.setInt(12, memberVO.getMem_autho());
			pstmt.setInt(13, memberVO.getMem_bonus());
			pstmt.setDate(14, memberVO.getMem_joindat());
			pstmt.setDate(15, memberVO.getMem_birth());
			pstmt.setInt(16, memberVO.getMem_warn());
			pstmt.setString(17, memberVO.getMem_id());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String mem_id) {
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, mem_id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public MemberVO findByPrimaryKey(String mem_id) {

		java.sql.Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		MemberVO memberVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			memberVO = new MemberVO();
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_pass(rs.getString("mem_pass"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_icon(rs.getBytes("mem_icon"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_addr(rs.getString("mem_addr"));
				memberVO.setBank_acc(rs.getString("bank_acc"));
				memberVO.setCard_no(rs.getString("card_no"));
				memberVO.setCard_yy(rs.getString("card_yy"));
				memberVO.setCard_mm(rs.getString("card_mm"));
				memberVO.setCard_sec(rs.getString("card_sec"));
				memberVO.setMem_autho(rs.getInt("mem_autho"));
				memberVO.setMem_bonus(rs.getInt("mem_bonus"));
				memberVO.setMem_joindat(rs.getDate("mem_joindat"));
				memberVO.setMem_birth(rs.getDate("mem_birth"));
				memberVO.setMem_warn(rs.getInt("mem_warn"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		java.util.List<MemberVO> list = null;
		MemberVO memberVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			list = new java.util.ArrayList<MemberVO>();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_pass(rs.getString("mem_pass"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_icon(rs.getBytes("mem_icon"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_addr(rs.getString("mem_addr"));
				memberVO.setBank_acc(rs.getString("bank_acc"));
				memberVO.setCard_no(rs.getString("card_no"));
				memberVO.setCard_yy(rs.getString("card_yy"));
				memberVO.setCard_mm(rs.getString("card_mm"));
				memberVO.setCard_sec(rs.getString("card_sec"));
				memberVO.setMem_autho(rs.getInt("mem_autho"));
				memberVO.setMem_bonus(rs.getInt("mem_bonus"));
				memberVO.setMem_joindat(rs.getDate("mem_joindat"));
				memberVO.setMem_birth(rs.getDate("mem_birth"));
				memberVO.setMem_warn(rs.getInt("mem_warn"));
				list.add(memberVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public String loginByEmail(String mem_email) {
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		String mem_id= null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, mem_email);
			rs = pstmt.executeQuery();
			rs.next();
			mem_id = rs.getString("mem_id");
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} 
		return mem_id;
	}
}

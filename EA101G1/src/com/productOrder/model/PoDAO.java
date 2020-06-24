package com.productOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poductOrderList.model.PolDAO;
import com.poductOrderList.model.PolVO;


public class PoDAO implements PoDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DANNY";
	String passwd = "123456";

	private static final String INSERT = "INSERT INTO PRODUCT_ORDER(PO_ID,MEM_ID,ORDSTAT_ID) VALUES(TO_CHAR(sysdate,'yyyy-mm-dd')||'-'||LPAD(TO_CHAR(SEQ_PO_ID.NEXTVAL),6,'0'),?,?)";
	private static final String UPDATE = "UPDATE PRODUCT_ORDER SET ORDSTAT_ID=?,RETURN_FORM=? WHERE PO_ID=?";
	private static final String DELETE = "DELETE FROM PRODUCT_ORDER WHERE PO_ID=?";
	private static final String GET_ALL_STMT = "SELECT PO_ID,MEM_ID,ORDSTAT_ID,to_char(ADD_DATE,'yyyy-mm-dd') ADD_DATE,RETURN_FORM FROM PRODUCT_ORDER ORDER BY PO_ID";
	private static final String GET_ONE_STMT = "SELECT PO_ID,MEM_ID,ORDSTAT_ID,to_char(ADD_DATE,'yyyy-mm-dd') ADD_DATE,RETURN_FORM FROM PRODUCT_ORDER WHERE PO_ID=?";
	@Override
	
	public void insert(PoVO poVO , List<PolVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			con.setAutoCommit(false);
			
			String cols[] = {"Po_id"};
			pstmt = con.prepareStatement(INSERT,cols);
			
			pstmt.setString(1, poVO.getMem_id());
			pstmt.setString(2, poVO.getOrdstat_id());
			pstmt.executeUpdate();
			
			String next_po_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_po_id = rs.getString(1);
				System.out.println("自增主鍵值" +next_po_id + "(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			
			rs.close();
			
			PolDAO dao = new PolDAO();
			System.out.println("list.size()-A="+list.size());
			for (PolVO pol : list) {
				pol.setPo_id(new String(next_po_id)) ;
				dao.insert2(pol,con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(PoVO poVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, poVO.getOrdstat_id());
			pstmt.setString(2, poVO.getReturn_form());
			pstmt.setString(3, poVO.getPo_id());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String po_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, po_id);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public PoVO findByPrimaryKey(String po_id) {
		PoVO poVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, po_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				poVO = new PoVO();
				poVO.setPo_id(rs.getString("po_id"));
				poVO.setMem_id(rs.getString("mem_id"));
				poVO.setOrdstat_id(rs.getString("ordstat_id"));
				poVO.setAdd_date(rs.getDate("add_date"));
				poVO.setReturn_form(rs.getString("return_form"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return poVO;

	}
	
	@Override
	public List<PoVO> getAll() {
		List<PoVO> list = new ArrayList<PoVO>();
		PoVO poVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				poVO = new PoVO();
				poVO.setPo_id(rs.getString("po_id"));
				poVO.setMem_id(rs.getString("mem_id"));
				poVO.setOrdstat_id(rs.getString("ordstat_id"));
				poVO.setAdd_date(rs.getDate("add_date"));
				poVO.setReturn_form(rs.getString("return_form"));
				list.add(poVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
}

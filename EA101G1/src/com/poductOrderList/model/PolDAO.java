package com.poductOrderList.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PolDAO implements com.poductOrderList.model.PolDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DANNY";
	String passwd = "123456";
	
	private static final String INSERT="INSERT INTO PRODUCT_ORDER_LIST(PO_ID,P_ID,ORDER_QUA,P_PRICE) VALUES(?,?,?,?)";
	private static final String DELETE="DELETE FROM PRODUCT_ORDER_LIST WHERE PO_ID=? AND P_ID=?";
	private static final String GET_ALL_STMT="SELECT PO_ID,P_ID,ORDER_QUA,P_PRICE FROM PRODUCT_ORDER_LIST ORDER BY PO_ID";
	private static final String GET_ONE_STMT="SELECT PO_ID,P_ID,ORDER_QUA,P_PRICE FROM PRODUCT_ORDER_LIST WHERE PO_ID=? AND P_ID=?";
	@Override
	public void insert(PolVO polVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,polVO.getPo_id());
			pstmt.setString(2,polVO.getP_id());
			pstmt.setInt(3,polVO.getOrder_qua());
			pstmt.setDouble(4,polVO.getP_price());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
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
	
	public void insert2(PolVO polVO ,Connection con) {

		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,polVO.getPo_id());
			pstmt.setString(2,polVO.getP_id());
			pstmt.setInt(3,polVO.getOrder_qua());
			pstmt.setDouble(4,polVO.getP_price());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
		}

	}
	
	@Override
	public void delete(String po_id, String p_id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,po_id);
			pstmt.setString(2,p_id);
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
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
	public PolVO findByPrimaryKey(String po_id, String p_id) {
		 PolVO polVO = null;
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url,userid,passwd);
			 pstmt = con.prepareStatement(GET_ONE_STMT);
			 
			 pstmt.setString(1,po_id);
			 pstmt.setString(2,p_id);
			 
			 rs = pstmt.executeQuery();
			 
			 while (rs.next()) {
				 
				 polVO = new PolVO();
				 
				 polVO.setPo_id(rs.getString("po_id"));
				 polVO.setP_id(rs.getString("p_id"));
				 polVO.setOrder_qua(rs.getInt("order_qua"));
				 polVO.setP_price(rs.getDouble("p_price"));
			 }
			 
			 
		 }catch (ClassNotFoundException e) {
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
		return polVO;
	}
	@Override
	public List<PolVO> getAll() {
		 List<PolVO> list= new ArrayList<PolVO>();
		 PolVO polVO = null;
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url,userid,passwd);
			 pstmt = con.prepareStatement(GET_ALL_STMT);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 polVO = new PolVO();
				 
				 polVO.setPo_id(rs.getString("po_id"));
				 polVO.setP_id(rs.getString("p_id"));
				 polVO.setOrder_qua(rs.getInt("order_qua"));
				 polVO.setP_price(rs.getDouble("p_price"));
				 
				 list.add(polVO);
			 }
			 
		 }catch (ClassNotFoundException e) {
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



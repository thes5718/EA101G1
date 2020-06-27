package com.favouriteProduct.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavpDAO implements com.favouriteProduct.model.FavpDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DANNY";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO FAVOURITE_PRODUCT(P_ID,MEM_ID) VALUES (?,?)";
	private static final String GET_FAVP_BY_MEM_STMT = "SELECT P_ID , MEM_ID FROM FAVOURITE_PRODUCT WHERE MEM_ID = ?";
	private static final String GET_ONE_FAVP_STMT = "SELECT P_ID , MEM_ID FROM FAVOURITE_PRODUCT WHERE P_ID=? AND MEM_ID=?";
	private static final String DELETE = "DELETE FROM FAVOURITE_PRODUCT WHERE P_ID = ? AND MEM_ID = ?";
	@Override
	public void insert(FavpVO favpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);		
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,favpVO.getP_id());
			pstmt.setString(2,favpVO.getMem_id());
			
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
	public void delete(String p_id, String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, p_id);
			pstmt.setString(2, mem_id);
			
			pstmt.executeQuery();
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
	public List<FavpVO> getFavpByMem(String mem_id) {
		List<FavpVO> list = new ArrayList<FavpVO>();
		FavpVO favpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_FAVP_BY_MEM_STMT);
			pstmt.setString(1,mem_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favpVO = new FavpVO();
				
				favpVO.setMem_id(rs.getString("mem_id"));
				favpVO.setP_id(rs.getString("p_id"));
				
				list.add(favpVO);
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
	@Override
	public FavpVO getOneFavp(String p_id, String mem_id) {
		FavpVO favpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FAVP_STMT);
			pstmt.setString(1,p_id);
			pstmt.setString(2,mem_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favpVO = new FavpVO();
				
				favpVO.setMem_id(mem_id);
				favpVO.setP_id(p_id);
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
		return favpVO;
	}
	
	
}

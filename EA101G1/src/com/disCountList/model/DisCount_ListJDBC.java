package com.disCountList.model;


import java.sql.*;
import java.util.*;





public class DisCount_ListJDBC implements DisCount_List_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G1";
	String passwd = "123456";
	
	public static final String INSERT_STMT="INSERT INTO DISCOUNT_LIST(P_ID,DIS_ID,DIS_PRICE) VALUES(?,?,?)";
	public static final String UPDATE_STMT="UPDATE DISCOUNT_LIST SET DIS_PRICE =? WHERE P_ID =? AND DIS_ID =? ";
	public static final String DELETE="delete from DISCOUNT_LIST where  P_ID = ? AND DIS_ID =? ";
	public static final String FIND_ONE_PK="select P_ID,DIS_ID,DIS_PRICE from DISCOUNT_LIST where P_ID =? and DIS_ID =?";
	public static final String GET_ALL_STMT="select P_ID,DIS_ID,DIS_PRICE from DISCOUNT_LIST";
	
	@Override
	public void insert(DisCount_ListVO disCount_ListVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1,disCount_ListVO.getP_id());
			pstmt.setString(2, disCount_ListVO.getDis_id());
			pstmt.setInt(3,disCount_ListVO.getDis_price());
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void update(DisCount_ListVO disCount_ListVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1,disCount_ListVO.getDis_price());
			pstmt.setString(2,disCount_ListVO.getP_id());
			pstmt.setString(3,disCount_ListVO.getDis_id());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void delete(String p_id,String dis_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,p_id);
			pstmt.setString(2, dis_id);
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public DisCount_ListVO findByPrimaryKey(String p_id,String dis_id) {
		// TODO Auto-generated method stub
		DisCount_ListVO disclVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ONE_PK);
			
			pstmt.setString(1, p_id);
			pstmt.setString(2, dis_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				disclVO=new DisCount_ListVO();
				disclVO.setP_id(rs.getString("p_id"));
				disclVO.setDis_id(rs.getString("dis_id"));
				disclVO.setDis_price(rs.getInt("dis_price"));
			}
					
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return disclVO;
	}

	@Override
	public List<DisCount_ListVO> getAll() {
		List<DisCount_ListVO> list = new ArrayList<DisCount_ListVO>();
		DisCount_ListVO diclVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				diclVO=new DisCount_ListVO();
				diclVO.setP_id(rs.getString("p_id"));
				diclVO.setDis_id(rs.getString("dis_id"));
				diclVO.setDis_price(rs.getInt("dis_price"));
				list.add(diclVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		// TODO Auto-generated method stub
		return list;
	}
	
	public static void main(String[] args) {
		
		DisCount_ListJDBC dao=new DisCount_ListJDBC();
//		DisCount_ListVO diclVO=new DisCount_ListVO();
		
		//insert
//		diclVO.setP_id("P002");
//		diclVO.setDis_id("DIS001");
//		diclVO.setDis_price(1121);
//		dao.insert(diclVO);
		
		//console�雿�� Update
//		diclVO.setDis_price(2000);
//		diclVO.setP_id("P003");
//		diclVO.setDis_id("DIS003");
//		dao.update(diclVO);
		
		//delete
//		dao.delete("P003", "DIS001");
		
		//��蝑�
//		diclVO=dao.findByPrimaryKey("P001","DIS001");
//		System.out.print(diclVO.getP_id()+",");
//		System.out.print(diclVO.getDis_id()+",");
//		System.out.print(diclVO.getDis_price());
//		System.out.println();
		
		//���
		List<DisCount_ListVO> list = dao.getAll();
		for (DisCount_ListVO diclVO : list) {
		System.out.print(diclVO.getP_id()+",");
		System.out.print(diclVO.getDis_id()+",");
		System.out.print(diclVO.getDis_price());
		System.out.println();
		}
		
	}

}

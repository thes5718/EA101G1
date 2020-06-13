package com.disCount.model;

import java.sql.*;
import java.util.*;

public class DisCountJDBC implements DisCountDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DANNY";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO DISCOUNT(DIS_ID,DIS_NAME,STAR_DATE,END_DATE) VALUES ('DIS'||LPAD(TO_CHAR(SEQ_DIS_ID.NEXTVAL),3,'0'),?,?,?)";
	public static final String GET_ALL_STMT = "SELECT * FROM DISCOUNT";
	public static final String UPDATE_STMT = "UPDATE DISCOUNT SET STAR_DATE=?,END_DATE=?,DIS_NAME=? WHERE DIS_ID=?";
	public static final String DELETE_STMT = "DELETE FROM DISCOUNT WHERE DIS_ID = ?";
	public static final String FIND_ONE_PK="SELECT DIS_ID,DIS_NAME,STAR_DATE,END_DATE FROM DISCOUNT WHERE DIS_ID = ?";
	
	@Override
	public void insert(DisCountVO discountVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, discountVO.getDis_name());
			pstmt.setTimestamp(2, discountVO.getStar_date());
			pstmt.setTimestamp(3, discountVO.getEnd_date());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
					// TODO: handle exception
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
					// TODO: handle exception
				}
			}
		}
	}

	@Override
	public void update(DisCountVO disCountVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setTimestamp(1, disCountVO.getStar_date());
			pstmt.setTimestamp(2, disCountVO.getEnd_date());
			pstmt.setString(3, disCountVO.getDis_name());

			pstmt.setString(4, disCountVO.getDis_id());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
					// TODO: handle exception
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
					// TODO: handle exception
				}
			}
		}

	}

	// �雿�
	@Override
	public void delete(String dis_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, dis_id);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
					// TODO: handle exception
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
					// TODO: handle exception
				}
			}
		}
	}

	@Override
	public DisCountVO findByPrimary(String dis_id) {
		DisCountVO dicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ONE_PK);
			
			pstmt.setString(1, dis_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dicVO = new DisCountVO();
				dicVO.setDis_id(rs.getString("dis_id"));
				dicVO.setDis_name(rs.getString("dis_name"));
				dicVO.setStar_date(rs.getTimestamp("star_date"));
				dicVO.setEnd_date(rs.getTimestamp("end_date"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return dicVO;
	}
	
	@Override
	public List<DisCountVO> getAll() {
		List<DisCountVO> list = new ArrayList<DisCountVO>();
		DisCountVO dicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dicVO = new DisCountVO();
				dicVO.setDis_id(rs.getString("dis_id"));
				dicVO.setDis_name(rs.getString("dis_name"));
				dicVO.setStar_date(rs.getTimestamp("star_date"));
				dicVO.setEnd_date(rs.getTimestamp("end_date"));
				list.add(dicVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return list;
	}


	public static void main(String[] args) {
		DisCountJDBC dao = new DisCountJDBC();
		DisCountVO dicVO = new DisCountVO();

		// insert
//	dicVO.setDis_name("哈哈哈");
//	dicVO.setStar_date(java.sql.Timestamp.valueOf("2020-05-08 11:12:55"));
//	dicVO.setEnd_date(java.sql.Timestamp.valueOf("2020-11-16 11:24:55"));
//	dao.insert(dicVO);

		// update
//	dicVO.setStar_date(java.sql.Timestamp.valueOf("2020-05-08 11:12:55"));
//	dicVO.setEnd_date(java.sql.Timestamp.valueOf("2020-11-16 11:24:55"));
//		dicVO.setDis_name("瘥扛蝭�����");
//	dicVO.setDis_id("DIS003");
//	dao.update(dicVO);
		
		//delete
//		dao.delete("DIS023");
//		
		//�蝑閰�
//		DisCountVO dicVO2 = dao.findByPrimary("DIS001");
//			System.out.print(dicVO2.getDis_id() + ",");
//			System.out.print(dicVO2.getDis_name() + " ,");
//			System.out.print(dicVO2.getStar_date() + " ,");
//			System.out.print(dicVO2.getStar_date() + " ,");
//			System.out.println();
//		
		//��閰�
//		List<DisCountVO> list = dao.getAll();
//		for (DisCountVO dicVO2 : list) {
//			System.out.print(dicVO2.getDis_id() + ",");
//			System.out.print(dicVO2.getDis_name() + ",");
//			System.out.print(dicVO2.getStar_date() + ",");
//			System.out.print(dicVO2.getStar_date() + ",");
//			
//			System.out.println();
//		}
	}

}

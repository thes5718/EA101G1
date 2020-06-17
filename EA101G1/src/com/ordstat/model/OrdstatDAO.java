package com.ordstat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdstatDAO implements OrdstatDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DANNY";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ordstat(ordstat_id,ordstat) VALUES (LPAD(to_char(ORDSTAT_SEQ.NEXTVAL), 3, '0'）,?)";
	private static final String UPDATE_STMT = "UPDATE ordstat set ordstat=? where ordstat_id=?";
	
//	private static final String DELETE_PRODUCT_LIST_STMT ="DELETE FROM product_order_ where po_id=?";
//	private static final String DELETE_PRODUCT_STMT ="DELETE FROM product where po_id=?";
//	private static final String DELETE_BONUS_STMT ="DELETE FROM bonus where ord_id=?";
//	private static final String DELETE_IMMED_STMT ="DELETE FROM immed where immed_id=?";
//	private static final String DELETE_AUCT_STMT ="DELETE FROM auct where auct_id=?";
//	private static final String DELETE_GRO_STMT ="DELETE FROM gro_order where ord_id=?";
	private static final String DELETE_Ordstat_STMT = "DELETE FROM ordstat where ordstat_id=?";
	
	private static final String GET_ALL_STMT = "SELECT * FROM ordstat order by ordstat_id";

	//新增訂單狀態
	@Override
	public void insert(OrdstatVO ordstatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ordstatVO.getOrdstat());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	//修改訂單狀態
	@Override
	public void update(OrdstatVO ordstatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1,ordstatVO.getOrdstat());
			pstmt.setString(2, ordstatVO.getOrdstat_id());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//刪除訂單狀態
	@Override
	public void delete(String ordstat_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_Ordstat_STMT);
			
			pstmt.setString(1, ordstat_id);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	//查詢全部訂單狀態
	@Override
	public List<OrdstatVO> getAll() {
		List<OrdstatVO> list = new ArrayList<OrdstatVO>(); 
		OrdstatVO ordstatVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ordstatVO = new OrdstatVO();
				ordstatVO.setOrdstat_id(rs.getString("ordstat_id"));
				ordstatVO.setOrdstat(rs.getString("ordstat"));
				list.add(ordstatVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

}










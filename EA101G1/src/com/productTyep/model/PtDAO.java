package com.productTyep.model;

import java.util.List;

public class PtDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DANNY";
	String passwd = "123456";
	
	private static final String INSERT = "INSERT INTO PRODUCT_TYPE(PT_ID,TYPENAME) VALUES(?,?)";
	private static final String UPDATE = "UPDATE PRODUCT_TYPE SET PT_ID=?,TYPENAME=? WHERE PT_ID=?";
	private static final String DELETE = "DELETE FROM PRODUCT_TYPE WHERE PT_ID=?";
	private static final String GET_ALL_STMT = "SELECT PT_ID,TYPENAME FROM PRODUCT_TYPE ORDER BY PT_ID";
	private static final String GET_ONE_STMT = "SELECT PT_ID,TYPENAME FROM PRODUCT_TYPE WHERE PT_ID=?";
	
	public void insert(PtVO proVO) {
		
		
	}
	
	public void update(PtVO proVO) {}
	
	public void delete(String p_id) {}
	
	public PtVO findByPrimaryKey(String pt_id ) {
		
		
	}
	
	public List<PtVO> getAll(){
		
		
	}
	
}

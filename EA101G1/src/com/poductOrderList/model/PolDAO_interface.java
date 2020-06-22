package com.poductOrderList.model;

import java.util.*;

import com.productOrder.model.PoVO;

public interface PolDAO_interface {
		public void insert(PolVO polVO);
		public void insert2(PolVO polVO, java.sql.Connection con);
		public void delete(String po_id,String p_id);
		public PolVO findByPrimaryKey(String po_id, String p_id);
		public List<PolVO> getAll();
		
		
}

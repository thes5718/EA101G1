package com.poductOrderList;

import java.util.*;

public interface PolDAO_interface {
		public void insert (PolVO polVO );
		public void delete(String po_id,String p_id);
		public PolVO findByPrimaryKey(String po_id, String p_id);
		public List<PolVO> getAll();

}

package com.productType.model;

import java.util.List;

public interface PtDAO_interface {
	
	public interface ProDAO_interface {
		public void insert(PtVO proVO);
		public void update(PtVO proVO);
		public void delete(String p_id);
		public PtVO findByPrimaryKey(String pt_id );
		public List<PtVO> getAll();
}
}

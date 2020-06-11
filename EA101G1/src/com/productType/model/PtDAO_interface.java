package com.productType.model;

import java.util.List;

public interface PtDAO_interface {
	
	public interface ProDAO_interface {
		public void insert(PtVO ptVO);
		public void update(PtVO ptVO);
		public void delete(String pt_id);
		public PtVO findByPrimaryKey(String pt_id );
		public List<PtVO> getAll();
}
}

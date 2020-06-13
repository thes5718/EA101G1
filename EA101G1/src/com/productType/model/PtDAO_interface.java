package com.productType.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProVO;


public interface PtDAO_interface {
	
	public void insert(PtVO ptVO);
	public void update(PtVO ptVO);
	public void delete(String pt_id);
	public PtVO findByPrimaryKey(String pt_id );
	public List<PtVO> getAll();
	public Set<ProVO> getProductByPtid(String pt_id);
}

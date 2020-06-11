package com.productOrder.model;

import java.util.List;


public interface PoDAO_interface {
	
	public void insert(PoVO poVO);
	public void update(PoVO poVO);
	public void delete(String po_id);
	public PoVO findByPrimaryKey(String po_id );
	public List<PoVO> getAll();
}

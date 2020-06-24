package com.productOrder.model;

import java.util.List;

import com.poductOrderList.model.PolVO;


public interface PoDAO_interface {
	
	public void insert(PoVO poVO , List<PolVO> list);//連明細一起新增
	public void update(PoVO poVO);
	public void delete(String po_id);
	public PoVO findByPrimaryKey(String po_id );
	public List<PoVO> getAll();
}

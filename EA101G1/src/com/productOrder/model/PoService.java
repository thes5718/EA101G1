package com.productOrder.model;

import java.util.List;

import com.productOrderList.model.PolVO;

public class PoService {
	
	private PoDAO_interface dao;
	
	public PoService() {
		dao = new PoDAO();
	}
	
	public void AddOrder (String mem_id , Double amount, List<PolVO> list) {
		PoVO poVO = new PoVO();
		poVO.setMem_id(mem_id);
		poVO.setAmount(amount);
		
		dao.insert(poVO, list);
	}
	
	public List<PoVO> getAll(){
		
		return dao.getAll();
	}
	
	public PoVO getOne(String po_id) {
		
		return dao.findByPrimaryKey(po_id);
	}
	
}

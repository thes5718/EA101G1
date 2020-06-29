package com.productOrder.model;

import java.util.List;

import com.productOrderList.model.PolVO;

public class PoService {
	
	private PoDAO_interface dao;
	
	public PoService() {
		dao = new PoDAO();
	}
	
	public void AddOrder (String mem_id, List<PolVO> list) {
		PoVO poVO = new PoVO();
		poVO.setMem_id(mem_id);
		
		dao.insert(poVO, list);
	}
}

package com.productOrder.model;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<PoVO> getOrder(String ordstat_id){
		List<PoVO> list = dao.getAll().stream()
				.filter(p ->p.getOrdstat_id().equals(ordstat_id))
				.collect(Collectors.toList());
		
		return list;
	}
	
	public PoVO getOne(String po_id) {
		
		return dao.findByPrimaryKey(po_id);
	}
	
	public void updateOrdStat(String po_id,  String ordstat_id) {
		PoVO poVO = new PoVO();
		poVO.setOrdstat_id(ordstat_id);
		poVO.setPo_id(po_id);
		
		dao.update(poVO);
	}
	
}

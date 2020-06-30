package com.productOrderList.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PolService {

	private PolDAO_interface dao;
	
	public PolService() {
		dao = new PolDAO();
	}
	
	public List<PolVO> getPolbyPoId(String po_id) {
		List<PolVO> list = dao.getAll().stream()
				.filter(p ->p.getPo_id().equals(po_id))
				.collect(Collectors.toList());
		return list;
	}
}

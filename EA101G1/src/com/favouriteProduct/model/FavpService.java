package com.favouriteProduct.model;

import java.util.List;

public class FavpService {
	
	private FavpDAO_interface dao;
	
	public FavpService() {
		dao = new FavpDAO();
	}
	
	public List<FavpVO> getProductByMem(String mem_id) {
		
		return dao.getFavpByMem(mem_id);
	}
	
	public void deleteFavp(String p_id,String mem_id) {
		dao.delete(p_id, mem_id);
	}
}

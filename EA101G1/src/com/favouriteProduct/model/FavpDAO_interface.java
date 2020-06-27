package com.favouriteProduct.model;

import java.util.List;


public interface FavpDAO_interface  {
	
	public void insert(FavpVO favpVO);
	public void delete(String p_id , String mem_id);
	public FavpVO getOneFavp(String p_id , String mem_id);
	public List<FavpVO> getFavpByMem(String mem_id);
}

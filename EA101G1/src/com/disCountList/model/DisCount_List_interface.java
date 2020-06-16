package com.disCountList.model;

import java.util.List;



public interface DisCount_List_interface {
	public void insert(DisCount_ListVO disCount_ListVO);
	public void update(DisCount_ListVO disCount_ListVO);
	public void delete(String p_id,String dis_id);
    public DisCount_ListVO findByPrimaryKey(String p_id,String dis_id);
    public List<DisCount_ListVO> getAll();

}

package com.disCount.model;

import java.util.List;
import java.util.Set;

public interface DisCountDAO_interface {
	public void insert(DisCountVO discountVO);
	public void update(DisCountVO disCountVO);
	public void delete(String dis_id);
	public List<DisCountVO> getAll();
	public DisCountVO findByPrimary(String dis_id);

}

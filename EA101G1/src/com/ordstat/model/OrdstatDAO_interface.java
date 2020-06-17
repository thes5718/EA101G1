package com.ordstat.model;

import java.util.List;

public interface OrdstatDAO_interface {
	public void insert(OrdstatVO ordstatVO);
	public void update(OrdstatVO ordstatVO);
	public void delete(String ordstat_id);
	public List<OrdstatVO> getAll();

}

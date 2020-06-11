package com.productOrder.model;

import oracle.sql.DATE;

public class PoVO {
	private String po_id;
	private String mem_no;
	private Integer ordstat_id;
	private DATE add_date;
	private String return_form;
	
	public String getPo_id() {
		return po_id;
	}
	public void setPo_id(String po_id) {
		this.po_id = po_id;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getOrdstat_id() {
		return ordstat_id;
	}
	public void setOrdstat_id(Integer ordstat_id) {
		this.ordstat_id = ordstat_id;
	}
	public DATE getAdd_date() {
		return add_date;
	}
	public void setAdd_date(DATE add_date) {
		this.add_date = add_date;
	}
	public String getReturn_form() {
		return return_form;
	}
	public void setReturn_form(String return_form) {
		this.return_form = return_form;
	}
}

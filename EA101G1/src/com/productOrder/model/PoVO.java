package com.productOrder.model;

import java.sql.Date;


public class PoVO {
	private String po_id;
	private String mem_id;
	private String ordstat_id;
	private Date add_date;
	private String return_form;
	private Double amount;
	
	
	public PoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPo_id() {
		return po_id;
	}
	public void setPo_id(String po_id) {
		this.po_id = po_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getOrdstat_id() {
		return ordstat_id;
	}
	public void setOrdstat_id(String ordstat_id) {
		this.ordstat_id = ordstat_id;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date date) {
		this.add_date = date;
	}
	public String getReturn_form() {
		return return_form;
	}
	public void setReturn_form(String return_form) {
		this.return_form = return_form;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}

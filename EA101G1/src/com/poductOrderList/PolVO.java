package com.poductOrderList;

import java.io.Serializable;

public class PolVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String po_id;
	private String p_id;
	private Integer order_qua;
	private Double p_price;
	
	public String getPo_id() {
		return po_id;
	}
	public void setPo_id(String po_id) {
		this.po_id = po_id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public Integer getOrder_qua() {
		return order_qua;
	}
	public void setOrder_qua(Integer order_qua) {
		this.order_qua = order_qua;
	}
	public Double getP_price() {
		return p_price;
	}
	public void setP_price(Double p_price) {
		this.p_price = p_price;
	}
}

package com.shopCart.model;

public class PRODUCT implements java.io.Serializable{
	
	public PRODUCT() {
		p_id = "";
		p_name ="";
		quantity = 0;
		p_price = 0.0;
		p_stock = 0;
		sub = 0.0;
	}
	
	private String p_id;
	private String p_name;
	private Integer quantity;
	private Double p_price;
	private Integer p_stock;
	private Double sub;
	

	public String getId() {
		return p_id;
	}
	
	public void setId(String p_id) {
		this.p_id = p_id;
	}
	public String getName() {
		return p_name;
	}
	public void setName(String name) {
		this.p_name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return p_price;
	}
	public void setPrice(Double price) {
		this.p_price = price;
	}
	public Integer getStock() {
		return p_stock;
	}

	public void setStock(Integer p_stock) {
		this.p_stock = p_stock;
	}
	public Double getSub() {
		return sub;
	}

	public void setSub(Double sub) {
		this.sub = sub;
	}
}

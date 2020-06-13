package com.disCount.model;

import java.sql.*;

public class DisCountVO {

	private String dis_id;
	private String dis_name;
	private Timestamp star_date;
	private Timestamp end_date;
	
	
	public DisCountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDis_id() {
		return dis_id;
	}
	public void setDis_id(String dis_id) {
		this.dis_id = dis_id;
	}
	public String getDis_name() {
		return dis_name;
	}
	public void setDis_name(String dis_name) {
		this.dis_name = dis_name;
	}
	public Timestamp getStar_date() {
		return star_date;
	}
	public void setStar_date(Timestamp star_date) {
		this.star_date = star_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	
	
}

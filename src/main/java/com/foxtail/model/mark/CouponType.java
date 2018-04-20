package com.foxtail.model.mark;

public class CouponType {
    
	private int id;
	private String coupontypename;//优惠卷分类名称
	private int sort;//排序
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoupontypename() {
		return coupontypename;
	}
	
	public void setCoupontypename(String coupontypename) {
		this.coupontypename = coupontypename;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}

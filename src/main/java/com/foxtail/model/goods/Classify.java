package com.foxtail.model.goods;

public class Classify {
	
	private int id;//商品分类ID
	private String goodsClassifyName;//商品分类名称
	private String goodsClassifyPhoto;//商品分类图片
	private int goodsClassifySort;//商品分类排序
	
	public Classify() {}
	
	
	
	public Classify(int id, String goodsClassifyName, String goodsClassifyPhoto, int goodsClassifySort) {
		super();
		this.id = id;
		this.goodsClassifyName = goodsClassifyName;
		this.goodsClassifyPhoto = goodsClassifyPhoto;
		this.goodsClassifySort = goodsClassifySort;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsClassifyName() {
		return goodsClassifyName;
	}
	public void setGoodsClassifyName(String goodsClassifyName) {
		this.goodsClassifyName = goodsClassifyName;
	}
	public String getGoodsClassifyPhoto() {
		return goodsClassifyPhoto;
	}
	public void setGoodsClassifyPhoto(String goodsClassifyPhoto) {
		this.goodsClassifyPhoto = goodsClassifyPhoto;
	}
	public int getGoodsClassifySort() {
		return goodsClassifySort;
	}
	public void setGoodsClassifySort(int goodsClassifySort) {
		this.goodsClassifySort = goodsClassifySort;
	}
	
	

}

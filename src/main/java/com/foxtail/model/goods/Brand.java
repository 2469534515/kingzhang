package com.foxtail.model.goods;

public class Brand {
	
	private int id;//品牌Id
	private String brandName;//品牌名称
	private String brandClassify;//品牌所属分类
	private String brandPhoto;//品牌图片
	private int brandSort;//品牌排序
	private int brandStatus;//品牌状态
	
	public Brand() {}
	
	
	
	public Brand(int id, String brandName, String brandClassify, String brandPhoto, int brandSort, int brandStatus) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.brandClassify = brandClassify;
		this.brandPhoto = brandPhoto;
		this.brandSort = brandSort;
		this.brandStatus = brandStatus;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandClassify() {
		return brandClassify;
	}
	public void setBrandClassify(String brandClassify) {
		this.brandClassify = brandClassify;
	}
	public String getBrandPhoto() {
		return brandPhoto;
	}
	public void setBrandPhoto(String brandPhoto) {
		this.brandPhoto = brandPhoto;
	}
	public int getBrandSort() {
		return brandSort;
	}
	public void setBrandSort(int brandSort) {
		this.brandSort = brandSort;
	}
	public int getBrandStatus() {
		return brandStatus;
	}
	public void setBrandStatus(int brandStatus) {
		this.brandStatus = brandStatus;
	}
	
	

}

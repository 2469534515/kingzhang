package com.foxtail.model.goods;

public class Goods {
	
	private int id;//商品ID
	private String goodsClassify;//商品所属分类
	private String goodsName;//商品名称
	private String goodsOutline;//商品概要
	private String goodsBrand;//商品品牌
	private String goodsMasterPhoto;//商品主图
	private String goodsType;//商品类型
	private int goodsSort;//商品排序
	private String goodsFormat;//商品规格
	private Double goodsPrice;//商品价格
	private Double marketPrice;//市场价格
	private int goodsStock;//商品库存
	private int goodsSales;//商品销量
	private String detailedInfor;//详细内容
	private Long agTime;//添加商品时间
	private int goodsStatus;//商品状态
	
	
	public Goods() {}
	


	


	public Goods(int id, String goodsClassify, String goodsName, String goodsOutline, String goodsBrand,
			String goodsMasterPhoto, String goodsType, int goodsSort, String goodsFormat, Double goodsPrice,
			Double marketPrice, int goodsStock, int goodsSales, String detailedInfor, Long agTime, int goodsStatus,
			Long beforeTime, Long afterTime) {
		super();
		this.id = id;
		this.goodsClassify = goodsClassify;
		this.goodsName = goodsName;
		this.goodsOutline = goodsOutline;
		this.goodsBrand = goodsBrand;
		this.goodsMasterPhoto = goodsMasterPhoto;
		this.goodsType = goodsType;
		this.goodsSort = goodsSort;
		this.goodsFormat = goodsFormat;
		this.goodsPrice = goodsPrice;
		this.marketPrice = marketPrice;
		this.goodsStock = goodsStock;
		this.goodsSales = goodsSales;
		this.detailedInfor = detailedInfor;
		this.agTime = agTime;
		this.goodsStatus = goodsStatus;
		this.beforeTime = beforeTime;
		this.afterTime = afterTime;
	}






	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsClassify() {
		return goodsClassify;
	}
	public void setGoodsClassify(String goodsClassify) {
		this.goodsClassify = goodsClassify;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsOutline() {
		return goodsOutline;
	}
	public void setGoodsOutline(String goodsOutline) {
		this.goodsOutline = goodsOutline;
	}
	public String getGoodsBrand() {
		return goodsBrand;
	}
	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}
	public String getGoodsMasterPhoto() {
		return goodsMasterPhoto;
	}
	public void setGoodsMasterPhoto(String goodsMasterPhoto) {
		this.goodsMasterPhoto = goodsMasterPhoto;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public int getGoodsSort() {
		return goodsSort;
	}
	public void setGoodsSort(int goodsSort) {
		this.goodsSort = goodsSort;
	}
	public String getGoodsFormat() {
		return goodsFormat;
	}
	public void setGoodsFormat(String goodsFormat) {
		this.goodsFormat = goodsFormat;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public int getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}
	public int getGoodsSales() {
		return goodsSales;
	}
	public void setGoodsSales(int goodsSales) {
		this.goodsSales = goodsSales;
	}
	public String getDetailedInfor() {
		return detailedInfor;
	}
	public void setDetailedInfor(String detailedInfor) {
		this.detailedInfor = detailedInfor;
	}
	
	
	public Long getAgTime() {
		return agTime;
	}

	public void setAgTime(Long agTime) {
		this.agTime = agTime;
	}



	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	//多条件查询
	private Long beforeTime;
	private Long afterTime;

	public Long getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(Long beforeTime) {
		this.beforeTime = beforeTime;
	}

	public Long getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(Long afterTime) {
		this.afterTime = afterTime;
	}
	
	
	

}

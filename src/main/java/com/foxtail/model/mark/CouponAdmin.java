package com.foxtail.model.mark;

public class CouponAdmin {
  
	private int id;//id
	private String coupontype;//优惠卷分类
	private String couponname;//优惠卷名称
	private Double facevalue;//面值
	private String applygoods;//适用商品
	private Double useif;//使用条件
	private Long starttime;//有效期限开始
	private Long endtime;//有效时间结束
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	private int issuesum;//发放总量
	private String coupondesc;//优惠卷说明
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoupontype() {
		return coupontype;
	}
	public void setCoupontype(String coupontype) {
		this.coupontype = coupontype;
	}
	public String getCouponname() {
		return couponname;
	}
	public void setCouponname(String couponname) {
		this.couponname = couponname;
	}
	public Double getFacevalue() {
		return facevalue;
	}
	public void setFacevalue(Double facevalue) {
		this.facevalue = facevalue;
	}
	public Double getUseif() {
		return useif;
	}
	public void setUseif(Double useif) {
		this.useif = useif;
	}
	public String getApplygoods() {
		return applygoods;
	}
	public void setApplygoods(String applygoods) {
		this.applygoods = applygoods;
	}
	public int getIssuesum() {
		return issuesum;
	}
	public void setIssuesum(int issuesum) {
		this.issuesum = issuesum;
	}
	public String getCoupondesc() {
		return coupondesc;
	}
	public void setCoupondesc(String coupondesc) {
		this.coupondesc = coupondesc;
	}
}

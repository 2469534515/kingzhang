package com.foxtail.model.ad;

import java.io.Serializable;

public class AdList implements Serializable{
    
	private Integer id; // id
	private String adname; // 广告名称
	private String adlinks; // 广告链接
	private String adtype; // 广告类型
	private String addesc; // 广告描述
	private String adspace; // 广告位
	private String adimg; // 广告图片
	private long starttime; //开始时间
	private long endtime;//结束时间
	
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	private Integer sort; // 排序
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getAdlinks() {
		return adlinks;
	}
	public void setAdlinks(String adlinks) {
		this.adlinks = adlinks;
	}
	public String getAdtype() {
		return adtype;
	}
	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}
	public String getAddesc() {
		return addesc;
	}
	public void setAddesc(String addesc) {
		this.addesc = addesc;
	}
	public String getAdspace() {
		return adspace;
	}
	public void setAdspace(String adspace) {
		this.adspace = adspace;
	}
	public String getAdimg() {
		return adimg;
	}
	public void setAdimg(String adimg) {
		this.adimg = adimg;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
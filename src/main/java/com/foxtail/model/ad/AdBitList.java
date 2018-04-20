package com.foxtail.model.ad;

import java.io.Serializable;

public class AdBitList implements Serializable {

	private Integer id; // id
	private String placename; // 广告位名称
	private String addesc; // 广告位描述
    private int sort;
	
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public String getAddesc() {
		return addesc;
	}

	public void setAddesc(String addesc) {
		this.addesc = addesc;
	}

}

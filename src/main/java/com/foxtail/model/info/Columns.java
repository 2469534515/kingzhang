package com.foxtail.model.info;

public class Columns {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String columnname; // 栏目名称
	private int columntype; // 栏目类型
	private String image; // 栏目图片
	private int sort; // 排序
	private int state; // 状态

	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	

	public int getColumntype() {
		return columntype;
	}

	public void setColumntype(int columntype) {
		this.columntype = columntype;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getSort() {
		return sort;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	
}

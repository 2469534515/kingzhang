package com.foxtail.model.navigation;

public class Navigation {
	
	private Integer id;
	private String navigationName;
	private String navigationPhoto;
	private int navigationSort;
	private int navigationStatus;
	
	public Navigation() {}
	
	
	
	public Navigation(Integer id, String navigationName, String navigationPhoto, int navigationSort,
			int navigationStatus) {
		super();
		this.id = id;
		this.navigationName = navigationName;
		this.navigationPhoto = navigationPhoto;
		this.navigationSort = navigationSort;
		this.navigationStatus = navigationStatus;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNavigationName() {
		return navigationName;
	}
	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}
	public String getNavigationPhoto() {
		return navigationPhoto;
	}
	public void setNavigationPhoto(String navigationPhoto) {
		this.navigationPhoto = navigationPhoto;
	}
	public int getNavigationSort() {
		return navigationSort;
	}
	public void setNavigationSort(int navigationSort) {
		this.navigationSort = navigationSort;
	}
	public int getNavigationStatus() {
		return navigationStatus;
	}
	public void setNavigationStatus(int navigationStatus) {
		this.navigationStatus = navigationStatus;
	}
	
	

}

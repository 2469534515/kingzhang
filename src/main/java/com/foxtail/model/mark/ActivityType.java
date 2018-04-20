package com.foxtail.model.mark;

public class ActivityType {

	private String id;//id
	private String typename;//活动分类名称
	private String typedesc;//活动说明
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	/**
	 * @param typename
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getTypedesc() {
		return typedesc;
	}
	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}
}

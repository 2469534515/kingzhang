package com.foxtail.model.message;

public class Message {
	
	private Integer id;
	private String feedbackPeople;
	private String feedbackContent;
	private Long feedbackTime;
	
	public Message() {}
	
	
	
	public Message(Integer id, String feedbackPeople, String feedbackContent, Long feedbackTime) {
		super();
		this.id = id;
		this.feedbackPeople = feedbackPeople;
		this.feedbackContent = feedbackContent;
		this.feedbackTime = feedbackTime;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFeedbackPeople() {
		return feedbackPeople;
	}
	public void setFeedbackPeople(String feedbackPeople) {
		this.feedbackPeople = feedbackPeople;
	}
	public String getFeedbackContent() {
		return feedbackContent;
	}
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	public Long getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	
	//封装前台传过来的条件
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

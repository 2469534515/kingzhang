package com.foxtail.model.member;

import java.util.List;

public class MemMemberList {
		private Integer id;
		private String headImg;
		private String sex;
		private String mName;
		private String phoneNumber;
		private Double balance;
		private Long regTime;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getHeadImg() {
			return headImg;
		}
		public void setHeadImg(String headImg) {
			this.headImg = headImg;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getmName() {
			return mName;
		}
		public void setmName(String mName) {
			this.mName = mName;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public Double getBalance() {
			return balance;
		}
		public void setBalance(Double balance) {
			this.balance = balance;
		}
		public Long getRegTime() {
			return regTime;
		}
		public void setRegTime(Long regTime) {
			this.regTime = regTime;
		}
		
		public MemMemberList() {}
		
		
		
		

		
		public MemMemberList(Integer id, String headImg, String sex, String mName, String phoneNumber, Double balance,
				Long regTime) {
			super();
			this.id = id;
			this.headImg = headImg;
			this.sex = sex;
			this.mName = mName;
			this.phoneNumber = phoneNumber;
			this.balance = balance;
			this.regTime = regTime;
		}






		//封装动态查询的条件
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

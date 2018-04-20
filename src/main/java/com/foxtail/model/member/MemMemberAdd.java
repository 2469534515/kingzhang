package com.foxtail.model.member;

public class MemMemberAdd {
		private int id;
		private String memberName;//会员名称
		private String memberPhone;//会员电话
		private String tradeName;//商品名称
		private String addClassify;//收藏类型
		private long addTime;//收藏时间
		private int memberListId;//会员列表ID
	
		public MemMemberAdd(){}
		
		

		public MemMemberAdd(int id, String memberName, String memberPhone, String tradeName, String addClassify,
				long addTime, int memberListId) {
			super();
			this.id = id;
			this.memberName = memberName;
			this.memberPhone = memberPhone;
			this.tradeName = tradeName;
			this.addClassify = addClassify;
			this.addTime = addTime;
			this.memberListId = memberListId;
		}



		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMemberName() {
			return memberName;
		}
		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
		public String getMemberPhone() {
			return memberPhone;
		}
		public void setMemberPhone(String memberPhone) {
			this.memberPhone = memberPhone;
		}
		public String getTradeName() {
			return tradeName;
		}
		public void setTradeName(String tradeName) {
			this.tradeName = tradeName;
		}
		public long getAddTime() {
			return addTime;
		}
		public void setAddTime(long addTime) {
			this.addTime = addTime;
		}
		public int getMemberListId() {
			return memberListId;
		}
		public void setMemberListId(int memberListId) {
			this.memberListId = memberListId;
		}

		public String getAddClassify() {
			return addClassify;
		}

		public void setAddClassify(String addClassify) {
			this.addClassify = addClassify;
		}
		
		
		//封装从前台传过来的参数
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

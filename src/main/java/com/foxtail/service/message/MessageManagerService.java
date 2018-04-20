package com.foxtail.service.message;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.message.Message;

public interface MessageManagerService {
	
	//多条件分页查询信息反馈
	public Pagination getAllMessageByPage(Pagination page,Message message);
	
	
	//删除信息反馈/批量删除
	public int deleteMessageManager(int[] ides);

}

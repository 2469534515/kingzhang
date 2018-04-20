package com.foxtail.dao.mybatis.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.message.Message;

public interface MessageManagerDao {
	
	
	//多条件查询信息反馈集合
	public List<Message> getAllMessageByPage(@Param("message") Message message);
	
	
	//删除信息反馈/批量删除
	public int deleteMessageManager(int[] ides);

}

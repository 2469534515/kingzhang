package com.foxtail.service.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.message.MessageManagerDao;
import com.foxtail.model.message.Message;
import com.foxtail.service.message.MessageManagerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class MessageManagerServiceImpl implements MessageManagerService{
	
	@Autowired
	private MessageManagerDao messageManagerDao;

	/*
	 * 分页查询所有信息反馈
	 * (non-Javadoc)
	 * @see com.foxtail.service.message.MessageManagerService#getAllMessageByPage(com.foxtail.common.page.Pagination, com.foxtail.model.message.Message)
	 */
	@Override
	public Pagination getAllMessageByPage(Pagination page, Message message) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry = (Page) messageManagerDao.getAllMessageByPage(message);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
	}

	/*
	 * 删除信息反馈/批量删除
	 * (non-Javadoc)
	 * @see com.foxtail.service.message.MessageManagerService#deleteMessageManager(int[])
	 */
	@Override
	public int deleteMessageManager(int[] ides) {
		// TODO Auto-generated method stub
		return messageManagerDao.deleteMessageManager(ides);
	}

}

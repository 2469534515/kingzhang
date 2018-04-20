package com.foxtail.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.member.MemMemberAddDao;
import com.foxtail.model.member.MemMemberAdd;
import com.foxtail.service.member.MemMemberAddService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class MemMemberAddServiceImpl implements MemMemberAddService{
	
	@Autowired
	private MemMemberAddDao memMemberAddDao;

	/*
	 * 多条件分页查询
	 * (non-Javadoc)
	 * @see com.foxtail.service.member.MemMemberAddService#getMemMemberAddListByPage(com.foxtail.common.page.Pagination)
	 */
	@Override
	public Pagination getMemMemberAddListByPage(Pagination page,MemMemberAdd memMemberAdd) {
		System.out.println("into getMemMemberAddListByPage()...................");
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)memMemberAddDao.getMemMemberAddListByPage(memMemberAdd);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
	
		return page;
	}

	/*
	 * 删除会员收藏信息/批量删除
	 * (non-Javadoc)
	 * @see com.foxtail.service.member.MemMemberAddService#deleteMemberAdd(int[])
	 */
	@Override
	public int deleteMemberAdd(int[] ides) {
		// TODO Auto-generated method stub
		return memMemberAddDao.deleteMemberAdd(ides);
	}

}

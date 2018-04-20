package com.foxtail.service.ad.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.ad.AdListDao;
import com.foxtail.model.ad.AdList;
import com.foxtail.service.ad.AdListService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class AdListServiceImpl implements AdListService{

	@Autowired
	private AdListDao adlistDao;

	/**
	 * 查找所有分页
	 */
	@Override
	public Pagination findAll(Pagination page, AdList adlist) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(),page.getPageSize());
		Page AdList = (Page) adlistDao.findAll(adlist);
		page.setTotalCount((int)AdList.getTotal());
		page.setList(AdList.getResult());
		return page;
	}

	/**
	 * 删除
	 */
	@Override
	public void deAdLists(String[] ids) {
		// TODO Auto-generated method stub
		adlistDao.deAdList(ids);;
	}

	/**
	 * 根据id查找
	 */
	@Override
	public AdList findAllById(Integer id) {
		// TODO Auto-generated method stub
		return adlistDao.findAllById(id);
	}

	/**
	 * 修改
	 */
	@Override
	public Integer updateAdList(AdList adlist) {
		// TODO Auto-generated method stub
		return adlistDao.updateAdList(adlist);
	}

	/**
	 * 添加
	 */
	@Override
	public void addAdList(AdList adlist) {
		// TODO Auto-generated method stub
		adlistDao.addAdList(adlist);
	}




	
   
}

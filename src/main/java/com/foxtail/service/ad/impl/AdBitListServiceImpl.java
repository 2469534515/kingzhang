package com.foxtail.service.ad.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.ad.AdBitListDao;
import com.foxtail.model.ad.AdBitList;
import com.foxtail.service.ad.AdBitListService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class AdBitListServiceImpl implements AdBitListService{
	
	private final static Logger log= Logger.getLogger(AdBitListServiceImpl.class);
    
	@Autowired
	private AdBitListDao AdBitDao;

	/**
	 * 查找所有分页
	 */
	@Override
	public Pagination findAll(Pagination page,AdBitList adbitlist) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(),page.getPageSize());
		Page AdBitList = (Page) AdBitDao.findAll(adbitlist);
		page.setTotalCount((int)AdBitList.getTotal());
		page.setList(AdBitList.getResult());
		return page;
	}
    /**
     *添加
     */
	@Override
	public void addAdBitList(AdBitList adbitlist) {
		// TODO Auto-generated method stub
		AdBitDao.addAdBitList(adbitlist);
	}

	/**
	 * 根据id查找
	 */
	@Override
	public AdBitList findAllByID(Integer id) {
		// TODO Auto-generated method stub
		return AdBitDao.findAllById(id);
	}

	/**
	 * 删除
	 */
	@Override
	public void deAdBitLists(String[] ids) {
		// TODO Auto-generated method stub
		AdBitDao.deAdBitList(ids);
	}

	/**
	 * 修改
	 */
	@Override
	public Integer upAdBitList(AdBitList adbitlist) {
		// TODO Auto-generated method stub
		return AdBitDao.upAdBitList(adbitlist);
	}
	@Override
	public List<AdBitList> findAdBitName() {
		// TODO Auto-generated method stub
		return AdBitDao.findAdBitName();
	}

	

	
	
	

}

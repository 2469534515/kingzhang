package com.foxtail.service.mark.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.mark.CouponTypeDao;
import com.foxtail.model.mark.CouponType;
import com.foxtail.service.mark.CouponTypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
public class CouponTypeServiceImpl implements CouponTypeService{
	
	private final static Logger log= Logger.getLogger(CouponTypeServiceImpl.class);
	
    @Autowired
	private CouponTypeDao couponTypeDao;

  
    /**
     * 查找分页
     */
	@Override
	public Pagination findAll(Pagination page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page CouponType  = (Page) couponTypeDao.findAll();
		page.setTotalCount((int)CouponType.getTotal());
		page.setList(CouponType.getResult());
		return page;

	}
	
	/**
	 * 批量删除
	 */
	@Override
	public void deCouponTypes(String[] ids) {
		// TODO Auto-generated method stub
		couponTypeDao.deCouponTypeById(ids);
	}


	/**
	 * 根据id查找
	 */
	@Override
	public CouponType findAllById(Integer id) {
		// TODO Auto-generated method stub
		return couponTypeDao.findAllById(id);
	}

	/**
	 * 修改
	 */
	@Override
	public Integer updateCouponType(CouponType coupontype) {
		// TODO Auto-generated method stub
		return couponTypeDao.updateCouponType(coupontype);
	}

	/**
	 * 添加
	 */
	@Override
	public void addCouponType(CouponType coupontype) {
		// TODO Auto-generated method stub
		couponTypeDao.addCouponType(coupontype);
	}

	/**
	 * 查找优惠卷名称
	 */
	@Override
	public List<CouponType> findCouponName() {
		// TODO Auto-generated method stub
		return couponTypeDao.findCouponName();
	}



	

	
	

}

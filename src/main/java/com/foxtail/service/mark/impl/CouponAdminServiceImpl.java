package com.foxtail.service.mark.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.mark.CouponAdminDao;
import com.foxtail.model.mark.CouponAdmin;
import com.foxtail.model.mark.CouponType;
import com.foxtail.service.mark.CouponAdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class CouponAdminServiceImpl implements CouponAdminService{
    
	@Autowired
	private CouponAdminDao couponadminDao;
	/**
	 * 查询分页
	 */
	@Override
	public Pagination findAll(Pagination page,CouponAdmin couponadmin) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(),page.getPageSize());
		Page CouponAdmin = (Page) couponadminDao.findAll(couponadmin);
		page.setTotalCount((int)CouponAdmin.getTotal());
		page.setList(CouponAdmin.getResult());
		return page;
	}
	/**
	 * 根据ID查询
	 */
	@Override
	public CouponAdmin findAllById(Integer id) {
		// TODO Auto-generated method stub
		return couponadminDao.findAllById(id);
	}
	/**
	 * 删除
	 */
	@Override
	public void deCouponAdmin(String[] ids) {
		// TODO Auto-generated method stub
		couponadminDao.deCouponAdmin(ids);
	}
	/**
	 * 修改
	 */
	@Override
	public Integer updateCouponAdmin(CouponAdmin couponadmin) {
		// TODO Auto-generated method stub
		return couponadminDao.updateCouponAdmin(couponadmin);
	}
	/**
	 * 添加
	 */
	@Override
	public void addCouponAdmin(CouponAdmin couponadmin) {
		// TODO Auto-generated method stub
		couponadminDao.addCouponAdmin(couponadmin);
	}

}

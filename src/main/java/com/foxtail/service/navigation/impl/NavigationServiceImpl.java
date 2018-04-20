package com.foxtail.service.navigation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.navigation.NavigationDao;
import com.foxtail.model.navigation.Navigation;
import com.foxtail.service.navigation.NavigationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class NavigationServiceImpl implements NavigationService{

	@Autowired
	private NavigationDao navigationDao;
	
	/*
	 * 分页查询所有首页导航
	 * (non-Javadoc)
	 * @see com.foxtail.service.navigation.NavigationService#getAllNavigationByPage(com.foxtail.common.page.Pagination)
	 */
	@Override
	public Pagination getAllNavigationByPage(Pagination page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry = (Page) navigationDao.getAllNavigationByPage();
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
	}

	/*
	 * 删除首页导航信息/批量删除
	 * (non-Javadoc)
	 * @see com.foxtail.service.navigation.NavigationService#deleteNavigation(int[])
	 */
	@Override
	public int deleteNavigation(int[] ides) {
		// TODO Auto-generated method stub
		return navigationDao.deleteNavigation(ides);
	}

	/*
	 * 通过id获得首页导航信息
	 * (non-Javadoc)
	 * @see com.foxtail.service.navigation.NavigationService#getNavigationById(java.lang.Integer)
	 */
	@Override
	public Navigation getNavigationById(Integer id) {
		// TODO Auto-generated method stub
		return navigationDao.getNavigationById(id);
	}

	/*
	 * 通过首页导航对象修改首页导航信息
	 * (non-Javadoc)
	 * @see com.foxtail.service.navigation.NavigationService#updateNavigation(com.foxtail.model.navigation.Navigation)
	 */
	@Override
	public int updateNavigation(Navigation navigation) {
		// TODO Auto-generated method stub
		return navigationDao.updateNavigation(navigation);
	}

}

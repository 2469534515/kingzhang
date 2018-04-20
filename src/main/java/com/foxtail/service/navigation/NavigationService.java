package com.foxtail.service.navigation;

import org.apache.ibatis.annotations.Param;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.navigation.Navigation;

public interface NavigationService {
	
	
	/*
	 * 分页查询所有首页导航
	 */
	public Pagination getAllNavigationByPage(Pagination page);
	
	/*
	 * 删除首页导航/批量删除
	 */
	public int deleteNavigation(int[] ides);
	
	
	//通过id获得首页导航信息
	public Navigation getNavigationById(Integer id);
	
	//通过首页导航对象修改首页导航信息
	public int updateNavigation(Navigation navigation);

}

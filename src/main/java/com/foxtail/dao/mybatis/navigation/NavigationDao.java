package com.foxtail.dao.mybatis.navigation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.navigation.Navigation;

public interface NavigationDao {
	
	
	//分页查询所有首页导航
	public List<Navigation> getAllNavigationByPage();
	
	//删除首页导航信息
	public int deleteNavigation(int[] ides);
	
	//通过id获得首页导航信息
	public Navigation getNavigationById(@Param("id") Integer id);
	
	//通过首页导航对象修改首页导航信息
	public int updateNavigation(Navigation navigation);

}

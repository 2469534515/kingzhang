package com.foxtail.dao.mybatis.mark;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.mark.ActivityList;


public interface ActivityListDao {
	
    public List<ActivityList> findAll(@Param("activitylist")ActivityList activitylist);//查找所有
	
	public ActivityList findAllById(@Param("id") Integer id);// 根据id查询所有
	
	public void deActivityList(@Param("ids")String[] ids);//删除包括批量删除
	
	public Integer updateActivityList(ActivityList activitylist);//修改
	
	public void addActivityList(ActivityList activitylist);//添加
}

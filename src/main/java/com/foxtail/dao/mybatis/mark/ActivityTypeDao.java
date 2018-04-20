package com.foxtail.dao.mybatis.mark;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.mark.ActivityType;

public interface ActivityTypeDao {
     
	public List<ActivityType> findAll();//查找分页
	
	public ActivityType findAllByID(@Param("id")Integer id);//根据ID查询所有
	
	public void deActivityTypes(@Param("ids")String[] ids);//批量删除
	
	public Integer upActivityType(ActivityType activitytype);//修改
	
	public void addActivityType(ActivityType activitytype);//添加
	
	public List<ActivityType> getActivityName();//获取分类名称
	
}

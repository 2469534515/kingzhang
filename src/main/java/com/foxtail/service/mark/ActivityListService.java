package com.foxtail.service.mark;


import com.foxtail.common.page.Pagination;
import com.foxtail.model.mark.ActivityList;

public interface ActivityListService {
	   public Pagination findAll(Pagination page,ActivityList activitylist);//查找所有
		
		public ActivityList findAllById(Integer id);// 根据id查询所有
		
		public void deActivityList(String[] ids);//删除包括批量删除
		
		public Integer updateActivityList(ActivityList activitylist);//修改
		
		public void addActivityList(ActivityList activitylist);//添加
}

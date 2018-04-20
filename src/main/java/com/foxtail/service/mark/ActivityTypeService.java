package com.foxtail.service.mark;



import java.util.List;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.mark.ActivityType;

public interface ActivityTypeService {
	
    public Pagination findAll(Pagination page);//查找分页
	
	public ActivityType findAllByID(Integer id);//根据ID查询所有
	
	public void deActivityTypes(String[] ids);//批量删除
	
	public Integer upActivityType(ActivityType activitytype);//修改
	
	public void addActivityType(ActivityType activitytype);//添加
	
	public List<ActivityType> getActivityName();//获取分类名称
	

}

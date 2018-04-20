package com.foxtail.service.mark.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.mark.ActivityListDao;
import com.foxtail.model.mark.ActivityList;
import com.foxtail.service.mark.ActivityListService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ActivityListServiceImpl implements ActivityListService {
    @Autowired
	private ActivityListDao activitylistDao;
	
    @Override
	public Pagination findAll(Pagination page,ActivityList activitylist) {
		// TODO Auto-generated method stub
    	PageHelper.startPage(page.getPageNo(),page.getPageSize());
		Page ActivityList = (Page) activitylistDao.findAll(activitylist);
		page.setTotalCount((int)ActivityList.getTotal());
		page.setList(ActivityList.getResult());
		return page;
	}
    
	@Override
	public ActivityList findAllById(Integer id) {
		// TODO Auto-generated method stub
		return activitylistDao.findAllById(id);
	}

	@Override
	public void deActivityList(String[] ids) {
		// TODO Auto-generated method stub
		activitylistDao.deActivityList(ids);
	}

	@Override
	public Integer updateActivityList(ActivityList activitylist) {
		// TODO Auto-generated method stub
		return activitylistDao.updateActivityList(activitylist);
	}

	@Override
	public void addActivityList(ActivityList activitylist) {
		// TODO Auto-generated method stub
		activitylistDao.addActivityList(activitylist);
	}

	

}

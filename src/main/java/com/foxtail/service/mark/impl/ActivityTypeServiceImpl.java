package com.foxtail.service.mark.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.mark.ActivityTypeDao;
import com.foxtail.model.mark.ActivityType;
import com.foxtail.service.mark.ActivityTypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ActivityTypeServiceImpl implements ActivityTypeService {
    @Autowired
	private ActivityTypeDao activitytypeDao;

	@Override
	public Pagination findAll(Pagination page) {
			// TODO Auto-generated method stub
			PageHelper.startPage(page.getPageNo(), page.getPageSize());
			Page ActivityType  = (Page) activitytypeDao.findAll();
			page.setTotalCount((int)ActivityType.getTotal());
			page.setList(ActivityType.getResult());
			return page;
	}
	

	@Override
	public ActivityType findAllByID(Integer id) {
		// TODO Auto-generated method stub
		return activitytypeDao.findAllByID(id);
	}

	@Override
	public void deActivityTypes(String[] ids) {
		// TODO Auto-generated method stub
		activitytypeDao.deActivityTypes(ids);
	}

	@Override
	public Integer upActivityType(ActivityType activitytype) {
		// TODO Auto-generated method stub
		return activitytypeDao.upActivityType(activitytype);
	}

	@Override
	public void addActivityType(ActivityType activitytype) {
		// TODO Auto-generated method stub
		activitytypeDao.addActivityType(activitytype);
	}


	@Override
	public List<ActivityType> getActivityName() {
		// TODO Auto-generated method stub
		return activitytypeDao.getActivityName();
	}


	

}

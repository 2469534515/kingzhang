package com.foxtail.service.info.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.info.ColumnDao;
import com.foxtail.model.info.Columns;
import com.foxtail.service.info.ColumnService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ColumnServiceImpl implements ColumnService{

	private final static Logger log= Logger.getLogger(ColumnServiceImpl.class);
	
	@Autowired
	private ColumnDao columnDao;

	/**
	 * 查找分页
	 */
	@Override
	public Pagination findAll(Pagination page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page Columns = (Page) columnDao.findAll();
		page.setTotalCount((int)Columns.getTotal());
		page.setList(Columns.getResult());
		return page;
	}

	/**
	 * 根据id查找
	 */
	@Override
	public Columns findAllById(Integer id) {
		// TODO Auto-generated method stub
		return columnDao.findAllById(id);
	}

	/**
	 * 删除
	 */
	@Override
	public void deColumns(String[] ids) {
		// TODO Auto-generated method stub
		columnDao.deColumns(ids);;
	}

	/**
	 * 修改
	 */
	@Override
	public Integer updateColumns(Columns columns) {
		// TODO Auto-generated method stub
		return columnDao.updateColumns(columns);
	}

	/**
	 * 添加
	 */
	@Override
	public void addColumns(Columns column) {
		// TODO Auto-generated method stub
		columnDao.addColumns(column);
	}

	@Override
	public List<Columns> findColumnName() {
		// TODO Auto-generated method stub
		return columnDao.findColumnName();
	}

	
}

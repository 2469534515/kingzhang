package com.foxtail.service.info;


import java.util.List;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.info.Columns;

public interface ColumnService {
     
    public Pagination findAll(Pagination page); //查找所有
	
	public Columns findAllById(Integer id); //根据ID查找
	
	public void deColumns(String[] ids); //批量删除
	
	public Integer updateColumns(Columns columns); //修改
	
	public void addColumns(Columns column); //添加
	
	public List<Columns> findColumnName(); //查找栏目名称
}

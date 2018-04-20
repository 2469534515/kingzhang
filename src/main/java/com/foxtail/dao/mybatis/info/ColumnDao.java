package com.foxtail.dao.mybatis.info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.info.Columns;

public interface ColumnDao {
    
	public List<Columns> findAll(); //查找所有
	
	public Columns findAllById(@Param("id")Integer id); //根据ID查找
	
	public void deColumns(@Param("ids")String[] ids); //批量删除
	
	public Integer updateColumns(Columns columns); //修改
	
	public void addColumns(Columns column); //添加
	
	public List<Columns> findColumnName(); //查找栏目名称
}

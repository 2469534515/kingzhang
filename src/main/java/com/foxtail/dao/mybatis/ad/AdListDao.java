package com.foxtail.dao.mybatis.ad;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.ad.AdList;

public interface AdListDao {
    
    public List<AdList> findAll(@Param("adlist")AdList adlist);//查找所有
	
	public AdList findAllById(@Param("id") Integer id);// 根据id查询所有
	
	public void deAdList(@Param("ids")String[] ids);//删除包括批量删除
	
	public Integer updateAdList(AdList adlist);//修改
	
	public void addAdList(AdList adlist);//添加
    
}

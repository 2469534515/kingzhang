package com.foxtail.service.ad;


import com.foxtail.common.page.Pagination;
import com.foxtail.model.ad.AdList;

public interface AdListService {
  
	public Pagination findAll(Pagination page,AdList adlist);//查找所有
	
	public void deAdLists(String[] ids);//批量删除
	
	public AdList findAllById(Integer id);//根据id查询所有
	
	public Integer updateAdList(AdList adlist);  //根据id修改
	
	public void addAdList(AdList adlist);//添加
}

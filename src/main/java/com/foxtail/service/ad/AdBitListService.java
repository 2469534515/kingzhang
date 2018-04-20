package com.foxtail.service.ad;


import java.util.List;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.ad.AdBitList;

public interface AdBitListService {

	public Pagination findAll(Pagination page,AdBitList adbitlist);//查找所有分页
	
	public void addAdBitList(AdBitList adbitlist);//添加广告位
	
	public AdBitList findAllByID(Integer id);//根据ID查询所有
	
	public void deAdBitLists(String[] ids);//批量删除
	
	public Integer upAdBitList(AdBitList adbitlist);//根据id修改广告位
	
	public List<AdBitList> findAdBitName();//查找广告位名称
	
}

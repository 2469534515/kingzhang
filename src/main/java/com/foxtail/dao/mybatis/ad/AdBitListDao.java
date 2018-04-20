package com.foxtail.dao.mybatis.ad;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.ad.AdBitList;


public interface AdBitListDao {
	
	    public List<AdBitList> findAll(@Param("adbitlist")AdBitList adbitlist);//查找所有
		
		public AdBitList findAllById(@Param("id") Integer id);// 根据id查询所有
		
		public void deAdBitList(@Param("ids")String[] ids);//删除包括批量删除
		
		public Integer upAdBitList(AdBitList adbitlist);//修改
		
		public void addAdBitList(AdBitList adbitlist);//添加
		
		public List<AdBitList> findAdBitName();//查找广告位名称
}

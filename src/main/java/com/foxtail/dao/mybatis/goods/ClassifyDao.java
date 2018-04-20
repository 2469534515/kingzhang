package com.foxtail.dao.mybatis.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.goods.Classify;

public interface ClassifyDao {
	
	/*
	 * 添加商品分类信息
	 */
	public int addClassify(Classify classify);
	
	/*
	 * 分页查询商品分类
	 */
	public List<Classify> getAllClassifyByPage();
	
	/*
	 * 通过商品分类Id查询获得商品分类对象
	 */
	public Classify getCalssifyById(@Param("id") int id);
	
	/*
	 * 通过商品分类对象修改商品分类信息
	 */
	public int updateClassifyByCalssify(Classify classify);
	
	/*
	 * 删除商品分类信息/批量删除
	 */
	public int deleteCalssify(int[] ides);
	
	/*
	 * 获取商品分类名称
	 */
	public List<Classify> getClassifyName();

}

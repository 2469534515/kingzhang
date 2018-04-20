package com.foxtail.dao.mybatis.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.goods.Goods;

public interface GoodsDao {
	
	
	/*
	 * 添加商品
	 */	
	public int addGoods(Goods goods);
	
	
	/*
	 * 多条件查询分页
	 */
	public List<Goods> findAllGoodsByPage(@Param("goods") Goods goods);
	
	/*
	 * 批量删除或单个删除
	 */
	public int deleteGoods(int[] ids);
	
	/*
	 * 根据id获得商品对象
	 */
	public Goods getGoodsById(@Param("id") int id);
	
	/*
	 * 根据商品对象修改商品对象
	 */
	public int updateGoodsByGoods(Goods goods);

}

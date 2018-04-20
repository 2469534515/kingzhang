package com.foxtail.service.goods;

import org.apache.ibatis.annotations.Param;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.goods.Goods;

public interface GoodsService {
	
	/*
	 * 添加商品
	 */
	public int addGoods(Goods goods);
	
	/*
	 * 多条件查询分页
	 */
	public Pagination findForPage(Pagination page,Goods goods);
	
	
	/*
	 * 批量删除或单个删除
	 */
	public int deleteGoods(int[] ids);
	
	
	/*
	 * 根据id获得商品对象
	 */
	public Goods getGoodsById(int id);

	/*
	 * 根据商品对象修改商品对象
	 */
	public int updateGoodsByGoods(Goods goods);

}

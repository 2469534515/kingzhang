package com.foxtail.service.goods.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.goods.GoodsDao;
import com.foxtail.model.goods.Goods;
import com.foxtail.service.goods.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class GoodsServiceImpl implements GoodsService{

	
	@Autowired
	private GoodsDao goodsDao;
	
	/*
	 * 添加会员信息
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.GoodsService#addGoods(com.foxtail.model.goods.Goods)
	 */
	@Override
	public int addGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.addGoods(goods);
	}

	/*
	 * 多条件查询分页
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.GoodsService#findForPage(com.foxtail.common.page.Pagination, com.foxtail.model.goods.Goods)
	 */
	@Override
	public Pagination findForPage(Pagination page, Goods goods) {
		// TODO Auto-generated method stub
		
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)goodsDao.findAllGoodsByPage(goods);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
	
		return page;

	}

	/*
	 * 批量删除或单个删除
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.GoodsService#deleteGoods(int[])
	 */
	@Override
	public int deleteGoods(int[] ids) {
		// TODO Auto-generated method stub
		return goodsDao.deleteGoods(ids);
	}

	/*
	 * 根据商品id获得商品对象
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.GoodsService#getGoodsById(int)
	 */
	@Override
	public Goods getGoodsById(int id) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsById(id);
	}

	/*
	 * 根据商品对象修改商品对象
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.GoodsService#updateGoodsByGoods(com.foxtail.model.goods.Goods)
	 */
	@Override
	public int updateGoodsByGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.updateGoodsByGoods(goods);
	}

}

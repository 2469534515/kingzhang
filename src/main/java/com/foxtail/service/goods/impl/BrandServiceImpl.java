package com.foxtail.service.goods.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.goods.BrandDao;
import com.foxtail.model.goods.Brand;
import com.foxtail.service.goods.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private BrandDao brandDao;

	/*
	 * 添加商品品牌对象
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.BrandService#addBrand(com.foxtail.model.goods.Brand)
	 */
	@Override
	public int addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandDao.addBrand(brand);
	}

	/*
	 * 分页查询所有商品品牌信息
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.BrandService#getAllBrandByPage(com.foxtail.common.page.Pagination)
	 */
	@Override
	public Pagination getAllBrandByPage(Pagination page,Brand brand) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)brandDao.getAllBrandByPage(brand);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
	}

	/*
	 * 通过id获得品牌信息对象
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.BrandService#getBrandById(int)
	 */
	@Override
	public Brand getBrandById(int id) {
		// TODO Auto-generated method stub
		return brandDao.getBrandById(id);
	}

	/*
	 * 通过品牌对象修改品牌对象
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.BrandService#updateBrandByBrand(com.foxtail.model.goods.Brand)
	 */
	@Override
	public int updateBrandByBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandDao.updateBrandByBrand(brand);
	}

	//删除品牌信息或批量删除
	@Override
	public int deleteBrand(int[] ides) {
		// TODO Auto-generated method stub
		return brandDao.deleteBrand(ides);
	}

	/*
	 * 获取品牌名称
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.BrandService#getBrandName()
	 */
	@Override
	public List<Brand> getBrandName() {
		// TODO Auto-generated method stub
		return brandDao.getBrandName();
	}

}

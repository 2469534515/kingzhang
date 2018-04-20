package com.foxtail.service.goods;

import java.util.List;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.goods.Brand;

public interface BrandService {
	
		//添加品牌信息
		public int addBrand(Brand brand);
		
		
		/*
		 * 分页查询商品分类信息
		 */
		public Pagination getAllBrandByPage(Pagination page,Brand brand);
		
		
		//通过id获取品牌信息对象
		public Brand getBrandById(int id);
		
		//修改品牌信息通过品牌对象
		public int updateBrandByBrand(Brand brand);
		
		//删除商品信息或批量删除
		public int deleteBrand(int[] ides);
		
		//获取品牌名称
		public List<Brand> getBrandName();

}

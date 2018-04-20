package com.foxtail.service.goods.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.goods.ClassifyDao;
import com.foxtail.model.goods.Classify;
import com.foxtail.service.goods.ClassifyService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ClassifyServiceImpl implements ClassifyService{
	
	@Autowired
	private ClassifyDao classifyDao;

	/*
	 * 添加商品分类
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.ClassifyService#addClassify(com.foxtail.model.goods.Classify)
	 */
	@Override
	public int addClassify(Classify classify) {
		// TODO Auto-generated method stub
		
		return classifyDao.addClassify(classify);
	}
	

	/*
	 * 分页查询所有商品分类信息
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.ClassifyService#getAllClassifyByPage()
	 */
	@Override
	public Pagination getAllClassifyByPage(Pagination page) {
		// TODO Auto-generated method stub
		
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)classifyDao.getAllClassifyByPage();
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
	
		return page;
	}


	/*
	 * 通过id获得商品分类对象
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.ClassifyService#getCalssifyById(int)
	 */
	@Override
	public Classify getCalssifyById(int id) {
		// TODO Auto-generated method stub
		return classifyDao.getCalssifyById(id);
	}


	/*
	 * 通过商品对象修改商品对象信息
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.ClassifyService#updateClassifyByCalssify(com.foxtail.model.goods.Classify)
	 */
	@Override
	public int updateClassifyByCalssify(Classify classify) {
		// TODO Auto-generated method stub
		return classifyDao.updateClassifyByCalssify(classify);
	}


	/*
	 * 删除商品分类对象/批量删除
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.ClassifyService#deleteCalssify(int[])
	 */
	@Override
	public int deleteCalssify(int[] ides) {
		// TODO Auto-generated method stub
		return classifyDao.deleteCalssify(ides);
	}


	/*
	 * 获取商品分类名称
	 * (non-Javadoc)
	 * @see com.foxtail.service.goods.ClassifyService#getClassifyName()
	 */
	@Override
	public List<Classify> getClassifyName() {
		// TODO Auto-generated method stub
		return classifyDao.getClassifyName();
	}

	
	
	

}

package com.foxtail.service.mark;

import java.util.List;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.mark.CouponType;

public interface CouponTypeService {

	public Pagination findAll(Pagination page); // 查找所有 
	
	public void deCouponTypes(String[] ids);// 批量删除
	
	public CouponType findAllById(Integer id);// 根据id查询所有

	public Integer updateCouponType(CouponType coupontype); // 修改

	public void addCouponType(CouponType coupontype);// 添加
	
	public List<CouponType> findCouponName();//查找优惠卷分类名称
}

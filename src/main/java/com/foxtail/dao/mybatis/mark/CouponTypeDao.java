package com.foxtail.dao.mybatis.mark;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.foxtail.model.mark.CouponType;

public interface CouponTypeDao {
 
	public List<CouponType> findAll(); // 查找所有
	
	public void deCouponTypeById(@Param("ids")String[] ids);//删除 批量删除

	public CouponType findAllById(@Param("id") Integer id);// 根据id查询所有

	public Integer updateCouponType(CouponType coupontype); // 修改

	public void addCouponType(CouponType coupontype);// 添加
	
	public List<CouponType> findCouponName();//查找优惠卷分类名称
}

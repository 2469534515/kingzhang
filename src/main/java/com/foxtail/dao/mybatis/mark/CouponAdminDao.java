package com.foxtail.dao.mybatis.mark;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.mark.CouponAdmin;
import com.foxtail.model.mark.CouponType;

public interface CouponAdminDao {
 
	public List<CouponAdmin> findAll(@Param("couponadmin")CouponAdmin couponadmin);//查找所有
	
	public CouponAdmin findAllById(@Param("id") Integer id);// 根据id查询所有
	
	public void deCouponAdmin(@Param("ids")String[] ids);//删除包括批量删除
	
	public Integer updateCouponAdmin(CouponAdmin couponadmin);//修改
	
	public void addCouponAdmin(CouponAdmin couponadmin);//添加
}

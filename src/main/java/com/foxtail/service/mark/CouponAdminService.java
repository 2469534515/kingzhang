package com.foxtail.service.mark;


import org.apache.ibatis.annotations.Param;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.mark.CouponAdmin;

public interface CouponAdminService {

    public Pagination findAll(Pagination page,CouponAdmin couponadmin);//查找所有
	
	public CouponAdmin findAllById(Integer id);//根据ID查找所有
	
	public void deCouponAdmin(String[] ids);//删除包括批量删除
	
	public Integer updateCouponAdmin(CouponAdmin couponadmin);//修改
	
	public void addCouponAdmin(CouponAdmin couponadmin);//添加
}

package com.foxtail.controller.mark;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.mark.CouponType;
import com.foxtail.service.mark.CouponTypeService;
import com.github.pagehelper.PageInfo;

import jxl.common.Logger;

@Controller
@RequestMapping(value="marketing/coupon")
public class CouponTypeController extends BaseController {
    
	private final static Logger log = Logger.getLogger(CouponTypeController.class);
	@Autowired
	private CouponTypeService coupontypeservice;
	
	/**
	 * 页面
	 * @return
	 */
	@RequestMapping(value="/coupontype")
	public String findcoupontype() {
		return "mark/coupontype/coupontype";
	}
	/**
	 * 查找所有分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/coupontypes")
	@ResponseBody
	public DataGrid findcoupontypes(HttpServletRequest request) {
		Pagination pagination = coupontypeservice.findAll(getPagination(request));
		return DataGridResult.getResult(pagination);
	}
    /**
     * 删除
     * @param ids
     * @return
     */
	@RequestMapping("deCouponTypes")
	@ResponseBody
	public Object delete(String ids) {
			this.coupontypeservice.deCouponTypes(ids.split(","));
			return JsonResult.getSuccessResult();
	}
	/**
	 * 添加
	 * @param AdList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCouponType")
	public String addcoupontype(CouponType coupontype) {
		coupontypeservice.addCouponType(coupontype);
		return "100";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value="/AddCouponTypeView")
	public ModelAndView AddView() {
		ModelAndView mv = new ModelAndView("/mark/coupontype/addcoupontype");
		return mv;
	}
	 
	/**
	 * 跳转到修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateCouponTypeView")
	public ModelAndView updateCouponTypeView(Integer id) throws Exception{
		ModelAndView mv = new ModelAndView("/mark/coupontype/upcoupontype");
		CouponType coupontype = new CouponType();
		coupontype = coupontypeservice.findAllById(id);
		mv.getModelMap().addAttribute("coupontype",coupontype);
		return mv;
	}
	
	/**
	 * 修改
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateCouponType")
	@ResponseBody
	public String UpCouponType(CouponType coupontype) {
		Integer update = coupontypeservice.updateCouponType(coupontype);
		return "100";
	}
	
	  /**
     * 查询所有类型名称
     */
    @RequestMapping("/findCouponName")
    @ResponseBody
    public PageInfo<CouponType> findCouponName(){
    	List<CouponType> list = coupontypeservice.findCouponName();
//    	System.out.println(list);
//    	for(ActivityType a : list) {
//    		System.out.println("名称/t" + a.getTypename());
//    	}
    	PageInfo page = new PageInfo(list);
    	return page;
    }
}

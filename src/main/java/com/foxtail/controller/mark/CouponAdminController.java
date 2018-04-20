package com.foxtail.controller.mark;

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
import com.foxtail.model.mark.CouponAdmin;
import com.foxtail.model.mark.CouponType;
import com.foxtail.service.mark.CouponAdminService;

@Controller
@RequestMapping(value="marketing/coupon")
public class CouponAdminController extends BaseController{
    @Autowired
    private CouponAdminService  couponadminservice;
    
    /**
     * 页面
     * @return
     */
    @RequestMapping(value="/couponadmin")
    public String findCouponAdmin(){
    	return "mark/couponadmin/couponadmin";
    }
    
    /**
     * 查询分页显示
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/couponadmins")
    public DataGrid findCouponAdmins(HttpServletRequest request,CouponAdmin couponadmin) {
		Pagination pagination = couponadminservice.findAll(getPagination(request), couponadmin);
		return DataGridResult.getResult(pagination);
	
    }
    /**
     * 删除 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/decouponadmin")
    public Object deCouponAdmin(String ids) {
    	this.couponadminservice.deCouponAdmin(ids.split(","));
    	return JsonResult.getSuccessResult();
    }
    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value="/addcouponadminView")
    public ModelAndView AddView() {
    	ModelAndView mv = new ModelAndView("/mark/couponadmin/addcouponadmin");
    	return mv;
    }
    /**
     * 添加
     * @param couponadmin
     * @return
     */
    @RequestMapping(value="/addcouponadmin")
    @ResponseBody
    public String addCouponAdmin(CouponAdmin couponadmin) {
    	couponadminservice.addCouponAdmin(couponadmin);
    	return "100";
    }
    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping(value="/updatecouponadminView")
    public ModelAndView UpView(Integer id) throws Exception{
    	ModelAndView mv = new ModelAndView("/mark/couponadmin/updatecouponadmin");
    	CouponAdmin couponadmin = new CouponAdmin();
    	couponadmin = couponadminservice.findAllById(id);
    	mv.getModelMap().addAttribute("couponadmin", couponadmin);
    	return mv;
    }
    /**
     * 修改
     * @param couponadmin
     * @return
     */
    @RequestMapping(value="/updatecouponadmin")
    @ResponseBody
    public String updateCouponAdmin(CouponAdmin couponadmin) {
    		System.out.println(couponadmin.getCoupontype() + "\tcouponadmin");
           Integer update = couponadminservice.updateCouponAdmin(couponadmin);
           return "100";
    }
}

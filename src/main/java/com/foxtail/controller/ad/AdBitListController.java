package com.foxtail.controller.ad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.foxtail.model.ad.AdBitList;
import com.foxtail.service.ad.AdBitListService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("ad/ad")
public class AdBitListController extends BaseController{
 
	private final static Logger log= Logger.getLogger(AdBitListController.class);
	
	@Autowired
	private AdBitListService adbitlistService;
	
	
	/**
     * 页面
     * @return
     */
    @RequestMapping(value="/adbitlist")
    public String findAdBitList(){
    	return "ad/adbitlist/adbitlist";
    }
    
	 /**
     * 查询分页显示
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/adbitlists")
    public DataGrid findCouponAdmins(HttpServletRequest request,AdBitList adbitlist) {
    	Pagination pagination = adbitlistService.findAll(getPagination(request), adbitlist);
		return DataGridResult.getResult(pagination);
	
    }
    /**
     * 删除 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deadbitlist")
    public Object deCouponAdmin(String ids) {
    	this.adbitlistService.deAdBitLists(ids.split(","));
    	return JsonResult.getSuccessResult();
    }
    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value="/addadbitlistView")
    public ModelAndView AddView() {
    	ModelAndView mv = new ModelAndView("/ad/adbitlist/addadbitlist");
    	return mv;
    }
    /**
     * 添加
     * @param couponadmin
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addadbitlist")
    public String addCouponAdmin(AdBitList adbitlist) {
    	adbitlistService.addAdBitList(adbitlist);
    	return "100";
    }
    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping(value="/updateadbitlistView")
    public ModelAndView UpView(Integer id) throws Exception{
    	ModelAndView mv = new ModelAndView("/ad/adbitlist/upadbitlist");
    	AdBitList adbitlist = new AdBitList();
    	adbitlist = adbitlistService.findAllByID(id);
    	mv.getModelMap().addAttribute("adbitlist", adbitlist);
    	return mv;
    }
    /**
     * 修改
     * @param couponadmin
     * @return
     */
    @RequestMapping(value="/updateadbitlist")
    @ResponseBody
    public String updateCouponAdmin(AdBitList adbitlist) {
           Integer update = adbitlistService.upAdBitList(adbitlist);
           return "100";
    }

    /**
     * 查找所有广告位
     * @return
     */
    @RequestMapping(value="/findAdBitName")
    @ResponseBody
    public PageInfo<AdBitList> findAdBitName(){
    	List<AdBitList> list = adbitlistService.findAdBitName();
    	PageInfo  page = new PageInfo(list);
    	return page;
    }
}

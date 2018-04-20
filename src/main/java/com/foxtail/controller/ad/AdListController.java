package com.foxtail.controller.ad;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.bouncycastle.mail.smime.handlers.multipart_signed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.SpringFileupload;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.ad.AdList;
import com.foxtail.service.ad.AdListService;

@Controller
@RequestMapping("ad/ad")
public class AdListController extends BaseController{
	private final static Logger log= Logger.getLogger(AdListController.class);
	@Autowired
	private AdListService adlistService;

	/**
     * 页面
     * @return
     */
    @RequestMapping(value="/adlist")
    public String findAdList(){
    	return "ad/adlist/adlist";
    }
    
    /**
     * 查询分页显示
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/adlists")
    public DataGrid findCouponAdmins(HttpServletRequest request,AdList adlist) {
    	Pagination pagination = adlistService.findAll(getPagination(request), adlist);
		return DataGridResult.getResult(pagination);
    }
    
    /**
     * 删除 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deadlist")
    public Object deAdList(String ids) {
    	this.adlistService.deAdLists(ids.split(","));
    	return JsonResult.getSuccessResult();
    }
    
    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value="/addadlistView")
    public ModelAndView AddView() {
    	ModelAndView mv = new ModelAndView("/ad/adlist/addadlist");
    	return mv;
    }
    /**
     * 添加
     * @param couponadmin
     * @return
     */
    @RequestMapping(value="/addadlist")
    @ResponseBody
    public String addCouponAdmin(AdList adlist,MultipartFile imgs,HttpServletRequest request) {
    	String path = request.getServletContext().getRealPath("/");
        try {
        	adlist.setAdimg(SpringFileupload.upload(imgs, path));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	adlistService.addAdList(adlist);
    	return "100";
    }
    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping(value="/updateadlistView")
    public ModelAndView UpView(Integer id) throws Exception{
    	ModelAndView mv = new ModelAndView("/ad/adlist/upadlist");
    	AdList adlist = new AdList();
    	adlist = adlistService.findAllById(id);
    	mv.getModelMap().addAttribute("adlist", adlist);
    	return mv;
    }
    /**
     * 修改
     * @param couponadmin
     * @return
     */
    @RequestMapping(value="/updateadlist")
    @ResponseBody
    public String updateCouponAdmin(AdList adlist,MultipartFile newimgs,HttpServletRequest request) {
    	String path = request.getServletContext().getRealPath("/");
    	try {
			adlist.setAdimg(SpringFileupload.upload(newimgs, path));
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Integer update = adlistService.updateAdList(adlist);
         return "100";
    }
}

package com.foxtail.controller.mark;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.mark.ActivityList;
import com.foxtail.model.mark.ActivityType;
import com.foxtail.service.mark.ActivityListService;
import com.foxtail.service.mark.ActivityTypeService;
@Controller
@RequestMapping(value="/marketing/activity")
public class ActivityListController extends BaseController{
	    @Autowired
	    private ActivityListService  activitylistservice;
	    /**
	     * 页面
	     * @return
	     */
	    @RequestMapping(value="/activitylist")
	    public String findCouponAdmin(){
	    	return "mark/activitylist/activitylist";
	    }
	    
	    /**
	     * 查询分页显示
	     * @param request
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value="/activitylists")
	    public DataGrid findCouponAdmins(HttpServletRequest request,ActivityList activitylist) {
			Pagination pagination = activitylistservice.findAll(getPagination(request), activitylist);
			return DataGridResult.getResult(pagination);
		
	    }
	    /**
	     * 删除 批量删除
	     * @param ids
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value="/deactivitylist")
	    public Object deCouponAdmin(String ids) {
	    	this.activitylistservice.deActivityList(ids.split(","));
	    	return JsonResult.getSuccessResult();
	    }
	    /**
	     * 跳转到添加页面
	     * @return
	     */
	    @RequestMapping(value="/addactivitylistView")
	    public ModelAndView AddView(ModelMap map) {
	    	//List<ActivityType> activityName = activitytypeservice.getActivityName();
	    	
	    	//map.addAttribute("activityName", activityName);
	    	ModelAndView mv = new ModelAndView("/mark/activitylist/addactivitylist");
	    	return mv;
	    }
	    /**
	     * 添加
	     * @param couponadmin
	     * @return
	     */
	    @RequestMapping(value="/addactivitylist")
	    @ResponseBody
	    public String addCouponAdmin(ActivityList activitylist) {
	    	activitylistservice.addActivityList(activitylist);
	    	return "100";
	    }
	    /**
	     * 跳转到修改页面
	     * @return
	     */
	    @RequestMapping(value="/updateactivitylistView")
	    public ModelAndView UpView(Integer id) throws Exception{
	    	ModelAndView mv = new ModelAndView("/mark/activitylist/updateactivitylist");
	    	ActivityList activitylist = new ActivityList();
	    	activitylist = activitylistservice.findAllById(id);
	    	mv.getModelMap().addAttribute("activitylist", activitylist);
	    	return mv;
	    }
	    /**
	     * 修改
	     * @param couponadmin
	     * @return
	     */
	    @RequestMapping(value="/updateactivitylist")
	    @ResponseBody
	    public String updateCouponAdmin(ActivityList activitylist) {
	           Integer update = activitylistservice.updateActivityList(activitylist);
	           return "100";
	    }
}

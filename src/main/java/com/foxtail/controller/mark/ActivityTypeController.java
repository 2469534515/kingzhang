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
import com.foxtail.model.mark.ActivityType;
import com.foxtail.service.mark.ActivityTypeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/marketing/activity")
public class ActivityTypeController extends BaseController{
	
	@Autowired
	private ActivityTypeService activityTypeService;
	
	/**
	 * 页面
	 * @return
	 */
    @RequestMapping(value="/activitytype")
	public String findActivityType() {
		return "mark/activitytype/activitytype";
	}
    /**
     * 查找分页
     * @param request
     * @return
     */
    @RequestMapping(value="/activitytypes")
    @ResponseBody
    public DataGrid findActivityTypes(HttpServletRequest request) {
    	Pagination pagination = activityTypeService.findAll(getPagination(request));
    	return DataGridResult.getResult(pagination);
    }
    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value="/deactivitytype")
    @ResponseBody
    public Object deActivityType(String ids) {
    	this.activityTypeService.deActivityTypes(ids.split(","));
    	return JsonResult.getSuccessResult();
    }
    
    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value="/addactivitytypeView")
    public ModelAndView AddView() {
    	ModelAndView mv = new ModelAndView("/mark/activitytype/addactivitytype");
    	return mv;
    }
    /**
     * 添加
     * @param couponadmin
     * @return
     */
    @RequestMapping(value="/addactivitytype")
    @ResponseBody
    public String addActivityType(ActivityType activitytype) {
    	activityTypeService.addActivityType(activitytype);
    	return "100";
    }
    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping(value="/updateactivitytypeView")
    public ModelAndView UpView(Integer id) throws Exception{
    	ModelAndView mv = new ModelAndView("/mark/activitytype/updateactivitytype");
    	ActivityType activitytype = new ActivityType();
    	activitytype = activityTypeService.findAllByID(id);
    	mv.getModelMap().addAttribute("activitytype", activitytype);
    	return mv;
    }
    /**
     * 修改
     * @param couponadmin
     * @return
     */
    @RequestMapping(value="/updateactivitytype")
    @ResponseBody
    public String updateActivityType(ActivityType activitytype) {
           Integer update = activityTypeService.upActivityType(activitytype);
           return "100";
    }
    
    /**
     * 查询所有类型名称
     */
    @RequestMapping("/getActivityTypeName")
    @ResponseBody
    public PageInfo<ActivityType> getActivityTypeName(){
    	List<ActivityType> list = activityTypeService.getActivityName();
//    	System.out.println(list);
//    	for(ActivityType a : list) {
//    		System.out.println("名称/t" + a.getTypename());
//    	}
    	PageInfo page = new PageInfo(list);
    	return page;
    }
}

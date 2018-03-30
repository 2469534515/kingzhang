package com.foxtail.controller.sys;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.common.web.JsonData;
import com.foxtail.model.sys.SysUser;
import com.foxtail.model.sys.SysUserRole;
import com.foxtail.service.sys.SysUserService;

@Controller
@RequestMapping("sys/auth/user") 
public class SysUserController extends BaseController {
	
	private final static Logger log= Logger.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService; 
	
	
	@RequestMapping() 
	public String toMain(String sysModule){
		
		return getMainJsp(sysModule);
	}
	
	
	@RequestMapping("view") 
	@ResponseBody
	public DataGrid list(HttpServletRequest request,String kw){
		
		Pagination pagination = sysUserService.findForPage(getPagination(request),kw);
		return DataGridResult.getResult(pagination);
	}
		
	
	@RequestMapping("toedit") 
	public String  toAdd(String sysM,String sysA,String id,ModelMap model){
		String jsp= getEditJsp(sysM);
		if(isEditPage(sysA))
			model.put("vo", sysUserService.getById(id));
		return jsp;
	}
	
	
	@RequestMapping("save") 
	@ResponseBody
	public Object save(@ModelAttribute SysUser po) {
		
			this.sysUserService.save(po);
		return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteById(String ids) {
			this.sysUserService.delete(ids.split(","));
			return JsonResult.getSuccessResult("删除成功");
	}
	

	
	@RequestMapping("/toSetUserRoles")
	@ResponseBody
	public JsonData toSetUserRoles(@RequestBody SysUserRole[] sysUserRoles){
		JsonData jsonData = new JsonData();
		try {
			this.sysUserService.setUserRole(sysUserRoles);
			jsonData.setSuccess(true);
		} catch (Exception e) {
			jsonData.setSuccess(false);
		}
	
		return jsonData;
	}
	
	@RequestMapping("toSetRole") 
	public ModelAndView toUserRoleTree(Integer userId)throws Exception{
		ModelAndView mv = new ModelAndView("sys/auth/user_role");
		if(null!=userId){
			mv.addObject("userId",userId);
 			StringBuffer roleId =new StringBuffer();
 			
			List<SysUserRole> list = null;//sysUserRoleService.selectByUserId(userId);
			if(null!=list&&list.size()>0){
				for(SysUserRole ur:list){
					roleId.append(ur.getRoleId()+",");
				}
			}
			mv.addObject("roleId", roleId.toString());
		}
 
		return mv;
	}
	
/*	@RequestMapping("/findJsonById")
	@ResponseBody
	public JsonData findJsonById(@ModelAttribute SysUser po)throws Exception{
		JsonData json = new JsonData();
		try {
			po = this.sysUserService.selectByPrimaryKey(po.getId());
			json.setSuccess(true);
			json.setObj(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}*/
	
	/**
	* Description:获得中文字的拼音    
	* @Title: getUserAccount  
	 *//*
	@RequestMapping("/getUserAccount")
	@ResponseBody
	public JsonData getUserAccount(String name){
		JsonData jsonData=new JsonData();
		try {
			String pinyinForName = PinyinUtil.getPinyinForName(name);
			jsonData.setSuccess(true);
			jsonData.setObj(pinyinForName);
		} catch (Exception e) {
			jsonData.setSuccess(false);
		}
		return jsonData;
	}
	*/
	
	/*@RequestMapping("/getIsExist")
	@ResponseBody
	public JsonData  getIsExist(String name,String type){
		JsonData jsonData=new JsonData();
		boolean findIsExist = sysUserService.findIsExist(name, type);
		jsonData.setSuccess(findIsExist);
		return jsonData;
		
	}*/
	
}
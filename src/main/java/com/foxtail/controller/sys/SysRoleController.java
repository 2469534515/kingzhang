package com.foxtail.controller.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.common.web.JsonData;
import com.foxtail.core.util.PageUtils;
import com.foxtail.model.sys.SysRole;
import com.foxtail.model.sys.SysUserRole;
import com.foxtail.service.sys.SysRoleService;
import com.foxtail.vo.tree.TreeNode;

@Controller
@RequestMapping("sys/auth/role") 
public class SysRoleController extends BaseController {
	
	private final static Logger log= Logger.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping
	public String toMain(String sysModule){
		
		return getMainJsp(sysModule);
	}
	
	@RequestMapping("/toedit") 
	public String  toAdd(String sysM,String sysA,String id,ModelMap model){
		String jsp= getEditJsp(sysM);
		if(isEditPage(sysA))
			model.put("vo", sysRoleService.getById(id));
		return jsp;
	}

	
	@RequestMapping("/view") 
	@ResponseBody
	public DataGrid list (HttpServletRequest request,String kw) {
		Pagination pagination = sysRoleService.findForPage(getPagination(request),kw);
		
		return DataGridResult.getResult(pagination);
	}
		
	
	
	
	
	@RequestMapping("save") 
	@ResponseBody
	public Object save(SysRole po) {
		
			this.sysRoleService.save(po);
			return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteById(String ids) {
		this.sysRoleService.deleteIds(ids.split(","));
		return JsonResult.getSuccessResult();
	}
	
	
	
	@RequestMapping("update")
	@ResponseBody
	public Object editSubmit(SysRole po)throws Exception{
		
			this.sysRoleService.update(po);
			
		return JsonResult.getSuccessResult();
	}
	
	
	
	@RequestMapping("/loadRoleTree") 
	@ResponseBody
	public List<TreeNode> loadGenerlRoleTree(String roleId){
	   
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		//只读取通用角色
		SysRole po = new SysRole(); 
		List<SysRole> list = this.sysRoleService.selectList(po);
		for(SysRole sysRole :list){
			TreeNode treeNode = new TreeNode(); 
			treeNode.setId(sysRole.getId()+"");
			treeNode.setText(sysRole.getName());
			treeNode.setpId("0");
			treeNode.setIsParent(false);
			if(roleId.indexOf(sysRole.getId()+"")>=0){
				treeNode.setChecked(true);
			} 
			treenodes.add(treeNode);
		}
		return treenodes;
	}
	
	@RequestMapping("/toAuthorization") 
	@ResponseBody
	public JsonData toAuthorization(String roleid, String resids){
		JsonData jsonData = new JsonData();
		try {
			
			String[] residArr = null;
			if(StringUtils.isEmpty(resids))residArr = new String[0];
			else residArr = resids.split(",");
			this.sysRoleService.setRoleResources(roleid,residArr);
			jsonData.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setSuccess(false);
		}
	
		return jsonData;
	}
	
	@RequestMapping("/copyRes") 
	@ResponseBody
	public Object copyRes(String roleid,String copyRoleid){
		
			this.sysRoleService.copyResources(roleid, copyRoleid);;
			
		return JsonResult.getSuccessResult();
	}
	
	
	
}
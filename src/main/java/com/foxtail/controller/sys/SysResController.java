package com.foxtail.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.foxtail.model.sys.SysResource;
import com.foxtail.service.sys.SysResService;
import com.foxtail.vo.tree.TreeNode;

@Controller
@RequestMapping("sys/auth/res") 
public class SysResController extends BaseController {
	
	private final static Logger log= Logger.getLogger(SysResController.class);
	
	@Autowired()
	private SysResService sysResService; 
	
	@RequestMapping() 
	public String toMain(String sysModule){
		return getMainJsp(sysModule);
	}

	
	@RequestMapping("/view") 
	@ResponseBody
	public DataGrid list (HttpServletRequest request,String kw,String parentId) {
		
		Pagination pagination = sysResService.findForPage(getPagination(request), kw,parentId);
		
		return DataGridResult.getResult(pagination);
	}
		
	
	@RequestMapping("toedit") 
	public String toAdd(String sysM,String sysA,String id,String parentId,Integer orderNum,ModelMap model){
		
		String jsp= getEditJsp(sysM);
		if(!isEditPage(sysA)) {
			SysResource sysResource = sysResService.getById(parentId);
			model.put("vo", sysResource);
			
		}else 
			model.put("vo", sysResService.getById(id));
		return jsp;
	
	}
	
	
	@RequestMapping("save") 
	@ResponseBody
	public Object save(SysResource po) {
	
			this.sysResService.save(po);
			return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteById(String  ids) {
		
		sysResService.delete(ids.split(","));
		return JsonResult.getSuccessResult();
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public Object update(SysResource po){
		
	this.sysResService.update(po);
	return JsonResult.getSuccessResult();
		
	}
	
	
	
	@RequestMapping("toTree") 
	public ModelAndView toTree()throws Exception{
		ModelAndView mv = new ModelAndView("jsp/sys/sysresource/sysresource_tree");
		return mv;
	}
	
	@RequestMapping("loadTree") 
	@ResponseBody
	public List<TreeNode> loadTree(String id){
		if(id==null)
		{
			id ="0";
		}
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		
		List<SysResource> list = this.sysResService.selectListByParentId(id);
		for(SysResource sysResource :list){
			TreeNode treeNode = new TreeNode();
			Map<String,Object> attributes = new HashMap<String,Object>();
			treeNode.setId(sysResource.getId()+"");
			treeNode.setText(sysResource.getName());
			treeNode.setpId(sysResource.getParentid()+"");
			treeNode.setState("closed");
			
			List<SysResource> listChilden = sysResService.selectListByParentId(sysResource.getId());
			if(listChilden.size() == 0){
				treeNode.setIsParent(false);
			}
			attributes.put("resourceLevel", sysResource.getLevel());
			treeNode.setAttributes(attributes);
			
			treenodes.add(treeNode);
		}
		return treenodes;
	}
	
	@RequestMapping("/toSelectTree") 
	public ModelAndView toSelectTree(Integer roleId)throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysresource/sysresource_selecttree");
		mv.addObject("roleId", roleId);
		return mv;
	}
	
	@RequestMapping("/loadSelectTree") 
	@ResponseBody
	public List<TreeNode> loadSelectTree(String id){

		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		List<SysResource> list = sysResService.findAuthorizationAll(id);
		for(SysResource sysResource:list){
			TreeNode node = new TreeNode();
			node.setId(sysResource.getId()+"");
			node.setText(sysResource.getName());
			node.setpId(sysResource.getParentid());
			List<SysResource> listChilden = sysResService.selectListByParentId(sysResource.getId());
			if(listChilden.size() == 0){
				node.setIsParent(false);
			}
			if(null != sysResource.getParentid() && "0".equals(sysResource.getParentid())){
				node.setOpen(true);
			}
			if(null != id){
				node.setChecked(true);
			}
			treenodes.add(node);
		}
		
		return treenodes;
	}
}
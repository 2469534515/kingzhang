package com.foxtail.controller.member;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import com.foxtail.common.AppModelMap;
import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.SpringFileupload;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.controller.LoginController;
import com.foxtail.core.sys.NodeBean;
import com.foxtail.model.member.MemMemberList;
import com.foxtail.model.sys.SysUser;
import com.foxtail.service.member.MemMemberListService;
import com.foxtail.service.sys.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@RequestMapping(value = "member/member")
@Controller
public class MemberListController extends BaseController{

	@Autowired
	private MemMemberListService memMemberListService;
	
	
	
	


	@RequestMapping(value = "/memberList")
	public String getMemberListByPage() {
		
		
//		if (pageIndex < 0) {
//			pageIndex = 1;
//		}
//		count = memMemberListService.getMemMemberListCount();
//		pageCount = (count % 5 == 0)?(count/5):(count/5+1);
//		
//		if (pageIndex > pageCount) {
//			pageIndex = pageCount;
//		}
//		
//		startNum = (pageIndex - 1) * 5;
// 		memMemberList = memMemberListService.findMemberListByPage(kw, startNum);
//		model.put("memberList", memMemberList);
		
		return "member/memberList";
	}
	
	//多条件查询分页
//	@RequestMapping(value = "/memberList")
	@RequestMapping(value = "/memberUserList")
	@ResponseBody
	public DataGrid list(HttpServletRequest request,MemMemberList memMemberList){
		System.out.println("从前台传过来的form信息\t昵称:\t" + memMemberList.getmName() + "\t开始时间:\t" + memMemberList.getBeforeTime() + "\t结束时间:\t" + memMemberList.getAfterTime());
	    Pagination pagination = memMemberListService.findMemberListByPage(getPagination(request), memMemberList);
 		return DataGridResult.getResult(pagination);
	}
	

	//根据会员id获得会员对象
	@RequestMapping("/toUpdateView")
	public ModelAndView toAmendView(Integer id){
		ModelAndView mv = new ModelAndView("member/updateMember");
		MemMemberList memMemberList = new MemMemberList();
		memMemberList = memMemberListService.getMemMemberListById(id);
//		mv.getModelMap().addAttribute("id", id);
		mv.getModelMap().addAttribute("memMemberList",memMemberList );
		return mv;
	}
	
	//根据会员对象修改会员对象
	@RequestMapping("/updaterMember")
	@ResponseBody
	public String updateByPrimaryKey(MemMemberList memMemberList,MultipartFile imgs,HttpServletRequest request){
		String path = request.getServletContext().getRealPath("/");
		try {
			memMemberList.setHeadImg(SpringFileupload.upload(imgs, path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer updateByPrimaryKey = memMemberListService.updateMemberById(memMemberList);
		return "100";
	}
	
	
	//跳转添加信息的页面
	@RequestMapping("/addMemberView")
	public ModelAndView addMemberView(){
		ModelAndView mv = new ModelAndView("member/addMember");
		return mv;
	}
	
	//添加会员信息
	@ResponseBody
	@RequestMapping("/addMember")
	public String addMember(MemMemberList memMember,MultipartFile imgs,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/");
		long currentTimeMillis = System.currentTimeMillis();
		memMember.setRegTime(currentTimeMillis);
		
		try {
			memMember.setHeadImg(SpringFileupload.upload(imgs, path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println(memMember + "\t哈哈哈\t" + memMember.getmName() + "\t" + memMember.getHeadImg());
		Integer addMember = memMemberListService.addMemMemberByMember(memMember);
		System.out.println("返回的状态" + addMember);
		return "100";
	}
	
	
	//删除多条
		@RequestMapping("/deleteMultiple")
		@ResponseBody
		public Object deleteMultiple(String ids) {
			System.out.println("********" + ids);
			 String[] s = ids.split(",");
			 int[] ides = new int[s.length]; 
			   for(int i = 0;i<s.length;i++){
			     ides[i] = Integer.parseInt(s[i]);
			   }
			   
			 Integer deleteMultiple = memMemberListService.deleteMultiple(ides);
			 System.out.println("返回的状态\t" + deleteMultiple);
			return JsonResult.getSuccessResult();
		}

	
	
//	/**
//	 * 新增用户/带图片上传功能
//	 */
//	@RequestMapping("/add") 
//	@ResponseBody
//	public JsonData add(User user,MultipartFile imgs,HttpServletRequest request) {
//		String path = request.getServletContext().getRealPath("/");
//		JsonData json = new JsonData();
//		try {
//			long currentTimeMillis = System.currentTimeMillis();
//			user.setCreatetime(currentTimeMillis);
//			user.setImg(SpringFileupload.upload(imgs, path));
//			us.insert(user);
//			json.setSuccess(true);
//			json.setMsg("添加成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return json;
//	}
//	
//	/**
//	 * 跳转到更新页面
//	 */
//	@RequestMapping("/editById")
//	public ModelAndView editById(User user)throws Exception{
//		ModelAndView mv = new ModelAndView("jyg/user/user_edit");
//		user = us.selectByPrimaryKey(user.getId());
//		mv.addObject("jyguser", user);
//		return mv;
//	}
//	
//	/**
//	 * 更新提交
//	 */
//	@RequestMapping("/editSubmit")
//	@ResponseBody
//	public JsonData editSubmit(User user,@RequestParam(required=false)MultipartFile imgs,HttpServletRequest request)throws Exception{
//		JsonData json = new JsonData();
//		try {
//			User beforUser= us.selectByPrimaryKey(user.getId());
//			if(imgs.isEmpty()) {
//				user.setImg(beforUser.getImg());
//			}else {
//	            String fileName = beforUser.getImg();
//	        	String a = request.getServletContext().getRealPath("/");
//	        	File file = new File(a);
//	        	String beforPhotoPath = new File(file.getParent()).getPath();
//	        	String deletePhoto = beforPhotoPath+fileName;
//	        	new File(deletePhoto).delete();
//	        	//新增图片
//	            String path = request.getServletContext().getRealPath("/");
//	            user.setImg(SpringFileupload.upload(imgs, path));
//			}
//			us.updateByPrimaryKey(user);
//			json.setSuccess(true);
//			json.setMsg("修改成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return json;
//	}


	
	
//	@RequestMapping(value = "/memberList")
//	@ResponseBody
//	public Object list(HttpServletRequest request,String mName){
//		
//		com.github.pagehelper.PageInfo pagination = memMemberListService.findMemberListByPage(getPagination(request), mName);
//		return pagination;
//	}
	
	
	
	
	
	
	
	
}

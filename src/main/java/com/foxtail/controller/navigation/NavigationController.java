package com.foxtail.controller.navigation;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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
import com.foxtail.model.goods.Goods;
import com.foxtail.model.message.Message;
import com.foxtail.model.navigation.Navigation;
import com.foxtail.service.navigation.NavigationService;

@RequestMapping("navigation")
@Controller
public class NavigationController extends BaseController{

		@Autowired
		private NavigationService navigationService;
		
		
		@RequestMapping("/navigationList")
		public String toNavigationList() {
			return "navigation/homePageNavigation";
		}
		
		
		/*
		 * 分页获得首页导航信息
		 */
		@RequestMapping("/getNavigationList")
		@ResponseBody
		public DataGrid list (HttpServletRequest request) {
			
			Pagination pagination = navigationService.getAllNavigationByPage(getPagination(request));
			return DataGridResult.getResult(pagination);
		} 
		
		/*
		 *删除首页导航信息/批量删除 
		 */
		@RequestMapping("/deleteNavigation")
		@ResponseBody
		public Object deleteNavigation(String ids) {
			System.out.println("********" + ids);
			String[] s = ids.split(",");
			int[] ides = new int[s.length];
			for(int i = 0;i < s.length;i++) {
				ides[i] = Integer.parseInt(s[i]);
			}
			
			int num = navigationService.deleteNavigation(ides);
			System.out.println("返回的结果是\t" + num);
			return JsonResult.getSuccessResult();
		}
		
		/*
		 * 获得首页导航信息通过id并跳转到修改首页导航页面
		 */
		@RequestMapping("/toUpdateNavigation")
		public ModelAndView updateNavigationView(Integer id){
			System.out.println("into updateNavigationView()...。。。。。。");
			ModelAndView mv = new ModelAndView("navigation/updateHomePageNavigation");

			Navigation navigation = new Navigation(); 

			navigation = navigationService.getNavigationById(id);

			mv.getModelMap().addAttribute("navigation",navigation);
			
			return mv;
		} 
		
		/*
		 * 通过首页导航对象修改首页导航信息
		 */
		@RequestMapping("/updateNavigation")
		@ResponseBody
		public String updateNavigation(Navigation navigation,MultipartFile imgs,HttpServletRequest request) {
			
			Navigation before_navigation = navigationService.getNavigationById(navigation.getId());
			
			if(imgs.isEmpty()) {
				navigation.setNavigationPhoto(before_navigation.getNavigationPhoto());
			}else {
				String fileName = before_navigation.getNavigationPhoto();
				String beforPath = request.getServletContext().getRealPath("/");
				File file = new File(beforPath);
				String beforPhotoPath = new File(file.getParent()).getPath();
				String deletePhoto = beforPhotoPath+fileName;
				new File(deletePhoto).delete();
				
				//新增图片
				String path = request.getServletContext().getRealPath("/");
				try {
					navigation.setNavigationPhoto(SpringFileupload.upload(imgs, path));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			int num = navigationService.updateNavigation(navigation);
			System.out.println("从后台返回过来的状态\t" + num);
			return "100";
		}
		
}

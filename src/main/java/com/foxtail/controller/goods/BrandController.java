package com.foxtail.controller.goods;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.SpringFileupload;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.goods.Brand;
import com.foxtail.model.goods.Classify;
import com.foxtail.service.goods.BrandService;
import com.github.pagehelper.PageInfo;

@RequestMapping("brand")
@Controller
public class BrandController extends BaseController{
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping("/brandList")
	public String toBrandList() {
		
		return "goods/brandList";
	}
	
	//跳转到添加商品品牌页面
	@RequestMapping("/toAddBrand")
	public ModelAndView toAddBrand(){
		ModelAndView mv = new ModelAndView("goods/addBrand");
//		Classify classify = classifyService.getCalssifyById(id);
//		mv.getModelMap().addAttribute("classify", classify);
		return mv;
	}
	
	/*
	 * 通过Ajax提交添加商品分类并返回添加页面
	 */
	@RequestMapping("/addBrand")
	@ResponseBody
	public String addBrand(Brand brand,MultipartFile imgs,HttpServletRequest request) {
		System.out.println("into addBrand()...........\t" + brand.getBrandClassify() + "\t" + brand.getBrandName() + "\t" + brand.getBrandSort());
		String path = request.getServletContext().getRealPath("/");
	
		try {
			brand.setBrandPhoto(SpringFileupload.upload(imgs, path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		brand.setBrandStatus(1);
		int num = brandService.addBrand(brand);
		System.out.println("添加品牌后的状态\t" + num);
		
		return "100";
	}
	
	//跳转到修改商品品牌页面
	@RequestMapping("/toUpdateBrand")
	public ModelAndView toUpdateBrand(int id){
		ModelAndView mv = new ModelAndView("goods/updateBrand");
		Brand brand = brandService.getBrandById(id);
		mv.getModelMap().addAttribute("brand", brand);
		return mv;
	}
	
	//分页显示商品品牌信息
	@RequestMapping("/getBrandList")
	@ResponseBody
	public DataGrid getBrandList (HttpServletRequest request,Brand brand) {
		System.out.println("into getBrandList().............\tbrandClassify:" + brand.getBrandClassify() + "\tbrandName:" + brand.getBrandName());
			
		Pagination pagination = brandService.getAllBrandByPage(getPagination(request),brand);
			
		return DataGridResult.getResult(pagination);
	}
	
	
	// 通过品牌信息修改品牌信息
	@RequestMapping("/updateBrand")
	@ResponseBody
	public String updateBrand(Brand brand, @RequestParam(value = "imgs", required = false) MultipartFile imgs,HttpServletRequest request) {
		System.out.println("into updateBrand.............\t" + imgs);
		System.out.println(imgs.isEmpty());
		Brand before_brand = brandService.getBrandById(brand.getId());
		if (imgs.isEmpty()) {
			brand.setBrandPhoto(before_brand.getBrandPhoto());
		} else {
			String fileName = before_brand.getBrandPhoto();
			String beforPath = request.getServletContext().getRealPath("/");
			File file = new File(beforPath);
			String beforPhotoPath = new File(file.getParent()).getPath();
			String deletePhoto = beforPhotoPath + fileName;
			new File(deletePhoto).delete();

			// 新增图片
			String path = request.getServletContext().getRealPath("/");

			try {
				brand.setBrandPhoto(SpringFileupload.upload(imgs, path));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		int num = brandService.updateBrandByBrand(brand);
		System.out.println("返回的状太\t" + num);
		return "100";
	}
	
	/*
	 * 批量删除或单个删除
	 */
	@RequestMapping("/deleteBrand")
	@ResponseBody
	public Object deleteBrand(String ids) {
		
		System.out.println("********" + ids);
		 String[] s = ids.split(",");
		 int[] ides = new int[s.length]; 
		   for(int i = 0;i<s.length;i++){
		     ides[i] = Integer.parseInt(s[i]);
		   }
		   
		   int num = brandService.deleteBrand(ides);
		   System.out.println("从后台获取的状态\t" + num);
		
		return JsonResult.getSuccessResult();
	}
	
	/*
	 * 获取商品名称集合
	 */
	@RequestMapping("/getBrandName")
	@ResponseBody
	public PageInfo<Brand> getBrandName(){
		List<Brand> list = brandService.getBrandName();
		PageInfo page = new PageInfo(list);
		return page;
	}
}

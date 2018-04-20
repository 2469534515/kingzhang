package com.foxtail.controller.info;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.info.Posts;
import com.foxtail.service.info.PostsService;

@Controller
@RequestMapping(value="/infoment/posts")
public class PostsController extends BaseController{

	@Autowired
	private PostsService postsservice;
	
	/**
	 * 页面
	 * @return
	 */
	@RequestMapping(value="/posts")
	public String findAllColumn() {
		return "info/posts/posts";
	}
	/**
	 * 分页显示
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/postss")
	public DataGrid findAllColumns(HttpServletRequest request,Posts posts) {
		Pagination pagination = postsservice.findAll(getPagination(request), posts);
		return DataGridResult.getResult(pagination);
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deposts")
	public Object deColumns(String ids) {
		this.postsservice.dePosts(ids.split(","));
		return JsonResult.getSuccessResult();
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value="/addpostsView")
	public ModelAndView AddView() {
		ModelAndView view = new ModelAndView("/info/posts/addposts");
		return view;
	}
	/**
	 * 添加
	 * @param columns
	 * @return
	 */
	@RequestMapping(value="/addposts")
	@ResponseBody
	public String AddColumn(Posts posts,MultipartFile img,HttpServletRequest request) {
		long currentTimeMillis = System.currentTimeMillis();
        posts.setTime(currentTimeMillis);
        String path = request.getServletContext().getRealPath("/");
        posts.setImage(path);
		postsservice.addPosts(posts);
		return "100";
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequestMapping(value="/upadtepostsView")
	public ModelAndView updateView(Integer id) throws Exception{
		ModelAndView view = new ModelAndView("/info/posts/updateposts");
		Posts posts = new Posts();
		posts = postsservice.findAllById(id);
		view.getModelMap().addAttribute("posts", posts);
		return view;
	}
	/**
	 * 修改
	 * @param columns
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateposts")
	public String updateColumn(Posts posts,MultipartFile newimg,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/");
		posts.setImage(path);
		Integer update = postsservice.updatePosts(posts);
		return "100";
	}
}

package com.foxtail.service.info.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.info.PostsDao;
import com.foxtail.model.info.Posts;
import com.foxtail.service.info.PostsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class PostsServiceImpl implements PostsService{
	
	private final static Logger log= Logger.getLogger(PostsServiceImpl.class);
	
	@Autowired
	private PostsDao postsdao;
	
	/**
	 * 查询分页
	 */
	@Override
	public Pagination findAll(Pagination page, Posts posts) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page Posts = (Page) postsdao.findAll(posts);
		page.setTotalCount((int) Posts.getTotal());
		page.setList(Posts.getResult());
		return page;
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Posts findAllById(Integer id) {
		// TODO Auto-generated method stub
		return postsdao.findAllById(id);
	}

	/**
	 * 删除
	 */
	@Override
	public void dePosts(String[] ids) {
		// TODO Auto-generated method stub
		postsdao.dePosts(ids);
	}

	/**
	 * 修改
	 */
	@Override
	public Integer updatePosts(Posts posts) {
		// TODO Auto-generated method stub
		return postsdao.updatePosts(posts);
	}

	/**
	 * 添加
	 */
	@Override
	public void addPosts(Posts posts) {
		// TODO Auto-generated method stub
		postsdao.addPosts(posts);
	}

}

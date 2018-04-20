package com.foxtail.service.info;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.info.Posts;

public interface PostsService {

	public Pagination findAll(Pagination page,Posts posts);//分页数据
	
	public Posts findAllById(Integer id);//根据id查找
	
	public void dePosts(String[] ids);//删除
	
	public Integer updatePosts(Posts posts);//修改
	
	public void addPosts(Posts posts);//添加
}

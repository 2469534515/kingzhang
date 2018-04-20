package com.foxtail.dao.mybatis.info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.info.Posts;

public interface PostsDao {
      
	public List<Posts> findAll(@Param("posts")Posts posts);//分页数据
	
	public Posts findAllById(@Param("id")Integer id);//根据id查找
	
	public void dePosts(@Param("ids")String[] ids);//删除
	
	public Integer updatePosts(Posts posts);//修改
	
	public void addPosts(Posts posts);//添加
}

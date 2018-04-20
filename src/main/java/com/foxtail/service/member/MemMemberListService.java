package com.foxtail.service.member;

import java.util.List;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.goods.Goods;
import com.foxtail.model.member.MemMemberList;
import com.github.pagehelper.PageInfo;

public interface MemMemberListService {
	
	//根据条件分页查询所有会员
	public Pagination findMemberListByPage(Pagination page,MemMemberList memberList);

	//获取会员的数量
	public Integer getMemMemberListCount();
	
	//根据会员Id删除会员
	public Integer deleteMemeberById(Integer id);
	
	//通过会员id获得会员对象
	public MemMemberList getMemMemberListById(Integer id);
	
	//通过会员idx修改会员信息
	public Integer updateMemberById(MemMemberList memMemberList);
	
	//插入会员信息
	public Integer addMemMemberByMember(MemMemberList memMemberList);
	
	//批量删除会员信息
	public Integer deleteMultiple(int[] ides);
	
	
}

package com.foxtail.dao.mybatis.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.member.MemMemberAdd;

public interface MemMemberAddDao {
	
	/*
	 * 分页查询会员收藏通过会员对象
	 */
	public List<MemMemberAdd> getMemMemberAddListByPage(@Param("memMemberAdd")MemMemberAdd memMemberAdd);
	
	
	/*
	 * 删除会员收藏信息/批量删除
	 */
	public int deleteMemberAdd(int[] ides);
		
}

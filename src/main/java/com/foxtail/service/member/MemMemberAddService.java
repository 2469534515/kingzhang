package com.foxtail.service.member;

import java.util.List;

import com.foxtail.common.page.Pagination;
import com.foxtail.model.member.MemMemberAdd;

public interface MemMemberAddService {
	
	/*
	 * 分页查询会员收藏通过会员对象
	 */
	public Pagination getMemMemberAddListByPage(Pagination page,MemMemberAdd memMemberAdd);
	
	
	/*
	 * 删除会员收藏信息/批量删除
	 */
	public int deleteMemberAdd(int[] ides);

}

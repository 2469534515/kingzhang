package com.foxtail.dao.mybatis.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxtail.model.member.MemMemberList;
import com.foxtail.model.sys.SysRole;

public interface MemMemberListDao {
	
	/*
	 *根据条件分页查询所有会员
	 */
//	public List<MemMemberList> findMemberListByPage(@Param(value = "startPro") Integer startPro,@Param(value = "pageSize") Integer pageSize,@Param(value = "mName") String mName,@Param(value = "bete")Integer bete,@Param(value = "afte")Integer afte);
	public List<MemMemberList> findMemberListByPage(@Param("memberList")MemMemberList memberList);
	 
	
	/*
	 * 查询会员数量
	 */
	public Integer getMemMemberListCount();
	
	
	/*
	 * 删除会员
	 */
	public Integer deleteMemeberById(@Param("id") Integer id);
	
	
	/*
	 * 添加会员
	 */
	public Integer addMemMemberByMember(MemMemberList memMemberList);
	
	/*
	 * 批量删除会员
	 */
	public Integer deleteMemMemberListById(@Param("ids")String[] ids);

	/*
	 * 根据会员id获取会员对象
	 */
	public MemMemberList getMemMemberListById(@Param("id") Integer id);
	
	/*
	 * 根据会员对象修改会员信息
	 */
	public Integer updateMemberById(MemMemberList memMemberList);
	
	/*
	 * 批量删除
	 */
	public Integer deleteMultiple(int[] ides);
	
	
	
}
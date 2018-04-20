package com.foxtail.service.member.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxtail.common.page.Pagination;
import com.foxtail.dao.mybatis.member.MemMemberListDao;
import com.foxtail.model.member.MemMemberList;
import com.foxtail.service.member.MemMemberListService;
import com.foxtail.service.sys.impl.SysUserServiceImpl;
import com.github.pagehelper.Page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MemMemberListServiceImpl implements MemMemberListService{
	
	private final static Logger log = Logger.getLogger(MemMemberList.class);
	
	@Autowired
	private MemMemberListDao memMemberListDao;

	

	//获得总条数
	@Override
	public Integer getMemMemberListCount(){
		// TODO Auto-generated method stub
			return memMemberListDao.getMemMemberListCount();
		
	}

	

	//通过id删除会员信息
	@Override
	public Integer deleteMemeberById(Integer id) {
		// TODO Auto-generated method stub
		return memMemberListDao.deleteMemeberById(id);
	}

	//通过会员id获得会员对象
	@Override
	public MemMemberList getMemMemberListById(Integer id) {
		// TODO Auto-generated method stub
		
		return memMemberListDao.getMemMemberListById(id);
	}

	
	//修改会员信息通过会员id
	@Override
	public Integer updateMemberById(MemMemberList memMemberList) {
		// TODO Auto-generated method stub
		return memMemberListDao.updateMemberById(memMemberList);
	}

	//插入会员信息
	@Override
	public Integer addMemMemberByMember(MemMemberList memMemberList) {
		// TODO Auto-generated method stub
		return memMemberListDao.addMemMemberByMember(memMemberList);
	}

	//批量删除会员信息
	@Override
	public Integer deleteMultiple(int[] ides) {
		// TODO Auto-generated method stub
		return memMemberListDao.deleteMultiple(ides);
	}

	//多条件查询分页
	@Override
	public Pagination findMemberListByPage(Pagination page, MemMemberList memberList) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry = (Page) memMemberListDao.findMemberListByPage(memberList);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
	}

	


//	@Override
//	public PageInfo findMemberListByPage(Pagination page, String mName) {
//		PageHelper.startPage(1, 8);
//		List<MemMemberList> list = memMemberListDao.findMemberListByPage(mName);
//		//page.setTotalCount((int)listCountry.getTotal());
//		//page.setList(listCountry.getResult());
//		PageInfo pageInfo = new PageInfo(list);
//		return pageInfo;
//	}

}

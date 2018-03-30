package com.foxtail.dao.mybatis.sys;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {	
    
    void delete(@Param("uids")String[] uids);
    
    void save(@Param("uid")String uid,@Param("roleid")String roleid);
}

package com.pro.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;

public interface AccountMapper {
	List<Role> findRoleListByUserId(@Param("userId")Integer id);

	List<Resource> findResourceListByRoleId(@Param("roleId")Integer roleId);

	List<Resource> findMenuByModuleIdAndUserId(@Param("moduleId")Integer moduleId, @Param("userId")Integer id);


	List<Resource> findModuleListUserId(@Param("userId")Integer userId);

	/***
	 * 查询所有有效的角色
	 * @return
	 */
	List<Role> findAllRoles();
}

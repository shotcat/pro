package com.pro.sys.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pro.sys.entity.Role;


public interface RoleMapper {

	List<Role> findAllRole();

	/**
	 * 根据用户id获取所拥有的角色id集合
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdListByUserId(@Param("userId")Integer userId);
/***
 * 根据用户id删除用户所拥有的角色
 * @param userId
 */
	void deleteRoleByUserId(@Param("userId")Integer userId);

	/**
	 * 批量插入用户与角色关联
	 * @param userId
	 * @param roleIdList
	 */
void createUserRoleByUserId(@Param("userId")Integer userId, @Param("roleIdList")List<Integer> roleIdList);

/**
 * 根据条件分页查询角色列表
 * @param i
 * @param rows
 * @param userName
 * @param startTime
 * @param endTime
 * @param state
 * @return
 */
	List<Role> findRoleList(@Param("resultStart")int start,@Param("rows") int rows, @Param("roleName")String roleName,@Param("startTime") Date startTime,
			@Param("endTime")Date endTime, @Param("state")Integer state);

	/***
	 * 查询角色总数根据条件
	 * @param userName
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @return
	 */
	int findRoleCount(@Param("roleName")String roleName, @Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("state")Integer state);

	
	/***
	 * 检查角色是否存在
	 * @param roleName
	 * @param roleId
	 * @return
	 */
	Role checkRoleName(@Param("roleName")String roleName, @Param("roleId")Integer roleId);

	/**
	 * 新增角色
	 * @param role
	 */
	void addRole(Role role);

	/**
	 * 更新用户
	 * @param role
	 */
	void editRole(Role role);
   
}
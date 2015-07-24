package com.pro.sys.service;

import java.util.Date;
import java.util.List;

import com.pro.sys.entity.Role;
import com.pro.sys.exception.ServiceException;

/**
 * 角色管理
 * @author gaoyuandong
 * 2015年4月30日 下午3:23:23
 */
public interface RoleService {

	/***
	 * 查询所有角色
	 * @return
	 */
	List<Role> findAllRole() throws ServiceException;
	
	/**
	 * 根据用户id查询用户所拥有的角色
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	List<Integer> findRoleIdListByUserId(Integer userId)throws ServiceException;

	/***
	 * 根据用户id设置角色
	 * @param userId
	 * @param roleIdStr
	 */
	void settingRoleByUserId(Integer userId, String roleIdStr) throws ServiceException;

	/***
	 * 分页查询角色列表
	 * @param page
	 * @param rows
	 * @param userName
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @return
	 */
	List<Role> findRoleList(int page, int rows, String roleName,
			Date startTime, Date endTime, Integer state);

	/***
	 * 根据条件查询角色总数
	 * @param userName
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @return
	 */
	int findRoleCount(String roleName, Date startTime, Date endTime,
			Integer state);

	/***
	 * 检查角色名称是否为空
	 * @param roleName 角色名称
	 * @param roleId 角色 id
	 * @return
	 */
	boolean checkRoleName(String roleName, Integer roleId) throws ServiceException;

	/***
	 * 添加角色
	 * @param role
	 */
	void addRole(Role role) throws ServiceException;

	/**
	 * 修改角色
	 * @param role
	 */
	void editRole(Role role)throws ServiceException;


}

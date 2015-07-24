package com.pro.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pro.sys.entity.Role;
import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.mapper.RoleMapper;
import com.pro.sys.service.RoleService;

/**
 * @author gaoyuandong
 * 2015年4月30日 下午3:21:25
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	/***
	 * 获取所有角色
	 */
	public List<Role> findAllRole() throws ServiceException {
		
		return this.roleMapper.findAllRole();
	}
	@Override
	public List<Integer> findRoleIdListByUserId(Integer userId)
			throws ServiceException {
		return this.roleMapper.findRoleIdListByUserId(userId);
	}
	@Override
	public void settingRoleByUserId(Integer userId, String roleIdStr)
			throws ServiceException {
		List<Integer> roleIdList = new ArrayList<Integer>();
		if(StringUtils.isNotBlank(roleIdStr)) {
			String[] strs = roleIdStr.split(",");
			for (int i = 0; i < strs.length; i++) {
				roleIdList.add(Integer.valueOf(strs[i]));
			}
		}
		
		//清空所有关联信息
		this.roleMapper.deleteRoleByUserId(userId);
		
		//添加用户与角色关联关系
		if(!CollectionUtils.isEmpty(roleIdList)) {
			this.roleMapper.createUserRoleByUserId(userId, roleIdList);
		}
	}
	/***
	 * 分页查询角色列表
	 */
	public List<Role> findRoleList(int page, int rows, String roleName,
			Date startTime, Date endTime, Integer state) {
		// TODO Auto-generated method stub
		return this.roleMapper.findRoleList((page-1)*rows, rows,roleName,startTime,endTime,state);
	}
	/***
	 * 查询角色总数
	 */
	public int findRoleCount(String roleName, Date startTime, Date endTime,
			Integer state) {
		return this.roleMapper.findRoleCount(roleName,startTime,endTime,state);
	}
	
	/***
	 * 检查角色是否为空
	 */
	public boolean checkRoleName(String roleName, Integer roleId)
			throws ServiceException {
        Role  role = this.roleMapper.checkRoleName(roleName, roleId);
        if (role == null) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * 新增角色
	 */
	public void addRole(Role role) throws ServiceException {
		
		this.roleMapper.addRole(role);
	}
	/***
	 * 更新用户
	 */
	public void editRole(Role role)throws ServiceException {

		this.roleMapper.editRole(role);
	}
	
	

}

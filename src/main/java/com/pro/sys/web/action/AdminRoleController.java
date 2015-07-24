package com.pro.sys.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.sys.annotation.DateParser;
import com.pro.sys.entity.Role;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.service.RoleService;
import com.pro.sys.util.JsonResult;

/**
 * @author gaoyuandong
 * 2015年4月28日 下午5:00:25
 */
@Controller
@RequestMapping("/admin/role")
public class AdminRoleController {

	@Autowired
	private RoleService roleService;
	
	/***
	 * 跳转到角色管理首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		
		return new ModelAndView("admin/role/index");
	}
	/***
	 * 查询用户列表
	 * @param page
	 * @param rows
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @param userName
	 * @return
	 */
	@RequestMapping("/findRoleList")
	@ResponseBody
	public Map<String, Object>  findRoleList(@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="10")int rows,
			Integer state,
			@DateParser(pattern="yyyy-MM-dd") Date startTime,
			@DateParser(pattern="yyyy-MM-dd") Date endTime,
			String roleName) {
		List<Role> users = this.roleService.findRoleList(page,rows,roleName,startTime,endTime,state);
		int count = this.roleService.findRoleCount(roleName,startTime,endTime,state);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", users);
		dataMap.put("total", count);
		return dataMap;
		
	}
	

	
	/***
	 * 获取所有角色
	 * @return
	 */
	@RequestMapping("/findAllRole")
	@ResponseBody
	public Map<String, Object>  findAllRole() {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Role> roles;
		try {
			roles = this.roleService.findAllRole();
			dataMap.put("rows", roles);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return dataMap;
		
	}
	
	
	/***
	 * 根据用户id查询用户所拥有的角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("findRoleIdListByUserId")
	@ResponseBody
	public JsonResult findRoleIdListByUserId(Integer userId) {
		
		JsonResult jsonResult = new JsonResult();
		if(userId == null) {
			jsonResult.setMessage("此用户不存在");
			return jsonResult;
		}
		try {
			List<Integer> roleIdList = this.roleService.findRoleIdListByUserId(userId);
			jsonResult.setSuccess(true);
			jsonResult.setSingleData(roleIdList);
		} catch (ServiceException e) {
			jsonResult.setMessage("出现异常,请重试!");
			e.printStackTrace();
		}
		return jsonResult;
		
	}
	@RequestMapping("settingRoleByUserId")
	@ResponseBody
	public JsonResult settingRoleByUserId(Integer userId,String roleIdStr) {
		
		JsonResult jsonResult = new JsonResult();
		if(userId == null) {
			jsonResult.setMessage("此用户不存在");
			return jsonResult;
		}
		try {
			this.roleService.settingRoleByUserId(userId,roleIdStr);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("角色设置成功");
		} catch (ServiceException e) {
			jsonResult.setMessage("出现异常,请重试!");
			e.printStackTrace();
		}
		return jsonResult;
		
	}
	
	
	/***
	 * 检查角色名称是否存在
	 * @param roleName 角色名称
	 * @param roleId  角色id 
	 * @return
	 */
	@RequestMapping("/checkRoleName")
	@ResponseBody
	public JsonResult checkRoleName(String roleName,Integer roleId) {
		
		JsonResult jsonResult = new JsonResult();
		if(StringUtils.isBlank(roleName)) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage("角色名称不能为空!");
		}
		try {
			
			boolean has = this.roleService.checkRoleName(roleName, roleId);
			if(has) {
				jsonResult.setSuccess(false);
				jsonResult.setSingleData("此角色已经存在！");
			} else {
				jsonResult.setSuccess(true);
				jsonResult.setMessage("成功");
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			jsonResult.setMessage("发生异常，请重试!");
		}
		return jsonResult;
	}
	
	
	/***
	 * 添加角色
	 * @return
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public JsonResult addRole(Role role) {
		JsonResult jsonResult = new JsonResult();
		
		if(role == null || StringUtils.isBlank(role.getRoleName())) {
			jsonResult.setMessage("请输入角色名称");
			return jsonResult;
		}
		try {
			
			this.roleService.addRole(role);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("保存成功");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			jsonResult.setMessage("新增角色发生异常，清重试!");
		}
		
		
		return jsonResult;
	}
	
	/***
	 * 更新角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/editRole")
	@ResponseBody
	public JsonResult editRole(Role role) {
		JsonResult jsonResult = new JsonResult();
		if(role.getRoleId() == null) {
			
			jsonResult.setMessage("此角色不存在");
			return jsonResult;
		}
		
		if(StringUtils.isBlank(role.getRoleName())) {
			jsonResult.setMessage("此角色名称不能为空");
		}
		
		try {
			
			this.roleService.editRole(role);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("更新成功");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return jsonResult;
	}
}

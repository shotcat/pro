package com.pro.sys.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.po.Tree;
import com.pro.sys.service.ResourceService;
import com.pro.sys.util.JsonResult;

/**
 * @author gaoyuandong
 * 2015年4月28日 下午5:00:29
 */
@Controller
@RequestMapping("/admin/resource")
public class AdminResourceController {

	@Autowired
	private ResourceService resourceService;
	
	/***
	 * 进入权限资源管理页面
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		
		return new ModelAndView("admin/resource/index");
	}
	
	/***
	 * 根据角色id查询当前角色所拥有的权限资源id集合
	 * @return
	 */
	@RequestMapping("/findDefaultResourceIdListByRoleId")
	@ResponseBody
	public JsonResult findDefaultResourceIdListByRoleId(Integer roleId) {
		
		JsonResult jsonResult = new JsonResult();
		try {
			
			List<Integer> resourceIdList = this.resourceService.findDefaultResourceIdListByRoleId(roleId);
			jsonResult.setSingleData(resourceIdList);
			jsonResult.setSuccess(true);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return jsonResult;
		
	}
	
	/***
	 * 查询所有权限资源
	 * @return
	 */
	@RequestMapping("/findAllResource")
	@ResponseBody
	public Map<String, Object>  findAllResouce() {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Role> roles;
		try {
			roles = this.resourceService.findAllResource();
			dataMap.put("rows", roles);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return dataMap;
		
	}
	
	/***
	 * 根据角色id 绑定权限资源
	 * @param roleId
	 * @param resourceIdStr
	 * @return
	 */
	@RequestMapping("/settingResourceByRoleId")
	@ResponseBody
	public JsonResult settingResourceByRoleId(Integer roleId,String resourceIdStr) {
		JsonResult jsonResult = new JsonResult();
		if(roleId == null) {
			jsonResult.setMessage("此角色不存在");
			return jsonResult;
		}
		try {
			this.resourceService.settingResourceByRoleId(roleId,resourceIdStr);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("权限资源设置成功");
		} catch (ServiceException e) {
			jsonResult.setMessage("出现异常,请重试!");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	/***
	 * 
	 * @return
	 */
	@RequestMapping("/findMenuList")
	@ResponseBody
	public List<Tree> findMenuListWithoutId() {
		return this.resourceService.findMenuList();
	}
	
	@RequestMapping("/addResource")
	@ResponseBody
	public JsonResult addResource(Resource resource) {
		JsonResult jsonResult = new JsonResult();
		
		if(resource == null || StringUtils.isBlank(resource.getResourceName())) {
			jsonResult.setMessage("请输入资源名称");
			return jsonResult;
		}
		try {
			Integer parentId = resource.getParentId();
			
			if(parentId == null) {
				resource.setType(0);
			} else {
				
				Resource parentResource = this.resourceService.findResourceById(parentId);
				Integer pId = parentResource.getParentId();
				if(pId == null) {
					resource.setType(1);
				} else {
					resource.setType(2);
				}
			}
			
			
			this.resourceService.addResource(resource);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("保存成功");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			jsonResult.setMessage("新增资源发生异常，清重试!");
		}
		
		
		return jsonResult;
	}
	
	@RequestMapping("/editResource")
	@ResponseBody
	public JsonResult editResource(Resource resource) throws ServiceException {
		JsonResult jsonResult = new JsonResult();
		if(resource.getResourceId() == null) {
			
			jsonResult.setMessage("此权限资源不存在");
			return jsonResult;
		}
		
		if(StringUtils.isBlank(resource.getResourceName())) {
			jsonResult.setMessage("此权限资源名称不能为空");
		}
		Integer parentId = resource.getParentId();
		
		if(parentId == null) {
			resource.setType(0);
		} else {
			
			Resource parentResource = this.resourceService.findResourceById(parentId);
			Integer pId = parentResource.getParentId();
			if(pId == null) {
				resource.setType(1);
			} else {
				resource.setType(2);
			}
		}
		
		this.resourceService.editResource(resource);
		jsonResult.setSuccess(true);
		jsonResult.setMessage("更新成功");
		
		return jsonResult;
	}
	
	
}

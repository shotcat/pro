package com.pro.sys.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.thrift.client.UserThriftClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pro.sys.annotation.DateParser;
import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.service.UserService;
import com.pro.sys.util.JsonResult;

/**
 * @author gaoyuandong
 * 2015年4月28日 下午5:00:22
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserThriftClient userThriftClient;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		
		return new ModelAndView("admin/user/index");
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
	@RequestMapping("/findUserList")
	@ResponseBody
	public Map<String, Object>  findUserList(@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="10")int rows,
			Integer state,
			@DateParser(pattern="yyyy-MM-dd") Date startTime,
			@DateParser(pattern="yyyy-MM-dd") Date endTime,
			String userName) {
		List<User> users = this.userService.findUserList(page,rows,userName,startTime,endTime,state);
		int count = this.userService.findUserCount(userName,startTime,endTime,state);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", users);
		dataMap.put("total", count);
		return dataMap;
		
	}
	
	
	/***
	 * 新增用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addUser",method={RequestMethod.POST})
	public JsonResult addUser(User user) {
		JsonResult jsonResult = new JsonResult();
		try {
			
			if(userThriftClient.checkUserName(user.getNickName(), 0) == true) {
				
				jsonResult.setMessage("此用户昵称已存在");
				return jsonResult;
			}
			if(userThriftClient.checkUserName(user.getUserName(), 0) == true) {
				
				jsonResult.setMessage("此用户名已存在");
				return jsonResult;
			}
			if(userThriftClient.checkEmail(user.getEmail(), 0) == true) {
				
				jsonResult.setMessage("此邮箱已存在");
				return jsonResult;
			}if(userThriftClient.checkMobile(user.getMobile(), 0) == true) {
				
				jsonResult.setMessage("此手机号码已存在");
				return jsonResult;
			}
			this.userService.addUser(user);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("保存成功");
		} catch (ServiceException e) {
			jsonResult.setMessage("发生异常，请重试");
			e.printStackTrace();
		}
		return jsonResult;
	}
	/***
	 * 更新用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editUser",method={RequestMethod.POST})
	public JsonResult editUser(User user) {
		JsonResult jsonResult = new JsonResult();
		try {
			
			Integer userId = user.getUserId();
			if(userId == null) {
				jsonResult.setMessage("此用户不存在");
				return jsonResult;
			}
			
			if(userThriftClient.checkUserName(user.getUserName(), userId) == true) {
				
				jsonResult.setMessage("此用户名已存在");
				return jsonResult;
			}
			if(userThriftClient.checkEmail(user.getEmail(), userId) == true) {
				
				jsonResult.setMessage("此邮箱已存在");
				return jsonResult;
			}if(userThriftClient.checkMobile(user.getMobile(), userId) == true) {
				
				jsonResult.setMessage("此手机号码已存在");
				return jsonResult;
			}
			this.userService.editUser(user);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("更新成功");
		} catch (ServiceException e) {
			jsonResult.setMessage("发生异常，请重试");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	/***
	 * 检查用户昵称是否存在
	 * @param nickName
	 * @param userId
	 * @return
	 */
	@RequestMapping("/checkNickName")
	@ResponseBody
	public JsonResult checkNickName(String nickName,Integer userId) {
		JsonResult jsonResult = new JsonResult();
		boolean  has  = false;
		if(StringUtils.isBlank(nickName)){
			jsonResult.setMessage("用户昵称不能为空");
			return jsonResult;
		}
		try {
			has = this.userService.checkNickName(nickName, userId);
			jsonResult.setSuccess(has);
		} catch (ServiceException e) {
			jsonResult.setMessage("发生异常，请重试!");
			e.printStackTrace();
		}
		return jsonResult;
		
	}
	/***
	 * 检查用户名是否存在
	 * @param nickName
	 * @param userId
	 * @return
	 */
	@RequestMapping("/checkUserName")
	@ResponseBody
	public JsonResult checkUserName(String userName,Integer userId) {
		JsonResult jsonResult = new JsonResult();
		boolean  has  = false;
		if(StringUtils.isBlank(userName)){
			jsonResult.setMessage("用户昵称不能为空");
			return jsonResult;
		}
		try {
			has = this.userService.checkUserName(userName, userId);
			jsonResult.setSuccess(has);
		} catch (ServiceException e) {
			jsonResult.setMessage("发生异常，请重试!");
			e.printStackTrace();
		}
		return jsonResult;
		
	}
	/***
	 * 检查邮箱是否存在
	 * @param nickName
	 * @param userId
	 * @return
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public JsonResult checkEmail(String email,Integer userId) {
		JsonResult jsonResult = new JsonResult();
		boolean  has  = false;
		if(StringUtils.isBlank(email)){
			jsonResult.setMessage("用户昵称不能为空");
			return jsonResult;
		}
		try {
			has = this.userService.checkEmail(email, userId);
			jsonResult.setSuccess(has);
		} catch (ServiceException e) {
			jsonResult.setMessage("发生异常，请重试!");
			e.printStackTrace();
		}
		return jsonResult;
		
	}
	/***
	 * 检查邮箱是否存在
	 * @param nickName
	 * @param userId
	 * @return
	 */
	@RequestMapping("/checkMobile")
	@ResponseBody
	public JsonResult checkMobile(String mobile,Integer userId) {
		JsonResult jsonResult = new JsonResult();
		boolean  has  = false;
		if(StringUtils.isBlank(mobile)){
			jsonResult.setMessage("手机号码不能为空");
			return jsonResult;
		}
		try {
			has = this.userService.checkMobile(mobile, userId);
			jsonResult.setSuccess(has);
		} catch (ServiceException e) {
			jsonResult.setMessage("发生异常，请重试!");
			e.printStackTrace();
		}
		return jsonResult;
		
	}
	
}

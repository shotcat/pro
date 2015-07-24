package com.pro.sys.web.action;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pro.sys.po.SystemMenu;
import com.pro.sys.security.SecurityUser;
import com.pro.sys.service.ResourceService;
import com.pro.sys.util.UserUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("admin/index");
		SecurityUser securityUser = UserUtils.findCurrentUser();
		Set<SystemMenu> systemMenus = this.resourceService.findAllMenusByUserId(securityUser.getUser().getUserId());
		modelAndView.addObject("systemMenus", systemMenus);
		modelAndView.addObject("name", "jack");
		return modelAndView;
	}
}

package com.pro.sys.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/index")
	public ModelAndView index() {
		
		return null;
	}
}

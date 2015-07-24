package com.pro.sys.web.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author dongtian
 * @date   2015年6月10日 下午12:39:06
 */
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> requiredType) {
	        return applicationContext.getBean(requiredType);
	    }
}

package com.pro.sys.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.CollectionUtils;

import com.pro.sys.entity.Resource;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.service.AccountService;

public class SecurityFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private static final Logger logger = Logger.getLogger(SecurityFilterInvocationSecurityMetadataSource.class);
	private Set<String> permissionList = new HashSet<String>();
	@Autowired
	private AccountService accountService;
	
	private void loadAllResourceList() {
		
		List<Resource> resourceList;
		try {
			resourceList = this.accountService.finAllResourceList(true);
			if (!CollectionUtils.isEmpty(resourceList)) {
				
				for (Resource resource : resourceList) {
					String url =resource.getUrl();
					url = url.split(".mvc")[0];
					permissionList.add(url);
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object){
		
		if (CollectionUtils.isEmpty(permissionList)) {
			this.loadAllResourceList();
			
		}
		FilterInvocation invocation = (FilterInvocation) object;
		String permission = invocation.getRequestUrl();
		if (permission.indexOf(";JSESSIONID=") != -1) {
			permission.substring(0, permission.indexOf(";JSESSIONID=") - 1);
		}
		if (permission.indexOf(".") != -1) {
			permission = permission.substring(0, permission.lastIndexOf("."));
		}
		
		

		
		
		logger.info("============ url :" + permission  +"============");
		Set<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
		if (permissionList.contains(permission)) {
			SecurityConfig config = new SecurityConfig(permission);
			configAttributes.add(config); 
			return configAttributes;
		} else {
			 return configAttributes; 
		}
		
		
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		if (CollectionUtils.isEmpty(permissionList)) {
			this.loadAllResourceList();
			
		}
		
		Set<ConfigAttribute> ConfigAttributes = new HashSet<ConfigAttribute>();
		for (String permission:permissionList) {
			SecurityConfig config = new SecurityConfig(permission);
			ConfigAttributes.add(config);
		}
		
		return ConfigAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return true;
	}

}

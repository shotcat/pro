package com.pro.sys.web.context;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.pro.sys.annotation.DateParser;

/**
 * 日期参数类型解析器
 * @author yuandong
 *
 */
public class DateHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	private static Logger log = Logger.getLogger(DateHandlerMethodArgumentResolver.class);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		DateParser dateParser =  parameter.getParameterAnnotation(DateParser.class);
		if(dateParser != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		String[] vals = webRequest.getParameterValues(parameter.getParameterName());
		if(vals == null || vals.length == 0) {
			return null;
		} else {
			
			if(StringUtils.isBlank(vals[0]))  {
				return null;
			}
			
			for (Iterator<String> itr = webRequest.getParameterNames(); itr.hasNext();) {
				String next =  itr.next();
				log.debug( next+" ： "+webRequest.getParameterValues(next)[0]);
			}
			DateParser dateParser = parameter.getParameterAnnotation(DateParser.class);
			String pattern = dateParser.pattern();
			String key = StringUtils.isBlank(dateParser.name()) ?parameter.getParameterName():dateParser.name();
			//TODO
			log.debug(parameter.getParameterName());
			Object obj = BeanUtils.instantiate(parameter.getParameterType());
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Date date =  dateFormat.parse(webRequest.getParameterValues(parameter.getParameterName())[0]);
			if(obj instanceof Date) {//是日期类型
				return date;
			} else {//是普通的 pojo
				Field[] fields  = parameter.getParameterType().getDeclaredFields();
				//TODO
				return null;
			}
			
		}
		
	}

}

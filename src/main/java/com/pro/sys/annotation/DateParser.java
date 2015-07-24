package com.pro.sys.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日期类型参数注解
 * 用于{@com.pro.sys.context.DateHandlerMethodArgumentResolver}
 * @author gaoyuandong
 * 2015年4月29日 上午11:37:47
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateParser {
	//名称
	String name() default "";
	//格式化
	String pattern() default "yyyy-MM-dd hh:mm:ss";
}

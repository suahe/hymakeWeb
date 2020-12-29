package com.dzb.console.syslog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 日志记录器接口
 * @author 38840472@qq.com
 * @date 2020-04-24 08:53:44
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String moduleName() default "";
	String submoduleName() default "";
	String[] params() default {};
	
}
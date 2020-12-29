package com.dzb.console.syslog;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dzb.console.service.BaseOperationlogService;

/**
 * @Description 日志记录类
 * @author 38840472@qq.com
 * @date 2020-04-24 08:54:08
 */

@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private BaseOperationlogService baseOperationlogService;
	
	@Pointcut("@annotation(com.dzb.console.syslog.SysLog)")
	public void logPointCut() {
	}
	
	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		
		String moduleName = "";
		String submoduleName = "";
		String className = "";
		String methodName = "";
		String resultParam = "";
		String[] params = null;
		List<String> resultParams = new ArrayList<String>();
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog sysLog  = method.getAnnotation(SysLog.class);
		
		if(null != sysLog) {
			moduleName = sysLog.moduleName();
			submoduleName = sysLog.submoduleName();
			params = sysLog.params();
		}
		className = joinPoint.getTarget().getClass().getName();
		methodName = signature.getName();
		
		Object[] args = joinPoint.getArgs();
		
		if(null != params && params.length > 0) {
			
			for(int i=0; i<params.length;i++) {
				if(!"".equals(params[i])) {
					resultParams.add(params[i]+"="+JSON.toJSONString(args[i]));
				}
			}
		}
		resultParam = StringUtils.join(resultParams,",");
		baseOperationlogService.insert(moduleName, submoduleName, className, methodName, resultParam);
	}

}
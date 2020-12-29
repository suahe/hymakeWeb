package com.dzb.console.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

/**
 * @Description 认证链路拦截器
 * @author 38840472@qq.com
 * @date 2020-04-24 08:49:07
 */
@Service
public class WebFilterAbstractSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
	@Autowired
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
	
	@Autowired
    public void setWebAccessDecisionManager(WebAccessDecisionManager webAccessDecisionManager) {
        super.setAccessDecisionManager(webAccessDecisionManager);
    }
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException {

        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }
	
	@Override
    public void destroy() {

    }

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.filterInvocationSecurityMetadataSource;
	}

}

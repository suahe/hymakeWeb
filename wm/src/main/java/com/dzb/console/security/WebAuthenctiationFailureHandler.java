package com.dzb.console.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.dzb.console.service.BaseUserlogService;

/**
 * @Description 登录失败助手类，用于记录登录失败日志及跳转至失败页面
 * @author 38840472@qq.com
 * @date 2019-01-11 22:01:29
 */
@Component("WebAuthenctiationFailureHandler")
public class WebAuthenctiationFailureHandler implements AuthenticationFailureHandler {
	
	private final static String DEFAULT_TARGET_URL = "/login";
	private final static int LOG_TYPE = -1;
	private final static String USER_ID = null;
	@Autowired
	private BaseUserlogService baseUserlogService;
	@Autowired
	private IpAttemptService ipAttemptService;
	
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String sessionid = request.getSession().getId();
		String cip =  ClientInfoUtil.getRemoteAddress(request);
		
		String username = request.getParameter("username");
		String errorMessage = exception.getMessage();
		baseUserlogService.insert(USER_ID, cip, LOG_TYPE, username+"："+errorMessage, sessionid);
		// 登录失败记录一次异常，权重值加1
		ipAttemptService.loginFailed(cip);
		try {
	        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
			this.redirectStrategy.sendRedirect(request, response, DEFAULT_TARGET_URL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.dzb.console.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.dzb.console.service.BaseUserlogService;

/**
 * @Description 用户登出助手类，用于记录登出日志和跳转至登录页面
 * @author 38840472@qq.com
 * @date 2019-01-11 22:02:11
 */
@Component("WebAuthenctiationLogoutSuccessHandler")
public class WebAuthenctiationLogoutSuccessHandler implements LogoutSuccessHandler {
	
	private final static String DEFAULT_TARGET_URL = "/login";
	private final static int LOG_TYPE = 0;
	@Autowired
	private BaseUserlogService baseUserlogService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		if(null != authentication){
			String sessionid = ClientInfoUtil.getSessionId(authentication);
			UserInfoEntity userInfoEntity = (UserInfoEntity)authentication.getPrincipal();
			String cip = userInfoEntity.getCip();
			baseUserlogService.insert(userInfoEntity.getUserId(), cip, LOG_TYPE,null, sessionid);
		}
		
		try {
			this.redirectStrategy.sendRedirect(request, response, DEFAULT_TARGET_URL);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

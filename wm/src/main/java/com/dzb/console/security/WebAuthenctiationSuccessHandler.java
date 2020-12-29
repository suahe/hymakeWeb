package com.dzb.console.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dzb.console.service.BaseUserlogService;

/**
 * @Description 用户登录成功助手类，用于记录记录登录日志和跳转至主页
 * @author 38840472@qq.com
 * @date 2019-01-11 22:06:30
 */
@Component("WebAuthenctiationSuccessHandler")
public class WebAuthenctiationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final static String DEFAULT_TARGET_URL = "/main/load";
	private final static int LOG_TYPE = 1;
	@Autowired
	private BaseUserlogService baseUserlogService;
	@Autowired
	private IpAttemptService ipAttemptService;
	
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
//		String remoteAddress = ClientInfoUtil.getRemoteAddress(authentication);
		String sessionid = ClientInfoUtil.getSessionId(authentication);
		UserInfoEntity userInfoEntity = (UserInfoEntity)authentication.getPrincipal();
		String cip = userInfoEntity.getCip();
		long userlogId = baseUserlogService.insert(userInfoEntity.getUserId(), cip, LOG_TYPE, null, sessionid);
		// 登录成功后，撤销该IP的异常记录
		ipAttemptService.loginSucceeded(cip);
		
		userInfoEntity.setUserlogId(String.valueOf(userlogId));
		try {
			this.redirectStrategy.sendRedirect(request, response, DEFAULT_TARGET_URL);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

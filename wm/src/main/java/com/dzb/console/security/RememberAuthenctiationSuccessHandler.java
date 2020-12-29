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
 * @Description 记住我自动登录成功后助手类
 * @author 38840472@qq.com
 * @date 2020-06-25 20:40:00
 */
@Component("RememberAuthenctiationSuccessHandler")
public class RememberAuthenctiationSuccessHandler  implements AuthenticationSuccessHandler {
	
	private final static String DEFAULT_TARGET_URL = "/main/load";
	private final static int LOG_TYPE = 2;
	@Autowired
	private BaseUserlogService baseUserlogService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String remoteAddress = ClientInfoUtil.getRemoteAddress(authentication);
		String sessionid = ClientInfoUtil.getSessionId(request);
		UserInfoEntity userInfoEntity = (UserInfoEntity)authentication.getPrincipal();
		long userlogId = baseUserlogService.insert(userInfoEntity.getUserId(), remoteAddress, LOG_TYPE, null, sessionid);
		userInfoEntity.setUserlogId(String.valueOf(userlogId));
		try {
			this.redirectStrategy.sendRedirect(request, response, DEFAULT_TARGET_URL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

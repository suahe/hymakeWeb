package com.dzb.console.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @Description 认证附加信息拦截器
 * @author 38840472@qq.com
 * @date 2020-04-24 08:48:36
 */
@Component
public class WebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

	@Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new WebWebAuthenticationDetails(context);
    }
}

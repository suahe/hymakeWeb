package com.dzb.console.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dzb.console.entity.ImageCodeEntity;
import com.google.code.kaptcha.Constants;

/**
 * @Description 验证码拦截器
 * @author 38840472@qq.com
 * @date 2020-04-16 10:55:03
 */
@Service
public class ValidateCodeFilter extends OncePerRequestFilter {
    
	@Autowired
	private WebCodeAuthenctiationFailureHandler webCodeAuthenctiationFailureHandler;
	@Autowired
	private IpAttemptService ipAttemptService;
 
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
 
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
    	String cip = ClientInfoUtil.getRemoteAddress(request);
    	
		if (StringUtils.equals("/login2", request.getRequestURI()) && StringUtils.endsWithIgnoreCase(request.getMethod(), "post")) {
			boolean b = ipAttemptService.isBlocked(cip);
			if(b) {
	            try {
	                validate(new ServletWebRequest(request));
	            } catch (ValidateCodeException exception) {
	            	webCodeAuthenctiationFailureHandler.onAuthenticationFailure(request, response, exception);
	                return;
	            }
			}
        }
        
        filterChain.doFilter(request,response);
    }
 
    /**
     * @Description 验证码验证
     * @param request
     * @throws ServletRequestBindingException
     * @author 38840472@qq.com
     * @date 2020-04-21 14:09:31
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
    	
        ImageCodeEntity codeInSession = (ImageCodeEntity) sessionStrategy.getAttribute(request, Constants.KAPTCHA_SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
 
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, Constants.KAPTCHA_SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, Constants.KAPTCHA_SESSION_KEY);
    }
 
}
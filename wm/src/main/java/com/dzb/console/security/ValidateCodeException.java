package com.dzb.console.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description 用于抛出验证码错误的异常，继承AuthenticationException可被SpringSecurity捕获到
 * @author 38840472@qq.com
 * @date 2020-04-21 14:08:53
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = -924983369000254463L;

	public ValidateCodeException(String msg) {
        super(msg);
    }
}

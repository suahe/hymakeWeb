package com.dzb.console.security;

import org.springframework.security.authentication.AccountStatusException;

/**
 * @Description 自定义返回登录界面的异常类型
 * @author 38840472@qq.com
 * @date 2020-07-10 11:44:50
 */
public class LoginException extends AccountStatusException {

	private static final long serialVersionUID = -5171640438211172051L;
	
	public LoginException(String msg) {
		super(msg);
	}

}

package com.dzb.console.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 基础页面服务类
 * @author 38840472@qq.com
 * @date 2020-04-24 08:52:16
 */

@Service
public class BaseHomeService {

	@Autowired
	private BaseUserService baseUserService;
	
	/**
	 * @Description 修改密码
	 * @param originalPassword
	 * @param latestPassword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-24 08:52:51
	 */
	public boolean changePassword(String originalPassword,String latestPassword) {
		
		return baseUserService.changePassword(originalPassword, latestPassword);
	}
}

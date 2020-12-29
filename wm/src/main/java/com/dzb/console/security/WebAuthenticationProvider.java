package com.dzb.console.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.service.BasePermissionService;
import com.dzb.console.service.BaseUserService;

/**
 * @Description 自定义验证
 * @author 38840472@qq.com
 * @date 2019-01-11 20:01:43
 */

@Component
public class WebAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private BaseUserService baseUserService;
	@Autowired
	private BasePermissionService basePermissionService;
	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private IpAttemptService ipAttemptService;
	
	private final static String ERROR_PASSWORD = "-1";
	private final static String ERROR_USERNAME = "0";
	private final static String USER_BLOCK = "-2";
	private final static String IP_BLOCK = "-3";

	@Override
    public Authentication authenticate(Authentication authentication) {
		
		String cip = "-";
     	UserInfoEntity userDetails = null;
        /* 获取前台提交的账户信息*/
        String username = authentication.getName();
        /* 获取前台提交的密码信息（明文）*/
        String password = authentication.getCredentials().toString();
        /* 获取登录的其他信息*/
        WebWebAuthenticationDetails details = (WebWebAuthenticationDetails) authentication.getDetails();
        /* 获取前台提交的IP信息*/
//        cip = details.getCip();
        cip = details.getRemoteAddress();
        
    	//可直接抛出错误（AuthenticationException的子类，在登录验证不通过重定向至登录页时可通过session.SPRING_SECURITY_LAST_EXCEPTION.message获取具体错误提示信息） 
        //判断当前IP是否已经被锁定
        if(!ipAttemptService.isBlocked(cip)) {
        	/* 通过账户信息获取数据库中用户信息 */
    		BaseUserEntity baseUserEntity = baseUserService.getPasswordByUsername(username);
    		/* 判断是否能查询到用户*/
    		if(null != baseUserEntity) {
    			if(!loginAttemptService.isBlocked(baseUserEntity.getUserId())) {
    				Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
    				SimpleGrantedAuthority auth = new SimpleGrantedAuthority("BASE");
    				auths.add(auth);
    				List<String> roleIds = basePermissionService.getGrantedAuthority(baseUserEntity.getUserId());
    				for(String roleId:roleIds) {
    					auth = new SimpleGrantedAuthority(roleId);
    					auths.add(auth);
    				}
    				String passwordInDataBase = baseUserEntity.getPassword();
    				boolean pass = authentication(passwordInDataBase,password);
    				if(pass) {
    					userDetails = new UserInfoEntity(username, password, true, true, true, true, auths);
    					userDetails.setRoleIds(roleIds);
    					userDetails.setName(baseUserEntity.getName());
    					userDetails.setUserId(baseUserEntity.getUserId());
    					userDetails.setCip(cip);
    					/* 验证成功返回UsernamePasswordAuthenticationToken对象*/
    					return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
    				}else {
    					/* 验证失败返回错误信息*/
    					loginAttemptService.loginFailed(baseUserEntity.getUserId());
    					throw new LoginException(ERROR_PASSWORD);//密码错误
    				}
    			}else {
    				throw new LoginException(USER_BLOCK);//用户已经被锁定
    			}
    		}else {
    			/* 验证失败返回错误信息*/
    			throw new LoginException(ERROR_USERNAME);//用户名不存在
    		}
        }else {
        	throw new LoginException(IP_BLOCK);//IP被锁定
        }
    }
	
	/**
	 * @Description 验证数据库中的密码是否与前台获取的密码一致
	 * @param passwordInDataBase 数据库中的密码（密文）
	 * @param password 前台提交的密码（明文）
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-11 20:07:45
	 */
	private boolean authentication(String passwordInDataBase,String password) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		return bCryptPasswordEncoder.matches(password,passwordInDataBase);
	}

    @Override
    public boolean supports(Class<?> authentication) {
        
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

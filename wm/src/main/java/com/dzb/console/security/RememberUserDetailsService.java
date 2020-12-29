package com.dzb.console.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.service.BasePermissionService;
import com.dzb.console.service.BaseUserService;

/**
 * @Description 自动登录
 * @author 38840472@qq.com
 * @date 2020-04-29 22:43:28
 */

@Service("RememberUserDetailsService")
public class RememberUserDetailsService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(RememberUserDetailsService.class);
	@Autowired
	private BaseUserService baseUserService;
	@Autowired
	private BasePermissionService basePermissionService;
	
	private UserInfoEntity userDetails = null;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BaseUserEntity baseUserEntity = baseUserService.getPasswordByUsername(username);
        if (baseUserEntity != null){
        	
        	Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority("BASE");
			auths.add(auth);
			List<String> roleIds = basePermissionService.getGrantedAuthority(baseUserEntity.getUserId());
			for(String roleId:roleIds) {
				auth = new SimpleGrantedAuthority(roleId);
				auths.add(auth);
			}
        	userDetails = new UserInfoEntity(username, baseUserEntity.getPassword(), true, true, true, true, auths);
        	userDetails.setRoleIds(roleIds);
        	userDetails.setName(baseUserEntity.getName());
			userDetails.setUserId(baseUserEntity.getUserId());
			log.debug("用户账号："+username+"登录成功");
            return userDetails;
        }else{
        	log.debug("用户账号："+username+"登录失败");
            return null;
        }
    }
}
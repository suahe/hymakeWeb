package com.dzb.console.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzb.console.entity.BaseMenuEntity;
import com.dzb.console.entity.BasePermissionEntity;

/**
 * @Description 权限资源服务类
 * @author 38840472@qq.com
 * @date 2020-04-24 08:53:14
 */

@Service("BasePermissionService")
public class BasePermissionService {
	
	@Autowired
	private BaseMenuService baseMenuService;
	@Autowired
	private BaseMenuRoleMappingService baseMenuRoleMappingService;
	@Autowired
	private BaseUserRoleMappingService baseUserRoleMappingService;
	
	public List<BasePermissionEntity> getPermissions(){
		
		List<BasePermissionEntity> basePermissios = new ArrayList<BasePermissionEntity>();
		List<BaseMenuEntity> baseMenus = baseMenuService.getMenuList();
		if(null != baseMenus) {
			for(BaseMenuEntity baseMenuEntity:baseMenus) {
				BasePermissionEntity basePermissionEntity = new BasePermissionEntity();
				
				List<String> roleIds = baseMenuRoleMappingService.getRoleIdByMenuId(baseMenuEntity.getMenuId());
				basePermissionEntity.setMenuName(baseMenuEntity.getMenuName());
				basePermissionEntity.setUrl(baseMenuEntity.getUrl());
				basePermissionEntity.setRoleIds(roleIds);
				basePermissios.add(basePermissionEntity);
			}
		}
		return basePermissios;
	}
	
	public List<String> getGrantedAuthority(String userId){
		
		return baseUserRoleMappingService.getByUserId(userId);
	}
	
	

}

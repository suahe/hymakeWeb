package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseMenuEntity;
import com.dzb.console.entity.BaseMenuRoleMappingEntity;
import com.dzb.console.entity.BaseRoleEntity;

/**
 * @Description t_base_menu_role_mapping 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:03
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseMenuRoleMappingMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseMenuRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	List<BaseMenuRoleMappingEntity> selectPagination(BaseMenuRoleMappingEntity baseMenuRoleMappingEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param menuRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	BaseMenuRoleMappingEntity selectByPkey(String menuRoleMappingId);
	
	/**
	 * @Description 新增信息
	 * @param baseMenuRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	int insert(BaseMenuRoleMappingEntity baseMenuRoleMappingEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseMenuRoleMappingEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	int update(BaseMenuRoleMappingEntity baseMenuRoleMappingEntity);
	
	/**
	 * @Description 删除信息
	 * @param baseMenuRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-25 11:16:45
	 */
	int delete(BaseMenuRoleMappingEntity baseMenuRoleMappingEntity);
	
	/**
	 * @Description 通过菜单编号获取角色编号
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:03
	 */
	List<String> selectRoleIdByMenuId(String menuId);
	
	List<BaseRoleEntity> selectRoleIdsByMenuId(String menuId);
	
	List<BaseMenuEntity> selectMenuByRoleId(String roleId);
	
	/**
	 * @Description 通过menuId删除映射的角色记录（用于删除菜单时级联同步删除映射表记录）
	 * @param menuId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-15 17:24:59
	 */
	int deleteByMenuId(String menuId);
	

}
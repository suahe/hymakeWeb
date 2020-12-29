package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.entity.BaseUserRoleMappingEntity;

/**
 * @Description t_base_user_role_mapping 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:21
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseUserRoleMappingMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseUserRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	List<BaseUserRoleMappingEntity> selectPagination(BaseUserRoleMappingEntity baseUserRoleMappingEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param userRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	BaseUserRoleMappingEntity selectByPkey(String userRoleMappingId);
	
	/**
	 * @Description 新增信息
	 * @param baseUserRoleMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	int insert(BaseUserRoleMappingEntity baseUserRoleMappingEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseUserRoleMappingEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	int update(BaseUserRoleMappingEntity baseUserRoleMappingEntity);
	
	/**
	 * @Description 删除信息
	 * @param userRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	int delete(String userRoleMappingId);
	
	/**
	 * @Description 通过用户编号查找用户角色
	 * @param userRoleMappingId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	List<String> selectByUserId(String userId);
	
	List<BaseUserEntity> selectUserByRoleIdPagination(String roleId);
	
	List<BaseUserEntity> selectUnUserByRoleIdPagination(BaseUserRoleMappingEntity baseUserRoleMappingEntity);
	
	int deleteByUserIdAndRoleId(BaseUserRoleMappingEntity baseUserRoleMappingEntity);

	List<BaseUserEntity> searchUserByRoleIdPagination(BaseUserRoleMappingEntity baseUserRoleMappingEntity);

}
package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseRoleEntity;

/**
 * @Description t_base_role 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2018-12-19 16:05:15
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseRoleMapper {
	
	/**
	 * @Description 通过主键查询信息
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:15
	 */
	BaseRoleEntity selectByPkey(String roleId);
	
	/**
	 * @Description 新增信息
	 * @param baseRoleEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:15
	 */
	int insert(BaseRoleEntity baseRoleEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseRoleEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2018-12-19 16:05:15
	 */
	int update(BaseRoleEntity baseRoleEntity);
	
	/**
	 * @Description 删除信息
	 * @param roleId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:15
	 */
	int delete(String roleId);
	
	List<BaseRoleEntity> selectAllRoleList();
	
	int updateIsLeaf(BaseRoleEntity baseRoleEntity);
	
	Integer selectMaxSequ();
	
	int countByRolePid(String rolePid);

}
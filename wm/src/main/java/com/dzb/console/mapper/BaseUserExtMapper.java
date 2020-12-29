package com.dzb.console.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseUserExtEntity;

/**
 * @Description t_base_user_ext 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-04-23 16:20:03
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseUserExtMapper {
	
	/**
	 * @Description 新增信息
	 * @param baseUserExtEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	int insert(BaseUserExtEntity baseUserExtEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseUserExtEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	int update(BaseUserExtEntity baseUserExtEntity);
	
	/**
	 * @Description 删除信息
	 * @param userExtId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	int delete(String userExtId);
	
	/**
	 * @Description 通过用户编号查询用户扩展信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:24:37
	 */
	BaseUserExtEntity selectByUserId(String userId);

}
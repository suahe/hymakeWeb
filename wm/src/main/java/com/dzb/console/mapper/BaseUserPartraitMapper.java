package com.dzb.console.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseUserPartraitEntity;

/**
 * @Description t_base_user_partrait 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-04-23 21:13:27
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseUserPartraitMapper {
	
	/**
	 * @Description 新增信息
	 * @param baseUserPartraitEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 21:13:27
	 */
	int insert(BaseUserPartraitEntity baseUserPartraitEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseUserPartraitEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2020-04-23 21:13:27
	 */
	int update(BaseUserPartraitEntity baseUserPartraitEntity);
	
	/**
	 * @Description 通过userId查找记录
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-24 10:53:58
	 */
	BaseUserPartraitEntity selectUserPortraitIdByUserId(String userId);

}
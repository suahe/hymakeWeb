package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseUserlogEntity;

/**
 * @Description t_base_userlog 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2019-01-03 10:48:20
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseUserlogMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseUserlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-03 10:48:20
	 */
	List<BaseUserlogEntity> selectPagination(BaseUserlogEntity baseUserlogEntity);
	
	/**
	 * @Description 新增信息
	 * @param baseUserlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-03 10:48:20
	 */
	int insert(BaseUserlogEntity baseUserlogEntity);

}
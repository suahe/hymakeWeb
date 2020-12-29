package com.hymake.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hymake.web.entity.JoinEntity;

/**
 * @Description t_join 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-06-26 00:31:49
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface JoinMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param joinEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	List<JoinEntity> selectPagination(JoinEntity joinEntity);
	
}
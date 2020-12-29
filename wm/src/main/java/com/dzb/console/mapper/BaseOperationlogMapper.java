package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseOperationlogEntity;

/**
 * @Description t_base_operationlog 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2019-01-10 14:47:58
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseOperationlogMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseOperationlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	List<BaseOperationlogEntity> selectPagination(BaseOperationlogEntity baseOperationlogEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param operationlogId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	BaseOperationlogEntity selectByPkey(String operationlogId);
	
	/**
	 * @Description 新增信息
	 * @param baseOperationlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	int insert(BaseOperationlogEntity baseOperationlogEntity);

}
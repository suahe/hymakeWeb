package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseTypeEntity;

/**
 * @Description t_base_type 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-05-12 17:51:09
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseTypeMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseTypeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	List<BaseTypeEntity> selectPagination(BaseTypeEntity baseTypeEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	BaseTypeEntity selectByPkey(String baseTypeId);
	
	/**
	 * @Description 新增信息
	 * @param baseTypeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	int insert(BaseTypeEntity baseTypeEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseTypeEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	int update(BaseTypeEntity baseTypeEntity);
	
	/**
	 * @Description 删除信息
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	int delete(String baseTypeId);
	
	/**
	 * @Description 通过代码查询数据字典编号
	 * @param code
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 09:11:49
	 */
	String selectByCode(String code);

}
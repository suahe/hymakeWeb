package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseConstantEntity;

/**
 * @Description t_base_constant 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-06-26 16:45:46
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseConstantMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseConstantEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	List<BaseConstantEntity> selectPagination(BaseConstantEntity baseConstantEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param constantId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	BaseConstantEntity selectByPkey(long constantId);
	
	/**
	 * @Description 新增信息
	 * @param baseConstantEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	int insert(BaseConstantEntity baseConstantEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseConstantEntity
	 * @return
	 * @author  daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	int update(BaseConstantEntity baseConstantEntity);
	
	/**
	 * @Description 通过keywor更新value的值
	 * @param baseConstantEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-28 14:25:08
	 */
	int updateKeywordAndValue(BaseConstantEntity baseConstantEntity);
	
	/**
	 * @Description 删除信息
	 * @param constantId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 16:45:46
	 */
	int delete(long constantId);
	
	/**
	 * @Description 通过keyword查询是否已经存在
	 * @param keyword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 18:04:18
	 */
	int countByKeyword(String keyword);
	
	/**
	 * @Description 通过keyword查询value值
	 * @param keyword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 22:58:16
	 */
	String selectValueByKeyword(String keyword);

}
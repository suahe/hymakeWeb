package com.hymake.web.mapper;

import org.apache.ibatis.annotations.Mapper;

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
	 * @Description 通过keyword查询value值
	 * @param keyword
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 22:58:16
	 */
	String selectValueByKeyword(String keyword);

}
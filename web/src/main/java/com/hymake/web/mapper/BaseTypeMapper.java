package com.hymake.web.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Description t_base_type 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-05-12 17:51:09
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseTypeMapper {
	
	/**
	 * @Description 通过代码查询数据字典编号
	 * @param code
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-05-13 09:11:49
	 */
	String selectByCode(String code);

}
package com.hymake.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hymake.web.entity.PosterItemEntity;

/**
 * @Description t_poster_item 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-06-15 09:19:15
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface PosterItemMapper {
			
	/**
	 * @Description 查询有效的数据
	 * @param type
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-17 15:17:19
	 */
	List<PosterItemEntity> selectByType(String type);
	

}
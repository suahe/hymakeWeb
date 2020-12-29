package com.dzb.wm.mapper;

import java.util.List;
import com.dzb.wm.entity.ChronologyEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Description t_chronology 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-07-03 13:48:53
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface ChronologyMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param chronologyEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	List<ChronologyEntity> selectPagination(ChronologyEntity chronologyEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param chronologyId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	ChronologyEntity selectByPkey(long chronologyId);
	
	/**
	 * @Description 新增信息
	 * @param chronologyEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	int insert(ChronologyEntity chronologyEntity);
	
	/**
	 * @Description 编辑信息
	 * @param chronologyEntity
	 * @return
	 * @author  daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	int update(ChronologyEntity chronologyEntity);
	
	/**
	 * @Description 删除信息
	 * @param chronologyId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	int delete(long chronologyId);

}
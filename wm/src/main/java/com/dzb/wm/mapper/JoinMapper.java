package com.dzb.wm.mapper;

import java.util.List;
import com.dzb.wm.entity.JoinEntity;

import org.apache.ibatis.annotations.Mapper;

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
	
	/**
	 * @Description 通过主键查询信息
	 * @param joinId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	JoinEntity selectByPkey(long joinId);
	
	/**
	 * @Description 新增信息
	 * @param joinEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	int insert(JoinEntity joinEntity);
	
	/**
	 * @Description 编辑信息
	 * @param joinEntity
	 * @return
	 * @author  daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	int update(JoinEntity joinEntity);
	
	/**
	 * @Description 删除信息
	 * @param joinId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	int delete(long joinId);
	
	/**
	 * @Description 更新发布状态
	 * @param joinEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 13:04:52
	 */
	int updatePushTimeByJoinId(JoinEntity joinEntity);

}
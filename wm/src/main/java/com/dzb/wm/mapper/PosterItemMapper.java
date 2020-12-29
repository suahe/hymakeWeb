package com.dzb.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.wm.entity.PosterItemEntity;

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
	 * @Description 查询信息列表
	 * @param posterItemEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	List<PosterItemEntity> selectPagination(PosterItemEntity posterItemEntity);
	
	
	/**
	 * @Description 查询有效的数据
	 * @param posterItemEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-17 15:17:19
	 */
	List<PosterItemEntity> selectByValid(PosterItemEntity posterItemEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param posterImageId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	PosterItemEntity selectByPkey(long posterItemId);
	
	/**
	 * @Description 新增信息
	 * @param posterItemEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	int insert(PosterItemEntity posterItemEntity);
	
	/**
	 * @Description 编辑信息
	 * @param posterItemEntity
	 * @return
	 * @author  daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	int update(PosterItemEntity posterItemEntity);
	
	/**
	 * @Description 编辑信息(不更新媒体文件信息)
	 * @param posterItemId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-22 14:39:50
	 */
	int updateWithoutMedia(PosterItemEntity posterItemEntity);
	
	/**
	 * @Description 删除信息
	 * @param posterImageId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-15 09:19:15
	 */
	int delete(long posterItemId);
	
	
	Integer selectMaxSequ();
	
	/**
	 * @Description 通过数据字典编号和排序序列找到比当前小的值
	 * @param posterItemEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:04:51
	 */
	PosterItemEntity selectDownBySequAndPosterId(PosterItemEntity posterItemEntity);
	
	/**
	 * @Description 通过数据字典编号和排序序列找到比当前大的值
	 * @param posterItemEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:04:51
	 */
	PosterItemEntity selectUpBySequAndPosterId(PosterItemEntity posterItemEntity);
	
	/**
	 * @Description 更新排序序号
	 * @param posterItemEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:21:50
	 */
	int updateSequ(PosterItemEntity posterItemEntity);
	
	int updatePushTimeByPosterItemId(PosterItemEntity posterItemEntity);
	
	int countByType(PosterItemEntity posterItemEntity);

}
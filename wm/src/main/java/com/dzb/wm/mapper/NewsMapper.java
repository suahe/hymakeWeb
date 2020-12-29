package com.dzb.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.wm.entity.NewsEntity;

/**
 * @Description t_news 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-06-03 13:39:45
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface NewsMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param newsEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	List<NewsEntity> selectPagination(NewsEntity newsEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param newsId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	NewsEntity selectByPkey(long newsId);
	
	/**
	 * @Description 新增信息
	 * @param newsEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	int insert(NewsEntity newsEntity);
	
	/**
	 * @Description 编辑信息
	 * @param newsEntity
	 * @return
	 * @author  daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	int update(NewsEntity newsEntity);
	
	/**
	 * @Description 编辑信息不更新图片
	 * @param newsEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-24 15:57:26
	 */
	int updateWithoutMedia(NewsEntity newsEntity);
	
	/**
	 * @Description 删除信息
	 * @param newsId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	int delete(long newsId);
	
	/**
	 * @Description 更新发布时间
	 * @param newsEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:04:58
	 */
	int updatePushTimeByNewsId(NewsEntity newsEntity);
	
	
	/**
	 * @Description 更新头条状态
	 * @param newsEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-04 16:23:55
	 */
	int updateIsHeadlineByNewsId(NewsEntity newsEntity);
	
	/**
	 * @Description 查看所有已经推送的新闻资资讯
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:42:08
	 */
	List<NewsEntity> selectAllPushList();
	
	/**
	 * @Description 查询头条的数量
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-23 20:43:25
	 */
	int countByIsHeadline();

}
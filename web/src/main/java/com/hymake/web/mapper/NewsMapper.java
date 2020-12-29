package com.hymake.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hymake.web.entity.NewsEntity;

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
	 * @param newType
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	List<NewsEntity> selectPagination(@Param("newType") String newType);
	
	/**
	 * @Description 查询头条新闻信息
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	NewsEntity selectByIsHeadline();
	
	/**
	 * @Description 通过主键(newsId)查询信息
	 * @param newsId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	NewsEntity selectByNewsId(long newsId);
	
	/**
	 * @Description 查询首页置顶的3条新闻<br>规则：头条新闻标识为0 新闻类型为1（公司新闻），推送时间不为空
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-05 20:42:08
	 */
	List<NewsEntity> selectByTopNews();

}
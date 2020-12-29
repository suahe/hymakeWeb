package com.hymake.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hymake.web.entity.NewsEntity;
import com.hymake.web.mapper.NewsMapper;

/**
 * @Description t_news 表Service类
 * @author daizb@hymake.com
 * @date 2020-06-03 13:39:45
 * @version 1.0
 * @remark create by ca
 */

@Service("NewsService")
@Transactional
public class NewsService {

	private static final Logger log = LoggerFactory.getLogger(NewsService.class);
	
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private BaseTypeService baseTypeService;
	
	public PageInfo<NewsEntity> getPagination(Integer offset, Integer limit, String newType) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		PageInfo<NewsEntity> pi = new PageInfo<NewsEntity>(newsMapper.selectPagination(newType));
		List<NewsEntity> list = pi.getList();
		list = baseTypeService.translate(list, "FBZ", "author", "authorName");
		list = baseTypeService.translate(list, "XWLX", "newType", "newTypeName");
		pi.setList(list);
		return pi;
	}
	
	/**
	 * @Description 查询头条新闻信息
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-29 13:58:20
	 */
	public NewsEntity getHeadlineNew() {
		
		log.debug("查询头条新闻信息");
		NewsEntity newsEntity = newsMapper.selectByIsHeadline();
		newsEntity = baseTypeService.translate(newsEntity, "FBZ", "author", "authorName");
		newsEntity = baseTypeService.translate(newsEntity, "XWLX", "newType", "newTypeName");
		return newsEntity;
	}
	
	/**
	 * @Description 查询首页置顶的3条新闻
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-05 20:44:09
	 */
	public List<NewsEntity> getTopNews(){
		
		List<NewsEntity> list = newsMapper.selectByTopNews();
		list = baseTypeService.translate(list, "FBZ", "author", "authorName");
		list = baseTypeService.translate(list, "XWLX", "newType", "newTypeName");
		return list;
	}
	
	/**
	 * @Description 通过主键(newsId)查询信息
	 * @param newsId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-03 13:39:45
	 */
	public NewsEntity getNew(long newsId){
	
		log.debug("通过主键查询信息");
		NewsEntity newsEntity =  newsMapper.selectByNewsId(newsId);
		newsEntity = baseTypeService.translate(newsEntity, "FBZ", "author", "authorName");
		newsEntity = baseTypeService.translate(newsEntity, "XWLX", "newType", "newTypeName");
		return newsEntity;
	}

	
}
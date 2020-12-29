package com.hymake.mobileWeb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hymake.mobileWeb.entity.PosterItemEntity;
import com.hymake.mobileWeb.mapper.PosterItemMapper;

/**
 * @Description t_poster_image 表Service类
 * @author daizb@hymake.com
 * @date 2020-06-15 09:19:15
 * @version 1.0
 * @remark create by ca
 */

@Service("PosterItemService")
@Transactional
public class PosterItemService {

	private static final Logger log = LoggerFactory.getLogger(PosterItemService.class);
	@Autowired
	private PosterItemMapper posterItemMapper;
	
	/**
	 * @Description 查询轮播列表信息
	 * @param type
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-17 15:18:41
	 */
	public List<PosterItemEntity> getByType(String type){
		
		log.debug("查询轮播列表信息");
		return posterItemMapper.selectByType(type);
	}
	
}
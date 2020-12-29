package com.dzb.wm.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.wm.entity.ChronologyEntity;
import com.dzb.wm.mapper.ChronologyMapper;

import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_chronology 表Service类
 * @author daizb@hymake.com
 * @date 2020-07-03 13:48:53
 * @version 1.0
 * @remark create by ca
 */

@Service("ChronologyService")
@Transactional
public class ChronologyService {

	private static final Logger log = LoggerFactory.getLogger(ChronologyService.class);
	
	@Autowired
	private ChronologyMapper chronologyMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param chronologyEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	public PageInfo<ChronologyEntity> getPagination(Integer offset, Integer limit, ChronologyEntity chronologyEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		return new PageInfo<ChronologyEntity>(chronologyMapper.selectPagination(chronologyEntity));
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param chronologyId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	public ChronologyEntity getByPkey(long chronologyId){
	
		log.debug("通过主键查询信息");
		return chronologyMapper.selectByPkey(chronologyId);
	}
	
	/**
	 * @Description 新增信息
	 * @param chronologyEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	public int insert(ChronologyEntity chronologyEntity) {

		log.debug("新增信息");
		chronologyEntity.setChronologyId(IDUtil.getSnowflakeId());
		chronologyEntity.setUpdateUserid(UserInfoUtil.getUserId());
		chronologyEntity.setUpdateTime(new Date());
		return chronologyMapper.insert(chronologyEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param chronologyEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	public int update(ChronologyEntity chronologyEntity){
	
		log.debug("编辑信息");
		chronologyEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		chronologyEntity.setUpdateTime(new Date());
		return chronologyMapper.update(chronologyEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param chronologyId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-07-03 13:48:53
	 */
	public int delete(long chronologyId){
	
		log.debug("删除信息");
		return chronologyMapper.delete(chronologyId);
	}
	
}
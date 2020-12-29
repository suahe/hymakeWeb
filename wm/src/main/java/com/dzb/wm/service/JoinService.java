package com.dzb.wm.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.service.BaseTypeService;
import com.dzb.console.util.IDUtil;
import com.dzb.wm.entity.JoinEntity;
import com.dzb.wm.mapper.JoinMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description t_join 表Service类
 * @author daizb@hymake.com
 * @date 2020-06-26 00:31:49
 * @version 1.0
 * @remark create by ca
 */

@Service("JoinService")
@Transactional
public class JoinService {

	private static final Logger log = LoggerFactory.getLogger(JoinService.class);
	
	@Autowired
	private JoinMapper joinMapper;
	@Autowired
	private BaseTypeService baseTypeService;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param joinEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	public PageInfo<JoinEntity> getPagination(Integer offset, Integer limit, JoinEntity joinEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		PageInfo<JoinEntity> pi = new PageInfo<JoinEntity>(joinMapper.selectPagination(joinEntity));
		List<JoinEntity> list = pi.getList();
		list = baseTypeService.translate(list, "ZPLX", "joinType", "joinTypeName");
		list = baseTypeService.translate(list, "GZDD", "workplace", "workplaceName");
		pi.setList(list);
		return pi;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param joinId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	public JoinEntity getByPkey(long joinId){
	
		log.debug("通过主键查询信息");
		JoinEntity joinEntity = joinMapper.selectByPkey(joinId);
		joinEntity = baseTypeService.translate(joinEntity, "ZPLX", "joinType", "joinTypeName");
		joinEntity = baseTypeService.translate(joinEntity, "GZDD", "workplace", "workplaceName");
		return joinEntity;
	}
	
	/**
	 * @Description 新增信息
	 * @param joinEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	public int insert(JoinEntity joinEntity) {

		log.debug("新增信息");
		joinEntity.setJoinId(IDUtil.getSnowflakeId());
		joinEntity.setUpdateUserid(UserInfoUtil.getUserId());
		joinEntity.setUpdateTime(new Date());
		return joinMapper.insert(joinEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param joinEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	public int update(JoinEntity joinEntity){
	
		log.debug("编辑信息");
		joinEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		joinEntity.setUpdateTime(new Date());
		return joinMapper.update(joinEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param joinId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-26 00:31:49
	 */
	public int delete(long joinId){
	
		log.debug("删除信息");
		return joinMapper.delete(joinId);
	}
	
	/**
	 * @Description 更新发布状态
	 * @param joinId
	 * @param pushTime
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 13:06:24
	 */
	public int updatePushTimeByJoinId(long joinId, Date pushTime) {
		
		JoinEntity joinEntity = new JoinEntity();
		joinEntity.setJoinId(joinId);
		joinEntity.setPushTime(pushTime);
		return joinMapper.updatePushTimeByJoinId(joinEntity);
	}
	
}
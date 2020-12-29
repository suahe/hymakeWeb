package com.dzb.console.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseUserlogEntity;
import com.dzb.console.mapper.BaseUserlogMapper;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Description t_base_userlog 表Service类
 * @author 38840472@qq.com
 * @date 2019-01-03 10:48:20
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseUserlogService")
@Transactional
public class BaseUserlogService {
	
	@Autowired
	private BaseUserlogMapper baseUserlogMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseUserlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-03 10:48:20
	 */
	public PageInfo<BaseUserlogEntity> selectPagination(Integer offset, Integer limit, String searchText) {
		
		PageHelper.offsetPage(offset, limit);
		PageHelper.orderBy("LOG_TIME DESC");
		BaseUserlogEntity baseUserlogEntity = new BaseUserlogEntity();
		baseUserlogEntity.setName(searchText);
		baseUserlogEntity.setUsername(searchText);
		List<BaseUserlogEntity> list = baseUserlogMapper.selectPagination(baseUserlogEntity);
		PageInfo<BaseUserlogEntity> pi = new PageInfo<BaseUserlogEntity>(list);
		return pi;
	}
	
	/**
	 * @Description 新增信息
	 * @param baseUserlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-03 10:48:20
	 */
	public int insert(BaseUserlogEntity baseUserlogEntity) {
		
		return baseUserlogMapper.insert(baseUserlogEntity);
	}
	
	/**
	 * @Description 新增日志信息
	 * @param userId
	 * @param remoteAddress
	 * @param logType
	 * @param sessionid
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-03 11:00:15
	 */
	public long insert(String userId,String remoteAddress,Integer logType,String errorMessage,String sessionid) {
		
		long userlogId = IDUtil.getSnowflakeId();
		BaseUserlogEntity baseUserlogEntity = new BaseUserlogEntity();
		baseUserlogEntity.setUserlogId(userlogId);
		baseUserlogEntity.setLogTime(new Date());
		baseUserlogEntity.setUserId(userId);
		baseUserlogEntity.setRemoteAddress(remoteAddress);
		baseUserlogEntity.setLogType(logType);
		baseUserlogEntity.setSessionid(sessionid);
		baseUserlogEntity.setErrorMessage(errorMessage);
		int i = insert(baseUserlogEntity);
		return i > 0 ? userlogId : 0;
	}
	
}
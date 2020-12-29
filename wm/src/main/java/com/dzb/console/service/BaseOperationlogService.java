package com.dzb.console.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseOperationlogEntity;
import com.dzb.console.mapper.BaseOperationlogMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Description t_base_operationlog 表Service类
 * @author 38840472@qq.com
 * @date 2019-01-10 14:47:58
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseOperationlogService")
@Transactional
public class BaseOperationlogService {
	
	@Autowired
	private BaseOperationlogMapper baseOperationlogMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseOperationlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	public PageInfo<BaseOperationlogEntity> selectPagination(Integer offset, Integer limit, String searchText) {
		
		PageHelper.offsetPage(offset, limit);
		PageHelper.orderBy("LOG_TIME DESC");
		BaseOperationlogEntity baseOperationlogEntity = new BaseOperationlogEntity();
		baseOperationlogEntity.setModuleName(searchText);
		baseOperationlogEntity.setSubmoduleName(searchText);
		baseOperationlogEntity.setUsername(searchText);
		List<BaseOperationlogEntity> list = baseOperationlogMapper.selectPagination(baseOperationlogEntity);
		PageInfo<BaseOperationlogEntity> pi = new PageInfo<BaseOperationlogEntity>(list);
		return pi;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param operationlogId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	public BaseOperationlogEntity getByPkey(String operationlogId){
	
		return baseOperationlogMapper.selectByPkey(operationlogId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseOperationlogEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:47:58
	 */
	public int insert(BaseOperationlogEntity baseOperationlogEntity) {
		
		return baseOperationlogMapper.insert(baseOperationlogEntity);
	}
	
	/**
	 * @Description 新增操作日志信息
	 * @param moduleName 模块名称
	 * @param submoduleName 子模块名称
	 * @param className 类名
	 * @param methodName 方法名
	 * @param params 参数
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-10 14:56:29
	 */
	public int insert(String moduleName,String submoduleName,String className,String methodName,String params) {
		
		BaseOperationlogEntity baseOperationlogEntity = new BaseOperationlogEntity();
		baseOperationlogEntity.setOperationlogId(IDUtil.getSnowflakeId());
		baseOperationlogEntity.setUserId(UserInfoUtil.getUserId());
		baseOperationlogEntity.setUserlogId(UserInfoUtil.getUserlogId());
		baseOperationlogEntity.setLogTime(new Date());
		baseOperationlogEntity.setModuleName(moduleName);
		baseOperationlogEntity.setSubmoduleName(submoduleName);
		baseOperationlogEntity.setClassName(className);
		baseOperationlogEntity.setMethodName(methodName);
		baseOperationlogEntity.setParams(params);
		return insert(baseOperationlogEntity);
	}
	
}
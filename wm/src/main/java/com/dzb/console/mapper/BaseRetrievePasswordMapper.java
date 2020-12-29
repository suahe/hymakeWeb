package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseRetrievePasswordEntity;

/**
 * @Description t_base_retrieve_password 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2019-01-16 11:04:43
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseRetrievePasswordMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	List<BaseRetrievePasswordEntity> selectPagination(BaseRetrievePasswordEntity baseRetrievePasswordEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param retrievePasswordId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	BaseRetrievePasswordEntity selectByPkey(String retrievePasswordId);
	
	/**
	 * @Description 新增信息
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	int insert(BaseRetrievePasswordEntity baseRetrievePasswordEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	int update(BaseRetrievePasswordEntity baseRetrievePasswordEntity);
	
	/**
	 * @Description 删除信息
	 * @param retrievePasswordId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:04:43
	 */
	int delete(String retrievePasswordId);
	
	/**
	 * @Description 通过用户编号查找找回密码信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 14:09:30
	 */
	BaseRetrievePasswordEntity selectByUserId(String userId);
	
	/**
	 * @Description 验证验证码信息
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 15:18:18
	 */
	String verifySecurityCodeByUserId(BaseRetrievePasswordEntity baseRetrievePasswordEntity);
	
	/**
	 * @Description 更新验证码使用时间
	 * @param baseRetrievePasswordEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 16:18:53
	 */
	int updateSecurityCodeUseTimeByRetrievePasswordId(BaseRetrievePasswordEntity baseRetrievePasswordEntity);

}
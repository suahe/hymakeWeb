package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseUserEntity;

/**
 * @Description t_base_user 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2018-12-18 16:24:42
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseUserMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseUserEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	List<BaseUserEntity> selectPagination(BaseUserEntity baseUserEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	BaseUserEntity selectByPkey(String userId);
	
	/**
	 * @Description 新增信息
	 * @param baseUserEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	int insert(BaseUserEntity baseUserEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseUserEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	int update(BaseUserEntity baseUserEntity);
	
	/**
	 * @Description 删除信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	int delete(String userId);
	
	/**
	 * @Description 逻辑删除
	 * @param userId
	 * @return
	 */
	int updateIsDel(BaseUserEntity baseUserEntity);
	
	/**
	 * @Description 通过有效账户查找密码
	 * @param username
	 * @return
	 */
	BaseUserEntity selectPasswordByUsername(String username);
	
	/**
	 * @Description 通过用户编号查找用户密码
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-21 10:03:49
	 */
	String selectPasswordByUserId(String userId);
	
	/**
	 * @Description 修改用户密码
	 * @param baseUserEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-21 10:04:28
	 */
	int updatePassword(BaseUserEntity baseUserEntity);

	/**
	 * @Description 通过邮箱地址查找用户信息
	 * @param email
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:32:08
	 */
	BaseUserEntity selectByMail(String email);
}
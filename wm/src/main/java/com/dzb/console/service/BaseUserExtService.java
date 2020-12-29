package com.dzb.console.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseUserExtEntity;
import com.dzb.console.mapper.BaseUserExtMapper;
import com.dzb.console.security.LoginAttemptService;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;

/**
 * @Description t_base_user_ext 表Service类
 * @author 38840472@qq.com
 * @date 2020-04-23 16:20:03
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseUserExtService")
@Transactional
public class BaseUserExtService {
	
	@Autowired
	private BaseUserExtMapper baseUserExtMapper;
	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private BaseTypeService baseTypeService;
	
	/**
	 * @Description 新增信息
	 * @param baseUserExtEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	public String insert(BaseUserExtEntity baseUserExtEntity) {
		
		baseUserExtEntity.setUserExtId(IDUtil.getId());
		baseUserExtEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseUserExtEntity.setCreateTime(new Date());
		baseUserExtEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseUserExtEntity.setUpdateTime(new Date());
		int i = baseUserExtMapper.insert(baseUserExtEntity);
		if(i > 0) {
			return baseUserExtEntity.getUserExtId();
		}else {
			return null;
		}
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseUserExtEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	public String update(BaseUserExtEntity baseUserExtEntity){
	
		baseUserExtEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseUserExtEntity.setUpdateTime(new Date());
		int i = baseUserExtMapper.update(baseUserExtEntity);
		if(i > 0) {
			return baseUserExtEntity.getUserExtId();
		}else {
			return null;
		}
	}
	
	/**
	 * @Description 删除信息
	 * @param userExtId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	public int delete(String userExtId){
	
		return baseUserExtMapper.delete(userExtId);
	}
	
	/**
	 * @Description 通过用户编号查询用户扩展信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:25:32
	 */
	public BaseUserExtEntity getByUserId(String userId) {
		
		BaseUserExtEntity baseUserExtEntity = baseUserExtMapper.selectByUserId(userId);
		baseUserExtEntity = baseTypeService.translate(baseUserExtEntity, "XB", "sex", "sexName");
		if(null != baseUserExtEntity) {
			int attempts = loginAttemptService.getAttemptsCount(userId);
			baseUserExtEntity.setAttempts(attempts);
			boolean isBlocked = loginAttemptService.isBlocked(userId);
			baseUserExtEntity.setBlocked(isBlocked);
		}
		return baseUserExtEntity;
	}
	
	/**
	 * @Description 获取当前用户的扩展信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-06 10:06:30
	 */
	public BaseUserExtEntity getByByActiveUser() {
		
		String userId = UserInfoUtil.getUserId();
		return getByUserId(userId);
	}
	
	/**
	 * @Description 保存信息
	 * @param baseUserExtEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-04-23 16:20:03
	 */
	public String save(BaseUserExtEntity baseUserExtEntity) {
		
		String userExtId = baseUserExtEntity.getUserExtId();
		if(null == userExtId || "".equals(userExtId)) {
			return insert(baseUserExtEntity);
		}else {
			return update(baseUserExtEntity);
		}
		
		
	}
	
}
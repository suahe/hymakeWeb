package com.dzb.console.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.entity.BaseUserEntity;
import com.dzb.console.mapper.BaseUserMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Description t_base_user 表Service类
 * @author 38840472@qq.com
 * @date 2018-12-18 16:24:42
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseUserService")
@Transactional
public class BaseUserService {
	
	@Autowired
	private BaseUserMapper baseUserMapper;
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseUserEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	public PageInfo<BaseUserEntity> selectPagination(Integer offset, Integer limit, String searchText) {
		
		PageHelper.offsetPage(offset, limit);
		PageHelper.orderBy("UPDATE_TIME DESC");
		BaseUserEntity baseUserEntity = new BaseUserEntity();
		baseUserEntity.setUsername(searchText);
		baseUserEntity.setName(searchText);
		List<BaseUserEntity> list = baseUserMapper.selectPagination(baseUserEntity);
		PageInfo<BaseUserEntity> pi = new PageInfo<BaseUserEntity>(list);
		return pi;
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	public BaseUserEntity getByPkey(String userId){
	
		return baseUserMapper.selectByPkey(userId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseUserEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	public int insert(BaseUserEntity baseUserEntity) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		
		baseUserEntity.setUserId(IDUtil.getId());
		baseUserEntity.setPassword(bCryptPasswordEncoder.encode("123456"));
		baseUserEntity.setIsDel(0);
		baseUserEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseUserEntity.setCreateTime(new Date());
		baseUserEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseUserEntity.setUpdateTime(new Date());
		return baseUserMapper.insert(baseUserEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseUserEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	public int update(BaseUserEntity baseUserEntity){
	
		baseUserEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseUserEntity.setUpdateTime(new Date());
		return baseUserMapper.update(baseUserEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-18 16:24:42
	 */
	public int delete(String userId){
	
		return baseUserMapper.delete(userId);
	}
	
	/**
	 * @Description 锁定/解锁用户信息
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-24 15:05:15
	 */
	public int updateIsDel(String userId) {
		
		BaseUserEntity oBaseUserEntity  = getByPkey(userId);
		if(null != oBaseUserEntity) {
			BaseUserEntity baseUserEntity = new BaseUserEntity();
			if(0 == oBaseUserEntity.getIsDel()) {
				baseUserEntity.setIsDel(1);
			}else {
				baseUserEntity.setIsDel(0);
			}
			baseUserEntity.setUserId(userId);
			baseUserEntity.setUpdateUserid(UserInfoUtil.getUserId());
			baseUserEntity.setUpdateTime(new Date());
			return baseUserMapper.updateIsDel(baseUserEntity);
		}else {
			return 0;
		}
	}
	
	/**
	 * @Description 通过有效账户查找密码
	 * @param username
	 * @return
	 */
	public BaseUserEntity getPasswordByUsername(String username) {
		
		return baseUserMapper.selectPasswordByUsername(username);
	}
	
	/**
	 * @Description 通过用户编号查找用户密码
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-21 10:03:49
	 */
	public String getPasswordByUserId(String userId) {
		
		return baseUserMapper.selectPasswordByUserId(userId);
	}
	
	/**
	 * @Description 修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-21 10:06:41
	 */
	public int updatePassword(String userId,String password) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		BaseUserEntity baseUserEntity = new BaseUserEntity();
		baseUserEntity.setUserId(userId);
		baseUserEntity.setPassword(bCryptPasswordEncoder.encode(password));
		return baseUserMapper.updatePassword(baseUserEntity);
		
	}
	
	/**
	 * @Description 修改用户密码
	 * @param originalPassword
	 * @param latestPassword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-21 10:12:12
	 */
	public boolean changePassword(String originalPassword,String latestPassword) {
		
		String userId = UserInfoUtil.getUserId();
		boolean changeSuccess = false;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		String password = getPasswordByUserId(userId);
		boolean pass = false;
		pass = bCryptPasswordEncoder.matches(originalPassword,password);
		if(pass) {
			int i = updatePassword(userId,latestPassword);
			changeSuccess = (i>0)?true:false;
		}
		return changeSuccess;
	}
	
	/**
	 * @Description 通过邮箱地址查找用户信息
	 * @param email
	 * @return
	 * @author 38840472@qq.com
	 * @date 2019-01-16 11:32:08
	 */
	public BaseUserEntity selectByMail(String email) {
		
		return baseUserMapper.selectByMail(email);
	}
	
	public String getUsernameByUserId(String userId){
		
		BaseUserEntity baseUserEntity = baseUserMapper.selectByPkey(userId);
		if(null != baseUserEntity) {
			return baseUserEntity.getUsername();
		}else {
			return "-";
		}
		
	}
	
	public String getNameByUserId(String userId){
		
		BaseUserEntity baseUserEntity = baseUserMapper.selectByPkey(userId);
		if(null != baseUserEntity) {
			return baseUserEntity.getName();
		}else {
			return "-";
		}
		
	}
	
	/**
	 * @Description 获取当前用户信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-06 10:15:30
	 */
	public BaseUserEntity getByActiveUser() {

		String userId = UserInfoUtil.getUserId();
		return getByPkey(userId);
	}
	
	/**
	 * @Description 验证当前用户密码是否正确
	 * @param originalPassword
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-07-06 11:09:11
	 */
	public boolean validatePasswordByActiveUser(String originalPassword){
    	
		boolean pass = false;
		String userId = UserInfoUtil.getUserId();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		String password = getPasswordByUserId(userId);
		pass = bCryptPasswordEncoder.matches(originalPassword,password);
		return pass;
    }

	public <T> List<T> translate(List<T> list, String _idField, String _nameField ) {
		
		List<T> l = new ArrayList<T>();
		for(T obj:list) {
			try {
				Class<?> clazz = obj.getClass();
				PropertyDescriptor pd = new PropertyDescriptor(_idField, clazz);
				Method rm = pd.getReadMethod();
				String _key = (String)rm.invoke(obj);
				String str = getNameByUserId(_key);
				pd = new PropertyDescriptor(_nameField, clazz);
				Method wm = pd.getWriteMethod();
				wm.invoke(obj, str);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			l.add(obj);
		}
		return l;
	}
	
}
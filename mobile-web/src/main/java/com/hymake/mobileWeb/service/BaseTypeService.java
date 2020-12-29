package com.hymake.mobileWeb.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hymake.mobileWeb.config.MarvelConfig;
import com.hymake.mobileWeb.entity.BaseTypeCacheEntity;
import com.hymake.mobileWeb.entity.BaseTypeListEntity;
import com.hymake.mobileWeb.mapper.BaseTypeListMapper;
import com.hymake.mobileWeb.mapper.BaseTypeMapper;


/**
 * @Description t_base_type 表Service类
 * @author daizb@hymake.com
 * @date 2020-05-12 17:51:09
 * @version 1.0
 * @remark create by ca
 */

@Service("BaseTypeService")
@Transactional
public class BaseTypeService {
	
	private static final Logger log = LoggerFactory.getLogger(BaseTypeService.class);
	
	@Autowired
	private BaseTypeMapper baseTypeMapper;
	@Autowired
	private BaseTypeListMapper baseTypeListMapper;
	
	private LoadingCache<String, List<BaseTypeCacheEntity>> listCache;
	
	public BaseTypeService(MarvelConfig marvelConfig) {
		this.listCache = CacheBuilder.newBuilder()
	        .expireAfterWrite(marvelConfig.getCacheBasetypeDuration(), TimeUnit.MINUTES)
	        .build(new CacheLoader<String, List<BaseTypeCacheEntity>>() {
	            @Override
	            public List<BaseTypeCacheEntity> load(String code) throws Exception {
	            	log.debug("开始缓存数据：" + code);
	            	List<BaseTypeCacheEntity> list = new ArrayList<BaseTypeCacheEntity>();
	            	String baseTypeId = baseTypeMapper.selectByCode(code);
	            	if(null != baseTypeId) {
	            		List<BaseTypeListEntity> l = baseTypeListMapper.selectItemByBaseTypeId(baseTypeId);
	            		for(BaseTypeListEntity baseTypeListEntity: l) {
	            			list.add(new BaseTypeCacheEntity(baseTypeListEntity.getName(), baseTypeListEntity.getValue()));
	            		}
	            	}
	                return list;
	            }
	        });
	}
	
	/**
	 * @Description 通过数据字典类型代码和值获取列表数据字典项
	 * @param code
	 * @param value
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-05 19:44:53
	 */
	public String getListNameByCodeAndValue(String code, String value) {
		
		if(null == value || "".equals(value)) {
			return null;
		}else {
			String baseTypeId = baseTypeMapper.selectByCode(code);
			if(null != baseTypeId) {
				BaseTypeListEntity oBaseTypeListEntity = new BaseTypeListEntity();
				oBaseTypeListEntity.setBaseTypeId(baseTypeId);
				oBaseTypeListEntity.setValue(value);
				return baseTypeListMapper.selectListNameByCodeAndValue(oBaseTypeListEntity);
			}else {
				return null;
			}
		}
	}
	
	/**
	 * @Description 查询数据字典项列表
	 * @param baseTypeId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-29 10:48:35
	 */
	public List<BaseTypeListEntity> getItem(String baseTypeId){
		
		return baseTypeListMapper.selectItemByBaseTypeId(baseTypeId);
	}
	
	/**
	 * @Description 通过代码和值查找项目信息
	 * @param code
	 * @param value
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-27 16:58:43
	 */
	public BaseTypeCacheEntity getCacheItem(String code, String value) {
		
		try {
			if(code==null||value==null){
				return null;
			}
			List<BaseTypeCacheEntity> list = listCache.get(code);
			for(BaseTypeCacheEntity baseTypeCacheEntity: list) {
				if(value.equals(baseTypeCacheEntity.getValue())) {
					return baseTypeCacheEntity;
				}
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description 通过代码和值查找名称
	 * @param code
	 * @param value
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-27 17:05:42
	 */
	public String getCacheName(String code, String value) {
		
		BaseTypeCacheEntity baseTypeCacheEntity =  getCacheItem(code, value);
		if(null != baseTypeCacheEntity) {
			return baseTypeCacheEntity.getName();
		}else {
			return "";
		}
	}
	
	/**
	 * @Description 针对列表转换数据字典值和名称
	 * @param <T>
	 * @param list
	 * @param code
	 * @param _idField
	 * @param _nameField
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-05 19:47:06
	 */
	public <T> List<T> translate(List<T> list, String code, String _idField, String _nameField ) {
		
		List<T> l = new ArrayList<T>();
		for(T obj:list) {
			try {
				Class<?> clazz = obj.getClass();
				PropertyDescriptor pd = new PropertyDescriptor(_idField, clazz);
				Method rm = pd.getReadMethod();
				String _key = (String)rm.invoke(obj);
				String str = getCacheName(code, _key);
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
	
	/**
	 * @Description 实体转换数据字典值和名称
	 * @param <T>
	 * @param obj
	 * @param code
	 * @param _idField
	 * @param _nameField
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-09 14:04:37
	 */
	public <T> T translate(T obj, String code, String _idField, String _nameField ) {
		
		if(null != obj) {
			PropertyDescriptor pd = null;
			try {
				Class<?> clazz = obj.getClass();
				pd = new PropertyDescriptor(_idField, clazz);
				Method rm = pd.getReadMethod();
				String _key = (String)rm.invoke(obj);
				String str = getCacheName(code, _key);
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
		}
		return obj;
	}
	
}
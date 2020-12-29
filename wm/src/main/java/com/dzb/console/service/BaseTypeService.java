package com.dzb.console.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.console.config.MarvelConfig;
import com.dzb.console.entity.BaseTypeCacheEntity;
import com.dzb.console.entity.BaseTypeEntity;
import com.dzb.console.entity.BaseTypeListEntity;
import com.dzb.console.entity.BaseTypeTreeEntity;
import com.dzb.console.mapper.BaseTypeListMapper;
import com.dzb.console.mapper.BaseTypeMapper;
import com.dzb.console.mapper.BaseTypeTreeMapper;
import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;

/**
 * @Description t_base_type 表Service类
 * @author 38840472@qq.com
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
	@Autowired
	private BaseTypeTreeMapper baseTypeTreeMapper;
	
	private LoadingCache<String, List<BaseTypeCacheEntity>> listCache;
	
	public BaseTypeService(MarvelConfig marvelConfig) {
		this.listCache = CacheBuilder.newBuilder()
	        .expireAfterWrite(marvelConfig.getCacheBasetypeDuration(), TimeUnit.MINUTES)		//设置清除缓存时间
	        .maximumSize(1000)
	        .recordStats()
	        .build(new CacheLoader<String, List<BaseTypeCacheEntity>>() {		//设置缓存（Map）的key和value值
	            @Override
	            public List<BaseTypeCacheEntity> load(String code) throws Exception {
	            	List<BaseTypeCacheEntity> list = new ArrayList<BaseTypeCacheEntity>();
	            	String baseTypeId = getBaseTypeIdByCode(code);
	            	if(null != baseTypeId) {
	            		List<BaseTypeListEntity> l = baseTypeListMapper.selectListByBasetypeId(baseTypeId);
	            		for(BaseTypeListEntity baseTypeListEntity: l) {
	            			BaseTypeCacheEntity baseTypeCacheEntity = new BaseTypeCacheEntity(baseTypeListEntity.getName(), baseTypeListEntity.getValue());
	            			list.add(baseTypeCacheEntity);
	            		}
	            	}
	                return list;
	            }
	        });
	}
	
	/**
	 * @Description 通过代码和值查找项目信息
	 * @param code
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 16:58:43
	 */
	public BaseTypeCacheEntity getCacheItem(String code, String value) {
		
		if(null == value || "".equals(value)) {
			return null;
		}else {
			try {
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
	}
	
	/**
	 * @Description 通过代码和值查找名称
	 * @param code
	 * @param value
	 * @return
	 * @author 38840472@qq.com
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
	 * @Description 通过代码查找列表信息
	 * @param code
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 16:59:15
	 */
	public List<BaseTypeCacheEntity> getCacheList(String code) {
		
		List<BaseTypeCacheEntity> list = null;
		try {
			
			list = listCache.get(code);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @Description 通过代码查找列表信息
	 * @param code
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 17:43:08
	 */
	public List<Map<String, String>> getCacheMapToList(String code){
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			List<BaseTypeCacheEntity> l = listCache.get(code);
			for(BaseTypeCacheEntity baseTypeCacheEntity: l) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", baseTypeCacheEntity.getValue());
				map.put("text", baseTypeCacheEntity.getName());
				list.add(map);
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @Description 清空指定code的缓存
	 * @param keyword
	 * @author 38840472@qq.com
	 * @date 2020-06-26 23:15:16
	 */
	public void invalidate(String code) {
		
		listCache.invalidate(code);
	}
	
	/**
	 * @Description 清空所有缓存
	 * @author 38840472@qq.com
	 * @date 2020-06-26 23:15:31
	 */
	public void invalidateAll() {
		
		listCache.invalidateAll();
	}
	
	/**
	 * @Description 查看缓存状态
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-26 23:25:29
	 */
	public Map<String, Long> stats() {
		
		CacheStats cacheStats = listCache.stats();
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("缓存命中次数(hitCount)", cacheStats.hitCount());
		map.put("缓存未命中次数(missCount)", cacheStats.missCount());
		map.put("缓存加载成功次数(loadSuccessCount)", cacheStats.loadSuccessCount());
		map.put("缓存加载异常次数(loadExceptionCount)", cacheStats.loadExceptionCount());
		map.put("总加载时间:纳秒(totalLoadTime)", cacheStats.totalLoadTime());
		map.put("缓存自动回收次数(evictionCount)", cacheStats.evictionCount());
		return map;
	}
	
	/**
	 * @Description 查询信息列表
	 * @param page
	 * @param rows
	 * @param baseTypeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	public PageInfo<BaseTypeEntity> getPagination(Integer offset, Integer limit, BaseTypeEntity baseTypeEntity) {
		
		log.debug("查询信息列表");
		PageHelper.offsetPage(offset, limit);
		return new PageInfo<BaseTypeEntity>(baseTypeMapper.selectPagination(baseTypeEntity));
	}
	
	/**
	 * @Description 通过主键查询信息
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	public BaseTypeEntity getByPkey(String baseTypeId){
	
		log.debug("通过主键查询信息");
		return baseTypeMapper.selectByPkey(baseTypeId);
	}
	
	/**
	 * @Description 新增信息
	 * @param baseTypeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	public int insert(BaseTypeEntity baseTypeEntity) {

		log.debug("新增信息");
		baseTypeEntity.setBaseTypeId(IDUtil.getId());
		baseTypeEntity.setVer(0);
		baseTypeEntity.setCreateUserid(UserInfoUtil.getUserId());
		baseTypeEntity.setCreateTime(new Date());
		baseTypeEntity.setUpdateUserid(UserInfoUtil.getUserId());
		baseTypeEntity.setUpdateTime(new Date());
		return baseTypeMapper.insert(baseTypeEntity);
	}
	
	/**
	 * @Description 编辑信息
	 * @param baseTypeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	public int update(BaseTypeEntity baseTypeEntity){
	
		log.debug("编辑信息");
		baseTypeEntity.setUpdateUserid(UserInfoUtil.getUserId());
 		baseTypeEntity.setUpdateTime(new Date());
		return baseTypeMapper.update(baseTypeEntity);
	}
	
	/**
	 * @Description 删除信息
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 17:51:09
	 */
	public int delete(String baseTypeId){
	
		log.debug("删除信息");
		return baseTypeMapper.delete(baseTypeId);
	}
	
	/**
	 * @Description 通过代码获取列表数据字典项列表
	 * @param code
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 09:03:59
	 */
	public List<Map<String, String>> getListByCode(String code){
		
		log.debug("通过代码查询列表数据列表");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String baseTypeId = baseTypeMapper.selectByCode(code);
		if(null != baseTypeId) {
			List<BaseTypeListEntity> l = baseTypeListMapper.selectByBaseTypeId(baseTypeId);
			for(BaseTypeListEntity baseTypeListEntity: l) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", baseTypeListEntity.getValue());
				map.put("text", baseTypeListEntity.getName());
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * @Description 通过代码获取树型数据字典项列表
	 * @param code
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-15 13:40:02
	 */
	public List<Tree<String>> getTreeByCode(String code){
		
		log.debug("通过代码查询树型数据列表");
		
		String baseTypeId = baseTypeMapper.selectByCode(code);
		
		if(null != baseTypeId) {
			
			List<BaseTypeTreeEntity> baseTypeTreeEntityList = baseTypeTreeMapper.selectByBaseTypeId(baseTypeId);
			
			List<TreeNode<String>> nodeList = CollUtil.newArrayList();
			TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
			treeNodeConfig.setIdKey("id");
			treeNodeConfig.setChildrenKey("nodes");
			treeNodeConfig.setNameKey("text");
			treeNodeConfig.setParentIdKey("pid");
			
			for(BaseTypeTreeEntity baseTypeTreeEntity:baseTypeTreeEntityList) {
				
				String id = baseTypeTreeEntity.getBaseTypeTreeId();
				String pid = baseTypeTreeEntity.getBaseTypeTreePid();
				String name = baseTypeTreeEntity.getName();
				nodeList.add(new TreeNode<>(id, pid, name, baseTypeTreeEntity.getValue()));
			}
			
			List<Tree<String>> treeList = TreeUtil.build(nodeList, "-", treeNodeConfig,
					(treeNode, tree) -> {
			            tree.setId(treeNode.getId());
			            tree.setParentId(treeNode.getParentId());
			            tree.setName(treeNode.getName());
			            tree.putExtra("value", treeNode.getWeight());
			        });
			return treeList;
		}else {
			
			return  null;
		}
	}
	
	/**
	 * @Description 通过代码和值查询树型数据
	 * @param code
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-15 14:24:45
	 */
	public BaseTypeTreeEntity getBaseTypeTreeByCodeAndValue(String code, String value) {
		
		BaseTypeTreeEntity baseTypeTreeEntity = null;
		String baseTypeId = getBaseTypeIdByCode(code);
		if(null != baseTypeId) {
			baseTypeTreeEntity = getTreeByCodeAndValue(baseTypeId, value);
		}
		return baseTypeTreeEntity;
	}
	
	/**
	 * @Description 通过数据字典编号和值获取树数据字典内容
	 * @param baseTypeId
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:32:59
	 */
	private BaseTypeTreeEntity getTreeByCodeAndValue(String baseTypeId, String value) {
		
		BaseTypeTreeEntity oBaseTypeTreeEntity = new BaseTypeTreeEntity();
		oBaseTypeTreeEntity.setBaseTypeId(baseTypeId);
		oBaseTypeTreeEntity.setValue(value);
		return baseTypeTreeMapper.selectTreeNameByCodeAndValue(oBaseTypeTreeEntity);
	}
	
	/**
	 * @Description 通过代码类型获取数据字典编号
	 * @param code
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 19:42:22
	 */
	private String getBaseTypeIdByCode(String code) {
		return baseTypeMapper.selectByCode(code);
	}
	
	/**
	 * @Description 通过数据字典编号和值获取列表数据字典内容
	 * @param baseTypeId
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 19:43:22
	 */
	private BaseTypeListEntity getListByCodeAndValue(String baseTypeId, String value) {
		
		BaseTypeListEntity oBaseTypeListEntity = new BaseTypeListEntity();
		oBaseTypeListEntity.setBaseTypeId(baseTypeId);
		oBaseTypeListEntity.setValue(value);
		return baseTypeListMapper.selectListNameByCodeAndValue(oBaseTypeListEntity);
	}
	
	/**
	 * @Description 通过数据字典类型代码和值获取列表数据字典项
	 * @param code
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 19:44:53
	 */
	public BaseTypeListEntity getListNameByCodeAndValue(String code, String value) {
		
		BaseTypeListEntity baseTypeListEntity = null;
		String baseTypeId = getBaseTypeIdByCode(code);
		if(null != baseTypeId) {
			baseTypeListEntity = getListByCodeAndValue(baseTypeId, value);
		}
		return baseTypeListEntity;
	}
	
	/**
	 * @Description 通过数据字典类型代码和值获取列表数据字典的名称
	 * @param code
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 19:46:47
	 */
	public String getNameByCodeAndValue(String code, String value) {
		
		BaseTypeListEntity baseTypeListEntity = getListNameByCodeAndValue(code, value);
		return (null ==  baseTypeListEntity) ? "" : baseTypeListEntity.getName();
	}
	
	/**
	 * @Description 通过数据字典类型代码和值获取树型数据字典的名称
	 * @param code
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:35:51
	 */
	public String getTreeNameByCodeAndValue(String code, String value) {
		
		BaseTypeTreeEntity baseTypeTreeEntity = getBaseTypeTreeByCodeAndValue(code, value);
		return (null ==  baseTypeTreeEntity) ? "" : baseTypeTreeEntity.getName();
	}
	
	/**
	 * @Description 针对列表转换数据字典值和名称
	 * @param <T>
	 * @param list
	 * @param code
	 * @param _idField
	 * @param _nameField
	 * @return
	 * @author 38840472@qq.com
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
//				String str = getNameByCodeAndValue(code, _key);
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
	 * @Description 针对树型数据字典转换数据字典值和名称
	 * @param <T>
	 * @param list
	 * @param code
	 * @param _idField
	 * @param _nameField
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:34:35
	 */
	public <T> List<T> translateTree(List<T> list, String code, String _idField, String _nameField ) {
		
		List<T> l = new ArrayList<T>();
		for(T obj:list) {
			try {
				Class<?> clazz = obj.getClass();
				PropertyDescriptor pd = new PropertyDescriptor(_idField, clazz);
				Method rm = pd.getReadMethod();
				String _key = (String)rm.invoke(obj);
				String str = getTreeNameByCodeAndValue(code, _key);
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
	 * @author 38840472@qq.com
	 * @date 2020-06-09 14:04:37
	 */
	public <T> T translate(T obj, String code, String _idField, String _nameField ) {
		
		PropertyDescriptor pd = null;
		if(null != obj) {
			try {
				Class<?> clazz = obj.getClass();
				pd = new PropertyDescriptor(_idField, clazz);
				Method rm = pd.getReadMethod();
				String _key = (String)rm.invoke(obj);
				//String str = getNameByCodeAndValue(code, _key);
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
	
	/**
	 * @Description 实体转换数据字典值和名称
	 * @param <T>
	 * @param obj
	 * @param code
	 * @param _idField
	 * @param _nameField
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-09 14:04:37
	 */
	public <T> T translateTree(T obj, String code, String _idField, String _nameField ) {
		
		PropertyDescriptor pd = null;
		try {
			Class<?> clazz = obj.getClass();
			pd = new PropertyDescriptor(_idField, clazz);
			Method rm = pd.getReadMethod();
			String _key = (String)rm.invoke(obj);
			String str = getTreeNameByCodeAndValue(code, _key);
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
		return obj;
	}
	
	/**
	 * @Description 通过数据字典类型代码和值获取树型数据字典的名称
	 * @param code
	 * @param value
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:35:51
	 */
	public String getTreeFullNameByCodeAndValue(String code, String value) {
		
		BaseTypeTreeEntity baseTypeTreeEntity = getBaseTypeTreeByCodeAndValue(code, value);
		return (null ==  baseTypeTreeEntity) ? "" : baseTypeTreeEntity.getFullName();
	}
	
	/**
	 * @Description 针对树型数据字典转换数据字典值和全名
	 * @param <T>
	 * @param list
	 * @param code
	 * @param _idField
	 * @param _nameField
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-05 20:34:35
	 */
	public <T> List<T> translateTreeFullName(List<T> list, String code, String _idField, String _nameField ) {
		
		List<T> l = new ArrayList<T>();
		for(T obj:list) {
			try {
				Class<?> clazz = obj.getClass();
				PropertyDescriptor pd = new PropertyDescriptor(_idField, clazz);
				Method rm = pd.getReadMethod();
				String _key = (String)rm.invoke(obj);
				String str = getTreeFullNameByCodeAndValue(code, _key);
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
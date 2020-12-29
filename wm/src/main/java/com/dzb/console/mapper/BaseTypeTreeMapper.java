package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseTypeTreeEntity;

/**
 * @Description t_base_type_tree 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-05-14 10:41:46
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseTypeTreeMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseTypeTreeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	List<BaseTypeTreeEntity> selectPagination(BaseTypeTreeEntity baseTypeTreeEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	BaseTypeTreeEntity selectByPkey(String baseTypeTreeId);
	
	/**
	 * @Description 新增信息
	 * @param baseTypeTreeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	int insert(BaseTypeTreeEntity baseTypeTreeEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseTypeTreeEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	int update(BaseTypeTreeEntity baseTypeTreeEntity);
	
	/**
	 * @Description 删除信息
	 * @param baseTypeTreeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-14 10:41:46
	 */
	int delete(String baseTypeTreeId);
	
	/**
	 * @Description 通过数据字典编号获取数据字典项
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 15:04:07
	 */
	List<BaseTypeTreeEntity> selectByBaseTypeId(String baseTypeId);
	
	/**
	 * @Description 通过代码和值查询树型数据
	 * @param baseTypeTreeEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-15 14:22:32
	 */
	BaseTypeTreeEntity selectTreeNameByCodeAndValue(BaseTypeTreeEntity baseTypeTreeEntity);
	
	/**
	 * @Description 通过数据字典编号查询排序序列的最大值
	 * @param baseTypeId
	 * @author 38840472@qq.com
	 * @date 2020-06-03 21:09:58
	 */
	Integer selectMaxByBaseTypeId(String baseTypeId);
	
	/**
	 * @Description 通过数据字典编号和排序序列找到比当前小的值
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:04:51
	 */
	BaseTypeTreeEntity selectDownBySequAndBaseTypeTreePid(BaseTypeTreeEntity baseTypeTreeEntity);
	
	/**
	 * @Description 通过数据字典编号和排序序列找到比当前大的值
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:04:51
	 */
	BaseTypeTreeEntity selectUpBySequAndBaseTypeTreePid(BaseTypeTreeEntity baseTypeTreeEntity);
	
	/**
	 * @Description 更新排序序号
	 * @param baseTypeListEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:21:50
	 */
	int updateSequ(BaseTypeTreeEntity baseTypeTreeEntity);
	
	/**
	 * @Description 通过父编号获取子节点信息
	 * @param baseTypeTreePid
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-11 10:18:54
	 */
	List<BaseTypeTreeEntity> selectByBaseTypeTreePid(String baseTypeTreePid);
	
	int updateFullName(BaseTypeTreeEntity baseTypeTreeEntity);

}
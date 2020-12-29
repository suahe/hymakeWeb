package com.dzb.console.mapper;

import java.util.List;
import com.dzb.console.entity.BaseTypeListEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Description t_base_type_list 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-05-13 14:59:08
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseTypeListMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseTypeListEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	List<BaseTypeListEntity> selectPagination(BaseTypeListEntity baseTypeListEntity);
	
	/**
	 * @Description 查询信息列表
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 16:20:32
	 */
	List<BaseTypeListEntity> selectListByBasetypeId(String baseTypeId);
	
	/**
	 * @Description 通过主键查询信息
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	BaseTypeListEntity selectByPkey(String baseTypeListId);
	
	/**
	 * @Description 新增信息
	 * @param baseTypeListEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	int insert(BaseTypeListEntity baseTypeListEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseTypeListEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	int update(BaseTypeListEntity baseTypeListEntity);
	
	/**
	 * @Description 删除信息
	 * @param baseTypeListId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 14:59:08
	 */
	int delete(String baseTypeListId);
	
	/**
	 * @Description 通过数据字典编号获取数据字典项
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-13 15:04:07
	 */
	List<BaseTypeListEntity> selectByBaseTypeId(String baseTypeId);
	
	/**
	 * @Description 通过代码和值查询列表数据
	 * @param baseTypeListEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-15 14:53:33
	 */
	BaseTypeListEntity selectListNameByCodeAndValue(BaseTypeListEntity baseTypeListEntity);
	
	/**
	 * @Description 通过数据字典编号查询排序序列的最大值
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 19:31:45
	 */
	Integer selectMaxByBaseTypeId(String baseTypeId);
	
	/**
	 * @Description 通过数据字典编号和排序序列找到比当前小的值
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:04:51
	 */
	BaseTypeListEntity selectDownBySequAndBaseTypeId(BaseTypeListEntity baseTypeListEntity);
	
	/**
	 * @Description 通过数据字典编号和排序序列找到比当前大的值
	 * @param baseTypeId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:04:51
	 */
	BaseTypeListEntity selectUpBySequAndBaseTypeId(BaseTypeListEntity baseTypeListEntity);
	
	/**
	 * @Description 更新排序序号
	 * @param baseTypeListEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:21:50
	 */
	int updateSequ(BaseTypeListEntity baseTypeListEntity);

}
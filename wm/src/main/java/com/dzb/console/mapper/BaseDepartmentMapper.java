package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseDepartmentEntity;

/**
 * @Description t_base_department 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-05-26 23:22:37
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseDepartmentMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseDepartmentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	List<BaseDepartmentEntity> selectPagination(BaseDepartmentEntity baseDepartmentEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	BaseDepartmentEntity selectByPkey(String departmentId);
	
	/**
	 * @Description 新增信息
	 * @param baseDepartmentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	int insert(BaseDepartmentEntity baseDepartmentEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseDepartmentEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	int update(BaseDepartmentEntity baseDepartmentEntity);
	
	/**
	 * @Description 删除信息
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:22:37
	 */
	int delete(String departmentId);
	
	/**
	 * @Description 查询所有的部门信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-26 23:29:07
	 */
	List<BaseDepartmentEntity> selectAllDepartment();
	

}
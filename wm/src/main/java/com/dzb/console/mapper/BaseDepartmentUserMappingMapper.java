package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseDepartmentEntity;
import com.dzb.console.entity.BaseDepartmentUserMappingEntity;
import com.dzb.console.entity.BaseUserEntity;

/**
 * @Description t_base_department_user_mapping 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-05-27 00:34:45
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseDepartmentUserMappingMapper {
	
	/**
	 * @Description 新增信息
	 * @param baseDepartmentUserMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 00:34:45
	 */
	int insert(BaseDepartmentUserMappingEntity baseDepartmentUserMappingEntity);
	
	/**
	 * @Description 删除信息
	 * @param departmentUserId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 00:34:45
	 */
	int delete(String departmentUserId);
	
	/**
	 * @Description 通过用户编号查找用户部门名称
	 * @param userId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:05:21
	 */
	BaseDepartmentEntity selectDepartmentByUserId(String userId);
	
	/**
	 * @Description 查询未被部门选中的人员列表
	 * @param baseDepartmentUserMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 08:57:03
	 */
	List<BaseUserEntity> selectUnUserByDepartmentIdPagination(BaseDepartmentUserMappingEntity baseDepartmentUserMappingEntity);
	
	/**
	 * @Description 查询被部门选中的人员列表
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 09:37:32
	 */
	List<BaseUserEntity> selectUserByDepartmentIdPagination(String departmentId);
	
	/**
	 * @Description 删除部门人员对应关系
	 * @param baseDepartmentUserMappingEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 10:31:01
	 */
	int deleteByUserIdAndDepartmentId(BaseDepartmentUserMappingEntity baseDepartmentUserMappingEntity);
	
	/**
	 * @Description 删除部门映射关系
	 * @param departmentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-27 10:43:54
	 */
	int deleteByDepartmentId(String departmentId);

}
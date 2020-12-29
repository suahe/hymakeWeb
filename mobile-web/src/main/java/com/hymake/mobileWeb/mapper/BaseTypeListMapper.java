package com.hymake.mobileWeb.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.hymake.mobileWeb.entity.BaseTypeListEntity;

/**
 * @Description t_base_type_list 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-05-13 14:59:08
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseTypeListMapper {
	
	/**
	 * @Description 通过代码和值查询列表数据
	 * @param baseTypeListEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-05-15 14:53:33
	 */
	String selectListNameByCodeAndValue(BaseTypeListEntity baseTypeListEntity);
	
	/**
	 * @Description 查询数据字典项列表
	 * @param baseTypeId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-06-29 10:46:19
	 */
	List<BaseTypeListEntity> selectItemByBaseTypeId(String baseTypeId);

}
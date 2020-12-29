package com.dzb.console.mapper;

import java.util.List;
import com.dzb.console.entity.BaseDocumentEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Description t_base_document 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2020-05-08 14:21:58
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseDocumentMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param baseDocumentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	List<BaseDocumentEntity> selectPagination(BaseDocumentEntity baseDocumentEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param documentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	BaseDocumentEntity selectByPkey(String documentId);
	
	/**
	 * @Description 新增信息
	 * @param baseDocumentEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	int insert(BaseDocumentEntity baseDocumentEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseDocumentEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	int update(BaseDocumentEntity baseDocumentEntity);
	
	/**
	 * @Description 删除信息
	 * @param documentId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-08 14:21:58
	 */
	int delete(String documentId);
	
	
	/**
	 * @Description 通过Md5查找文件信息
	 * @param md5key
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-05-12 13:36:50
	 */
	String selectByMd5(String md5key);

}
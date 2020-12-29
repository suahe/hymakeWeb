package com.dzb.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzb.console.entity.BaseMenuEntity;

/**
 * @Description t_base_menu 表数据库操作接口
 * @author 38840472@qq.com
 * @date 2018-12-19 16:00:36
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface BaseMenuMapper {
	
	/**
	 * @Description 通过主键查询信息
	 * @param menuId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	BaseMenuEntity selectByPkey(String menuId);
	
	/**
	 * @Description 新增信息
	 * @param baseMenuEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	int insert(BaseMenuEntity baseMenuEntity);
	
	/**
	 * @Description 编辑信息
	 * @param baseMenuEntity
	 * @return
	 * @author  38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	int update(BaseMenuEntity baseMenuEntity);
	
	/**
	 * @Description 删除信息
	 * @param menuId
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	int delete(String menuId);
	
	/**
	 * @Description 获取菜单列表
	 * @return
	 * @author 38840472@qq.com
	 * @date 2018-12-19 16:00:36
	 */
	List<BaseMenuEntity> selectMenuList();
	
	/**
	 * @Description 查询所有菜单信息
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 21:24:59
	 */
	List<BaseMenuEntity> selectAllMenuList();
	
	/**
	 * @Description 获取最大的排序值
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 21:24:43
	 */
	Integer selectMaxSequ();
	
	/**
	 * @Description 统计父节点有几个子节点信息
	 * @param menuPid
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 21:25:18
	 */
	int countByMenuPid(String menuPid);
	
	/**
	 * @Description 通过菜单编号和排序序列找到比当前小的值
	 * @param baseMenuEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-03 20:04:51
	 */
	BaseMenuEntity selectDownBySequAndMenuPid(BaseMenuEntity baseMenuEntity);
	
	/**
	 * @Description 更新排序字段
	 * @param baseMenuEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 21:24:32
	 */
	int updateSequ(BaseMenuEntity baseMenuEntity);
	
	/**
	 * @Description 通过菜单编号和排序序列找到比当前大的值
	 * @param baseMenuEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-06-27 21:18:50
	 */
	BaseMenuEntity selectUpBySequAndMenuPid(BaseMenuEntity baseMenuEntity);

}
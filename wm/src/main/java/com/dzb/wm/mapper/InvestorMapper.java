package com.dzb.wm.mapper;

import java.util.List;
import com.dzb.wm.entity.InvestorEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Description t_investor 表数据库操作接口
 * @author daizb@hymake.com
 * @date 2020-08-11 19:02:47
 * @version 1.0
 * @remark create by ca
 */

@Mapper
public interface InvestorMapper {
	
	/**
	 * @Description 查询信息列表
	 * @param investorEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	List<InvestorEntity> selectPagination(InvestorEntity investorEntity);
	
	/**
	 * @Description 通过主键查询信息
	 * @param investorId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	InvestorEntity selectByPkey(long investorId);
	
	/**
	 * @Description 新增信息
	 * @param investorEntity
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	int insert(InvestorEntity investorEntity);
	
	/**
	 * @Description 编辑信息
	 * @param investorEntity
	 * @return
	 * @author  daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	int update(InvestorEntity investorEntity);
	
	/**
	 * @Description 删除信息
	 * @param investorId
	 * @return
	 * @author daizb@hymake.com
	 * @date 2020-08-11 19:02:47
	 */
	int delete(long investorId);
	
	/**
	 * @Description 更新推送（发布）时间
	 * @param investorEntity
	 * @return
	 * @author 38840472@qq.com
	 * @date 2020-08-12 14:52:00
	 */
	int updatePushTimeByInvestorId(InvestorEntity investorEntity);

}
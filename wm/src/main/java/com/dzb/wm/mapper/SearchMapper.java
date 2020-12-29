package com.dzb.wm.mapper;

import com.dzb.wm.entity.SearchEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author suahe
 * @date 2020/10/28 16:41
 * @describe TODO
 */
@Mapper
public interface SearchMapper {

    /**
     * @Description 查询信息列表
     */
    List<SearchEntity> selectPagination(SearchEntity searchEntity);

    /**
     * @Description 通过主键查询信息
     */
    SearchEntity selectByPkey(long id);

    /**
     * @Description 新增信息
     */
    int insert(SearchEntity searchEntity);

    /**
     * @Description 编辑信息
     */
    int update(SearchEntity searchEntity);

    /**
     * @Description 删除信息
     */
    int delete(long id);


}

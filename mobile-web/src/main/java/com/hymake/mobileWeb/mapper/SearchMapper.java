package com.hymake.mobileWeb.mapper;

import com.hymake.mobileWeb.entity.SearchEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author suahe
 * @date 2020/10/28 16:41
 * @describe TODO
 */
@Mapper
public interface SearchMapper {

    List<SearchEntity> selectPagination(String keyword);
}

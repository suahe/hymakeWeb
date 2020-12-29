package com.hymake.mobileWeb.mapper;


import com.hymake.mobileWeb.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author suahe
 * @date 2020/10/28 16:41
 * @describe TODO
 */
@Mapper
public interface ProductMapper {

    List<ProductEntity> selectPagination(@Param("dropDownBoxId")Long dropDownBoxId,@Param("labelId")Long labelId,
                                         @Param("belongPageType") Integer belongPageType);

}

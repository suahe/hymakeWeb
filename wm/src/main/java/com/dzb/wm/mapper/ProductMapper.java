package com.dzb.wm.mapper;

import com.dzb.wm.entity.ProductEntity;
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

    List<ProductEntity> selectPagination(ProductEntity productEntity);


    int insert(ProductEntity productEntity);

    int del(long productId);

    ProductEntity getByPkey(long productId);

    int update(ProductEntity productEntity);

    List<ProductEntity> getProductsByLabelId(@Param("belongPageType") Integer belongPageType, @Param("labelId") Long labelId);

    int updateState(ProductEntity productEntity);

    List<ProductEntity> getProductsByDropDownBoxId(@Param("belongPageType") Integer belongPageType, @Param("dropDownBoxId")Long dropDownBoxId);
}

package com.dzb.wm.mapper;

import com.dzb.wm.entity.LabelEntity;
import com.dzb.wm.entity.LabelProdcutEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author suahe
 * @date 2020/10/28 16:41
 * @describe TODO
 */
@Mapper
public interface LabelMapper {

    List<LabelEntity> selectPagination(LabelEntity labelEntity);

    int insert(LabelEntity labelEntity);

    int del(long labelId);

    LabelEntity getByPkey(long labelId);

    int update(LabelEntity labelEntity);

    List<LabelEntity> getLabels(@Param("belongPageType") Integer belongPageType,@Param("dropDownBoxId") Long dropDownBoxId);

    int delSelectedProduct(Long labelId);

    int batchInsertSelectedProcut(@Param("labelProdcutEntitieList") List<LabelProdcutEntity> labelProdcutEntitieList);

    String getProdcutNames(Long labelId);

    int updateState(LabelEntity labelEntity);
}

package com.dzb.wm.mapper;

import com.dzb.wm.entity.DropDownBoxEntity;
import com.dzb.wm.entity.DropDownBoxLabelEntity;
import com.dzb.wm.entity.DropDownBoxProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author suahe
 * @date 2020/10/28 16:41
 * @describe TODO
 */
@Mapper
public interface DropDownBoxMapper {

    List<DropDownBoxEntity> selectPagination(DropDownBoxEntity dropDownBoxEntity);

    int insert(DropDownBoxEntity dropDownBoxEntity);

    int del(Long dropDownBoxId);

    DropDownBoxEntity getByPkey(Long dropDownBoxId);

    int update(DropDownBoxEntity dropDownBoxEntity);

    int delSelectedLabel(Long dropDownBoxId);

    int batchInsertSelectedLabel(@Param("dropDownBoxLabelEntityList") List<DropDownBoxLabelEntity> dropDownBoxLabelEntityList);

    String getLabelNames(Long dropDownBoxId);

    int updateState(DropDownBoxEntity dropDownBoxEntity);

    int batchInsertSelectedProduct(@Param("dropDownBoxProductEntityList") List<DropDownBoxProductEntity> dropDownBoxProductEntityList);

    int delSelectProduct(Long dropDownBoxId);

    String getProductNames(Long dropDownBoxId);
}

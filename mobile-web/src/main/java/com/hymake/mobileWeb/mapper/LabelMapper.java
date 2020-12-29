package com.hymake.mobileWeb.mapper;


import com.hymake.mobileWeb.entity.LabelEntity;
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


    List<LabelEntity> getByDropDownIdAndBelongPageType(@Param("dropDownBoxId")Long dropDownBoxId, @Param("belongPageType")Integer belongPageType);

    List<LabelEntity> getByBelongPageType(Integer belongPageType);
}

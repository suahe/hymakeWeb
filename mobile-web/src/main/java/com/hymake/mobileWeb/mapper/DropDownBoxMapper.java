package com.hymake.mobileWeb.mapper;

import com.hymake.mobileWeb.entity.DropDownBoxEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author suahe
 * @date 2020/10/28 16:41
 * @describe TODO
 */
@Mapper
public interface DropDownBoxMapper {


    List<DropDownBoxEntity> getByBelongPageType( Integer belongPageType);

    DropDownBoxEntity getByPkey(Long dropDownBoxId);

    DropDownBoxEntity getByName(String dropDownBoxName);
}

package com.hymake.mobileWeb.service;

import com.hymake.mobileWeb.entity.DropDownBoxEntity;
import com.hymake.mobileWeb.mapper.DropDownBoxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author suahe
 * @date 2020/11/11 14:13
 * @describe 产品service
 */
@Service
public class DropDownBoxService {

    private static final Logger log = LoggerFactory.getLogger(DropDownBoxService.class);

    @Autowired
    private DropDownBoxMapper dropDownBoxMapper;


    public List<DropDownBoxEntity> getByBelongPageType(Integer belongPageType) {
        return dropDownBoxMapper.getByBelongPageType(belongPageType);
    }

    public DropDownBoxEntity getByPkey(long dropDownBoxId) {
        return dropDownBoxMapper.getByPkey(dropDownBoxId);
    }

    public DropDownBoxEntity getByName(String dropDownBoxName) {
        return dropDownBoxMapper.getByName(dropDownBoxName);
    }
}

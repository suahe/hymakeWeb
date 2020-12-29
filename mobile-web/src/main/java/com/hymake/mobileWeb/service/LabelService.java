package com.hymake.mobileWeb.service;


import com.hymake.mobileWeb.entity.LabelEntity;
import com.hymake.mobileWeb.mapper.LabelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author suahe
 * @date 2020/11/11 14:13
 * @describe 产品service
 */
@Service
public class LabelService {

    private static final Logger log = LoggerFactory.getLogger(LabelService.class);

    @Autowired
    private LabelMapper labelMapper;

    public List<LabelEntity> getByDropDownIdAndBelongPageType(Long dropDownBoxId,Integer belongPageType) {
        return labelMapper.getByDropDownIdAndBelongPageType(dropDownBoxId,belongPageType);
    }

    public List<LabelEntity> getByBelongPageType(Integer belongPageType) {
        return labelMapper.getByBelongPageType(belongPageType);
    }
}

package com.hymake.mobileWeb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.ProductEntity;
import com.hymake.mobileWeb.mapper.ProductMapper;
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
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BaseTypeService baseTypeService;

    public PageInfo<ProductEntity> getPagination(Integer offset, Integer limit,Long dropDownBoxId,Long labelId,Integer belongPageType) {
        log.debug("查询信息列表");
        PageHelper.offsetPage(offset, limit);
        PageInfo<ProductEntity> pi = new PageInfo<>(productMapper.selectPagination(dropDownBoxId,labelId,belongPageType));
        List<ProductEntity> list = pi.getList();
        list = baseTypeService.translate(list, "CPLX", "belongPageType", "belongPageTypeName");
        pi.setList(list);
        return pi;
    }
}

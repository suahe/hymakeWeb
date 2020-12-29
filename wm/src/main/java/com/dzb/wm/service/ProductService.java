package com.dzb.wm.service;

import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.service.BaseTypeService;
import com.dzb.console.util.IDUtil;
import com.dzb.wm.entity.InvestorEntity;
import com.dzb.wm.entity.LabelEntity;
import com.dzb.wm.entity.ProductEntity;
import com.dzb.wm.mapper.ProductMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public PageInfo<ProductEntity> getPagination(Integer offset, Integer limit, ProductEntity productEntity) {

        log.debug("查询信息列表");
        PageHelper.offsetPage(offset, limit);
        PageInfo<ProductEntity> pi = new PageInfo<>(productMapper.selectPagination(productEntity));
        List<ProductEntity> list = pi.getList();
        list = baseTypeService.translate(list, "CPLX", "belongPageType", "belongPageTypeName");
        pi.setList(list);
        return pi;
    }

    public int insert(ProductEntity productEntity) {
        log.debug("新增信息");
        productEntity.setProductId(IDUtil.getSnowflakeId());
        productEntity.setUpdateUserid(UserInfoUtil.getUserId());
        productEntity.setUpdateTime(new Date());
        return productMapper.insert(productEntity);
    }

    public int del(long productId) {
        return productMapper.del(productId);
    }

    public ProductEntity getByPkey(long productId) {
        ProductEntity productEntity = productMapper.getByPkey(productId);
        productEntity = baseTypeService.translate(productEntity, "CPLX", "belongPageType", "belongPageTypeName");
        return productEntity;
    }

    public int update(ProductEntity productEntity) {
        productEntity.setUpdateUserid(UserInfoUtil.getUserId());
        productEntity.setUpdateTime(new Date());
        return productMapper.update(productEntity);
    }

    public List<Map> getProductsByLabelId(Integer belongPageType, Long labelId) {
        List<ProductEntity> procuts = productMapper.getProductsByLabelId(belongPageType,labelId);
        List<Map> list = new ArrayList<>();
        for (ProductEntity productEntity : procuts) {
            Map<String, Object> labelMap = new HashMap<String, Object>();
            labelMap.put("value",productEntity.getProductId());
            labelMap.put("label",productEntity.getTitle());
            labelMap.put("selected",productEntity.getSelected());
            list.add(labelMap);
        }
        return list;
    }
    public List<Map> getProductsByDropDownBoxId(Integer belongPageType, Long dropDownBoxId) {
        List<ProductEntity> procuts = productMapper.getProductsByDropDownBoxId(belongPageType,dropDownBoxId);
        List<Map> list = new ArrayList<>();
        for (ProductEntity productEntity : procuts) {
            Map<String, Object> labelMap = new HashMap<String, Object>();
            labelMap.put("value",productEntity.getProductId());
            labelMap.put("label",productEntity.getTitle());
            labelMap.put("selected",productEntity.getSelected());
            list.add(labelMap);
        }
        return list;
    }

    public int updateState(Long productId,Integer mark) {
        ProductEntity productEntity = productMapper.getByPkey(productId);
        if (productEntity!=null){
            if(mark==1){
                productEntity.setPushTime(new Date());
            }else {
                productEntity.setPushTime(null);
            }
        }
        return productMapper.updateState(productEntity);
    }

}

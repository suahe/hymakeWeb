package com.dzb.wm.service;

import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.service.BaseTypeService;
import com.dzb.console.util.IDUtil;
import com.dzb.wm.entity.*;
import com.dzb.wm.mapper.DropDownBoxMapper;
import com.dzb.wm.mapper.LabelMapper;
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
public class LabelService {

    private static final Logger log = LoggerFactory.getLogger(LabelService.class);

    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private BaseTypeService baseTypeService;

    public PageInfo<LabelEntity> getPagination(Integer offset, Integer limit, LabelEntity labelEntity) {

        log.debug("查询信息列表");
        PageHelper.offsetPage(offset, limit);
        PageInfo<LabelEntity> pi = new PageInfo<>(labelMapper.selectPagination(labelEntity));
        List<LabelEntity> list = pi.getList();
        list = baseTypeService.translate(list, "CPLX", "belongPageType", "belongPageTypeName");
        for (LabelEntity entity : list) {
            String prodcutNames = labelMapper.getProdcutNames(entity.getLabelId());
            entity.setProductNames(prodcutNames);
        }
        pi.setList(list);
        return pi;
    }

    public int insert(LabelEntity labelEntity) {
        log.debug("新增信息");
        labelEntity.setLabelId(IDUtil.getSnowflakeId());
        labelEntity.setUpdateUserid(UserInfoUtil.getUserId());
        labelEntity.setUpdateTime(new Date());
        int i = labelMapper.insert(labelEntity);
        if (i>0){
            List<LabelProdcutEntity> labelProdcutEntitieList = getLabelProdcutEntitieList(labelEntity);
            labelMapper.batchInsertSelectedProcut(labelProdcutEntitieList);
        }
        return i;
    }

    public int del(long labelId) {
        labelMapper.delSelectedProduct(labelId);
        return labelMapper.del(labelId);
    }

    public LabelEntity getByPkey(long labelId) {
        LabelEntity labelEntity = labelMapper.getByPkey(labelId);
        labelEntity = baseTypeService.translate(labelEntity, "CPLX", "belongPageType", "belongPageTypeName");
        return labelEntity;
    }

    public int update(LabelEntity labelEntity) {
        labelEntity.setUpdateUserid(UserInfoUtil.getUserId());
        labelEntity.setUpdateTime(new Date());
        int i = labelMapper.update(labelEntity);
        if (i>0){
            labelMapper.delSelectedProduct(labelEntity.getLabelId());
            List<LabelProdcutEntity> labelProdcutEntitieList = getLabelProdcutEntitieList(labelEntity);
            labelMapper.batchInsertSelectedProcut(labelProdcutEntitieList);
        }
        return i;
    }

    public List<Map> getLabels(Integer belongPageType,Long dropDownBoxId) {
        List<LabelEntity> labels = labelMapper.getLabels(belongPageType,dropDownBoxId);
        List<Map> list = new ArrayList<>();
        for (LabelEntity label : labels) {
            Map<String, Object> labelMap = new HashMap<String, Object>();
            labelMap.put("value",label.getLabelId());
            labelMap.put("label",label.getName());
            labelMap.put("selected",label.getSelected());
            list.add(labelMap);
        }
        return list;
    }

    public int updateState(Long labelId,Integer mark) {
        LabelEntity labelEntity = labelMapper.getByPkey(labelId);
        if (labelEntity!=null){
            if(mark==1){
                labelEntity.setPushTime(new Date());
            }else {
                labelEntity.setPushTime(null);
            }
        }
        return labelMapper.updateState(labelEntity);
    }

    /**
     * 封装中间表数据
     */
    private List<LabelProdcutEntity> getLabelProdcutEntitieList(LabelEntity labelEntity){
        List<Long> productIds = labelEntity.getProductIds();
        List<LabelProdcutEntity> labelProdcutEntitieList = new ArrayList<>();
        for (Long productId : productIds) {
            LabelProdcutEntity labelProdcutEntity = new LabelProdcutEntity();
            labelProdcutEntity.setId(IDUtil.getSnowflakeId());
            labelProdcutEntity.setLabelId(labelEntity.getLabelId());
            labelProdcutEntity.setProductId(productId);
            labelProdcutEntitieList.add(labelProdcutEntity);
        }
        return labelProdcutEntitieList;
    }
}

package com.dzb.wm.service;

import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.service.BaseTypeService;
import com.dzb.console.util.IDUtil;
import com.dzb.wm.entity.DropDownBoxEntity;
import com.dzb.wm.entity.DropDownBoxLabelEntity;
import com.dzb.wm.entity.DropDownBoxProductEntity;
import com.dzb.wm.entity.LabelEntity;
import com.dzb.wm.mapper.DropDownBoxMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private BaseTypeService baseTypeService;

    public PageInfo<DropDownBoxEntity> getPagination(Integer offset, Integer limit, DropDownBoxEntity dropDownBoxEntity) {

        log.debug("查询信息列表");
        PageHelper.offsetPage(offset, limit);
        PageInfo<DropDownBoxEntity> pi = new PageInfo<>(dropDownBoxMapper.selectPagination(dropDownBoxEntity));
        List<DropDownBoxEntity> list = pi.getList();
        list = baseTypeService.translate(list, "CPLX", "belongPageType", "belongPageTypeName");
        for (DropDownBoxEntity entity : list) {
            String labelNames = dropDownBoxMapper.getLabelNames(entity.getDropDownBoxId());
            entity.setLabelNames(labelNames);
            String productNames = dropDownBoxMapper.getProductNames(entity.getDropDownBoxId());
            entity.setProductNames(productNames);
        }
        pi.setList(list);
        return pi;
    }

    public int insert(DropDownBoxEntity dropDownBoxEntity) {
        log.debug("新增信息");
        dropDownBoxEntity.setDropDownBoxId(IDUtil.getSnowflakeId());
        dropDownBoxEntity.setUpdateUserid(UserInfoUtil.getUserId());
        dropDownBoxEntity.setUpdateTime(new Date());
        int i = dropDownBoxMapper.insert(dropDownBoxEntity);
        if (i>0){
            List<DropDownBoxLabelEntity> dropDownBoxLabelEntityList = getDropDownBoxLabelEntityList(dropDownBoxEntity);
            dropDownBoxMapper.batchInsertSelectedLabel(dropDownBoxLabelEntityList);
            List<DropDownBoxProductEntity> dropDownBoxProductEntityList = getDropDownBoxProductEntityList(dropDownBoxEntity);
            dropDownBoxMapper.batchInsertSelectedProduct(dropDownBoxProductEntityList);
        }
        return i;
    }

    public int del(long dropDownBoxId) {
        dropDownBoxMapper.delSelectedLabel(dropDownBoxId);
        return dropDownBoxMapper.del(dropDownBoxId);
    }

    public DropDownBoxEntity getByPkey(long dropDownBoxId) {
        DropDownBoxEntity dropDownBoxEntity = dropDownBoxMapper.getByPkey(dropDownBoxId);
        dropDownBoxEntity = baseTypeService.translate(dropDownBoxEntity, "CPLX", "belongPageType", "belongPageTypeName");
        return dropDownBoxEntity;
    }

    public int update(DropDownBoxEntity dropDownBoxEntity) {
        dropDownBoxEntity.setUpdateUserid(UserInfoUtil.getUserId());
        dropDownBoxEntity.setUpdateTime(new Date());
        int i = dropDownBoxMapper.update(dropDownBoxEntity);
        if(i>0){
            dropDownBoxMapper.delSelectedLabel(dropDownBoxEntity.getDropDownBoxId());
            List<DropDownBoxLabelEntity> dropDownBoxLabelEntityList = getDropDownBoxLabelEntityList(dropDownBoxEntity);
            dropDownBoxMapper.batchInsertSelectedLabel(dropDownBoxLabelEntityList);
            dropDownBoxMapper.delSelectProduct(dropDownBoxEntity.getDropDownBoxId());
            List<DropDownBoxProductEntity> dropDownBoxProductEntityList = getDropDownBoxProductEntityList(dropDownBoxEntity);
            dropDownBoxMapper.batchInsertSelectedProduct(dropDownBoxProductEntityList);
        }
        return i;
    }

    public int updateState(Long dropDownBoxId,Integer mark) {
        DropDownBoxEntity dropDownBoxEntity = dropDownBoxMapper.getByPkey(dropDownBoxId);
        if (dropDownBoxEntity!=null){
            if(mark==1){
                dropDownBoxEntity.setPushTime(new Date());
            }else {
                dropDownBoxEntity.setPushTime(null);
            }
        }
        return dropDownBoxMapper.updateState(dropDownBoxEntity);
    }

    /**
     * 封装中间表数据
     */
    private List<DropDownBoxLabelEntity> getDropDownBoxLabelEntityList(DropDownBoxEntity dropDownBoxEntity){
        List<Long> labelIds = dropDownBoxEntity.getLabelIds();
        ArrayList<DropDownBoxLabelEntity> dropDownBoxLabelEntitiyList = new ArrayList<>();
        for (Long labelId : labelIds) {
            DropDownBoxLabelEntity dropDownBoxLabelEntity = new DropDownBoxLabelEntity();
            dropDownBoxLabelEntity.setId(IDUtil.getSnowflakeId());
            dropDownBoxLabelEntity.setDropDownBoxId(dropDownBoxEntity.getDropDownBoxId());
            dropDownBoxLabelEntity.setLabelId(labelId);
            dropDownBoxLabelEntitiyList.add(dropDownBoxLabelEntity);
        }
        return dropDownBoxLabelEntitiyList;
    }

    /**
     * 封装中间表数据
     */
    private List<DropDownBoxProductEntity> getDropDownBoxProductEntityList(DropDownBoxEntity dropDownBoxEntity){
        List<Long> productIds = dropDownBoxEntity.getProductIds();
        ArrayList<DropDownBoxProductEntity> dropDownBoxProductEntitieList = new ArrayList<>();
        for (Long productId : productIds) {
            DropDownBoxProductEntity dropDownBoxProductEntity = new DropDownBoxProductEntity();
            dropDownBoxProductEntity.setId(IDUtil.getSnowflakeId());
            dropDownBoxProductEntity.setDropDownBoxId(dropDownBoxEntity.getDropDownBoxId());
            dropDownBoxProductEntity.setProductId(productId);
            dropDownBoxProductEntitieList.add(dropDownBoxProductEntity);
        }
        return dropDownBoxProductEntitieList;
    }
}

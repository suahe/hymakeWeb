package com.hymake.mobileWeb.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;
import java.util.List;

/**
 * @author suahe
 * @date 2020/11/4 9:43
 * @describe 下拉框分类实体
 */
public class DropDownBoxEntity {

    @JsonSerialize(using= ToStringSerializer.class)
    private long dropDownBoxId;

    //所属页面类型(1 产品体系 2解决方案)
    private String belongPageType;

    //所属页面名称
    private String belongPageTypeName;

    //名称
    private String name;

    //排序
    private int sequ;

    //发布时间
    private Date pushTime;

    //更新人
    private String updateUserid;

    //更新时间
    private Date updateTime;

    //标签集合
    private List<Long> labelIds;

    //选择的标签名称集合
    private String labelNames;

    public long getDropDownBoxId() {
        return dropDownBoxId;
    }

    public void setDropDownBoxId(long dropDownBoxId) {
        this.dropDownBoxId = dropDownBoxId;
    }

    public String getBelongPageType() {
        return belongPageType;
    }

    public void setBelongPageType(String belongPageType) {
        this.belongPageType = belongPageType;
    }

    public String getBelongPageTypeName() {
        return belongPageTypeName;
    }

    public void setBelongPageTypeName(String belongPageTypeName) {
        this.belongPageTypeName = belongPageTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequ() {
        return sequ;
    }

    public void setSequ(int sequ) {
        this.sequ = sequ;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Long> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<Long> labelIds) {
        this.labelIds = labelIds;
    }

    public String getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(String labelNames) {
        this.labelNames = labelNames;
    }
}

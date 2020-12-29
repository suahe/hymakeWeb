package com.hymake.mobileWeb.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 * @author suahe
 * @date 2020/10/28 16:35
 * @describe 产品实体
 */
public class ProductEntity {

    @JsonSerialize(using= ToStringSerializer.class)
    private long productId;

    //1 产品体系；2 解决方案
    private String belongPageType;

    //类型名称
    private String belongPageTypeName;

    //标题
    private String title;

    //内容
    private String content;

    //图标路径
    private String iconPath;

    //跳转路径
    private String url;

    //排序
    private int sequ;

    //发布时间
    private Date pushTime;

    //更新人
    private String updateUserid;

    //更新时间
    private Date updateTime;

    //是否被选择
    private Boolean selected;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}

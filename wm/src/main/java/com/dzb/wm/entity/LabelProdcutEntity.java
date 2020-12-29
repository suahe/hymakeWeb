package com.dzb.wm.entity;

/**
 * @author suahe
 * @date 2020/11/13 10:12
 * @describe 标签分类和产品中间实体
 */
public class LabelProdcutEntity {

    private Long id;

    private Long labelId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

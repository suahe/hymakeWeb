package com.dzb.wm.entity;

/**
 * @author suahe
 * @date 2020/11/13 9:35
 * @describe 下拉框分类和产品中间实体
 */
public class DropDownBoxProductEntity {

    private Long id;

    //下拉框分类id
    private Long dropDownBoxId;
    //标签id
    private Long productId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDropDownBoxId() {
        return dropDownBoxId;
    }

    public void setDropDownBoxId(Long dropDownBoxId) {
        this.dropDownBoxId = dropDownBoxId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

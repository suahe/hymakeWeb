package com.dzb.wm.entity;

/**
 * @author suahe
 * @date 2020/11/13 9:35
 * @describe 下拉框分类和标签分类中间实体
 */
public class DropDownBoxLabelEntity {

    private Long id;

    //下拉框分类id
    private Long dropDownBoxId;
    //标签id
    private Long labelId;


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

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}

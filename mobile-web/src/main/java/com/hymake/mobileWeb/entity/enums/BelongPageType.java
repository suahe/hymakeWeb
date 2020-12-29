package com.hymake.mobileWeb.entity.enums;

/**
 * @author suahe
 * @date 2020/11/3 11:13
 * @describe 所属页面类型
 */
public enum BelongPageType {

    PRODUCT_SYSTEM(1,"产品体系"),

    SOLUTION(2,"解决方案");

    private int value;
    private String name;

    private BelongPageType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }

    public static String getNameByValue(int value) {
        for (BelongPageType productDownClsType: BelongPageType.values()){
            if (productDownClsType.value==value){
                return productDownClsType.getName();
            }
        }
        return "";
    }

    public boolean isType(int value) {
        if (this.value==value){
            return true;
        }
        return false;
    }

}

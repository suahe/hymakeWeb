package com.hymake.mobileWeb.entity;

import com.hymake.mobileWeb.entity.ChronologyEntity;

import java.util.List;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/15 15:19.
 * @Description: ChronologyEntity按年份分组
 */
public class ChronologyGroup {

    /**
     * 年份
     */
    private String year;

    /**
     * ChronologyEntity实体集合
     */
    private List<ChronologyEntity> chronologyEntityList;

    public ChronologyGroup() {
    }

    public ChronologyGroup(String year, List<ChronologyEntity> chronologyEntityList) {
        this.year = year;
        this.chronologyEntityList = chronologyEntityList;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<ChronologyEntity> getChronologyEntityList() {
        return chronologyEntityList;
    }

    public void setChronologyEntityList(List<ChronologyEntity> chronologyEntityList) {
        this.chronologyEntityList = chronologyEntityList;
    }
}

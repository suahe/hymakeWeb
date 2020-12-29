package com.hymake.mobileWeb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.ChronologyEntity;
import com.hymake.mobileWeb.entity.ChronologyGroup;
import com.hymake.mobileWeb.mapper.ChronologyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/15 14:26.
 * @Description: t_chronology 表Service类
 */

@Service("ChronologyService")
public class ChronologyService {

    private static final Logger log = LoggerFactory.getLogger(ChronologyService.class);

    @Autowired
    private ChronologyMapper chronologyMapper;

    /**
     * 查询信息列表
     */
    public PageInfo<ChronologyEntity> getPagination(Integer offset, Integer limit, ChronologyEntity chronologyEntity) {

        log.debug("查询信息列表");
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<ChronologyEntity>(chronologyMapper.selectPagination(chronologyEntity));
    }

    /**
     * 通过主键查询信息
     */
    public ChronologyEntity getByPkey(long chronologyId) {

        log.debug("通过主键查询信息");
        return chronologyMapper.selectByPkey(chronologyId);
    }

    /**
     * 查询所有信息列表
     */
    public List<ChronologyEntity> getAll() {
        return chronologyMapper.selectAll();
    }

    /**
     * 根据年份分组并对集合进行排序
     */
    public List<ChronologyGroup> getYearGroupList(List<ChronologyEntity> ChronologyEntityList) {
        List<ChronologyGroup> chronologyGroupList = ChronologyEntityList.stream()
                .collect(Collectors.groupingBy((chronologyEntity) -> {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(chronologyEntity.getOccurrenceTime());
                            return calendar.get(Calendar.YEAR);
                        }
                ))
                .entrySet()
                .stream()
                .map(e -> new ChronologyGroup(String.valueOf(e.getKey()), e.getValue()))
                .collect(Collectors.toList());
        //对年份进行排序
        chronologyGroupList.sort((a, b) -> Integer.valueOf(b.getYear()) - Integer.valueOf(a.getYear()));
        //对年中的月日进行排序
        chronologyGroupList.forEach(chronologyGroup -> {
            chronologyGroup.getChronologyEntityList().sort((a, b) -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                String aOccurrenceTime = simpleDateFormat.format(a.getOccurrenceTime());
                String bOccurrenceTime = simpleDateFormat.format(b.getOccurrenceTime());
                return  Integer.valueOf(bOccurrenceTime) - Integer.valueOf(aOccurrenceTime);
            });
        });
        return chronologyGroupList;
    }

    public List<ChronologyEntity> getByYear(String year){
        return chronologyMapper.getByYear(year);
    }
    public List<String> getYears(){
        return chronologyMapper.getYears();
    }
}
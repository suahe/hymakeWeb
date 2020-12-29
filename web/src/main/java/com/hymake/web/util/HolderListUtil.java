package com.hymake.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: suahe.
 * @Date:Created in 2020/7/6 10:45.
 * @Description: 处理list工具类
 */
public class HolderListUtil {

    /**
     * 按maxNumber大小对list进行切割
     */
    public static List separateListHolder(List list, int maxNumber) {
        List newList = new ArrayList();
        if (list==null||list.size()==0){
            return newList;
        }
        int limit = (list.size()+maxNumber-1)/maxNumber;
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            newList.add(list.stream().skip(i * maxNumber).limit(maxNumber).collect(Collectors.toList()));
        });
        return newList;
    }
}

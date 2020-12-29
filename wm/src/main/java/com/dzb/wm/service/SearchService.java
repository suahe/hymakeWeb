package com.dzb.wm.service;

import com.dzb.console.security.UserInfoUtil;
import com.dzb.console.util.IDUtil;
import com.dzb.wm.entity.SearchEntity;
import com.dzb.wm.mapper.SearchMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author suahe
 * @date 2020/10/28 16:39
 * @describe TODO
 */
@Service("SearchService")
public class SearchService {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private SearchMapper searchMapper;

    /**
     * @Description 查询信息列表
     */
    public PageInfo<SearchEntity> getPagination(Integer offset, Integer limit, SearchEntity searchEntity) {

        log.debug("查询信息列表");
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<SearchEntity>(searchMapper.selectPagination(searchEntity));
    }

    /**
     * @Description 通过主键查询信息
     */
    public SearchEntity getByPkey(long id){

        log.debug("通过主键查询信息");
        return searchMapper.selectByPkey(id);
    }

    /**
     * @Description 新增信息
     */
    public int insert(SearchEntity searchEntity) {

        log.debug("新增信息");
        searchEntity.setId(IDUtil.getSnowflakeId());
        searchEntity.setCreateUserid(UserInfoUtil.getUserId());
        searchEntity.setCreateTime(new Date());
        return searchMapper.insert(searchEntity);
    }

    /**
     * @Description 编辑信息
     */
    public int update(SearchEntity searchEntity){

        log.debug("编辑信息");
        searchEntity.setCreateUserid(UserInfoUtil.getUserId());
        searchEntity.setUpdateTime(new Date());
        return searchMapper.update(searchEntity);
    }

    /**
     * @Description 删除信息
     */
    public int delete(long id){

        log.debug("删除信息");
        return searchMapper.delete(id);
    }
}

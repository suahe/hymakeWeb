package com.hymake.web.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hymake.web.entity.InvestorEntity;
import com.hymake.web.mapper.InvestorMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("InvestorService")
@Transactional
public class InvestorService {
    private static final Logger log = LoggerFactory.getLogger(InvestorService.class);
    @Autowired
    private InvestorMapper investorMapper;
    @Autowired
    private BaseTypeService baseTypeService;

    public InvestorService() {
    }

    public PageInfo<InvestorEntity> getPagination(Integer offset, Integer limit, String type) {
        log.debug("查询信息列表");
        PageHelper.offsetPage(offset, limit);
        InvestorEntity investorEntity = new InvestorEntity();
        investorEntity.setType(type);
        PageInfo<InvestorEntity> pi = new PageInfo(this.investorMapper.selectPagination(investorEntity));
        List<InvestorEntity> list = pi.getList();
        list = this.baseTypeService.translate(list, "TZZGXGG", "type", "typeName");
        pi.setList(list);
        return pi;
    }
}


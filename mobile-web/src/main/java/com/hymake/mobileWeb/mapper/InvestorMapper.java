package com.hymake.mobileWeb.mapper;

import com.hymake.mobileWeb.entity.InvestorEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvestorMapper {

    List<InvestorEntity> selectPagination(InvestorEntity investorEntity);

    InvestorEntity selectByPkey(long investorId);

    int insert(InvestorEntity investorEntity);

    int update(InvestorEntity investorEntity);

    int delete(long investorId);

    int updatePushTimeByInvestorId(InvestorEntity investorEntity);
}

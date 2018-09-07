package com.panda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "overviewMapper")
public interface OverviewMapper {
    @Select("select turnover from pe_overview where todaydate=#{today}")
    Integer selectTurnover(String today);
}

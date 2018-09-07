package com.panda.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "overviewMapper")
public interface OverviewMapper {
    @Select("select turnover from cms_overview where todaydate=#{todaydate}")
    String selectTurnover(@Param("todaydate") String todaydate);

    @Insert("insert into cms_overview (storeid,todaydate,turnover,finaldate) " +
            "values(#{storeid},#{todaydate},#{turnover},#{finaldate})")
    void insertOverview(@Param("storeid")Integer storeid,@Param("todaydate")String todaydate,
                @Param("turnover")Integer turnover,@Param("finaldate")String Finaldate);

    @Update("update turnover,finaldate from cms_overview where todaydate=#{todaydate},storeid=#{storeid}")
    void updateOverview(@Param("storeid")Integer storeid,@Param("todaydate")String todaydate);
}

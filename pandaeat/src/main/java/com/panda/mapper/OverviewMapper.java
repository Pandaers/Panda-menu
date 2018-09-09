package com.panda.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "overviewMapper")
public interface OverviewMapper {
    /*
    * 查询今日营业额
    * */
    @Select("select turnover from cms_overview where todaydate=#{todaydate} limit 1")
    Integer selectTurnover(@Param("todaydate") String todaydate);
    /*
    * 插入今日统计数据
    * */
    @Insert("insert into cms_overview (storeid,todaydate,turnover,finaldate) " +
            "values(#{storeid},#{todaydate},#{turnover},#{finaldate})")
    void insertOverview(@Param("storeid")Integer storeid,@Param("todaydate")String todaydate,
                @Param("turnover")Integer turnover,@Param("finaldate")String Finaldate);
    /*
    * 更新今日营业额数据
    * */
    @Update("update cms_overview set turnover=turnover+#{turnover},finaldate=#{finaldate}  " +
            " where todaydate=#{todaydate} and storeid=#{storeid}")
    void updateOverview(@Param("storeid")Integer storeid,@Param("todaydate")String todaydate,
                        @Param("turnover")Integer turnover,@Param("finaldate")String Finaldate);

    @Select("select count(*) from cms_overview where storeid=#{storeid} and todaydate=#{todaydate}")
    Integer countOverviewExist(@Param("storeid")Integer storeid,@Param("todaydate")String todaydate);

}

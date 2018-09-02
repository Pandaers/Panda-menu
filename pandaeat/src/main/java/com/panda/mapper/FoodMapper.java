package com.panda.mapper;

import com.panda.model.Food;
import com.panda.model.FoodForCMS;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "foodMapper")
public interface FoodMapper {
    @Select("select * from pe_food where storeid=${storeid} and status=1")
    List<Food> selectFoodDateByStoreId(@Param("storeid") String storeid);

    @Select("select * from pe_food where status=1")
    List<Food> selectFoodAllData();

    @Select("select id,storeid,name,price,detail,catid,status,compressimg,realsales from pe_food where isdelete=0 " +
            "and status=1 and storeid=#{storeid} order by addtime desc")
    List<FoodForCMS> selectFoodCMSList(Integer storeid);

    @Select("select * from pe_food where isdelete=0 and status=1 and storeid=#{storeid} and id=#{id} limit 1")
    Food selectSingleFood(Integer storeid,Integer id);
/*private Integer id;
    private Integer storeid;
    private String name;
    private BigDecimal price;
    private BigDecimal originalprice;
    private String detail;
    private Integer catid;
    private Integer status;
    private Integer addtime;
    private Integer score;
    private Integer isdelete;
    private Integer virtualsales;
    private String compressimg;
    private String img;
    private String videourl;
    private Integer realsales;
    private Integer goodsnums;
    private String catname;*/
    @Insert("insert into pe_food (storeid,name,price,originalprice,detail,catid,addtime,score," +
            "virtualsales,compressimg,img,videourl,realsales,goodsnums) value(#{food.storeid},#{food.name},#{food.price}," +
            "#{food.originalprice},#{food.detail},#{food.catid},#{food.addtime},#{food.score},#{food.virtualsales}," +
            "#{food.compressimg},#{food.img},#{food.videourl},#{food.realsales},#{food.goodsnums})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void insertFood(@Param("food")Food food);
}

package com.panda.mapper;

import com.panda.model.Food;
import com.panda.model.FoodForCMS;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "foodMapper")
public interface FoodMapper {
    /*
    * 返回该饭店的所有菜品详情
    * */
    @Select("select * from pe_food where storeid=${storeid} and status=1 and isdelete=0")
    List<Food> selectFoodDateByStoreId(@Param("storeid") String storeid);
    /*
    * 返回该饭店的所有菜品详情
    * */
    @Select("select * from pe_food where status=1 and isdelete=0")
    List<Food> selectFoodAllData();
    /*
     * 返回该饭店的所有菜品部分详情CMS
     * */
    @Select("select id,storeid,name,price,detail,catid,status,compressimg,realsales from pe_food where isdelete=0 " +
            "and status=1 and storeid=#{storeid} order by addtime desc")
    List<FoodForCMS> selectFoodCMSList(@Param("storeid") Integer storeid);
    /*
     * 返回该饭店单个菜品全部详情
     * */
    @Select("select * from pe_food where isdelete=0 and status=1 and storeid=#{storeid} and id=#{id} limit 1")
    Food selectSingleFood(@Param("storeid") Integer storeid,@Param("id") Integer id);
    /*
     * 插入新菜品
     * */
    @Insert("insert into pe_food (storeid,name,price,originalprice,detail,catid,addtime,score," +
            "virtualsales,compressimg,img,videourl,realsales,goodsnums) value(#{food.storeid},#{food.name},#{food.price}," +
            "#{food.originalprice},#{food.detail},#{food.catid},#{food.addtime},#{food.score},#{food.virtualsales}," +
            "#{food.compressimg},#{food.img},#{food.videourl},#{food.realsales},#{food.goodsnums})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void insertFood(@Param("food")Food food);
    /*
     * 更新单个菜品
     * */
    @Update("update pe_food set name=#{food.name},price=#{food.price},originalprice=#{food.originalprice}," +
            "detail=#{food.detail},catid=#{food.catid},status=#{food.status},score=#{food.score}," +
            "virtualsales=#{food.virtualsales},compressimg=#{food.compressimg}," +
            "img=#{food.img},videourl=#{food.videourl},goodsnums=#{food.goodsnums} where id=#{food.id})")
    void updateFood(@Param("food")Food food);
    /*
    * 模糊搜索，条件：菜名or菜品详情
    * */
    @Select("select id,storeid,name,price,detail,catid,status,compressimg,realsales from pe_food where isdelete=0 " +
            "and status=1 and storeid=#{storeid} and (name like #{searchString} or detail like #{searchString}) order by addtime desc")
    List<FoodForCMS> selectFoodCMSListLikeString(@Param("storeid")Integer storeid,@Param("searchString") String searchString);

}

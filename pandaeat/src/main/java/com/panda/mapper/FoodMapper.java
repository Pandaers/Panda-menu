package com.panda.mapper;

import com.panda.model.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "foodMapper")
public interface FoodMapper {
    @Select("select * from pe_food where storeid='#{storeid}' and `status`=1")
    List<Food> selectFoodDateByStoreId(String storeid);

}

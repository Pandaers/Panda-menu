package com.panda.service.getFoodNameMap;

import com.panda.mapper.OrderMapper;
import com.panda.model.FoodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetFoodNameMapmpl implements GetFoodNameMap{
    @Autowired
    OrderMapper orderMapper;
    public Map<Integer,String> getFoodNameMap(Integer storeid){
        Map<Integer, String> Foodmap = orderMapper.foodNameReform(storeid).stream().collect(Collectors.toMap(FoodName::getId, FoodName::getName));
        return Foodmap;
    }

}

package com.panda.service.foodNameReform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.panda.mapper.OrderMapper;
import com.panda.service.getFoodNameMap.GetFoodNameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodNameReformlmpl implements FoodNameReform{
    @Autowired
    GetFoodNameMap getFoodNameMap;

    public String foodNameReform(String intFoodName,Integer storeid){
        JSONObject jsonObject = JSONObject.parseObject(intFoodName);
        Map<String,Object> tempmap = (Map<String,Object>)jsonObject;

        Map<Integer,String> foodNameMap=getFoodNameMap.getFoodNameMap(storeid);
        Map<String,Object> resultMap= new HashMap();

        for(Map.Entry<String, Object> entry : tempmap.entrySet()){
            resultMap.put(foodNameMap.get(Integer.parseInt(entry.getKey())),entry.getValue());
        }
        return JSON.toJSONString(resultMap);
    }
}

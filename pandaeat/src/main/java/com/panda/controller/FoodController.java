package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.FoodMapper;
import com.panda.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FoodController {
    @Autowired
    FoodMapper foodMapper;
    /*
    *这是个啥
    * */
    @RequestMapping(value = "/catMap")
    public ResponseEntity getcatMap(){
        List<Food> foodList=foodMapper.selectFoodAllData();

        Map result = new HashMap();
        Map goodsMap = new HashMap();
        for(Food list:foodList){
            Map goodsTempMap = new HashMap();
            goodsTempMap.put("price",list.getPrice());
            goodsTempMap.put("name",list.getName());
            goodsTempMap.put("id",list.getId());
            goodsMap.put(list.getId(),goodsTempMap);
        }
        result.put("goods_map",goodsMap);
        return new ResponseEntity(RespCode.SUCCESS,result);
    }
}

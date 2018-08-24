package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.CatMapper;
import com.panda.mapper.FoodMapper;
import com.panda.mapper.StoreMapper;
import com.panda.model.Cat;
import com.panda.model.Food;
import com.panda.model.Store;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/*
* 商家菜单展示
* */
@RestController
public class MenuController {
    //接收参数
    String storeid;

    //商家、菜品注入
    FoodMapper foodMapper;
    StoreMapper storeMapper;
    CatMapper catMapper;

    @RequestMapping(value = "/menu")
    public ResponseEntity insertUser(String storeid){

        List<Food> foodList=foodMapper.selectFoodDateByStoreId(storeid);
        Store store =storeMapper.selcetStoreDateByStoreId(storeid);

        Map result = new HashMap();

        //菜品类别的int转name对应map
        Map<Integer, String> catmap = catMapper.selectAllCatDate().stream().collect(Collectors.toMap(Cat::getCatid, Cat::getCatname));

        result.put("nickname",store.getNickname());
        result.put("avatar",store.getAvatar());
        result.put("mobile",store.getMobile());
        result.put("address",store.getAddress());
        result.put("selltime",store.getSelltime());
        result.put("foodscore",store.getFoodscore());
        result.put("severscore",store.getServerscore());
        result.put("avescore",store.getAvescore());
        result.put("notice",store.getNotice());
        Map<Integer, List<Food>> groupBy = foodList.stream().collect(Collectors.groupingBy(Food::getCatid));
        for(Map.Entry<Integer, List<Food>> entry:groupBy.entrySet()){
            Map goupMap = new HashMap();
            goupMap.put("catname",catmap.get(entry.getKey()));
            Map goupTempMap = new HashMap();
            List<Food> innerFoodList=entry.getValue();
            for(Food list:innerFoodList){
                goupTempMap.put("id",list.getId());
                goupTempMap.put("avatar",list.getCompressimg());
                goupTempMap.put("name",list.getName());
                goupTempMap.put("virtualsales",list.getVirtualsales());
                goupTempMap.put("price",list.getPrice());
                goupTempMap.put("goodsnums",list.getGoodsnums());
            }
            goupMap.put("foods",innerFoodList);
            result.put("menus",goupMap);
            System.out.println("key=" +entry.getKey() +" and value="+entry.getValue());
        }
        return new ResponseEntity(RespCode.SUCCESS,result);
    }
    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }
}

package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.FoodMapper;
import com.panda.mapper.StoreMapper;
import com.panda.model.Food;
import com.panda.model.Store;
import com.panda.service.getCatMap.GetCatMap;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    FoodMapper foodMapper;
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    GetCatMap getCatMap;

    @RequestMapping(value = "/menu")
    public ResponseEntity getfoodmenu(String storeid){

        List<Food> foodList=foodMapper.selectFoodDateByStoreId(storeid);
        Store store =storeMapper.selcetStoreDateByStoreId(storeid);
        //要返回的result
        Map result = new HashMap();

        //得到菜品名int转string对应map
        Map<Integer, String> catmap = getCatMap.getCatMap(Integer.parseInt(storeid));

        result.put("nickname",store.getNickname());
        result.put("avatar",store.getAvatar());
        result.put("mobile",store.getMobile());
        result.put("address",store.getAddress());
        result.put("selltime",store.getSelltime());
        result.put("foodscore",store.getFoodscore());
        result.put("severscore",store.getSeverscore());
        result.put("avescore",store.getAvescore());
        result.put("notice",store.getNotice());

        //按菜品类别进行分组，返回map的key=商品类别值，value=同商品类别的商品list
        Map<Integer, List<Food>> groupBy = foodList.stream().collect(Collectors.groupingBy(Food::getCatid));
        List goupMapList =new ArrayList();

        //对分组map遍历
        for(Map.Entry<Integer, List<Food>> entry:groupBy.entrySet()){
            Map goupMap = new HashMap();
            goupMap.put("catname",catmap.get(entry.getKey()));

            List<Food> innerFoodList=entry.getValue();
            List goupTempMapList =new ArrayList();
            //对每个小组内list进行遍历，并插入到tempMap中
            for(Food list:innerFoodList){
                Map goupTempMap = new HashMap();
                goupTempMap.put("id",list.getId());
                goupTempMap.put("avatar",list.getCompressimg());
                goupTempMap.put("name",list.getName());
                goupTempMap.put("virtualsales",list.getVirtualsales());
                goupTempMap.put("price",list.getPrice());
                goupTempMap.put("goodsnums",list.getGoodsnums());
                goupTempMapList.add(goupTempMap);
            }
            //临时list放入goupMap
            goupMap.put("foods",goupTempMapList);
            goupMapList.add(goupMap);
        }
        result.put("menus",goupMapList);

        return new ResponseEntity(RespCode.SUCCESS,result);
    }
    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

}

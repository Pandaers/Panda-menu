package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.CatMapper;
import com.panda.mapper.FoodMapper;
import com.panda.model.Cat;
import com.panda.model.Food;
import com.panda.model.FoodForCMS;
import com.panda.model.RequestOrder;
import com.panda.service.getCatMap.GetCatMap;
import com.panda.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MenuForCMSController {

    @Autowired
    FoodMapper foodMapper;
    @Autowired
    CatMapper catMapper;
    @Autowired
    GetCatMap getCatMap;
    @Autowired
    TimeUtil timeUtil;
    Integer storeid;
    Integer id;
    /*
    * 返回该饭店所有菜品
    * */
    @RequestMapping(value = "/CMS/MenuList")
    public ResponseEntity selectMenuList(Integer storeid){
        List<FoodForCMS> result =foodMapper.selectFoodCMSList(storeid);
        //得到菜品名int转string对应map
        Map<Integer, String> catmap = getCatMap.getCatMap();
        if(result.size()==0){
            return new ResponseEntity(RespCode.SUCCESS,null);
        }
        for(FoodForCMS i:result){
            i.setCatname(catmap.get(i.getCatid()));
        }
        return new ResponseEntity(RespCode.SUCCESS,result);
    }
    /*
     * 返回所选的菜品详情
     * */
    @RequestMapping(value = "/CMS/SingleMenu")
    public ResponseEntity selectSingleMenu(Integer storeid,Integer id){
        //得到菜品名int转string对应map
        Map<Integer, String> catmap = getCatMap.getCatMap();
        Food result = foodMapper.selectSingleFood(storeid,id);
        if (result==null){
            return new ResponseEntity(RespCode.WRONG);
        }
        result.setCatname(catmap.get(result.getCatid()));
        return new ResponseEntity(RespCode.SUCCESS,result);
    }
    /*
    * ajax传回类别表（CatList）
    * */
    @RequestMapping(value = "/CMS/allCatName")
    public ResponseEntity allCatName(Cat data){
        List<Cat> result=catMapper.selectAllCatData();
        return new ResponseEntity(RespCode.SUCCESS,result);
    }

    @RequestMapping(value = "/CMS/AddMenu")
    public ResponseEntity selectSingleMenu(Food food){
        food.setAddtime(timeUtil.getNowTime());
        foodMapper.insertFood(food);
        return new ResponseEntity(RespCode.SUCCESS);
    }



    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }
}

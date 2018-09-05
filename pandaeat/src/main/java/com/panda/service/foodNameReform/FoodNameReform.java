package com.panda.service.foodNameReform;

import org.springframework.stereotype.Service;

@Service
public interface FoodNameReform {
    /*
    * 功能：接收一个订单，将其中订单内容的菜名id转换成实际菜名，返回该订单
    * */
    String foodNameReform(String intFoodName,Integer storeid);
}

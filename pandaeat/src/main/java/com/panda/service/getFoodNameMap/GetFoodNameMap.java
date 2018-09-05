package com.panda.service.getFoodNameMap;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GetFoodNameMap {
    /*
    * 功能：传入商店id，返回一个食品id与实际食品名的对照Map
    * */
    Map<Integer, String> getFoodNameMap(Integer storeid);
}

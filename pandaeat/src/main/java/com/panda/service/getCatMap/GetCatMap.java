package com.panda.service.getCatMap;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GetCatMap {
    /*
    * 功能：传入商店id，返回一个菜品类别id与实际菜品名的对照Map
    * */
    Map<Integer, String> getCatMap(Integer storeid);
}

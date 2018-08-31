package com.panda.service.getFoodNameMap;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GetFoodNameMap {
    Map<Integer, String> getFoodNameMap(Integer storeid);
}

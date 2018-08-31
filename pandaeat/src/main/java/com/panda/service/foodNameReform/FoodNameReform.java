package com.panda.service.foodNameReform;

import org.springframework.stereotype.Service;

@Service
public interface FoodNameReform {
    String foodNameReform(String intFoodName,Integer storeid);
}

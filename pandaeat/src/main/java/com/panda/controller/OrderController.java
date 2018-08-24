package com.panda.controller;

import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* 订单插入及查询
* */
@RestController
public class OrderController {

    OrderMapper orderMapper;

    @RequestMapping(value = "/order")
    public ResponseEntity insertUser(){

        return ;
    }

}

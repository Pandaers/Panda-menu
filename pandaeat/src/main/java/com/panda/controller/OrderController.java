package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.model.Order;
import com.panda.model.RequestOrder;
import com.panda.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* 订单插入及查询
* */
@RestController
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TimeUtil timeUtil;

    @RequestMapping(value = "/order")
    public ResponseEntity insertUser(RequestOrder data){
        // 需要插入Integer customernum;
        //    short orderstatue;
        Order order;
        try {
            order =new Order(Integer.parseInt(data.getStoreid()),Integer.parseInt(data.getSeatid()),
                    Integer.parseInt(data.getUserid()),timeUtil.getNowTime(),data.getOrdercontent(),
                    data.getOrderprice(),data.getCustomernum(),data.getOrderstatue(),null,data.getPayway());
            orderMapper.insertOrder(order);
        }
        catch (NumberFormatException e){
            System.out.println("数字格式错误");
        }





        return new ResponseEntity(RespCode.SUCCESS,data);
    }

}

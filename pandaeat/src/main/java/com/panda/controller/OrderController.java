package com.panda.controller;

import com.github.pagehelper.PageHelper;
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
            //payway=3&seatid="12"&tips="榴莲味黄瓜"&storeid=“1000”&userid=“1”&ordercontent={"0":6,"1":4}&orderprice="68.00"&customernum=12&orderstatue=1
            order =new Order(Integer.parseInt(data.getStoreid()),Integer.parseInt(data.getSeatid()),
                    Integer.parseInt(data.getUserid()),timeUtil.getNowTime(),data.getOrdercontent(),
                    data.getOrderprice(),data.getCustomernum(),data.getOrderstatue(),null,data.getPayway(),data.getTips());
            orderMapper.insertOrder(order);
        }
        catch (NumberFormatException e){
            System.out.println("数字格式错误");
            return new ResponseEntity(RespCode.WARN);
        }
        return new ResponseEntity(RespCode.SUCCESS);
    }



}

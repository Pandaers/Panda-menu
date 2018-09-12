package com.panda.controller;

import com.github.pagehelper.PageHelper;
import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.model.Order;
import com.panda.model.RequestOrder;
import com.panda.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
* 订单插入及查询
* */
@RestController
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TimeUtil timeUtil;
    /*
    * 插入用户订单数据
    * */
    @Transactional
    @RequestMapping(value = "/order")
    public ResponseEntity insertOrder(RequestOrder data){

        Order order;
        try {
            //payway=3&seatid="12"&tips="榴莲味黄瓜"&storeid=“1000”&userid=“1”&ordercontent={"0":6,"1":4}&orderprice="68.00"&customernum=12&orderstatue=1
            order =new Order(Integer.parseInt(data.getStoreid()),Integer.parseInt(data.getSeatid()),
                    Integer.parseInt(data.getUserid()),timeUtil.getNowTime(),data.getOrdercontent(),
                    data.getOrderprice(),data.getCustomernum(),data.getOrderstatue(),null,data.getPayway(),data.getTips());
            orderMapper.insertOrder(order);
            //System.out.println(order.getOrderid());
            Map<String,String> result=new HashMap();
            result.put("orderid",String.valueOf(order.getOrderid()));
            result.put("creattime",order.getCreatetime());
            return new ResponseEntity(RespCode.SUCCESS,result);
        }
        catch (NumberFormatException e){
            System.out.println("数字格式错误");
            return new ResponseEntity(RespCode.WARN);
        }
        catch(RuntimeException e){
            return new ResponseEntity(RespCode.WARN);

        }

    }



}

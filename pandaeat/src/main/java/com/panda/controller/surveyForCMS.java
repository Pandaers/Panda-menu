package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.mapper.UserMapper;
import com.panda.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class surveyForCMS {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TimeUtil timeUtil;

    @RequestMapping(value = "/CMS/survey")
    public ResponseEntity survey(String storeid){
        Map<String,Integer> result =new HashMap();
        String today=timeUtil.getYearToDay();
        result.put("newOrder",orderMapper.countOrderOfToday(Integer.parseInt(storeid),today));
        result.put("allOrder",orderMapper.countAllOrder(Integer.parseInt(storeid)));
        result.put("newUser",userMapper.countNewUserOfToday(Integer.parseInt(storeid),today));
        return new ResponseEntity(RespCode.SUCCESS,result);

    }
}

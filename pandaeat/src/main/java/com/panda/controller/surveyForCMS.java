package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.mapper.OverviewMapper;
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
    @Autowired
    OverviewMapper overviewMapper;

    @RequestMapping(value = "/CMS/survey")
    public ResponseEntity survey(String storeid){
        Map<String,String> result =new HashMap();
        String today=timeUtil.getYearToDay();
        result.put("newOrder",String.valueOf(orderMapper.countOrderOfToday(Integer.parseInt(storeid),today)));
        result.put("allOrder",String.valueOf(orderMapper.countAllOrder(Integer.parseInt(storeid))));
        result.put("newUser",String.valueOf(userMapper.countNewUserOfToday(Integer.parseInt(storeid),today)));

        result.put("turnover",overviewMapper.selectTurnover(today));
        return new ResponseEntity(RespCode.SUCCESS,result);

    }
}

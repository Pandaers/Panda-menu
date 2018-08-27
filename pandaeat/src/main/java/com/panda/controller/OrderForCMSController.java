package com.panda.controller;

import com.github.pagehelper.PageHelper;
import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.model.OrderForCMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderForCMSController {
    @Autowired
    OrderMapper orderMapper;
    Integer currentPage;


    @RequestMapping(value = "CMS/pendingOrder")
    public ResponseEntity selectOrderListForCMS(Integer currentPage){
        List<OrderForCMS> result = new ArrayList();
        PageHelper.startPage(currentPage, 20);
        result=orderMapper.selectOrderForCMS();
        return new ResponseEntity(RespCode.SUCCESS,result);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}

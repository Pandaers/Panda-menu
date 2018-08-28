package com.panda.controller;

import com.github.pagehelper.PageHelper;
import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.model.OrderForCMS;
import com.panda.service.catNameReform.CatNameReform;
import com.panda.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderForCMSController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TimeUtil timeUtil;
    @Autowired
    CatNameReform catNameReform;


    Integer currentPage;
    Integer orderid;
    Integer storeid;

    /*
    * 返回出未上菜和未处理完成的订单
    * */
    @RequestMapping(value = "CMS/pendingOrder")
    public ResponseEntity selectOrderListForCMS(Integer currentPage,Integer storeid){
        List<OrderForCMS> result = new ArrayList();
        PageHelper.startPage(currentPage, 20);
        result=orderMapper.selectOrderForCMS(storeid);

        if(result==null){
            return new ResponseEntity(RespCode.ERROR);
        }
        for(OrderForCMS list:result){
            list.setOrdercontent(catNameReform.catNameReform(list.getOrdercontent()));
        }

        return new ResponseEntity(RespCode.SUCCESS,result);
    }
    /*
    *返回出所有订单信息
     */
    @RequestMapping(value = "CMS/AllOrder")
    public ResponseEntity selectAllOrderListForCMS(Integer currentPage,Integer storeid){
        List<OrderForCMS> result = new ArrayList();
        PageHelper.startPage(currentPage, 20);
        result=orderMapper.selectAllOrderForCMS(storeid);
        if(result==null){
            return new ResponseEntity(RespCode.ERROR);
        }
        for(OrderForCMS list:result){
            list.setOrdercontent(catNameReform.catNameReform(list.getOrdercontent()));
        }
        return new ResponseEntity(RespCode.SUCCESS,result);
    }

    /*
    * 返回所给orderid所对应的订单
    **/
    @RequestMapping(value = "CMS/SingleOrder")
    public ResponseEntity selectSingleOrderForCMS(Integer orderid,Integer storeid){
        if(orderid==null){
            return new ResponseEntity(RespCode.WRONG);
        }
        OrderForCMS result =new OrderForCMS();
        result=orderMapper.selectSingleOrderForCMS(orderid,storeid);
        if(result==null){
            return new ResponseEntity(RespCode.ERROR);
        }
        result.setOrdercontent(catNameReform.catNameReform(result.getOrdercontent()));
        return new ResponseEntity(RespCode.SUCCESS,result);
    }

    /*
    * 删除所选订单(update delstatue = 1)
    * */
    @RequestMapping(value = "CMS/delOrder")
    public  ResponseEntity delOrder(Integer orderid){
        if(orderMapper.countOrderByOrderid(orderid)==0){
            return new ResponseEntity(RespCode.ERROR);
        }
        orderMapper.delOrderByOrderid(orderid,timeUtil.getNowTime());
        return new ResponseEntity(RespCode.SUCCESS);
    }


    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}

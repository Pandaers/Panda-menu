package com.panda.controller;

import com.github.pagehelper.PageHelper;
import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.mapper.OverviewMapper;
import com.panda.model.FoodName;
import com.panda.model.Order;
import com.panda.model.OrderForCMS;
import com.panda.service.AmountUtils.AmoutUtils;
import com.panda.service.foodNameReform.FoodNameReform;
import com.panda.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    FoodNameReform foodNameReform;
    @Autowired
    AmoutUtils amoutUtils;
    @Autowired
    OverviewMapper overviewMapper;



    Integer currentPage;
    Integer orderid;
    Integer storeid;

    /*
    * 返回出未上菜和未处理完成的订单
    * */
    @RequestMapping(value = "CMS/pendingOrder")
    public ResponseEntity selectOrderListForCMS(Integer currentPage,Integer storeid){
        List<OrderForCMS> result = new ArrayList();
        //对result分页
        PageHelper.startPage(currentPage, 20);
        result=orderMapper.selectOrderForCMS(storeid);

        if(result==null){
            return new ResponseEntity(RespCode.ERROR);
        }
        for(OrderForCMS list:result){
            //转化订单详情为实际菜品名
           list.setOrdercontent(foodNameReform.foodNameReform(list.getOrdercontent(),storeid));
        }

        return new ResponseEntity(RespCode.SUCCESS,result);
    }
    /*
    *返回出所有订单信息
     */
    @RequestMapping(value = "CMS/AllOrder")
    public ResponseEntity selectAllOrderListForCMS(Integer currentPage,Integer storeid){
        List<OrderForCMS> result = new ArrayList();
        //对result分页
        PageHelper.startPage(currentPage, 20);
        result=orderMapper.selectAllOrderForCMS(storeid);
        if(result==null){
            return new ResponseEntity(RespCode.ERROR);
        }
        for(OrderForCMS list:result){
            //转化订单详情为实际菜品名
            list.setOrdercontent(foodNameReform.foodNameReform(list.getOrdercontent(),storeid));
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
        Order result =new Order();
        result=orderMapper.selectSingleOrderForCMS(orderid,storeid);
        if(result==null){
            return new ResponseEntity(RespCode.ERROR);
        }
        result.setOrdercontent(foodNameReform.foodNameReform(result.getOrdercontent(),storeid));
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
        orderMapper.delOrderByOrderid(orderid);
        return new ResponseEntity(RespCode.SUCCESS);
    }
    /*
    * 更新所选订单
    * */
    @Transactional
    @RequestMapping(value = "CMS/updateOrder")
    public  ResponseEntity delOrder(Order order) throws Exception {
        try{
            orderMapper.updateOrder(order);
            if(order.getOrderstatue()==3){

                Integer turnover=amoutUtils.changeY2F(orderMapper.selectOrderprice(order.getOrderid()));
                String todaydate=order.getCreatetime().substring(0,10);
                if(overviewMapper.countOverviewExist(order.getStoreid(),todaydate)>0)
                    overviewMapper.updateOverview(order.getStoreid(),todaydate,turnover,timeUtil.getNowTime());
                else
                    overviewMapper.insertOverview(order.getStoreid(),todaydate,turnover,timeUtil.getNowTime());
            }
            return new ResponseEntity(RespCode.SUCCESS);
        }catch (RuntimeException e){
            return new ResponseEntity(RespCode.WARN);
        }

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

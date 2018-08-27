package com.panda.mapper;

import com.panda.model.Order;
import com.panda.model.OrderForCMS;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "orderMapper")
//private Integer storeid;
//private Integer seatid;
//private Integer orderid;
//private Integer userid;
//private String createtime;
//private Ordercontent ordercontent;
//private String orderprice;
//private Integer customernum;
//private short orderstatue;
//private String endtime;
//private short payway;

public interface OrderMapper {
    @Insert("insert into pe_order (storeid,seatid,orderid,userid,createtime,ordercontent," +
            "orderprice,customernum,orderstatue,endtime,payway)" +
            "values(#{order.storeid},#{order.seatid},#{order.orderid},#{order.userid}," +
            "#{order.createtime},#{order.ordercontent},#{order.orderprice},#{order.customernum}," +
            "#{order.orderstatue},#{order.endtime},#{order.payway})")
    void insertOrder(@Param("order") Order order);

    /*
    * 选择未处理和未上菜的订单，按时间倒序
    * */
    @Select("select orderid,ordercontent,tips,seatid,orderprice,orderstatue,dishstatue,createtime,endtime from " +
            "pe_order where delstatue=0 and (dishstatue=0 or orderstatue!=3) order by createtime desc")
    List<OrderForCMS> selectOrderForCMS();

    /*
    * 选择出所有订单，按时间倒序
    * */
    @Select("select orderid,ordercontent,tips,seatid,orderprice,orderstatue,dishstatue,createtime,endtime from " +
            "pe_order  where delstatue=0 order by createtime desc")
    List<OrderForCMS> selectAllOrderForCMS();

    /*
     * 选择出所需单个订单所有信息
     * */
    @Select("select * from pe_order where orderid=#{orderid} order by createtime desc limit 1")
    OrderForCMS selectSingleOrderForCMS(@Param("orderid") Integer orderid);

    @Select("SELECT COUNT(*) FROM pe_order WHERE orderid=#{orderid} and delstatue=0")
    Integer countOrderByOrderid(@Param("orderid") Integer orderid);

    @Update("update pe_order set delstatue=1, endtime=#{endtime} where orderid=#{orderid}")
    void delOrderByOrderid(@Param("orderid") Integer orderid,@Param("endtime") String endtime);
}


package com.panda.mapper;

import com.panda.model.Order;
import com.panda.model.OrderForCMS;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select orderid,ordercontent,tips,seatid,orderprice,orderstatue,dishstatue,createtime from pe_order where dishstatue=0 or orderstatue!=3 order by createtime desc")
    List<OrderForCMS> selectOrderForCMS();
}


package com.panda.mapper;

import com.panda.model.FoodName;
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
    @Select("select orderid,ordercontent,tips,seatid,orderprice,orderstatue,dishstatue,createtime,endtime,payway from " +
            "pe_order where storeid=#{storeid} and delstatue=0 and (dishstatue=0 or orderstatue=0) order by createtime desc")
    List<OrderForCMS> selectOrderForCMS(@Param("storeid") Integer storeid);

    /*
    * 选择出所有订单，按时间倒序
    * */
    @Select("select orderid,ordercontent,tips,seatid,orderprice,orderstatue,dishstatue,createtime,endtime,payway from " +
            "pe_order  where storeid=#{storeid} and delstatue=0 order by createtime desc")
    List<OrderForCMS> selectAllOrderForCMS(@Param("storeid") Integer storeid);

    /*
     * 选择出所需单个订单所有信息
     * */
    @Select("select * from pe_order where storeid=#{storeid} and orderid=#{orderid} and delstatue=0 order " +
            "by createtime desc limit 1")
    Order selectSingleOrderForCMS(@Param("orderid") Integer orderid,@Param("storeid") Integer storeid);

    /*
     * 删除单个订单（计数验证）
     * */
    @Select("SELECT COUNT(*) FROM pe_order WHERE orderid=#{orderid} and delstatue=0")
    Integer countOrderByOrderid(@Param("orderid") Integer orderid);
    /*
     * 删除单个订单
     * */
    @Update("update pe_order set delstatue=1 where orderid=#{orderid}")
    void delOrderByOrderid(@Param("orderid") Integer orderid);

    /*
    * 选择菜品的名字与id
    * */
    @Select("select id,name from pe_food where storeid=#{storeid} and status=1 and isdelete=0 order by addtime desc")
    List<FoodName> foodNameReform (@Param("storeid")Integer storeid);

    /*
    * 返回该商店的总订单数
    * */
    @Select("select count(*) from pe_order where storeid=#{storeid} and delstatue=0")
    Integer countAllOrder(@Param("storeid")Integer storeid);

    /*
    * 返回今日订单数
    * */
    @Select("select count(*) from pe_order where storeid=#{storeid} and delstatue=0 and createtime>#{today}")
    Integer countOrderOfToday(@Param("storeid")Integer storeid,@Param("today")String today);
    /*
    * 返回订单金额
    * */
    @Select("select orderprice from pe_order where orderid=#{orderid}")
    String selectOrderprice(@Param("orderid")Integer orderid);
    /*
    * 更新数据
    * */
    @Update("update pe_order set tips=#{order.tips},seatid=#{order.seatid}," +
            "orderstatue=#{order.orderstatue},dishstatue=#{order.dishstatue} where orderid=#{order.orderid}")
    void updateOrder(@Param("order")Order order);
    /*
    *
    * */

}


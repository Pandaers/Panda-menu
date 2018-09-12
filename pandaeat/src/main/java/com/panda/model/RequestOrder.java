package com.panda.model;

import java.util.Map;

public class RequestOrder {
    String paywaystring;
    short payway;
    String seatid;
    String tips;
    String storeid;
    String userid;
    String  ordercontent;
    String orderprice;
    Integer customernum;
    short orderstatue;
    String orderid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public short getOrderstatue() {
        return orderstatue;
    }

    public void setOrderstatue(short orderstatue) {
        this.orderstatue = orderstatue;
    }

    public Integer getCustomernum() {
        return customernum;
    }

    public void setCustomernum(Integer customernum) {
        this.customernum = customernum;
    }

    public String getPaywaystring() {
        return paywaystring;
    }

    public void setPaywaystring(String paywaystring) {
        this.paywaystring = paywaystring;
    }

    public short getPayway() {
        return payway;
    }

    public void setPayway(short payway) {
        this.payway = payway;
    }

    public String getSeatid() {
        return seatid;
    }

    public void setSeatid(String seatid) {
        this.seatid = seatid;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrdercontent() {
        return ordercontent;
    }

    public void setOrdercontent(String ordercontent) {
        this.ordercontent = ordercontent;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }
}

package com.panda.model;

import java.util.Map;

public class RequestOrder {
    String paywaystring;
    Integer payway;
    String seatid;
    String tips;
    String storeid;
    String userid;
    String  ordercontent;
    String orderprice;

    public String getPaywaystring() {
        return paywaystring;
    }

    public void setPaywaystring(String paywaystring) {
        this.paywaystring = paywaystring;
    }

    public Integer getPayway() {
        return payway;
    }

    public void setPayway(Integer payway) {
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

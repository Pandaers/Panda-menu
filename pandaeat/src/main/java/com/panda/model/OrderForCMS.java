package com.panda.model;

public class OrderForCMS {
    private Integer orderid;
    private String ordercontent;
    private String tips;
    private Integer seatid;
    private String orderprice;
    private short orderstatue;
    private short payway;
    private short dishstatue;
    private String createtime;
    private String endtime;
    private short delstatue;

    public short getPayway() {
        return payway;
    }

    public void setPayway(short payway) {
        this.payway = payway;
    }

    public short getDelstatue() {
        return delstatue;
    }

    public void setDelstatue(short delstatue) {
        this.delstatue = delstatue;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getOrdercontent() {
        return ordercontent;
    }

    public void setOrdercontent(String ordercontent) {
        this.ordercontent = ordercontent;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public short getOrderstatue() {
        return orderstatue;
    }

    public void setOrderstatue(short orderstatue) {
        this.orderstatue = orderstatue;
    }

    public short getDishstatue() {
        return dishstatue;
    }

    public void setDishstatue(short dishstatue) {
        this.dishstatue = dishstatue;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}

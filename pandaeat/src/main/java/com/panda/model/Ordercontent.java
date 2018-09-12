package com.panda.model;

public class Ordercontent {
    private Integer orderid;
    private String createtime;
    private String orderprice;
    private Integer orderstatue;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public Integer getOrderstatue() {
        return orderstatue;
    }

    public void setOrderstatue(Integer orderstatue) {
        this.orderstatue = orderstatue;
    }
}

package com.panda.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 14:26
 * @Description:
 */
public class RequestUser {
    private Integer storeid;
    private Integer openid;
    private Integer createtime;
    private String nickname;
    private String avatar;
    private String mobile;
    private short gender;
    private Integer ordernum;
    private String orderprice;

    public RequestUser(Integer storeid, Integer openid, Integer createtime, String nickname, String avatar, String mobile, short gender, Integer ordernum, String orderprice) {
        this.storeid = storeid;
        this.openid = openid;
        this.createtime = createtime;
        this.nickname = nickname;
        this.avatar = avatar;
        this.mobile = mobile;
        this.gender = gender;
        this.ordernum = ordernum;
        this.orderprice = orderprice;
    }

    public Integer getStoreid() {
        return storeid;
    }


    public Integer getOpenid() {
        return openid;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public short getGender() {
        return gender;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }
}

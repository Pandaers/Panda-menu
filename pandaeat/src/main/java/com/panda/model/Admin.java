package com.panda.model;

public class Admin {
    private Integer adminid;
    private Integer storeid;
    private String name;
    private String password;
    private String createtime;



    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCreatetime() {
        return createtime;
    }
}

package com.panda.model;

public class Cat {
    private Integer catid;
    private Integer storeid;
    private Integer createtime;
    private String catname;


    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public Integer getCatid() {
        return catid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public String getCatname() {
        return catname;
    }
}

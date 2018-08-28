package com.panda.model;

import java.math.BigDecimal;

public class FoodForCMS {
    private Integer id;
    private Integer storeid;
    private String name;
    private BigDecimal price;
    private String detail;
    private Integer catid;
    private Integer status;
    private String compressimg;
    private Integer realsales;
    private String catname;

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompressimg() {
        return compressimg;
    }

    public void setCompressimg(String compressimg) {
        this.compressimg = compressimg;
    }

    public Integer getRealsales() {
        return realsales;
    }

    public void setRealsales(Integer realsales) {
        this.realsales = realsales;
    }
}

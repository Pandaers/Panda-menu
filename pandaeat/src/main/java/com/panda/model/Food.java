package com.panda.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Food {
    private Integer id;
    private Integer storeid;
    private String name;
    private BigDecimal price;
    private BigDecimal originalprice;
    private String detail;
    private Integer catid;
    private Integer status;
    private Integer addtime;
    private Integer score;
    private Integer isdelete;
    private Integer virtualsales;
    private String compressimg;
    private String img;
    private String videourl;
    private Integer realsales;
    private Integer goodsnums;
    private String catname;

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public Integer getGoodsnums() {
        return goodsnums;
    }

    public void setGoodsnums(Integer goodsnums) {
        this.goodsnums = goodsnums;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOriginalprice(BigDecimal originalprice) {
        this.originalprice = originalprice;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public void setVirtualsales(Integer virtualsales) {
        this.virtualsales = virtualsales;
    }


    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public void setRealsales(Integer realsales) {
        this.realsales = realsales;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getOriginalprice() {
        return originalprice;
    }

    public String getDetail() {
        return detail;
    }

    public Integer getCatid() {
        return catid;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public Integer getVirtualsales() {
        return virtualsales;
    }

    public String getCompressimg() {
        return compressimg;
    }

    public void setCompressimg(String compressimg) {
        this.compressimg = compressimg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideourl() {
        return videourl;
    }

    public Integer getRealsales() {
        return realsales;
    }


}

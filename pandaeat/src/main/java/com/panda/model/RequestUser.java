package com.panda.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 14:26
 * @Description:
 */
public class RequestUser {

    private String storeid;
    private String code;
    private String nickname;
    private String avatar;
    private String mobile;
    private String gender;

    public RequestUser(String storeid, String code, String nickname, String avatar, String mobile, String gender) {
        this.storeid = storeid;
        this.code = code;
        this.nickname = nickname;
        this.avatar = avatar;
        this.mobile = mobile;
        this.gender = gender;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

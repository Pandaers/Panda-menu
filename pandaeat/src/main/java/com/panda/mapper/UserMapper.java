package com.panda.mapper;

import com.panda.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 13:50
 * @Description:
 */

@Mapper
@Component(value = "userMapper")
public interface UserMapper {

//    private Integer storeid;
//    private Integer userid;
//    private Integer openid;
//    private Integer createtime;
//    private String nickname;
//    private String avatar;
//    private String mobile;
//    private short gender;
//    private Integer ordernum;
//    private String orderprice;

    @Insert("INSERT INTO  pe_user(storeid,openid,createtime,nickname,avatar,mobile,gender,ordernum,orderprice) " +
            "VALUES(#{user.storeid},#{user.openid},#{user.createtime},#{user.nickname},#{user.avatar},#{user.mobile},#{user.gender},#{user.ordernum},#{user.orderprice});")
    @Options(useGeneratedKeys = true, keyProperty = "user.userid")
    void insertUserData(@Param("user")User user);

}
package com.panda.mapper;

import com.panda.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Locale;

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
    /*
    * 插入新用户数据
    * */
    @Insert("INSERT INTO  pe_user(storeid,openid,createtime,nickname,avatar,mobile,gender,ordernum,orderprice) " +
            "VALUES(#{user.storeid},#{user.openid},#{user.createtime},#{user.nickname},#{user.avatar},#{user.mobile},#{user.gender},#{user.ordernum},#{user.orderprice});")
    @Options(useGeneratedKeys = true, keyProperty = "user.userid")
    void insertUserData(@Param("user")User user);
    /*
    * 检查该用户是否存在
    * */
    @Select("SELECT COUNT(openid) FROM pe_user WHERE openid=#{openid} and storeid=#{storeid}")
    Integer selectUersOpenId(@Param("openid")String openid,@Param("storeid")String storeid);

    /*
    * 返回用户数据
    * */
    @Select("select userid,nickname,openid from pe_user where openid=#{openid} LIMIT 1")
    User selectUersDataByOpenId(@Param("openid")String openid);

    /*
    * 返回今日新增的用户数
    * */
    @Select("select count(*) from pe_user where createtime>#{today} and storeid=#{storeid}")
    Integer countNewUserOfToday(@Param("storeid")Integer storeid,@Param("today")String today);
    /*
     *根据openID返回用户id
     * */
    @Select("select userid from pe_user where openid=#{openid} limit 1")
    Integer selectUseridByOpenid(@Param("openid")String openid);





}

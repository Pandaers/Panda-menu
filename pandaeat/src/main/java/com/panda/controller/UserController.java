package com.panda.controller;

import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.UserMapper;
import com.panda.model.RequestUser;
import com.panda.model.User;
import org.apache.ibatis.javassist.compiler.MemberResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 13:47
 * @Description:
 */

@RestController
public class UserController {


    //mapper注入
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    public ResponseEntity insertUser2(RequestUser data){
        //拟合要插入的数据
        User user = new User(data.getStoreid(),0,data.getOpenid(),data.getCreatetime(),data.getNickname()
        ,data.getAvatar(),data.getMobile(),data.getGender(),data.getOrdernum(),data.getOrderprice());

       userMapper.insertUserData(user);

        Map result = new HashMap();
        result.put("userid",user.getUserid());
        result.put("nickname",user.getNickname());


        return new ResponseEntity(RespCode.SUCCESS,result);
    }
}

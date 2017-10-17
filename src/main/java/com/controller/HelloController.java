package com.controller;

import com.alibaba.fastjson.JSON;
import com.pojo.User;
import com.redis.JedisClient;
import com.service.UserService;
import com.util.SignUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PC on 2017/6/12.
 */
@Controller
public class HelloController {

    @Resource
   private UserService userService ;
    @Resource
    private JedisClient jedisClient;
    private  static Logger log=Logger.getLogger(HelloController.class);
    @RequestMapping(value="hello",method = RequestMethod.GET)
    @ResponseBody
    public List Hello(){
    log.info(JSON.toJSONString(userService.select()));
    //System.out.println(userService.select());
        jedisClient.get("aaa");
        //jedisClient.expire()
        List ls=userService.select();
       ls.add(jedisClient.get("aaa"));
        return ls;
    }


}

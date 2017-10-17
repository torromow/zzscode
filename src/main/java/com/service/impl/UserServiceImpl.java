package com.service.impl;

import com.dao.UserMapper;
import com.pojo.User;
import com.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PC on 2017/6/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> select() {
        return userMapper.select();
    }
}

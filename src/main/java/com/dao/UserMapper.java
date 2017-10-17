package com.dao;



import org.mybatis.spring.annotation.MapperScan;
import com.pojo.User;

import java.util.List;


@MapperScan
public interface UserMapper {


    List<User> select();

}